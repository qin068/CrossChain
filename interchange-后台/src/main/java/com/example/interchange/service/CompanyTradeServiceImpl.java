package com.example.interchange.service;

import com.example.interchange.mapper.CompanyTradeMapper;
import com.example.interchange.pojo.CompanyTrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-06 8:36 PM
 */

@Service
public class CompanyTradeServiceImpl implements CompanyTradeService {
    @Autowired
    CompanyTradeMapper companyTradeMapper;

    @Override
    public List<CompanyTrade> seleceAllCompanyTrade() {
        return companyTradeMapper.selectCompanyTrade();
    }

    @Override
    public CompanyTrade selectCompanyTradeById(String id) {
        return companyTradeMapper.selectCompanyTradeById(id);
    }

    @Override
    public int insertCompanyTrade(CompanyTrade companyTrade) {
        return companyTradeMapper.insertCompanyTradeTrade(companyTrade);
    }

    @Override
    public int deleteCompanyTrade(String id) {
        return companyTradeMapper.deleteCompanyTrade(id);
    }

    @Override
    public int updateCompanyTrade(CompanyTrade companyTrade) {
        return companyTradeMapper.updateCompanyTrade(companyTrade);
    }
}
