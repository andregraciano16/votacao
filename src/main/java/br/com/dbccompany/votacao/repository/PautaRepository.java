package br.com.dbccompany.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dbccompany.votacao.entity.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Integer> {

}
