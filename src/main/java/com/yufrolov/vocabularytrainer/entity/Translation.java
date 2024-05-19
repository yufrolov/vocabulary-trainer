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
@Table(name = "translations")
@NoArgsConstructor
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "word_id", referencedColumnName = "id", nullable = false)
    private Word word;

    @ManyToOne
    @JoinColumn(name = "translate_word_id", referencedColumnName = "id", nullable = false)
    private Word translateWord;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "translation")
    private List<VocabularyTranslation> vocabularyTranslations = new ArrayList<>();

    public Translation(Word word, Word translateWord) {
        this.word = word;
        this.translateWord = translateWord;
    }
}
