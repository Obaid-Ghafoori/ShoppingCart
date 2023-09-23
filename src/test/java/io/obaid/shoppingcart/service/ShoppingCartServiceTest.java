package io.obaid.shoppingcart.service;//package io.obaid.shoppingcart.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.obaid.shoppingcart.model.Product;
import io.obaid.shoppingcart.model.ShoppingCartItem;
import io.obaid.shoppingcart.repository.InMemoryShoppingCartRepository;
import io.obaid.shoppingcart.repository.ShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    private List<ShoppingCartItem> shoppingCartItems;

    @BeforeEach
    void setUp() {
        shoppingCartItems = new ArrayList<>();
        shoppingCartRepository = new InMemoryShoppingCartRepository(shoppingCartItems);
    }

    @Test
    @DisplayName("Add item to the shopping cart, increment the quantity if the item already exists")
    void addItemToCartIncrementQuantityOfItemIfExists() {
        // Arrange: Prepare an existing item
        Product product = Product.builder().id(1l).name("Apple").description("abc").price(1.2).quantityInStock(22).category("fruits").build();
        ShoppingCartItem item = new ShoppingCartItem();

        item.setId(1);
        item.setProduct(product);
        item.setQuantity(3);
        item.setTotalCost(2.33);
        shoppingCartItems.add(item);

        // Act: Add the same item again
        ShoppingCartItem existingItem = new ShoppingCartItem();
        existingItem.setId(1);
        existingItem.setQuantity(2);

        Optional<ShoppingCartItem> result = shoppingCartRepository.addItemToCart(existingItem);

        // Assert: Verify that the item's quantity is incremented
        assertThat(result).isPresent();
        assertThat(result.get().getQuantity()).isEqualTo(5);

        // Assert: Verify that the item is not duplicated in the cart
        assertThat(shoppingCartItems.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Add item to the shopping cart, add the item if it doesn't exist")
    void addItemToCartAddItemIfNotExists() {
        // Act: Add a new item
        ShoppingCartItem newItem = new ShoppingCartItem();
        newItem.setId(1);
        newItem.setQuantity(2);

        Optional<ShoppingCartItem> result = shoppingCartRepository.addItemToCart(newItem);

        // Assert: Verify that the item is added
        assertThat(result).isEmpty(); // An empty Optional should be returned
        assertThat(shoppingCartItems.size()).isEqualTo(1);
        assertThat(shoppingCartItems.get(0)).isEqualTo(newItem);
    }
}