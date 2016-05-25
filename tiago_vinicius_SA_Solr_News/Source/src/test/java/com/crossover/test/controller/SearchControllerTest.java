package com.crossover.test.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.crossover.test.model.Article;
import com.crossover.test.repository.SolrRepository;
import com.crossover.test.security.UserDetailsService;

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest {

	@Mock
	private SolrRepository repository;
	@Mock
	private UserDetailsService service;
	@InjectMocks
	private SearchController controller;

	@Test
	public void shouldShowArticle() {
		String articleId = "123AB";
		Article article = new Article();
		article.setId(articleId);
		
		Mockito.when(repository.getArticleById(articleId)).thenReturn(article);
		ModelAndView mav = controller.showArticle(articleId);
		Assert.assertEquals(article, mav.getModel().get("article"));
		Assert.assertEquals("article", mav.getViewName());

	}

}
