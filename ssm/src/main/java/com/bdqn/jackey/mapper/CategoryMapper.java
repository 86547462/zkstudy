package com.bdqn.jackey.mapper;

import com.bdqn.jackey.pojo.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 周坤 on 2020/9/24.
 * 分类接口
 */
public interface CategoryMapper {
    /**
     * 查询所有图书分类
     * @param from 分页起始位置
     * @param pageSize 页面大小
     * @return 结果集合
     */
    List<Category> queryAllCategories(@Param("from") Integer from,@Param("pageSize")Integer pageSize);

    /**
     * 新增分类信息
     * @param category 分类实例
     * @return 执行结果
     */
    int addCategory(Category category);

    /**
     * 修改分类信息
     * @param category 分类实例
     * @return 执行结果
     */
    int updateCategory(Category category);

    /**
     * 删除分类信息
     *
     * @param id 分类id
     * @return 执行结果
     */
    int deleteCategory(@Param("id") Integer id);

    /**
     * 重新启用分类
     * @param id 分类id
     * @return 结果
     */
    int editCategoryStatus(@Param("id") Integer id);
    /**
     * 查询分类总数
     * @return 查询结果
     */
    int queryAllCategoriesCount();
}
