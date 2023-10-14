package com.example.interchange.mapper;

import com.example.interchange.pojo.BlockAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-06 10:23 PM
 */

@Mapper
public interface BlockAccountMapper {
    @Select("select * from block_account")
    List<BlockAccount> selectAllAccount();

    @Insert("insert into block_account values (#{name},#{address},#{chain},#{words})")
    int insertBlockAccount(BlockAccount blockAccount);
}
