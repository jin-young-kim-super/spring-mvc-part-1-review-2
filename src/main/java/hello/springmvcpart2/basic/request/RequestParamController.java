package hello.springmvcpart2.basic.request;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

/**
 * GET 쿼리 스트링, HTML Form 태그로 전송되는 요청 데이터 조회
 */

@Slf4j
@Controller
public class RequestParamController {

    // Servlet에서 요청 데이터 조회
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        Integer age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");

    }

    //@RequestParam으로 요청 데이터 조회
    @RequestMapping("/request-param-v2")
    @ResponseBody // 이게 없으면 return "ok"에 의해 뷰를 반환한다.
    public String requestParamV2(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * String, int, Integer 등의 단순 타입이면 @RequestParam 생략 가능
     * -> 즉 개발자가 직접 만든 타입이 아니면 생략 가능(그러나 이것까지 생략하는 건 너무 과함)
     */
    @RequestMapping("/request-param-v3")
    @ResponseBody
    public String requestParamV3(
            String username,
            int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // Integer age : int a는 0이던 어떠한 값이 반드시 들어 가야 한다. 근데 만약 요청 데이터에
    // age가 없으면 null이 들어 가야 하므로 Integer로 바꿔줘야 한다.
    @RequestMapping("/request-param-required")
    @ResponseBody
    public String requestParamV4(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * defaultValue가 설정되면 required가 true/false 상관없이, 요청 데이터가 없으면 디폴트 값을 세팅
     */
    @RequestMapping("/request-param-default")
    @ResponseBody
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }


    /**
     * Map<String,Object> : 나 같으면, 요청 데이터가 많을 때 사용할 것 같아.
     * -> 요청 데이터가 만약 10개라면 일일이 파라미터 정의하는 건.. 좀.. ㅠㅠ
     * @param paramMap
     * @return
     */
    @RequestMapping("/request-param-map")
    @ResponseBody
    public String requetParamMap( @RequestParam Map<String,Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username") , paramMap.get("age"));
        return "ok";
    }

    /**
     * MultiValueMap<String, Object> : 같은 이름의 요청 데이터가 있을 떄 사용!
     * -> 파라미터 중복이 확실히 없을 때에는 Map 사용!!!
     */
    @RequestMapping("/request-param-multimap")
    @ResponseBody
    public String requetParamMultiMap(@RequestParam MultiValueMap<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username") , paramMap.get("age"));
        return "ok";
    }

}
