package io.obaid.shoppingcart;

import io.obaid.shoppingcart.model.Product;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class ProductDataGenerator {

    private static final List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Generate and insert 50 different types of products
        for (int i = 1; i <= 50; i++) {
            Product product = new Product();
            product.setId((long) i);
            product.setName("Product " + i);
            product.setDescription("Description for Product " + i);
            product.setPrice(10.0 * i); // Set a different price for each product
            product.setQuantityInStock(100); // Set an initial quantity
            product.setCategory("Category " + (i % 5)); // Divide into 5 categories
            System.out.println();

            // Add the product to the in-memory repository
            products.add(product);
        }
    }

    public static List<Product> getAllProducts() {
        return products;
    }
}

