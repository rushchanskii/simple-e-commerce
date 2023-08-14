package com.rushchanskii.storeapp.orders;

import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rushchanskii.storeapp.catalogue.entities.CatalogueProductItem;
import com.rushchanskii.storeapp.catalogue.repositories.CatalogueCategoryRepository;
import com.rushchanskii.storeapp.catalogue.repositories.CataloguePicturesRepository;
import com.rushchanskii.storeapp.catalogue.repositories.CatalogueProductItemRepository;
import com.rushchanskii.storeapp.catalogue.repositories.CatalogueSubCategoryRepository;
import com.rushchanskii.storeapp.users.UsersRepository;

@Controller
public class OrdersController {

	private CatalogueCategoryRepository catRepository;
	private CatalogueSubCategoryRepository subCatRepository;
	private CatalogueProductItemRepository prodRepository;
	private CataloguePicturesRepository picRepository;
	private UsersRepository usersRepository;

	private OrderDetailsRepository orderDetailsRepository;
	private OrderHeadersRepository orderHeadersRepository;
	private ShoppingCartRepository shoppingCartRepository;

	public OrdersController(CatalogueCategoryRepository catRepository, CatalogueSubCategoryRepository subCatRepository,
			CatalogueProductItemRepository prodRepository, CataloguePicturesRepository picRepository,
			UsersRepository usersRepository, OrderDetailsRepository orderDetailsRepository,
			OrderHeadersRepository orderHeadersRepository, ShoppingCartRepository shoppingCartRepository) {
		super();
		this.catRepository = catRepository;
		this.subCatRepository = subCatRepository;
		this.prodRepository = prodRepository;
		this.picRepository = picRepository;
		this.usersRepository = usersRepository;
		this.orderDetailsRepository = orderDetailsRepository;
		this.orderHeadersRepository = orderHeadersRepository;
		this.shoppingCartRepository = shoppingCartRepository;
	}

	@RequestMapping(value = "/client/addtocart", method = RequestMethod.GET)
	public String addProductItem(@RequestParam Map<String, String> model) {
		// System.out.println(products.toString());
		Long currentlyLoggedIn = getLoggedinUserId();
		ShoppingCart cart;
		CatalogueProductItem products;

		Long productId = Long.parseLong(model.get("productIdCheckbox"));
		cart = new ShoppingCart(0L, currentlyLoggedIn, productId, "",0L,"", 0, 0.0);

		products = prodRepository.findById(productId).stream().toList().get(0);
		cart.setProductName(products.getName());
		cart.setDescription(products.getDescription());
		cart.setCartPrice(products.getPrice());
		cart.setMainPictureId(products.getMainPictureId());

		shoppingCartRepository.save(cart);

		return "redirect:/catalogueFiltered?categoryIdCheckbox=" + products.getCategoryId().toString()
				+ "&subCategoryIdCheckbox=" + products.getSubCategoryId().toString() + "&productIdCheckbox="
				+ productId.toString();

	}
	
	@RequestMapping(value = "/client/shoppingcart", method = RequestMethod.GET)
	public String editShoppingCart(ModelMap model) {
		// System.out.println(products.toString());
		Long currentlyLoggedIn = getLoggedinUserId();
		String currentlyLoggedUsername = getLoggedinUsername();
		List<ShoppingCart> cart;
		boolean isAuthenticated;
		
		
		
		if (getLoggedinUsername().matches("Guest")) {
			isAuthenticated = false;
		} else {
			isAuthenticated = true;
		}
		
		shoppingCartRepository.updateCurrentPriceByUserId(currentlyLoggedIn.toString());
		model.addAttribute("welcomeUser", currentlyLoggedUsername);
		model.addAttribute("isAuthenticated", isAuthenticated);
		cart = shoppingCartRepository.findAllByUserId(currentlyLoggedIn);
		model.addAttribute("shoppingCart",cart);
	
		return "shoppingCart";

	}

	@RequestMapping(value = "/client/deletefromcart", method = RequestMethod.GET)
	public String deleteProductItem(@RequestParam Map<String, String> model) {
		// System.out.println(products.toString());
		Long currentlyLoggedIn = getLoggedinUserId();
		ShoppingCart cart;
		CatalogueProductItem products;

		Long cartId = Long.parseLong(model.get("cartId"));
		Long productId = Long.parseLong(model.get("productIdCheckbox"));

		cart = shoppingCartRepository.findById(cartId).stream().toList().get(0);
		products = prodRepository.findById(productId).stream().toList().get(0);

		Long userfromcart = cart.getUserId();

		if (currentlyLoggedIn.equals(userfromcart)) {
			shoppingCartRepository.deleteById(cart.getId());
		}

		return "redirect:/catalogueFiltered?categoryIdCheckbox=" + products.getCategoryId().toString()
				+ "&subCategoryIdCheckbox=" + products.getSubCategoryId().toString() + "&productIdCheckbox="
				+ productId.toString();

	}
	
	@RequestMapping(value = "/client/updatecart", method = RequestMethod.GET)
	public String updateQuantityinCart(@RequestParam Map<String, String> model) {
		// System.out.println(products.toString());
		Long currentlyLoggedIn = getLoggedinUserId();
		ShoppingCart cart;
		CatalogueProductItem products;

		Long cartId = Long.parseLong(model.get("cartId"));
		Integer quantity = Integer.parseInt(model.get("productQuantity"));
		cart = shoppingCartRepository.findById(cartId).get();
		cart.setQuantity(quantity);

		cart = shoppingCartRepository.findById(cartId).get();

		Long userfromcart = cart.getUserId();

		if (currentlyLoggedIn.equals(userfromcart)) {
			shoppingCartRepository.save(cart);
		}

		return "redirect:/client/shoppingcart";

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

	private Long getLoggedinUserId() {
		String userName = getLoggedinUsername();
		if (userName.matches("Guest")) {
			return 0L;
		} else {
			return usersRepository.findByLoginName(userName).getId();
		}
	}

}
