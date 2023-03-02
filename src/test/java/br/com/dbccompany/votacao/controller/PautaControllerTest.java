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
import br.com.dbccompany.votacao.request.PautaRequest;
import br.com.dbccompany.votacao.service.PautaService;

@AutoConfigureJsonTesters
@WebMvcTest(PautaController.class)
public class PautaControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private JacksonTester<PautaRequest> jsonCartaoRequest;
	@Autowired
	private JacksonTester<Pauta> jsonPautaRequest;
	
	@MockBean
	private PautaService pautaService;

	private static PautaRequest pautaRequest;
	private static Pauta pauta;
	
	@BeforeAll
	public static void carregarDados() {
		pautaRequest = PautaRequest.builder().nome("Cadastro Unico").build();
		pauta = Pauta.builder().id(1).nome("Cadastro Unico").build();
	}
	
	@Test
	public void deveCadastrarUmaPauta() throws Exception {
		given(pautaService.cadastrar(pautaRequest)).willReturn(pauta);

		MockHttpServletResponse response = mvc.perform(post("/pauta").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(jsonCartaoRequest.write(pautaRequest).getJson()))
				.andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonPautaRequest.write(pauta).getJson());

	}
	
	@Test
	public void deveRetornarErro400() throws Exception {
		PautaRequest pautaRequestErro =  PautaRequest.builder().nome("").build();
		
		MockHttpServletResponse response = mvc.perform(post("/pauta").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(jsonCartaoRequest.write(pautaRequestErro).getJson()))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
		
	}
	
}
