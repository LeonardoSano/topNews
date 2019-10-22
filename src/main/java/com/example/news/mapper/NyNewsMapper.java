package com.example.news.mapper;

import com.example.news.dto.NewsDto;
import com.example.news.dto.NyNewsDto;
import com.example.news.entity.NyNews;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


import java.util.List;


@Mapper(uses = CustomDate.class)
public interface NyNewsMapper {

    NyNewsMapper INSTANCE = Mappers.getMapper(NyNewsMapper.class);


    @Mapping(source = "updated_date", target = "updated_date", qualifiedByName = {"CustomDate", "DateToTime"})
    @Mapping(source = "published_date", target = "published_date", qualifiedByName = {"CustomDate", "DateToTime"})
    @Mapping(source = "created_date", target = "created_date", qualifiedByName = {"CustomDate", "DateToTime"})
    NyNewsDto nyNewsToNyNewsDto(NyNews nyNews);

    @IterableMapping(qualifiedByName = {"CustomDate", "DateToTime"})
    List<NewsDto> ListNewsToNewsListDto(List<NyNews> news);
}