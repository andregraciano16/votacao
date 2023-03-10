package br.com.dbccompany.votacao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Pauta {

	@Id
	@GeneratedValue(generator = "id", strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
}
