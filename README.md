Loan Interest Accrual Service

1. Overview: 
    A Spring Boot backend service that calculates daily loan interest, stores interest history in MySQL, and exposes REST APIs for manual and automatic interest accrual.
    Interest is calculated using BigDecimal for financial accuracy.
    Supports both scheduled cron job and manual trigger.

    Note: I used MySQL instead of PostgreSQL because I’m currently more familiar with it.
    I will explore PostgreSQL further to match the company’s tech stack.


2. Tech Stack

    Java 17
    Spring Boot
    Spring Web
    Spring Data JPA
    MySQL
    Spring Scheduler (Cron)
    Postman (for API testing)


3. Database Setup (MySQL)

3.1 Create Database
    Run: CREATE DATABASE loanservice;

3.2 Configure Credentials
    In src/main/resources/application.properties, update:

    "spring.datasource.url=jdbc:mysql://localhost:3306/loanservice?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Kolkata
    spring.datasource.username=YOUR_USERNAME
    spring.datasource.password=YOUR_PASSWORD

    spring.jpa.hibernate.ddl-auto=update

    spring.jackson.time-zone=Asia/Kolkata
    spring.jpa.properties.hibernate.jdbc.time_zone=Asia/Kolkata"

    -Replace YOUR_USERNAME and YOUR_PASSWORD with your actual MySQL credentials.

3.3 Insert Test Data

    Use these records to test APIs(can use your own custom data as well):

    INSERT INTO loan (
        customer_name,
        principal_outstanding,
        annual_interest_rate,
        total_interest_accrued,
        created_at,
        updated_at
    ) VALUES ('Ayush', 10000.00, 0.10, 0, NOW(), NOW());

    INSERT INTO loan (
        customer_name,
        principal_outstanding,
        annual_interest_rate,
        total_interest_accrued,
        created_at,
        updated_at
    ) VALUES ('Sejal', 15000.00, 0.10, 0, NOW(), NOW());

    -Tables are auto-created by Hibernate — no need to manually create them.


4. Cron Job Details

    4.1 Cron Expression - 0 11 20 * * ?

    4.2 Runs At - 8:11 PM IST every day

    4.3 What It Does - Calculates previous day’s interest
        -Formula: principal_outstanding × annual_interest_rate / 365

        -Inserts record into interest_history
        -Updates total_interest_accrued
        -Skips calculation if a record already exists for that date


5. API Endpoints

    5.1 Get Loan by ID - GET /api/loans/{loanId}

    5.2 Get Interest History for Loan - GET /api/loans/{loanId}/interest-history

    5.3 Manual Trigger (Calculate Interest) - POST /api/interest/run


Sample Response:

{
  "processed": 1,
  "skipped": 0,
  "message": "Daily interest calculated successfully"
}


6. Postman Collection

    6.1 File Location - postman/Loan Interest Accrual Service.postman_collection.json

    6.2 How to Use

        -Open Postman
        -Click Import
        -Select the JSON file
        -Run any of the pre-configured API requests
        -Collection includes:
            Get Loan by ID
            Get Interest History
            Manual Interest Trigger