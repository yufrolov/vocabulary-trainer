package com.yufrolov.vocabularytrainer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "profiles")
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "midname")
    private String midname;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profile")
    private List<Vocabulary> vocabularies = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    public Profile(String password, String surname, String name, String midname, String email, Role role) {
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.midname = midname;
        this.email = email;
        this.role = role;
    }

    public Profile(Profile profile) {
        this.id = profile.getId();
        this.password = profile.getPassword();
        this.surname = profile.getSurname();
        this.name = profile.getName();
        this.midname = profile.getMidname();
        this.email = profile.getEmail();
        this.vocabularies = profile.getVocabularies();
        this.role = profile.getRole();
    }
}
