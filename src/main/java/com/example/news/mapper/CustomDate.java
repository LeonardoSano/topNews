package com.example.news.mapper;

import org.mapstruct.Named;

import java.util.Date;

@Named("CustomDate")
public class CustomDate {
    @Named("DateToTime")
    public String dateToTimestamp(Date date) {
        return String.valueOf(date.getTime());
    }
    @Named("IntToTime")
    public String intToTimestamp(int date) {
        Long time = (long)date*1000;
        return String.valueOf(time);
    }
}