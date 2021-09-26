package database;

import office.Employee;

import java.sql.*;

public class EmployeeDataBase {
    private Connection connection = null;

    public EmployeeDataBase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/officedatabase", "root", "1234567890");
    }

    public int save(Employee employee) throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            String sqlQuery = String.format("INSERT INTO employee (first_name , last_name , personal_number , birth_date , id_work_unit) VALUES ('%s','%s','%s','%s',%d)",
                     employee.getFirstName(), employee.getLastName(), employee.getPersonalId(), employee.getBirthDate(), employee.getWorkUnit_id());
            int i = statement.executeUpdate(sqlQuery);
            return i;

        } else {
            return 0;
        }
    }

    public int updateEmployeeInformation(Employee employee) throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            String sqlQuery = String.format("UPDATE employee SET first_name='%s' , last_name='%s' WHERE id_employee=%d", employee.getFirstName(), employee.getLastName(), employee.getId());
            int i = statement.executeUpdate(sqlQuery);
            return i;

        } else {
            return 0;
        }
    }

    public void showEmployee(int workUnitId) throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            String sqlQuery = String.format("SELECT * FROM employee WHERE id_work_unit=%d", workUnitId);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("first_name"));
                }
            } else {
                System.out.println("there aren't any thing to show ! ");

            }
        } else {
            System.out.println("connection is null !! ");

        }

    }
}
