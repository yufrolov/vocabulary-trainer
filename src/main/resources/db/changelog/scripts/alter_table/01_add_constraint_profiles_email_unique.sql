ALTER TABLE profiles
    ADD CONSTRAINT profiles_email_unique UNIQUE (email);