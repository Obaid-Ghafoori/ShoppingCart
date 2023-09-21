package io.obaid.shoppingcart.service;

import io.obaid.shoppingcart.model.Product;
import io.obaid.shoppingcart.model.ShoppingCartItem;
import io.obaid.shoppingcart.repository.InMemoryShoppingCartRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;



import java.util.List;

/**
 * A service that provides operations for managing shopping carts.
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartService {
    private List<ShoppingCartItem> shoppingCartItemList;
    private InMemoryShoppingCartRepository shoppingCartRepository;
    public ShoppingCartService(List<ShoppingCartItem> shoppingCartItem){
        this.shoppingCartItemList = shoppingCartItem;
        this.shoppingCartRepository = new InMemoryShoppingCartRepository(shoppingCartItemList);
    }
    public ShoppingCartItem addItemToCart(ShoppingCartItem shoppingCartItem) {
        shoppingCartRepository.addItemToCart(shoppingCartItem);
        return shoppingCartItem;
    }

    public void addProduct(Product product) {
        shoppingCartRepository.addProduct(product);
    }
    public void removeItemFromCart(Integer itemId) {
    shoppingCartRepository.removeItemFromCart(itemId);
    }

    public List<ShoppingCartItem> getShoppingCartItems(){
        return shoppingCartRepository.getShoppingCartItems();
    }

    public void editItemInCart(Integer itemId, ShoppingCartItem shoppingCartItem) {
        shoppingCartRepository.editItemInCart(itemId,shoppingCartItem);
    }
}
