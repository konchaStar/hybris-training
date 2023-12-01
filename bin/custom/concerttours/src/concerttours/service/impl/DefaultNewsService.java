package concerttours.service.impl;

import concerttours.daos.NewsDao;
import concerttours.model.NewsModel;
import concerttours.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class DefaultNewsService implements NewsService {
    @Autowired
    private NewsDao newsDao;

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public List<NewsModel> getNewsOfDay(Date date) {
        return newsDao.getNewsOfDay(date);
    }
}
