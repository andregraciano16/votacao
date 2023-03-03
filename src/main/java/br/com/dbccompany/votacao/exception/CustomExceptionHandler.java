package br.com.dbccompany.votacao.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.dbccompany.votacao.dto.ErroDetail;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
			WebRequest request) {
		List<ErroDetail> erros = new ArrayList<>();
		exception.getFieldErrors() .stream().forEach(e -> {
			ErroDetail detail = ErroDetail.builder().mensagem(e.getDefaultMessage()).campo(e.getField()).build();
			erros.add(detail);
		});
		return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(VotoJaRealizadoException.class)
	public ResponseEntity<Object> handleVotoJaRealizadoException(VotoJaRealizadoException exception,
			WebRequest request) {

		return new ResponseEntity<>(exception.getMessage(), exception.getHttpStatus());
	}
	
	@ExceptionHandler(VotacaoEncerradaException.class)
	public ResponseEntity<Object> handleVotoJaRealizadoException(VotacaoEncerradaException exception,
			WebRequest request) {

		return new ResponseEntity<>(exception.getMessage(), exception.getHttpStatus());
	}

}
