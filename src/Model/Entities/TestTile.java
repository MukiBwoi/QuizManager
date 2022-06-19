package Model.Entities;

public class TestTile {
    private String name;
    private String author;
    private String category;
    private int marks;
    private boolean isDone;

    public TestTile(String name, String author, String category, int marks, boolean isDone) {
        this.setName(name);
        this.setAuthor(author);
        this.setCategory(category);
        this.setMarks(marks);
        this.setDone(isDone);
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

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}

