package Model;

public class Lecturer {
    int id;
    String first_name;
    String last_name;
    String email;
    String branch;
    int auth_id;

    public Lecturer() {
    }

    public Lecturer(int id, String first_name, String last_name, String email, String branch, int auth_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.branch = branch;
        this.auth_id = auth_id;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", branch='" + branch + '\'' +
                ", auth_id=" + auth_id +
                '}';
    }
}
