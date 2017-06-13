package com.pursuit.noteblog.expection;

/**
 * http工具异常类
 */
public class NoteBlogHttpUtilsException extends RuntimeException {

	private static final long serialVersionUID = 1087328658524130263L;

	public NoteBlogHttpUtilsException(String message) {
		super(message);
	}

	public NoteBlogHttpUtilsException(Throwable e) {
		super(e);
	}

	public NoteBlogHttpUtilsException(String message, Throwable cause) {
		super(message, cause);
	}
}