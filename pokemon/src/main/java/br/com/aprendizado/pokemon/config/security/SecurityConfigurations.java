package br.com.aprendizado.pokemon.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.aprendizado.pokemon.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	AutenticacaoService autenticacaoService;

	@Autowired
	private TokenService tokenService;
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	//Para a classe instanciada em AutenticacaoController, aí adicionamos a anotacao @Bean
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//Configurações de autenticação
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			//Digo em qual classe teremos a logica de autenticação
			auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());

		}

		//Configurações de Autorização - quem pode acessar url, por exemplo
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			//permito que qualquer pessoa aceite o /topicos em todos os métodos GET, POST, etc.
			//  http.authorizeRequests().antMatchers("/pokemon").permitAll();


			//Aqui liberará a consulta geral (GET) e a consulta com parâmetros (GET) tbm
			http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/pokemon").permitAll()
			.antMatchers(HttpMethod.GET, "/pokemon/*").permitAll()
			.antMatchers(HttpMethod.POST, "/auth").permitAll()
			.anyRequest().authenticated()
			.and().csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);;
		}

		//Configuração de recursos estáticos - Css, Javascript, imagens
		@Override
		public void configure(WebSecurity web) throws Exception {

		}
}