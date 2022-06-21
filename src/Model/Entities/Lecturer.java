package Model.Entities;

import java.io.File;
import java.sql.Timestamp;

public class Lecturer {
    private int id;
    private File avatar;
    private String first_name;
    private String last_name;
    private String email;
    private String branch;
    private int auth_id;
    private AuthUser authUser;

    public Lecturer() {
    }

    public Lecturer(int id ,File avatar, String first_name, String last_name, String email, String branch, int auth_id) {
        this.setId(id);
        this.setAvatar(avatar);
        this.setFirst_name(first_name);
        this.setLast_name(last_name);
        this.setEmail(email);
        this.setBranch(branch);
        this.setAuth_id(auth_id);
    }

    public Lecturer(String first_name, String last_name, String email, String branch, int auth_id ,
                    String password, String emp_type,boolean isVerified, Timestamp last_login) {
        this.authUser = new AuthUser(email , password , emp_type,isVerified, last_login);
        this.setFirst_name(first_name);
        this.setLast_name(last_name);
        this.setEmail(email);
        this.setBranch(branch);
        this.setAuth_id(auth_id);
    }



    @Override
    public String toString() {
        return "Lecturer{" +
                "id=" + getId() +
                ", first_name='" + getFirst_name() + '\'' +
                ", last_name='" + getLast_name() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", branch='" + getBranch() + '\'' +
                ", auth_id=" + getAuth_id() +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(int auth_id) {
        this.auth_id = auth_id;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }
}
