ALTER TABLE profiles
    ADD CONSTRAINT profiles_login_unique UNIQUE (login);