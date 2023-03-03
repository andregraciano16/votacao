package br.com.dbccompany.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dbccompany.votacao.entity.Associado;
import br.com.dbccompany.votacao.exception.EntityNotFoundException;
import br.com.dbccompany.votacao.repository.AssociadoRepository;
import br.com.dbccompany.votacao.request.AssociadoRequest;

@Service
public class AssociadoService {

	@Autowired
	private AssociadoRepository associadoRepository;
	
	public Associado cadastrar(AssociadoRequest associadoRequest) {
		Associado associado = Associado.builder().nome(associadoRequest.getNome()).build();
		return associadoRepository.save(associado);
	}
	
	public Associado findById(Integer id) throws EntityNotFoundException {
		return associadoRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Associado não encontrado")
		);		
	}
}
