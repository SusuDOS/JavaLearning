package com.itheima;

import com.itheima.dao.BookDao;
import com.itheima.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// 下行括号部分可以省略.
@SpringBootTest(classes = Springboot08MybatisApplication.class)
class Springboot08MybatisApplicationTests {

    @Autowired
    private BookDao bookDao;

    @Test
    void testGetById() {
        Book book = bookDao.getById(1);
        System.out.println(book);
    }

}
