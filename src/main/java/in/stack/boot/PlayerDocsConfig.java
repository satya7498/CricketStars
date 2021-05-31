package in.stack.boot;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class PlayerDocsConfig {
	
	@Bean
	public Docket postApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Cricket Players").apiInfo( apiInfo()).select()
				.paths(regex("/api.*")).build();
	}
	
	
	
	private ApiInfo apiInfo() {
		
		return new ApiInfoBuilder().title("Player Service").description("player services")
				.termsOfServiceUrl("All rights reserved").license("Team License").version("1.0").build();
		
	}

}
