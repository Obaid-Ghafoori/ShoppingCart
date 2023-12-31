package io.obaid.shoppingcart.repository;

import io.obaid.shoppingcart.model.Product;
import io.obaid.shoppingcart.model.ShoppingCartItem;
import io.obaid.shoppingcart.repository.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * An implementation of the `ShoppingCartRepository` interface that uses a `List` to store the shopping cart items in memory.
 */
@AllArgsConstructor
@NoArgsConstructor
public class InMemoryShoppingCartRepository implements ShoppingCartRepository {
    private List<ShoppingCartItem> shoppingCarItems = new ArrayList<>();

    /**
     * Adds a new shopping cart item to the repository if the item is not already added.
     * otherwise the quantity of the item will be incremented
     *
     * @param shoppingCartItem the shopping cart item to add
     * @return shoppingCartItem with the correct quantity
     */
    @Override
    public Optional<ShoppingCartItem> addItemToCart(ShoppingCartItem shoppingCartItem) {
        Optional<ShoppingCartItem> existingShoppingCartItem = findItemById(shoppingCartItem.getId());

        if (existingShoppingCartItem.isPresent()) {
            existingShoppingCartItem.get().setQuantity(existingShoppingCartItem.get().getQuantity() + shoppingCartItem.getQuantity());
        } else {
            shoppingCarItems.add(shoppingCartItem);
        }
        return existingShoppingCartItem;
    }

    /**
     * @param product a provided product to be added to the stock
     * @return @link{Product}
     */
    @Override
    public Product addProduct(Product product) {
        Product productToAdd = new Product();
        productToAdd.setId(product.getId());
        productToAdd.setName(product.getName());
        productToAdd.setDescription(product.getDescription());
        productToAdd.setPrice(productToAdd.getPrice());
        productToAdd.setQuantityInStock(product.getQuantityInStock());
        productToAdd.setCategory(product.getCategory());

        return productToAdd;
    }


    /**
     * Edits an existing shopping cart item in the repository.
     *
     * @param itemId           the ID of the shopping cart item to edit
     * @param shoppingCartItem the item in the shopping cart
     * @throws ItemNotFoundException if the item could not be found
     */
    @Override
    public void editItemInCart(Integer itemId, ShoppingCartItem shoppingCartItem) {
        Optional<ShoppingCartItem> itemInShoppingCart = findItemById(itemId);
        if (itemInShoppingCart.isEmpty()) {
            throw new ItemNotFoundException(String.format("Shopping cart item with id %d not found", itemId));
        }

        itemInShoppingCart.get().setId(shoppingCartItem.getId());
        itemInShoppingCart.get().setProduct(shoppingCartItem.getProduct());
        itemInShoppingCart.get().setQuantity(shoppingCartItem.getQuantity());
        itemInShoppingCart.get().setTotalCost(shoppingCartItem.getTotalCost());

    }

    /**
     * Removes a shopping cart item from the repository.
     *
     * @param itemId the ID of the shopping cart item to remove
     * @return deleted item id
     */
    @Override
    public Integer removeItemFromCart(Integer itemId) {
        boolean removed = shoppingCarItems.removeIf(item -> item.getId().equals(itemId));
        if (!removed) {
            throw new ItemNotFoundException(String.format("Shopping cart item with id %d not found", itemId));
        }
        return itemId;
    }

    /**
     * @param itemId the ID of the shopping cart item to find.
     * @return @link{ShoppingCartItem} if the item exist, otherwise return null
     */
    @Override
    public Optional<ShoppingCartItem> findItemById(Integer itemId) {
        return shoppingCarItems.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();
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
