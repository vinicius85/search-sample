package com.crossover.test.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.crossover.test.model.ArticleForm;
import com.crossover.test.repository.ArticleRepository;
import com.crossover.test.security.UserDetailsService;

@RunWith(MockitoJUnitRunner.class)
public class BackofficeControllerTest {

	@Mock
	private ArticleForm form;
	@Mock
	private ArticleRepository articleRepo;
	@Mock
	private UserDetailsService userDetailsService;
	@InjectMocks
	private BackofficeController controller;

	@Test
	public void shouldReturnCreateArticle() {
		ModelAndView mav = controller.createArticle();
		Assert.assertTrue("createArticle".equals(mav.getViewName()));
	}

	@Test
	public void shouldReturnSavedArticle() {
		String addArticle = controller.addArticle(form);
		Assert.assertTrue("Article saved!".equals(addArticle));
	}

}
