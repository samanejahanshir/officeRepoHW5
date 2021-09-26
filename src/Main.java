import database.EmployeeDataBase;
import database.WorkUnitDataBase;
import office.Employee;
import office.WorkUnit;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static EmployeeDataBase employeeDataBase;

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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        boolean exit = false;
        while (exit == false) {
            System.out.println("1.add employee\n2.add work unit\n3.update employee\n4.update work unit\n5.show work unit\n6.show employee\n7.exit");
            Scanner scanner = new Scanner(System.in);
            String selectNumberMenu = scanner.next();
            if (CheckValidation.checkInt(selectNumberMenu)) {
                switch (Integer.parseInt(selectNumberMenu)) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        addWorkUnit();
                        break;
                    case 3:
                        updateEmployee();
                        break;
                    case 4:
                        updateWorkUnit();
                        break;
                    case 5:
                        showWorkUnit();
                        break;
                    case 6:
                        showEmployee();
                        break;
                    case 7:
                        exit = true;
                        break;
                    default:
                        System.out.println("please enter number between 1 and 7 !!");
                        break;
                }
            } else {
                System.out.println("please enter number !!");
            }
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
            if (myDate.isValidDate(myDate.getYear(), myDate.getMonth(), myDate.getDay()) && workUnitDataBase.searchWorkUnitId(Integer.parseInt(workUnitId))){
                Employee employee=new Employee(firstName,lastName,personalId,myDate.toString(),Integer.parseInt(workUnitId));
                int index=employeeDataBase.save(employee);
                if(index!=0){
                    System.out.println("add employee was successfully");

                }else {
                    System.out.println("add employee was failed !");
                }
            }else {
                System.out.println(!(myDate.isValidDate(myDate.getYear(), myDate.getMonth(), myDate.getDay()))?"date is not valid !":"work unit is not found");

            }
        } else {
            System.out.println("check your information input ! ");

        }


    }

    public static void addWorkUnit() {


    }

    public static void updateEmployee() {

    }

    public static void updateWorkUnit() {

    }

    public static void showWorkUnit() {

    }

    public static void showEmployee() {

    }
}
