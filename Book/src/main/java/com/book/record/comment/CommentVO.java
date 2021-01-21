package com.book.record.comment;

import java.sql.Timestamp;

public class CommentVO {
	private String cseq;
	private String pseq;
	private String id;
	private String content;
	private String nick_name;
	private Timestamp regdate;
	
	public String getCseq() {
		return cseq;
	}
	public void setCseq(String cseq) {
		this.cseq = cseq;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "CommentVO [cseq=" + cseq + ", pseq=" + pseq + ", id=" + id + ", content=" + content + ", nick_name=" + nick_name + ", regdate=" + regdate +"]";
	}
	
	
}
