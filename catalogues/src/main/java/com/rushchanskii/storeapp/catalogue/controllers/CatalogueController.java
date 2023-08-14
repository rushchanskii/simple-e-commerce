package com.rushchanskii.storeapp.catalogue.controllers;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rushchanskii.storeapp.catalogue.entities.CatalogueCategory;
import com.rushchanskii.storeapp.catalogue.entities.CataloguePictures;
import com.rushchanskii.storeapp.catalogue.entities.CatalogueProductItem;
import com.rushchanskii.storeapp.catalogue.entities.CatalogueSubCategory;
import com.rushchanskii.storeapp.catalogue.repositories.CatalogueCategoryRepository;
import com.rushchanskii.storeapp.catalogue.repositories.CataloguePicturesRepository;
import com.rushchanskii.storeapp.catalogue.repositories.CatalogueProductItemRepository;
import com.rushchanskii.storeapp.catalogue.repositories.CatalogueSubCategoryRepository;
import com.rushchanskii.storeapp.orders.ShoppingCart;
import com.rushchanskii.storeapp.orders.ShoppingCartRepository;
import com.rushchanskii.storeapp.users.Users;
import com.rushchanskii.storeapp.users.UsersRepository;

import jakarta.validation.Valid;

@Controller
// @SessionAttributes("sessionCategory", "sessionSubCategory")
public class CatalogueController {

	private CatalogueCategoryRepository catRepository;
	private CatalogueSubCategoryRepository subCatRepository;
	private CatalogueProductItemRepository prodRepository;
	private CataloguePicturesRepository picRepository;
	private UsersRepository usersRepository;
	private ShoppingCartRepository cartRepository;

	private boolean isEditable = false;
	private boolean isAuthenticated = false;

	public CatalogueController(CatalogueCategoryRepository cat, CatalogueSubCategoryRepository subcat,
			CatalogueProductItemRepository prod, CataloguePicturesRepository pics, 
			UsersRepository usersRepository, ShoppingCartRepository cartRepository) {
		super();
		this.catRepository = cat;
		this.subCatRepository = subcat;
		this.prodRepository = prod;
		this.picRepository = pics;
		this.usersRepository = usersRepository;
		this.cartRepository = cartRepository;

	}

	// @RequestMapping(value="/manager/uploadpicture",method = RequestMethod.POST)
	@PostMapping("/manager/uploadpicture")
	public String uploadProductPicture(@RequestParam("file") MultipartFile file,
			@RequestParam Map<String, String> model_in, ModelMap model) throws IOException {
		CataloguePictures picture;
		CatalogueProductItem product;
		// String output = "";
	
		String productId = model_in.get("productIdCheckbox");
		if (!file.isEmpty()) {
			picture = new CataloguePictures(0L, Long.parseLong(productId), 
					scalePicture(file.getBytes(),0,700));
			product = prodRepository.findById(Long.parseLong(productId)).get();
			Long newPicId;
			newPicId = picRepository.save(picture).getId();
			if (product.getMainPictureId().equals(0L) ) {
				product.setMainPictureId(newPicId);
				prodRepository.save(product);
			}
		}
//		model.addAttribute("products", products);
//		output.concat("redirect:/catalogueFiltered");
//		output.concat("?productIdCheckbox=");
//		output.concat(productId);
		// System.out.println(output);
		return "redirect:/catalogueFiltered?productIdCheckbox=" + productId;
		// return output;
	}

	@RequestMapping(value = "/manager/addproduct", method = RequestMethod.GET)
	public String emptyProductItem(@RequestParam Map<String, String> model_in, ModelMap model) {
		CatalogueProductItem products;

		String categoryId = model_in.get("categoryIdCheckbox");
		String subCategoryId = model_in.get("subCategoryIdCheckbox");

		products = new CatalogueProductItem(0L, 
				Long.parseLong(categoryId), 
				Long.parseLong(subCategoryId), " ", " ",
				0.0);
		model.addAttribute("products", products);
		return "addProduct";
	}
	
