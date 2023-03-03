package br.com.dbccompany.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dbccompany.votacao.entity.Votacao;

public interface VotacaoRepository extends JpaRepository<Votacao, Integer> {

}
