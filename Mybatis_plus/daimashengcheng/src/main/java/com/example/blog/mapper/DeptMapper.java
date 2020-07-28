package com.example.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.pojo.Dept;
import com.example.blog.pojo.User;
import org.springframework.stereotype.Component;


import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 周坤
 * @since 2020-08-17
 */
@Component
public interface DeptMapper extends BaseMapper<Dept> {
    List<Dept> selAllDpet(String dept_name);//1对多 查看部门拥有的员工
}
