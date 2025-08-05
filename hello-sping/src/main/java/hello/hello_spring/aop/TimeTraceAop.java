package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * AOP: 공통 관심 사항과 핵심 관심 사항 분리
 * 시간 측정은 공통 관심 사항임
 * joinPoint를 통해 callback 함수처럼 사용할 수 있음
 * Around로 타겟 범위를 설정할 수 있음
 *
 * 장점
 * - 핵심 관심 사항을 깔끔하게 유지
 * - 변경이 필요하면 이 로직만 변경하면 됨
 * - 원하는 적용 대상을 선택할 수 있음
 */

@Component
@Aspect
public class TimeTraceAop {

    @Around("execution(* hello.hello_spring..*(..))") //
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start =  System.currentTimeMillis();
        System.out.println("START: "+ joinPoint.toString());
        try{
            return joinPoint.proceed();
        } finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+joinPoint.toString()+" "+timeMs+"ms");
        }

    }
}
