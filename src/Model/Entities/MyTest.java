package Model.Entities;

public class MyTest {
    private TestData testData;
    private double marks;
    private boolean isDone;

    public MyTest(TestData testData, double marks, boolean isDone) {
        this.setTestData(testData);
        this.setMarks(marks);
        this.setDone(isDone);
    }


    public TestData getTestData() {
        return testData;
    }

    public void setTestData(TestData testData) {
        this.testData = testData;
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
}

