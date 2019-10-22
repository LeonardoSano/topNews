package com.example.news.entity;

import com.example.news.Service.NewsServiceImpl;
import com.example.news.dto.NewsDto;

import java.util.List;

public enum TypeNews {

    HCK{
        @Override
        public List<NewsDto> getNews(){
            return new NewsServiceImpl().getHackerNews();
        }
    },
    NY{
        @Override
        public List<NewsDto> getNews(){
            return new NewsServiceImpl().getNyNews();
        }
    },
    ALL{
        @Override
        public List<NewsDto> getNews(){
            return new NewsServiceImpl().getAllNews();
        }
    };

    public abstract List<NewsDto> getNews();
}