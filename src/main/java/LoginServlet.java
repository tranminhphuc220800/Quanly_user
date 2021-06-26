import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns= "/mphuc")
public class LoginServlet extends HttpServlet {
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>DUOC ROI NHA</h1>");
        out.flush();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html:charset=utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDaoImpl userDao = new UserDaoImpl();
        boolean isSuccess = userDao.login(password, username);

        if(isSuccess){
            response.getWriter().write("FUCKING SHIT");
        }else {
            response.getWriter().write("NO FUCKING SHIT");
        }

    }
}
