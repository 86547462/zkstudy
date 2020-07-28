package com.example.blog.service.impl;

import com.example.blog.pojo.Dept;
import com.example.blog.mapper.DeptMapper;
import com.example.blog.pojo.User;
import com.example.blog.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 周坤
 * @since 2020-08-17
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {


    @Autowired
    DeptMapper mapper;
    @Override
    public List<Dept> selAllDpet(String dep_tname) {
        return mapper.selAllDpet(dep_tname);
    }
}
