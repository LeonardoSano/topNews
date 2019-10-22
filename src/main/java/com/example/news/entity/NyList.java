package com.example.news.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NyList implements Serializable {

    private String status;
    private String copyright;
    private String section;
    private String last_updated;
    private int num_results;
    private List<NyNews> results;

}