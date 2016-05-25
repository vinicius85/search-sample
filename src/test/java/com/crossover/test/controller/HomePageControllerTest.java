package com.crossover.test.controller;

import java.util.Arrays;
import java.util.List;

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
public class HomePageControllerTest {

	@Mock
	private SolrRepository repository;
	@Mock
	private UserDetailsService userDetailsService;
	@InjectMocks
	private HomePageController controller;

	@Test
	public void shouldReturnHomePage() {
		Article article1 = new Article();
		article1.setId("1");
		Article article2 = new Article();
		article1.setId("2");
		List<Article> articles = Arrays.asList(article1, article2);
		Mockito.when(repository.get("*:*")).thenReturn(articles);
		ModelAndView mav = controller.getArticlesFirstPage();
		List<Article> resultArticles = (List<Article>) mav.getModel().get("articles");
		Assert.assertEquals(articles, resultArticles);
		Assert.assertTrue("index".equals(mav.getViewName()));
	}

}
