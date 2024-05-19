ALTER TABLE words
    ADD CONSTRAINT words_word_and_code_unique UNIQUE (word,language_code);