import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "CalcServlet", urlPatterns = "/CalcServlet")
public class CalcServlet extends HttpServlet {
    ArrayList<String> calculationHistory = new ArrayList<>();

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
        String calcResult = "";
        if (request.getParameter("number1") != null && request.getParameter("number2") != null) {
            calcResult = calculate(request);
        } else {
            int calcHistorySize = calculationHistory.size();
            calcResult = calculationHistory.get(calcHistorySize-1);
            calculationHistory = new ArrayList<>();
        }
        String goToCalc = "http://localhost:8082/webapp1_war_exploded/index.jsp";
        String gotoCalcServlet = "http://localhost:8082/webapp1_war_exploded/CalcServlet";
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.println("<a href=" + goToCalc + "> Wróć do kalkulatora</a>");
            printWriter.println("<a href=" + gotoCalcServlet + ">Wyczyść historię obliczeń</a>");
            printWriter.println("<h1>Wynik obliczeń:</h1>");
            printWriter.println(calcResult);
            printWriter.println("<h1>Historia obliczeń:");
            for (int i = 0; i < calculationHistory.size(); i++) {
                printWriter.println("<br/> " + calculationHistory.get(i));
            }
        } catch (Exception ex) {

        }
    }

    private String calculate(HttpServletRequest request) {
        double number1 = Double.parseDouble(request.getParameter("number1"));
        double number2 = Double.parseDouble(request.getParameter("number2"));
        String operation = request.getParameter("operations");
        String value = number1 + operation + number2 + " = ";
        double res = 0;
        switch (operation) {
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
        calculationHistory.add(value);
        return value;
    }

    private void deleteCalculationHistory() {
        calculationHistory = new ArrayList<>();
    }
}
