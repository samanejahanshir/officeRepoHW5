package office;

import office.Employee;

public class WorkUnit {
    private int id;
    private  String name;
    private  String phoneNumber;
    private Employee[] employees=new  Employee[100];
    private  int indexEmployees=0;

    public WorkUnit(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmployees() {
       String listEmployees="";
       for(int i=0;i<indexEmployees;i++){
           listEmployees+=employees[i].getId()+",";
       }
        return listEmployees;
    }
}
