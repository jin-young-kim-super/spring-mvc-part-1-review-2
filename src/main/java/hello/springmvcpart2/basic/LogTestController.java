package hello.springmvcpart2.basic;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

    // @Slf4j : Lombok이 제공하는 기능으로, 아래 코드 대체 가능
    // -> 참고로 Lombok은 코드를 대신 적어주는 에노테이션이였다.
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        log.trace("trace log={}",name);
        log.debug("debug log={}",name);
        log.info("info log={}",name);
        log.warn("warn log={}",name); // warn, error 로그는 운영 서버에서 따로 로그 파일로 기록하거나 할 수 있다.
        log.error("error log={}",name);

        return name;
    }
}
