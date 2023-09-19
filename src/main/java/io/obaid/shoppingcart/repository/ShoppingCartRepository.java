package io.obaid.shoppingcart.repository;

import io.obaid.shoppingcart.model.Product;
import io.obaid.shoppingcart.model.ShoppingCartItem;

import java.util.List;

public interface ShoppingCartRepository {
    void addItemToCart(ShoppingCartItem shoppingCartItem);

    void editItemInCart(long itemId, Product product, int quantity);

    void removeItemFromCart(long itemId);

    List<ShoppingCartItem> getShoppingCartItems();
}

