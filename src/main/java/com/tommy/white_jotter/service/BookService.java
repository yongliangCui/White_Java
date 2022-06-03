package com.tommy.white_jotter.service;

import com.tommy.white_jotter.dao.BookDao;
import com.tommy.white_jotter.pojo.Book;
import com.tommy.white_jotter.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private RedisService redisService;

    public List<Book> list(){
        List<Book> books;
        String key = "booklist";
        Object bookcach = redisService.get(key);
        if (bookcach == null) {
            books = bookDao.selectAll();
            redisService.set(key, books);
            return books;
        }
        return (List<Book>) bookcach;
    }

    public void addOrUpdate(Book book){
        if (null == book.getId()){
            bookDao.insert(book);
        }else {
            redisService.delete("booklist");
            bookDao.updateByPrimaryKey(book);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            redisService.delete("booklist");
        }
    }

    public void deleteById(Long id){
        redisService.delete("booklist");
        bookDao.deleteByPrimaryKey(id);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//      防止插入过程中有并发导致booklist又出现缓存。
        redisService.delete("booklist");
    }

    public List<Book> listByCategory(int cid){
        List<Book> books = bookDao.findByCid(cid);
        return books;
    }

    public List<Book> search(String keywords){
        Example example = new Example(Book.class);
        example.setForUpdate(true);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("title","%"+keywords+"%").orLike("author","%"+keywords+"%");
        List<Book> books = bookDao.selectByExample(example);
        return books;
    }
}
