package com.yufrolov.vocabularytrainer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "language_code", referencedColumnName = "code", nullable = false)
    private Language languageCode;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "word")
    private List<Translation> wordsToTranslate = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "translateWord")
    private List<Translation> translatedWords = new ArrayList<>();

    public Word(String word, Language languageCode) {
        this.word = word;
        this.languageCode = languageCode;
    }
}
