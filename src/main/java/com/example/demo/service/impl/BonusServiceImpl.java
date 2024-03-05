package com.example.demo.service.impl;

import com.example.demo.dao.BonusDao;
import com.example.demo.service.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "myjdbc2TransactionManager")
public class BonusServiceImpl implements BonusService {

    @Autowired
    private BonusDao bonusDao;

    @Override
    public void addBonus(Integer userid, Integer bonus){
        bonusDao.addBonus(userid, bonus);

        int i = 1/0;
    }
}
