package br.com.dbccompany.votacao.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;
	
	public EntityNotFoundException(String mensagem) {
		super(mensagem);
		httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
	}
}
