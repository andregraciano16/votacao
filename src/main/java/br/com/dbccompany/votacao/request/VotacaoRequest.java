package br.com.dbccompany.votacao.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
public class VotacaoRequest {

	@NotNull(message = "{mensagem.validacao.id.pauta.obrigatorio}")
	@Positive(message = "{mensagem.validacao.id.pauta.positivo}")
	private Integer idPauta;
		
	private Integer tempoEmMinutos;
	
}
