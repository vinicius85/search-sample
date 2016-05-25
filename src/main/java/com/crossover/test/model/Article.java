package com.crossover.test.model;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
/**
 * Article represents Solr Document from News SolrCollection
 * @author vinicius
 *
 */
public class Article {

	@Field
	private String id;

	@Field
	private String title;

	@Field
	private String category;

	@Field
	private String image;

	@Field
	private String author;
	
	@Field
	private String content;

	@Field
	private List<String> tags;

	public String getId() {
		return id;
	}

	public Article setId(String id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Article setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getCategory() {
		return category;
	}

	public Article setCategory(String category) {
		this.category = category;
		return this;
	}

	public String getImage() {
		return image;
	}

	public Article setImage(String image) {
		this.image = image;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public Article setAuthor(String author) {
		this.author = author;
		return this;
	}

	public List<String> getTags() {
		return tags;
	}

	public Article setTags(List<String> tags) {
		this.tags = tags;
		return this;
	}

	/**
	 * Just for debugging purposes
	 * 
	 * TODO: Refactor
	 */
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", category=" + category + ", image=" + image + ", author="
				+ author + ", tags=" + tags + "]";
	}

	public String getContent() {
		return content;
	}

	public Article setContent(String content) {
		this.content = content;
		return this;
	}
	
	

}
