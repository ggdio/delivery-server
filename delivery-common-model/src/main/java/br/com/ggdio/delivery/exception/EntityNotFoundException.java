package br.com.ggdio.delivery.exception;

public class EntityNotFoundException extends LocalizedRuntimeException {

	public EntityNotFoundException() {
		super("error.entity.notfound");
	}
	
	public EntityNotFoundException(String message, Object...args) {
		super(message, args);
	}
	
}