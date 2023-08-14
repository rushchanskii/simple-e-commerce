package com.rushchanskii.storeapp.catalogue.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CatalogueSubCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	private Long categoryId;
	private String subCategoryName;

	protected CatalogueSubCategory() {
		
	};
	
	public CatalogueSubCategory(Long id, Long categoryId, String subCategoryName) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.subCategoryName = subCategoryName;
		
	};
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	
	
	

}
