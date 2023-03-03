package br.com.dbccompany.votacao.dto;

import br.com.dbccompany.votacao.enums.ResultadoVotacaoEnum;
import br.com.dbccompany.votacao.enums.StatusVotacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoVotacaoDto {

	private String nomePauta;
	private StatusVotacaoEnum statusVotacao;
	private ResultadoVotacaoEnum resultadoVotacao;
	
}
