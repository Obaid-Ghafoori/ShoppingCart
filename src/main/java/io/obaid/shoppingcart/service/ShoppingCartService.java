package io.obaid.shoppingcart.service;

import io.obaid.shoppingcart.model.Product;
import io.obaid.shoppingcart.model.ShoppingCartItem;
import io.obaid.shoppingcart.repository.InMemoryShoppingCartRepository;
import io.obaid.shoppingcart.repository.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A service that provides operations for managing shopping carts.
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ShoppingCartService {
    private List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();
    private InMemoryShoppingCartRepository shoppingCartRepository;
    public ShoppingCartService(List<ShoppingCartItem> shoppingCartItems) {
            this.shoppingCartItemList = shoppingCartItems;
            this.shoppingCartRepository = new InMemoryShoppingCartRepository(shoppingCartItemList);

    }
    public ShoppingCartItem addItemToCart(ShoppingCartItem shoppingCartItem) {
        Optional<ShoppingCartItem> existingItem = shoppingCartRepository.findItemById(shoppingCartItem.getId());

        if (existingItem.isPresent()) {
            ShoppingCartItem currentItem = existingItem.get();
            currentItem.setQuantity(currentItem.getQuantity() + shoppingCartItem.getQuantity());
            return currentItem;
        } else {
            shoppingCartRepository.addItemToCart(shoppingCartItem);
            return shoppingCartItem;
        }
    }


    public void addProduct(Product product) {
        shoppingCartRepository.addProduct(product);
    }

    public Integer removeItemFromCart(Integer itemId) throws ItemNotFoundException{
        ShoppingCartItem shoppingCartItem = shoppingCartItemList.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElse(null);

        if (shoppingCartItem == null) {
            throw new ItemNotFoundException(String.format("Shopping cart item with id %d not found", itemId));
        }

        return shoppingCartRepository.removeItemFromCart(shoppingCartItem.getId());
    }

    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartRepository.getShoppingCartItems();
    }

    public void editItemInCart(Integer itemId, ShoppingCartItem shoppingCartItem) {
        shoppingCartRepository.editItemInCart(itemId, shoppingCartItem);
    }
}
