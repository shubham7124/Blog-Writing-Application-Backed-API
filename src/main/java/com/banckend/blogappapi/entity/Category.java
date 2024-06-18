package com.banckend.blogappapi.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Setter
@Getter
@NoArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryID;
	
	@Column(name="tittle",length = 100, nullable = false)
	private  String categoryTittle;
	
	@Column(name="description")
	private String categoryDescription;
	
	
	@OneToMany(mappedBy ="category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Post> post=new ArrayList<>();
	
	
	
	
	
	public Integer getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryTittle() {
		return categoryTittle;
	}
	public void setCategoryTittle(String categoryTittle) {
		this.categoryTittle = categoryTittle;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	@Override
	public String toString() {
		return "Category [categoryID=" + categoryID + ", categoryTittle=" + categoryTittle + ", categoryDescription="
				+ categoryDescription + "]";
	}
	

}
