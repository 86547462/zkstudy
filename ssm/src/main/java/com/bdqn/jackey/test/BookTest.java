package com.bdqn.jackey.test;

import com.bdqn.jackey.pojo.Book;
import com.bdqn.jackey.pojo.Category;
import com.bdqn.jackey.service.BookService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 周坤 on 2020/9/22.
 */
public class BookTest {
    @Test
    public void show() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) applicationContext.getBean("bookService");
        Date date = new Date();
        int result = bookService.addBook(new Book(new Category(3, "武侠"), "射雕英雄传", "金庸神奇武侠", "金庸", new SimpleDateFormat("yyyy-MM-dd").format(date)));
        System.out.println(result);
    }

    @Test
    public void show2() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) applicationContext.getBean("bookService");
        List<Book> bookList = bookService.queryAllBooks(1,10);
    }

    @Test
    public void show3() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) applicationContext.getBean("bookService");
        Date date = new Date();
        try {
            int result = bookService.updateBookById(new Book(5, new Category(3, "武侠"), "射雕英雄传4", "金庸神奇武侠3", "金庸", new SimpleDateFormat("yyyy-MM-dd").format(date)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
