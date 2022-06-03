package com.tommy.white_jotter.service;

import com.tommy.white_jotter.dao.CategoryDao;
import com.tommy.white_jotter.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    public List<Category> list(){
        Example example = new Example(Category.class);
        example.orderBy("id").desc();
        List<Category> categories = categoryDao.selectByExample(example);
        return categories;
    }
    public Category get(int id){
        return categoryDao.selectByPrimaryKey(id);
    }
}
