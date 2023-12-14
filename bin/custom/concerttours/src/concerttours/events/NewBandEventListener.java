package concerttours.events;

import concerttours.model.BandModel;
import concerttours.model.NewsModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.jalo.CatalogVersion;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.event.events.AfterItemCreationEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;

public class NewBandEventListener extends AbstractEventListener<AfterItemCreationEvent> {
    private static final String HEADLINE = "New band %s";
    private static final String CONTENT = "There is a new band %s. Tour news to be soon";
    private static final String CATALOG_ID = "NewsCatalog";
    private static final String CATALOG_VERSION = "Online";

    private ModelService modelService;
    private CatalogVersionService catalogVersionService;

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public void setCatalogVersionService(CatalogVersionService catalogVersionService) {
        this.catalogVersionService = catalogVersionService;
    }

    @Override
    protected void onEvent(AfterItemCreationEvent afterItemCreationEvent) {
        Object item = modelService.get(afterItemCreationEvent.getSource());

        if (item instanceof BandModel) {
            BandModel bandModel = (BandModel) item;
            NewsModel news = getNewsModel(bandModel.getName());

            modelService.save(news);
        }
    }

    private NewsModel getNewsModel(String bandName) {
        NewsModel news = modelService.create(NewsModel.class);
        CatalogVersionModel version = catalogVersionService.getCatalogVersion(CATALOG_ID, CATALOG_VERSION);

        news.setDate(new Date());
        news.setHeadline(String.format(HEADLINE, bandName));
        news.setContent(String.format(CONTENT, bandName));
        news.setCatalogVersion(version);

        return news;
    }
}
