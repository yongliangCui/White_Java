package com.tommy.white_jotter.dao;

import com.tommy.white_jotter.pojo.Category;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface CategoryDao extends tk.mybatis.mapper.common.Mapper<Category> {
    @Select("select * from category where id = #{id}")
    @Results(id = "categoryMap",value = {
            @Result(id = true ,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "list",column = "id",many = @Many(select = "com.tommy.white_jotter.dao.BookDao.findByCid",
                    fetchType = FetchType.EAGER))
    })
    public Category findById(@Param("id") int id);
}
