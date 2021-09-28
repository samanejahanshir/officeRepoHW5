import java.sql.SQLException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        boolean exit = false;
        while (exit == false) {
            System.out.println("------------------ Menu -----------------");
            System.out.println("1.add employee\n2.add work unit\n3.update employee\n4.update work unit\n5.show work unit\n6.show employee\n7.exit");
            System.out.println("-----------------------------------------");
            Office office = new Office();
            Scanner scanner = new Scanner(System.in);
            String selectNumberMenu = scanner.next();
            if (CheckValidation.checkInt(selectNumberMenu)) {
                switch (Integer.parseInt(selectNumberMenu)) {
                    case 1:
                        office.addEmployee();
                        break;
                    case 2:
                        office.addWorkUnit();
                        break;
                    case 3:
                        office.updateEmployee();
                        break;
                    case 4:
                        office.updateWorkUnit();
                        break;
                    case 5:
                        office.showWorkUnit();
                        break;
                    case 6:
                        office.showEmployee();
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

}
