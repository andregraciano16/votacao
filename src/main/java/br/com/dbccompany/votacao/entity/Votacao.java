package br.com.dbccompany.votacao.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Votacao {

	@Id
	@GeneratedValue(generator = "id", strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	private Pauta pauta;

	@Column(name = "tempo_minutos", nullable = false)
	private Integer tempoMinutos;

	@OneToMany
	@JsonManagedReference
	@JoinColumn(name = "votacao_id",  referencedColumnName = "id")
	private List<Voto> votos;

	@Column(name = "data_hora_cadastro", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataHoraCadastro;
	
	@PrePersist
	void prePersist() {
		if (this.dataHoraCadastro == null) {
			this.dataHoraCadastro = LocalDateTime.now();
		}
		if (this.tempoMinutos == null) {
			this.tempoMinutos = 1;
		}
	}
	
	
}
