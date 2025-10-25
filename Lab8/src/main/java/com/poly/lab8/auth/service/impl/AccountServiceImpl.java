package com.poly.lab8.auth.service.impl;

import com.poly.lab8.auth.entity.Account;
import com.poly.lab8.auth.repository.AccountRepository;
import com.poly.lab8.auth.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repo;

    @Override
    public Account findById(String username) {
        return repo.findById(username).orElse(null);
    }

    @Override
    public Account save(Account a) {
        return repo.save(a);
    }
}
