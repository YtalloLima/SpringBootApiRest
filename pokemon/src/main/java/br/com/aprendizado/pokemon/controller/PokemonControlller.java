package br.com.aprendizado.pokemon.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aprendizado.pokemon.modelo.Pokemon;

@RestController
@RequestMapping("/pokemon")
public class PokemonControlller {
	
	@GetMapping
	public List listaPokemons() {
		
		ArrayList<Pokemon> lista = new ArrayList<Pokemon>();
		Pokemon poke = new Pokemon(Long.valueOf(1), "Charmander");
		Pokemon poke1 = new Pokemon(Long.valueOf(1), "Charmeleon");
		Pokemon poke2 = new Pokemon(Long.valueOf(1), "Charizard");
		
		lista.add(poke);
		lista.add(poke1);
		lista.add(poke2);
		
		return lista;
	}

}
