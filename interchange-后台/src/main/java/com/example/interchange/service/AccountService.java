package com.example.interchange.service;

import com.example.interchange.pojo.Account;
import com.example.interchange.pojo.BlockAccount;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2021-10-19 2:12 PM
 */


public interface AccountService {
    public List<Account> getAccount(String chain);

    public List<Account> selectAllAccount();
}
