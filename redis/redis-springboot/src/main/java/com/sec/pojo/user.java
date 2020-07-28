package com.sec.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component//类注入到spring容器中
@AllArgsConstructor//有参构造函数
@NoArgsConstructor//无惨构造函数
@Data//getset
public class user implements Serializable {
    private String name;
    private String sex;
    private Integer age;
}
