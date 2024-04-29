CREATE TABLE IF NOT EXISTS profiles
(
    id BIGSERIAL,
    login TEXT NOT NULL,
    password TEXT NOT NULL,
    surname TEXT NOT NULL,
    name TEXT NOT NULL,
    midname TEXT,
    email TEXT NOT NULL,
    PRIMARY KEY(id)
);