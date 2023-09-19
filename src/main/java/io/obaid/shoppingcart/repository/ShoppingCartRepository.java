package io.obaid.shoppingcart.repository;

import io.obaid.shoppingcart.model.Product;
import io.obaid.shoppingcart.model.ShoppingCartItem;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository {
    void addItemToCart(ShoppingCartItem shoppingCartItem);

    void editItemInCart(long itemId, Product product, int quantity) throws ClassNotFoundException;

    void removeItemFromCart(long itemId);
    Optional<ShoppingCartItem> findItemById(long itemId);

    List<ShoppingCartItem> getShoppingCartItems();
}

