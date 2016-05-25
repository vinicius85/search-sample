package com.crossover.test.repository;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import com.crossover.test.model.Article;

/**
 * JDBI binds SQL query to Article bean and save in database
 * 
 * @author vinicius
 *
 */
public interface ArticleDAO {

	@SqlUpdate(value = "INSERT INTO article (id, title, content, author, tags, image, category) VALUES (:id, :title, :content, :author, :tags, :image, :category)")
	void insertArticle(@BindBean Article article);

}
