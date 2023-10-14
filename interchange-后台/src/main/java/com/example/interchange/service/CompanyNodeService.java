package com.example.interchange.service;

import com.example.interchange.pojo.Company;
import com.example.interchange.pojo.CompanyNode;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-06 7:33 PM
 */


public interface CompanyNodeService {
    List<CompanyNode> selectAllCompanyNode();

    CompanyNode selectCompanyNodeById(String id);
    int insertCompanyNode(CompanyNode companyNode);
    int deleteCompanyNode(String id);
    int updateCompanyNode(CompanyNode companynode);
}
