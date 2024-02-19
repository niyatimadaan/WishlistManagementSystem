# Wishlist Management Application

This project is a Spring Boot application that provides a RESTful API for managing wishlists. It includes features for adding items to a wishlist, retrieving a list of wishlists, and removing items from a wishlist. The application also includes authentication and registration features.

## Assumptions

- The application currently only supports adding product names to the wishlists table. To enhance this, a new table for product categories will be added, and this table will be linked to the wishlists table via a product ID.
- Users are required to sign up before accessing wishlists.
  Each user can have multiple wishlists, and wishlist items are unique per user.

## Setup

1. **Prerequisites**: Ensure you have Java  17 installed on your machine.

2. **Database Setup**: The application uses PostgreSQL as its database. Make sure you have PostgreSQL installed and running.

3. **Application Configuration**: Update the `application.properties` file with the correct database connection details:

   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
   spring.datasource.username=your_postgresql_username
   spring.datasource.password=your_postgresql_password
   ```

   Replace the `username` and `password` with your PostgreSQL credentials.

4. **Clone Repository**: Use the following git command to clone the application:

   ```
   git clone https://github.com/niyatimadaan/WishlistManagementSystem.git
   ```

5. **Building the Application**: Use the following Maven command to build the application:

   ```
   mvn clean install
   ```

6. **Running the Application**: Once the build is successful, navigate to the project's root directory and run:

   ```
   mvn spring-boot:run
   ```

   The application will start, and you can access it at `http://localhost:8081`.

## Running the Application

After setting up and starting the application, you can interact with it using a REST client like Postman or curl. The application exposes the following endpoints:

- `POST /api/wishlists`: Add an item to the wishlist.
- `GET /api/wishlists`: Retrieve the list of wishlists.
- `DELETE /api/wishlists`: Remove an item from the wishlist by its ID.
- `POST /register`: Register a new user.
- `POST /login`: Authenticate a user and log them in.

## Testing the Application

To run the unit tests, use the following Maven command:

```
mvn test
```

This will execute all the test cases in the `wishlistManagementApplicationTests` package.


## Documentation

This project uses Swagger UI for API documentation. Swagger UI dynamically generates beautiful, interactive documentation from a Swagger-compliant API specification. This helps developers understand and interact with the API's endpoints, request parameters, response formats, and more.

To view the API documentation, run the application and navigate to the following URL in your web browser:

http://localhost:8080/swagger-ui/index.html

Replace `8080` with the port number on which your application is running if it's different.

## Authentication

To access the wishlist endpoints, you must include an authorization token in the header of your HTTP requests. This token is used to authenticate and authorize the user making the request.

### Obtaining an Authorization Token

1. **Register or Login**: To obtain an authorization token, you must first register a new user or log in to an existing account.

2. **Token Issuance**: Upon successful registration or login, the server will issue an authorization token. This token should be stored securely and included in all subsequent requests to the wishlist endpoints.

### Including the Authorization Token in Requests

When making requests to the wishlist endpoints, include the authorization token in the `Authorization` header of your HTTP requests. The header should be in the format `Authorization: Bearer YOUR_TOKEN_HERE`, where `YOUR_TOKEN_HERE` is the token you received upon registration or login.

Here's an example of how to include the authorization token in a cURL request:



## Future Enhancements

- Add a product categories table and link it to the wishlists table via a product ID to support more detailed wishlist items.
- Implement a front-end interface for users to interact with the wishlist management system.

## Contributing

Contributions are welcome! Please submit a pull request with your proposed changes.
