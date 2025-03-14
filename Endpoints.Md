
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


### **4. `GET /api/v1/creator/course?offset=0&limit=10&title=&description=`**
- **Access Control:**
  - Requires the **CREATOR** role.
  - A **CREATOR** can view only their own courses.
  - Supports optional filtering by `title` and `description`.
  - Results are paginated based on `offset` and `limit` parameters.
  - **Title and description filters use a case-insensitive partial match**, meaning a search for `"otl"` will match `"Kotlin"`.


### **5. `GET /api/v1/course?offset=0&limit=10&creatorId=&title=&description=`**
- **Access Control:**
  - Available to any authenticated user. Fetches all available courses in the marketplace.
  - Supports optional filtering by `title` and `description` and `creatorId`.
  - Results are paginated based on `offset` and `limit` parameters.
  - **Title and description filters use a case-insensitive partial match**, meaning a search for `"otl"` will match `"Kotlin"`.
---

## **Purchase Management**

### **6. `POST /api/v1/customer/buy/course/{courseId}`**
- **Access Control:**
  - Only accessible to users with the CUSTOMER role.
  - The price of the purchase = price of the course.
  - 202 Accepted – Purchase successful.

---


## **Additional Notes**
- **Authorization:**
  - All protected endpoints require an `AccessToken`, which must be sent via **HttpOnly cookies**.
- **Access Denied Handling:**
  - If a user does not have the required permissions, the API responds with **`403 Forbidden`**.

---

