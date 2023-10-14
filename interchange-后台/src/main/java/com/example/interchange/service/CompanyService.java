package com.example.interchange.service;


import com.example.interchange.pojo.Company;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-06 4:47 PM
 */


public interface CompanyService {
    int insertCompany(Company company);
    int deleteCompanyById(String id);
    int updateCompany(Company company);
    List<Company> selectAllCompany();
    Company selectCompanyById(String id);
}
