import org.apache.coyote.Response;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
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

        try (FileInputStream fis = new FileInputStream(EXCEL_FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip the header row
                }

                String name = row.getCell(0).getStringCellValue();
                String date = row.getCell(1).getStringCellValue();
                String progress = row.getCell(2).getStringCellValue();

                tasks.add(new Task(name, date, progress));
            }

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
        try (FileInputStream fis = new FileInputStream(EXCEL_FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis);
             FileOutputStream fos = new FileOutputStream(EXCEL_FILE_PATH)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip the header row
                }

                Cell cell = row.getCell(0);
                if (cell != null && cell.getStringCellValue().equals(taskName)) {
                    // Update the date and progress
                    Cell dateCell = row.getCell(1);
                    Cell progressCell = row.getCell(2);
                    
                    dateCell.setCellValue(newDate);
                    progressCell.setCellValue(newProgress);
                    break; // Task found and updated, no need to continue
                }
            }

            // Save the changes to the Excel file
            workbook.write(fos);

            return Response.ok("Task updated: " + taskName).build();

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
}
}
