ALTER TABLE words
    ADD CONSTRAINT words_language_fk FOREIGN KEY(language_id) REFERENCES languages(id);