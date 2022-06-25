package Model.Entities;

public class Test {

    private int id;
    private String name;
    private String author;
    private String category;
    private String description;
    private int nofQuizs;
    private int enrolledCount;

    public Test(int id,String name, String author, String category, String description, int nofQuizs, int enrolledCount) {
        this.setName(name);
        this.setAuthor(author);
        this.setCategory(category);
        this.setDescription(description);
        this.setNofQuizs(nofQuizs);
        this.setEnrolledCount(enrolledCount);
        this.setId(id);
    }
    public Test(String name, String author, String category, String description, int nofQuizs, int enrolledCount) {
        this.setName(name);
        this.setAuthor(author);
        this.setCategory(category);
        this.setDescription(description);
        this.setNofQuizs(nofQuizs);
        this.setEnrolledCount(enrolledCount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNofQuizs() {
        return nofQuizs;
    }

    public void setNofQuizs(int nofQuizs) {
        this.nofQuizs = nofQuizs;
    }

    public int getEnrolledCount() {
        return enrolledCount;
    }

    public void setEnrolledCount(int enrolledCount) {
        this.enrolledCount = enrolledCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", nofQuizs=" + nofQuizs +
                ", enrolledCount=" + enrolledCount +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
