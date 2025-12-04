# 📚 Collaborative Study Platform  
A real-time collaborative study management system built with **Spring Boot**, **WebSocket (STOMP)** and **SQLite**.  
This project provides a complete backend for a multi-user study environment where students can:

- Create and join study groups  
- Exchange messages in real-time  
- Share resources and materials  
- Manage tasks with deadlines  
- Track activities (logging system)  
- Access a fully documented REST API (Swagger UI)

---

## 🚀 Features

### 🔐 Authentication & Users
- User registration (with BCrypt password hashing)
- Login with secure password verification
- Fetch user details
- Activity logging for every important action

### 👥 Study Groups
- Create, edit and delete groups
- View all groups or a specific one
- Join groups via Membership module

### 👤 Membership System
- Add user to a group  
- View all group members  
- View all groups a user is part of  

### 📝 Tasks
- Create tasks inside groups  
- Update and delete tasks  
- Set deadlines  
- Fetch tasks by group  
- REST-validated DTOs  
- Activity logging for task updates

### 📁 Resources
- Upload/share learning materials (links, PDF references, etc.)
- Fetch resources uploaded by user
- Fetch resources within a group

### 💬 Real-time Chat (WebSocket + STOMP)
- Fully working group chat
- Live message broadcasting via WebSocket
- REST access to chat message history
- Sender name, senderId, timestamp stored in DB

### 📊 Activity Log
- Logs join actions, group operations, tasks, resources, etc.
- Fetch logs globally or per-user

### 📄 Swagger Documentation
Accessible at:
http://localhost:8080/swagger-ui/index.html



---

## 🏛️ Architecture Overview

### Backend Stack
- **Java 17+**
- **Spring Boot 3.5.7**
- Spring Web  
- Spring Data JPA  
- Spring Validation  
- Spring WebSocket (STOMP)  
- SQLite database  
- Lombok  
- SpringDoc OpenAPI (Swagger)  
- BCrypt hashing  

### Project Structure

src/main/java/com/studyplatform/server
│
├── config/ # Swagger & WebSocket configuration
├── controllers/ # REST & WebSocket controllers
├── dto/ # Request/response DTOs
├── entities/ # Database models
├── repositories/ # Spring Data JPA repositories
├── services/ # Business logic layers
└── StudyPlatformServerApplication.java



---

## 🗄️ Database Model (ER Diagram Overview)

- **User** — one-to-many → **ActivityLog**
- **User** — many-to-many (via Membership) → **Group**
- **Group** — one-to-many → **Task**
- **Group** — one-to-many → **StudyResource**
- **Group** — one-to-many → **ChatMessage**
- **User** — one-to-many → **ChatMessage**

Database: **SQLite `.db` file**

Tables:
- users  
- groups  
- memberships  
- tasks  
- resources  
- chat_messages  
- activity_logs  

---

## 🔌 REST API Overview

### Authentication
| Method | Path | Description |
|--------|-------|-------------|
| POST | `/api/auth/register` | Register user |
| POST | `/api/auth/login` | Login user |

### Users
| GET | `/api/users/{id}` |
| GET | `/api/users` |

### Groups
| Method | Path |
|--------|-------|
| POST | `/api/groups/create` |
| GET | `/api/groups/all` |
| GET | `/api/groups/{id}` |
| PUT | `/api/groups/{id}` |
| DELETE | `/api/groups/{id}` |

### Membership
| Method | Path |
|--------|-------|
| POST | `/api/memberships/add?userId=&groupId=` |
| GET | `/api/memberships/user/{id}` |
| GET | `/api/memberships/group/{id}` |

### Tasks
| POST | `/api/tasks/create?groupId=` |
| GET  | `/api/tasks/{id}` |
| GET  | `/api/tasks/group/{groupId}` |
| PUT  | `/api/tasks/{id}` |
| DELETE | `/api/tasks/{id}` |

### Resources
| POST | `/api/resources/create?groupId=&userId=` |
| GET  | `/api/resources/group/{id}` |
| GET  | `/api/resources/user/{id}` |
| DELETE | `/api/resources/{id}` |

### Activity Log
| GET | `/api/activity/all` |
| GET | `/api/activity/user/{id}` |

### Chat (REST)
| GET | `/api/messages/group/{id}` |

---

## 🔊 WebSocket Chat (STOMP)

### WebSocket endpoint:
ws://localhost:8080/ws/websocket

shell
Copy code

### SEND messages to:
/app/chat.sendMessage

shell
Copy code

### SUBSCRIBE to:
/topic/chat/{groupId}

pgsql
Copy code

### Client → Server JSON:
```
{
  "groupId": 1,
  "senderId": 1,
  "senderName": "Dino",
  "content": "Hello!"
}
```
Server → Client JSON:
```
{
  "id": 10,
  "groupId": 1,
  "senderId": 1,
  "senderName": "Dino",
  "content": "Hello!",
  "timestamp": "2025-12-21T14:25:20"
}
```
🧪 Testing
All endpoints can be tested via:

Swagger UI

requests.http (IntelliJ Idea)

WebSocket King / STOMP client

Postman or Insomnia

▶ How to Run
bash
Copy code
git clone <repository>
cd study-platform-server
mvn clean install
mvn spring-boot:run
Server will run at:

arduino
Copy code
http://localhost:8080
Swagger available at:

bash
Copy code
http://localhost:8080/swagger-ui/index.html
👨‍💻 Team
Role	Name
Backend developer	YOU (Java + Spring Boot)
Frontend developer	Partner (JavaFX client)

🏁 Conclusion
This backend fully implements all required functionality from the semester assignment:

✔ Authentication
✔ Groups
✔ Membership
✔ Tasks
✔ Resources
✔ Activity log
✔ Real-time chat
✔ WebSocket notifications
✔ REST API + Swagger
✔ SQLite integration
