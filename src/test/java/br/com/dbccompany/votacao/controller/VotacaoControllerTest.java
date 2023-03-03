package br.com.dbccompany.votacao.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import br.com.dbccompany.votacao.entity.Pauta;
import br.com.dbccompany.votacao.entity.Votacao;
import br.com.dbccompany.votacao.request.VotacaoRequest;
import br.com.dbccompany.votacao.service.VotacaoService;

@AutoConfigureJsonTesters
@WebMvcTest(VotacaoController.class)
public class VotacaoControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private JacksonTester<VotacaoRequest> jsonVotacaoRequest;
	@Autowired
	private JacksonTester<Votacao> jsonVotacao;
	
	@MockBean
	private VotacaoService votacaoService;

	private static VotacaoRequest votacaoRequest;
	private static Votacao votacao;
	private static Pauta pauta;
	
	@BeforeAll
	public static void carregarDados() {
		votacaoRequest = VotacaoRequest.builder().idPauta(1).tempoEmMinutos(10).build();
		pauta = Pauta.builder().id(1).nome("Cadastro Unico").build();
		votacao = Votacao.builder().id(1).tempoMinutos(10).pauta(pauta).build();
	}
	
	@Test
	public void deveCadastrarUmaPauta() throws Exception {
		given(votacaoService.cadastrar(votacaoRequest)).willReturn(votacao);

		MockHttpServletResponse response = mvc.perform(post("/votacao").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(jsonVotacaoRequest.write(votacaoRequest).getJson()))
				.andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonVotacao.write(votacao).getJson());

	}
	
	@Test
	public void deveRetornarErro400() throws Exception {
		VotacaoRequest votacaoRequestErro =  VotacaoRequest.builder().idPauta(0).tempoEmMinutos(1).build();
		
		MockHttpServletResponse response = mvc.perform(post("/votacao").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(jsonVotacaoRequest.write(votacaoRequestErro).getJson()))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
		
	}
}
