package br.com.aprendizado.pokemon.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.aprendizado.pokemon.modelo.Pokemon;
import br.com.aprendizado.pokemon.repository.PokemonRepository;

public class AtualizacaoPokemonForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String nome;

	@NotNull @NotEmpty @Length(min = 5)
	private String descricao;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pokemon atualizar(Long id, PokemonRepository pokemonRepository) {
		
		Pokemon poke = pokemonRepository.getOne(id);
		
		poke.setNome(this.nome);
		poke.setDescricao(this.descricao);
		
		return poke;
	}

}
