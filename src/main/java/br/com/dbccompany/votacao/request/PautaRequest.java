package br.com.dbccompany.votacao.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
public class PautaRequest {

	@NotEmpty(message = "{mensagem.validacao.nome.obrigatorio}")
	@Size(max = 100, message = "{mensagem.validacao.nome.tamanho}")
	private String nome;
	
}
