package com.example.interchange.service;

import com.example.interchange.pojo.Post;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2021-09-23 1:37 PM
 */


public interface PostService {
    List<String> sendPost(String title, String content, String chain, String channel);

    List<Post> listPost(String chain);

    List<Post> listSendPost(String chain);
}
