package br.com.dbccompany.votacao.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import br.com.dbccompany.votacao.dto.ResultadoVotacaoDto;
import br.com.dbccompany.votacao.entity.Pauta;
import br.com.dbccompany.votacao.entity.Votacao;
import br.com.dbccompany.votacao.entity.Voto;
import br.com.dbccompany.votacao.enums.ResultadoVotacaoEnum;
import br.com.dbccompany.votacao.enums.StatusVotacaoEnum;
import br.com.dbccompany.votacao.enums.TipoVotoEnum;
import br.com.dbccompany.votacao.exception.EntityNotFoundException;
import br.com.dbccompany.votacao.repository.VotacaoRepository;
import br.com.dbccompany.votacao.request.VotacaoRequest;
import br.com.dbccompany.votacao.util.DataUtil;

@Service
public class VotacaoService {

	@Autowired
	private VotacaoRepository votacaoRepository;
	
	@Autowired
	private PautaService pautaService;
	
	@Autowired
    private MessageSource messageSource;
	
	public Votacao cadastrar(VotacaoRequest votacaoRequest) throws EntityNotFoundException {
		try {
			Pauta pauta = pautaService.findById(votacaoRequest.getIdPauta());
			Votacao votacao = Votacao.builder()
					.pauta(pauta)
					.tempoMinutos(votacaoRequest.getTempoEmMinutos()).build();
			return votacaoRepository.save(votacao);
		} catch (EntityNotFoundException e) {
			throw e;
		}
	}
	
	public StatusVotacaoEnum verificarStatusVotacao (Votacao votacao) {
		 long minutos = DataUtil.calcularDiferencaMinutosDataAtual(votacao.getDataHoraCadastro());
		 if (Long.valueOf(votacao.getTempoMinutos()) < minutos) {
			 return StatusVotacaoEnum.FINALIZADO;
		 }
		 return StatusVotacaoEnum.EM_ANDAMENTO;
	}
	
	public Votacao findById(Integer id) throws EntityNotFoundException {
		return votacaoRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(messageSource.getMessage("mensagem.votacao.nao.encontrada", null, null))
		);		
	}
	
	public ResultadoVotacaoDto resultadoVotacao(Integer id) {
		ResultadoVotacaoDto resultado = new ResultadoVotacaoDto();
		Optional<Votacao> votacao = votacaoRepository.findById(id);
		votacao.ifPresent(votacaoBase -> {
			resultado.setNomePauta(votacaoBase.getPauta().getNome());
			resultado.setStatusVotacao(verificarStatusVotacao(votacaoBase));
			resultado.setResultadoVotacao(verificarResultadoVotacao(votacaoBase.getVotos()));
		});
		return resultado;
	}
	
	private ResultadoVotacaoEnum verificarResultadoVotacao(List<Voto> votos) {
		
		Map<TipoVotoEnum, Long> ocorrenciasPorTipo = votos.stream()
			    .collect(Collectors.groupingBy(o -> o.getTipoVoto(), Collectors.counting()));
				
		Long maisVotado = ocorrenciasPorTipo.entrySet().stream()
			    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			    .findFirst()
			    .map(Map.Entry::getValue)
			    .orElse(null);
		
		TipoVotoEnum tipoMaisVotado = ocorrenciasPorTipo.entrySet().stream()
			    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			    .findFirst()
			    .map(Map.Entry::getKey)
			    .orElse(null);
		
		long temEmpate = ocorrenciasPorTipo.entrySet().stream().filter(o -> o.getValue() == maisVotado).count();

		return  temEmpate == TipoVotoEnum.values().length ?
			ResultadoVotacaoEnum.getStatus(null) :
		    ResultadoVotacaoEnum.getStatus(tipoMaisVotado);
				
	}
	
}
