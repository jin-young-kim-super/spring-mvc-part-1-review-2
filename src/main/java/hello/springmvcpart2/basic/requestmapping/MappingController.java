package hello.springmvcpart2.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {

    // url을 배열로 2개 이상 넣을 수 있음
    @RequestMapping({"/hello-basic", "/hello-advanced"})
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @GetMapping("/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * @PathVariable("userId") String data == @PathVariable String userId
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("userId={}",data);
        return "ok";
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}",userId,orderId);
        return "ok";
    }

    /**
     * params : 특정 쿼리 파라미터가 없으면 호출 거부할 때 사용
     *
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     **/
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * headers : 특정 헤더가 없으면 호출 거부할 때 사용
     *
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-contentType", consumes = "application/json")
    public String mappingContentType() {
        log.info("mappingContentType");
        return "ok";
    }
    /**
     * 클라이언트의 Accept를 전제로 한다.
     *
     * Accept 헤더 기반 Media Type : 클라이언트가 응답으로 받고 싶은 Content-Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */

    // Produces : 서버가 응답하는 Content-type
    // -> 고로 Accept에 만약 Produces에 없는 것이 적히면 컨트롤러 호출 거부된다.
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }




}
