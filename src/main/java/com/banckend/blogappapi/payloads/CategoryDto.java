package com.banckend.blogappapi.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotBlank
	@Size(min =4,message = "Min size of cartegory is 4 char")
	private String categoryTittle;
	
	@NotBlank
	@Size(min=10, message = "Min size of Descriptin is 10 char")
	private String categoryDescription;
	
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
		return "CategoryDto [categoryId=" + categoryId + ", categoryTittle=" + categoryTittle + ", categoryDescription="
				+ categoryDescription + "]";
	}

}
