package com.example.news.entity;


import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public abstract class News implements Comparable<News>, Serializable {

    private String title;
    private String url;
    private Date data;

    abstract Date getData();

    @Override
    public int compareTo(News b) {
        return -this.getData().compareTo(b.getData());

    }

}