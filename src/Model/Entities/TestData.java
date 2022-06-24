package Model.Entities;

public class TestData {
    private String name;
    private String author;
    private String category;
    private int nofQuizs;
    private int enrolledCount;

    public TestData(String name, String author, String category, int nofQuizs, int enrolledCount) {
        this.setName(name);
        this.setAuthor(author);
        this.setCategory(category);
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
}
