ALTER TABLE users_words
    ADD CONSTRAINT users_words_translation_fk FOREIGN KEY(translation_id) REFERENCES translations(id);