ALTER TABLE users_words
    ADD CONSTRAINT users_words_profile_fk FOREIGN KEY(profile_id) REFERENCES profiles(id) on delete cascade ;