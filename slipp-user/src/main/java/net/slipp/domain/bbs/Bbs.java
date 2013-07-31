package net.slipp.domain.bbs;

import net.slipp.domain.user.User;
import net.slipp.service.bbs.PasswordMismatchException;

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

	public boolean matchPassword(String checkPassword) {
		if (checkPassword == null) {
			return false;
		}
		
		return checkPassword.equals(bbsPassword);
	}
	
	public void update(Bbs updateBbs) throws PasswordMismatchException {
		// TODO Auto-generated method stub
		if (!matchPassword(updateBbs.bbsPassword)) {
			throw new PasswordMismatchException();
		}
		
		this.userId = updateBbs.userId;
		this.subject = updateBbs.subject;
		this.content = updateBbs.content;
		this.writeDate = updateBbs.writeDate;		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bbs other = (Bbs) obj;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		
		if (bbsPassword == null) {
			if (other.bbsPassword != null)
				return false;
		} else if (!bbsPassword.equals(other.bbsPassword))
			return false;
		
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}	
	
	
	@Override
	public String toString() {
		return "Bbs [bbsIdx=" + bbsIdx + ", userId=" + userId + ", bbsPassword=" + bbsPassword + ", subject=" + subject 
				+ ", content=" + content +", writeDate=" + writeDate + "]";
	}	

	
	
}
