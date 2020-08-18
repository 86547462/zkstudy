package com.example.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 编写user实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @TableId(type = IdType.ID_WORKER) //默认雪花算法（全球唯一id）AUTO（自增id，数据库设置自增）NONE，未设置主键，INPUT手动输入UUID，ID_WORKER_STR字符串表示法
    private Long id;
    private String name;
    private Integer age;
    private String email;
    //乐观锁
    @Version
    private int version;

    //逻辑删除
    @TableLogic
    private int deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;//创建时间
    @TableField(fill = FieldFill.UPDATE)
    private Date gmtModified;//修改时间

}
