package com.bdqn.jackey.pojo;

import java.util.Date;

/**
 * @author 周坤 on 2020/9/22.
 * 业务实体类
 */
public class Book {
    private Integer id;
    private Category category;
    private String title;
    private String summary;
    private String uploadUser;
    private String creatDate;
    private Integer delFlag;

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Book(Category category, String title, String summary, String uploadUser, String creatDate) {
        this.category = category;
        this.title = title;
        this.summary = summary;
        this.uploadUser = uploadUser;
        this.creatDate = creatDate;
    }

    public Book(Integer id, Category category, String title, String summary, String uploadUser, String creatDate) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.summary = summary;
        this.uploadUser = uploadUser;
        this.creatDate = creatDate;
    }

    public Book() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", category=" + category.getName() +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", uploadUser='" + uploadUser + '\'' +
                ", creatDate=" + creatDate +
                '}';
    }
}
