package br.com.aprendizado.pokemon.modelo;

public class Pokemon {

	private Long id;
	
	private String nome;

	public Pokemon(Long id, String nome) {
	 this.id = id;
	 this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	
}
