package com.example.interchange.controller;

import com.example.interchange.pojo.CompanyTotal;
import com.example.interchange.service.CompanyTotalService;
import com.example.interchange.utils.ResponseUtil;
import com.example.interchange.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 秃头小刘
 * @date 2022-05-06 9:36 PM
 */

@RestController
public class CompanyTotalController {
    @Autowired
    CompanyTotalService companyTotalService;

    @GetMapping("/selectAllCompanyTotal")
    public Result selectAllCompanyToal(){
        return ResponseUtil.resp(200,"查询成功",companyTotalService.selectAllCompanyTotal());
    }

    @GetMapping("/selectCompanyTotalById")
    public Result selectCompanyTotalById(String id){
        return ResponseUtil.resp(200,"查询成功",companyTotalService.selectComanyTotalById(id));
    }

    @GetMapping("/deleteCompanyTotal")
    public Result deleteCompanyTotal(String id){
        if(companyTotalService.selectComanyTotalById(id) == null){
            return ResponseUtil.resp(200,"该id不存在",null);
        }else{
            return ResponseUtil.resp(200,"删除成功",companyTotalService.deleteCompanyTotal(id));
        }
    }

    @GetMapping("insertCompanyTotal")
    private Result insertCompanyTotal(String id,String tt_count,String tt_height,String tt_peak,String tt_trade){
        if(companyTotalService.selectComanyTotalById(id) != null){
            return ResponseUtil.resp(200,"该id已存在",null);
        }else{
            return ResponseUtil.resp(200,"添加成功",companyTotalService.insertCompanyTotal(new CompanyTotal(id,tt_count,tt_height,tt_peak,tt_trade)));
        }
    }

    @GetMapping("/updateCompanyTotal")
    public Result updateCompanyTotal(String id,String tt_count,String tt_height,String tt_peak,String tt_trade){
        if(companyTotalService.selectComanyTotalById(id) == null){
            return ResponseUtil.resp(200,"该id不存在",null);
        }else{
            return ResponseUtil.resp(200,"添加成功",companyTotalService.updateCompanyTotal(new CompanyTotal(id,tt_count,tt_height,tt_peak,tt_trade)));
        }
    }
}
