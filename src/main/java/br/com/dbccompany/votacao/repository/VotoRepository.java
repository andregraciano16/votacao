package br.com.dbccompany.votacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dbccompany.votacao.entity.Voto;

public interface VotoRepository  extends JpaRepository<Voto, Integer>{

	public Optional<Voto> findByAssociadoIdAndVotacaoId(Integer idAssociado, Integer idVotacao);
	
}
