package database;

import office.Employee;
import office.WorkUnit;

import java.sql.*;

public class WorkUnitDataBase {
    private Connection connection = null;

    public WorkUnitDataBase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/officedatabase", "root", "1234567890");

    }

    public int save(WorkUnit workUnit) throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            String sqlQuery = String.format("INSERT INTO  work_unit ( id_work_unit , name , phone_number , id_employees ) VALUES (%d,%s,%s,%s)",
                    workUnit.getId(), workUnit.getName(), workUnit.getPhoneNumber(), workUnit.getEmployees());
            int i = statement.executeUpdate(sqlQuery);
            return i;

        } else {
            return 0;
        }
    }

    public int updateWorkUnitInformation(WorkUnit workUnit) throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            String sqlQuery = String.format("UPDATE work_unit SET name=%s WHERE id_work_unit=%d", workUnit.getName(), workUnit.getId());
            int i = statement.executeUpdate(sqlQuery);
            return i;

        } else {
            return 0;
        }
    }

    public void showWorkUnit() throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            String sqlQuery = String.format("SELECT * FROM work_unit");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("name"));
                }
            } else {
                System.out.println("there aren't any thing to show ! ");

            }
        } else {
            System.out.println("connection is null !! ");

        }

    }

    public  boolean searchWorkUnitId(int workUnitId) throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            String sqlQuery = String.format("SELECT id_work_unit FROM work_unit WHERE id_work_unit=%d", workUnitId);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                return true;
            } else {
                return false;

            }
        } else {
            return false;
        }
    }
}
