package com.vizaco.onlinecontrol.controller;

import com.vizaco.onlinecontrol.model.News;
import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.service.NewsService;
import com.vizaco.onlinecontrol.service.RoleService;
import com.vizaco.onlinecontrol.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class NewsController extends BaseController{

    @Autowired
    private NewsService newsService;
    @Autowired
    private Utils utils;

    @RequestMapping(value = "/news")
    public ModelAndView readAllNews() {

        ModelAndView mav = new ModelAndView("news/news");

        List<News> news = newsService.getAllNews();

        mav.addObject("news", news);

        return mav;
    }

    //CREATE NEWS

    @RequestMapping(value = "/news/new", method = RequestMethod.GET)
    public String register(Model model) {
        News news = new News();
        model.addAttribute(news);
        return "/news/createOrUpdateNewsForm";
    }

    @RequestMapping(value = "/news/new", method = RequestMethod.POST)
    public String saveNews(@ModelAttribute("news") @Valid News news, BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute(news);
            return "/news/createOrUpdateNewsForm";
        }

        newsService.saveNews(news);
        return "redirect:/news/";
    }

    //READ ROLE

    @RequestMapping(value = "/news/{newsId}")
    public ModelAndView readNews(@PathVariable("newsId") String newsIdStr) {

        News news = utils.getNews(newsIdStr, null);

        ModelAndView mav = new ModelAndView("/news/newsDetails");

        mav.addObject("news", news);

        return mav;
    }

    //UPDATE ROLE

    @RequestMapping(value = "/news/{newsId}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("newsId") String newsIdStr) {

        News news = utils.getNews(newsIdStr, null);
        ModelAndView mav = new ModelAndView("/news/createOrUpdateNewsForm");

        mav.addObject("news", news);

        return mav;
    }

    @RequestMapping(value = "/news/{newsId}/edit", method = RequestMethod.PUT)
    public String edit(@PathVariable("newsId") String newsIdStr, @ModelAttribute("news") @Valid News news, BindingResult result, Model model) {

        News newsEdit = utils.getNews(newsIdStr, null);

        if(result.hasErrors()){
            model.addAttribute("news", news);
            return "/news/createOrUpdateNewsForm";
        }

        news.setId(newsEdit.getId());
        newsService.saveNews(news);
        return "redirect:/news/";
    }

    //DELETE ROLE

    @RequestMapping(value = "/news/{newsId}/delete", method = RequestMethod.DELETE)
    public ModelAndView deleteNews(@PathVariable("newsId") String newsIdStr) {

        News news = utils.getNews(newsIdStr, null);
        newsService.deleteNews(news.getId());

        return new ModelAndView("redirect:/news");

    }

}
