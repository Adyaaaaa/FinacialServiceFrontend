<%@page import="com.PaymentModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>All Payments</h1>
				
				
				<br>
				<div id="divItemsGrid">
					<%
					PaymentModel itemObj = new PaymentModel();
					out.print(itemObj.readPayment());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>