ALTER TABLE translations
    ADD CONSTRAINT translations_translate_word_fk FOREIGN KEY(translate_word_id) REFERENCES words(id);

