<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>	

<div class="container">
	
	<h1>Enter Subcategory Details for ${categoryName}</h1>
	
	<form:form method="post" modelAttribute="subcategory">

		<fieldset class="mb-3">				
			<form:label path="subCategoryName">Subcategory name</form:label>
			<form:input type="text" path="subCategoryName" required="required"/>
		</fieldset>

		
		<form:input type="hidden" path="id" />
		<form:input type="hidden" path="categoryId" />
	

		<input type="submit" class="btn btn-success"/>
	
	</form:form>
	<a href="/manager/deletesubcategory?categoryIdCheckbox=${categoryId}+&subCategoryIdCheckbox=${subCategoryId}" class="btn btn-warning">Delete</a> 
</div>

<%@ include file="common/footer.jspf" %>


