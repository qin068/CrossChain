package com.example.interchange.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.interchange.pojo.Post;
import com.example.interchange.pojo.Title;
import com.example.interchange.service.PostService;
import com.example.interchange.service.PostServiceIml;
import com.example.interchange.service.TitleService;
import com.example.interchange.utils.ResponseUtil;
import com.example.interchange.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2021-09-23 4:33 PM
 */

@SuppressWarnings("all")
@RestController
public class PostController {
    PostService postService = new PostServiceIml();

    @Autowired
    TitleService titleService;

    @GetMapping("/sendPost")
    public Result SendPost(String title, String content, String chain, String channel){
        if(!checkParam(chain)) return ResponseUtil.resp(400,"请求参数有误","");
        String data, height,timestamp,txhash;
        try {
            List<String> strings = postService.sendPost(title, content, chain, channel);
            String[] info = getInfo(strings);
            data = info[0];
            if(data.equals("")){
                return ResponseUtil.resp(202,"发送消息失败","跨链通信失败");
            }
            height = info[1];
            txhash = info[2];
            timestamp = System.currentTimeMillis()/1000 +"";
            //将title 相关信息写入数据库
            titleService.insertTitle(new Title(title,content,height,txhash,timestamp));

        } catch (Exception e) {
            return ResponseUtil.resp(202,"发送消息失败","");
        }
        return ResponseUtil.resp(200,"发送消息成功",new Title(title,content,height,txhash,timestamp));
    }

    @GetMapping("/listPost")
    public Result listPost(String chain){
        if(!checkParam(chain)) return ResponseUtil.resp(400,"请求参数有误","");
        List<Post> posts = null;
        try {
             posts = postService.listPost(chain);
        } catch (Exception e) {
            return ResponseUtil.resp(202,"更新消息列表失败","");
        }
        return ResponseUtil.resp(200,"更新消息列表成功",posts);
    }

    @GetMapping("/listSendPost")
    public Result listSendPost(String chain){
        if(!checkParam(chain)) return ResponseUtil.resp(400,"请求参数有误","");
        List<Post> posts = null;
        try {
            posts = postService.listSendPost(chain);
        } catch (Exception e) {
            return ResponseUtil.resp(202,"更新发送消息列表失败","");
        }
        return ResponseUtil.resp(200,"更新发送消息列表成功",posts);
    }

    private boolean checkParam(String chain){
        int cnt = 0;
        try {
            cnt = Integer.parseInt(chain);
        } catch (NumberFormatException e) {
            return false;
        }
        return  cnt > 0 && cnt < 4;
    }
    public String[] getInfo(List<String> strings){
        String line = strings.get(0);
//        System.out.println(line);
        JSONObject lineJson = JSONObject.parseObject(line);
        String resultData = lineJson.getString("data");
        String[] strs = line.split(",");
        String heightLine = strs[0],txHashLine = strs[1];
//        String data = strs[4];
//        System.out.println(data);
        char[] heightArr = heightLine.toCharArray(),txHashArr = txHashLine.toCharArray();
        int first = -1,second = -1;
        for(int i = heightArr.length - 1; i >= 0; --i){
            if(heightArr[i] == '"'){
                if(first == -1){
                    first = i;
                }else{
                    second = i;
                    break;
                }
            }
        }
        String height = heightLine.substring(second + 1,first);
        first = -1;
        second = -1;
        for(int i = txHashArr.length - 1; i >= 0; --i){
            if(txHashArr[i] == '"'){
                if(first == -1){
                    first = i;
                }else{
                    second = i;
                    break;
                }
            }
        }
        String txHash = txHashLine.substring(second + 1,first);
        return new String[]{resultData, height,txHash};
    }
}
