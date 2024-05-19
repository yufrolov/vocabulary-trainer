ALTER TABLE vocabularies_translations
    ADD CONSTRAINT vocabularies_translations_vocabulary_fk FOREIGN KEY(vocabulary_id) REFERENCES vocabularies(id) on delete cascade ;