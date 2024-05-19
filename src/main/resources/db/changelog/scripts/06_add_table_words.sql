CREATE TABLE IF NOT EXISTS words
(
    id BIGSERIAL,
    word TEXT NOT NULL,
    language_code CHAR(2) NOT NULL,
    PRIMARY KEY(id)
);