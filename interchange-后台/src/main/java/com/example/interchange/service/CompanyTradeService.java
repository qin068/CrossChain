package com.example.interchange.service;

import com.example.interchange.pojo.CompanyTrade;

import java.awt.*;
import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-06 8:36 PM
 */


public interface CompanyTradeService {
    List<CompanyTrade> seleceAllCompanyTrade();
    CompanyTrade selectCompanyTradeById(String id);

    int insertCompanyTrade(CompanyTrade companyTrade);
    int deleteCompanyTrade(String id);
    int updateCompanyTrade(CompanyTrade companyTrade);
}
