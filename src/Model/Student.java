package Model;
import java.sql.Date;

public class Student {
    int id;
    String first_name;
    String last_name;
    String email;
    Date DOB;
    int age;
    String batch;
    int auth_id;

    public Student() {
    }

    public Student(int id, String first_name, String last_name, String email, Date DOB, int age, String batch, int auth_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.DOB = DOB;
        this.age = age;
        this.batch = batch;
        this.auth_id = auth_id;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", DOB=" + DOB +
                ", age=" + age +
                ", batch='" + batch + '\'' +
                ", auth_id=" + auth_id +
                '}';
    }

}
