package Model.Entities;
import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;

public class Student{
    private int id;
    private File avatar;
    private String first_name;
    private String last_name;
    private String email;
    private Date DOB;
    private int age;
    private String batch;
    private int auth_id;
    private AuthUser authUser;


    public Student() {
    }

    public Student(int id,File avatar, String first_name, String last_name, String email, Date DOB, int age, String batch, int auth_id) {
        this.id = id;
        this.setAvatar(avatar);
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.DOB = DOB;
        this.age = age;
        this.batch = batch;
        this.auth_id = auth_id;
    }

    public Student( String first_name, String last_name, String email, Date DOB, int age, String batch,
                   int auth_id, String password, String emp_type,boolean isVerified, Timestamp last_login) {
        this.setAuthUser(new AuthUser(email , password , emp_type ,isVerified, last_login));
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.DOB = DOB;
        this.age = age;
        this.batch = batch;
        this.auth_id = auth_id;
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

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
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


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", avatar=" + avatar +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", DOB=" + DOB +
                ", age=" + age +
                ", batch='" + batch + '\'' +
                ", auth_id=" + auth_id +
                ", authUser=" + authUser +
                '}';
    }
}
