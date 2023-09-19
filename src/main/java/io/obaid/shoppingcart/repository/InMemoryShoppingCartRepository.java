package io.obaid.shoppingcart.repository;

import io.obaid.shoppingcart.model.Product;
import io.obaid.shoppingcart.model.ShoppingCartItem;
import io.obaid.shoppingcart.repository.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A simple implementation of the `ShoppingCartRepository` interface that uses a `List` to store the shopping cart items in memory.
 */
@AllArgsConstructor
public class InMemoryShoppingCartRepository implements ShoppingCartRepository {
    private List<ShoppingCartItem> shoppingCarItems = new ArrayList<>();

    /**
     * Adds a new shopping cart item to the repository if the item is not already added.
     * otherwise the quantity of the item will be incremented
     *
     * @param shoppingCartItem the shopping cart item to add
     */
    @Override
    public void addItemToCart(ShoppingCartItem shoppingCartItem) {
        Optional<ShoppingCartItem> existingShoppingCartItem = findItemById(shoppingCartItem.getId());
        if (!existingShoppingCartItem.isPresent()) {
            existingShoppingCartItem.get().setQuantity(existingShoppingCartItem.get().getQuantity() + shoppingCartItem.getQuantity());
        } else {
            shoppingCarItems.add(shoppingCartItem);
        }
    }

    /**
     * Edits an existing shopping cart item in the repository.
     *
     * @param itemId   the ID of the shopping cart item to edit
     * @param product  the new product for the shopping cart item
     * @param quantity the new quantity for the shopping cart item
     * @throws @link{ItemNotFoundException} if the item could not be found
     */
    @Override
    public void editItemInCart(long itemId, Product product, int quantity) {
        Optional<ShoppingCartItem> itemInShoppingCart = findItemById(itemId);
        if (itemInShoppingCart.isEmpty()) {
            throw new ItemNotFoundException("Shopping cart item not found");
        }

        itemInShoppingCart.get().setProduct(product);
        itemInShoppingCart.get().setQuantity(quantity);

    }

    /**
     * Removes a shopping cart item from the repository.
     *
     * @param itemId the ID of the shopping cart item to remove
     */
    @Override
    public void removeItemFromCart(long itemId) {
        Optional<ShoppingCartItem> shoppingCartItem = findItemById(itemId);
        if (shoppingCartItem == null) {
            throw new ItemNotFoundException("Shopping cart item not found");
        }

        shoppingCarItems.remove(shoppingCartItem);
    }

    /**
     * @param itemId the ID of the shopping cart item to find.
     * @return @link{ShoppingCartItem} if the item exist, otherwise return null
     */
    @Override
    public Optional<ShoppingCartItem> findItemById(long itemId) {
        return Optional.ofNullable(shoppingCarItems.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElse(null));
    }

    /**
     * Gets all the shopping cart items in the repository.
     *
     * @return a list of shopping cart items
     */
    @Override
    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCarItems;
    }
}
