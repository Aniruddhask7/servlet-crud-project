package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<h1>Employees List</h1>");

        out.println("<a href='index.html'>Add New Employee</a>");

        out.println("<br><br>");

        List<Emp> list = EmpDao.getAllEmployees();

        out.println("<table border='1' width='100%'>");

        out.println(
            "<tr>" +
            "<th>Id</th>" +
            "<th>Name</th>" +
            "<th>Password</th>" +
            "<th>Email</th>" +
            "<th>Country</th>" +
            "<th>Edit</th>" +
            "<th>Delete</th>" +
            "</tr>"
        );

        for (Emp e : list) {

            out.println(
                "<tr>" +

                "<td>" + e.getId() + "</td>" +

                "<td>" + e.getName() + "</td>" +

                "<td>" + e.getPassword() + "</td>" +

                "<td>" + e.getEmail() + "</td>" +

                "<td>" + e.getCountry() + "</td>" +

                "<td>" +
                "<a href='EditServlet?id=" +
                e.getId() +
                "'>Edit</a>" +
                "</td>" +

                "<td>" +
                "<a href='DeleteServlet?id=" +
                e.getId() +
                "' " +
                "onclick=\"return confirm('Are you sure?')\">" +
                "Delete</a>" +
                "</td>" +

                "</tr>"
            );
        }

        out.println("</table>");

        out.close();
    }
}