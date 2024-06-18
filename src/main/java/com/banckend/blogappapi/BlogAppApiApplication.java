package com.banckend.blogappapi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.banckend.blogappapi.entity.Role;
import com.banckend.blogappapi.repositories.AppConstant;
import com.banckend.blogappapi.repositories.RoleRepo;

import java.util.*;



@SpringBootApplication
public class BlogAppApiApplication  implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo  roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
//		System.out.println(this.passwordEncoder.encode("xyz"));
		
//		First time running the appln then it crating role table and asssigned the data into the table,when it emppty.
		 try {
			 Role role1=new Role();
			 role1.setName("ROLE_NORMAL");
			 role1.setId(AppConstant.ROLE_NORMAL);
			 
			 Role role2=new Role();
			 role2.setName("ROLE_ADMIN");
			 role2.setId(AppConstant.ROLE_ADMIN	);
			 
			List<Role> roles=new ArrayList<>();
			roles.add(role1);
			roles.add(role2);
			
			this.roleRepo.saveAll(roles);
			
			   System.out.println(roles);
			 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	

	
}
