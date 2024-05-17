package com.yufrolov.vocabularytrainer.service.thirdparty;

import com.yufrolov.vocabularytrainer.dto.LibreTranslateDTO;
import com.yufrolov.vocabularytrainer.dto.ResponseTranslateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "libretranslate", url = "http://localhost:5000")
public interface LibreTranslateClient {

    @PostMapping(value = "/translate")
    ResponseTranslateDTO translate(LibreTranslateDTO libreTranslateDTO);

}
