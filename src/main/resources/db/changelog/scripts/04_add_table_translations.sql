CREATE TABLE IF NOT EXISTS translations
(
    id BIGSERIAL,
    word_id BIGINT NOT NULL,
    translate_word_id BIGINT NOT NULL,
    PRIMARY KEY(id)
);