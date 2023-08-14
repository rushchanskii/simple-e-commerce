<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>	

<div class="container">
	<h1>Your Shopping Cart</h1>
	<table class="table">
		<thead>
			<tr>
			    <th> </th>
				<th>Product</th>
				<th>Current Price</th>
				<th>Quantity</th>
				<th></th>
				
				</tr>
		</thead>
		<tbody>		
			<c:forEach items="${shoppingCart}" var="shoppingCart">
				<tr>
				
					<td>
								<c:if test="${shoppingCart.mainPictureId != 0}">
								<img src="/getproductpicture/${shoppingCart.mainPictureId}"
										width="75">
								</c:if>
					</td>
					<td>
								 <div class="">${shoppingCart.productName}</div>
								 <div class="text-muted ps-3 pt-1">
								  <small>${shoppingCart.description}</small>
								  </div>
					
					</td>
					<td> 
					 <c:choose>
    					<c:when test="${shoppingCart.cartPrice > shoppingCart.currentPrice}">
   						 
   						   <del>${shoppingCart.cartPrice}</del>  
   						    <div class="text-success mr-3 text-small">
   						  <strong> ${shoppingCart.currentPrice}</strong></div>
   						 
    					</c:when>   

    					<c:when test="${shoppingCart.cartPrice < shoppingCart.currentPrice}">
   						   
   						     <del>${shoppingCart.cartPrice}</del>  
   						      <div class="text-danger mr-3 text-small">
   						  <strong> ${shoppingCart.currentPrice}</strong></div>
   						   
      					</c:when>   

    					<c:otherwise>
     					 					 <strong> 		 ${shoppingCart.currentPrice}</strong>
   						 </c:otherwise>
   						 
					</c:choose>
			<%-- 		<c:if test="${shoppingCart.cartPrice == shoppingCart.currentPrice}">
					${shoppingCart.currentPrice}
					</c:if> --%>
					</td>
							
			<td>
		<form action="/client/updatecart" method="get">
			<input type="text" name="productQuantity"
				value="${shoppingCart.quantity}" onchange="this.form.submit()" />
			<input type="hidden" name="cartId"
				value="${shoppingCart.id}"/>
		
		</form>
						</td>
					
					<td> <a href="/client/deletefromcart?cartId=${shoppingCart.id}&productIdCheckbox=${shoppingCart.productId}" 
					class="btn btn-warning">Delete</a>   </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/client/completeOrder" class="btn btn-success">Complete order</a>
</div>

<%@ include file="common/footer.jspf" %>