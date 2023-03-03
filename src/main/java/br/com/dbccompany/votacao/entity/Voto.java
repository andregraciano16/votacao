package br.com.dbccompany.votacao.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.dbccompany.votacao.enums.TipoVotoEnum;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Voto {

	@Id
	@GeneratedValue(generator = "id", strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Associado associado;
	
	@ManyToOne
	private Votacao votacao;

    @Enumerated(EnumType.ORDINAL)
	private TipoVotoEnum tipoVoto;

}
