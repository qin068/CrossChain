package com.example.interchange.service;

import com.example.interchange.pojo.CompanyTotal;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-06 9:32 PM
 */


public interface CompanyTotalService {

    List<CompanyTotal> selectAllCompanyTotal();

    CompanyTotal selectComanyTotalById(String id);
    int insertCompanyTotal(CompanyTotal companyTotal);
    int deleteCompanyTotal(String id);
    int updateCompanyTotal(CompanyTotal companyTotal);
}
