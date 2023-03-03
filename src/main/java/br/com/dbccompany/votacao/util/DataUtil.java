package br.com.dbccompany.votacao.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class DataUtil {
	
	public static long calcularDiferencaMinutosDataAtual(LocalDateTime data) {
		 LocalDateTime dataAtual = LocalDateTime.now();
		 Duration duracao = Duration.between(data, dataAtual);
	     return duracao.toMinutes();
	}

}
