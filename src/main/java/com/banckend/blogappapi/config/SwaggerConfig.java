package com.banckend.blogappapi.config;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.annotations.Comments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		  info = @Info(
				    contact = @Contact(
				    		  name = "Shubham Sutar",
				    		  email = "Shubhamsutar1182@gmail.com",
				    		  url = "https://portfolio-app-shubham.web.app/"
				    		),
				    description = "Blogging Application API'S description ",
				    title = "Blog Writting Application : Backend API's",
				    version = "1.0",
				    license = @License(
				    		  name = "Licence name",
				    		  url="None"
				    		),
				    termsOfService = "Terms of Services"
				  ),
		  servers = {
				  @Server(
						   description = "local ENv",
						   url = "http://localhost:8080"
						  ),
				  @Server(
						   description = "Production ENV",
						   url = "http://localhost:8080"
						  )
		  },
		  security = {
				  @SecurityRequirement(
						   name="bearerAuth"
						  )
		  }
		)

@SecurityScheme(
		  name = "bearerAuth",
		  description = "JWT Authentication Description",
		  scheme = "bearer",
		  type = SecuritySchemeType.HTTP,
		  bearerFormat = "JWT",
		  in = SecuritySchemeIn.HEADER
		)

public class SwaggerConfig {
    
	

	   
}
