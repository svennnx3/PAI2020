import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "CalcServlet", urlPatterns = "/CalcServlet")
public class CalcServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String calcResult = calculate(request);
        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.println("<h1>Wynik kalkulacji:" + calcResult + "</h1>");

        } catch (Exception ex) {

        }
    }

    private String calculate(HttpServletRequest request) {
//        try {
            double number1 = Double.parseDouble(request.getParameter("number1"));
            double number2 = Double.parseDouble(request.getParameter("number2"));
            String operation = request.getParameter("operations");
            String value = number1 + operation + number2 + " = ";
            double res = 0;
            switch (operation) {
                case "+":
                    value += (number1 + number2);
                    break;
                case "-":
                    value += (number1 - number2);
                    break;
                case "*":
                    value += (number1 * number2);
                    break;
                case "/":
                    if (number2 <= 0.1e-10) value += "Dzielenie przez 0 jest niemozliwe";
                    else
                        value += (number1 / number2);
                    break;
                default:
                    value += (number1 + number2);
            }
            return value;
//        } catch (NumberFormatException | NullPointerException ex) {
//            return "Bledne dane";
//        }
    }
}
