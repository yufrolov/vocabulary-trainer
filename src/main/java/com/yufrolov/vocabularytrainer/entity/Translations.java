package com.yufrolov.vocabularytrainer.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "translations")
public class Translations {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "word_id", referencedColumnName = "id", nullable = false)
    private Words word;

    @ManyToOne
    @JoinColumn(name = "translate_word_id", referencedColumnName = "id", nullable = false)
    private Words translateWord;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "translation")
    private List<UsersWords> translations;
}
