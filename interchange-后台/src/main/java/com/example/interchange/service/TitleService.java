package com.example.interchange.service;

import com.example.interchange.pojo.Title;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-13 8:38 PM
 */


public interface TitleService {
    List<Title> selectAllTitle();

    Title selectTitleById(String title);

    int insertTitle(Title title);
}
