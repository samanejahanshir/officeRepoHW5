package office;

public class WorkUnit {
    private int id;
    private String name;
    private String phoneNumber;
    public String[] idEmployees = new String[100];
    public int indexEmployees = 0;

    public void setId(int id) {
        this.id = id;
    }

    public WorkUnit(String name, String phoneNumber) {

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

    public String getIdEmployees() {
        String listEmployees = "";
        for (int i = 0; i < indexEmployees; i++) {
            listEmployees += idEmployees[i]+ ",";
        }
        return listEmployees;
    }
    public void setIdEmployees(String listEmployee){
        String [] temp =listEmployee.split(",");
        for(int i=0; i<temp.length;i++){
            this.idEmployees[indexEmployees++]=temp[i];
        }



    }
}
