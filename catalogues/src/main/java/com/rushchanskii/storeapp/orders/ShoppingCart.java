package com.rushchanskii.storeapp.orders;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
//	@Column(unique=true)
//	@GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
//	@GeneratedValue(generator = "generator")
	private Long id;
	
	@Column(nullable = false)
	private Long userId;
	
	@Column(nullable = false)
	private Long productId;
	private String productName;
	private Long mainPictureId;
	private String description;
	
	private Integer quantity;
	private Double cartPrice;
	private Double currentPrice;
	
	private ShoppingCart() {
		
	}

	public ShoppingCart(Long id, Long userId, Long productId, String productName, Long mainPictureId,
			String description, Integer quantity, Double cartPrice) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.productName = productName;
		this.mainPictureId = mainPictureId;
		this.description = description;
		this.quantity = quantity;
		this.cartPrice = cartPrice;
		this.currentPrice = 0.0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getMainPictureId() {
		return mainPictureId;
	}

	public void setMainPictureId(Long mainPictureId) {
		this.mainPictureId = mainPictureId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(Double cartPrice) {
		this.cartPrice = cartPrice;
	}

	public Double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}

	

}
