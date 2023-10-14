package com.example.interchange.mapper;

import com.example.interchange.pojo.Channel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChannelMapper {
    @Select("select * from channel")
    List<Channel> selectAllChannel();

    @Insert("insert into channel values (#{channelId},#{from},#{to},#{port})")
    int insertChannel(Channel channel);
}
