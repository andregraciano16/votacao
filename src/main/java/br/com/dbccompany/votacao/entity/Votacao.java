package br.com.dbccompany.votacao.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Votacao {

	@Id
	@GeneratedValue(generator = "id", strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	private Pauta pauta;
	
	@Column(name = "tempo_minutos", nullable = false)
	private Integer tempoMinutos;

	@OneToMany
	private List<Voto> votos;
	
}
