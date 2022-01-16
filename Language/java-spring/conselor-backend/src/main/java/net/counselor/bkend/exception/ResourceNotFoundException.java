package net.counselor.bkend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = -6995856550340210081L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
