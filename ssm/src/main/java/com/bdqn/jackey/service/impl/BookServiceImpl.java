package com.bdqn.jackey.service.impl;

import com.bdqn.jackey.mapper.BookMapper;
import com.bdqn.jackey.pojo.Book;
import com.bdqn.jackey.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 周坤 on 2020/9/22.
 */
@Service("bookService")
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;

    /**
     * 查询图书信息
     *
     * @return 结果集合
     */
    @Override
    public List<Book> queryAllBooks(Integer from, Integer pageSize) {
        return bookMapper.queryAllBooks(from, pageSize);
    }

    /**
     * 按条件查询图书信息
     *
     * @param title      图书名称
     * @param categoryId 分类id
     * @param uploadUser 上传人
     * @param creatDate  上传时间
     * @return 结果集合
     */
    @Override
    public List<Book> queryAllBooksByCondition(String title, Integer categoryId, String uploadUser, String creatDate, Integer from, Integer pageSize) {
        return bookMapper.queryAllBooksByCondition(title, categoryId, uploadUser, creatDate, from, pageSize);
    }

    /**
     * 新增图书信息
     *
     * @param book 封装图书信息的对象
     * @return 执行结果
     */
    @Override
    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    /**
     * 根据图书id 修改图书信息
     *
     * @param book 图书信息
     * @return 执行结果
     */
    @Override
    public int updateBookById(Book book) {
        return bookMapper.updateBookById(book);
    }

    /**
     * 查询分类下是否有图书信息，删除图书分类使用
     *
     * @param categoryId 分类id
     * @return
     */
    @Override
    public List<Book> queryBooksByCategoryId(Integer categoryId) {
        return bookMapper.queryBooksByCategoryId(categoryId);
    }

    /**
     * 查询图书总数量用来分页
     *
     * @param title      图书名称
     * @param categoryId 分类id
     * @param uploadUser 上传人
     * @param creatDate  上传时间
     * @return 图书数量
     */
    @Override
    public int queryBooksCount(String title, Integer categoryId, String uploadUser, String creatDate) {
        return bookMapper.queryBooksCount(title, categoryId, uploadUser, creatDate);
    }

    /**
     * 根据id删除图书信息
     *
     * @param id 图书id
     * @return 删除结果
     */
    @Override
    public int deleteBook(Integer id) {
        return bookMapper.deleteBook(id);
    }
}
