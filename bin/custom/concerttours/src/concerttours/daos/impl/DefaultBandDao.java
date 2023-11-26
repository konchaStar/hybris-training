package concerttours.daos.impl;

import concerttours.daos.BandDao;
import concerttours.model.BandModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

public class DefaultBandDao implements BandDao {
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<BandModel> getBands() {
        final String queryString = "SELECT {p:" + BandModel.PK + "} FROM {" + BandModel._TYPECODE + " AS p} ";
        FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        return flexibleSearchService.<BandModel>search(query).getResult();
    }

    @Override
    public List<BandModel> getBandsByCode(String code) {
        String queryString = "SELECT {p:" + BandModel.PK + "} FROM {" + BandModel._TYPECODE + " AS p} "
                + "WHERE " + "{p:" + BandModel.CODE + "}=?code ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<BandModel>search(query).getResult();
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
