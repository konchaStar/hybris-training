package concerttours.service.impl;

import concerttours.daos.NewsDao;
import concerttours.model.NewsModel;
import concerttours.service.NewsService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class DefaultNewsService implements NewsService {
    private static final String NOT_UNIQUE_ERROR = "Not unique headline, found %d news";
    private static final String NOT_FOUND_ERROR = "Cannot find news with headline %s";

    @Autowired
    private NewsDao newsDao;

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public List<NewsModel> getNewsOfDay(Date date) {
        return newsDao.getNewsOfDay(date);
    }

    @Override
    public List<NewsModel> getNews() {
        return newsDao.getNews();
    }

    @Override
    public NewsModel getNewsByHeadline(String headline) {
        List<NewsModel> news = newsDao.getNewsByHeadline(headline);

        if(news.size() > 1) {
            throw new AmbiguousIdentifierException(String.format(NOT_UNIQUE_ERROR, news.size()));
        }

        if(CollectionUtils.isEmpty(news)) {
            throw new UnknownIdentifierException(String.format(NOT_FOUND_ERROR, headline));
        }

        return news.get(0);
    }
}
