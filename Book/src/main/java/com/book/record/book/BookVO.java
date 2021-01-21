package com.book.record.book;

public class BookVO {
	private String isbn;
	private String title;
	private String author;
	private String cover;
	private String publisher;
	private String pubDate;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String gettitle() {
		return title;
	}
	public void settitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	@Override
	public String toString() {
		return "BookVO [isbn=" + isbn + ", title=" + title + ", author=" + author + ", cover=" + cover
				+ ", publisher=" + publisher + ", pubDate=" + pubDate + "]";
	}
	
	
	
}
