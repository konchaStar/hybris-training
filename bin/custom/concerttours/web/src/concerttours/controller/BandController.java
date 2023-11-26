package concerttours.controller;

import concerttours.service.BandService;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BandController implements Controller {

    private static final String CODE_PARAM = "code";
    private static final String BAND_ATTRIBUTE = "band";
    private static final String BANDS_ATTRIBUTE = "bands";
    private static final String BAND_JSP = "band.jsp";
    private static final String BANDS_JSP = "BANDS.JSP";

    private BandService bandService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = Optional.ofNullable(request.getParameter(CODE_PARAM)).orElse(StringUtil.EMPTY_STRING);
        Map<String, Object> model = new HashMap<>();
        if (!StringUtils.isEmpty(code)) {
            model.put(BAND_ATTRIBUTE, bandService.getBandByCode(code));
            return new ModelAndView(BAND_JSP, model);
        } else {
            model.put(BANDS_ATTRIBUTE, bandService.getBands());
            return new ModelAndView(BANDS_JSP, model);
        }
    }

    public void setBandService(BandService bandService) {
        this.bandService = bandService;
    }
}
