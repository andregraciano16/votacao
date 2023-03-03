package br.com.dbccompany.votacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dbccompany.votacao.entity.Voto;
import br.com.dbccompany.votacao.exception.EntityNotFoundException;
import br.com.dbccompany.votacao.request.VotoRequest;
import br.com.dbccompany.votacao.service.VotoService;

@RestController
@RequestMapping("/voto")
public class VotoController {

	@Autowired
	private VotoService votoService;
	
	@PostMapping
	public ResponseEntity<Voto> cadastrar(@RequestBody @Valid VotoRequest votoRequest) throws EntityNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(votoService.votar(votoRequest));
	}
	
}
