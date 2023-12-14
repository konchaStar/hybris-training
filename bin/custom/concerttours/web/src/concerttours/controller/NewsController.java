package concerttours.controller;

import concerttours.model.NewsModel;
import concerttours.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsController implements Controller {
    private static final String NEWS_ATTRIBUTE = "news";
    private static final String NEWS_JSP = "news.jsp";

    @Autowired
    private NewsService newsService;

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<NewsModel> news = newsService.getNews();
        Map<String, Object> model = new HashMap<>();

        model.put(NEWS_ATTRIBUTE, news);

        return new ModelAndView(NEWS_JSP, model);
    }
}
