package com.rushchanskii.storeapp.catalogue.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rushchanskii.storeapp.catalogue.entities.CatalogueCategory;
import com.rushchanskii.storeapp.catalogue.entities.CatalogueProductItem;

public interface CatalogueCategoryRepository 
	extends JpaRepository<CatalogueCategory, Long>{

	Optional<CatalogueCategory> findById(long parseLong);

};
