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
@Table(name = "vocabularies")
@NoArgsConstructor
@AllArgsConstructor
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "language_code", referencedColumnName = "code", nullable = false)
    private Language languageCode;

    @ManyToOne
    @JoinColumn(name = "language_translate_code", referencedColumnName = "code", nullable = false)
    private Language languageTranslateCode;

    public Vocabulary(Profile profile, Language languageCode, Language languageTranslateCode) {
        this.profile = profile;
        this.languageCode = languageCode;
        this.languageTranslateCode = languageTranslateCode;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vocabulary")
    private List<VocabularyTranslation> vocabularyTranslations  = new ArrayList<>();

}
