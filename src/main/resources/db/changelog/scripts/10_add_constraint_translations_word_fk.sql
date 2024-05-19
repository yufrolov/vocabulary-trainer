ALTER TABLE translations
    ADD CONSTRAINT translations_word_fk FOREIGN KEY(word_id) REFERENCES words(id);