ALTER TABLE languages
    ADD CONSTRAINT languages_title_unique UNIQUE (title);