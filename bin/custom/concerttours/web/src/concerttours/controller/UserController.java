package concerttours.controller;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.hsqldb.rights.User;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class UserController implements Controller {
    private UserService userService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uid = request.getParameter("uid");
        UserModel user = null;
        if (uid == null) {
            user = userService.getCurrentUser();
        } else {
            user = userService.getUser(uid);
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("user", user);
        return new ModelAndView("user.jsp", model);
    }

    @Required
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }
}