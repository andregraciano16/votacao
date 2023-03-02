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
import br.com.dbccompany.votacao.request.AssociadoRequest;
import br.com.dbccompany.votacao.service.AssociadoService;

@AutoConfigureJsonTesters
@WebMvcTest(AssociadoController.class)
public class AssociadoControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private JacksonTester<AssociadoRequest> jsonAssociadoRequest;
	@Autowired
	private JacksonTester<Associado> jsonAssociado;
	
	@MockBean
	private AssociadoService associadoService;

	private static AssociadoRequest associadoRequest;
	private static Associado associado;
	
	@BeforeAll
	public static void carregarDados() {
		associadoRequest = AssociadoRequest.builder().nome("Maria da Silva").build();
		associado = Associado.builder().id(1).nome("Maria da Silva").build();
	}
	
	@Test
	public void deveCadastrarUmaPauta() throws Exception {
		given(associadoService.cadastrar(associadoRequest)).willReturn(associado);

		MockHttpServletResponse response = mvc.perform(post("/associado").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(jsonAssociadoRequest.write(associadoRequest).getJson()))
				.andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonAssociado.write(associado).getJson());

	}
	
	@Test
	public void deveRetornarErro400() throws Exception {
		AssociadoRequest associadoRequest = AssociadoRequest.builder().nome("").build();
		
		MockHttpServletResponse response = mvc.perform(post("/associado").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(jsonAssociadoRequest.write(associadoRequest).getJson()))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
		
	}
	
}
