package Model;

import java.sql.Timestamp;

public class AuthUser {
    private int id;
    private String email;
    private String password;
    private String emp_type;
    private Timestamp last_login;

    public AuthUser() {
    }

    public AuthUser(String email, String password, String emp_type, Timestamp last_login) {
        this.setEmail(email);
        this.setPassword(password);
        this.setEmp_type(emp_type);
        this.setLast_login(last_login);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmp_type() {
        return emp_type;
    }

    public void setEmp_type(String emp_type) {
        this.emp_type = emp_type;
    }

    public Timestamp getLast_login() {
        return last_login;
    }

    public void setLast_login(Timestamp last_login) {
        this.last_login = last_login;
    }
}
