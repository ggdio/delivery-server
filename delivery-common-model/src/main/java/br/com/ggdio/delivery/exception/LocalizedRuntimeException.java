package br.com.ggdio.delivery.exception;

public class LocalizedRuntimeException extends RuntimeException {
	
	private Object[] args;
	
	public LocalizedRuntimeException() {
		// TODO Auto-generated constructor stub
	}
	
	public LocalizedRuntimeException(String message, Object...args) {
		super(message);
		this.args = args;
	}
	
	public LocalizedRuntimeException(Throwable cause, String message, Object...args) {
		super(message, cause);
		this.args = args;
	}
	
	public Object[] getArgs() {
		return args;
	}

}
