package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userInfo {
    private Integer id;
    private String user;
    private String pwd;
    private String power;
}
