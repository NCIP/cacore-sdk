package test.gov.nih.nci.restgen.client;

import java.io.Serializable;

public class Book implements Serializable{
	private String id;
	private String title;
	private String author;
	private String ibn;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIbn() {
		return ibn;
	}
	public void setIbn(String ibn) {
		this.ibn = ibn;
	}
}
