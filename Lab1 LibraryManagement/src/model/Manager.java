package model;

public class Manager {
    private String managerId;
    private String name;
    private String telephone;
    private String password;

    //构造方法
    public Manager() {}

    public Manager(String managerId, String name, String telephone, String password) {
        this.managerId = managerId;
        this.name = name;
        this.telephone = telephone;
        this.password = password;
    }

    //Getter 和 Setter 方法
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
