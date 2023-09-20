package io.obaid.shoppingcart.repository;

import io.obaid.shoppingcart.model.Product;
import io.obaid.shoppingcart.model.ShoppingCartItem;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository {
    ShoppingCartItem addItemToCart(ShoppingCartItem shoppingCartItem);
    Product addProduct(Product product);

    void editItemInCart(long itemId, ShoppingCartItem shoppingCartItem);

    void removeItemFromCart(long itemId);
    Optional<ShoppingCartItem> findItemById(long itemId);

    List<ShoppingCartItem> getShoppingCartItems();
}

