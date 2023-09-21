package io.obaid.shoppingcart.repository;

import io.obaid.shoppingcart.model.Product;
import io.obaid.shoppingcart.model.ShoppingCartItem;
import io.obaid.shoppingcart.repository.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * An implementation of the `ShoppingCartRepository` interface that uses a `List` to store the shopping cart items in memory.
 */
@AllArgsConstructor
public class InMemoryShoppingCartRepository implements ShoppingCartRepository {
    private List<ShoppingCartItem> shoppingCarItems = new ArrayList<>();

    /**
     * Adds a new shopping cart item to the repository if the item is not already added.
     * otherwise the quantity of the item will be incremented
     *
     * @param shoppingCartItem the shopping cart item to add
     * @return
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
     * @throws @link{ItemNotFoundException} if the item could not be found
     */
    @Override
    public void editItemInCart(Integer itemId, ShoppingCartItem shoppingCartItem) {
        Optional<ShoppingCartItem> itemInShoppingCart = findItemById(itemId);
        if (itemInShoppingCart.isEmpty()) {
            throw new ItemNotFoundException("Shopping cart item not found");
        }

        itemInShoppingCart.get().setProduct(shoppingCartItem.getProduct());
        itemInShoppingCart.get().setQuantity(shoppingCartItem.getQuantity());

    }

    /**
     * Removes a shopping cart item from the repository.
     *
     * @param itemId the ID of the shopping cart item to remove
     */
    @Override
    public void removeItemFromCart(Integer itemId) {
        boolean removed = shoppingCarItems.removeIf(item -> item.getId().equals(itemId));

        if (!removed) {
            throw new ItemNotFoundException("Shopping cart item not found");
        }
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
