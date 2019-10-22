package com.example.news.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class NyNews
        extends News {
    private String section;
    private String subsection;
    private String byline;
    private String item_type;
    private Date updated_date;
    private Date created_date;
    private Date published_date;
    private String material_type_facet;
    private String kicker;
    private List<String> per_facet;
    private List<String> geo_facet;
    private String short_url;

    @Override
    public Date getData() {
        return getPublished_date();
    }
}