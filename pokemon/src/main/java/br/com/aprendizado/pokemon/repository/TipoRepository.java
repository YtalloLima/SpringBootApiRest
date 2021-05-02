package br.com.aprendizado.pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendizado.pokemon.modelo.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, Long> {

	Tipo findByNome(String nomeTipo);

}
