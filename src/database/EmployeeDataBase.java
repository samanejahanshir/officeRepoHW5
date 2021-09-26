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
            if (i != 0) {
                int id = searchEmployee(employee.getFirstName(), employee.getLastName(), employee.getPersonalId());
                return id;
            }else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public int updateEmployeeInformation(String firstName, String lastName, int id) throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            String sqlQuery = String.format("UPDATE employee SET first_name='%s' , last_name='%s' WHERE id_employee=%d", firstName, lastName, id);
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
                System.out.println("---------- Employees in work unit "+workUnitId+" ----------");

                do {
                    System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getString(4)+" ");
                } while (resultSet.next());
                System.out.println("----------------------------------------------");

            } else {
                System.out.println("there aren't any thing to show ! ");

            }
        } else {
            System.out.println("connection is null !! ");

        }

    }

    public boolean searchEmployee(int idEmployee) throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            String sqlQuery = String.format("SELECT id_employee FROM employee WHERE id_employee=%d", idEmployee);
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

    public int searchEmployee(String firstName, String lastName, String personalId) throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            String sqlQuery = String.format("SELECT id_employee FROM employee WHERE first_name='%s' && last_name='%s' && personal_number='%s' ", firstName, lastName, personalId);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
