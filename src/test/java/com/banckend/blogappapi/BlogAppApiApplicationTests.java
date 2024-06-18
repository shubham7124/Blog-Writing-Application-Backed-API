package com.banckend.blogappapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.banckend.blogappapi.repositories.UserRepo;

@SpringBootTest
class BlogAppApiApplicationTests {
	
	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}
	
//	tracking the implemantion class name of userRespo interface
//    @Test
//	public void respoTest()
//	{
//		String name=this.userRepo.getClass().getName();
//		String pkg=this.userRepo.getClass().getPackageName();
//		System.out.println(name);
//		System.out.println(pkg);
//		
//	}
}
