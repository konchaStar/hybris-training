package concerttours.service.impl;

import concerttours.daos.BandDao;
import concerttours.model.BandModel;
import concerttours.service.BandService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.List;

public class DefaultBandService implements BandService {

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
            throw new AmbiguousIdentifierException("Band code '" + code + "' is not unique, " + bands.size() + " bands found!");
        }
        if (bands.isEmpty()) {
            throw new UnknownIdentifierException("Band with code '" + code + "' not found!");
        }
        return bands.get(0);
    }
}
