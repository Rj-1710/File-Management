package com.mphasis.parent.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
 
@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI().info(new Info().title("Trade File Application ").version("1.01").description("FileLoad API"))
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
				.components(new Components().addSecuritySchemes("bearerAuth",new SecurityScheme().name("Authorization")
				.type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
	}
 
}
 