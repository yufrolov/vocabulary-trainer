package com.yufrolov.vocabularytrainer.entity;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "languageCode")
    private List<Word> words = new ArrayList<>();
}
