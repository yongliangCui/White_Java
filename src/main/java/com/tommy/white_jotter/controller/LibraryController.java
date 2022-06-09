package com.tommy.white_jotter.controller;

import com.tommy.white_jotter.pojo.Book;
import com.tommy.white_jotter.result.Result;
import com.tommy.white_jotter.result.ResultFactory;
import com.tommy.white_jotter.service.BookService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin
public class LibraryController {
    @Autowired
    private BookService bookService;

    @GetMapping("api/books")
    public Result findBooks(){
        return ResultFactory.buildSuccessResult(bookService.list());
    }

    @PostMapping("/api/admin/content/books")
    public Result addOrtUpdate(@RequestBody @Valid Book book){
        bookService.addOrUpdate(book);
        return ResultFactory.buildSuccessResult("OK");
    }

    @PostMapping("/api/admin/content/books/delete")
    public Result deleteBook(@RequestBody @Valid Book book){
        if (null == book.getId()){
            return ResultFactory.buildFailResult("bookid不能为空");
        }
        bookService.deleteById(book.getId());
        return ResultFactory.buildSuccessResult("删除成功");
    }

    @GetMapping("api/search")
    public Result search(@RequestParam("keywords") String keywords){
        if (StringUtils.isBlank(keywords)){
            return ResultFactory.buildFailResult("keywords不能为空");
        }
        List<Book> bookList = bookService.search(keywords);
        return ResultFactory.buildSuccessResult(bookList);
    }

    @GetMapping("/api/categories/{cid}/books")
    public Result listByCategory(@PathVariable("cid") int cid){
        if (cid == 0){
            return ResultFactory.buildSuccessResult(bookService.list());
        }
        return ResultFactory.buildSuccessResult(bookService.listByCategory(cid));
    }

    @PostMapping("/api/admin/content/books/covers")
    public String coversUpload(MultipartFile file) {
        String folder = "D:/workspace/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, com.tommy.white_jotter.util.StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


}
