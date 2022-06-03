package com.tommy.white_jotter.dao;

import com.tommy.white_jotter.pojo.jotter_article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JotterArticleDao extends tk.mybatis.mapper.common.Mapper<jotter_article> {
}
