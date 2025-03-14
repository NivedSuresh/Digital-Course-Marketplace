# **Course Management API**


## **Authentication**


- **Auth Tokens** are sent via **HttpOnly cookies** and are automatically managed by the client (e.g., Postman, browsers).
- This means that once you **sign up/log in as an ADMIN**, Postman (or any client) will retain the session and include the token in subsequent requests.
- To perform actions as another role (e.g., CUSTOMER, CREATOR), you must **sign in or sign up with a different account**.


### **1. `POST /api/v1/login`**
- Authenticates the user and returns an **Access Token** and **Refresh Token** in **HttpOnly cookies**.
- Postman and other clients will automatically include these cookies in subsequent requests.

### **2. `POST /api/v1/signup`**
- Creates a new user account.
- Returns an **Access Token** and **Refresh Token** in **HttpOnly cookies**.
- The **Access Token** is valid for **30 minutes**.



#### **Example Signup Requests for Different Roles:**

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
  - **CREATOR** can only create courses for themselves.
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
- If the user is **CREATOR**, the `creatorId` will be **overwritten** with their own ID (retrieved from the authenticated principal).

### **4. `GET /api/v1/creator/course?page=1&limit=10&title=&description=`**
- **Access Control:**
  - Requires the **CREATOR** role.
  - A **CREATOR** can view only their own courses.
  - Supports optional filtering by `title` and `description`.
  - Results are paginated using `page` and `limit` parameters.
  - **Title and description filters use case-insensitive partial matching**, e.g., a search for "otl" will match "Kotlin".

### **5. `GET /api/v1/course?page=1&limit=10&creatorId=&title=&description=`**
- **Access Control:**
  - Available to any authenticated user.
  - Fetches all available courses in the marketplace.
  - Supports optional filtering by `title`, `description`, and `creatorId`.
  - Results are paginated using `page` and `limit` parameters.
  - **Title and description filters use case-insensitive partial matching**.

---

## **Purchase Management**

### **6. `POST /api/v1/customer/buy/course/{courseId}`**
- **Access Control:**
  - Only accessible to users with the **CUSTOMER** role.
  - The purchase price is equal to the course price.
  - **Returns `202 Accepted`** on successful purchase.

---

## **Admin & Statistics**

### **7. `GET /api/v1/user?page=1&limit=10&roles=`**
- **Access Control:**
  - Only accessible to users with the **ADMIN** role.
  - Returns all users with roles **CUSTOMER** and **CREATOR** if no role filter is provided.
  - Supports pagination with `page` and `limit` parameters.

### **8. `GET /api/v1/stats?startDate=2024-01-01&endDate=2026-12-31&page=1&limit=10`**
- **Access Control:**
  - Only accessible to users with the **ADMIN** role.
  - Returns all purchased courses along with the total amount paid.
  - Supports pagination with `page` and `limit` parameters.

---

## **Additional Notes**

### **Authorization**
- All protected endpoints require an **Access Token**, which is sent via **HttpOnly cookies**.

### **Error Handling**
- If a user does not have the required permissions, the API responds with **`403 Forbidden`**.
- Invalid or expired tokens result in **`401 Unauthorized`**.

### **Pagination Format**
- `page` parameter is **1-based** in API requests (e.g., `page=1` for the first page).

---

