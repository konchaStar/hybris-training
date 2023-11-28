package concerttours.dynamicattributeshandlers;

import de.hybris.platform.core.model.ClientNameModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

public class DynamicAttributesStringSample implements DynamicAttributeHandler<String, ClientNameModel> {
    private static final String VALUE_DELIMITER = " ";
    private static final String ILLEGAL_ARGUMENT_ERROR = "Item model is required";

    @Override
    public String get(final ClientNameModel item) {
        if (item == null) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_ERROR);
        }

        return String.format("%s%s%s", item.getFirstName(), VALUE_DELIMITER, item.getLastName());
    }

    @Override
    public void set(final ClientNameModel item, final String value) {
        if (item != null && value != null) {
            String[] split = value.split(VALUE_DELIMITER);
            item.setFirstName(split[0]);
            item.setLastName(split[1]);
        }
    }
}
