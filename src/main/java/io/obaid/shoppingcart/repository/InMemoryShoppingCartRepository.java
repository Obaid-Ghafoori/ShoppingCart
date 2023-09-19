package io.obaid.shoppingcart.repository;

import io.obaid.shoppingcart.model.Product;
import io.obaid.shoppingcart.model.ShoppingCartItem;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the `ShoppingCartRepository` interface that uses a `List` to store the shopping cart items in memory.
 */
@AllArgsConstructor
public class InMemoryShoppingCartRepository implements ShoppingCartRepository {
    private List<ShoppingCartItem> items = new ArrayList<>();

    /**
     * Adds a new shopping cart item to the repository if the item is not already added.
     * otherwise the quantity of the item will be incremented
     *
     * @param shoppingCartItem the shopping cart item to add
     */
    @Override
    public void addItemToCart(ShoppingCartItem shoppingCartItem) {
        ShoppingCartItem existingShoppingCartItem = items.stream()
                .filter(item -> item.getId().equals(shoppingCartItem.getId()))
                .findFirst()
                .orElse(null);
        if (existingShoppingCartItem != null) {
            existingShoppingCartItem.setQuantity(existingShoppingCartItem.getQuantity() + shoppingCartItem.getQuantity());
        } else {
            items.add(shoppingCartItem);
        }
    }

    @Override
    public void editItemInCart(long itemId, Product product, int quantity) {

    }

    @Override
    public void removeItemFromCart(long itemId) {

    }

    @Override
    public List<ShoppingCartItem> getShoppingCartItems() {
        return null;
    }
}
