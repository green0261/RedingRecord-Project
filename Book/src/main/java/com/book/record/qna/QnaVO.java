package com.book.record.qna;

import java.sql.Timestamp;

public class QnaVO {
	private int qseq;
	private String id;
	private String qna_title;
	private String qna_content;
	private String reply;
	private String rep;
	private Timestamp indate;
	
	
	public int getQseq() {
		return qseq;
	}


	public void setQseq(int qseq) {
		this.qseq = qseq;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getQna_title() {
		return qna_title;
	}


	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}


	public String getQna_content() {
		return qna_content;
	}


	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}


	public String getReply() {
		return reply;
	}


	public void setReply(String reply) {
		this.reply = reply;
	}


	public String getRep() {
		return rep;
	}


	public void setRep(String rep) {
		this.rep = rep;
	}


	public Timestamp getIndate() {
		return indate;
	}


	public void setIndate(Timestamp indate) {
		this.indate = indate;
	}


	@Override
	public String toString() {
		return "QnaVO [qseq=" + qseq + ", id=" + id + ", qna_title=" + qna_title + ", qna_content=" + qna_content
				+ ", reply=" + reply + ", rep=" + rep + ", indate=" + indate + "]";
	}
	
	
}
