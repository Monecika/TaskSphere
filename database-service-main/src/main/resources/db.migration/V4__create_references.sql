-- FK: tasks -> users (assigned)
ALTER TABLE tasks
    ADD CONSTRAINT fk_task_assigned_user
        FOREIGN KEY (assigned_user_id) REFERENCES users (id) ON DELETE SET NULL;

-- FK: tasks -> users (created_by)
ALTER TABLE tasks
    ADD CONSTRAINT fk_task_creator
        FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE CASCADE;

-- FK: tasks -> teams
ALTER TABLE tasks
    ADD CONSTRAINT fk_task_team
        FOREIGN KEY (team_id) REFERENCES teams (id) ON DELETE SET NULL;

-- FK: timelogs -> users
ALTER TABLE timelogs
    ADD CONSTRAINT fk_timelog_user
        FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

-- FK: timelogs -> tasks
ALTER TABLE timelogs
    ADD CONSTRAINT fk_timelog_task
        FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE;

ALTER TABLE user_teams
    ADD CONSTRAINT fk_user_teams_user
        FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

-- FK: user_teams -> teams
ALTER TABLE user_teams
    ADD CONSTRAINT fk_user_teams_team
        FOREIGN KEY (team_id) REFERENCES teams (id) ON DELETE CASCADE;