CREATE TABLE IF NOT EXISTS words
(
    id BIGSERIAL,
    word TEXT NOT NULL,
    language_id INT NOT NULL,
    PRIMARY KEY(id)
);