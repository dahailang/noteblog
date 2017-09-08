package com.pursuit.noteblog.expection;


public class NBException extends RuntimeException  {
	private static final long serialVersionUID = 1L;
	public NBException() {
		super();
	}

	public NBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NBException(String message, Throwable cause) {
		super(message, cause);
	}

	public NBException(String message) {
		super(message);
	}

	public NBException(Throwable cause) {
		super(cause);
	}


}
