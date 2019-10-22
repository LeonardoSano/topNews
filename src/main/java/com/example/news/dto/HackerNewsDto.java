package com.example.news.dto;

import lombok.Data;

import java.util.List;

@Data
public class HackerNewsDto
        extends NewsDto {

    private String by;
    private Float descendants;
    private int id;
    private List<Integer> kids;
    private Float score;
    private String type;
    private String time;

    @Override
    public String getData() {
        return time;
    }
}