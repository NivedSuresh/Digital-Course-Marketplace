
---

# **Course Management API**

## **Authentication**

### **1. `POST /api/v1/login`**
- Authenticates the user and returns **AccessToken** and **RefreshToken** in **HttpOnly cookies**.
- Postman will automatically include these cookies in subsequent requests.

### **2. `POST /api/v1/signup`**
- Creates a new user account.
- Returns **AccessToken** and **RefreshToken** in **HttpOnly cookies**.
- **AccessToken** is valid for **30 minutes**.

### **Signup with All 3 Roles**
Signup the following users into the system:

```json
[
  {
    "username": "Creator",
    "email": "creator@example.com",
    "password": "12345",
    "role": "CREATOR"
  },
  {
    "username": "Admin",
    "email": "admin@example.com",
    "password": "12345",
    "role": "ADMIN"
  },
  {
    "username": "Customer",
    "email": "customer@example.com",
    "password": "12345",
    "role": "CUSTOMER"
  }
]
```

---

## **Course Management**

### **3. `POST /api/v1/creator/course`**
- **Access Control:**
    - Requires **ADMIN** or **CREATOR** role.
    - **ADMIN** can create courses for **any** creator.
    - **CREATOR** can create courses **only for themselves**.
    - **Customers are not allowed** to create courses.

#### **Request Body:**
```json
{
  "title": "Kotlin for Beginners",
  "description": "A comprehensive guide to learning Kotlin from scratch.",
  "price": 49.99,
  "creatorId": "{CreatorId}"
}
```
- If the user is **ADMIN**, they can create a course for **any** creator by specifying the `creatorId`.
- If the user is **CREATOR**, the `creatorId` will be **overwritten** with their own (i.e., the principal’s) ID.

---

## **Additional Notes**
- **Authorization**: All endpoints requiring authorization expect the `AccessToken` in **HttpOnly cookies**.

---
