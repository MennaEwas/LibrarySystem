# Library Management System

A comprehensive REST API-based Library Management System built with Spring Boot, featuring role-based authentication, book management, member management, and borrowing transaction tracking.

## Features

### Core Functionality
- **Book Management**: CRUD operations for books with metadata (title, ISBN, authors, categories, etc.)
- **Member Management**: Library member registration and management
- **User Management**: System users with role-based access control
- **Borrowing System**: Track book borrowing and return transactions
- **Authentication & Authorization**: Secure role-based access control

### Technical Features
- RESTful API design with proper HTTP methods and status codes
- Role-based security (Administrator, Librarian, Staff)
- H2 in-memory database for easy setup and testing
- JPA/Hibernate for database operations
- Comprehensive error handling and validation
- Password encryption with BCrypt

## Architecture
### Entity Relationship Overview
- **Users**: System users with different roles and permissions
- **Members**: Library borrowers with membership details
- **Books**: Book catalog with rich metadata and copy management
- **Authors**: Book authors and publishers (unified entity)
- **Categories**: Book categorization using enums
- **Borrowing Transactions**: Track borrowing and return activities

### Technology Stack
- **Backend**: Spring Boot 3.5.3, Java 21
- **Database**: H2 (in-memory), JPA/Hibernate
- **Security**: Spring Security with HTTP Basic Authentication
- **Build Tool**: Gradle
- **Testing**: H2 Console, Postman

## Installation & Setup
### Prerequisites
- Java 21 or higher
- Gradle (included via wrapper)

### Getting Started
1. **Clone the repository**
   ```bash
   git clone https://github.com/MennaEwas/LibrarySystem
   cd library_system
   ```

2. **Run the application**
   ```bash
   ./gradlew bootRun
   ```

3. **Access the application**
   - API Base URL: `http://localhost:8080`
   - H2 Database Console: `http://localhost:8080/h2-console`

### Database Configuration
- **JDBC URL**: `jdbc:h2:mem:library`
- **Username**: `sa`
- **Password**: (empty)

## User Roles & Permissions

The system includes three predefined user roles with different access levels:

| Role | Permissions |
|------|-------------|
| **Administrator** | Full system access - manage users, members, books, and transactions |
| **Librarian** | Manage members, books, and transactions (cannot manage system users) |
| **Staff** | Manage books and transactions only |

### Default User Accounts
The system creates these default accounts on startup:

| Username | Password | Role | Email |
|----------|----------|------|-------|
| `admin` | `admin123` | Administrator | admin@library.com |
| `librarian` | `lib123` | Librarian | librarian@library.com |
| `staff` | `staff123` | Staff | staff@library.com |

## API Documentation
### Authentication
All API endpoints (except `/api/auth/**` and `/h2-console/**`) require HTTP Basic Authentication.

**Example Authorization Header:**
```
Authorization: Basic YWRtaW46YWRtaW4xMjM=
```

### Core API Endpoints

#### User Management (Admin Only)
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

#### Book Management (Admin, Librarian, Staff)
- `GET /api/books` - Get all books
- `GET /api/books/{id}` - Get book by ID
- `GET /api/books/isbn/{isbn}` - Get book by ISBN
- `GET /api/books/category/{category}` - Get books by category
- `GET /api/books/available` - Get available books
- `GET /api/books/search?title={title}` - Search books by title
- `POST /api/books` - Create new book
- `PUT /api/books/{id}` - Update book
- `DELETE /api/books/{id}` - Delete book

#### Member Management (Admin, Librarian)
- `GET /api/members` - Get all members
- `GET /api/members/{id}` - Get member by ID
- `GET /api/members/active` - Get active members
- `POST /api/members` - Create new member
- `PUT /api/members/{id}` - Update member
- `DELETE /api/members/{id}` - Delete member

#### Borrowing Transactions (Admin, Librarian, Staff)
- `GET /api/borrowing-transactions` - Get all transactions
- `GET /api/borrowing-transactions/{id}` - Get transaction by ID
- `POST /api/borrowing-transactions` - Create new transaction
- `PUT /api/borrowing-transactions/{id}` - Update transaction
- `DELETE /api/borrowing-transactions/{id}` - Delete transaction

