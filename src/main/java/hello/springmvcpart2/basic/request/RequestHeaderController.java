package hello.springmvcpart2.basic.request;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
//@RequestMapping("")
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpMethod httpMethod,
            Locale locale,
            // MultiValueMap : 하나의 키에 여러 value를 넣을 수 있음
            @RequestHeader MultiValueMap<String,String> headerMap,
            @RequestHeader("host") String host,
            @CookieValue(value = "myCooie", required = false) String cookie
            ) {
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "header list";
    }



}
