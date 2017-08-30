package com.pursuit.noteblog.expection;


public class NoteBlogException extends RuntimeException  {
	private static final long serialVersionUID = 1L;
	public NoteBlogException() {
		super();
	}

	public NoteBlogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoteBlogException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoteBlogException(String message) {
		super(message);
	}

	public NoteBlogException(Throwable cause) {
		super(cause);
	}


}
