package Model.Entities;

public class Category {
    private int id;
    private String name;
    private int test_count;

    public Category(int id, String name, int test_count) {
        this.setId(id);
        this.setName(name);
        this.setTest_count(test_count);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTest_count() {
        return test_count;
    }

    public void setTest_count(int test_count) {
        this.test_count = test_count;
    }
}
