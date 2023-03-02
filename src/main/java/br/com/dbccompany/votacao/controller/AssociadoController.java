package br.com.dbccompany.votacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dbccompany.votacao.entity.Associado;
import br.com.dbccompany.votacao.request.AssociadoRequest;
import br.com.dbccompany.votacao.service.AssociadoService;

@RestController
@RequestMapping("/associado")
public class AssociadoController {

	@Autowired
	private AssociadoService associadoService;
	
	@PostMapping
	public ResponseEntity<Associado> cadastrar(@RequestBody @Valid AssociadoRequest associadoReqeust) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(associadoService.cadastrar(associadoReqeust));
	}
	
}
