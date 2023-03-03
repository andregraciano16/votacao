package br.com.dbccompany.votacao.enums;

public enum ResultadoVotacaoEnum {

	APROVADO, REJEITADO, EMPATADO;
	
	public static ResultadoVotacaoEnum getStatus(TipoVotoEnum tipo) {
		return TipoVotoEnum.SIM.equals(tipo) ? ResultadoVotacaoEnum.APROVADO : 
			   TipoVotoEnum.NAO.equals(tipo) ? ResultadoVotacaoEnum.REJEITADO : 
			   ResultadoVotacaoEnum.EMPATADO;
	}
	
}
