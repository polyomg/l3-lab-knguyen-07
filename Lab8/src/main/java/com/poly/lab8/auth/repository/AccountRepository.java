package com.poly.lab8.auth.repository;


import com.poly.lab8.auth.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
