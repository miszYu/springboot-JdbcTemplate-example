package com.example.demo.dao.impl;

import com.example.demo.dao.BonusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BonusDaoImpl implements BonusDao {

    @Autowired
    @Qualifier("myjdbc2")
    private NamedParameterJdbcTemplate myjdbc2;

    @Override
    public void addBonus(Integer userid, Integer bonus) {
        String sql = "UPDATE bonus SET bonus_money = bonus_money + :bonus WHERE userid = :userid";

        Map<String, Object> map = new HashMap<>();
        map.put("userid", userid);
        map.put("bonus", bonus);

        myjdbc2.update(sql, map);
    }
}
