CREATE TABLE IF NOT EXISTS users_words
(
    id BIGSERIAL,
    profile_id UUID NOT NULL,
    translation_id BIGINT NOT NULL,
    PRIMARY KEY(id)
);