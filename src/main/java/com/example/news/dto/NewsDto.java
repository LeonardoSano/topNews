package com.example.news.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class NewsDto implements Comparable<NewsDto>,Serializable {

    private String title;
    private String url;
    private String data;

    abstract String getData();

    @Override
    public int compareTo(NewsDto b) {
        return -this.getData().compareTo(b.getData());
    }
}