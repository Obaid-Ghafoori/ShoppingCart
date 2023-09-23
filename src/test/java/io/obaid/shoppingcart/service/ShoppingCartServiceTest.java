package io.obaid.shoppingcart.service;

import io.obaid.shoppingcart.model.Product;
import io.obaid.shoppingcart.model.ShoppingCartItem;
import io.obaid.shoppingcart.repository.InMemoryShoppingCartRepository;
import io.obaid.shoppingcart.repository.exception.ItemNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceTest {

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    @Mock
    private InMemoryShoppingCartRepository shoppingCartRepository;

    private List<ShoppingCartItem> shoppingCartItems;

    @BeforeEach
    void setUp() {
        shoppingCartItems = new ArrayList<>();
    }

    @Test
    @DisplayName("Add item to the shopping cart, if it is not already exist")
    void addItemToCartIfNotExists() {
        // Arrange
        ShoppingCartItem newItem = new ShoppingCartItem();
        newItem.setId(1);
        newItem.setQuantity(2);
        shoppingCartItems.add(newItem);

        //mock the behaviour
        when(shoppingCartRepository.addItemToCart(newItem)).thenReturn(Optional.of(newItem));

        // Act
        ShoppingCartItem actualResult = shoppingCartService.addItemToCart(newItem);

        // Assert: Verify that the item is added
        assertThat(shoppingCartItems.size()).isEqualTo(1);
        assertThat(actualResult).isEqualTo(newItem);
        assertThat(actualResult.getId()).isEqualTo(1);

        verify(shoppingCartRepository).addItemToCart(newItem);
    }

    @Test
    @DisplayName("Add item to the shopping cart, increment the quantity if the item already exists")
    void addItemToCartIncrementQuantityOfItemIfExists1() {
        // Arrange: Prepare an existing item
        Product product = Product.builder().id(1L).name("Apple").description("abc").price(1.2).quantityInStock(22).category("fruits").build();
        ShoppingCartItem existingItem = new ShoppingCartItem();
        existingItem.setId(1);
        existingItem.setProduct(product);
        existingItem.setQuantity(3);
        shoppingCartItems.add(existingItem);

        // Mock behavior of shoppingCartRepository
        when(shoppingCartRepository.findItemById(1)).thenReturn(Optional.of(existingItem));

        // Act: Add the same item again
        ShoppingCartItem newItem = new ShoppingCartItem();
        newItem.setId(1);
        existingItem.setProduct(product);
        newItem.setQuantity(2);

        ShoppingCartItem actualResult = shoppingCartService.addItemToCart(newItem);

        // Assert: Verify that the item's quantity is incremented
        assertThat(actualResult.getQuantity()).isEqualTo(5);

        // Assert: Verify that the item is not duplicated in the cart after calling the service method
        assertThat(shoppingCartItems).hasSize(1);

        // Verify that shoppingCartRepository methods were called
        verify(shoppingCartRepository).findItemById(1);
    }

    @Test
    @DisplayName("Remove item from the shopping cart, item exists")
    void removeItemFromCartItemFoundTest() {
        // Arrange: Prepare an item
        shoppingCartService = new ShoppingCartService(shoppingCartItems);
        ShoppingCartItem item = new ShoppingCartItem();
        item.setId(1);

        // Act: add item to the cart
        shoppingCartService.addItemToCart(item);

        // Act: remove item from the cart
        Integer actualResult = shoppingCartService.removeItemFromCart(1);

        // Assert: Verify that the item is removed and the correct value is returned
        assertThat(actualResult).isEqualTo(1);
        assertThat(shoppingCartService.getShoppingCartItems()).hasSize(0);
    }

    @Test
    @DisplayName("Throws Item Not Found Exception if the item does not exist in the cart")
    void removeItemFromCartThrowsItemNotFoundExceptionTest() {
        shoppingCartService = new ShoppingCartService();
        // Arrange: Item does not exist
        Integer itemId = 1;

        // Act and Assert: Verify that an ItemNotFoundException is thrown
        assertThatThrownBy(() -> shoppingCartService.removeItemFromCart(itemId))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessage(String.format("Shopping cart item with id %d not found", itemId));

    }


}
