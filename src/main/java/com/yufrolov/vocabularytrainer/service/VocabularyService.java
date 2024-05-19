package com.yufrolov.vocabularytrainer.service;

import com.yufrolov.vocabularytrainer.dto.VocabularyDTO;
import com.yufrolov.vocabularytrainer.entity.Vocabulary;
import com.yufrolov.vocabularytrainer.exception.LanguageEqualsException;
import com.yufrolov.vocabularytrainer.exception.VocabularyNotFoundException;
import com.yufrolov.vocabularytrainer.repository.VocabularyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VocabularyService {
    private final VocabularyRepository vocabularyRepository;

    private final LanguageService languageService;

    private final ProfileService profileService;

    private final VocabularyTranslationService vocabularyTranslationService;

    public VocabularyService(VocabularyRepository vocabularyRepository
            , LanguageService languageService
            , ProfileService profileService
            , VocabularyTranslationService vocabularyTranslationService) {
        this.vocabularyRepository = vocabularyRepository;
        this.languageService = languageService;
        this.profileService = profileService;
        this.vocabularyTranslationService = vocabularyTranslationService;
    }

    private boolean isLangEquals(String lang, String translateLang){
        return lang.equals(translateLang);
    }

    public Vocabulary create(UUID profileId,VocabularyDTO vocabularyDTO){
        var profile = profileService.getProfile(profileId);
        var lang = languageService.search(vocabularyDTO.getSource());
        var translateLang = languageService.search(vocabularyDTO.getTarget());
        if (isLangEquals(lang.getCode(), translateLang.getCode())){
            throw new LanguageEqualsException("A vocabulary cannot be with the same languages");
        }
        return vocabularyRepository.save(new Vocabulary(profile, lang, translateLang));
    }
    public Vocabulary getVocabulary(UUID profileId, Long id) {
        profileService.getProfile(profileId);
        return vocabularyRepository.findVocabularyByIdAndProfileId(profileId, id).orElseThrow(
                () -> new VocabularyNotFoundException("You don't have a dictionary with this id")
        );
    }

    public List<Vocabulary> getAllVocabularies(UUID profileId){
        return vocabularyRepository.findVocabulariesByProfileId(profileId);
    }

    public void deleteVocabulary(UUID profileId, Long id){
        var vocabulary = getVocabulary(profileId, id);
        vocabularyRepository.delete(vocabulary);
    }

    public void deleteTranslationInVocabulary(UUID profileId, Long id, Long translationId){
        getVocabulary(profileId, id);
        vocabularyTranslationService.deleteByVocabularyIdAndTranslationId(id,translationId);
    }

}
