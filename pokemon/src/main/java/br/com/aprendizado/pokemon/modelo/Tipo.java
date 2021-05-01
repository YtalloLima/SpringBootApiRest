package br.com.aprendizado.pokemon.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tipo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@ManyToOne
	private Usuario autor;
	
	private LocalDateTime dataCriacao = LocalDateTime.now();

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Usuario getAutor() {
		return autor;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	
}
