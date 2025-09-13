CREATE TABLE jwt_token_blacklist (
                                     id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                     token TEXT NOT NULL,
                                     blacklisted_at TIMESTAMP WITH TIME ZONE NOT NULL
);

-- Teams
CREATE TABLE teams
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name       VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP        DEFAULT NOW()
);

-- Users
CREATE TABLE users
(
    id         UUID PRIMARY KEY             DEFAULT gen_random_uuid(),
    username   VARCHAR(50) UNIQUE  NOT NULL,
    email      VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    role       VARCHAR(20)           NOT NULL DEFAULT 'EMPLOYEE',
    created_at TIMESTAMP                    DEFAULT NOW()
);


-- Tasks
CREATE TABLE tasks
(
    id               UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    title            VARCHAR(100) NOT NULL,
    description      TEXT,
    status           task_status  NOT NULL DEFAULT 'TODO',
    assigned_user_id UUID,
    team_id          UUID,
    created_by       UUID         NOT NULL,
    created_at       TIMESTAMP             DEFAULT NOW()
);

-- Time Logs
CREATE TABLE timelogs
(
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id      UUID NOT NULL,
    task_id      UUID NOT NULL,
    hours_worked INT  NOT NULL,
    log_date     DATE             DEFAULT CURRENT_DATE,
    created_at   TIMESTAMP        DEFAULT NOW()
);


CREATE TABLE user_teams
(
    id      UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    team_id UUID NOT NULL
);