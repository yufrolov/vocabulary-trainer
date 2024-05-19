package com.yufrolov.vocabularytrainer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "languages")
@NoArgsConstructor
public class Language {
    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "languageCode")
    private List<Word> words = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "languageCode")
    private List<Vocabulary> vocabularies = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "languageTranslateCode")
    private List<Vocabulary> translate_vocabularies = new ArrayList<>();

}
