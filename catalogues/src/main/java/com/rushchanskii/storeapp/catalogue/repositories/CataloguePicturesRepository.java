package com.rushchanskii.storeapp.catalogue.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.rushchanskii.storeapp.catalogue.entities.CataloguePictures;

import jakarta.transaction.Transactional;

public interface CataloguePicturesRepository 
	extends JpaRepository<CataloguePictures, Long>{

//	Optional<CataloguePictures> findByName(String fileName);
	List<CataloguePictures> findByProductId(Long productId);

	
	@Modifying
	@Transactional
	void deleteByProductId(long parseLong);
	

};
