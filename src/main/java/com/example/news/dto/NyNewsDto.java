package com.example.news.dto;

import lombok.Data;

import java.util.List;

@Data
public class NyNewsDto
        extends NewsDto {
    private String section;
    private String subsection;
    private String byline;
    private String item_type;
    private String updated_date;
    private String created_date;
    private String published_date;
    private String material_type_facet;
    private String kicker;
    private List<String> per_facet;
    private List<String> geo_facet;
    private String short_url;

    @Override
    public String getData() {
        return getPublished_date();
    }
}