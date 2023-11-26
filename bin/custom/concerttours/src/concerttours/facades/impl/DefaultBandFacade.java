package concerttours.facades.impl;

import concerttours.data.BandData;
import concerttours.facades.BandFacade;
import concerttours.model.BandModel;
import concerttours.service.BandService;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultBandFacade implements BandFacade {
    private BandService bandService;

    public void setBandService(BandService bandService) {
        this.bandService = bandService;
    }

    @Override
    public List<BandData> getBands() {
        List<BandModel> models = bandService.getBands();

        List<BandData> dataList = models.stream()
                .map(model -> {
                    BandData data = new BandData();
                    data.setBandHistory(model.getHistory());
                    data.setCode(model.getCode());
                    data.setName(model.getName());
                    data.setSoldAlbums(model.getAlbumSales());
                    return data;
                })
                .collect(Collectors.toList());

        return dataList;
    }

    @Override
    public BandData getBandByCode(String code) {
        BandModel bandModel = bandService.getBandByCode(code);

        BandData bandData = new BandData();
        bandData.setBandHistory(bandModel.getHistory());
        bandData.setCode(bandModel.getCode());
        bandData.setName(bandModel.getName());
        bandData.setSoldAlbums(bandModel.getAlbumSales());

        return bandData;
    }
}