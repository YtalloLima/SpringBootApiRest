package br.com.aprendizado.pokemon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendizado.pokemon.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	//metodo que vai ser usado em AutenticacaoService
	Optional<Usuario> findByEmail(String email);
}


