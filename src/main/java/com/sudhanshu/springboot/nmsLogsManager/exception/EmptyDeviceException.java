package com.sudhanshu.springboot.nmsLogsManager.exception;

@SuppressWarnings("serial")
public class EmptyDeviceException extends NmsLogManagerExeption {

	public EmptyDeviceException() {
		super();
	}

	public EmptyDeviceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmptyDeviceException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyDeviceException(String message) {
		super(message);
	}

	public EmptyDeviceException(Throwable cause) {
		super(cause);
	}

}
