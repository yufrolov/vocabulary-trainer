ALTER TABLE users_words
    ADD CONSTRAINT profiles_fk FOREIGN KEY(profile_id) REFERENCES profiles(id);

ALTER TABLE users_words
    ADD CONSTRAINT words_fk FOREIGN KEY(word_id) REFERENCES words(id);

ALTER TABLE users_words
    ADD CONSTRAINT translate_words_fk FOREIGN KEY(translate_word_id) REFERENCES words(id);

