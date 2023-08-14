<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container-fluid">
	<h1>Catalogue</h1>
	
	


	
	<div class="row">
		<div class="col-lg-2 align-middle">
			<table class="table align-middle">
				<thead>
					<tr>
						<th>Category</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${categories}" var="categories">
						<tr>
							<td>${categories.categoryName}</td>
		<td>
		<form action="catalogueFiltered" method="get">
  			<input type="checkbox" name="categoryIdCheckbox" 
  			value="${categories.id}"
  			onchange="this.form.submit()"/>
		</form>					
							
       
         </td>
						</tr>
					

					</c:forEach>
				</tbody>
			</table>
				<a href="catalogue" class="btn btn-success">All</a>
	  <c:if test="${isEditable}">
         <a href="manager/addcategory" class="btn btn-success">Add</a>
      </c:if>
				
		</div>
		<div class="col-lg-2">
				<table class="table align-middle">
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
							<td>
								${subcategories.subCategoryName}
							</td>
							<td>
		<form action="catalogueFiltered" method="get">
		    <input type="hidden" name="categoryIdCheckbox" value="${subcategories.categoryId}">
  			<input type="checkbox" name="subCategoryIdCheckbox" 
  			value="${subcategories.id}"
  			onchange="this.form.submit()"/>
		</form>	
		
         </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	 <c:if test="${isEditable && isCategoryDefined}" >
	      <c:url var="addsubcatURL" value="manager/addsubcategory">
                  <c:param name="categoryIdCheckbox" value="${currentCategory}"/>
          </c:url>
         <a href="${addsubcatURL}" class="btn btn-success"> Add Subcategory </a>
      </c:if>
		</div>

		<div class="col-lg-8">
				<table class="table align-middle">
				<thead>
					<tr>
						<th>Products</th>
						<th>Price</th>
						<th class="th.sm"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${products}" var="products">
						<tr>
							<td>${products.name}</td>
							<td>${products.price}</td>
		<td>
           <form action="catalogueFiltered" method="get">
		    <input type="hidden" name="categoryIdCheckbox" value="${products.categoryId}">
		    <input type="hidden" name="subCategoryIdCheckbox" value="${products.subCategoryId}">
  			<input type="checkbox" class="form-check-input-sm" name="productIdCheckbox" 
  			value="${products.id}"
  			onchange="this.form.submit()"/>
		</form>	
        </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	<c:if test="${isEditable && isCategoryDefined && isSubCategoryDefined}">
	      <c:url var="addproductURL" value="manager/addproduct">
                  <c:param name="categoryIdCheckbox" value="${currentCategory}"/>
                  <c:param name="subCategoryIdCheckbox" value="${currentSubCategory}"/>
          </c:url>
         <a href="${addproductURL}" class="btn btn-success"> Add Product </a>
      </c:if>
		</div>

	</div>
</div>
<c:if test="${isProductDefined}">
<div class="container p-3 mb-5 bg-light">
				<table class="table mt-30">
				<thead>
					<tr>
						<th>Products</th>
						<th>Description</th>
						<th>Price</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${products}" var="products">
						<tr>
						<td>${products.name}</td>
						<td>${products.description}</td>
			            <td>${products.price}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div class="table-responsive">
			<table class="table">
				<tbody>
				   <tr>
					 <c:forEach items="${productPictures}" var="productPictures">
						<td>
						   <img src="/getproductpicture/${productPictures.id}" height="400">
						</td>
					 </c:forEach>
					</tr>
				</tbody>
			</table>
			</div>
			
	     <c:url var="addToCard" value="manager/addtocard">
                  <c:param name="categoryIdCheckbox" value="${currentCategory}"/>
                  <c:param name="subCategoryIdCheckbox" value="${currentSubCategory}"/>
                  <c:param name="productIdCheckbox" value="${currentProduct}"/>
                 
          </c:url>
         		<a href="${addToCard}" class="btn btn-success"> Add to Card </a>
         
         
          <div class="form-group">
        <form id="picture-upload-form" action="manager/uploadpicture" method="post" enctype="multipart/form-data">
           <fieldset>
               <legend>Add picture</legend>
               <input id="file" name="file" type="file" accept="image/*" class="form-control">
               <input type="hidden" name="productIdCheckbox" value="${currentProduct}">
               <button type="submit" class="form-control">Upload</button>
           </fieldset>
       </form>
       </div>
        
      
			
		</div>
 </c:if>
<%@ include file="common/footer.jspf"%>