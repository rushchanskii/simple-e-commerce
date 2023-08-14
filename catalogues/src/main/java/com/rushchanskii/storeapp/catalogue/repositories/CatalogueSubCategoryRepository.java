package com.rushchanskii.storeapp.catalogue.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rushchanskii.storeapp.catalogue.entities.CatalogueSubCategory;

public interface CatalogueSubCategoryRepository 	
   extends JpaRepository<CatalogueSubCategory, Long>{

	List<CatalogueSubCategory> findAllByCategoryId(Long categoryId);

};
