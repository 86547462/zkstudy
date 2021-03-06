package com.example.pojo;

/**
 * 实体类
 */
public class Content {

    private String  img;
    private String price;
    private String title;

    public String getImg() {
        return img;
    }

    @Override
    public String toString() {
        return "Content{" +
                "img='" + img + '\'' +
                ", price='" + price + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Content() {
    }

    public Content(String img, String price, String title) {
        this.img = img;
        this.price = price;
        this.title = title;
    }
}
