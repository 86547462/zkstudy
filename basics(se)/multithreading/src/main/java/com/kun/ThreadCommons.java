package com.kun;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;


//练习Thread，实现多线程同步下载图片
public class ThreadCommons implements Runnable{

    private String url;
    private String name;
    public ThreadCommons(String url,String name){
        this.url=url;
        this.name=name;
    }

    public void run() {
        webDownloader webDownloader=new webDownloader();
        webDownloader.downLoader(url,name);
        System.out.println("图片下载成功!"+name);

    }

    public static void main(String[] args) {
        ThreadCommons t1=new ThreadCommons("http://img.netbian.com/file/2020/0818/4769624e404775ab1f8e0ccdacb73671.jpg","multithreading/src/main/resources/img/1.jpg");
        ThreadCommons t2=new ThreadCommons("http://img.netbian.com/file/2020/0729/04162503777824e0543884d809b4119b.jpg","multithreading/src/main/resources/img/2.jpg");
        ThreadCommons t3=new ThreadCommons("http://img.netbian.com/file/2020/0518/8f7e5dd953443cfc101ef3fc9fae395d.jpg","multithreading/src/main/resources/img/3.jpg");
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();

    }

}

//图片下载器
class webDownloader{

    public void downLoader(String url,String name) {
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

