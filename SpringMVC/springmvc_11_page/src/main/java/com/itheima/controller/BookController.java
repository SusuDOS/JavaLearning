package com.itheima.controller;

import com.itheima.domain.Book;
import com.itheima.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    /*BookDao是一个接口，没有实现类，接口是不能创建对象的，所以最终注入的应该是代理对象,代理对象是由Spring的IOC容器来创建管理的。
    IOC容器又是在Web服务器启动的时候才会创建,IDEA在检测依赖关系的时候，没有找到适合的类注入，所以会提示错误提示。
    但是程序运行的时候，代理对象就会被创建，框架会使用DI进行注入，所以程序运行无影响。*/

    @PostMapping
    public Result save(@RequestBody Book book) {
        boolean flag = bookService.save(book);
        System.out.println("flag:" + flag);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @PutMapping
    public Result update(@RequestBody Book book) {
        boolean flag = bookService.update(book);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = bookService.delete(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Book book = bookService.getById(id);
        Integer code = book != null ? Code.GET_OK : Code.GET_ERR;
        String msg = book != null ? "" : "数据查询失败，请重试！";
        return new Result(code, book, msg);
    }

    @GetMapping
    public Result getAll() {
        List<Book> bookList = bookService.getAll();
        Integer code = bookList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = bookList != null ? "" : "数据查询失败，请重试！";
        return new Result(code, bookList, msg);
    }

    // 地址栏带参数传递:http://localhost/books/dataParam?date=2088/08/08&date1=2088-08-08
    @RequestMapping("/dataParam")
    @ResponseBody
    public String dataParam(Date date, @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1) {
        System.out.println("参数传递 date ==> " + date);
        System.out.println("参数传递 date1(yyyy-MM-dd) >>> " + date1);
        return "{'module':'data param'}";
    }
}
