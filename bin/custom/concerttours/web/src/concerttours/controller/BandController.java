package concerttours.controller;

import concerttours.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BandController implements Controller {
    private BandService bandService;
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = Optional.ofNullable(request.getParameter("code")).orElse("");
        Map<String, Object> model = new HashMap<>();
        if(!code.isEmpty()) {
            model.put("band", bandService.getBandByCode(code));
            return new ModelAndView("band.jsp", model);
        } else {
            model.put("bands", bandService.getBands());
            return new ModelAndView("bands.jsp", model);
        }
    }

    public void setBandService(BandService bandService) {
        this.bandService = bandService;
    }
}
