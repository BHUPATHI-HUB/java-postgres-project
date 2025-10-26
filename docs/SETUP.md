# Java PostgreSQL Project - Setup Guide

## Overview
This project provides a comprehensive Java implementation for connecting to and working with PostgreSQL databases. It includes reusable connection management classes and example applications.

## Prerequisites

Before running this project, ensure you have the following installed:

1. **Java Development Kit (JDK)** - Version 8 or higher
   - Download from: https://www.oracle.com/java/technologies/downloads/
   - Verify installation: `java -version`

2. **PostgreSQL Database** - Version 10 or higher
   - Download from: https://www.postgresql.org/download/
   - Verify installation: `psql --version`

3. **PostgreSQL JDBC Driver**
   - Download from: https://jdbc.postgresql.org/
   - Or use Maven/Gradle dependency management

## Database Setup

### 1. Install PostgreSQL

For Windows:
```bash
# Download installer from postgresql.org and run
# Follow the installation wizard
```

For Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install postgresql postgresql-contrib
```

For macOS:
```bash
brew install postgresql
```

### 2. Create Database

```sql
-- Connect to PostgreSQL
psql -U postgres

-- Create a new database
CREATE DATABASE mydb;

-- Create a user (optional)
CREATE USER myuser WITH PASSWORD 'mypassword';

-- Grant privileges
GRANT ALL PRIVILEGES ON DATABASE mydb TO myuser;
```

## Project Setup

### 1. Clone the Repository

```bash
git clone https://github.com/BHUPATHI-HUB/java-postgres-project.git
cd java-postgres-project
```

### 2. Add PostgreSQL JDBC Driver

#### Option A: Manual Download
1. Download the JDBC driver JAR from https://jdbc.postgresql.org/
2. Add it to your project's classpath

#### Option B: Maven (Recommended)

Add to `pom.xml`:
```xml
<dependencies>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.6.0</version>
    </dependency>
</dependencies>
```

#### Option C: Gradle

Add to `build.gradle`:
```gradle
dependencies {
    implementation 'org.postgresql:postgresql:42.6.0'
}
```

### 3. Configure Database Connection

Update the connection parameters in `PostgresApp.java`:

```java
String host = "localhost";      // Your database host
String port = "5432";            // PostgreSQL default port
String dbName = "mydb";          // Your database name
String user = "postgres";        // Your database user
String password = "password";    // Your database password
```

## Running the Application

### Compile the Source Code

```bash
# Navigate to the project directory
cd java-postgres-project

# Compile Java files
javac -d bin -cp ".:postgresql-42.6.0.jar" src/main/java/com/example/postgres/*.java
```

### Run the Application

```bash
# Run the main application
java -cp "bin:postgresql-42.6.0.jar" com.example.postgres.PostgresApp
```

### Expected Output

```
Successfully connected to PostgreSQL database!
Table 'users' created successfully!
Data inserted successfully!

Querying data from users table:
ID: 1, Username: john_doe, Email: john@example.com
Database connection closed.
```

## Project Structure

```
java-postgres-project/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── example/
│                   └── postgres/
│                       ├── DatabaseConnection.java
│                       └── PostgresApp.java
├── docs/
│   └── SETUP.md
├── .gitignore
├── LICENSE
└── README.md
```

## Troubleshooting

### Connection Issues

**Problem**: `Connection refused`
**Solution**: 
- Ensure PostgreSQL is running: `sudo service postgresql status`
- Check if PostgreSQL is listening on the correct port
- Verify firewall settings

**Problem**: `Authentication failed`
**Solution**:
- Verify username and password
- Check PostgreSQL's `pg_hba.conf` for authentication settings
- Ensure the user has proper permissions

**Problem**: `ClassNotFoundException: org.postgresql.Driver`
**Solution**:
- Ensure the PostgreSQL JDBC driver is in your classpath
- Download the driver and add it to your project

### Database Issues

**Problem**: `Database does not exist`
**Solution**:
- Create the database using: `CREATE DATABASE mydb;`
- Ensure you're connecting to the correct database name

**Problem**: `Permission denied`
**Solution**:
- Grant necessary privileges: `GRANT ALL PRIVILEGES ON DATABASE mydb TO myuser;`

## Next Steps

1. Explore the `DatabaseConnection.java` class for connection management
2. Review `PostgresApp.java` for example database operations
3. Modify the code to suit your specific requirements
4. Add additional classes for your business logic
5. Implement connection pooling for production use

## Additional Resources

- [PostgreSQL Official Documentation](https://www.postgresql.org/docs/)
- [JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/)
- [PostgreSQL JDBC Driver Documentation](https://jdbc.postgresql.org/documentation/)

## Support

For issues and questions, please open an issue on the GitHub repository.
