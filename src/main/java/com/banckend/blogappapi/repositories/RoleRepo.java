package com.banckend.blogappapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banckend.blogappapi.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
