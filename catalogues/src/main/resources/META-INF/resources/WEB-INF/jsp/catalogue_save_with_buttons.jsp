<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>	
<div class="container-fluid">
 <div class="row">
 <div class="col-lg-3">
	<h1>Product Category</h1>
	<table class="table">
		<thead>
			<tr>
				<th>Category</th>
					<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>		
			<c:forEach items="${categories}" var="categories">
				<tr>
					<td>${categories.categoryName}</td>
					<td> <a href="delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a>   </td>
					<td> <a href="update-todo?id=${todo.id}" class="btn btn-success">Update</a>   </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="add-todo" class="btn btn-success">Add Category</a>
	</div>
<div class="col-lg-4">	
		<h1>Product SubCategory</h1>
	<table class="table">
		<thead>
			<tr>
				<th>Sub Category</th>
					<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>		
			<c:forEach items="${subcategories}" var="subcategories">
				<tr>
					<td>${subcategories.subCategoryName}</td>
					<td> <a href="delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a>   </td>
					<td> <a href="update-todo?id=${todo.id}" class="btn btn-success">Update</a>   </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="add-todo" class="btn btn-success">Add SubCategory</a>
	
</div>

<div class="col-lg-5">	
		<h1>Products</h1>
	<table class="table">
		<thead>
			<tr>
				<th>Sub Category</th>
					<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>		
			<c:forEach items="${products}" var="products">
				<tr>
					<td>${products.productName}</td>
					<td> <a href="delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a>   </td>
					<td> <a href="update-todo?id=${todo.id}" class="btn btn-success">Update</a>   </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="add-todo" class="btn btn-success">Add SubCategory</a>
	
</div>

</div>
</div>

<%@ include file="common/footer.jspf" %>