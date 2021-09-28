package database;

import office.WorkUnit;

import java.sql.*;

public class WorkUnitDataBase extends DataBase {
    public WorkUnitDataBase() throws ClassNotFoundException, SQLException {
        super();
    }

    public int save(WorkUnit workUnit) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("INSERT INTO  work_unit (name,phone_number) VALUES ('%s','%s')",
                    workUnit.getName(), workUnit.getPhoneNumber());
            int i = statement.executeUpdate(sqlQuery);
            return i;

        } else {
            return 0;
        }
    }

    public int updateWorkUnitInformation(String name, int id) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("UPDATE work_unit SET name='%s' WHERE id_work_unit=%d", name, id);
            int i = statement.executeUpdate(sqlQuery);
            return i;

        } else {
            return 0;
        }
    }

    public WorkUnit[] returnWorkUnit() throws SQLException {
        WorkUnit[] workUnits = new WorkUnit[50];
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("SELECT * FROM work_unit");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                int index = 0;
                do {
                    workUnits[index] = new WorkUnit(resultSet.getString(2), resultSet.getString(3));
                    workUnits[index].setId(Integer.parseInt(resultSet.getString(1)));
                    workUnits[index].setIdEmployees(resultSet.getString(4) + "");
                    index++;

                } while (resultSet.next());
                return workUnits;
            } else {
                System.out.println("there aren't any thing to show ! ");

            }
        } else {
            System.out.println("connection is null !! ");

        }
        return null;
    }

    public boolean searchWorkUnitName(String workUnitName) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("SELECT id_work_unit FROM work_unit WHERE name='%s'", workUnitName);
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

    public boolean searchWorkUnitId(int workUnitId) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
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

    public int updateListEmployee(WorkUnit workUnit) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("UPDATE work_unit SET id_employees='%s' WHERE id_work_unit=%d", workUnit.getIdEmployees(), workUnit.getId());
            int i = statement.executeUpdate(sqlQuery);
            return i;

        } else {
            return 0;
        }


    }

    public void showWorkUnit() throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("SELECT * FROM work_unit");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                System.out.println("----------  work units  ----------");
                System.out.println("Id  NameUnit  Phone  listEmployee");

                do {
                    System.out.println(resultSet.getString(1) + "   " + resultSet.getString(2) + "   " + resultSet.getString(3) + "   " + resultSet.getString(4));

                } while (resultSet.next());
                System.out.println("-----------------------------------");

            } else {
                System.out.println("there aren't any thing to show ! ");

            }
        } else {
            System.out.println("connection is null !! ");

        }
    }
}
