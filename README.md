AEON Bank Assessment

# Simple Library System

This is a simple library system application built with Java and Spring Boot.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- Java 17 installed on your machine
- Oracle Database
- Postman for testing purposes

## How to Run It Locally

1. **Clone the Repository**
   
   Open your terminal and run the following command to clone the repository:

   ```sh
   git clone -b main https://github.com/hasifnizam/simplelibrarysystem.git

2. Configure the Database Connection
   
   Navigate to the application.properties file and update below details with your Oracle database username and password.

   ```sh
   spring.datasource.username: yourUserName
   spring.datasource.password: yourPassword

3. Build the Project
   
   Run the following command to clean and build the project:

   ```sh
   mvn clean install

4. Run the Application
   
   Navigate to the target folder and run the application using the following command:
   
   ```sh
   java -jar simplelibrarysystem-0.0.1-SNAPSHOT.jar

5. Test with Postman
    
   Open Postman and import the collection from SimpleLibrarySystem.postman_collection. You can start testing the API endpoints as per the collection.
