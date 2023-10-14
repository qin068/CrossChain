package com.example.interchange.controller;

import com.example.interchange.pojo.Title;
import com.example.interchange.service.TitleService;
import com.example.interchange.utils.ResponseUtil;
import com.example.interchange.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-13 8:40 PM
 */

@RestController
public class TitleController {
    @Autowired
    TitleService titleService;

    @GetMapping("/getAllTitle")
    public Result getAllTitle(){
        return ResponseUtil.resp(200,"查询成功",titleService.selectAllTitle());
    }

    @GetMapping("/getTitleById")
    public Result getTitleById(String title){
        return ResponseUtil.resp(200,"查询成功",titleService.selectTitleById(title));
    }

    @GetMapping("/insertTitle")
    public Result insertTitle(String title, String content, String height,String txhash,String timestamp){
        List<Title> titles = titleService.selectAllTitle();
        boolean flag = false;
        for(Title title1 : titles){
            if(title1.getTitle().equals(title)){
                flag = true;
                break;
            }
        }
        if(!flag){
            return ResponseUtil.resp(200,"插入成功",titleService.insertTitle(new Title(title,content,height,txhash,timestamp)));
        }else{
            return ResponseUtil.resp(200,"节点名称已存在，请更换",null);
        }

    }
}
