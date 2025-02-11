
# Student Course Management Application
## SQL Part
This is a Spring Boot web application that allows you to manage a catalog of students and the courses they are enrolled in. It uses MariaDB as the relational database for student data. The application offers features for adding, updating, and deleting student records.

## Prerequisites

Before running the application, make sure you have the following installed:

- **Java 17** or later
- **Docker** for MariaDB container setup

## Installation
### Set up the MariaDB Database with Docker

This project includes a `dockerfile` to set up a MariaDB container for the database. Follow these steps:

1. **Create the dockerfile**: The dockerfile contains the necessary configurations for the MariaDB container. Here’s an example of the dockerfile:

```dockerfile
FROM mariadb:latest
ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_DATABASE=pos
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=password
EXPOSE 3306
```

2. **Build the MariaDB Docker image**: After creating the dockerfile, navigate to the folder containing the file and run the following command to build the image:

```bash
docker build -t mariadb-student-catalog .
```
This will build the Docker image using the provided `dockerfile`.

3. **Run the MariaDB container**: Once the image is built, run the image in the Docker app

### Setup Database

1. You can now connect to MariaDB using DBWeaver or any other database client and create the database.

3. Configure your `application.properties` file in `src/main/resources` with the correct database connection settings:

```properties
spring.datasource.url=jdbc:mariadb://localhost:3307/pos
spring.datasource.driverClassName=org.mariadb.jdbc.Driver
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true
```

Replace `your-username` and `your-password` with your MariaDB credentials.

### Install Dependencies

This project uses Maven as the build tool. To install all the required dependencies, run the following command:

```bash
mvn clean install
```

### Run the Application

To run the application, use the following command:

```bash
mvn spring-boot:run
```

## Features

- **Student Management**: You can add students, update their details

## Project Structure

- **Controller**: Handles HTTP requests and responses, directing them to the appropriate service.
- **Repository**: Uses Spring Data JPA to interact with the MariaDB database.
- **DTO**: Defines entities like `StudentDTO`
- **Hateoas** : Provides features to easily add  links using RepresentationModel or EntityModel

## Dependencies

The following dependencies are included in this project:

- **Spring Boot Starter Web**: For building the web application.
- **Spring Boot Starter Data JPA**: For database access using JPA (Java Persistence API).
- **MariaDB JDBC Driver**: To connect to the MariaDB database.
- **Lombok**: To simplify Java code by automatically generating getters, setters, and constructors.
- **Spring Boot Starter Test**: For writing tests for the application.
- **gRPC**: For communication between services (optional and can be updated depending on the project needs).
---
## NoSQL Part

This is a Spring Boot web application that allows you to manag disciplines. It uses MongoDB as the NoSQL database to store course-related data and offers features for adding, updating, and deleting course information.
## Prerequisites

Before running the application, make sure you have the following installed:

- **Java 17** or later
- **Docker** for MariaDB container setup

## Installation
### Set up the MongoDB with Docker

This project includes a `dockerfile` to set up a MongoDB container for the database. Follow these steps:

1. **Create the dockerfile**: The dockerfile contains the necessary configurations for the MongoDB container. Here’s an example of the dockerfile:

```dockerfile
FROM mongo:latest
ENV MONGO_INITDB_ROOT_USERNAME=root
ENV MONGO_INITDB_ROOT_PASSWORD=password
EXPOSE 27017
```

2. **Build the MongoDB image**: After creating the dockerfile, navigate to the folder containing the file and run the following command to build the image:

```bash
docker build -t mongodb-catalog .
```
This will build the Docker image using the provided `dockerfile`.

3. **Run the MongoDB container**: Once the image is built, run the image in the Docker app

### Setup Database

1. You can now connect to MongoDB using MongeDBCompass and create the database.

3. Configure your `application.properties` file in `src/main/resources` with the correct database connection settings:

```properties
spring.data.mongodb.username=pos_******
spring.data.mongodb.password=password
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=pos
```

### Install Dependencies

This project uses Maven as the build tool. To install all the required dependencies, run the following command:

