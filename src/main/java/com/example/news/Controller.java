package com.example.news;

import com.example.news.Service.NewsServiceImpl;
import com.example.news.dto.NewsDto;
import com.example.news.entity.TypeNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    NewsServiceImpl newsService;

    @GetMapping("/news/{source}")
    public List<NewsDto> getSpecificNews(@PathVariable TypeNews source) {
        return  this.newsService.newsBySource(source);
    }
}