CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE IF NOT EXISTS profiles
(
    id UUID DEFAULT uuid_generate_v4(),
    password TEXT NOT NULL,
    surname TEXT NOT NULL,
    name TEXT NOT NULL,
    midname TEXT,
    email TEXT NOT NULL,
    PRIMARY KEY(id)
);