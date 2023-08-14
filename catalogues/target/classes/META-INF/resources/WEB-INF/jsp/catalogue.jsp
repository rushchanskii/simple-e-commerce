<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>


<c:if test="${!isProductDefined}">
	<div class="container-fluid m-0">
		<div class="row m-0">
			<a href="/catalogue" class="btn btn-warning "> Catalogue </a>
		</div>
		<div class="row md-1  justify-content-between">
			<div class="col-2 align-middle">
				<table class="table align-middle">
					<thead>
						<tr>
							<th>Category</th>
							<th></th>
								<c:if test="${isEditable}">
								<th>Ed</th>
								</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${categories}" var="categories">
							<tr>
								<td>${categories.categoryName}</td>
								<td>
									<form action="catalogueFiltered" method="get">
										<input type="checkbox" name="categoryIdCheckbox"
											value="${categories.id}" onchange="this.form.submit()" />
									</form>
								</td>
								<c:if test="${isEditable}">
								<td>
								 <div class="bg-warning">
									<form action="/manager/editcategory" method="get">
										<input type="checkbox" name="categoryIdCheckbox"
											value="${categories.id}" onchange="this.form.submit()" />
									</form>
								 </div>
								</td>
								</c:if>
							</tr>


						</c:forEach>
					</tbody>
				</table>
<!-- 				<a href="catalogue" class="btn btn-success">All</a> -->
				<c:if test="${isEditable}">
					<a href="manager/editcategory" class="btn btn-success">Add Category</a>
				</c:if>

			</div>
			<div class="col-3">
				<table class="table align-middle">
					<thead>
						<tr>
							<th>Sub Category</th>
							<th></th>
							
							<c:if test="${isEditable && isCategoryDefined}">
								<th>Ed</th>
								</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${subcategories}" var="subcategories">
							<tr>
								<td>${subcategories.subCategoryName}</td>
								<td>
									<form action="catalogueFiltered" method="get">
										<input type="hidden" name="categoryIdCheckbox"
											value="${subcategories.categoryId}"> <input
											type="checkbox" name="subCategoryIdCheckbox"
											value="${subcategories.id}" onchange="this.form.submit()" />
									</form>
									
									<c:if test="${isEditable && isCategoryDefined}">
									<td>
									 <div class="bg-warning">
									<form action="/manager/editsubcategory" method="get">
										<input type="hidden" name="categoryIdCheckbox"
											value="${subcategories.categoryId}"> <input
											type="checkbox" name="subCategoryIdCheckbox"
											value="${subcategories.id}" onchange="this.form.submit()" />
									</form>
									</div>
									</td>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:if test="${isEditable && isCategoryDefined}">
					<c:url var="addsubcatURL" value="manager/editsubcategory">
						<c:param name="categoryIdCheckbox" value="${currentCategory}" />
					</c:url>
					<a href="${addsubcatURL}" class="btn btn-success"> Add
						Subcategory </a>
				</c:if>
			</div>

			<div class="col-7">
				<table class="table align-middle">
					<thead>
						<tr>
						    <th></th>
							<th>Products</th>
							<th>Price</th>
							<th class="th.sm"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${products}" var="products">
							<tr>
								<td>
								<c:if test="${products.mainPictureId != 0}">
								<img src="/getproductpicture/${products.mainPictureId}"
										width="75">
								</c:if>
								</td>
								<td>
								 <div class="">${products.name}</div>
								 <div class="text-muted ps-3 pt-1">
								  <small>${products.description}</small>
								  </div>
								 </td>
								<td>${products.price}</td>
								<td>
									<form action="catalogueFiltered" method="get">
										<input type="hidden" name="categoryIdCheckbox"
											value="${products.categoryId}"> <input type="hidden"
											name="subCategoryIdCheckbox"
											value="${products.subCategoryId}"> <input
											type="checkbox" class="form-check-input-sm"
											name="productIdCheckbox" value="${products.id}"
											onchange="this.form.submit()" />
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:if
					test="${isEditable && isCategoryDefined && isSubCategoryDefined}">
					<c:url var="addproductURL" value="manager/addproduct">
						<c:param name="categoryIdCheckbox" value="${currentCategory}" />
						<c:param name="subCategoryIdCheckbox"
							value="${currentSubCategory}" />
					</c:url>
					<a href="${addproductURL}" class="btn btn-success"> Add Product
					</a>
				</c:if>
			</div>

		</div>
	</div>
</c:if>




<c:if test="${isProductDefined}">

	<div class="container-fluid ">
		<div class="row m mb-2 justify-content-between">
			<div class="col-8 ">
				<c:url var="gotocatetgory" value="/catalogueFiltered">
					<c:param name="categoryIdCheckbox" value="${currentCategory}" />
				</c:url>
				<a href="${gotocatetgory}" class="btn btn-success">
					${currentCategoryName} </a>

				<c:url var="gotoSubcatetgory" value="/catalogueFiltered">
					<c:param name="categoryIdCheckbox" value="${currentCategory}" />
					<c:param name="subCategoryIdCheckbox" value="${currentSubCategory}" />
				</c:url>
				<a href="${gotoSubcatetgory}" class="btn btn-success">
					${currentSubCategoryName} </a>
			</div>
			<c:if test="${isAuthenticated}">

				<div class="col-auto mr-auto ">
					<c:url var="addToCart" value="/client/addtocart">
						<c:param name="productIdCheckbox" value="${currentProduct}" />
					</c:url>

					<a href="${addToCart}" class="btn btn-warning"> Add to Shopping Cart </a> 
					
				</div>

			</c:if>


		</div>
	</div>
	
	<div class="container-fluid">
		<div class="row m-0">
			<div class="col-8 align-middle bg-light md-3">
				<table class="table" style="font-size:20px">
					<thead>
						<tr>
							<th>Product</th>
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
									<td><img src="/getproductpicture/${productPictures.id}"
										height="350"></td>
								</c:forEach>
							</tr>
						</tbody>
					</table>
				</div>


				<c:if test="${isEditable}">
				<c:url var="editProduct" value="/manager/editproduct">
			<c:param name="productIdCheckbox" value="${currentProduct}" />
			</c:url>
 
					<div class="justify-content-right">
						<a href="${editProduct}" class="btn btn-warning"> Edit details </a> 
 
						<form id="picture-upload-form" action="manager/uploadpicture"
							method="post" enctype="multipart/form-data">
							<fieldset class="d-flex justify-content-center align-items-right">
								<input id="file" name="file" type="file" accept="image/*"
									class="form-control"> <input type="hidden"
									name="productIdCheckbox" value="${currentProduct}">
								<button type="submit" class="btn btn-success d-flex">Upload</button>
							</fieldset>
						</form>
					</div>
				</c:if>

			</div>

			<c:if test="${isAuthenticated}">
				<div style="background-color: rgb(225, 235, 235)"
					class="col-4 align-middle md-3">







					<table class="table align-middle">

						<thead>
							<tr>
								<th>Products in cart</th>
								<th>Del</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${shoppingcart}" var="shoppingcart">
								<tr>
									<td>${shoppingcart.productName}</td>
									<td>
										<form action="client/deletefromcart" method="get">
											<input type="checkbox" name="cartId"
												value="${shoppingcart.id}" onchange="this.form.submit()" />
											<input type="hidden" name="productIdCheckbox"
												value="${currentProduct}">

										</form>


									</td>
								</tr>


							</c:forEach>
						</tbody>
					</table>


				</div>
			</c:if>
		</div>
	</div>
</c:if>
<%@ include file="common/footer.jspf"%>