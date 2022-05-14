package com.generation.blogpessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.model.UsuarioLogin;
import com.generation.blogpessoal.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			return Optional.empty();

		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		return Optional.of(usuarioRepository.save(usuario));
	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		
		if (usuarioRepository.findById(usuario.getId()).isPresent()) {
				
				usuario.setSenha(criptografarSenha(usuario.getSenha()));
				
				return Optional.ofNullable(usuarioRepository.save(usuario));	
				// VAI SALVAR SOMENTE SE ELE EXISTIR
				//NULLABLE - PODE VIR ALGO NULO, PORTANTO, É PARA PREVENIR 
			}
			return Optional.empty();//SÓ SERÁ UTILIZADO CASO O USUARIO NÃO EXISTA//
		}

	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {
		// primeiro abrir o optional
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

		if (usuario.isPresent()) {

			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {

				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setFoto(usuario.get().getFoto());

				usuarioLogin.get()
						.setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));

				usuarioLogin.get().setSenha(usuario.get().getSenha());

				return usuarioLogin; // usuario preenchido! devolve usuario e login
			}

		}

		return Optional.empty();// SÓ SERÁ UTILIZADO CASO O USUARIO NÃO EXISTA//
	}

	private String gerarBasicToken(String usuario, String senha) {
		// modelo padrão token -->
		String token = usuario + ":" + senha;

		// formato que vai fazer a codificação // é o númeto de bytes que ele vai usar
		// pra fazer a codificação
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);
	}

	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaBanco);
		// compara as senhas pelo metodo matches
	}

	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);

	}
}
