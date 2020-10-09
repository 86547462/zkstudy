package com.bdqn.jackey.service;

import com.bdqn.jackey.pojo.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 周坤 on 2020/9/22.
 */
public interface BookService {
    /**
     * 查询图书信息
     *
     * @return 结果集合
     */
    List<Book> queryAllBooks(Integer from, Integer pageSize);

    /**
     * 按条件查询图书信息
     *
     * @param title      图书名称
     * @param categoryId 分类id
     * @param uploadUser 上传人
     * @param creatDate  上传时间
     * @param from       分页起始位置
     * @param pageSize   页面大小
     * @return 结果集合
     */
    List<Book> queryAllBooksByCondition(String title, Integer categoryId, String uploadUser, String creatDate, Integer from, Integer pageSize);

    /**
     * 查询分类下是否有图书信息，删除图书分类使用
     *
     * @param categoryId 分类id
     * @return
     */
    List<Book> queryBooksByCategoryId(@Param("categoryId") Integer categoryId);

    /**
     * 新增图书信息
     *
     * @param book 封装图书信息的对象
     * @return 执行结果
     */
    int addBook(Book book);

    /**
     * 根据图书id 修改图书信息
     *
     * @param book 图书信息
     * @return 执行结果
     */
    int updateBookById(Book book);


    /**
     * 查询图书总数量用来分页
     *
     * @param title      图书名称
     * @param categoryId 分类id
     * @param uploadUser 上传人
     * @param creatDate  上传时间
     * @return 图书数量
     */
    int queryBooksCount(String title, Integer categoryId, String uploadUser, String creatDate);

    /**
     * 根据id删除图书信息
     *
     * @param id 图书id
     * @return 删除结果
     */
    int deleteBook(Integer id);
}
