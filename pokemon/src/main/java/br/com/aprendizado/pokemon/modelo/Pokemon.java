package br.com.aprendizado.pokemon.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pokemon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@ManyToOne
	private Usuario autor;

	@ManyToOne
	private Tipo tipo;

	private LocalDateTime dataCriacao = LocalDateTime.now();

	public Pokemon() {
		
	}
	
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

	public Usuario getAutor() {
		return autor;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
}
