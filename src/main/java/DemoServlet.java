import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "DemoServlet", urlPatterns = "/DemoServlet")
public class DemoServlet extends HttpServlet {
    Date date1;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void init() throws ServletException {
        super.init();
        date1 = new Date();
        sdf.format(date1);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try(PrintWriter printWriter = response.getWriter()) {
            printWriter.println("<!DOCTYPE html>");
            printWriter.println("<html lang='pl'>");
            printWriter.println("<head>");
            printWriter.println("<title>Servlet HelloServlet</title>");
            printWriter.println("<meta charset='UTF-8' >");
            printWriter.println("</head>");
            printWriter.println("<body>");

            printWriter.println("<p>data z processRequest " + dateFormat.format(new Date()) + "</p>");
            printWriter.println("<p>data z pola date1 " + dateFormat.format(date1) + "</p>");

            printWriter.println("<h2>Dane serwera</h2>");
            printWriter.println("<p>request.getServerName(): " + request.getServerName() + "</p>");
            printWriter.println("<p>request.getServerPort(): " + request.getServerPort() + "</p>");
            printWriter.println("<p>request.getRemoteHost(): " + request.getRemoteHost() + "</p>");
            printWriter.println("<p>request.getRemoteAddr(): " + request.getRemoteAddr() + "</p>");
            printWriter.println("<h2>Szczegóły żądania</h2>");
            printWriter.println("<p>request.getMethod(): " + request.getMethod() + " </p>");
            printWriter.println("<p>request.getQueryString(): " + request.getQueryString() + "</p>");

            printWriter.println("</body>");
            printWriter.println("</html>");
        }catch (Exception ex){

        }
    }
}
