package concerttours.cjperform;

import concerttours.model.TokenCronJobModel;
import concerttours.model.TokenModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.UUID;

public class TokenPerformable extends AbstractJobPerformable<TokenCronJobModel> {
    private static final String SELECT_TOKEN_QUERY = "SELECT {p:" + TokenModel.PK
            + "} FROM {" + TokenModel._TYPECODE + " AS p}";

    private FlexibleSearchService flexibleSearchService;
    private ModelService modelService;

    @Override
    public PerformResult perform(TokenCronJobModel tokenCronJobModel) {
        FlexibleSearchQuery query = new FlexibleSearchQuery(SELECT_TOKEN_QUERY);
        TokenModel token = flexibleSearchService.<TokenModel>search(query).getResult().get(0);
        token.setToken(UUID.randomUUID().toString());
        modelService.save(token);
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    @Override
    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
