ALTER TABLE vocabularies
    ADD CONSTRAINT vocabularies_profile_fk FOREIGN KEY(profile_id) REFERENCES profiles(id) on delete cascade ;