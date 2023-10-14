package com.example.interchange.service;

import com.example.interchange.mapper.CompanyNodeMapper;
import com.example.interchange.pojo.Company;
import com.example.interchange.pojo.CompanyNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-06 7:35 PM
 */

@Service
public class CompanyNodeServiceImpl implements CompanyNodeService {

    @Autowired
    CompanyNodeMapper companyNodeMapper;

    @Override
    public List<CompanyNode> selectAllCompanyNode() {
        return companyNodeMapper.selectAllCompanyNode();
    }

    @Override
    public CompanyNode selectCompanyNodeById(String id) {
        return companyNodeMapper.selectCompanyNodeById(id);
    }

    @Override
    public int insertCompanyNode(CompanyNode companyNode) {
        return companyNodeMapper.insertCompanyNode(companyNode);
    }

    @Override
    public int deleteCompanyNode(String id) {
        return companyNodeMapper.deleteCompanyNode(id);
    }

    @Override
    public int updateCompanyNode(CompanyNode companyNode) {
        return companyNodeMapper.updateCompanyNode(companyNode);
    }
}
