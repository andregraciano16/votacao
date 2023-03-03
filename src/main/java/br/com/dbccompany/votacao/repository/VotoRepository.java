package br.com.dbccompany.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dbccompany.votacao.entity.Voto;

public interface VotoRepository  extends JpaRepository<Voto, Integer>{

}
