package concerttours.daos;

import concerttours.model.BandModel;

import java.util.List;

public interface BandDao {
    List<BandModel> getBands();
    List<BandModel> getBandsByCode(String code);
}
