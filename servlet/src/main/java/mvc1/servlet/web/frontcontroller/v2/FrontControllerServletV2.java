package mvc1.servlet.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc1.servlet.web.frontcontroller.MyView;
import mvc1.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import mvc1.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import mvc1.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 화면을 렌더링하는 기능을 MyView로 분리해서
 * 1. v1과 같이 controller에서 로직 수행
 * 2. MyView를 통해 렌더링
 * => 화면을 출력하는 코드 중복을 줄이고 기능을 확실히 분리함
 */
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2(){
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String requestURI = request.getRequestURI();

        ControllerV2 controller = controllerMap.get(requestURI);
        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}
