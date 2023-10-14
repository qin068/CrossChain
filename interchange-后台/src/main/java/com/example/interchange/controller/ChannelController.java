package com.example.interchange.controller;

import com.example.interchange.pojo.Channel;
import com.example.interchange.service.ChannelService;
import com.example.interchange.service.ChannelServiceIml;
import com.example.interchange.utils.ResponseUtil;
import com.example.interchange.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 秃头小刘
 * @date 2021-10-14 1:43 PM
 */

@RestController
@SuppressWarnings("all")
public class ChannelController {
    @Autowired
    ChannelService channelService;

    @GetMapping("/getAllChannel")
    public Result getAllchannel(){
        List<Channel> res = null;
        try {
            res = channelService.getAllChannels();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.resp(202,"启动链并查询channel失败","");
        }
        return ResponseUtil.resp(200,"启动链并查询所有的channel成功",res);
    }

    @GetMapping("/selectAllChannel")
    public Result selectAllchannel(){
        List<Channel> res = null;
        try {
            res = channelService.selectAllChannels();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.resp(202,"查询channel失败","");
        }
        return ResponseUtil.resp(200,"查询所有的channel成功",res);
    }

    @GetMapping("/getChannelByPort")
    public Result getChannelByPort(String port){
        if(port == null || port.length() == 0) return ResponseUtil.resp(400,"请求参数不能为空","");

        List<Channel> res = new ArrayList<>();
        try {
            List<Channel> channels = channelService.getAllChannels();
            for (Channel channel : channels) {
                if(channel != null && port.equals(channel.getPort())) res.add(channel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.resp(202,"查询channel失败","");
        }
        return ResponseUtil.resp(200,"查询指定的channel成功",res);
    }

    @GetMapping("/getChannelByChainName")
    public Result getChannelByChainId(String chainName){
        if(chainName == null || chainName.length() == 0) return ResponseUtil.resp(400,"请求参数不能为空","");

        List<Channel> res = new ArrayList<>();
        try {
            List<Channel> channels = channelService.getAllChannels();
            for (Channel channel : channels) {
                System.out.println(chainName + " " + channel.getFrom());
                if(channel != null && chainName.equals(channel.getFrom())) res.add(channel);
            }
            System.out.println(channels.size() + " " + res.size());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.resp(202,"查询channel失败","");
        }
        return ResponseUtil.resp(200,"查询指定的channel成功",res);
    }
}
