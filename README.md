# banking-app-spring-boot
A simple banking application that handles accounts for
customers. It is an MVP REST API and allows for the following:
- Opening accounts (creating new accounts)
- Depositing amount to account - Withdrawing amount from account
- Transferring amount between accounts - Requesting the current balance of account
  
How it works
* To start: run as a spring-boot application.
* An embedded SQL is responsible for persisting data. There is an initializer file in resources/data.sql. It starts automatically when running the application, creates the tables and populates them.
* You can send JSON requests via Postman to the following endpoints to see how it is working:
  * GET http://localhost:8081/api/v1/account : get the list of accounts
  * GET http://localhost:8081/api/v1/account/1 : get account 1
  * POST http://localhost:8081/api/v1/account : add a new account
  * GET http://localhost:8081/api/v1/account/1/balance : request the balance of account 1
  * PUT http://localhost:8081/api/v1/account/1/deposit : deposit money to account 1
  * PUT http://localhost:8081/api/v1/account/1/withdraw : withdraw money from account 1
  * PUT http://localhost:8081/api/v1/account/1/transfer : transfer money from account 1 to a specified account number
