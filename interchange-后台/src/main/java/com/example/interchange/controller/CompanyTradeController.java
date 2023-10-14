package com.example.interchange.controller;

import com.example.interchange.pojo.CompanyTrade;
import com.example.interchange.service.CompanyTradeService;
import com.example.interchange.utils.ResponseUtil;
import com.example.interchange.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 秃头小刘
 * @date 2022-05-06 8:41 PM
 */

@RestController
public class CompanyTradeController {
    @Autowired
    CompanyTradeService companyTradeService;

    @GetMapping("/selectAllCompanyTrade")
    public Result selectAllCompanyTrade(){
        return ResponseUtil.resp(200,"查询companytrade成功",companyTradeService.seleceAllCompanyTrade());
    }

    @GetMapping("/selectCompanyTradeById")
    public Result selectCompanyTradeById(String id){
        if (companyTradeService.selectCompanyTradeById(id) == null){
            return ResponseUtil.resp(200,"该id不存在",null);
        }else {
            return ResponseUtil.resp(200,"查询companytrade成功",companyTradeService.selectCompanyTradeById(id));
        }
    }

    @GetMapping("/deleteCompanyTrade")
    public Result deleteComPanyTrade(String id){
        if(companyTradeService.selectCompanyTradeById(id) == null){
            return ResponseUtil.resp(200,"该id不存在",null);
        }else{
            return ResponseUtil.resp(200,"删除成功",companyTradeService.deleteCompanyTrade(id));
        }
    }

    @GetMapping("/insertCompanyTrade")
    public Result insertCompanyTrade(String id,String companyId,String td_type,String td_ip,String td_valid,String td_height,String td_hash,String td_timestamp,String td_code){
        if(companyTradeService.selectCompanyTradeById(id) != null){
            return ResponseUtil.resp(200,"该id已经存在",null);
        }else{
            return ResponseUtil.resp(200,"插入成功",companyTradeService.insertCompanyTrade(new CompanyTrade(id,companyId,td_type,td_ip,td_valid,td_height,td_hash,td_timestamp,td_code)));
        }
    }

    @GetMapping("/updateCompanyTrade")
    public Result updateCompanyTrade(String id,String companyId,String td_type,String td_ip,String td_valid,String td_height,String td_hash,String td_timestamp,String td_code){
        if(companyTradeService.selectCompanyTradeById(id) == null){
            return ResponseUtil.resp(200,"输入id不存在",null);
        }else{
            return ResponseUtil.resp(200,"更新成功",companyTradeService.updateCompanyTrade(new CompanyTrade(id,companyId,td_type,td_ip,td_valid,td_height,td_hash,td_timestamp,td_code)));
        }
    }
}
