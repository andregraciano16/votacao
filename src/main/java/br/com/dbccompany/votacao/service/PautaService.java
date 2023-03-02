package br.com.dbccompany.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dbccompany.votacao.entity.Pauta;
import br.com.dbccompany.votacao.repository.PautaRepository;
import br.com.dbccompany.votacao.request.PautaRequest;

@Service
public class PautaService {

	@Autowired
	private PautaRepository pautaRepository;
	
	public Pauta cadastrar(PautaRequest pautaRequest) {
		Pauta pauta = Pauta.builder().nome(pautaRequest.getNome()).build();
		return pautaRepository.save(pauta);
	}
	
}
