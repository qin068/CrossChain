package com.example.interchange.service;


import com.example.interchange.mapper.CompanyMapper;
import com.example.interchange.pojo.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-06 4:48 PM
 */

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyMapper companyMapper;

    @Override
    public int insertCompany(Company company) {
        return companyMapper.insertCompany(company);
    }

    @Override
    public int deleteCompanyById(String id) {
        return companyMapper.deleteByCompanyId(id);
    }


    @Override
    public int updateCompany(Company company) {
        return companyMapper.updateCompany(company);
    }

    @Override
    public List<Company> selectAllCompany() {
        return companyMapper.selectAllCompany();
    }

    @Override
    public Company selectCompanyById(String id) {
        return companyMapper.selectByCompanyId(id);
    }
}
