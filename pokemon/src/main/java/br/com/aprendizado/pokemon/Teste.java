package br.com.aprendizado.pokemon;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.aprendizado.pokemon.controller.dto.TokenDTO;

public class Teste {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		
		  String url = "http://localhost:8080/auth";

				  //setando o header da requisição. Veja se a documentação pede algum
				  //outro header além desse e adicione, se necessário
				  HttpHeaders httpHeaders = new HttpHeaders();
				  httpHeaders.setContentType(MediaType.APPLICATION_JSON);

				  //Montando o json esperado pelo Facebook
				  JSONObject json = new JSONObject();
				  json.put("email", "aluno@email.com");
				  json.put("senha", "123456");

				  //Criando o objeto que representa a requisição a ser enviada
				  HttpEntity <String> httpEntity = new HttpEntity <String> (json.toString(), httpHeaders);
				  RestTemplate restTemplate = new RestTemplate();
				  
				  String response = restTemplate.postForObject(url, httpEntity, String.class);
				 
				  //convertendo string em Json
				  Gson g = new Gson(); 
				  TokenDTO tokenDTO = g.fromJson(response, TokenDTO.class);

				  System.out.println(tokenDTO.getToken());
				  System.out.println(tokenDTO.getTipo());

	//Removendo dado		
	    String theUrl1 = "http://localhost:8080/pokemon/3";
	    RestTemplate restTemplate1 = new RestTemplate();

	    try {
		    String encodedAuth = tokenDTO.getTipo() +" " + tokenDTO.getToken();
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    headers.add("Authorization", encodedAuth);
	        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	        ResponseEntity<String> response1 = restTemplate.exchange(theUrl1, HttpMethod.DELETE, entity, String.class);
	        System.out.println("Result - status ("+ response1.getStatusCode() + ") has body: " + response1.hasBody());
	    }
	    catch (Exception eek) {
	        System.out.println("** Exception: "+ eek.getMessage());
	    }
	}	
	
	public HttpHeaders createHttpHeaders(String user, String password)
	{
	    String notEncoded = user + ":" + password;
	    String encodedAuth = "Bearer " + Base64.getEncoder().encodeToString(notEncoded.getBytes());
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("Authorization", encodedAuth);
	    return headers;
	}

	public void doYourThing() 
	{
	    String theUrl = "http://localhost:8080/auth";
	    RestTemplate restTemplate = new RestTemplate();
	    try {
	        HttpHeaders headers = createHttpHeaders("aluno@email.com","123456");
	        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	        ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.GET, entity, String.class);
	        System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
	    }
	    catch (Exception eek) {
	        System.out.println("** Exception: "+ eek.getMessage());
	    }
	}
}