```bash
mvn clean install
```

### Run the Application

To run the application, use the following command:

```bash
mvn spring-boot:run
```

## Features

- **Discipline Management**: Add, update, and delete courses

## Project Structure

- **Controller**: Handles HTTP requests and responses, directing them to the appropriate service.
- **Repository**: Interacts with MongoDB to store and retrieve course data.
- **Models**: Defines entities like `disciplina`
- **Hateoas** : Provides features to easily add  links using RepresentationModel or EntityModel

## Dependencies

The following dependencies are included in this project:

- **Spring Boot Starter Web**: For building the web application.
- **Spring Boot Starter Data MongoDB:**: For MongoDB database access.
- **Spring Boot Starter Hateoas:**:  For supporting HATEOAS in the application.
- **Lombok**: To simplify Java code by automatically generating getters, setters, and constructors.
- **Spring Boot Starter Test**: For writing tests for the application.
---
## Python - gRPC Part

This project is a Python application with gRPC and SQLAlchemy to manage users and their data, with token-based authentication using JWT. The application interacts with a 
SQL database (MariaDB) using SQLAlchemy as the ORM, and gRPC is used to expose services for communication between the client and server.

## Prerequisites

Before you start, ensure that you have the following installed on your machine:

- **Python 3.7** or later
- **MySQL (MariaDB)** (running on port 3307, or adjust the connection string as needed)
- **gRPC**: To enable communication between the server and clients.
- **Protobuf**: For serializing the data exchanged between the server and client.
- **SQLAlchemy**: For database interaction.

You can install the necessary dependencies using pip:
```bash
pip install grpcio grpcio-tools sqlalchemy pymysql jwt futures3
```
## Installation

### Set Up the Database

1. MariaDB from the SQL Part

### Generate Protobuf Files

Before running the application, you will need to compile the .proto file into Python classes. You can use grpcio-tools for this. Run the following command to generate the Python classes from your users.proto file:

```bash
python -m grpc_tools.protoc -I./ --python_out=. --grpc_python_out=. users.proto
```
This will generate users_pb2.py and users_pb2_grpc.py, which are needed for gRPC communication.

The server will listen on localhost:50051 (as set in the ISS variable).

### JWT Authentication

To secure communication, JWT (JSON Web Tokens) is used. Tokens are issued during authentication and need to be passed in the headers of each request. JWT tokens are generated with the jwt library.

## Dependencies

This project uses the following dependencies:

- `gRPC`: For communication between the client and server.
- `SQLAlchemy`: For interacting with the MySQL database.
- `JWT`: For secure token-based authentication.
- `MySQL`: The relational database used for user data.
- `futures3`: For concurrency handling in the server.
- `Pymysql`: MySQL database connector.
---
## React Part
This React application is designed to manage user login functionality, using gRPC for communication between the client and the backend server. The app also uses various libraries to ensure a smooth user experience, including React Router for navigation and Axios for making HTTP requests.

## Prerequisites

Make sure you have the following tools installed on your system:

- **Node.js**: Version 14 or later is required to run the application.
- **npm** or **yarn**: A package manager to manage project dependencies.

### Install Dependencies

Navigate to the project folder and install the required dependencies:
```bash
npm install
```
### Run the Application

Start the React development server with the following command:

```bash
npm start
```
The application will be available at `http://localhost:3000`.

## Project Structure

- **Login Component**: The main component to handle user login functionality. This component sends login requests to the backend server via gRPC using Axios.

## Features

- **Login**: Users can log in using their credentials (email/username and password).

## Dependencies

This project includes the following dependencies:

- **axios**: For making HTTP requests to the backend.
- **google-protobuf**: For handling Protocol Buffers in gRPC communications.
- **grpc-web**: A library for using gRPC with web applications.
- **react**: JavaScript library for building user interfaces.
- **react-dom**: Provides the DOM-specific methods for React.
- **react-router-dom**: Enables routing and navigation for React applications.
- **react-scripts**: Scripts for running and building React applications.
- **web-vitals**: For measuring and tracking performance metrics in web applications.
