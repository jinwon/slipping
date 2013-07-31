package net.slipp.service.bbs;

public class PasswordMismatchException extends Exception {
	private static final long serialVersionUID = -124944399687435182L;

	@Override
	public String getMessage() {
		return "게시물 비밀번호가 틀립니다.";
	}
}
