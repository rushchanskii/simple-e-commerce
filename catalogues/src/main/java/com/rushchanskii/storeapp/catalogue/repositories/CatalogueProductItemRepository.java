package com.rushchanskii.storeapp.catalogue.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rushchanskii.storeapp.catalogue.entities.CatalogueCategory;
import com.rushchanskii.storeapp.catalogue.entities.CatalogueProductItem;

public interface CatalogueProductItemRepository 	
 extends JpaRepository<CatalogueProductItem, Long>{

	List<CatalogueProductItem> findAllBySubCategoryId(Long subCategoryId);

	List<CatalogueProductItem> findAllByCategoryId(long categoryId);

	
	List<CatalogueProductItem> findByNameContainingIgnoreCase(String searchPattern);

};
