package mvc1.servlet.web.frontcontroller.v5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc1.servlet.web.frontcontroller.ModelView;
import mvc1.servlet.web.frontcontroller.MyView;
import mvc1.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import mvc1.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import mvc1.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import mvc1.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import mvc1.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import mvc1.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import mvc1.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import mvc1.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>(); //기존엔 Controller3 or Controller4였지만 둘 다 받을 수 있게 Object 사용
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5(){
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Object handler = getHandler(request);
        if(handler==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handle(request, response, handler);

        MyView view = viewResolver(mv.getViewName());
        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/"+viewName+".jsp");
    }

    //핸들러를 처리할 수 있는 어댑터 조회 -> handler=ControllerV3라면 ControllerV3HandlerAdapter 반환
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for(MyHandlerAdapter adapter: handlerAdapters){
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler = "+handler);
    }

    //핸들러 매칭 -> handlerMappingMap에서 URL에 매팽된 핸들러(컨트롤러)를 찾아 반환
    private Object getHandler(HttpServletRequest request) {
        return handlerMappingMap.get(request.getRequestURI());
    }

    //핸들러 어댑터 초기화
    private void initHandlerAdapters() {
        //v3
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        //v4
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    //핸들러 매핑 초기화
    private void initHandlerMappingMap() {
        //v3
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        //v4
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }
}
