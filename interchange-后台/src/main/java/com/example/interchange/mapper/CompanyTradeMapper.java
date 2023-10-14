package com.example.interchange.mapper;

import com.example.interchange.pojo.CompanyTrade;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-06 8:35 PM
 */


public interface CompanyTradeMapper {
    @Select("select * from company_trade")
    List<CompanyTrade> selectCompanyTrade();

    @Select("select * from company_trade where id = #{id}")
    CompanyTrade selectCompanyTradeById(String id);

    @Insert("insert into company_trade values(#{id},#{companyId},#{td_type},#{td_ip},#{td_valid},#{td_height},#{td_hash},#{td_timestamp},#{td_code})")
    int insertCompanyTradeTrade(CompanyTrade companyTrade);

    @Update("update company_trade set companyId = #{companyId},td_type = #{td_type},td_ip = #{td_ip},td_valid = #{td_valid},td_height = #{td_height},td_hash = #{td_hash},td_timestamp = #{td_timestamp},td_code = #{td_code} where id = #{id}")
    int updateCompanyTrade(CompanyTrade companyTrade);

    @Delete("delete from company_trade where id = #{id}")
    int deleteCompanyTrade(String id);
}
