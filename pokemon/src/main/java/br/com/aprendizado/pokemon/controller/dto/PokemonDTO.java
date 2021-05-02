package br.com.aprendizado.pokemon.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import br.com.aprendizado.pokemon.modelo.Pokemon;

public class PokemonDTO {
	
	private Long id;

	private String nome;
	
	private String descricao;

	private LocalDateTime dataCriacao;
	
	
	public PokemonDTO() {
		
	}

	public PokemonDTO(Pokemon poke) {
		this.id = poke.getId();
		this.nome = poke.getNome();
		this.descricao = poke.getDescricao();
		this.dataCriacao = poke.getDataCriacao();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public static List<PokemonDTO> converterPokemon(List<Pokemon> pokemons) {

		return pokemons.stream().map(PokemonDTO::new).collect(Collectors.toList());
	}

}
