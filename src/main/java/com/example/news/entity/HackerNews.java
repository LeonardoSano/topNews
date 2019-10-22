package com.example.news.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class HackerNews
        extends News {

    private String by;
    private Float descendants;
    private int id;
    private List<Integer> kids;
    private Float score;
    private String type;
    private long time;

    @Override
    public Date getData() {
        return new Date(this.time * 1000);
    }

}