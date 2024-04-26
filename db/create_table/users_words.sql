CREATE TABLE IF NOT EXISTS users_words
(
    profile_id BIGINT NOT NULL,
    word_id BIGINT NOT NULL,
    translate_word_id BIGINT NOT NULL,
    PRIMARY KEY(profile_id, word_id,translate_word_id)
);