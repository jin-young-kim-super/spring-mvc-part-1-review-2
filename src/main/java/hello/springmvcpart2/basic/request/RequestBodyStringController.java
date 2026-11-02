package hello.springmvcpart2.basic.request;

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

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * HTTP MESSAGE BODY : text/plain : HTTP API REQUEST의 BODY에 JSON이 아닌 단순 텍스트 데이터를 조회하는 법을 배운다.
 *
 * 요청 데이터(GET 쿼리 파라미터, HTML Form 데이터)가 아닌, HTTP Message Body에 직접 데이터가 담겨 있는 경우에는
 * 더이상 @RequestParam, @ModelAttribute로 데이터 바인딩 안됨.
 * -> Q. HTML form 데이터도 결국 메시지 바디에 들어가는 데이터인데 왜 @RequestParam, @ModelAttribute로 데이터 바인딩이
 * 가능했을까? A. 그건 form 방식도 결국에는 GET 쿼리 파라미터와 같은 형식의 데이터(username=...$age=..)로 전달되기 떄문이다.
 */

@Controller
@Slf4j
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // inputStream : 바이트 코드(ASCII 등으로 인코딩된 코드)
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messagBody = {}",messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messagBody = {}",messageBody);

        responseWriter.write("ok");

    }

    /**
     * Http Message Converter를 통해 httpEntity에 Http Message body 내용이 자동 인코딩돼서 들어 간다.
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();

        log.info("messagBody = {}",messageBody);

        return new HttpEntity<>("ok");

    }

    // 만약 헤더 정보 등의 부가 정보가 필요하면 파라미터에 HttpEntity를 추가하기만 하면 된다.
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {

        log.info("messagBody = {}",messageBody);

        return "ok";
    }
}
