package com.example.interchange.service;

import com.example.interchange.info.ChainConfigInfo;
import com.example.interchange.mapper.BlockAccountMapper;
import com.example.interchange.pojo.Account;
import com.example.interchange.pojo.BlockAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 秃头小刘
 * @date 2021-10-19 2:13 PM
 */

@SuppressWarnings("all")
@Service
public class AccountServiceIml implements AccountService{

    @Autowired
    BlockAccountMapper blockAccountMapper;


    @Override
    public List<Account> getAccount(String chain) {
        String command = "starport chain serve -c " + chain + ".yml" + " -r";
//        String command = "ls";
        List<String> strings = runCommands(command);
        List<Account> accounts = resToAccount(strings, chain);

        for(Account account : accounts){
            String[] words = account.getWords();
            String target = "";
            for(int i = 0; i < words.length; ++i){
                target += words[i];
                if(i != words.length - 1) target += "-";
            }
            blockAccountMapper.insertBlockAccount(new BlockAccount(account.getName(),account.getAddress(),account.getChain(),target));
        }


        return accounts;
    }

    @Override
    public List<Account> selectAllAccount() {
        List<BlockAccount> accounts = blockAccountMapper.selectAllAccount();

        List<Account> res = new ArrayList<>();
        for(BlockAccount blockAccount : accounts){
            String[] words = blockAccount.getWords().split("-");
            Account account = new Account(blockAccount.getName(),blockAccount.getAddress(),blockAccount.getChain(),words);
            res.add(account);
        }
        return res;
    }



    private List<Account> resToAccount(List<String> list,String chain){
        List<Account> res = new ArrayList<>();
        for (String str : list) {
            if(str.contains("Created")){
                String name = "#";
                String address ="#";
                String[] word = null;
                List<Integer> cnt = new ArrayList<>();
                for(int i = 0; i < str.length(); ++i) if(str.charAt(i) == '"') cnt.add(i);
                if(cnt.size() < 6) continue;
                name = str.substring(cnt.get(0) + 1,cnt.get(1));
                address = str.substring(cnt.get(2) + 1,cnt.get(3));
                word = str.substring(cnt.get(4) + 1,cnt.get(5)).split(" ");
                Account account = new Account(name,address,chain,word);
                res.add(account);
            }
        }
        return res;
    }
    private List<String> runCommands(String command){
//        String path = "/Users/liuxingda/tendmint/interchange";
        String path = ChainConfigInfo.ChainFilePath;

        System.out.println(command);

        Process process = null;
        List<String> processList = new ArrayList<String>();

        try {
            process = Runtime.getRuntime().exec(command,null,new File(path));

            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = "";
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                if(line.contains("Tendermint node")) break;
                processList.add(line);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //process.destroy();
        }

        return processList;
    }
}
