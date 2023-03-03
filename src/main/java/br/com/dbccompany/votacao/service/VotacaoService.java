package br.com.dbccompany.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dbccompany.votacao.entity.Pauta;
import br.com.dbccompany.votacao.entity.Votacao;
import br.com.dbccompany.votacao.exception.PautaNotFoundException;
import br.com.dbccompany.votacao.repository.VotacaoRepository;
import br.com.dbccompany.votacao.request.VotacaoRequest;

@Service
public class VotacaoService {

	@Autowired
	private VotacaoRepository votacaoRepository;
	
	@Autowired
	private PautaService pautaService;
	
	public Votacao cadastrar(VotacaoRequest votacaoRequest) throws PautaNotFoundException {
		try {
			Pauta pauta = pautaService.findById(votacaoRequest.getIdPauta());
			Votacao votacao = Votacao.builder()
					.pauta(pauta)
					.tempoMinutos(votacaoRequest.getTempoEmMinutos()).build();
			return votacaoRepository.save(votacao);
		} catch (PautaNotFoundException e) {
			throw e;
		}
	}
	
}
