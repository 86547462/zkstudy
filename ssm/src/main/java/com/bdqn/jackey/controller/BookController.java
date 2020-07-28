package com.bdqn.jackey.controller;

import com.bdqn.jackey.pojo.Book;
import com.bdqn.jackey.service.BookService;
import com.bdqn.jackey.utils.LayData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 周坤 on 2020/9/23.
 * 控制器
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 查询图书信息列表，返回给layui表格
     */
    @RequestMapping("/list")
    @ResponseBody
    public LayData getBookList(HttpServletRequest request) {
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer limit = Integer.parseInt(request.getParameter("limit"));
        int count = bookService.queryBooksCount(null, 0, null, null);
        List<Book> books = bookService.queryAllBooks((page - 1) * limit, limit);
        return new LayData(0, "成功", count, books);
    }

    /**
     * 加载数据表格信息到iframe
     *
     * @return
     */
    @RequestMapping("/toList")
    public String toList() {
        return "booklist";
    }

    /**
     * 修改图书信息
     *
     * @param book 图书信息
     * @return 执行结果
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public int updateBook(Book book) {
        book.getCategory().setId(Integer.parseInt(book.getCategory().getName()));
        return bookService.updateBookById(book);
    }

    /**
     * 新增图书信息
     *
     * @param book 图书信息
     * @return 执行结果
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public int addBook(Book book) {
        book.getCategory().setId(Integer.parseInt(book.getCategory().getName()));
        return bookService.addBook(book);
    }

    @RequestMapping("/searchList")
    @ResponseBody
    public LayData getBookListByCondition(HttpServletRequest request) {
        String title = request.getParameter("title");
        String creatDate = request.getParameter("creatDate");
        String uploadUser = request.getParameter("uploadUser");
        String id = request.getParameter("categoryId");
        Integer categoryId = 0;
        if (id != null) {
            categoryId = Integer.parseInt(request.getParameter("categoryId"));
        }
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer limit = Integer.parseInt(request.getParameter("limit"));
        List<Book> books = bookService.queryAllBooksByCondition(title, categoryId, uploadUser, creatDate, (page - 1) * limit, limit);
        int count = bookService.queryBooksCount(title, categoryId, uploadUser, creatDate);
        return new LayData(0, "成功", count, books);
    }

    /**
     * 删除图书信息
     *
     * @param id 图书id
     * @return 删除结果
     */
    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public int deleteBook(@PathVariable("id") Integer id) {
        return bookService.deleteBook(id);
    }

    @RequestMapping(value = "/isHave/{id}")
    @ResponseBody
    public List<Book> getCategoryBooks(@PathVariable("id") Integer id) {
        return bookService.queryBooksByCategoryId(id);
    }
}
