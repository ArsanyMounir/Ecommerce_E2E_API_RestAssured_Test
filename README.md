# E-Commerce API Automation Testing (REST Assured + POJOs)

This project is a simple **end-to-end (E2E)** automation test suite built using **Java, REST Assured**, and **TestNG**. It simulates a user journey in an e-commerce system through API calls, including login, product creation, ordering, and deletion.

## 📌 Test Flow Overview

1. **Login** to get an authentication token.
2. **Add Product** using the authenticated token.
3. **Create Order** on the added product.
4. **Delete Product** and assert that it's deleted successfully.

## 🛠️ Technologies Used

- Java
- REST Assured
- TestNG
- POJO classes for request/response mapping
- JSONPath (for parsing responses)

## 📁 Project Structure

```
src
├── main
│   └── java
│       
└── test
    └── java
        └── EcommerceTest.java
        └── pojo
          ├── LoginRequest.java
          ├── LoginResponse.java
          ├── OrderDetail.java
          └── Orders.java
```

### POJO Classes

- `LoginRequest`: Maps login request payload.
- `LoginResponse`: Maps login response to extract token and userId.
- `OrderDetail`: Represents individual order detail.
- `Orders`: A wrapper for sending multiple orders in one payload.

## ▶️ How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/ecommerce-api-testing.git
   cd ecommerce-api-testing
   ```

2. Import the project into your preferred Java IDE (IntelliJ, Eclipse, etc.).

3. Make sure the dependencies (REST Assured, TestNG) are added via Maven or manually.

4. Replace the image file path (`productImage`) with an actual file path on your machine (if needed).

5. Run the `EcommerceTest.java` as a TestNG test.

## 📌 Sample Credentials

- **Email**: `arshyashraf@gmail.com`
- **Password**: `aA@123456`


## ✅ Sample Assertions

- Product is successfully deleted (validated using message assertion).
- API status codes and responses can be extended for more validations.



---

If you find this helpful, feel free to ⭐️ this repo!
