package concerttours.service.impl;

import concerttours.daos.BandDao;
import concerttours.model.BandModel;
import concerttours.service.BandService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class DefaultBandService implements BandService {
    private static final String NOT_UNIQUE_ERROR = "Band code '%s' is not unique, %d bands found!";
    private static final String NOT_FOUND_ERROR = "Band with code '%s' not found!";

    private BandDao bandDao;

    public void setBandDao(BandDao bandDao) {
        this.bandDao = bandDao;
    }

    @Override
    public List<BandModel> getBands() {
        return bandDao.getBands();
    }

    @Override
    public BandModel getBandByCode(String code) {
        List<BandModel> bands = bandDao.getBandsByCode(code);

        if (bands.size() > 1) {
            throw new AmbiguousIdentifierException(String.format(NOT_UNIQUE_ERROR, code, bands.size()));
        }

        if (CollectionUtils.isEmpty(bands)) {
            throw new UnknownIdentifierException(String.format(NOT_FOUND_ERROR, code));
        }

        return bands.get(0);
    }
}
