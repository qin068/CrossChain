package com.example.interchange.mapper;

import com.example.interchange.pojo.Title;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-13 8:35 PM
 */


public interface TitleMapper {
    @Select("select * from printer")
    List<Title> selectAllTitle();

    @Select("select * from printer where title = #{title}")
    Title selectTitleById(String title);

    @Insert("REPLACE into printer values(#{title},#{content},#{height},#{txhash},#{timestamp})")
    int insertTitle(Title title);
}
