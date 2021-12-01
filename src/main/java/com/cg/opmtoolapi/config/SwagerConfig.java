package com.cg.opmtoolapi.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwagerConfig {

	@Bean
	public Docket productApi() {
		
		//Configure Swagger and return Docket instace
		return new Docket(DocumentationType.SWAGGER_2)		
				.select().apis(RequestHandlerSelectors.basePackage("com.cg.opmtoolapi.web"))
				.paths(PathSelectors.regex("/onlinepassport.*"))				
				.build()
				.apiInfo(metoInfo());
	}

	private ApiInfo metoInfo() {

		return new ApiInfo(
				"Multi-client Website Offering System API", 
				"OPM API created by Team 4 & 5", 
				"1.0", 
				"Terms of Service", 
				new Contact("Team ", "https://www.multiclientwebsite.in/", "multiclientwebsite@gmail.com"), 
				"OPP Licence v.1.0", 
				"https://www.multiclientwebsite.in/", new ArrayList<>());
	}
}