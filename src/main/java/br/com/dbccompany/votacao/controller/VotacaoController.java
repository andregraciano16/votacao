package br.com.dbccompany.votacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dbccompany.votacao.dto.ResultadoVotacaoDto;
import br.com.dbccompany.votacao.entity.Votacao;
import br.com.dbccompany.votacao.exception.EntityNotFoundException;
import br.com.dbccompany.votacao.request.VotacaoRequest;
import br.com.dbccompany.votacao.service.VotacaoService;

@RestController
@RequestMapping("/votacao")
public class VotacaoController {
	
	@Autowired
	private VotacaoService votacaoService;
	
	@PostMapping
	public ResponseEntity<Votacao> cadastrar(@RequestBody @Valid VotacaoRequest votacaoRequest) throws EntityNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(votacaoService.cadastrar(votacaoRequest));
	}
	
	@GetMapping("/{id}/resultado")
	public ResponseEntity<ResultadoVotacaoDto> consultarResultado(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(votacaoService.resultadoVotacao(id));
	}
}
