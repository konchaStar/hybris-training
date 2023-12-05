package concerttours.dynamicattributeshandlers;

import concerttours.model.ConcertModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

@Component
public class DaysUntilConcertDynamicAttributeHandler extends AbstractDynamicAttributeHandler<Optional<Long>, ConcertModel> {
    @Override
    public Optional<Long> get(@Nonnull ConcertModel model) {
        if (Objects.nonNull(model.getDate())) {
            return Optional.empty();
        }

        ZonedDateTime date = model.getDate().toInstant().atZone(ZoneId.systemDefault());
        ZonedDateTime now = ZonedDateTime.now();
        Duration duration = Duration.between(now, date);

        return Optional.of(duration.toDays());
    }

}
