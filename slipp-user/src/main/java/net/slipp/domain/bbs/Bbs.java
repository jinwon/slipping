package net.slipp.domain.bbs;

public class Bbs {
	
	private String bbsIdx;
	private String userId;
	private String bbsPassword;
	private String subject;
	private String content;
	private String writeDate;
	
	
	public Bbs() {
		
	}
	
	public Bbs(String bbsIdx, String userId, String bbsPassword, String subject, String content, String writeDate) {
		this.bbsIdx = bbsIdx;
		this.userId = userId;
		this.bbsPassword = bbsPassword;
		this.subject = subject;
		this.content = content;
		this.writeDate = writeDate;
	}
	
	public String getBbsIdx() {
		return bbsIdx;
	}

	public void setBbsIdx(String bbsIdx) {
		this.bbsIdx = bbsIdx;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getBbsPassword() {
		return bbsPassword;
	}
	public void setBbsPassword(String bbsPassword) {
		this.bbsPassword = bbsPassword;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
		
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}


	
	
}
