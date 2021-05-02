package br.com.aprendizado.pokemon.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aprendizado.pokemon.controller.dto.PokemonDTO;
import br.com.aprendizado.pokemon.controller.form.PokemonForm;
import br.com.aprendizado.pokemon.modelo.Pokemon;
import br.com.aprendizado.pokemon.repository.PokemonRepository;
import br.com.aprendizado.pokemon.repository.TipoRepository;

@RestController
@RequestMapping("/pokemon")
public class PokemonControlller {
	
	@Autowired
	PokemonRepository pokemonRepository;
	
	@Autowired
	TipoRepository tipoRepository;
	
	@RequestMapping("/pokemons")
	public List<PokemonDTO> listaPokemonsByNome(String nome) {
		List<Pokemon> pokemons = new ArrayList<Pokemon>();

		 //no navegador http://localhost:8080/pokemon/pokemons?nome=Charmander 
		  pokemons = pokemonRepository.findByNome(nome);
		
		return PokemonDTO.converterPokemon(pokemons);
	}
	
	@RequestMapping("/tipo")
	public List<PokemonDTO> listaPokemonsByTipo(String nome) {
		List<Pokemon> pokemons = new ArrayList<Pokemon>();

		 //no navegador http://localhost:8080/pokemon/tipo?nome=Planta 
		  pokemons = pokemonRepository.findByTipoNome(nome);
		
		return PokemonDTO.converterPokemon(pokemons);
	}
	
	@GetMapping
	public List listaPokemons() {
		
		ArrayList<Pokemon> lista = new ArrayList<Pokemon>();
		Pokemon poke = new Pokemon(Long.valueOf(1), "Charmander", "top");
		Pokemon poke1 = new Pokemon(Long.valueOf(1), "Charmeleon", "hahah");
		Pokemon poke2 = new Pokemon(Long.valueOf(1), "Charizard", "eirji");
		
		lista.add(poke);
		lista.add(poke1);
		lista.add(poke2);
		
		return lista;
	}
	
	@PostMapping
	public void cadastrar(@RequestBody PokemonForm pokeForm) {
		Pokemon poke = pokeForm.converter(tipoRepository);
		
		pokemonRepository.save(poke);
		
	}

}
