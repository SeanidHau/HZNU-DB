package model;

public class Reader {
    private String readerNumber;
    private String name;
    private String department;
    private String gender;
    private String telephone;


    public Reader() {}

    public Reader(String readerNumber, String name, String department, String gender, String telephone) {
        this.readerNumber = readerNumber;
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.telephone = telephone;
    }

    public String getReaderNumber() {
        return readerNumber;
    }

    public void setReaderNumber(String readerNumber) {
        this.readerNumber = readerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
