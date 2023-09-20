package io.obaid.shoppingcart.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * Represents a shopping cart item.
 */
@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItem {
    private Integer id;
    private Product product;
    private Integer quantity;

    /**
     * The total cost of the shopping cart item, including any discounts.
     */
    private Double totalCost;

}