	@RequestMapping(value = "/manager/addproduct", method = RequestMethod.POST)
	public String addProductItem(@Valid CatalogueProductItem products, ModelMap model, BindingResult result) {
		// System.out.println(products.toString());
		prodRepository.save(products);
		model.addAttribute("products", products);
		return "redirect:/catalogueFiltered?categoryIdCheckbox=" + products.getCategoryId().toString()
				+ "&subCategoryIdCheckbox=" + products.getSubCategoryId().toString();
	}
	
	
	@RequestMapping(value = "/manager/editproduct", method = RequestMethod.GET)
	public String editProductItem(@RequestParam Map<String, String> model_in, ModelMap model) {
		CatalogueProductItem product;

		Long productId = Long.parseLong(model_in.get("productIdCheckbox"));
	//	System.out.println(productId);
		product=prodRepository.findById(productId).get();
		model.addAttribute("products", product);
		model.addAttribute("productId", productId);
		return "addProduct";
	}
	
	@RequestMapping(value = "/manager/editproduct", method = RequestMethod.POST)
	public String saveProductItem (ModelMap model, CatalogueProductItem products) {
		
		prodRepository.save(products);
		return "redirect:/catalogueFiltered?productIdCheckbox=" + products.getId().toString();
	}

	
	@RequestMapping(value = "/manager/deleteproduct", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam Map<String, String> model_in, ModelMap model) {
		CataloguePictures prictures = null;
		CatalogueProductItem product;
		String productId = model_in.get("productIdCheckbox");
		String catId = null;
		String subCatId = null;
		
		
		if (!productId.isBlank()) {
			Long prodId = Long.parseLong(productId);
			product=prodRepository.findById(prodId).get();
			catId = product.getCategoryId().toString();
			subCatId = product.getSubCategoryId().toString();
		
			prodRepository.delete(product);
			picRepository.deleteByProductId(prodId);
		
		}
	
	 	return "redirect:/catalogueFiltered?categoryIdCheckbox=" + catId +
	 			"&subCategoryIdCheckbox=" + subCatId;
	}

	
	

	
	@RequestMapping(value = "/manager/editcategory", method = RequestMethod.GET)
	public String editCategory(@RequestParam Map<String, String> model_in, ModelMap model) {
		CatalogueCategory category;
		String categoryId = model_in.get("categoryIdCheckbox");
		
        if (categoryId !=null) {
			category = catRepository.findById(Long.parseLong(categoryId)).get();
        } else {
        	category = new CatalogueCategory(0L, "");
        }
		model.addAttribute("category", category);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("welcomeUser", getLoggedinUsername());
		return "addCategory";
	}
	
	@RequestMapping(value = "/manager/editcategory", method = RequestMethod.POST)
	public String saveCategory (ModelMap model, CatalogueCategory category) {
		if (!category.getCategoryName().isBlank()) {
		catRepository.save(category); }
		if (category.getId() != 0L) 
			return "redirect:/catalogueFiltered?categoryIdCheckbox=" + category.getId().toString();
		else 
			return "redirect:/catalogue";
	}
	
	@RequestMapping(value = "/manager/deletecategory", method = RequestMethod.GET)
	public String deleteCategory(@RequestParam Map<String, String> model_in, ModelMap model) {
		List<CatalogueSubCategory> subCategory = null;
		CatalogueCategory category;
		String categoryId = model_in.get("categoryIdCheckbox");
		
		if (!categoryId.isBlank()) {
		
			subCategory=subCatRepository.findAllByCategoryId(Long.parseLong(categoryId));
			if (subCategory.isEmpty())
				catRepository.deleteById(Long.parseLong(categoryId));
		}
	
		return "redirect:/catalogue";
	}
	
	

	
	
	@RequestMapping(value = "/manager/editsubcategory", method = RequestMethod.GET)
	public String editSubCategory(@RequestParam Map<String, String> model_in, ModelMap model) {
		CatalogueSubCategory subCategory;
		CatalogueCategory category;
		String categoryName;
		String categoryId = model_in.get("categoryIdCheckbox");
		String subCategoryId = model_in.get("subCategoryIdCheckbox");
		
		if (categoryId !=null) {
		categoryName = catRepository.findById(Long.parseLong(categoryId)).get().getCategoryName();
		if (subCategoryId != null) {
			subCategory=subCatRepository.findById(Long.parseLong(subCategoryId)).get();
		} else {
			subCategory = new CatalogueSubCategory(0L, Long.parseLong(categoryId), " ");
		}
		} else  {
			return "redirect:catalogue";
		}
		model.addAttribute("subcategory", subCategory);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("subCategoryId", subCategoryId);
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("welcomeUser", getLoggedinUsername());
		return "addSubCategory";
	}
	
	

	
	@RequestMapping(value = "/manager/editsubcategory", method = RequestMethod.POST)
	public String saveSubCategory (ModelMap model, CatalogueSubCategory subCategory) {
		if (!subCategory.getSubCategoryName().isBlank()) {
		subCatRepository.save(subCategory);}
		if (subCategory.getId() !=0) {
		return "redirect:/catalogueFiltered?categoryIdCheckbox=" + subCategory.getCategoryId().toString()+
		"&subCategoryIdCheckbox=" + subCategory.getId().toString();
		} else {
			return "redirect:/catalogueFiltered?categoryIdCheckbox=" + subCategory.getCategoryId().toString();
		}
	}
	
