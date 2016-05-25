package com.crossover.test.model;

import java.util.List;

/**
 * Form  when Create Article Controller is invoked
 * @author vinicius
 *
 */
public class ArticleForm {
	
	private String id;

	private String title;

	private String category;

	private String image;

	private String author;
	
	private String content;

	private List<String> tags;

	public String getId() {
		return id;
	}

	public ArticleForm setId(String id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public ArticleForm setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getCategory() {
		return category;
	}

	public ArticleForm setCategory(String category) {
		this.category = category;
		return this;
	}

	public String getImage() {
		return image;
	}

	public ArticleForm setImage(String image) {
		this.image = image;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public ArticleForm setAuthor(String author) {
		this.author = author;
		return this;
	}

	public List<String> getTags() {
		return tags;
	}

	public ArticleForm setTags(List<String> tags) {
		this.tags = tags;
		return this;
	}

	public String getContent() {
		return content;
	}

	public ArticleForm setContent(String content) {
		this.content = content;
		return this;
	}

}
