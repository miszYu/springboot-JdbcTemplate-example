package com.example.demo.service;

public interface AccountService {

    void transfer(Integer fromAccountId, Integer toAccountId, Integer money);

    void transfer_bonus(Integer fromAccountId, Integer toAccountId, Integer money);
}
