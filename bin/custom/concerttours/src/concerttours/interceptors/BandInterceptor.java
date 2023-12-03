package concerttours.interceptors;

import concerttours.events.BandAlbumSalesEvent;
import concerttours.model.BandModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BandInterceptor implements ValidateInterceptor, PrepareInterceptor {
    private static final String NEGATIVE_ERROR = "Album sales must be positive";
    private static final Long BIG_ALBUM_SALES = 100000L;

    @Autowired
    private EventService eventService;

    @Override
    public void onPrepare(Object object, InterceptorContext interceptorContext) throws InterceptorException {
        if (object instanceof BandModel) {
            BandModel band = (BandModel) object;

            if (band.getAlbumSales() > BIG_ALBUM_SALES) {
                eventService.publishEvent(new BandAlbumSalesEvent(band.getName(), band.getAlbumSales()));
            }

        }
    }

    @Override
    public void onValidate(Object object, InterceptorContext interceptorContext) throws InterceptorException {
        if (object instanceof BandModel) {
            BandModel band = (BandModel) object;
            Long sales = band.getAlbumSales();

            if (Objects.nonNull(sales) && sales < 0L) {
                throw new InterceptorException(NEGATIVE_ERROR);
            }

        }
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
}
