<%@page import="com.billsModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/bill.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Items Management V10.1</h1>
				<form id="formItem" name="formItem">
					Bill id: <input id="bid" name="bid" type="text" class="form-control form-control-sm"> <br> 
					Month: <input id="mon" name="mon" type="text" class="form-control form-control-sm"> <br> 
					Amount: <input id="amount" name="amount" type="text" class="form-control form-control-sm"> <br> 
					unit rate: <input id="ur" name="ur" type="text" class="form-control form-control-sm"> <br> 
					used units: <input id="uu" name="uu" type="text" class="form-control form-control-sm"> <br> 
					Account number: <input id="acno" name="acno" type="text" class="form-control form-control-sm"> <br> 
					
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divItemsGrid">
					<%
					billsModel itemObj = new billsModel();
					out.print(itemObj.readBill("0","0"));
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>