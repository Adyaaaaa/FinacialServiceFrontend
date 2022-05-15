<%@page import="com.SalaryModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Salary.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Salary Management </h1>
				<form id="formItem" name="formItem">
					Employee ID: <input id="empid" name="empid" type="text"
						class="form-control form-control-sm"> <br>
					Salary ID:
					<input id="salid" name="salid" type="text"
						class="form-control form-control-sm"> <br> 
					Amount: <input id="Amount" name="Amount" type="text"
						class="form-control form-control-sm"> <br> 
					Allowance: <input id="allowance" name="allowance" type="text"
						class="form-control form-control-sm"> <br> 
					Financial Manager ID: <input id="fm" name="fm" type="text"
						class="form-control form-control-sm"> <br>
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
						
						<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divItemsGrid">
					<%
					SalaryModel salObj = new SalaryModel();
					out.print(salObj.readSalary());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>