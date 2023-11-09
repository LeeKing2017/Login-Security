package web.LoginSecurity.domain.recaptcha.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import web.LoginSecurity.domain.recaptcha.dto.ReCaptchaResponseDto;
import web.LoginSecurity.domain.recaptcha.exception.ReCaptchaErrorException;

import java.util.LinkedHashMap;

@Service
@Slf4j
public class ReCaptchaService {
    @Value("${google.recaptcha.key.secret}")
    public String secretKey;

    public void verifyReCaptcha(String reCaptchaResponse){
        String reCaptchaUri = "https://www.google.com/recaptcha/api/siteverify";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        MultiValueMap<String, String> httpRequestHashMap = new LinkedMultiValueMap<>();
        httpRequestHashMap.add("secret", secretKey);
        httpRequestHashMap.add("response", reCaptchaResponse);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(httpRequestHashMap, httpHeaders);

        ResponseEntity<ReCaptchaResponseDto> responseEntity =
                restTemplate.exchange(
                        reCaptchaUri,
                        HttpMethod.POST,
                        httpEntity,
                        ReCaptchaResponseDto.class);

        ReCaptchaResponseDto reCaptchaResponseDto = responseEntity.getBody();

        if (!reCaptchaResponseDto.isSuccess()){
            throw new ReCaptchaErrorException("ReCaptcha 인증 오류");
        }
    }
}
