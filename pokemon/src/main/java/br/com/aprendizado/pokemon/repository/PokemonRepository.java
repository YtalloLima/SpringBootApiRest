package br.com.aprendizado.pokemon.repository;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aprendizado.pokemon.modelo.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long>{
	
	//SpringBoot tem um padrão de consultas. Se colocarmos findByNomeCampo e esse campo exista no modelo, ele busca
	List<Pokemon> findByNome(String nome);
	
	List<Pokemon> findByTipoNome(String nome);

}
