# TaskSphere

## 📌 Project Description
**TaskSphere** is a microservices-based task management system designed to streamline team collaboration and productivity.  
It provides role-based access where **Admins** manage users and roles, **Managers** organize teams and tasks, and **Employees** track and complete their assigned work.  

The platform emphasizes modularity by dividing functionality across dedicated microservices for authentication, user management, teams, and tasks.  

---

## 🚀 Concept Overview
- **Admin** → Creates users, assigns roles.  
- **Manager** → Creates teams and tasks, tracks employee progress.  
- **Employee** → Views assigned tasks, logs time, and updates task status.  

---

## 🏗 Microservices Overview
- **API Service** → Authentication, registration, token validation, logout, request routing  
- **User Service** → Manage users, roles, teams, and user-team relationships  
- **Team Service** → Manage team creation, membership, and managers  
- **Task Service** → Manage task lifecycle (create, assign, update, list)  

---

## 🔑 Roles and Permissions
- **Admin**
  - Create/update/delete users
  - Assign roles
  - View all data  
- **Manager**
  - Create and assign tasks
  - Manage teams
  - View team performance  
- **Employee**
  - View assigned tasks
  - Log time
  - Mark tasks complete  

---

## 📡 API Endpoints

### **API Service**
- `POST /api/register` → Register new user (default role: EMPLOYEE)  
- `POST /api/login` → Login and receive JWT  
- `POST /api/logout` → Logout (invalidate token)  

### **User Service**
- `GET /users` → Get own profile  
- `GET /users/{id}` → Get user by ID (**Admin only**)  
- `PUT /users/{id}/role` → Update user role (**Admin only**)  

### **Team Service**
- `POST /teams/` → Create a team (**Admin/Manager**)  
- `GET /teams/{id}` → Get team info (**Admin/Manager/member**)  
- `PUT /teams/{id}` → Update team info (**Admin/Manager**)  
- `DELETE /teams/{id}` → Delete team (**Admin only**)  
- `POST /teams/{id}/users` → Add user(s) to team (**Admin/Manager**)  
- `DELETE /user-teams/{id}/users/{userId}` → Remove user from team (**Admin/Manager**)  
- `GET /user-teams/{id}/users/{userId}` → List team members  
- `GET /user-teams/{id}/users` → List team managers  
- `GET /user-teams/{id}/teams` → Get teams user belongs to  

### **Task Service**
- `POST /tasks/` → Create a task (**Manager of the team**)  
- `GET /tasks/assigned` → Get tasks assigned to logged-in user  
- `GET /tasks/team/{teamId}` → Get all tasks for a team (**Manager/Admin**)  
- `PUT /tasks/{id}/status` → Update task status (**Assigned employee**)  
- `PUT /tasks/{id}/assign` → Assign task to a user in team (**Manager**)  
