package mvc1.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@S1f4j

/**
 * application.properties에서 로깅 레벨 설정
 * TRACE > DEBUG > INFO > WARN > ERROR
 * - 개발 서버는 debug 출력
 * - 운영 서버는 info 출력
 */
@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        log.trace("trace-log={}", name);
        log.debug("debug-log={}", name);
        log.info(" info-log={}", name);
        log.warn(" warn-log={}", name);
        log.error("error-log={}", name);

        //로그를 사용하지 않아도 a+b 계산 로직이 먼저 실행됨. 이런 식으로 사용 X
        log.debug("String concat log=" + name);
        return "ok";
    }
}
