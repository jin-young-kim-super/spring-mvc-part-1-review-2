package hello.springmvcpart2.basic.request;

import hello.springmvcpart2.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * HTTP MESSAGE BODY  application/json : HTTP API REQUEST의 BODY에 JSON 데이터 조회하는 법을 배운다.
 * -> 요청 데이터(GET 쿼리 파라미터, HTML Form 태그 데이터)와는 달리 데이터에 & 기호를 기준으로 파라미터를 선언한다는 규칙이 없기 때문에
 * request.getInputStream()을 통해서 바이트 코드 그대로를 받는 것이 중요한 개념이다.
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}",messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloData={}",helloData);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-json-v2")
    public void requestBodyJsonV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}",messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloData={}",helloData);

        responseWriter.write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody String messageBody)  {

        log.info("messageBody = {}",messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloData={}",helloData);

        return "ok";
    }

    /**
     * 스프링의 생략 전략
     * 파라미터 String, int, Integer의 경우 : @RequestParam이 생략된 걸로 인식
     * 피라미터 개발자가 만든 객체의 경우 : @ModelAttribute가 생략된 걸로 인식
     * -> 고로, @RequestBOdy 생략 불가능 : 만약 생략하게 되면 @ModelAttribute가 생략된 걸로 인식!
     */
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(@RequestBody HelloData helloData)  {
        log.info("helloData={}",helloData);
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public String requestBodyJsonV5(HttpEntity<HelloData> data)  {

        HelloData helloData = data.getBody();

        log.info("helloData={}",helloData);

        return "ok";
    }
}