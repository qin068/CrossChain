package com.example.interchange.controller;

import com.example.interchange.pojo.Company;
import com.example.interchange.service.CompanyService;
import com.example.interchange.utils.ResponseUtil;
import com.example.interchange.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-06 5:22 PM
 */

@RestController
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/insertCompany")
    public Result insertCompany(String companyId,String companyName,String companyLogo){
        if(companyService.selectCompanyById(companyId) == null){
            companyService.insertCompany(new Company(companyId,companyName,companyLogo));
            return ResponseUtil.resp(200,"添加company成功",null);
        }else{
            return ResponseUtil.resp(200,"companyId重复",null);
        }
    }

    @GetMapping("/deleteCompany")
    public Result deleteCompany(String companyId){
        if(companyService.selectCompanyById(companyId) != null){
            companyService.deleteCompanyById(companyId);
            return ResponseUtil.resp(200,"删除company成功",null);
        }else{
            return ResponseUtil.resp(200,"companyId不存在",null);
        }
    }

    @GetMapping("updateCompany")
    public Result updateCompany(String companyId,String companyName,String companyLogo){
        if(companyService.selectCompanyById(companyId) != null){
            companyService.updateCompany(new Company(companyId,companyName,companyLogo));
            return ResponseUtil.resp(200,"更新company成功",null);
        }else{
            return ResponseUtil.resp(200,"companyId不存在",null);
        }
    }

    @GetMapping("/selectCompanyById")
    public Result selectCompanyById(String companyId){
        Company company = companyService.selectCompanyById(companyId);
        if(company == null){
            return ResponseUtil.resp(200,"commpanyId不存在",null);
        }else{
            return ResponseUtil.resp(200,"查询company成功",company);
        }
    }
    @GetMapping("/selectAllCompany")
    public Result selectAllCompany(){
        List<Company> res =  companyService.selectAllCompany();
        return ResponseUtil.resp(200,"查询company成功",res);
    }
}