package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpDao {

    // Database Connection
    public static Connection getConnection() {

        Connection con = null;

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "hr",
                    "pass"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }


    // CREATE
    public static int save(Emp e) {

        int status = 0;

        try {

            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO user905 " +"(id, name, password, email, country) " +"VALUES (user905_seq.NEXTVAL, ?, ?, ?, ?)");

            ps.setString(1, e.getName());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getCountry());

            status = ps.executeUpdate();

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }


    // UPDATE
    public static int update(Emp e) {

        int status = 0;

        try {

            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE user905 " +
                "SET name=?, password=?, email=?, country=? " +
                "WHERE id=?"
            );

            ps.setString(1, e.getName());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getCountry());
            ps.setInt(5, e.getId());

            status = ps.executeUpdate();

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }


    // DELETE
    public static int delete(int id) {

        int status = 0;

        try {

            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM user905 WHERE id=?"
            );

            ps.setInt(1, id);

            status = ps.executeUpdate();

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }


    // READ ONE
    public static Emp getEmployeeById(int id) {

        Emp e = new Emp();

        try {

            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM user905 WHERE id=?"
            );

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                e.setPassword(rs.getString("password"));
                e.setEmail(rs.getString("email"));
                e.setCountry(rs.getString("country"));
            }

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return e;
    }


    // READ ALL
    public static List<Emp> getAllEmployees() {

        List<Emp> list = new ArrayList<>();

        try {

            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM user905 ORDER BY id"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Emp e = new Emp();

                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                e.setPassword(rs.getString("password"));
                e.setEmail(rs.getString("email"));
                e.setCountry(rs.getString("country"));

                list.add(e);
            }

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }
}