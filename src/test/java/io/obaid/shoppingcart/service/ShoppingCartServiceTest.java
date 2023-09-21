package io.obaid.shoppingcart.service;

import io.obaid.shoppingcart.model.Product;
import io.obaid.shoppingcart.model.ShoppingCartItem;
import io.obaid.shoppingcart.repository.ShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class ShoppingCartServiceTest {

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("add item to the shopping cart, increment the quantity in case the item is already in the cart")
    void testAddItemToCart() {
        // arrange
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        Product product = Product.builder()
                .id(1l)
                .name("Apple")
                .description("abc")
                .price(1.2)
                .quantityInStock(22)
                .category("fruits")
                .build();
        shoppingCartItem.setId(123);
        shoppingCartItem.setProduct(product);
        shoppingCartItem.setQuantity(4);

        // mock behaviour
        when(shoppingCartRepository.addItemToCart(shoppingCartItem)).thenReturn(Optional.of(shoppingCartItem));

        // act
        Optional<ShoppingCartItem> result = shoppingCartRepository.addItemToCart(shoppingCartItem);

        // assert
        assertThat(result).isPresent();
        assertThat(result).isNotEqualTo(shoppingCartItem);

        verify(shoppingCartRepository).addItemToCart(shoppingCartItem);
    }

    @Test
    void addProduct() {
    }

    @Test
    void removeItemFromCart() {
    }

    @Test
    void getShoppingCartItems() {
    }

    @Test
    void editItemInCart() {
    }
}