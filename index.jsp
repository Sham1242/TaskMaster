<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="TaskMaster.Jersey.Task" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Task Details - Bootdey.com</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css" integrity="sha256-mmgLkCYLUQbXn0B1SRqzHar6dCnv9oZFPEC1g1cwlkk=" crossorigin="anonymous">
</head>

<body>
	<style>
/* Style the body */
body {
  font-family: Arial;
  margin: 0;
}

/* Header/Logo Title */
.header {
  padding: 60px;
  text-align: center;
  background: #1abc9c;
  color: white;
  font-size: 30px;
}

/* Page Content */
.content {padding:20px;}
</style>

<body>

<div class="header">
  <h1>TaskMaster</h1>
  <p>An Ahtsham Sohail Production</p>
</div>

</body>
	
	</div>
    <div class="container">
        <div class="col-md-12 col-12 col-sm-12">
            <div class="card">
                <div class="card-header">
                    <h4>Task Details</h4>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <tbody>
                                <tr>
                                    <th class="text-center">
                                        <div class="custom-checkbox custom-checkbox-table custom-control">
                                            <input type="checkbox" data-checkboxes="mygroup" data-checkbox-role="dad" class="custom-control-input" id="checkbox-all">
                                            <label for="checkbox-all" class="custom-control-label">&nbsp;</label>
                                        </div>
                                    </th>
                                    <th>Task Name</th>
                                    <th>Progress</th>
                                    <th>Due Date</th>
                                    <th>Action</th>
                                </tr>
                                <% List<Task> tasks = (List<Task>) request.getAttribute("tasks");
                                if (tasks != null && !tasks.isEmpty()) {
                                    for (Task task : tasks) { %>
                                <tr>
                                    <td class="p-0 text-center">
                                        <div class="custom-checkbox custom-control">
                                            <input type="checkbox" data-checkboxes="mygroup" class="custom-control-input" id="checkbox-<%= task.getId() %>">
                                            <label for="checkbox-<%= task.getId() %>" class="custom-control-label">&nbsp;</label>
                                        </div>
                                    </td>
                                    <td><%= task.getName() %></td>
                                    <td class="align-middle">
                                        <div class="progress" data-height="4" data-toggle="tooltip" data-original-title="<%= task.getProgress() %>" style="height: 4px;">
                                            <div class="progress-bar bg-success" data-width="<%= task.getProgress() %>" style="width: <%= task.getProgress() %>px;"></div>
                                        </div>
                                    </td>
                                    <td><%= task.getDate() %></td>
                                    <td>
                                        <a class="btn btn-primary btn-action mr-1" href="webapi/myresource/edit">Jersey resource</a>
                                        <a class="btn btn-danger btn-action" data-toggle="tooltip" data-confirm="Are You Sure?|This action can not be undone. Do you want to continue?" data-confirm-yes="alert('Deleted')" data-original-title="Delete"><i class="fas fa-trash"></i></a>
                                    </td>
                                </tr>
                                <% 
                                    }
                                } else { %>
                                <tr>
                                    <td colspan="5">No tasks available</td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript"></script>
</body>
</html>
