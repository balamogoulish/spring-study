package mvc1.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 뷰 템플릿 호출 컨트롤러
 */
@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mv = new ModelAndView("response/hello")
                .addObject("data", "helloV1!");
        return mv;
    }

    /**
     * @ResponseBody가 없으면 String 반환 시, 뷰 리졸버가 실행되어 뷰를 찾고 렌더링한다.
     */
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "helloV2!");
        return "response/hello";
    }
}
