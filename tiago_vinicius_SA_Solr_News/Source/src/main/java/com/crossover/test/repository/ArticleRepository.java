package com.crossover.test.repository;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.springframework.stereotype.Repository;

import com.crossover.test.model.Article;

/**
 * Repository articles in MySQL
 * 
 * @author vinicius
 *
 */
@Repository
public class ArticleRepository {

	/**
	 * TODO: Improvement: database credentials as application config
	 * @param article
	 */
	public void insertArticle(Article article) {
		DBI dbi = new DBI("jdbc:mysql://localhost:3306/news", "root", "root");
		Handle handle = dbi.open();
		ArticleDAO articleDAO = handle.attach(ArticleDAO.class);
		articleDAO.insertArticle(article);
	}

}
