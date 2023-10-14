package com.example.interchange.service;

import com.example.interchange.pojo.Blog;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2021-09-23 1:37 PM
 */


public interface BlogService {
    List<String> sendMessage(String title, String body, String chain);

    List<Blog> listBlog(String chain);
}
