package io.obaid.shoppingcart;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.obaid.shoppingcart.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;


@SpringBootApplication
public class ShoppingcartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingcartApplication.class, args);

        List<Product> allProducts = ProductDataGenerator.getAllProducts();
        // Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Enable pretty printing for better readability (optional)
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // Convert the list of products to JSON
        try {
            String productsJson = objectMapper.writeValueAsString(allProducts);
            System.out.println();
            System.out.println("----------------------------------------------------");
            System.out.println(productsJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