	@RequestMapping(value = "/manager/deletesubcategory", method = RequestMethod.GET)
	public String deleteSubCategory(@RequestParam Map<String, String> model_in, ModelMap model) {
		List<CatalogueProductItem> products = null;
		CatalogueSubCategory subCategory;
		String subCategoryId = model_in.get("subCategoryIdCheckbox");
		String categoryId = model_in.get("categoryIdCheckbox");
		
		if (!subCategoryId.isBlank()) {
		
			products=prodRepository.findAllBySubCategoryId(Long.parseLong(subCategoryId));
			if (products.isEmpty())
				subCatRepository.deleteById(Long.parseLong(subCategoryId));
		}
	
	 	return "redirect:/catalogueFiltered?categoryIdCheckbox=" + categoryId;
	}

	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchProducts(@RequestParam Map<String, String> model_in, ModelMap model) {
		List<CatalogueProductItem> products;

		String searchPattern = model_in.get("search");

		products = prodRepository.findByNameContainingIgnoreCase(searchPattern);
		model.addAttribute("products", products);
		model.addAttribute("isCategoryDefined", false);
		model.addAttribute("isSubCategoryDefined", false);
		model.addAttribute("isProductDefined", false);
		return "catalogue";
	}

	@RequestMapping(value = "/getproductpicture/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<ByteArrayResource> getProductPicture(@PathVariable("id") String picId) {
		CataloguePictures picture;
		picture = picRepository.getReferenceById(Long.parseLong(picId));

		return ResponseEntity.ok(new ByteArrayResource(picture.getImageData()));
	}
	



	@RequestMapping(value = "/catalogue", method = RequestMethod.GET)
	public String startCataloguePage(ModelMap model) {

		List<CatalogueCategory> categories = catRepository.findAll();
		List<CatalogueSubCategory> subCategories = subCatRepository.findAll();
		List<CatalogueProductItem> products = prodRepository.findAll();
		// System.out.println(categories);
		// model.put("name", getLoggedinUsername());

		if (getLoggedinUsername().matches("Guest")) {
			isAuthenticated = false;
			isEditable = false;
		} else {
			isAuthenticated = true;
		}

		model.addAttribute("categories", categories);
		model.addAttribute("subcategories", subCategories);
		model.addAttribute("products", products);
		model.addAttribute("isEditable", isLoggedUserManager());
		model.addAttribute("isAuthenticated", isAuthenticated);
		model.addAttribute("isCategoryDefined", false);
		model.addAttribute("isSubCategoryDefined", false);
		model.addAttribute("isProductDefined", false);
		model.addAttribute("welcomeUser", getLoggedinUsername());

		return "catalogue";
	}

