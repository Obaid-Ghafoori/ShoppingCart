package io.obaid.shoppingcart.controller;

import io.obaid.shoppingcart.model.Product;
import io.obaid.shoppingcart.model.ShoppingCartItem;
import io.obaid.shoppingcart.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A controller that handles user requests to manage shopping carts.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("api/shopping-cart")
public class ShoppingCartController {

    private List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();
    private final ShoppingCartService shoppingCartService = new ShoppingCartService(shoppingCartItemList);


    /**
     * Adds an item to the shopping cart.
     *
     * @param shoppingCartItem the shopping cart item to add
     * @return a redirect to the shopping cart page
     */
    @PostMapping("/cart/add-item")
    public ShoppingCartItem addItemToCart(@RequestBody ShoppingCartItem shoppingCartItem) {
        ShoppingCartItem updatedItem = shoppingCartService.addItemToCart(shoppingCartItem);
        System.out.println(updatedItem);
        return updatedItem;
    }

    /**
     * Adds an item to the shopping cart.
     *
     * @param product the shopping cart item to add
     * @return a redirect to the shopping cart page
     */
    @PostMapping("/stock/add-product")
    public Product addProduct(@RequestBody Product product) {
        shoppingCartService.addProduct(product);
        return product;
    }

    /**
     * Edits an item in the shopping cart.
     *
     * @param itemId the ID of the item to edit
     * @param shoppingCartItem the updated shopping cart item
     * @return a redirect to the shopping cart page
     */
    @PutMapping("/cart/{itemId}")
    public String editItemInCart(@PathVariable long itemId, @RequestBody ShoppingCartItem shoppingCartItem) {
        shoppingCartService.editItemInCart(itemId, shoppingCartItem);
        return "redirect:/cart";
    }

    /**
     * Removes an item from the shopping cart.
     *
     * @param itemId the ID of the item to remove
     * @return a redirect to the shopping cart page
     */
    @DeleteMapping("/cart/{itemId}")
    public String removeItemFromCart(@PathVariable long itemId) {
        shoppingCartService.removeItemFromCart(itemId);
        return "redirect:/cart";
    }

    /**
     * Gets all the items in the shopping cart.
     *
     * @return the shopping cart page
     */
    @GetMapping("/cart")
    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartService.getShoppingCartItems();
    }
    /**
     *
     * @return Welcome message
     */
    @GetMapping("/welcome")
    public String getWelcome() {
        return "Welcome to Shopping cart";
    }
}

