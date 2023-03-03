package br.com.dbccompany.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dbccompany.votacao.entity.Associado;
import br.com.dbccompany.votacao.entity.Votacao;
import br.com.dbccompany.votacao.entity.Voto;
import br.com.dbccompany.votacao.exception.EntityNotFoundException;
import br.com.dbccompany.votacao.exception.VotoJaRealizadoException;
import br.com.dbccompany.votacao.repository.VotoRepository;
import br.com.dbccompany.votacao.request.VotoRequest;

@Service
public class VotoService {

	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
	private AssociadoService associadoService;
	
	@Autowired
	private VotacaoService votacaoService;
	
	public Voto votar(VotoRequest votoRequest) throws EntityNotFoundException {
		Associado associado = associadoService.findById(votoRequest.getIdAssociado());
		Votacao votacao = votacaoService.findById(votoRequest.getIdVotacao());
		this.validarSeAssociadoJaVotou(votoRequest);
		Voto voto = Voto.builder().associado(associado).votacao(votacao).tipoVoto(votoRequest.getTipoVoto()).build();
		return votoRepository.saveAndFlush(voto);
	}
	
	private void validarSeAssociadoJaVotou(VotoRequest votoRequest) {
		votoRepository.findByAssociadoIdAndVotacaoId(votoRequest.getIdAssociado(), votoRequest.getIdVotacao()).ifPresent(voto -> {
			throw new VotoJaRealizadoException("Só é possível votar apenas uma vez");
		});
	}
	
}
