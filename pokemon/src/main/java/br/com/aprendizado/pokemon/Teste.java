package br.com.aprendizado.pokemon;

import java.util.Base64;

import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Teste {

	public static void main(String[] args) {
		
		
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

				  //Chamada propriamente dita, com a resposta do Facebook mapeada para uma String
				  String response = restTemplate.postForObject(url, httpEntity, String.class);
				  
				  System.out.println(response);
				}
//		System.out.println("rfjirjo");
//		
//	    String theUrl = "http://localhost:8080/pokemon/3";
//	    RestTemplate restTemplate = new RestTemplate();
//	    
//	    String user = "aluno@email.com";
//	    String password = "123456";
//	    try {
//		    String notEncoded = user + ":" + password;
//		    String encodedAuth = "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBcGkgUG9rZW1vbnMiLCJzdWIiOiIxIiwiaWF0IjoxNjE5OTc3MjIzLCJleHAiOjE2MjAwNjM2MjN9.tB4bTm1IAerSqIaAUhyyKWnFroMX2Iko_LrtUnlstKA";
//		    HttpHeaders headers = new HttpHeaders();
//		    headers.setContentType(MediaType.APPLICATION_JSON);
//		    headers.add("Authorization", encodedAuth);
//	        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//	        ResponseEntity<String> response = restTemplate.exchange(theUrl, HttpMethod.DELETE, entity, String.class);
//	        System.out.println("Result - status ("+ response.getStatusCode() + ") has body: " + response.hasBody());
//	    }
//	    catch (Exception eek) {
//	        System.out.println("** Exception: "+ eek.getMessage());
//	    }
	
	
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
