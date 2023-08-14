package com.rushchanskii.storeapp.orders;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{
	
	

	List<ShoppingCart> findAllByUserId(Long loggedinUserId);
	
//	"""UPDATE shopping_cart SET 
//    current_price = catalogue_product_item.price WHERE 
//    		(shopping_cart.product_id = catalogue_product_item_id AND shopping_cart_user_id=?""")
// @Query(value = "UPDATE shopping_cart 
//	SET current_price = (
//	SELECT catalogue_product_item.price 
//	FROM catalogue_product_item 
//	WHERE shopping_cart.product_id = catalogue_product_item.id 
//	AND shopping_cart.user_id = :loggeduser)")
	   	
//    @Query("UPDATE ShoppingCart sc" +
//    		"SET sc.currentPrice = ( " +
//    		"SELECT price " + 
//    		"FROM CatalogueProductItem pi" +
//    		"WHERE sc.productId = pi.id " +
//    		"AND sc.userId = :loggeduser) ")
	
//  @Query("UPDATE ShoppingCart sc" +
//	"SET sc.currentPrice = ( " +
//	"SELECT price " + 
//	"FROM CatalogueProductItem pi" +
//	"WHERE sc.productId = pi.id " +
//	"AND sc.userId = :loggeduser) ")
	
//	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE shopping_cart SET current_price = ( " +
			"SELECT catalogue_product_item.price " +
			"FROM catalogue_product_item "+
			"WHERE shopping_cart.product_id = catalogue_product_item.id "+
			"AND shopping_cart.user_id = :loggeduser)", nativeQuery = true)
    void updateCurrentPriceByUserId(@Param("loggeduser") String loggeduser);

}
