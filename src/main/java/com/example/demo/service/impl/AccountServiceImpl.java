package com.example.demo.service.impl;

import com.example.demo.dao.AccountDao;
import com.example.demo.service.AccountService;
import com.example.demo.service.AccountService2;
import com.example.demo.service.BonusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "myjdbc1TransactionManager")
public class AccountServiceImpl implements AccountService {

    private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountService2 accountService2;

    @Autowired
    private BonusService bonusService;

    @Override
    public void transfer(Integer fromAccountId, Integer toAccountId, Integer money) {

        // User A 扣除轉帳金額
        accountDao.decreaseMoney(fromAccountId, money);

        //Transactional看進入點是否有下@Transactional，而會走Transactiona。
        //accountService2沒有@Transactional。
        //可是進入點有@Transactional一樣會roll back
        accountService2.decreaseMoney(3,1);
        int i = 1/0;

        // User B 收到轉入金額
        accountDao.addMoney(toAccountId, money);
    }

    @Override
    public void transfer_bonus(Integer fromAccountId, Integer toAccountId, Integer money){
        // User A 扣除轉帳金額
        accountDao.decreaseMoney(fromAccountId, money);

        // User B 收到轉入金額
        accountDao.addMoney(toAccountId, money);

        // User A 增加紅利，此method中故意造成的ArithmeticException，若錯誤發生在此method中
        // 則Table:account、bonus會roll back，因為兩者的Transactional皆未完成
        bonusService.addBonus(fromAccountId, 10);

        // 若錯誤發生在此，則bonusService.addBonus()的Transactional已完成
        // 而AccountService.transfer_bonus()的交易未完成
        // 故account會roll back，bonus不會roll back
        // int i = 1/0;
    }

}
