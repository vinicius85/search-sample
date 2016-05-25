package com.crossover.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.crossover.test.model.Article;
import com.crossover.test.repository.SolrRepository;
import com.crossover.test.security.UserDetailsService;

/**
 * Render Home Page
 * 
 * @author vinicius
 *
 */
@Controller
public class HomePageController {

	@Autowired
	private SolrRepository repository;
	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping("/")
	public ModelAndView getArticlesFirstPage() {
		ModelAndView mav = new ModelAndView("index");
		//get user name and roles
		userDetailsService.getUserDetails(mav);
		
		//query articles using random criteria
		List<Article> articles = repository.get("*:*");
		mav.addObject("articles", articles);
		return mav;
	}

}
