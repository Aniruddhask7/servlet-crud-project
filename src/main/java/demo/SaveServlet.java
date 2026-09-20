package demo;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        Emp e = new Emp();

        e.setName(name);
        e.setPassword(password);
        e.setEmail(email);
        e.setCountry(country);

        int status = EmpDao.save(e);

        if (status > 0) {

            out.println("<h3>Record saved successfully!</h3>");

            out.println("<a href='index.html'>Add New Employee</a>");
            out.println("<br><br>");
            out.println("<a href='ViewServlet'>View Employees</a>");

        } else {

            out.println("<h3>Sorry! Unable to save record.</h3>");
        }

        out.close();
    }
}