package concerttours.facades;

import concerttours.data.BandData;

import java.util.List;

public interface BandFacade {
    List<BandData> getBands();
    BandData getBandByCode(String code);
}
