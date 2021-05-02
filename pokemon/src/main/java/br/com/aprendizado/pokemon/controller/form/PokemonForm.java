package br.com.aprendizado.pokemon.controller.form;

import br.com.aprendizado.pokemon.modelo.Pokemon;
import br.com.aprendizado.pokemon.modelo.Tipo;
import br.com.aprendizado.pokemon.repository.TipoRepository;

public class PokemonForm {
	
	private String nome;
	
	private String descricao;
	
	private String nomeTipo;

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

	public String getNomeTipo() {
		return nomeTipo;
	}

	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}
	
	public Pokemon converter(TipoRepository tipoRepository) {
		
		Tipo tipo = tipoRepository.findByNome(nomeTipo);
		
		return new Pokemon(nome, descricao, tipo);
		
	}
}	
