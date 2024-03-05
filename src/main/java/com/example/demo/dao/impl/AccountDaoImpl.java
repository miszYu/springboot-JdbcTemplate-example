package com.example.demo.dao.impl;

import com.example.demo.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    @Qualifier("myjdbc1")
    private NamedParameterJdbcTemplate myjdbc1;

    @Override
    public void decreaseMoney(Integer userid, Integer money) {
        String sql = "UPDATE account SET balance = balance - :money WHERE userid = :userid";

        Map<String, Object> map = new HashMap<>();
        map.put("userid", userid);
        map.put("money", money);

        myjdbc1.update(sql, map);
    }

    @Override
    public void addMoney(Integer userid, Integer money) {
        String sql = "UPDATE account SET balance = balance + :money WHERE userid = :userid";

        Map<String, Object> map = new HashMap<>();
        map.put("userid", userid);
        map.put("money", money);

        myjdbc1.update(sql, map);
    }
}
