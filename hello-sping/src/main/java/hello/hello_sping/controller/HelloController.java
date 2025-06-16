package hello.hello_sping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    //API
    @GetMapping("hello-string")
    @ResponseBody //응답 바디에 다음을 직접 넣어줌
    public String helloString(@RequestParam("name") String name){
        //문자열로 반환 시 -> StringConverter에서 문자열로 반환
        return "hello "+name;
    }
    //API-json 형태로 반환
    @GetMapping("hello-api")
    @ResponseBody //html 보내는 게 아니라, 응답 바디에 데이터를 넘겨야 하는구나!
    public Hello helloApi(@RequestParam("name") String name){
        //객체로 반환 시 -> JsonConverter에서 객체를 json으로 변환
        Hello hello = new Hello();
        hello.setName(("name"));
        return hello;
    }
    public static class Hello{
        private String name;

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }
}
