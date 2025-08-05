package mvc1.servlet.web.servlet;

import mvc1.servlet.domain.member.Member;
import mvc1.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Member> members = memberRepository.findAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write("""
                <html>
                <head>
                    <meta charset"UTF-8">
                    <title>memberList</title>
                </head>
                <body>
                <a href="/index.html">메인</a>
                <table>
                    <thead>
                        <th>id</th>
                        <th>username</th>
                        <th>age</th>
                    </thead>
                    <tbody>
                """);
        for(Member member : members){
            w.write("<tr>\n"+
                    "<td>"+member.getId()+"</td>\n"+
                    "<td>"+member.getUsername()+"</td>\n"+
                    "<td>"+member.getAge()+"</td>\n"+
                    "</tr>"
                    );
        }
        w.write("""
                </tbody>
                </table>
                </body>
                </html>
                """);

    }
}
