ALTER TABLE vocabularies
    ADD CONSTRAINT vocabularies_language_translate_fk FOREIGN KEY(language_translate_code) REFERENCES languages(code) on delete cascade ;