ALTER TABLE words
    ADD CONSTRAINT words_languages_fk FOREIGN KEY(language_id) REFERENCES languages(id);

ALTER TABLE words
    ADD CONSTRAINT words_unique UNIQUE (word);