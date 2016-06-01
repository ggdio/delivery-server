package br.com.ggdio.delivery.exception;

public class LocalizedException extends Exception {
	
	private Object[] args;
	
	public LocalizedException() {
		// TODO Auto-generated constructor stub
	}
	
	public LocalizedException(String message, Object...args) {
		super(message);
		this.args = args;
	}
	
	public LocalizedException(Throwable cause, String message, Object...args) {
		super(message, cause);
		this.args = args;
	}
	
	public Object[] getArgs() {
		return args;
	}

}
