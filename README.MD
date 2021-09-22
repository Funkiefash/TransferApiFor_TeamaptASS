# TransferAPIForTeamAss
Simple Spring boot application which provide RESTful API for money transfer

### Prerequisite
- Maven
- JDK 1.8+
- IDE (ECLIPSE, INTELLIJ IDEA, NETBEANS)
### Project Structure
```bash
TransferAPIForTeamAss
├── src
│   ├── main
│   │   ├── java\com\example\demo
│   │   └── resources
│   └── test
│       ├── java\com\example\demo
│       └── resources
├── LICENSE
├── .gitignore
├── pom.xml
└── README.md
```
### Packaging
```
mvn package
```
### Test
```
mvn test
```
### Running
```
java -jar demo-0.0.1-SNAPSHOT.jar
```
### Dependencies
```
To install Ojdbc8.jar file to POM
  i. Download from the oracle website ojdbc8.jar
  ii.Add this to the Pom.xml file
  <dependency>
			<groupId>com.oracle</groupId>
			<artifactId>ojdbc</artifactId>
			<version>8</version>
			<scope>system</scope>
			<systemPath>C:/Users/USER/Downloads/ojdbc8.jar</systemPath>
		</dependency>
    iii. System path is according to the location of file on your Pc.
```
### SpringBoot Version
```
2.6.0-SNAPSHOT
```
### Data
```
Initial data (src\main\resources\TransferAPI.sql) will be loaded in the H2 database when application start.
```
## Feature
This application is for demo only. It provides APIs for
### Swagger-UI
```
API Specification is provided in the [swagger-ui page](http://localhost:8080/swagger-ui.html) after spring boot application start.
```
http://localhost:8080/swagger-ui.html

### Library Used
```
| Library | Usage |
| --- | --- |
| spring boot | for spring boot application |
| spring data jpa | for ORM and DB operation purpose |
| H2 | lightweight database for demo purpose |
| springfox swagger | generate swagger API specification from code |
| springfox swagger ui | generate swagger ui page for specification |
| httpClient | configurate http request |
| spring oauth2 security| config for security authorization and authentication.
```
### TASK
1. How to build a restful service with spring-boot: https://spring.io/guides/gs/rest-service/ Under this tutorial you will see links to other aspects too.
2. How to connect your service to a database: https://spring.io/guides/gs/accessing-data-mysql/
3. Simple authentication using spring security: https://spring.io/guides/gs/securing-web/ , https://spring.io/guides/tutorials/spring-security-and-angular-js/

To bring it all together, try to use spring boot to solve the problem below:

Imagine you are asked to develop a transfer service with APIs to transfer money between two accounts
Your application is expected to have the following database structure

TABLE transactions
  - reference (unique)
  - amount
  - account nr

TABLE balances
  - account nr (unique)
  - balance

The transaction table registers any transaction in an account (ie. today I paid N2000 for a movie with my card), the balances table represents the account balance of customers (ie. I have N50k in my bank account).

Assume that the database is a relational database like MYSQL

You are expected to have a near-production-ready code.

The API you are to develop should be able to handle a transfer request of the form {from: account, to: account, amount: money} and updates the transactions/balances table accordingly.

A few things to keep in mind:

you will receive requests from a mobile app, that customers use to transfer money around
can you deal with the fact that one customer might tap on the "transfer" button twice, by mistake?
what happens if your database becomes unavailable in the middle of your logic?
what happens if 2 users (A, B) transfer money to user C at the same time?
how will you handle transaction validity and consistency
the only thing you will not need to validate is that the user is authorized to perform this API request. let's assume the user is logged in and carries a cookie, with the transfer request, that ensures it comes from a legit source
