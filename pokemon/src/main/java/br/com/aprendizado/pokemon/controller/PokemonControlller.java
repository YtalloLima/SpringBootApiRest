package br.com.aprendizado.pokemon.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.aprendizado.pokemon.controller.dto.PokemonDTO;
import br.com.aprendizado.pokemon.controller.form.AtualizacaoPokemonForm;
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

		// no navegador http://localhost:8080/pokemon/pokemons?nome=Charmander
		pokemons = pokemonRepository.findByNome(nome);

		return PokemonDTO.converterPokemon(pokemons);
	}

	@RequestMapping("/tipo")
	public List<PokemonDTO> listaPokemonsByTipo(String nome) {
		List<Pokemon> pokemons = new ArrayList<Pokemon>();

		// no navegador http://localhost:8080/pokemon/tipo?nome=Planta
		pokemons = pokemonRepository.findByTipoNome(nome);

		return PokemonDTO.converterPokemon(pokemons);
	}

	@GetMapping
	public List<PokemonDTO> listaPokemons() {

		List<Pokemon> pokemons = pokemonRepository.findAll();

		return PokemonDTO.converterPokemon(pokemons);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<PokemonDTO> cadastrar(@RequestBody @Valid PokemonForm pokeForm, UriComponentsBuilder uriBuilder) {
		Pokemon poke = pokeForm.converter(tipoRepository);

		pokemonRepository.save(poke);
		
		URI uri = uriBuilder.path("/pokemon/{id}").buildAndExpand(poke.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	@PutMapping("/{id}")
	// avisa pro Spring que ?? para salvar no final
	// http://localhost:8080/pokemon/5
	@Transactional
	public ResponseEntity<PokemonDTO> atualizar(@PathVariable Long id,
			@RequestBody @Valid AtualizacaoPokemonForm atualizacaoPokemonForm) {

		Optional<Pokemon> optional = pokemonRepository.findById(id);

		if (optional.isPresent()) {
			Pokemon poke = atualizacaoPokemonForm.atualizar(id, pokemonRepository);

			return ResponseEntity.ok(new PokemonDTO(poke));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Pokemon> optional = pokemonRepository.findById(id);

		if (optional.isPresent()) {
			pokemonRepository.deleteById(id);

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();

	}
	

	
}
