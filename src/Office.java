import database.EmployeeDataBase;
import database.WorkUnitDataBase;
import office.Employee;
import office.WorkUnit;
import java.sql.SQLException;
import java.util.Scanner;

public class Office {
    static EmployeeDataBase employeeDataBase;
    static WorkUnit[] workUnits = new WorkUnit[50];

    static {
        try {
            employeeDataBase = new EmployeeDataBase();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static WorkUnitDataBase workUnitDataBase;

    static {
        try {
            workUnitDataBase = new WorkUnitDataBase();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addEmployee() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("first name :");
        String firstName = scanner.next();
        System.out.println("last name :");
        String lastName = scanner.next();
        System.out.println("personalId :");
        String personalId = scanner.next();
        System.out.println("birth date :");
        System.out.println("year :");
        String year = scanner.next();
        System.out.println("month :");
        String month = scanner.next();
        System.out.println("day :");
        String day = scanner.next();
        System.out.println("work unit id :");
        String workUnitId = scanner.next();
        if (CheckValidation.checkString(firstName) && CheckValidation.checkString(lastName) && CheckValidation.checkInt(personalId) &&
                CheckValidation.checkInt(year) && CheckValidation.checkInt(month) && CheckValidation.checkInt(day)) {
            MyDate myDate = new MyDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            if (employeeDataBase.searchEmployee(firstName, lastName, personalId) != 0) {
                System.out.println("this employee is exist in DataBase ");

            } else if (myDate.isValidDate(myDate.getYear(), myDate.getMonth(), myDate.getDay()) && workUnitDataBase.searchWorkUnitId(Integer.parseInt(workUnitId))) {
                Employee employee = new Employee(firstName, lastName, personalId, myDate.toString(), Integer.parseInt(workUnitId));
                int id = employeeDataBase.save(employee);
                if (id != 0) {
                    employee.setId(id);
                    System.out.println("add employee was successfully");
                    workUnits = workUnitDataBase.returnWorkUnit();
                    for (int i = 0; i < workUnits.length; i++) {
                        if (workUnits[i].getId() == Integer.parseInt(workUnitId)) {
                            workUnits[i].setIdEmployees(id + "");
                            workUnitDataBase.updateListEmployee(workUnits[i]);
                            break;
                        }
                    }


                } else {
                    System.out.println("add employee was failed !");
                }
            } else {
                System.out.println(!(myDate.isValidDate(myDate.getYear(), myDate.getMonth(), myDate.getDay())) ? "date is not valid !" : "work unit is not found");

            }
        } else {
            System.out.println("check your information input ! ");

        }


    }

    public static void addWorkUnit() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("name :");
        String name = scanner.next();
        System.out.println("phone number");
        String phoneNumber = scanner.next();
        if (CheckValidation.checkString(name) && CheckValidation.checkInt(phoneNumber)) {
            if (workUnitDataBase.searchWorkUnitName(name)) {
                System.out.println("work unit with this name is exist ");

            } else {
                WorkUnit workUnit = new WorkUnit(name, phoneNumber);
                int index = workUnitDataBase.save(workUnit);

                if (index != 0) {
                    System.out.println("add unit was successfully ");
                } else {
                    System.out.println("add unit was failed ! ");
                }
            }
        } else {
            System.out.println("input is not valid !");

        }

    }

    public static void updateEmployee() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("id employee :");
        String idEmployee = scanner.next();
        if (CheckValidation.checkInt(idEmployee)) {
            if (employeeDataBase.searchEmployee(Integer.parseInt(idEmployee))) {
                System.out.println("first name : ");
                String firstName = scanner.next();
                System.out.println("last name : ");
                String lastName = scanner.next();
                if (CheckValidation.checkString(firstName) && CheckValidation.checkString(lastName)) {
                    if (employeeDataBase.updateEmployeeInformation(firstName, lastName, Integer.parseInt(idEmployee)) != 0) {
                        System.out.println(" update employee was successfully");

                    } else {
                        System.out.println(" update employee was failed !");

                    }
                } else {
                    System.out.println("enter current name please !");

                }
            } else {
                System.out.println("this employee not found !");
            }

        } else {
            System.out.println("enter number please !");
        }
    }

    public static void updateWorkUnit() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("id work unit :");
        String idWorkUnit = scanner.next();
        if (CheckValidation.checkInt(idWorkUnit)) {
            if (workUnitDataBase.searchWorkUnitId(Integer.parseInt(idWorkUnit))) {
                System.out.println("name :");
                String name = scanner.next();
                if (CheckValidation.checkString(name)) {
                    if (workUnitDataBase.updateWorkUnitInformation(name, Integer.parseInt(idWorkUnit)) != 0) {
                        System.out.println("update was successfully");
                    } else {
                        System.out.println("update was failed !");

                    }
                } else {
                    System.out.println("enter valid name please !");

                }
            } else {
                System.out.println("work unit not found !");

            }
        } else {
            System.out.println("enter number id please !");

        }
    }

    public static void showWorkUnit() throws SQLException {
        workUnitDataBase.showWorkUnit();
    }

    public static void showEmployee() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("id work unit :");
        String idWorkUnit = scanner.next();
        if (CheckValidation.checkInt(idWorkUnit)) {
            if (workUnitDataBase.searchWorkUnitId(Integer.parseInt(idWorkUnit))) {
                employeeDataBase.showEmployee(Integer.parseInt(idWorkUnit));
            } else {
                System.out.println("work unit not found !");
            }
        } else {
            System.out.println("enter number please !");

        }
    }
}

