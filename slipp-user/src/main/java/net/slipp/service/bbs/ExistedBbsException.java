package net.slipp.service.bbs;

public class ExistedBbsException extends Exception {
	
	private static final long serialVersionUID = -2161492654834321369L;

	private String bbsIdx;
	
	public ExistedBbsException(String bbsIdx) {
		this.bbsIdx = bbsIdx;
	}
	
	public String getBbsIdx() {
		return bbsIdx;
	}
	
	@Override
	public String getMessage() {
		return String.format("%s는 이미 존재하는 아이디입니다.", this.bbsIdx);
	}
}
