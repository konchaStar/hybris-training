package concerttours.events;

import concerttours.model.BandModel;
import concerttours.model.NewsModel;
import de.hybris.platform.servicelayer.event.events.AfterItemCreationEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;

public class NewBandEventListener extends AbstractEventListener<AfterItemCreationEvent> {
    private static final String HEADLINE = "New band %s";
    private static final String CONTENT = "There is a new band %s. Tour news to be soon";

    private ModelService modelService;

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
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

        news.setDate(new Date());
        news.setHeadline(String.format(HEADLINE, bandName));
        news.setContent(String.format(CONTENT, bandName));

        return news;
    }
}
