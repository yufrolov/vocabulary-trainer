package com.yufrolov.vocabularytrainer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "language")
    private List<Word> words;
}