#### Authentication
- `POST /api/auth/register` - Register new user
- `GET /api/auth/me` - Get current user info

### Sample API Requests
#### Create a New Book
```json
POST /api/books
Authorization: Basic librarian:lib123
Content-Type: application/json

{
    "title": "The Great Gatsby",
    "isbn": "978-0-7432-7356-5",
    "publicationYear": "1925",
    "edition": "First Edition",
    "category": "FICTION",
    "totalCopies": 3,
    "availableCopies": 3,
    "authors": []
}
```
#### Create a New Member
```json
POST /api/members
Authorization: Basic librarian:lib123
Content-Type: application/json

{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@email.com",
    "phone": "123-456-7890",
    "member_id": "LIB001",
    "active": true,
    "membershipDate": "2024-07-03"
}
```

#### Create a Borrowing Transaction
```json
POST /api/borrowing-transactions
Authorization: Basic staff:staff123
Content-Type: application/json

{
    "member": {"id": 1},
    "book": {"id": 1},
    "borrowDate": "2024-07-03"
}
```

## Database Schema
![alt text](erd.png)
![alt text](RelDia.png)

### Key Tables
- **users** - System users with authentication and role information
- **members** - Library members who can borrow books
- **books** - Book catalog with metadata and copy tracking
- **authors** - Authors and publishers
- **book_authors** - Many-to-many relationship between books and authors
- **borrowing_transactions** - Track borrowing and return activities

### Book Categories
The system supports the following book categories:- 
- FICTION, NON_FICTION, SCIENCE, HISTORY, BIOGRAPHY
- MYSTERY, ROMANCE, FANTASY, THRILLER, CHILDREN
- TEXTBOOK, REFERENCE, POETRY, DRAMA, PHILOSOPHY

## Testing

### Using Postman
1. Import the provided Postman collection [Postman API Collection](https://warped-station-244043.postman.co/workspace/APITest~f71328de-81bb-4765-b4ad-6153be8b5aac/collection/26059171-d3fcc747-1e6e-4152-8811-6c22786b0bb3?action=share&creator=26059171) 
2. Use HTTP Basic Authentication with the default user credentials
3. Test all CRUD operations for each entity
4. Verify role-based access restrictions

### Using H2 Console
1. Navigate to `http://localhost:8080/h2-console`
2. Use the database connection details provided above
3. Execute SQL queries to inspect data and relationships
4. Verify database constraints and foreign key relationships
5. But there is no beta data you can add them via create apis.

### Sample Test Scenarios
1. **User Management**: Create users with different roles and test access restrictions
2. **Book Management**: Add books, search by various criteria, manage inventory
3. **Member Management**: Register members, update member information
4. **Borrowing Flow**: Borrow books, return books, track transactions
5. **Security Testing**: Verify role-based access control works correctly

## Future Enhancements

### Planned Features
- [ ] JWT token-based authentication
- [ ] Advanced search and filtering
- [ ] Overdue book notifications
- [ ] Fine calculation for late returns
- [ ] Book reservation system
- [ ] Reporting and analytics
- [ ] Email notifications
- [ ] Barcode scanning integration

### Technical Improvements
- [ ] PostgreSQL/MySQL production database support
- [ ] Docker containerization
- [ ] API documentation with Swagger/OpenAPI
- [ ] Unit and integration testing
- [ ] CI/CD pipeline setup
- [ ] Monitoring and logging
- [ ] Rate limiting and API throttling

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is developed as part of a technical assessment for CODE81. All rights reserved.

## Developer Notes
### Design Decisions
- **Role-Based Security**: Implemented three-tier access control for different user types
- **Entity Relationships**: Used JPA annotations for proper database relationships
- **Error Handling**: Comprehensive validation and constraint enforcement
- **API Design**: RESTful principles with appropriate HTTP methods and status codes

### Known Limitations
- In-memory database (data is lost on restart)
- Basic authentication (no JWT implementation)
- Limited search functionality
- No pagination for large datasets

---

**Built with ❤️ using Spring Boot, JPA, and Java 21