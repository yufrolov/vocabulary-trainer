package com.yufrolov.vocabularytrainer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "words")
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "word", nullable = false, unique = true)
    private String word;

    @ManyToOne
    @JoinColumn(name = "language_code", referencedColumnName = "code", nullable = false)
    private Language languageCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "word")
    private List<Translation> wordsToTranslate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "translateWord")
    private List<Translation> translatedWords;

    public Word(String word, Language languageCode) {
        this.word = word;
        this.languageCode = languageCode;
    }
}
