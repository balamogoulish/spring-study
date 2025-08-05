package mvc1.servlet.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        //이렇게 하면 html은 문자열 내에 있기 대문에 오류를 감지하기가 어렵다.
        PrintWriter w = response.getWriter();
        w.write("""
                <!DOCTYPE html>
                <html>
                <head>
                 <meta charset="UTF-8">
                 <title>Title</title>
                </head>
                <body>
                <form action="/servlet/members/save" method="post">
                 username: <input type="text" name="username" />
                 age: <input type="text" name="age" />
                 <button type="submit">전송</button>
                </form>
                </body>
                </html>
                """);
    }
}
