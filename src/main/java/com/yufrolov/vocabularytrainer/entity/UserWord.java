package com.yufrolov.vocabularytrainer.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "users_words")
@RequiredArgsConstructor
public class UserWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "translation_id", referencedColumnName = "id", nullable = false)
    private Translation translation;

    public UserWord(Profile profile, Translation translation) {
        this.profile = profile;
        this.translation = translation;
    }
}
