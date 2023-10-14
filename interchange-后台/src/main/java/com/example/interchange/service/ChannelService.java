package com.example.interchange.service;

import com.example.interchange.pojo.Channel;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2021-10-14 10:24 AM
 */


public interface ChannelService {
    List<Channel> getAllChannels()throws InterruptedException;
    List<Channel> selectAllChannels();
}
