package com.bdqn.jackey.service.impl;

import com.bdqn.jackey.mapper.CategoryMapper;
import com.bdqn.jackey.pojo.Category;
import com.bdqn.jackey.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 周坤 on 2020/9/24.
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 重新启用分类
     *
     * @param id 分类id
     * @return 结果
     */
    @Override
    public int editCategoryStatus(Integer id) {
        return categoryMapper.editCategoryStatus(id);
    }

    /**
     * 查询所有图书分类
     * @param from     分页起始位置
     * @param pageSize 页面大小
     * @return 结果集合
     */
    @Override
    public List<Category> queryAllCategories(Integer from, Integer pageSize) {
        return categoryMapper.queryAllCategories(from, pageSize);
    }

    /**
     * 新增分类信息
     *
     * @param category 分类实例
     * @return 执行结果
     */
    @Override
    public int addCategory(Category category) {
        return categoryMapper.addCategory(category);
    }

    /**
     * 修改分类信息
     *
     * @param category 分类实例
     * @return 执行结果
     */
    @Override
    public int updateCategory(Category category) {
        return categoryMapper.updateCategory(category);
    }

    /**
     * 删除分类信息
     *
     * @param id 分类id
     * @return 执行结果
     */
    @Override
    public int deleteCategory(Integer id) {
        return categoryMapper.deleteCategory(id);
    }

    /**
     * 查询分类总数
     *
     * @return 查询结果
     */
    @Override
    public int queryAllCategoriesCount() {
        return categoryMapper.queryAllCategoriesCount();
    }
}
