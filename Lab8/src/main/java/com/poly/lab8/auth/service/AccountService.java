package com.poly.lab8.auth.service;

import com.poly.lab8.auth.entity.Account;

public interface AccountService {
    Account findById(String username);
    Account save(Account a);
}
