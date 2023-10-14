package com.example.interchange.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.interchange.pojo.Blog;
import com.example.interchange.pojo.Title;
import com.example.interchange.service.*;
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
public class BlogController {
    BlogService blogService = new BlogServiceIml();
    PostService postService = new PostServiceIml();

    @Autowired
    TitleService titleService;

    @GetMapping("/sendMessage")
    public Result SendMessage(String title, String body, String chain){
        if(!checkParam(chain)) return ResponseUtil.resp(400,"请求参数有误","");
        try {
            List<String> strings = blogService.sendMessage(title, body, chain);
            String data = getReturnData(strings);
            if(data.equals("")){
                return ResponseUtil.resp(202,"发送消息失败","单链通信失败");
            }
        } catch (Exception e) {
            return ResponseUtil.resp(202,"发送消息失败","");
        }
        return ResponseUtil.resp(200,"发送消息成功","");
    }

    @GetMapping("/sendInterBlog")
    public Result SendInterBlog(String title, String content, String chain, String channel){
        if(!checkParam(chain)) return ResponseUtil.resp(400,"请求参数有误","");
        try {
            List<String> strings = postService.sendPost(title, content, chain, channel);
            String data = getReturnData(strings);
            if(data.equals("")){
                return ResponseUtil.resp(202,"发送跨链博客失败","跨链博客通信失败");
            }
        } catch (Exception e) {
            return ResponseUtil.resp(202,"发送跨链博客失败","");
        }
        return ResponseUtil.resp(200,"发送跨链博客成功","");
    }

    @GetMapping("/insertInterBlog")
    public Result insertInterBlog(String title, String content){
        try {
            //将title 相关信息写入数据库
            titleService.insertTitle(new Title(title,content,"","",""));
        } catch (Exception e) {
            return ResponseUtil.resp(202,"插入跨链博客失败","");
        }
        return ResponseUtil.resp(200,"插入跨链博客成功","");
    }


    @GetMapping("/listBlog")
    public Result listBlog(String chain){
        if(!checkParam(chain)) return ResponseUtil.resp(400,"请求参数有误","");
        List<Blog> blogs = null;
        try {
            blogs = blogService.listBlog(chain);
        } catch (Exception e) {
            return ResponseUtil.resp(202,"更新消息失败","");
        }
        return ResponseUtil.resp(200,"更新消息列表成功",blogs);
    }

    private boolean checkParam(String chain){
        int cnt = 0;
        try {
            cnt = Integer.parseInt(chain);
        } catch (NumberFormatException e) {
            return false;
        }
        return  cnt > 0 && cnt < 4;//限制只开3条链
    }

    public String getReturnData(List<String> strings){
        String line = strings.get(0);
        JSONObject lineJson = JSONObject.parseObject(line);
        String resultData = lineJson.getString("data");
        return resultData;
    }
}
