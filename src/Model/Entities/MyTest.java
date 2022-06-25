package Model.Entities;

public class MyTest {
    private int id;
    private Test test;
    private double marks;
    private boolean isDone;

    public MyTest(int id , Test testData, double marks, boolean isDone) {
        this.setId(id);
        this.setTestData(testData);
        this.setMarks(marks);
        this.setDone(isDone);
    }


    public Test getTestData() {
        return test;
    }

    public void setTestData(Test test) {
        this.test = test;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

