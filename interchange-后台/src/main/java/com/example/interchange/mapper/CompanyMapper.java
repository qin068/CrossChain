package com.example.interchange.mapper;

import com.example.interchange.pojo.Company;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-06 4:45 PM
 */

@Mapper
public interface CompanyMapper {
    @Insert("insert into company values(#{id},#{company_name},#{company_logo})")
    int insertCompany(Company company);

    @Delete("delete from company where id = #{id}")
    int deleteByCompanyId(String id);

    @Update("update company set company_name = #{company_name},company_logo = #{company_logo} where id = #{id}")
    int updateCompany(Company company);

    @Select("select * from company")
    List<Company> selectAllCompany();

    @Select("select * from company where id = #{id}")
    Company selectByCompanyId(String id);
}
