package com.book.record.post;

import java.sql.Timestamp;

public class RecordVO {
	private String rseq;
	private String id;
	private String pseq;
	private int page;
	private String content;
	private Timestamp regdate;
	public String getRseq() {
		return rseq;
	}
	public void setRseq(String rseq) {
		this.rseq = rseq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPseq() {
		return pseq;
	}
	public void setPseq(String pseq) {
		this.pseq = pseq;
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
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "RecordVO [rseq=" + rseq + ", id=" + id + ", pseq=" + pseq + ", page=" + page + ", content=" + content
				+ ", regdate=" + regdate + "]";
	}
	
	
}
