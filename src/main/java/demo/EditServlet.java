package demo;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String sid = request.getParameter("id");

        int id = Integer.parseInt(sid);

        Emp e = EmpDao.getEmployeeById(id);

        out.println("<h1>Update Employee</h1>");

        out.println(
            "<form action='EditServlet2' method='post'>"
        );

        out.println("<table>");

        // ID
        out.println(
            "<tr>" +
            "<td></td>" +
            "<td>" +
            "<input type='hidden' name='id' " +
            "value='" + e.getId() + "'>" +
            "</td>" +
            "</tr>"
        );

        // Name
        out.println(
            "<tr>" +
            "<td>Name:</td>" +
            "<td>" +
            "<input type='text' name='name' " +
            "value='" + e.getName() + "' required>" +
            "</td>" +
            "</tr>"
        );

        // Password
        out.println(
            "<tr>" +
            "<td>Password:</td>" +
            "<td>" +
            "<input type='password' name='password' " +
            "value='" + e.getPassword() + "' required>" +
            "</td>" +
            "</tr>"
        );

        // Email
        out.println(
            "<tr>" +
            "<td>Email:</td>" +
            "<td>" +
            "<input type='email' name='email' " +
            "value='" + e.getEmail() + "' required>" +
            "</td>" +
            "</tr>"
        );

        // Country
        out.println(
            "<tr>" +
            "<td>Country:</td>" +
            "<td>" +
            "<select name='country'>" +

            "<option " +
            (e.getCountry().equals("India") ? "selected" : "") +
            ">India</option>" +

            "<option " +
            (e.getCountry().equals("USA") ? "selected" : "") +
            ">USA</option>" +

            "<option " +
            (e.getCountry().equals("UK") ? "selected" : "") +
            ">UK</option>" +

            "<option " +
            (e.getCountry().equals("Other") ? "selected" : "") +
            ">Other</option>" +

            "</select>" +
            "</td>" +
            "</tr>"
        );

        // Submit
        out.println(
            "<tr>" +
            "<td colspan='2'>" +
            "<input type='submit' value='Edit & Save'>" +
            "</td>" +
            "</tr>"
        );

        out.println("</table>");

        out.println("</form>");

        out.println("<br>");

        out.println("<a href='ViewServlet'>Back to Employees</a>");

        out.close();
    }
}