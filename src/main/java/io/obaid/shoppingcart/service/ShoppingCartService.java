package io.obaid.shoppingcart.service;

import io.obaid.shoppingcart.model.Product;
import io.obaid.shoppingcart.model.ShoppingCartItem;
import io.obaid.shoppingcart.repository.InMemoryShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * A service that provides operations for managing shopping carts.
 */
@Service
@AllArgsConstructor
public class ShoppingCartService {
    private List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();
    private InMemoryShoppingCartRepository shoppingCartRepository = new InMemoryShoppingCartRepository(shoppingCartItemList);


    public void addItemToCart(ShoppingCartItem shoppingCartItem) {
        shoppingCartRepository.addItemToCart(shoppingCartItem);
    }

    public void editItemInCart(long itemId, Product product, int quantity){
        shoppingCartRepository.editItemInCart(itemId,product,quantity);
    }

    public void removeItemFromCart(long itemId) {
    shoppingCartRepository.removeItemFromCart(itemId);
    }

    public List<ShoppingCartItem> getShoppingCartItems(){
        return shoppingCartRepository.getShoppingCartItems();
    }
}
