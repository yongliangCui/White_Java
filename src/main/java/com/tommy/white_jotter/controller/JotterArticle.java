package com.tommy.white_jotter.controller;

import com.tommy.white_jotter.pojo.jotter_article;
import com.tommy.white_jotter.result.Result;
import com.tommy.white_jotter.result.ResultFactory;
import com.tommy.white_jotter.service.JotterArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class JotterArticle {
    @Resource
    private JotterArticleService jotterArticleService;
    @PostMapping("api/admin/content/article")
    public Result saveArticle(@RequestBody jotter_article jotterArticle){
        jotterArticleService.addOrUpdate(jotterArticle);
        return ResultFactory.buildSuccessResult("保存成功");
    }

    @GetMapping("/api/article/{size}/{page}")
    public Result listArticle(@PathVariable("size") int size, @PathVariable("page") int page){
        return ResultFactory.buildSuccessResult(jotterArticleService.list(page,size));
    }

    @GetMapping("/api/article/{id}")
    public Result getOneArticle(@PathVariable("id") Long id){
        return ResultFactory.buildSuccessResult(jotterArticleService.findById(id));
    }

    @DeleteMapping("/api/admin/content/article/{id}")
    public Result deleteArticle(@PathVariable("id") Long id){
        jotterArticleService.delete(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }

}
