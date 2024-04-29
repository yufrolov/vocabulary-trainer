package com.yufrolov.vocabularytrainer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "words")
public class Words {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "word", nullable = false, unique = true)
    private String word;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id", nullable = false)
    private Languages language;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "word")
    private List<Translations> wordsToTranslate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "translateWord")
    private List<Translations> translatedWords;
}
