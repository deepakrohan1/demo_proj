package com.example.demo.deepakrohan.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private Long id;
	private String headerPost;
	private String bodyPost;
	
	public Post() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHeaderPost() {
		return headerPost;
	}
	public void setHeaderPost(String headerPost) {
		this.headerPost = headerPost;
	}
	public String getBodyPost() {
		return bodyPost;
	}
	public void setBodyPost(String bodyPost) {
		this.bodyPost = bodyPost;
	}
	
	
	
}
