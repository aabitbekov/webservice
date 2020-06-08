package domain;

public class Students {
    private long id;
    private String fname;
    private String lname;
    private String username;
    private  String password;
    private String department;

    public Students(){

    }


    public Students(String fname, String lname){
        this.fname = fname;
        this.lname = lname;
    }

    public Students(long id,String fname, String lname){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
    }

    public Students(String fname, String lname, String username, String password) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
    }

    public Students(long id, String fname, String lname, String username) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
    }

    public Students(long id, String fname, String lname, String username, String password) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
    }

    public Students(String fname, String lname, String username, String password, String department) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.department = department;
    }

    public Students(long id, String fname, String lname, String username, String password, String department) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
