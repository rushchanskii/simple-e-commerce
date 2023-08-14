package com.rushchanskii.storeapp.orders;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderHeaders {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
//	@Column(unique=true)
//	@GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
//	@GeneratedValue(generator = "generator")
	private Long id;
	
	
	@Column(nullable = false)
	private Long orderId;
	
	
	@Column(nullable = false)
	private Long userId;
	
	@Column(nullable = false)
	private Double totalPrice;
	
	@Column(nullable = false)
	private LocalDate orderDate;

	
	
	
	
	public OrderHeaders(Long id, Long orderId, Long userId, Double totalPrice, LocalDate orderDate) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
	
	
	
	
	
	
}
