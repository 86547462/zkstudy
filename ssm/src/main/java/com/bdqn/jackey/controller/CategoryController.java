package com.bdqn.jackey.controller;

import com.bdqn.jackey.pojo.Category;
import com.bdqn.jackey.service.CategoryService;
import com.bdqn.jackey.utils.LayData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 周坤 on 2020/9/24.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    /**
     * 获取所有分类显示在新增图书页面
     */
    @RequestMapping("/all")
    @ResponseBody
    public LayData getAllCategories(HttpServletRequest request) {
        String from = request.getParameter("page");
        String limit = request.getParameter("limit");
        int count = categoryService.queryAllCategoriesCount();
        List<Category> categories = new ArrayList<Category>();
        if (from == null || limit == null) {
            categories = categoryService.queryAllCategories(0, count);
        } else {
            Integer pageSize = Integer.parseInt(limit);
            Integer page = Integer.parseInt(from);
            categories = categoryService.queryAllCategories((page - 1) * pageSize, pageSize);
        }
        return new LayData(0, "成功", count, categories);
    }

    /**
     * 点击首页分类信息跳转页面
     *
     * @return
     */
    @RequestMapping("/toList")
    public String toCategoryList() {
        return "/categorylist";
    }

    /**
     * 新增分类信息
     * @param category 分类对象
     * @return 结果
     */
    @RequestMapping("/add")
    @ResponseBody
    public int addCategory(Category category) {
        return categoryService.addCategory(category);
    }

    /**
     * 修改分类信息
     * @param category 分类信息
     * @return 结果
     */
    @RequestMapping("/update")
    @ResponseBody
    public int updateCategory(Category category) {
        return categoryService.updateCategory(category);
    }

    /**
     * 删除分类信息
     * @param id 分类id
     * @return 结果
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public int deleteCategory(@PathVariable("id") Integer id) {
        return categoryService.deleteCategory(id);
    }

    /**
     * 重新启用分类
     * @param id 分类id
     * @return 结果
     */
    @RequestMapping("/reuse/{id}")
    @ResponseBody
    public int reUseCategory(@PathVariable("id") Integer id) {
        return categoryService.editCategoryStatus(id);
    }
}
