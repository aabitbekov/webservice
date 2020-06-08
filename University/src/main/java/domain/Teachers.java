package domain;

public class Teachers{
    private long id;
    private  String fname;
    private  String lname;
    private String s_id;
    private String username;
    private  String password;

    public Teachers(long id, String fname, String lname, String username, String s_id){}

    public Teachers(String fname, String lname, String s_id) {
        this.fname = fname;
        this.lname = lname;
        this.s_id = s_id;
    }

    public Teachers(long id, String fname, String lname, String s_id) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.s_id = s_id;
    }

    public Teachers(long id, String fname, String lname, String s_id, String username, String password) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.s_id = s_id;
        this.username = username;
        this.password = password;
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

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
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

    @Override
    public String toString() {
        return "teachers{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", s_id='" + s_id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
