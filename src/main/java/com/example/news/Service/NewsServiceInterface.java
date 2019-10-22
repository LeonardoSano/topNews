package com.example.news.Service;


import com.example.news.dto.NewsDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface NewsServiceInterface {

    Mono<Integer[]> getItemHackNews();

    List<NewsDto> searchByItem(Mono<Integer[]> alfa);

    List<NewsDto> searchNyNews();

    List<NewsDto> getNewsList();

    List<NewsDto> getHackerNews();

    List<NewsDto> getNyNews();

    List<NewsDto> getAllNews();

}