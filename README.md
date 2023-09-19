
# ShoppingCart

This is a simple shopping cart Spring Boot application that allows users to add, edit, remove, and view items in their cart. The application does not require a database connection, so all data is stored in memory.

## Requirements

- Java 17
- Maven
- Lombok

## Unit Testing

To run the unit tests, execute the following command:

```bash
mvn test
```

## Usage

To start the application, run the following command:

```bash
mvn spring-boot:run
```

The REST API endpoints are accessible at the following URL:
[http://localhost:8080/api/shopping-cart](http://localhost:8080/api/shopping-cart)

### Example Endpoints

- **Add item to cart:**
    - POST `/items`
    - Request Body:
      ```json
      {
        "productId": 1,
        "quantity": 2
      }
      ```

- **Edit item in cart:**
    - PUT `/items/{itemId}`
    - Request Body:
      ```json
      {
        "productId": 1,
        "quantity": 3
      }
      ```

- **Remove item from cart:**
    - DELETE `/items/{itemId}`

- **View items in cart:**
    - GET `/items`

## License

This project is licensed under the MIT License.