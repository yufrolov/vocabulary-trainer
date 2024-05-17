package com.yufrolov.vocabularytrainer.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "languages")
@RequiredArgsConstructor
public class Language {
    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "languageCode")
    private List<Word> words;
}
