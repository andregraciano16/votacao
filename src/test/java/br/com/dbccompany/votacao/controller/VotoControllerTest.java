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

import br.com.dbccompany.votacao.entity.Associado;
import br.com.dbccompany.votacao.entity.Pauta;
import br.com.dbccompany.votacao.entity.Votacao;
import br.com.dbccompany.votacao.entity.Voto;
import br.com.dbccompany.votacao.enums.TipoVotoEnum;
import br.com.dbccompany.votacao.request.VotoRequest;
import br.com.dbccompany.votacao.service.VotoService;

@AutoConfigureJsonTesters
@WebMvcTest(VotoController.class)
public class VotoControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private JacksonTester<VotoRequest> jsonVotoRequest;
	@Autowired
	private JacksonTester<Voto> jsonVoto;
	
	@MockBean
	private VotoService votoService;

	private static VotoRequest votoRequest;
	private static Voto voto;
	private static Associado associado;
	private static Votacao votacao;
	private static Pauta pauta;

	@BeforeAll
	public static void carregarDados() {
		votoRequest = VotoRequest.builder().idAssociado(1).idVotacao(1).tipoVoto(TipoVotoEnum.SIM).build();
		associado = Associado.builder().id(1).nome("Maria teste").build();
		pauta = Pauta.builder().id(1).nome("Cadastro Unico").build();
		votacao = Votacao.builder().pauta(pauta).tempoMinutos(10).build();
		voto = Voto.builder().associado(associado).votacao(votacao).tipoVoto(TipoVotoEnum.SIM).build();
	}
	
	@Test
	public void deveCadastrarUmaPauta() throws Exception {
		given(votoService.votar(votoRequest)).willReturn(voto);

		MockHttpServletResponse response = mvc.perform(post("/voto").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(jsonVotoRequest.write(votoRequest).getJson()))
				.andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonVoto.write(voto).getJson());

	}
	
	@Test
	public void deveRetornarErro400() throws Exception {
		VotoRequest votoRequestErro =  VotoRequest.builder().idAssociado(1).idVotacao(1).tipoVoto(null).build();
		
		MockHttpServletResponse response = mvc.perform(post("/voto").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(jsonVotoRequest.write(votoRequestErro).getJson()))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
		
	}
	
}
