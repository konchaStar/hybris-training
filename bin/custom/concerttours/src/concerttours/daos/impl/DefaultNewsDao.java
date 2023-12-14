package concerttours.daos.impl;

import concerttours.daos.NewsDao;
import concerttours.model.NewsModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class DefaultNewsDao implements NewsDao {
    private static final String SQL_DATE_FORMAT = "yyyy-MM-dd";
    private static final String QUERY_NEWS_WHERE_DATE = "SELECT {p:" + NewsModel.PK + "} "
            + "FROM {" + NewsModel._TYPECODE + " AS p} " + "WHERE {date} = DATE '%S'";
    private static final String SELECT_NEW_QUERY = "SELECT {p:" + NewsModel.PK + "} FROM {" + NewsModel._TYPECODE + " AS p}";
    private static final String WHERE_HEADLINE_CONDITION = " WHERE {p:headline} = '%s'";

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<NewsModel> getNewsOfDay(@Nonnull Date date) {
        String day = new SimpleDateFormat(SQL_DATE_FORMAT).format(date);
        String strQuery = String.format(QUERY_NEWS_WHERE_DATE, day);

        FlexibleSearchQuery query = new FlexibleSearchQuery(strQuery);

        return flexibleSearchService.<NewsModel>search(query).getResult();
    }

    @Override
    public List<NewsModel> getNews() {
        FlexibleSearchQuery query = new FlexibleSearchQuery(SELECT_NEW_QUERY);
        return flexibleSearchService.<NewsModel>search(query).getResult();
    }

    @Override
    public List<NewsModel> getNewsByHeadline(String headline) {
        String condition = String.format(WHERE_HEADLINE_CONDITION, headline);
        FlexibleSearchQuery query = new FlexibleSearchQuery(String.format("%s%s", SELECT_NEW_QUERY, condition));
        return flexibleSearchService.<NewsModel>search(query).getResult();
    }
}
