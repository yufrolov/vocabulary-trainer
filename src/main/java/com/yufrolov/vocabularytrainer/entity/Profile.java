package com.yufrolov.vocabularytrainer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    UUID id;

    @Column(name = "login", nullable = false, unique = true)
    String login;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    String password;

    @Column(name = "surname", nullable = false)
    String surname;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "midname")
    String midname;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profile")
    private List<UserWord> translatedWords;

    public Profile(String login, String password, String surname, String name, String midname, String email) {
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.midname = midname;
        this.email = email;
    }

    public Profile(Profile profile) {
        this.id = profile.getId();
        this.login = profile.getLogin();
        this.password = profile.getPassword();
        this.surname = profile.getSurname();
        this.name = profile.getName();
        this.midname = profile.getMidname();
        this.email = profile.getEmail();
    }

    public Profile() {
    }
}
