package com.crossover.test.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crossover.test.model.Article;
import com.crossover.test.model.ArticleForm;
import com.crossover.test.repository.ArticleRepository;
import com.crossover.test.security.UserDetailsService;

/**
 * BackOffice Controller - Methods to save articles in database
 * @author vinicius
 *
 */
@Controller
@RequestMapping("/backoffice")
public class BackofficeController {

	@Autowired
	private ArticleRepository articleRepo;
	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(value = "/createArticle")
	public ModelAndView createArticle() {
		ModelAndView mav = new ModelAndView("createArticle");
		//get username and roles
		userDetailsService.getUserDetails(mav);
		return mav;
	}

	@RequestMapping("/saveArticle")
	@ResponseBody
	public String addArticle(@ModelAttribute ArticleForm form) {
		//get values from form and populate model entity
		Article article = new Article().setId(RandomStringUtils.randomAlphanumeric(10)).setAuthor(form.getAuthor())
				.setTitle(form.getTitle()).setContent(form.getContent()).setCategory(form.getCategory())
				.setImage(form.getImage());
		//insert in news.article MySQL table
		articleRepo.insertArticle(article);
		return "Article saved!";
	}
}