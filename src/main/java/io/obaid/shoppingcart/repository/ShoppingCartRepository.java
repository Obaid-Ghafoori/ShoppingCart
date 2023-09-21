package io.obaid.shoppingcart.repository;

import io.obaid.shoppingcart.model.Product;
import io.obaid.shoppingcart.model.ShoppingCartItem;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository {
    Optional<ShoppingCartItem> addItemToCart(ShoppingCartItem shoppingCartItem);
    Product addProduct(Product product);

    void editItemInCart(Integer itemId, ShoppingCartItem shoppingCartItem);

    void removeItemFromCart(Integer itemId);
    Optional<ShoppingCartItem> findItemById(Integer itemId);

    List<ShoppingCartItem> getShoppingCartItems();
}

