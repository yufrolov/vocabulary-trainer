CREATE TABLE IF NOT EXISTS vocabularies
(
    id BIGSERIAL,
    profile_id UUID NOT NULL,
    language_code CHAR(2) NOT NULL,
    language_translate_code CHAR(2) NOT NULL,
    PRIMARY KEY(id)
);