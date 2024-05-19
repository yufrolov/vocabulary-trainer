package com.yufrolov.vocabularytrainer.entity.compositepk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class VocabularyTranslationId implements Serializable {

    @Column(name = "vocabulary_id")
    private Long vocabularyId;

    @Column(name = "translation_id")
    private Long translationId;
}
