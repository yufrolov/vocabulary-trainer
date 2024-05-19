CREATE TABLE IF NOT EXISTS vocabularies_translations
(
    vocabulary_id BIGINT NOT NULL,
    translation_id BIGINT NOT NULL,
    PRIMARY KEY(vocabulary_id,translation_id)
);