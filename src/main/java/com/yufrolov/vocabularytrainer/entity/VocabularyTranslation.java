package com.yufrolov.vocabularytrainer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yufrolov.vocabularytrainer.entity.compositepk.VocabularyTranslationId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "vocabularies_translations")
@NoArgsConstructor
@AllArgsConstructor
public class VocabularyTranslation {

    @JsonIgnore
    @EmbeddedId
    private VocabularyTranslationId vocabularyTranslationId = new VocabularyTranslationId();

    @JsonIgnore
    @ManyToOne
    @MapsId("vocabularyId")
    @JoinColumn(name = "vocabulary_id", referencedColumnName = "id", nullable = false)
    private Vocabulary vocabulary;

    @ManyToOne
    @MapsId("translationId")
    @JoinColumn(name = "translation_id", referencedColumnName = "id", nullable = false)
    private Translation translation;

    public VocabularyTranslation(Vocabulary vocabulary, Translation translation) {
        this.vocabulary = vocabulary;
        this.translation = translation;
    }
}
