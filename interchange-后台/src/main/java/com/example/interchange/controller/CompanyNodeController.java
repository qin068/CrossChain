package com.example.interchange.controller;

import com.example.interchange.pojo.CompanyNode;
import com.example.interchange.service.CompanyNodeService;
import com.example.interchange.utils.ResponseUtil;
import com.example.interchange.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-06 7:36 PM
 */

@RestController
public class CompanyNodeController {
    @Autowired
    CompanyNodeService companyNodeService;

    @GetMapping("/selectAllCompanyNode")
    public Result getAllCompanyNode(){
        return ResponseUtil.resp(200,"查询companynode成功",companyNodeService.selectAllCompanyNode());
    }

    @GetMapping("/selectCompanyNodeById")
    public Result getCompanyNodeById(String id){
        if(companyNodeService.selectCompanyNodeById(id) == null){
            return ResponseUtil.resp(200,"该id不存在",null);
        }else return ResponseUtil.resp(200,"查询companynode成功",companyNodeService.selectCompanyNodeById(id));
    }

    @GetMapping("/insertCompanyNode")
    public Result inserCompanyNode(String id,String companyId,String nd_name,String nd_location,String nd_state,String nd_height,String nd_hash,String nd_timestamp,String nd_code){
        List<CompanyNode> companyNodes = companyNodeService.selectAllCompanyNode();
        boolean flag = false;
        for(CompanyNode companyNode : companyNodes){
            if(companyNode.getNd_name().equals(nd_name)){
                flag = true;
                break;
            }
        }
        if(!flag){
            return ResponseUtil.resp(200,"插入成功",companyNodeService.insertCompanyNode(new CompanyNode(id,companyId,nd_name,nd_location,nd_state,nd_height,nd_hash,nd_timestamp,nd_code)));
        }else{
            return ResponseUtil.resp(200,"id已存在",null);
        }
    }
    @GetMapping("/deleteCompanyNode")
    public Result deleteCompanyNode(String id){
        if(companyNodeService.selectCompanyNodeById(id) == null){
            return ResponseUtil.resp(200,"id不存在",null);
        }else{
            return ResponseUtil.resp(200,"删除成功",companyNodeService.deleteCompanyNode(id));
        }
    }
    @GetMapping("/updateCompanyNode")
    public Result updateCompanyNode(String id,String companyId,String nd_name,String nd_location,String nd_state,String nd_height,String nd_hash,String nd_timestamp,String nd_code){
        if(companyNodeService.selectCompanyNodeById(id) == null){
            return ResponseUtil.resp(200,"id不存在",null);
        }else{
            return ResponseUtil.resp(200,"更新成功",companyNodeService.updateCompanyNode(new CompanyNode(id,companyId,nd_name,nd_location,nd_state,nd_height,nd_hash,nd_timestamp,nd_code)));
        }
    }
}
