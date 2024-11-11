# Gallerist API

A simple restful API that allows purchasing cars with the local database.

## Getting Started

### ENV Setup

Create ```.env``` file like this:
```
API_KEY=YOUR_VALUE
DB_DATABASE=YOUR_VALUE
DB_USER=YOUR_VALUE
DB_PASSWORD=YOUR_VALUE
```

### Swagger Testing
You can test the requests with your local swagger: ```http://localhost:8080/swagger-ui/index.html#```


### Requirements
- **Java 17 or higher**
- **Maven 3.6.0 or higher**
- **IDE**: IDE: IntelliJ IDEA or Eclipse (IntelliJ preferred)

### Installation

After cloning the project, run the following command in your terminal to install the necessary dependencies:
```bash
mvn clean install
