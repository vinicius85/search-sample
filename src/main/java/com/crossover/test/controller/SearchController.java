package com.crossover.test.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crossover.test.model.Article;
import com.crossover.test.repository.SolrRepository;
import com.crossover.test.security.UserDetailsService;

/**
 * SearchController - request from Solr
 * @author vinicius
 *
 */
@Controller
@RequestMapping("/news")
public class SearchController {

	@Autowired
	private SolrRepository repository;
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Method invoked by Angular to exhibit search results in SPA pattern
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/searchForm", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Article> search(@RequestParam("q") String query) {
		//If there is no keyword typed in textbox just return some random stuff
		query = StringUtils.isBlank(query) ? "*:*" : query;
		List<Article> articles = repository.get(query);
		return articles;
	}
	
	@RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
	public ModelAndView showArticle(@PathVariable("articleId") String id) {
		ModelAndView mav = new ModelAndView("article");
		//get user name and roles
		userDetailsService.getUserDetails(mav);
		//get article by id from Solr
		Article article= repository.getArticleById(id);
		mav.addObject("article", article);
		return mav;
	}

}
