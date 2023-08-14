<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>	

<div class="container">
	
	<h1>Enter category details</h1>
	
	<form:form method="post" modelAttribute="category">

		<fieldset class="mb-3">				
			<form:label path="categoryName">Category name</form:label>
			<form:input type="text" path="categoryName" required="required"/>
		</fieldset>

		<form:input type="hidden" path="id" />
	

		<input type="submit" class="btn btn-success"/>
	
	</form:form>
	<a href="/manager/deletecategory?categoryIdCheckbox=${categoryId}" class="btn btn-warning">Delete</a> 
	
</div>

<%@ include file="common/footer.jspf" %>


