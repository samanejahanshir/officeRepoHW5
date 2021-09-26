package office;

import database.EmployeeDataBase;
public class Employee {
    private  int id;
    private  String firstName;
    private  String lastName;
    private  String personalId;
    private  String birthDate;
    private  int workUnit_id;

    public void setId(int id) {
        this.id = id;
    }

    public Employee(String firstName, String lastName, String personalId, String birthDate, int workUnit_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.birthDate = birthDate;
        this.workUnit_id = workUnit_id;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonalId() {
        return personalId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public int getWorkUnit_id() {
        return workUnit_id;
    }

}
