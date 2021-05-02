package br.com.aprendizado.pokemon.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.aprendizado.pokemon.modelo.Pokemon;
import br.com.aprendizado.pokemon.modelo.Tipo;
import br.com.aprendizado.pokemon.repository.TipoRepository;

public class PokemonForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	
	@NotNull @NotEmpty @Length(min = 5)
	private String descricao;
	
	@NotNull @NotEmpty @Length(min = 3)
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
