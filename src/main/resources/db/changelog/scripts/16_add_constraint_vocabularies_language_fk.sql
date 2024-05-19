ALTER TABLE vocabularies
    ADD CONSTRAINT vocabularies_language_fk FOREIGN KEY(language_code) REFERENCES languages(code) on delete cascade ;