package com.example;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * 代码生成器
 */
public class AutoGenerator_Mybatis_Plus {

    public static void main(String[] args) {
        //需要构建一个代码自动生成构造器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("周坤");//生成的作者名字
        gc.setOpen(false);//是否打开文件夹
        gc.setFileOverride(false);//是否覆盖原来生成的文件
        gc.setServiceName("%sService"); //去掉Service接口的首字母I
        gc.setIdType(IdType.ASSIGN_ID);//ID自动生成；雪花算法
        gc.setDateType(DateType.ONLY_DATE);//设置时间格式
        gc.setSwagger2(true); //实体属性 Swagger2 注解

        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("blog");//模块名
        pc.setParent("com.example");
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("user","dept");//需要映射的表名
        //strategy.setTablePrefix(pc.getModuleName() + "_");//设置表前缀不生成
        //strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型  @Accessors(chain = true) setter链式操作
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);//去掉布尔值的is_前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setLogicDeleteFieldName("deleted");//逻辑删除

        //自动装填配置

        strategy.setVersionFieldName("version");//乐观锁
        TableFill gmt_create=new TableFill("gmt_create", FieldFill.INSERT);//添加默认时间
        TableFill gmt_modified=new TableFill("gmt_modified", FieldFill.UPDATE);//默认添加修改时间
        ArrayList <TableFill> list=new ArrayList<>();
        list.add(gmt_create);
        list.add(gmt_modified);
        strategy.setTableFillList(list);


        strategy.setRestControllerStyle(true);//支持rest风格
        strategy.setControllerMappingHyphenStyle(true);//localhost:8080/hello_h2_1 //url中驼峰转连字符

        mpg.execute();
    }
}
