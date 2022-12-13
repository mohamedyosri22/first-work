package com.spring.controller;

import com.spring.exception.ResourceNotFoundException;
import com.spring.model.Articles;
import com.spring.model.News;
import com.spring.repository.ArticlesRepo;
import com.spring.repository.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CurrencyEditor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/update")
public class UpdatesController {

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private ArticlesRepo articlesRepo;


    @PostMapping("/addNews")
    public String addNews(@RequestBody News news){
        newsRepo.save(news);

        return "added !!";
    }

    @PostMapping("/addArticles")
    public String addArticles(@RequestBody Articles articles){
        articlesRepo.save(articles);

        return "added !!";
    }

    @PostMapping("/news")
    public String updateNews(@RequestBody News news){
        News CurrentNews = newsRepo.findById(news.getId()).get();

        if(CurrentNews == null){
            throw new ResourceNotFoundException("id not found");
        }

        CurrentNews.setId(news.getId());
        CurrentNews.setContent(news.getContent());
        CurrentNews.setTitle(news.getTitle());

        newsRepo.save(CurrentNews);
        return "update successful";
    }

    @GetMapping("/news/{id}")
    public News getNews(@PathVariable("id")int id){
        News news = newsRepo.findById(id).get();
        if(news == null){
            throw new ResourceNotFoundException("id not found");
        }
        return news;
    }

    @PostMapping("/article")
    public String updateArticles(@RequestBody Articles article){

        Articles currentArticle = articlesRepo.findById(article.getId()).get();

        if(currentArticle == null){
            throw new ResourceNotFoundException("id not found");
        }

        currentArticle.setId(article.getId());
        currentArticle.setContent(article.getContent());
        currentArticle.setTitle(article.getTitle());

        articlesRepo.save(currentArticle);
        return "update successful";
    }

    @GetMapping("/article/{id}")
    public Articles getArticles(@PathVariable("id")int id){

        Articles article = articlesRepo.findById(id).get();
        if(article == null){
            throw new ResourceNotFoundException("id not found");
        }
        return article;
    }


}
