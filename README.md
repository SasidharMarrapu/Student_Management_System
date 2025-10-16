# 🎓 Student Management System

A **Java-based Student Management System** integrated with **Oracle Database** using **JDBC**.  
This project enables administrators and students to manage profiles, attendance, marks, announcements, and tasks efficiently.  
It also supports frontend integration using **HTML, CSS, and JavaScript** via REST API endpoints.

---
## 📸 Screenshots

### 🧑‍🎓 Login Page

<img width="700" height="800" alt="Login" src="https://github.com/user-attachments/assets/296fb9c5-8471-4f2a-b940-73ca43ea276a" />

### 🏠 Dashboard

<img width="700" height="800" alt="DashBoard" src="https://github.com/user-attachments/assets/aecef002-638f-4745-97c5-09c91e18e0ca" />


## 🚀 Features

### 👨‍🏫 Admin Module
- Secure admin authentication (username/password)
- Add, update, delete, and search student records
- Assign and manage student tasks
- Post, edit, and delete announcements
- Enter or update student marks with automatic grade calculation
- View all registered students

### 🎓 Student Module
- Secure student login
- View and update personal profile (email, phone, password)
- View assigned tasks and mark them as complete
- View announcements from admin
- Check attendance and marks reports

---

## 💾 Technology Stack

| Layer | Technology Used |
|-------|------------------|
| **Frontend** | HTML, CSS, JavaScript |
| **Backend** | Core Java + JDBC |
| **Database** | Oracle 11g / 19c |
| **Build Tool** | Maven / Manual Compilation |
| **IDE (Recommended)** | Eclipse / IntelliJ IDEA |
| **Version Control** | Git & GitHub |

---

## 🧩 Project Structure

StudentManagementSystem/
│
├── project/
│ ├── Admin.java
│ ├── Student.java
│ ├── Task.java
│ └── Announcement.java
│
├── service/
│ ├── AdminService.java
│ ├── StudentService.java
│ ├── AttendanceService.java
│ ├── MarksService.java
│ ├── TaskService.java
│ ├── AnnouncementService.java
│ ├── ProfileService.java
│ └── Main.java
│
├── util/
│ ├── DBConnection.java
│ └── FileUtil.java (legacy serialization helper)
│
├── frontend/
│ ├── index.html
│ ├── styles.css
│ └── script.js
│
└── README.md


---

## 🗄️ Database Setup (Oracle)

Before running, create the following tables and sequences in Oracle SQL Command Line:

```sql
CREATE TABLE admins (
  username VARCHAR2(50) PRIMARY KEY,
  password VARCHAR2(50) NOT NULL
);

INSERT INTO admins VALUES ('admin', 'admin123');

CREATE TABLE students (
  roll NUMBER PRIMARY KEY,
  name VARCHAR2(100),
  password VARCHAR2(50),
  email VARCHAR2(100),
  phone VARCHAR2(20)
);

CREATE TABLE marks (
  roll NUMBER REFERENCES students(roll),
  marks NUMBER,
  grade VARCHAR2(5)
);

CREATE TABLE attendance (
  roll NUMBER REFERENCES students(roll),
  att_date DATE
);

CREATE TABLE tasks (
  task_id NUMBER PRIMARY KEY,
  description VARCHAR2(255),
  assigned_to NUMBER REFERENCES students(roll),
  status VARCHAR2(20)
);

CREATE SEQUENCE task_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE announcements (
  ann_id NUMBER PRIMARY KEY,
  message VARCHAR2(255)
);

CREATE SEQUENCE ann_seq START WITH 1 INCREMENT BY 1;
```
⚙️ Configuration

## 🔗 Database Connection (DBConnection.java)
```
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "system";
    private static final String PASSWORD = "12345";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

## 🖥️ How to Run (Backend)

- Open project in Eclipse or IntelliJ IDEA.
- Add ojdbc17.jar to your project build path.
- Run the Oracle SQL script above to create required tables.
- Execute Main.java to run the console version.
- Default admin credentials:
  Username: admin
  Password: admin123

  👨‍💻 Author

Sasidhar Marrapu

📧 Gmail: sasidharmarrapu674@gmail.com
💼 LinkedIn : https://www.linkedin.com/in/sasidharmarrapu
💻 GitHub : https://github.com/SasidharMarrapu
