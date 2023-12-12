package concerttours.facades.impl;

import concerttours.data.BandData;
import concerttours.facades.BandFacade;
import concerttours.model.BandModel;
import concerttours.model.ProducerModel;
import concerttours.service.BandService;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.servicelayer.media.MediaService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultBandFacade implements BandFacade {
    private static final String LIST_FORMAT = "bandList";
    private static final String DETAIL_FORMAT = "bandDetail";

    private BandService bandService;
    private MediaService mediaService;

    public void setBandService(BandService bandService) {
        this.bandService = bandService;
    }

    public void setMediaService(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @Override
    public List<BandData> getBands() {
        List<BandModel> models = bandService.getBands();
        MediaFormatModel format = mediaService.getFormat(LIST_FORMAT);

        List<BandData> dataList = models.stream()
                .map(model -> {
                    BandData data = new BandData();
                    data.setBandHistory(model.getHistory());
                    data.setCode(model.getCode());
                    data.setName(model.getName());
                    data.setSoldAlbums(model.getAlbumSales());
                    data.setImageUrl(getImageUrl(model.getImage(), format));
                    return data;
                })
                .collect(Collectors.toList());

        return dataList;
    }

    private String getImageUrl(MediaContainerModel container, MediaFormatModel format) {

        if (Objects.nonNull(container)) {
            return mediaService.getMediaByFormat(container, format).getDownloadURL();
        }

        return null;
    }

    @Override
    public BandData getBandByCode(String code) {
        BandModel bandModel = bandService.getBandByCode(code);
        MediaFormatModel format = mediaService.getFormat(DETAIL_FORMAT);

        BandData bandData = new BandData();
        bandData.setBandHistory(bandModel.getHistory());
        bandData.setCode(bandModel.getCode());
        bandData.setName(bandModel.getName());
        bandData.setSoldAlbums(bandModel.getAlbumSales());
        bandData.setImageUrl(getImageUrl(bandModel.getImage(), format));

        ProducerModel producer = bandModel.getProducer();

        if (!Objects.nonNull(producer)) {
            bandData.setProducer(String.format("%s %s", producer.getName(), producer.getSurname()));
        }

        return bandData;
    }
}
