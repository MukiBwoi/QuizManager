package Model.Entities;

public class MyTest {
    private int id;
    private Test test;
    private double marks;
    private boolean isDone;
    private int auth_id;

    public MyTest(){}
    public MyTest(int id , Test testData, double marks, boolean isDone,int auth_id) {
        this.setId(id);
        this.setTestData(testData);
        this.setMarks(marks);
        this.setDone(isDone);
        this.setAuth_id(auth_id);
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

    public int getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(int auth_id) {
        this.auth_id = auth_id;
    }
}

