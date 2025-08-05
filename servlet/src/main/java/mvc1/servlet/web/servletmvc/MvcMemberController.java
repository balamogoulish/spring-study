package mvc1.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="mvcMemberController",urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //WEB-INF 내에 있는 jsp는 외부에서 직접 jsp를 호출할 수 없다 -> 언제나 controller를 통해 jsp를 호출
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //redirect vs forward
        //redirect: 클라이언트가 redirect 경로로 다시 요청 -> 클라이언트가 인지
        //forward: 서버 내부 호출로 클라이언트 인지 X
        dispatcher.forward(request, response); //다른 서블릿이나 JSP로 이동할 수 있는 기능. 서버 내부에서 다시 호출 발생
    }
}
