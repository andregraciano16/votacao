package br.com.dbccompany.votacao.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class VotoJaRealizadoException  extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;
	
	public VotoJaRealizadoException(String mensagem) {
		super(mensagem);
		httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
	}
}
