package com.yufrolov.vocabularytrainer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "translations")
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "word_id", referencedColumnName = "id", nullable = false)
    private Word word;

    @ManyToOne
    @JoinColumn(name = "translate_word_id", referencedColumnName = "id", nullable = false)
    private Word translateWord;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "translation")
    private List<UserWord> translations;
}
