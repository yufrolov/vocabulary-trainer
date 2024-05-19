ALTER TABLE vocabularies_translations
    ADD CONSTRAINT vocabularies_translations_translation_fk FOREIGN KEY(translation_id) REFERENCES translations(id) on delete cascade;