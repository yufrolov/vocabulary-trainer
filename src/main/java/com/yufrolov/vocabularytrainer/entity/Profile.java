package com.yufrolov.vocabularytrainer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    Long id;

    @Column(name = "login", nullable = false, unique = true)
    String login;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "surname", nullable = false)
    String surname;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "midname")
    String midname;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profile")
    private List<UserWord> translatedWords;
}
