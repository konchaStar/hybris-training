package concerttours.daos.impl;

import concerttours.daos.BandDao;
import concerttours.model.BandModel;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

@IntegrationTest
public class DefaultBandDaoIntTest {
    private static final String NAME = "Rock band";
    private static final String CODE = "Rock1";
    private static final String HISTORY = "history";

    private BandModel bandModel;

    @Resource
    private BandDao bandDao;

    @Resource
    private ModelService modelService;

    @Before
    public void init() {
        bandModel = getBandModel();
        modelService.save(bandModel);
    }

    @Test
    public void getBands_notEmptyBands_bands() {
        List<BandModel> bands = bandDao.getBands();
        Assert.assertEquals(1, bands.size());
        Assert.assertTrue(bands.contains(bandModel));
    }

    @Test
    public void getBandsByCode_validCode_band() {
        List<BandModel> bands = bandDao.getBandsByCode(CODE);
        Assert.assertEquals(CODE, bands.get(0).getCode());
        Assert.assertEquals(NAME, bands.get(0).getName());
        Assert.assertEquals(HISTORY, bands.get(0).getHistory());
    }

    @Test
    public void getBandsByCode_emptyCode_noBands() {
        List<BandModel> bands = bandDao.getBandsByCode("");
        Assert.assertTrue(bands.isEmpty());
    }

    private BandModel getBandModel() {
        BandModel bandModel = modelService.create(BandModel.class);
        bandModel.setCode(CODE);
        bandModel.setHistory(HISTORY);
        bandModel.setName(NAME);
        return bandModel;
    }
}
