# PriceVision – Smart Product Price Tracking and Prediction System

### Description

PriceVision is a web-based application designed to demonstrate how product details from e-commerce platforms can be stored and how future price trends can be predicted using machine learning techniques. The system separates product onboarding and price prediction workflows, enabling effective demonstration of database storage and ML-based forecasting without requiring real-time data collection.

### About

PriceVision is a smart price tracking and prediction system developed using Java Spring Boot, MySQL, and a Java-based Machine Learning model. The project addresses a key limitation of real-world price tracking systems—time dependency—by allowing pre-populated historical price data to be used for prediction demonstrations.

### The application consists of two major flows:

- Product Onboarding Flow – where users paste an e-commerce product URL (Amazon/Flipkart), preview product details, and store them in a database.

- Price Prediction Flow – where historical price data (manually populated in the database) is used to predict product prices for the next seven days using Linear Regression.

- This modular design ensures clarity, scalability, and practical demonstration of both backend persistence and machine learning concepts.

### Features

- Product onboarding using e-commerce URLs (Amazon / Flipkart).

- Automatic extraction of product metadata (name, platform, image).

- Secure storage of product details in MySQL database.

- Manual historical price data support for ML training.

- Java-based Linear Regression for price prediction.

- Prediction of next 7 days’ prices.

- RESTful API-based backend architecture.

- Single-page frontend with tab-based navigation.

- Clean separation of data storage and prediction modules.

- High scalability and maintainability.

### Requirements
- Operating System

- Windows 10 / Windows 11 (64-bit)

- Ubuntu 20.04 or later (64-bit)

### Development Environment

- Java JDK 21 or later

- Maven 3.8+

### Backend Framework

- Spring Boot 3.x

- Spring Data JPA

- Hibernate ORM

### Database

- MySQL 8.x

### Frontend Technologies

- HTML5

- Tailwind CSS

- JavaScript (Fetch API)

### Machine Learning

- Linear Regression implemented in Java (no Python dependency)

### Libraries & Tools

- Jsoup (HTML parsing)

- HikariCP (connection pooling)

- Git (version control)

- IDE

- IntelliJ IDEA / Eclipse / VS Code

### System Architecture

   Architecture Overview:
```
Frontend (HTML + JS)
        |
        | REST API
        |
Spring Boot Backend
        |
        | JPA / Hibernate
        |
MySQL Database
        |
        | Historical Price Data
        |
Linear Regression Prediction Engine
```

(Insert system architecture diagram screenshot here if required)

### Output
- Output 1 – Product Onboarding

  - User pastes product URL.

 -  Product details are fetched and displayed.

 - Product information is stored in the database.


### Output 2 – Price Prediction

- User enters product ID (with pre-populated historical data).

  - System predicts prices for the next 7 days.

  - Results are displayed dynamically on the frontend.


### Prediction Accuracy

- Prediction accuracy depends on the quality and consistency of historical price data.

- Linear Regression provides effective trend-based forecasting for demonstration purposes.



### Results and Impact

PriceVision demonstrates a realistic approach to building a price prediction system within academic constraints. By separating product onboarding and prediction workflows, the project successfully simulates real-world data pipelines while avoiding the impracticality of long-term live data collection.

### The project highlights:

- Practical application of machine learning without Python dependency.

- Clean backend–frontend integration.

- Scalable and modular system design.

- Strong foundation for future enhancements such as live APIs, graph visualization, and automated schedulers.

### Future Enhancements

- Integration with official e-commerce affiliate APIs.

- Automated daily price collection using schedulers.

- Visualization of price trends using charts.

- Support for multiple prediction algorithms.

- User authentication and personalized dashboards.

### Articles Published / References

T. Hastie, R. Tibshirani, J. Friedman, The Elements of Statistical Learning, Springer, 2009.

I. Goodfellow, Y. Bengio, A. Courville, Deep Learning, MIT Press, 2016.

Spring Boot Documentation – https://spring.io/projects/spring-boot

MySQL Documentation – https://dev.mysql.com/doc/
