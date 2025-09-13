-- Insert Teams
INSERT INTO teams (id, name)
VALUES ('00000000-0000-0000-0000-000000000001', 'Alpha Team'),
       ('00000000-0000-0000-0000-000000000002', 'Beta Team'),
       ('767e0e61-09cc-4136-85ba-5e3121231d48', 'monaTeams'),
       ('a2007c16-9782-4f94-98c0-23476ca3d784', 'monaTeams1'),
       ('00000000-0000-0000-0000-000000000002', 'test_update');

-- Insert Users without team_id column
INSERT INTO users (id, username, email, password, role)
VALUES ('10000000-0000-0000-0000-000000000001', 'admin1', 'admin@example.com', 'admin123', 'ADMIN'),
       ('10000000-0000-0000-0000-000000000002', 'manager1', 'manager@example.com', 'manager123', 'MANAGER'),
       ('10000000-0000-0000-0000-000000000003', 'employee1', 'employee1@example.com', 'employee123', 'EMPLOYEE'),
       ('cb80dfbd-9695-4bb6-8cff-c90f49969268', 'mona', 'mona@example.com', 'mona', 'EMPLOYEE'),
       ('cb80dfbd-9695-4bb6-8cff-c90f49969269', 'mona_admin', 'mona_admin@example.com', 'mona', 'ADMIN'),
       ('be6ff43f-9bea-49ae-9367-66b11acce140', 'mona_manager', 'ona_manager@example.com', 'mona', 'MANAGER');


-- Insert Tasks
INSERT INTO tasks (id, title, description, status, assigned_user_id, team_id, created_by)
VALUES ('20000000-0000-0000-0000-000000000001', 'Build Frontend', 'Create UI for dashboard', 'TODO',
        '10000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000001',
        '10000000-0000-0000-0000-000000000002'),
       ('20000000-0000-0000-0000-000000000002', 'Setup Backend', 'Create REST API with JWT', 'IN_PROGRESS', null,
        '00000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000002'),

       ('b501106d-5bb2-4139-8091-ba1e56f56053', 'New Task',null, 'IN_PROGRESS', '10000000-0000-0000-0000-000000000002',
        '767e0e61-09cc-4136-85ba-5e3121231d48', '10000000-0000-0000-0000-000000000002'),
       ('ba9da18b-d407-4dc3-9c81-4c9e81678c83', 'New Task',null, 'TODO', 'be6ff43f-9bea-49ae-9367-66b11acce140',
        '767e0e61-09cc-4136-85ba-5e3121231d48', '10000000-0000-0000-0000-000000000002'),
       ('b2c99228-cf01-45cc-ac16-dad0b1f72895', 'New Task',null, 'IN_PROGRESS', 'be6ff43f-9bea-49ae-9367-66b11acce140',
        '767e0e61-09cc-4136-85ba-5e3121231d48', '10000000-0000-0000-0000-000000000002');

-- Insert Time Logs
INSERT INTO timelogs (id, user_id, task_id, hours_worked)
VALUES ('30000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000003',
        '20000000-0000-0000-0000-000000000001', 4),
       ('30000000-0000-0000-0000-000000000002', '10000000-0000-0000-0000-000000000003',
        '20000000-0000-0000-0000-000000000001', 3);

INSERT INTO user_teams (user_id, team_id)
VALUES ('10000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000001'),
       ('10000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000001');