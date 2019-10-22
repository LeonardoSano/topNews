package com.example.news.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NyListDto implements Serializable {

    private String status;
    private String copyright;
    private String section;
    private String last_updated;
    private int num_results;
    private List<NyNewsDto> results;

}
