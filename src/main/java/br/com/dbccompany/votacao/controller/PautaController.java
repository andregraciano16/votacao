package br.com.dbccompany.votacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dbccompany.votacao.entity.Pauta;
import br.com.dbccompany.votacao.request.PautaRequest;
import br.com.dbccompany.votacao.service.PautaService;

@RestController
@RequestMapping("/pauta")
public class PautaController {

	@Autowired
	private PautaService pautaService;
	
	@PostMapping
	public ResponseEntity<Pauta> cadastrar(@RequestBody @Valid PautaRequest pautaRequest) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(pautaService.cadastrar(pautaRequest));
	}
	
}
