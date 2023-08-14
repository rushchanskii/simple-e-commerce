package com.rushchanskii.storeapp.catalogue.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Size;

@Entity
public class CatalogueProductItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
//	@Column(unique=true)
//	@GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
//	@GeneratedValue(generator = "generator")
	private Long id;
	private Long categoryId;
    private Long subCategoryId;
    
	@Size(min=5, message="Enter product name")
    private String name;
	
//	@Lob
//	private byte[] description;
    private String description;
    private Double price;
    private Long   mainPictureId;
    
//    @Lob
//    @Column(name = "imagedata", length = 1000)
//    private byte[] imageData;
//    
    protected CatalogueProductItem() {
    };
    
    
    
    
    public CatalogueProductItem(Long id, Long categoryId, Long subCategoryId, String name, String description, Double price) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.mainPictureId = 0L;
	}




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




	public Long getSubCategoryId() {
		return subCategoryId;
	}




	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Double getPrice() {
		return price;
	}




	public void setPrice(Double price) {
		this.price = price;
	}

	
	public Long getMainPictureId() {
		return mainPictureId;
	}
	public void setMainPictureId(Long id) {
		this.mainPictureId = id;
	}





    
    
}
