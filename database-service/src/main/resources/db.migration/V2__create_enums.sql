-- User roles
CREATE TYPE user_role AS ENUM ('ADMIN', 'MANAGER', 'EMPLOYEE');

-- Task status
CREATE TYPE task_status AS ENUM ('TODO', 'IN_PROGRESS', 'DONE');
