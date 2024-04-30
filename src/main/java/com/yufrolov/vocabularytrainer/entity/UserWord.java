package com.yufrolov.vocabularytrainer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users_words")
public class UserWord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "translation_id", referencedColumnName = "id", nullable = false)
    private Translation translation;
}
