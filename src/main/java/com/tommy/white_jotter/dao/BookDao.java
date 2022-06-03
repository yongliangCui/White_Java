package com.tommy.white_jotter.dao;

import com.tommy.white_jotter.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookDao extends tk.mybatis.mapper.common.Mapper<Book> {
    @Select("select * from book where cid = #{cid}")
    public List<Book> findByCid(int cid);
}
