package TaskMaster.Jersey;

public class Task {
    private int id; // Assuming you have an id for each task
    private String name;
    private String date;
    private int progress;

    public Task(int id, String name, String date, int progress) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.progress = progress;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getProgress() {
        return progress;
    }
}
