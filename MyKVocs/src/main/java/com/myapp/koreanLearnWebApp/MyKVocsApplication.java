package com.myapp.koreanLearnWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MyKVocsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyKVocsApplication.class, args);
	}
	
	
	
	
	/* TODO: Feature to search for a word in order to add it to the app
	 * private static void getEmployees() { final String uri =
	 * "https://krdict.korean.go.kr/api/search?key=3CBFC5CAD75E90CC09011CB702EC7CDC&q=사과&translated=y&trans_lang=1";
	 * 
	 * //TODO: Autowire the RestTemplate in all the examples RestTemplate
	 * restTemplate = new RestTemplate();
	 * 
	 * String result = restTemplate.getForObject(uri, String.class);
	 * System.out.println(result); }
	 */

}
