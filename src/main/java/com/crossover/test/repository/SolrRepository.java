package com.crossover.test.repository;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.crossover.test.model.Article;

/**
 * Classes that uses SolrJ to communicate with Solr
 * 
 * @author vinicius
 *
 */
@Component
public class SolrRepository {

	private SolrClient client;

	private Logger logger;

	public SolrRepository() {
		client = new HttpSolrClient("http://localhost:8983/solr/news");
		logger = LoggerFactory.getLogger(SolrRepository.class);
	}

	/**
	 * TODO: Improvement Bring highlight maybe some facet
	 * TODO: Improve log exceptions
	 * @param query
	 * @return
	 */
	public List<Article> get(String query) {
		List<Article> articles = null;
		try {
			SolrQuery solrQuery = new SolrQuery(query);
			
			//searchable fields. Just random boost values to show that matches in title are more relevant
			solrQuery.set("qf", "title^10 content^5 category^5");
			solrQuery.set("defType", "edismax");
			QueryResponse response = client.query(solrQuery);
			
			articles = response.getBeans(Article.class);
		} catch (SolrServerException e) {
			logger.error("Error getting article from Solr", e);
		} catch (IOException e) {
			logger.error("Error getting article from Solr", e);
		}
		return articles;
	}

	public Article getArticleById(String id) {
		Article article = null;
		try {
			SolrQuery solrQuery = new SolrQuery("id:" + id);
			QueryResponse response = client.query(solrQuery);
			List<Article> articles = response.getBeans(Article.class);
			if (CollectionUtils.isNotEmpty(articles)) {
				article = articles.get(0);
			}
		} catch (SolrServerException e) {
			logger.error("Error getting article from Solr", e);
		} catch (IOException e) {
			logger.error("Error getting article from Solr", e);
		}
		return article;
	}

}
