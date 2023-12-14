package concerttours.service;

import concerttours.model.NewsModel;

import java.util.Date;
import java.util.List;

public interface NewsService {
    List<NewsModel> getNewsOfDay(Date date);

    List<NewsModel> getNews();

    NewsModel getNewsByHeadline(String headline);
}
