package com.example.interchange.mapper;

import com.example.interchange.pojo.Company;
import com.example.interchange.pojo.CompanyNode;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-06 7:32 PM
 */


public interface CompanyNodeMapper {

    @Select("select * from company_node")
    List<CompanyNode> selectAllCompanyNode();

    @Select("select * from company_node where id = #{id}")
    CompanyNode selectCompanyNodeById(String id);

    @Insert("insert into company_node values(#{id},#{companyId},#{nd_name},#{nd_location},#{nd_state},#{nd_height},#{nd_hash},#{nd_timestamp},#{nd_code})")
    int insertCompanyNode(CompanyNode companyNode);

    @Delete("delete from company_node where id = #{id}")
    int deleteCompanyNode(String id);

    @Update("update company_node set companyId = #{companyId},nd_name = #{nd_name},nd_location = #{nd_location},nd_state = #{nd_state},nd_height = #{nd_height},nd_hash = #{nd_hash},nd_timestamp = #{nd_timestamp},nd_code = #{nd_code} where id = #{id}")
    int updateCompanyNode(CompanyNode companyNode);
}
