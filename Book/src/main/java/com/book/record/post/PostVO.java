package com.book.record.post;

import java.sql.Timestamp;

public class PostVO {
	private String pseq;
	private String id;
	private String nick_name;
	private String image;
	private String post_title;
	private String p_content;
	private String isbn;
	private String readyn;
	private Timestamp regdate;
	private String days;
	private String title;
	private String author;
	private String cover;
	private String publisher;
	private String pubDate;
	private String rseq;
	private int page;
	private String content;
	private Timestamp r_regdate;
	
	
	public String getPseq() {
		return pseq;
	}


	public void setPseq(String pseq) {
		this.pseq = pseq;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNick_name() {
		return nick_name;
	}


	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getPost_title() {
		return post_title;
	}


	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}


	public String getP_content() {
		return p_content;
	}


	public void setP_content(String p_content) {
		this.p_content = p_content;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getReadyn() {
		return readyn;
	}


	public void setReadyn(String readyn) {
		this.readyn = readyn;
	}


	public Timestamp getRegdate() {
		return regdate;
	}


	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}


	public String getDays() {
		return days;
	}


	public void setDays(String days) {
		this.days = days;
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


	public String getCover() {
		return cover;
	}


	public void setCover(String cover) {
		this.cover = cover;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public String getPubDate() {
		return pubDate;
	}


	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}


	public String getRseq() {
		return rseq;
	}


	public void setRseq(String rseq) {
		this.rseq = rseq;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Timestamp getR_regdate() {
		return r_regdate;
	}


	public void setR_regdate(Timestamp r_regdate) {
		this.r_regdate = r_regdate;
	}


	@Override
	public String toString() {
		return "PostVO [pseq=" + pseq + ", id=" + id + ", nick_name=" + nick_name + ", image=" + image + ", post_title="
				+ post_title + ", p_content=" + p_content + ", isbn=" + isbn + ", readyn=" + readyn + ", regdate="
				+ regdate + ", days=" + days + ", title=" + title + ", author=" + author + ", cover=" + cover
				+ ", publisher=" + publisher + ", pubDate=" + pubDate + ", rseq=" + rseq + ", page=" + page
				+ ", content=" + content + ", r_regdate=" + r_regdate + "]";
	}
	
	
	
}
