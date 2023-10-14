package com.example.interchange.mapper;

import com.example.interchange.pojo.CompanyTotal;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-06 9:29 PM
 */


public interface CompanyTotalMapper {
    @Select("select * from company_total")
    List<CompanyTotal> selectAllCompanyTotal();

    @Select("select * from company_total where id = #{id}")
    CompanyTotal selectCompanyTotalById(String id);

    @Insert("insert into company_total values(#{id},#{tt_count},#{tt_height},#{tt_peak},#{tt_trade})")
    int insertCompanyTotal(CompanyTotal companyTotal);

    @Delete("delete from company_total where id = #{id}")
    int deleteCompanyTotal(String id);

    @Update("update company_total set tt_count = #{tt_count},tt_height = #{tt_height},tt_peak =  #{tt_peak}, tt_trade = #{tt_trade} where id = #{id}")
    int updateCompanyTotal(CompanyTotal companyTotal);

}
