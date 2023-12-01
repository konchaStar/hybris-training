package concerttours.dynamicattributehandlers;

import concerttours.dynamicattributeshandlers.DaysUntilConcertDynamicAttributeHandler;
import concerttours.model.ConcertModel;
import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.Optional;

@UnitTest
public class DaysUntilConcertDynamicAttributeHandlerUnitTest {
    private static final long ONE_DAY = 1000 * 60 * 60 * 24;

    @Test
    public void get_DateInFuture_1L() {
        ConcertModel concert = new ConcertModel();
        Date concertDate = new Date(new Date().getTime() + ONE_DAY);
        DaysUntilConcertDynamicAttributeHandler handler = new DaysUntilConcertDynamicAttributeHandler();

        concert.setDate(concertDate);

        Assert.assertEquals(1L, handler.get(concert).get().longValue());
    }

    @Test
    public void get_nullDate_emptyOptional() {
        ConcertModel concert = new ConcertModel();
        DaysUntilConcertDynamicAttributeHandler handler = new DaysUntilConcertDynamicAttributeHandler();

        Assert.assertFalse(handler.get(concert).isPresent());
    }
}
