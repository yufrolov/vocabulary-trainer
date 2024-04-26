ALTER TABLE profiles
    ADD CONSTRAINT profiles_login_unique UNIQUE (login);

ALTER TABLE profiles
    ADD CONSTRAINT profiles_email_unique UNIQUE (email);