ALTER TABLE words
    ADD CONSTRAINT words_language_fk FOREIGN KEY(language_code) REFERENCES languages(code);