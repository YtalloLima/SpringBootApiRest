package br.com.aprendizado.pokemon.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.aprendizado.pokemon.modelo.Usuario;
import br.com.aprendizado.pokemon.repository.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	
	@Autowired 
	private UsuarioRepository usuarioRepository;

	//Não consigo injetar minha classe TokenService com o Autowired. Então, vou fazer por meio do construtor
	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}
	
	//método que vai receber o toker da requisição e validar
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperaToken(request);
		boolean valido = tokenService.isTokenValido(token);

		System.out.println(token);
		if (valido)
			autenticarCliente(token);
		
		filterChain.doFilter(request, response);
	}

	private String recuperaToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7, token.length());
	}

	private void autenticarCliente(String token) {

		Long idUsuario = tokenService.getIdUsuario(token);
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
