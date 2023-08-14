<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>	

<div class="container">
	
	<h1>Enter Product Details</h1>
	
	<form:form method="post" modelAttribute="products">

		<fieldset class="mb-3">				
			<form:label path="name">Product name</form:label>
			<form:input type="text" path="name" required="required"/>
		</fieldset>

		<fieldset class="mb-3">				
			<form:label path="description">Product Description</form:label>
			<form:input type="text" path="description" required="required"/>
	
		</fieldset>
		
		<fieldset class="mb-3">				
			<form:label path="price">Price</form:label>
			<form:input type="text" path="price" required="required"/>
	
		</fieldset>

		<form:input type="hidden" path="id" />
		<form:input type="hidden" path="categoryId" />
		<form:input type="hidden" path="subCategoryId" />
		<form:input type="hidden" path="mainPictureId" />

		<input type="submit" class="btn btn-success"/>
	
	</form:form>
	
	<a href="/manager/deleteproduct?productIdCheckbox=${productId}" class="btn btn-warning">Delete</a> 
	
</div>

<%@ include file="common/footer.jspf" %>


