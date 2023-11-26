package concerttours.controller;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.hsqldb.rights.User;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class UserController implements Controller {
    private static final String UID_PARAM = "uid";
    private static final String USER_ATTRIBUTE = "user";
    private UserService userService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uid = request.getParameter(UID_PARAM);
        UserModel user = null;
        if (StringUtils.isEmpty(uid)) {
            user = userService.getCurrentUser();
        } else {
            user = userService.getUser(uid);
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put(USER_ATTRIBUTE, user);
        return new ModelAndView("user.jsp", model);
    }

    @Required
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }
}