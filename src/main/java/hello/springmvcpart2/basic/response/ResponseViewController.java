package hello.springmvcpart2.basic.response;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView modelAndView = new ModelAndView("response/hello")
                .addObject("data","hello!");
        return modelAndView;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data","hello!");
        return "response/hello";
    }

    /**
     * 컨트롤러 URL와 논리적 뷰 이름이 같을 떄, 스프링은 자동으로 뷰를 반환한다.
     * -> 명시성이 너무 떨어지므로 절대 사용 금지
     */
    @RequestMapping("response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data","hello!");
    }
}
