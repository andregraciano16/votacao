package br.com.dbccompany.votacao.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.dbccompany.votacao.enums.TipoVotoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class VotoRequest {

	@NotNull(message = "{mensagem.validacao.id.votacao.obrigatorio}")
	@Positive(message = "{mensagem.validacao.id.votacao.positivo}")
	private Integer idVotacao;
	
	@NotNull(message = "{mensagem.validacao.id.associado.obrigatorio}")
	@Positive(message = "{mensagem.validacao.id.associa.positivo}")
	private Integer idAssociado;
	
	@NotNull(message = "{mensagem.validacao.tipo.voto.obrigatorio}")
	private TipoVotoEnum tipoVoto;
}
