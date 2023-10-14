package com.example.interchange.service;

import com.example.interchange.mapper.TitleMapper;
import com.example.interchange.pojo.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2022-05-13 8:39 PM
 */

@Service
public class TitleServiceImpl implements TitleService{
    @Autowired
    TitleMapper titleMapper;

    @Override
    public List<Title> selectAllTitle() {
        return titleMapper.selectAllTitle();
    }

    @Override
    public Title selectTitleById(String title) {
        return titleMapper.selectTitleById(title);
    }

    @Override
    public int insertTitle(Title title) {
        return titleMapper.insertTitle(title);
    }
}
