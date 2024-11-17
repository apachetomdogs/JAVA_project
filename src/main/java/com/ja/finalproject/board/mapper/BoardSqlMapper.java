package com.ja.finalproject.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ja.finalproject.dto.ArticleDto;

@Mapper
public interface BoardSqlMapper {
    public void createArticle(ArticleDto articleDto);
    public List<ArticleDto> findAll();
    public ArticleDto findById(int id); 
    public void increaseReadCount(int id);

    
    public void deleteById(int id);
    public void update(ArticleDto articleDto);
}
