package com.example.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author 周坤
 * @since 2020-08-17
 */
@ApiModel(value="Zk_commentmeta对象", description="")
public class Zk_commentmeta implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "meta_id", type = IdType.AUTO)
    private Long meta_id;

    private Long comment_id;

    private String meta_key;

    private String meta_value;


    public Long getMeta_id() {
        return meta_id;
    }

    public void setMeta_id(Long meta_id) {
        this.meta_id = meta_id;
    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public String getMeta_key() {
        return meta_key;
    }

    public void setMeta_key(String meta_key) {
        this.meta_key = meta_key;
    }

    public String getMeta_value() {
        return meta_value;
    }

    public void setMeta_value(String meta_value) {
        this.meta_value = meta_value;
    }

    @Override
    public String toString() {
        return "Zk_commentmeta{" +
        "meta_id=" + meta_id +
        ", comment_id=" + comment_id +
        ", meta_key=" + meta_key +
        ", meta_value=" + meta_value +
        "}";
    }
}