	@RequestMapping(value = "/catalogueFiltered", method = RequestMethod.GET)
	public String filteredCataloguePage(@RequestParam Map<String, String> model_in, ModelMap model) {

		// System.out.println(model_in);
		List<CatalogueCategory> categories = null;
		List<CatalogueSubCategory> subCategories = null;
		List<CatalogueProductItem> products = null;
		List<CataloguePictures> productPictures = null;
		List<ShoppingCart> shoppingCart = null;
		String userName = getLoggedinUsername();

		if (userName.matches("Guest")) {
			isAuthenticated = false;
			isEditable = false;
		} else {
			isAuthenticated = true;
		}

		model.addAttribute("isCategoryDefined", false);
		model.addAttribute("isAuthenticated", isAuthenticated);
		model.addAttribute("isSubCategoryDefined", false);
		model.addAttribute("isProductDefined", false);
		model.addAttribute("welcomeUser", userName);

		String categoryId = model_in.get("categoryIdCheckbox");
		String subCategoryId = model_in.get("subCategoryIdCheckbox");
		String productId = model_in.get("productIdCheckbox");

		// System.out.println(categoryId);
		// System.out.println(subCategoryId);

		if (categoryId != null) {
			categories = catRepository.findById(Long.parseLong(categoryId)).stream().toList();
			model.addAttribute("isCategoryDefined", true);
		} else
			categories = catRepository.findAll();

		if (categoryId != null) {
			subCategories = subCatRepository.findAllByCategoryId(Long.parseLong(categoryId)).stream().toList();
			products = prodRepository.findAllByCategoryId(Long.parseLong(categoryId));
			model.addAttribute("isCategoryDefined", true);
		} else {
			subCategories = subCatRepository.findAll();
			products = prodRepository.findAll();
		}
		// subCategories= subCatRepository.findAllByCategoryId(categoryId);

		if (subCategoryId != null) {
			products = prodRepository.findAllBySubCategoryId(Long.parseLong(subCategoryId)).stream().toList();
			subCategories = subCatRepository.findById(Long.parseLong(subCategoryId)).stream().toList();
			model.addAttribute("isSubCategoryDefined", true);
		}

		if (productId != null) {
			products = prodRepository.findById(Long.parseLong(productId)).stream().toList();
//			subCategories= subCatRepository.findById(Long.parseLong(subCategoryId)).stream().toList();
//			categories= catRepository.findById(Long.parseLong(categoryId)).stream().toList();
			subCategories = subCatRepository.findById(products.get(0).getSubCategoryId()).stream().toList();
			categories = catRepository.findById(products.get(0).getCategoryId()).stream().toList();
			model.addAttribute("currentCategory", categories.get(0).getId().toString());
			model.addAttribute("currentCategoryName", categories.get(0).getCategoryName());
			model.addAttribute("currentSubCategory", subCategories.get(0).getId().toString());
			model.addAttribute("currentSubCategoryName", subCategories.get(0).getSubCategoryName());
			model.addAttribute("isProductDefined", true);
			model.addAttribute("isCategoryDefined", true);
			model.addAttribute("isSubCategoryDefined", true);
			productPictures = picRepository.findByProductId(Long.parseLong(productId));
		}

		isEditable = isLoggedUserManager();
		model.addAttribute("categories", categories);
		model.addAttribute("subcategories", subCategories);
		model.addAttribute("products", products);
		model.addAttribute("isEditable", isEditable);
		if (categoryId != null)
			model.addAttribute("currentCategory", categoryId);
		if (subCategoryId != null)
			model.addAttribute("currentSubCategory", subCategoryId);
		if (productId != null) {
			model.addAttribute("currentProduct", productId);
			model.addAttribute("productPictures", productPictures);
		}

		if (isAuthenticated) {
			shoppingCart = cartRepository.findAllByUserId(getLoggedinUserId());
			model.addAttribute("shoppingcart", shoppingCart);
		}
		
		return "catalogue";
	}

	private String getLoggedinUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		return authentication.getName();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			return currentUserName;
		} else {
			return "Guest";
		}
	}
//	private Boolean isLoggedUserManager() {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("MANAGER"))) {
//			    return true;
//			} else {
//				return false;
//			}
//	}

	private Boolean isLoggedUserManager() {
		String username = getLoggedinUsername();
		if (username.equals("Guest")) {
			return false;
		} else {
			Users user = usersRepository.findByLoginName(username);
			if (user.getUserRole().equals("ROLE_MANAGER")) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	private Long getLoggedinUserId() {
	    String userName = getLoggedinUsername();
		if (userName.matches("Guest")) {
			return 0L;
		} else {
			return usersRepository.findByLoginName(userName).getId();
		}
	}
		
		
		private byte[] scalePicture(byte[] fileData, int width, int height) throws IOException {
		    ByteArrayInputStream in = new ByteArrayInputStream(fileData);
		        BufferedImage img = ImageIO.read(in);
		        if(height == 0) {
		            height = (width * img.getHeight())/ img.getWidth(); 
		        }
		        if(width == 0) {
		            width = (height * img.getWidth())/ img.getHeight();
		        }
		        Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		        BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		        imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

		        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		        ImageIO.write(imageBuff, "jpg", buffer);

		        return buffer.toByteArray();
		  
		        
		    }
	
	
	
	
}