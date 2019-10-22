package com.example.news.Service;

import com.example.news.dto.NewsDto;
import com.example.news.entity.HackerNews;
import com.example.news.entity.NyList;
import com.example.news.entity.NyNews;
import com.example.news.entity.TypeNews;
import com.example.news.mapper.HackerNewsMapper;
import com.example.news.mapper.NyNewsMapper;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsServiceInterface {

    private WebClient webClient;

    /**
     * getItemHackNews()
     *
     * @return La lista di item necessaria per completare i link per le news di
     * HackerNews
     */
    @Override
    public Mono<Integer[]> getItemHackNews() {

        this.webClient = WebClient.create("https://hacker-news.firebaseio.com");

        return webClient
                .get()
                .uri("/v0/topstories.json?print=pretty")
                .retrieve()
                .bodyToMono(Integer[].class);
    }

    /**
     * dati gli itemId di hacker-news
     *
     * @param alfa
     * @return tutte le notizie del sito hacker-news
     */
    @Override
    public List<NewsDto> searchByItem(Mono<Integer[]> alfa) {
        this.webClient = WebClient.create("https://hacker-news.firebaseio.com");
        List<Integer> alfaList = Arrays.asList(alfa.block());
        List<List<Integer>> alfaSubList = Lists.partition(alfaList, 20);
        List<Mono<HackerNews>> monos = new ArrayList<>();
        for (int cont = 0; cont < alfaSubList.size(); cont++) {
            monos.addAll(alfaSubList.get(cont).stream()
                    .map(i -> webClient.get().uri("/v0/item/" + i + ".json").retrieve().bodyToMono(HackerNews.class))
                    .collect(Collectors.toList()));
        }
        List<HackerNews> hcknewsDto = new LinkedList<>((Flux.mergeSequential(monos)).collectList().block());
        List<NewsDto> news = HackerNewsMapper.INSTANCE.stringDateToStringList(hcknewsDto);
        return news;

    }

    /**
     * estrae le top news del nytimes
     *
     * @return lista delle notizie del ny time
     */
    @Override
    public List<NewsDto> searchNyNews() {

        this.webClient = WebClient.create();
        Mono<NyList> nyList =
                webClient
                        .get()
                        .uri("https://api.nytimes.com/svc/topstories/v2/science.json?api-key=lrO3y7ujzL09S95JgWFZcBeySIJkGqmq")
                        .retrieve()
                        .bodyToMono(NyList.class);
        List<NyNews> news = new LinkedList<>(nyList.block().getResults());
        List<NewsDto> newsDto = NyNewsMapper.INSTANCE.ListNewsToNewsListDto(news);
        return newsDto;

    }

    /**
     * List<News> getNewsList()
     *
     * @return lista di notizie prese da hacker-news e dal new york time ordinate
     * per data
     */
    @Override
    public List<NewsDto> getNewsList() {
        List<NewsDto> newsList = new LinkedList<>(this.searchByItem(this.getItemHackNews()));
        List<NewsDto> nyList = this.searchNyNews();
        newsList.addAll(nyList.parallelStream().collect(Collectors.toList()));
        return newsList;
    }

    @Override
    public List<NewsDto> getHackerNews() {
        return this.searchByItem(this.getItemHackNews()).parallelStream().sorted().collect(Collectors.toList());
    }

    @Override
    public List<NewsDto> getNyNews() {
        return (this.searchNyNews().parallelStream().sorted().collect(Collectors.toList()));
    }

    @Override
    public List<NewsDto> getAllNews() {
        return (this.getNewsList().parallelStream().sorted().collect(Collectors.toList()));
    }

    public List<NewsDto> newsBySource(TypeNews request) {
        return request.getNews();
    }

}