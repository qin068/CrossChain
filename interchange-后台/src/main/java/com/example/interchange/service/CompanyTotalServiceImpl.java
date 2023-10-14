package com.example.interchange.service;

import com.example.interchange.mapper.CompanyTotalMapper;
import com.example.interchange.pojo.CompanyTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-06 9:34 PM
 */

@Service
public class CompanyTotalServiceImpl implements CompanyTotalService {
    @Autowired
    CompanyTotalMapper companyTotalMapper;

    @Override
    public List<CompanyTotal> selectAllCompanyTotal() {
        return companyTotalMapper.selectAllCompanyTotal();
    }

    @Override
    public CompanyTotal selectComanyTotalById(String id) {
        return companyTotalMapper.selectCompanyTotalById(id);
    }

    @Override
    public int insertCompanyTotal(CompanyTotal companyTotal) {
        return companyTotalMapper.insertCompanyTotal(companyTotal);
    }

    @Override
    public int deleteCompanyTotal(String id) {
        return companyTotalMapper.deleteCompanyTotal(id);
    }

    @Override
    public int updateCompanyTotal(CompanyTotal companyTotal) {
        return companyTotalMapper.updateCompanyTotal(companyTotal);
    }
}
