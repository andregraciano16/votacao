package br.com.dbccompany.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dbccompany.votacao.entity.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Integer> {

}
