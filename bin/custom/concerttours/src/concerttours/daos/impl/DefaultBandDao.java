package concerttours.daos.impl;

import concerttours.daos.BandDao;
import concerttours.model.BandModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("bandDao")
public class DefaultBandDao implements BandDao {
    private static final String SELECT_ALL_QUERY = "SELECT {p:" + BandModel.PK + "}" +
            " FROM {" + BandModel._TYPECODE + " AS p} ";
    private static final String SELECT_CODE_QUERY = "SELECT {p:" + BandModel.PK + "}" +
            " FROM {" + BandModel._TYPECODE + " AS p} WHERE{p:" + BandModel.CODE + "}=?code ";
    private static final String CODE_PARAM = "code";
    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<BandModel> getBands() {
        FlexibleSearchQuery query = new FlexibleSearchQuery(SELECT_ALL_QUERY);
        return flexibleSearchService.<BandModel>search(query).getResult();
    }

    @Override
    public List<BandModel> getBandsByCode(String code) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(SELECT_CODE_QUERY);
        query.addQueryParameter(CODE_PARAM, code);
        return flexibleSearchService.<BandModel>search(query).getResult();
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
