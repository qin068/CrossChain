package com.example.interchange.controller;

import com.example.interchange.pojo.Account;
import com.example.interchange.pojo.Channel;
import com.example.interchange.service.AccountService;
import com.example.interchange.service.AccountServiceIml;
import com.example.interchange.utils.ResponseUtil;
import com.example.interchange.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 秃头小刘
 * @date 2021-10-19 3:21 PM
 */

@RestController
@SuppressWarnings("all")
public class AccountController {
    @Autowired
    AccountService accountService;

    Set<String> set = new HashSet<String>(){
        {
            add("CompanyA");
            add("CompanyB");
            add("CompanyC");
        }
    };

    @GetMapping("/startServe")
    public Result getAllchannel(String chain){
        if(!set.contains(chain)) return ResponseUtil.resp(400,chain +"输入的链ID不存在","");
        List<Account> res = null;
        try {
            res = accountService.getAccount(chain);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.resp(202,chain +"链启动失败","");
        }
        return ResponseUtil.resp(200,chain +"链启动成功",res);
    }

    @GetMapping("/selectAllAccount")
    public Result getAllAccount(){
        return ResponseUtil.resp(200,"获取账户信息成功",accountService.selectAllAccount());
    }
}
