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



### SEND messages to:
/app/chat.sendMessage



### SUBSCRIBE to:
/topic/chat/{groupId}



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

git clone <repository>
cd study-platform-server
mvn clean install
mvn spring-boot:run
Server will run at:


http://localhost:8080
Swagger available at:


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



Screenshots of Interface:
<img width="396" height="529" alt="{C7FB8A0D-FCF0-4746-9EC7-026D5CAB1EF9}" src="https://github.com/user-attachments/assets/e6602f16-42cb-4b28-b790-e92a24dc2586" />

<img width="396" height="529" alt="{179C3427-8B98-455D-85DE-13E663F13FB9}" src="https://github.com/user-attachments/assets/c476fcd4-e915-48b9-b7b3-318250bf603f" />

<img width="950" height="675" alt="{3C9D06F5-845C-4DB4-875A-5174273503AC}" src="https://github.com/user-attachments/assets/51f50b18-4884-4082-aff0-dc2ffc23b82d" />

<img width="940" height="685" alt="{82C13761-4699-4BE1-8FFE-02130C83C472}" src="https://github.com/user-attachments/assets/b8aa7bf9-bae9-4893-9443-d958207f43f7" />

<img width="949" height="678" alt="{C4213A77-F19E-471A-A5F7-4E57C87162EB}" src="https://github.com/user-attachments/assets/1d92e364-804c-4fbc-b258-daf0f16772fe" />

<img width="957" height="676" alt="{D40726FA-74AE-4304-BCF3-20A2C1AE6315}" src="https://github.com/user-attachments/assets/ddf6d0e9-1162-4443-9f98-c7ac16d97755" />

<img width="950" height="684" alt="{3D2864F7-625F-4093-9C8B-B8B5EFECEA73}" src="https://github.com/user-attachments/assets/15577bf0-3156-41b2-9923-81712b80ab70" />

<img width="950" height="681" alt="{2ED9E9E3-50C9-4234-9FFA-56E669DF2E17}" src="https://github.com/user-attachments/assets/85635688-eff3-4648-a014-1ea707cfc790" />

<img width="951" height="682" alt="{72C893D9-E768-439D-828F-5B7DA12F463A}" src="https://github.com/user-attachments/assets/ca59fe5c-57e7-4335-b2c3-88f8e74fb2d2" />

<img width="951" height="685" alt="{A4424A1C-70FF-470A-9CE6-5BB540E1D6AB}" src="https://github.com/user-attachments/assets/28a3cded-598f-4425-93e9-5dcf5f6943cc" />






