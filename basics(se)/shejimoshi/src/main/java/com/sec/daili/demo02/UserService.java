package com.sec.daili.demo02;

/**
 * 代理模式再理解
 * 在不改变原有代码的条件下，扩展功能（使用代理模式）
 */
public interface UserService {
    void add();
    void delete();
    void update();
    void query();

}
