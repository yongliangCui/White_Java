package com.tommy.white_jotter.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tommy.white_jotter.dao.JotterArticleDao;
import com.tommy.white_jotter.pojo.jotter_article;
import com.tommy.white_jotter.redis.RedisService;
import com.tommy.white_jotter.util.MyPage;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JotterArticleService {

    @Resource
    private JotterArticleDao jotterArticleDao;
    @Resource
    private RedisService redisService;

    public MyPage<jotter_article> list(int page,int size){
        MyPage<jotter_article> myPage;
        String key = "mypage";
        Object mypagecache = redisService.get(key);
        if (mypagecache == null){
            Example example = new Example(jotter_article.class);
            example.orderBy("id").desc();
            PageHelper.startPage(page, size);
            List<jotter_article> jotter_articles = jotterArticleDao.selectByExample(example);
            PageInfo<jotter_article> pageInfo = new PageInfo<>(jotter_articles);
            myPage = new MyPage<>(pageInfo);
            return myPage;
        }
        return (MyPage<jotter_article>) mypagecache;
    }

//    public jotter_article findById(Long id){
//        jotter_article article;
//        String key = "article";
//        Object articlecache = redisService.get(key);
//        if (articlecache == null){
//
//        }
//    }

//    通过id寻找article
    public jotter_article findById(Long id){
        jotter_article jotter_article;
        String key = "jotter_article"+id;
        Object cache = redisService.get(key);
        if (cache == null){
            return jotterArticleDao.selectByPrimaryKey(id);
        }
        return (com.tommy.white_jotter.pojo.jotter_article) cache;
    }

    public void addOrUpdate(jotter_article article){
        jotterArticleDao.insert(article);
        redisService.delete("jotter_article"+article.getId());
        redisService.delete("mypage");
    }

    public void delete(Long id){
        jotterArticleDao.deleteByPrimaryKey(id);
        redisService.delete("jotter_article"+id);
        redisService.delete("mypage");
    }


}
