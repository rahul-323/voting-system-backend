# 🗳️ Voting System Backend API

A comprehensive **Spring Boot REST API** for managing elections, candidates, and votes. This application provides secure user authentication, election management, and vote tracking with JWT-based security.

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [Project Structure](#project-structure)
- [Architecture](#architecture)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Security Implementation](#security-implementation)
- [Usage Examples](#usage-examples)
- [API Documentation](#api-documentation)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)

---

## ✨ Features

✅ **User Management**
- User registration and authentication
- Role-based access control (Admin/Voter)
- User profile management
- Password encryption with BCrypt

✅ **Election Management**
- Create and manage elections
- Auto-status determination (Upcoming/Active/Completed)
- Filter elections by status
- Election statistics

✅ **Candidate Management**
- Add candidates to elections
- Manage candidate details
- Associate candidates with specific elections

✅ **Voting System**
- Cast votes for candidates
- Track vote history
- View vote details
- Vote management and deletion

✅ **Security Features**
- JWT token-based authentication
- BCrypt password encryption
- CORS enabled for cross-origin requests
- Global exception handling
- Input validation

---

## 🛠️ Technology Stack

| Component | Technology |
|-----------|-----------|
| **Language** | Java 21 |
| **Framework** | Spring Boot 3.4.5 |
| **Web** | Spring Web (REST APIs) |
| **Security** | Spring Security + JWT (JJWT 0.11.5) |
| **Database** | MySQL 8.0+ |
| **ORM** | JPA/Hibernate |
| **Validation** | Jakarta Bean Validation |
| **Code Generation** | Lombok |
| **Build Tool** | Maven |
| **Documentation** | Springdoc OpenAPI (Swagger)* |

*Optional: Can be added for API documentation

---

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java 21** or higher ([Download](https://www.oracle.com/java/technologies/downloads/#java21))
- **MySQL 8.0** or higher ([Download](https://dev.mysql.com/downloads/mysql/))
- **Maven 3.8+** ([Download](https://maven.apache.org/download.cgi))
- **Git** ([Download](https://git-scm.com/))
- **Postman** or **Thunder Client** (for API testing - optional)

---

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/voting-system-backend.git
cd voting-system-backend
```

### 2. Create MySQL Database

```sql
CREATE DATABASE voting_system;
USE voting_system;

-- Tables will be auto-created by Hibernate
```

### 3. Setup Environment Variables

Create/Update `.env` file in project root with your database credentials:

```properties
DB_PASSWORD=your_mysql_password
DB_USERNAME=root
DB_HOST=localhost
DB_PORT=3306
DB_NAME=voting_system
```

**Example `.env` file:**
```properties
DB_PASSWORD=8554044619@Rahul
DB_USERNAME=root
DB_HOST=localhost
DB_PORT=3306
DB_NAME=voting_system
```

### 4. Install Dependencies

```bash
mvn clean install
```

---

## ⚠️ **IMPORTANT: Before Running Application**

You **MUST** load environment variables from `.env` file first!

**PowerShell (Recommended):**
```powershell
Get-Content .env | ForEach-Object {
    if ($_ -match '(.+?)=(.+)') {
        [Environment]::SetEnvironmentVariable($Matches[1], $Matches[2])
    }
}
```

**Quick Alternative (PowerShell):**
```powershell
$env:DB_PASSWORD = "8554044619@Rahul"
```

After loading environment variables, proceed to **Running the Application** section below.

---

## ⚙️ Configuration

### JWT Configuration

Edit `src/main/java/com/example/electionProject/security/JwtUtils.java`:

```java
private static final String SECRET_KEY = "your_super_secret_key_here";
private static final long JWT_EXPIRATION = 604800000; // 7 days in milliseconds
```

### CORS Configuration

CORS is enabled in `CorsConfig.java`:

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }
}
```

---

## 🏃 Running the Application

### ⚠️ **PREREQUISITE: Load Environment Variables**

Before running the application, you **MUST** load the `.env` file in your terminal:

**PowerShell:**
```powershell
Get-Content .env | ForEach-Object {
    if ($_ -match '(.+?)=(.+)') {
        [Environment]::SetEnvironmentVariable($Matches[1], $Matches[2])
    }
}
```

**Alternative - Single Line (PowerShell):**
```powershell
$env:DB_PASSWORD = "8554044619@Rahul"
```

**Command Prompt (Windows):**
```cmd
for /f "tokens=1,2 delims==" %a in (.env) do set %a=%b
```

**Linux/Mac (Bash):**
```bash
export $(cat .env | xargs)
```

---

### Using Maven

```bash
# Navigate to project directory
cd voting-system-backend

# Run the application (after loading .env file above)
mvn spring-boot:run
```

The application will start at: `http://localhost:8080`

### Using IDE

1. Open the project in your IDE (IntelliJ IDEA, Eclipse, or VS Code)
2. **First:** Load environment variables in terminal (see PREREQUISITE above)
3. Locate `ElectionProjectApplication.java`
4. Right-click and select **Run** or **Run As** → **Java Application**

### Default Server URL

```
http://localhost:8080
```

### Verify Application Started

Open in browser:
```
http://localhost:8080/api/elections
```

Should return: `[]` (empty array)

---

## 📁 Project Structure

```
voting-system-backend/
├── src/
│   ├── main/
│   │   ├── java/com/example/electionProject/
│   │   │   ├── ElectionProjectApplication.java        # Main entry point
│   │   │   ├── controllers/                           # HTTP request handlers
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── UserController.java
│   │   │   │   ├── CandidateController.java
│   │   │   │   ├── ElectionController.java
│   │   │   │   └── VoteController.java
│   │   │   ├── entities/                              # JPA entities (database models)
│   │   │   │   ├── User.java
│   │   │   │   ├── Election.java
│   │   │   │   ├── Candidate.java
│   │   │   │   └── Vote.java
│   │   │   ├── repositories/                          # Database access layer
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── ElectionRepository.java
│   │   │   │   ├── CandidateRepository.java
│   │   │   │   └── VoteRepository.java
│   │   │   ├── services/                              # Business logic layer
│   │   │   │   ├── UserService.java
│   │   │   │   ├── UserServiceImpl.java
│   │   │   │   ├── ElectionService.java
│   │   │   │   ├── ElectionServiceImpl.java
│   │   │   │   ├── CandidateService.java
│   │   │   │   ├── CandidateServiceImpl.java
│   │   │   │   ├── VoteService.java
│   │   │   │   └── VoteServiceImpl.java
│   │   │   ├── security/                              # Security configuration
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── JwtUtils.java
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   ├── JwtAuthenticationEntryPoint.java
│   │   │   │   ├── CustomUserDetailsService.java
│   │   │   │   └── CorsConfig.java
│   │   │   ├── payloads/                              # Request/Response DTOs
│   │   │   │   ├── LoginRequest.java
│   │   │   │   └── LoginResponse.java
│   │   │   └── exception/                             # Custom exceptions
│   │   │       ├── GlobalExceptionHandler.java
│   │   │       ├── ResourceNotFoundException.java
│   │   │       ├── DuplicateEmailException.java
│   │   │       └── DuplicatePhoneException.java
│   │   └── resources/
│   │       └── application.properties                 # Application configuration
│   └── test/                                          # Unit tests
│       └── java/com/example/electionProject/
│           └── ElectionProjectApplicationTests.java
├── pom.xml                                            # Maven dependencies
├── mvnw & mvnw.cmd                                   # Maven wrapper
├── README.md                                          # This file
└── Voting-System-Postman-Collection.json             # Postman API collection
```

---

## 🏗️ Architecture

### 3-Tier Layered Architecture

```
┌─────────────────────────────────────────────────────┐
│         PRESENTATION LAYER - Controllers            │
│    (Handle HTTP requests and responses)             │
└─────────────────────────────────────────────────────┘
                          ↓
┌─────────────────────────────────────────────────────┐
│      BUSINESS LOGIC LAYER - Services                │
│    (Implement business rules and logic)             │
└─────────────────────────────────────────────────────┘
                          ↓
┌─────────────────────────────────────────────────────┐
│    DATA ACCESS LAYER - Repositories                 │
│    (Interact with the database)                     │
└─────────────────────────────────────────────────────┘
                          ↓
┌─────────────────────────────────────────────────────┐
│      DATABASE LAYER - MySQL                         │
│    (Store and retrieve data)                        │
└─────────────────────────────────────────────────────┘
```

### Security Flow

```
Client Request
     ↓
JwtAuthenticationFilter
  ├─ Extract JWT token
  ├─ Validate signature
  ├─ Check expiration
  └─ Verify token integrity
     ↓
CustomUserDetailsService
  ├─ Load user from database
  ├─ Retrieve authorities
  └─ Build UserDetails object
     ↓
Controller
  ├─ Process request
  ├─ Call services
  └─ Return response
     ↓
Response sent to client
```

---

## 📡 API Endpoints

### Base URL: `http://localhost:8080/api`

### Authentication Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/auth/register` | Register new user | ❌ |
| POST | `/auth/login` | Login user, get JWT token | ❌ |

### User Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| GET | `/users` | Get all users | ✅ |
| GET | `/users/{id}` | Get user by ID | ✅ |
| PUT | `/users/{id}` | Update user | ✅ |
| DELETE | `/users/{id}` | Delete user | ✅ |

### Election Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/elections` | Create election | ❌ |
| GET | `/elections` | Get all elections | ❌ |
| GET | `/elections/{id}` | Get election by ID | ❌ |
| GET | `/elections/status/{status}` | Filter by status | ❌ |
| GET | `/elections/stats` | Get statistics | ❌ |
| PUT | `/elections/{id}` | Update election | ❌ |
| DELETE | `/elections/{id}` | Delete election | ❌ |

### Candidate Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/candidates` | Create candidate | ❌ |
| GET | `/candidates` | Get all candidates | ❌ |
| GET | `/candidates/{id}` | Get candidate by ID | ❌ |
| PUT | `/candidates/{id}` | Update candidate | ❌ |
| DELETE | `/candidates/{id}` | Delete candidate | ❌ |

### Vote Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/votes` | Cast vote | ❌ |
| GET | `/votes` | Get all votes | ❌ |
| GET | `/votes/{id}` | Get vote by ID | ❌ |
| DELETE | `/votes/{id}` | Delete vote | ❌ |

---

## 🗄️ Database Schema

### User Table

```sql
CREATE TABLE user (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(10) NOT NULL,
    voter_id VARCHAR(12) NOT NULL,
    user_type VARCHAR(20) NOT NULL CHECK (user_type IN ('Admin', 'Voter'))
);
```

### Election Table

```sql
CREATE TABLE election (
    election_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    start_date VARCHAR(10) NOT NULL,
    end_date VARCHAR(10) NOT NULL,
    status VARCHAR(20) CHECK (status IN ('Upcoming', 'Active', 'Completed'))
);
```

### Candidate Table

```sql
CREATE TABLE candidate (
    candidate_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    election_id BIGINT,
    party VARCHAR(100) NOT NULL,
    symbol VARCHAR(50),
    FOREIGN KEY (election_id) REFERENCES election(election_id)
);
```

### Vote Table

```sql
CREATE TABLE vote (
    vote_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    candidate_id BIGINT NOT NULL,
    election_id BIGINT NOT NULL,
    vote_date VARCHAR(10),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (candidate_id) REFERENCES candidate(candidate_id),
    FOREIGN KEY (election_id) REFERENCES election(election_id)
);
```

---

## 🔐 Security Implementation

### Authentication Flow

1. **Registration**: User registers with email and password
2. **Password Encryption**: Password is hashed using BCrypt
3. **Login**: User provides credentials
4. **JWT Generation**: Token is generated with user info and expiration
5. **Token Validation**: JWT token must be included in Authorization header for protected endpoints

### JWT Token Structure

```
Header.Payload.Signature

Header: Algorithm (HS256) and type (JWT)
Payload: User info, roles, expiration
Signature: Encoded header + payload signed with secret key
```

### Security Headers

Add to requests:
```
Authorization: Bearer {jwt_token}
Content-Type: application/json
```

---

## 💡 Usage Examples

### 1. Register a New User

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "securePass123",
    "phone": "9876543210",
    "voterID": "ABC12345",
    "userType": "Voter"
  }'
```

### 2. Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "securePass123",
    "userType": "Voter"
  }'
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 3. Create Election

```bash
curl -X POST http://localhost:8080/api/elections \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Presidential Election 2024",
    "startDate": "2024-06-15",
    "endDate": "2024-06-25"
  }'
```

### 4. Get Active Elections

```bash
curl -X GET http://localhost:8080/api/elections/status/Active
```

### 5. Create Candidate

```bash
curl -X POST http://localhost:8080/api/candidates \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Smith",
    "electionID": 1,
    "party": "Democratic Party",
    "symbol": "Lion"
  }'
```

### 6. Cast Vote

```bash
curl -X POST http://localhost:8080/api/votes \
  -H "Content-Type: application/json" \
  -d '{
    "userID": 1,
    "candidateID": 1,
    "electionID": 1,
    "voteDate": "2024-06-20"
  }'
```

---

## 📚 API Documentation

### Request/Response Format

All requests and responses use **JSON** format.

**Request Headers:**
```
Content-Type: application/json
Authorization: Bearer {jwt_token}  (for protected endpoints)
```

**Response Status Codes:**
- `200 OK` - Success
- `201 Created` - Resource created
- `400 Bad Request` - Invalid input
- `401 Unauthorized` - Authentication failed
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server error

### Validation Rules

| Field | Validation |
|-------|-----------|
| Email | Valid email format, unique |
| Password | Min 8 characters |
| Phone | Exactly 10 digits |
| Voter ID | 6-12 alphanumeric characters |
| User Type | "Admin" or "Voter" |
| Start/End Date | Format: YYYY-MM-DD |

---

## ❓ Troubleshooting

### Issue: "Cannot connect to database"

**Solution:**
1. Verify MySQL is running: `mysql -u root -p`
2. Check database credentials in `application.properties`
3. Ensure database `voting_system` exists

### Issue: "Port 8080 already in use"

**Solution:**
```bash
# Find process using port 8080
netstat -ano | findstr :8080

# Kill process (Windows)
taskkill /PID {PID} /F

# Or change port in application.properties
server.port=8081
```

### Issue: "JWT token expired"

**Solution:**
- Increase JWT expiration time in `JwtUtils.java`
- Login again to get a new token

### Issue: "Build failure with Maven"

**Solution:**
```bash
# Clear Maven cache and rebuild
mvn clean install -U
```

### Issue: "Lombok not working"

**Solution:**
- Install Lombok plugin in IDE
- Restart IDE
- Ensure Lombok dependency is in pom.xml

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit changes: `git commit -am 'Add new feature'`
4. Push to branch: `git push origin feature/your-feature`
5. Submit a pull request

---

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

## 👨‍💻 Author

**Rahul Kumar**
- Email: your.email@example.com
- GitHub: [@yourusername](https://github.com/yourusername)

---

## 📞 Support

For support, email support@votingsystem.com or open an issue on GitHub.

---

## 🔗 Useful Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Documentation](https://spring.io/projects/spring-security)
- [JWT.io - JWT Debugger](https://jwt.io)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Maven Documentation](https://maven.apache.org/guides/)

---

## 📝 Postman Collection

A complete Postman collection is included: `Voting-System-Postman-Collection.json`

**To import:**
1. Open Postman
2. Click **Import**
3. Select the JSON file
4. Start testing the API

---

**Happy Coding! 🚀**
