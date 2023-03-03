package br.com.dbccompany.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import br.com.dbccompany.votacao.entity.Associado;
import br.com.dbccompany.votacao.entity.Votacao;
import br.com.dbccompany.votacao.entity.Voto;
import br.com.dbccompany.votacao.enums.StatusVotacaoEnum;
import br.com.dbccompany.votacao.exception.EntityNotFoundException;
import br.com.dbccompany.votacao.exception.VotacaoEncerradaException;
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
	
	@Autowired
    private MessageSource messageSource;
	
	public Voto votar(VotoRequest votoRequest) throws EntityNotFoundException {
		Associado associado = associadoService.findById(votoRequest.getIdAssociado());
		Votacao votacao = votacaoService.findById(votoRequest.getIdVotacao());
		Voto voto = Voto.builder().associado(associado).votacao(votacao).tipoVoto(votoRequest.getTipoVoto()).build();
		this.validarSeAssociadoJaVotou(votoRequest);
		this.validarSeVotacaoEncerrou(votacao);
		return votoRepository.saveAndFlush(voto);
	}
	
	private void validarSeVotacaoEncerrou (Votacao votacao) {
		 StatusVotacaoEnum statusVotacaoEnum =  votacaoService.verificarStatusVotacao(votacao);
		 if (StatusVotacaoEnum.FINALIZADO.equals(statusVotacaoEnum)) {
			 throw new VotacaoEncerradaException(messageSource.getMessage("mensagem.votacao.encerrada", null, null));
		 }
	}
	
	private void validarSeAssociadoJaVotou(VotoRequest votoRequest) {
		votoRepository.findByAssociadoIdAndVotacaoId(votoRequest.getIdAssociado(), votoRequest.getIdVotacao()).ifPresent(voto -> {
			throw new VotoJaRealizadoException(messageSource.getMessage("mensagem.voto.unico", null, null));
		});
	}
	
}
