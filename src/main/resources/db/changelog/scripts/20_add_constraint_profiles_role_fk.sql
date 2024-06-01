ALTER TABLE profiles
    ADD CONSTRAINT profiles_role_fk FOREIGN KEY(role_id) REFERENCES roles(id);