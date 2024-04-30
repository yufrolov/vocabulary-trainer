ALTER TABLE languages
    ADD CONSTRAINT languages_code_unique UNIQUE (code);