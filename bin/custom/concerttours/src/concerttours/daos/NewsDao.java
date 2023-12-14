package concerttours.daos;

import concerttours.model.NewsModel;

import java.util.Date;
import java.util.List;

public interface NewsDao {
    List<NewsModel> getNewsOfDay(Date date);

    List<NewsModel> getNews();

    List<NewsModel> getNewsByHeadline(String headline);
}
