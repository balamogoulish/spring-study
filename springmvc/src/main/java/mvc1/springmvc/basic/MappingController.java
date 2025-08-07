package mvc1.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Slf4j
@RestController
public class MappingController {

    /**
     * 기본 요청
     * HTTP 메서드 모두 허용
     */
    @RequestMapping("/hello-basic")
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1(){
        log.info("mappingGetV1");
        return "ok";
    }
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2(){
        log.info("mappingGetV2");
        return "ok";
    }
    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId) String userId -> @PathVariable String userId
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId={}", data);
        return "ok";
    }
    @GetMapping("mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId){
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 특정 파라미터 조건 매핑
     * params="mode"
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! =)
     * params = {"mode=debug", "data="good"}
     * 위의 조건을 충족해야 다음 메서드와 매핑된다. 거의 사용하지 않음
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam(){
        log.info("mappingParam");
        return "ok";
    }
    /**
     * 특정 헤더 추가 매핑(예시는 파라미터 경우와 같음)
     * headers="mode
     */
    @GetMapping(value = "mapping-header", params = "mode=debug")
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }
    /**
     * 미디어 타입 조건 매핑 Accept, produce
     * Accept 헤더 기반 Media Type
     * produces= "text-html"
     * produces= "!text-html"
     * produces= "text/*
     * 만약 맞지 않으면 HTTP 406(Not Acceptable)을 반환
     */
    @PostMapping(value="/mapping-produces", produces = "text/html")
    public String mappingProduces(){
        log.info("mappingProduces");
        return "ok";
    }

}
