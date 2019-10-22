package com.example.news.mapper;

import com.example.news.dto.HackerNewsDto;
import com.example.news.dto.NewsDto;
import com.example.news.entity.HackerNews;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(uses = CustomDate.class)
public interface HackerNewsMapper {

    HackerNewsMapper INSTANCE = Mappers.getMapper( HackerNewsMapper.class);

    @Mapping(source = "time", target = "time",qualifiedByName = {"CustomDate","IntToTime"})
    HackerNewsDto HackerNewsToHackerNewsDto(HackerNews hackerNews);

    @IterableMapping(qualifiedByName = {"CustomDate","IntToTime"})
    List<NewsDto> stringDateToStringList(List<HackerNews> dates);
}