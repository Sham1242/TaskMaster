package TaskMaster.Jersey;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.*;
import java.util.*;

@Path("myresource")
public class MyResource {

    private static final String EXCEL_FILE_PATH = "\\Users\\Sham\\OneDrive\\Desktop\\Projects\\Task Master Jersey\\TaskData.xlsx";

    // ... existing methods ...

    @GET
    @Path("gettasks")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();

        try {
            List<Task> excelTasks = readExcelData(EXCEL_FILE_PATH);
            tasks.addAll(excelTasks);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return tasks;
    }

    @GET
    @Path("edit")
    public Response editTask(@QueryParam("task") String taskName,
                             @QueryParam("newDate") String newDate,
                             @QueryParam("newProgress") String newProgress) {
        try {
            List<Task> excelTasks = readExcelData(EXCEL_FILE_PATH);

            for (Task task : excelTasks) {
                if (task.getName().equals(taskName)) {
                    task.setDate(newDate);
                    task.setProgress(newProgress);
                    saveExcelData(excelTasks);
                    return Response.ok("Task updated: " + taskName).build();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // Utility methods for reading and writing Excel data
    private List<Task> readExcelData(String filePath) throws IOException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    String date = parts[1];
                    String progress = parts[2];
                    Task task = new Task(name, date, progress);
                    tasks.add(task);
                   
                }
            }
        }
        return tasks;
    }

    private void saveExcelData(List<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EXCEL_FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.getName() + "," + task.getDate() + "," + task.getProgress());
                writer.newLine();
            }
        }
    }

    // Task class
    private static class Task {
        private String name;
        private String date;
        private String progress;

        public Task(String name, String date, String progress) {
            this.name = name;
            this.date = date;
            this.progress = progress;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public String getProgress() {
            return progress;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }
    }
}
