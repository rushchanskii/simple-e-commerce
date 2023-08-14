package com.rushchanskii.storeapp.catalogue.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.rushchanskii.storeapp.catalogue.entities.CataloguePictures;
import com.rushchanskii.storeapp.catalogue.repositories.CataloguePicturesRepository;

public class CataloguePicturesServices {

//	
//	@Autowired
//    private CataloguePicturesRepository pictRepositary;
//	
//	public CataloguePictures uploadImage(MultipartFile file) throws IOException {
//		CataloguePictures productImage = new CataloguePictures();
//		productImage.setName(file.getOriginalFilename());
//		productImage.setType(file.getContentType());
//		productImage.setImageData(ImageUtils.compressImage(file.getBytes()));
//		return pictRepositary.save(productImage);
//	}
//	
//	public byte[] downloadImage(String fileName){
//        Optional<CataloguePictures> imageData = pictRepositary.findByName(fileName);
//        return ImageUtils.decompressImage(imageData.get().getImageData());
//    }


}