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
}
