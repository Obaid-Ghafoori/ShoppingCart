package io.obaid.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Represents a shopping cart item.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItem {
    private Long id;
    private Product product;
    private Integer quantity;

    /**
     * The total cost of the shopping cart item, including any discounts.
     */
    private BigDecimal totalCost;

}

