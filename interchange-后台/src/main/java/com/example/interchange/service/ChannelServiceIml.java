package com.example.interchange.service;

import com.example.interchange.mapper.ChannelMapper;
import com.example.interchange.pojo.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 秃头小刘
 * @date 2021-10-14 10:40 AM
 */

@SuppressWarnings("all")
@Service
public class ChannelServiceIml implements ChannelService{

    @Autowired
    ChannelMapper channelMapper;

    @Override
    public List<Channel> getAllChannels() throws InterruptedException {
        String command = "starport relayer connect";
        List<String> processList = runCommands(command);
        List<Channel> channelList = getChannels(processList);
//        System.out.println(channelList);
        for(Channel channel: channelList){
            channelMapper.insertChannel(new Channel(channel.getChannelId(),channel.getFrom(),channel.getTo(),channel.getPort()));
        }

        return channelList;
    }

    @Override
    public  List<Channel> selectAllChannels() {
        return channelMapper.selectAllChannel();
    }

    private List<Channel> getChannels(List<String> list){
        List<Channel> res = new ArrayList<>();
        String channelId,from,to,port;
        String source = "#",target = "#";
        for (String s : list) {
            if(!s.contains(":")) continue;

            if(s.contains(":") && !s.contains(">")){
                String str = s.substring(0,s.indexOf(":"));
                String[] split = str.split("-");
                if(split.length < 2) continue;
                source = split[0];
                StringBuilder sb = new StringBuilder(source);
                while(sb.length() > 0 && !isLetter(sb.charAt(0))) sb.delete(0,1);
                sb.delete(0,1);
                source = sb.toString();
                //System.out.println(source + " " + source.length());
                target = split[1];
            }else if(s.contains(">")){
                if(s.contains(source)){
                    from = source;
                    to = target;
                    channelId = s.substring(s.lastIndexOf(":") + 2,s.lastIndexOf(")"));
                    port = s.substring(s.indexOf(":") + 2,s.indexOf(")"));
                    res.add(new Channel(channelId,from,to,port));
                } else if(s.contains(target)){
                    from = target;
                    to = source;
                    channelId = s.substring(s.lastIndexOf(":") + 2,s.lastIndexOf(")"));
                    port = s.substring(s.indexOf(":") + 2,s.indexOf(")"));
                    res.add(new Channel(channelId,from,to,port));
                }
            }
        }
        return res;
    }
    private boolean isLetter(char c){
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private List<String> runCommands(String command) throws InterruptedException {
        System.out.println(command);
        Process process = null;
        List<String> processList = new ArrayList<String>();

        try {
            process = Runtime.getRuntime().exec(command);

            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = "";
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                if(line.contains("Listening and relaying packets between chains")) break;
                processList.add(line);
            }
            process.destroy();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return processList;
    }
}
