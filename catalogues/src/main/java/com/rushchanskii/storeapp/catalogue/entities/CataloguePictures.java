package com.rushchanskii.storeapp.catalogue.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

// import java.awt.image.BufferedImage;

// import com.rushchanskii.storeapp.catalogue.services.ImageUtils;


@Entity
@Data
public class CataloguePictures {
	
	@Id
	@GeneratedValue
	private Long id;
	private Long productId;
//    private String name;
//    private String description;
//    private String type;
    
    @Lob
    private byte[] imageData;
    
//    @Lob
//    private byte[] thumbnail;
    
    protected CataloguePictures() {
    }
    
  
	public CataloguePictures(Long id, Long productId, 
//			String name, 
//			String description, 
//			String type, 
			byte[] imageData) {
		super();
		this.id = id;
		this.productId = productId;
//		this.name = name;
//		this.description = description;
//		this.type = type;
		this.imageData = imageData;
//		this.thumbnail = (byte[]) ImageUtils.resizeImage((BufferedImage)imageData,50,50);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	};
    
    
}
  