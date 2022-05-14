package com.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;
import com.generation.blogpessoal.service.UsuarioService;
public class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private UsuarioService usuarioService;

    @Autowired
	private UsuarioRepository usuarioRepository;
    
    @BeforeAll
	void start(){

		usuarioRepository.deleteAll();
		@Test
		@Order(1)
		@DisplayName("Cadastrar Um Usuário")
		public void deveCriarUmUsuario() {

			HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L, 
				"Paulo Antunes", "paulo_antunes@email.com.br", "13465278", "https://i.imgur.com/JR7kUFU.jpg"));

			ResponseEntity<Usuario> resposta = testRestTemplate
				.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);

			assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
			assertEquals(requisicao.getBody().getNome(), resposta.getBody().getNome());
			assertEquals(requisicao.getBody().getUsuario(), resposta.getBody().getUsuario());
		}
}