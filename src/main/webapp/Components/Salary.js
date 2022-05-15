$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});


// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
		{
			url: "SalaryModelAPI",
			type: type,
			data: $("#formItem").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onItemSaveComplete(response.responseText, status);
			}
		});
});


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
	//alert('Alert');
	$("#hidItemIDSave").val($(this).closest("tr").find('td:eq(0)').text());
	$("#empid").val($(this).closest("tr").find('td:eq(0)').text());
	$("#salid").val($(this).closest("tr").find('td:eq(1)').text());
	$("#Amount").val($(this).closest("tr").find('td:eq(2)').text());
	$("#allowance").val($(this).closest("tr").find('td:eq(3)').text());
	$("#fm").val($(this).closest("tr").find('td:eq(4)').text());
});

function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.stringify(response);
		if (status== "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
		{
			url: "SalaryModelAPI",
			type: "DELETE",
			data: "empId=" + $(this).data("itemid"),
			dataType: "text",
			complete: function(response, status) {
				onItemDeleteComplete(response.responseText, status);
			}
		});
});



// CLIENT-MODEL================================================================
function validateItemForm() {
	// CODE
	if ($("#empid").val().trim() == "") {
		return "Insert Employee id.";
	}
	// NAME
	if ($("#salid").val().trim() == "") {
		return "Insert salary id.";
	}
	// PRICE-------------------------------
	if ($("#Amount").val().trim() == "") {
		return "Insert amount.";
	}
	if ($("#allowance").val().trim() == "") {
		return "Insert allowance.";
	}
	if ($("#fm").val().trim() == "") {
		return "Insert financial manager id.";
	}
	// is numerical value
	var tmpPrice = $("#Amount").val().trim();
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value for amount.";
	}
	var al = $("#allowance").val().trim();
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value for allowance.";
	}
	// convert to decimal Account number
	//$("#Amount").val(parseFloat(tmpPrice).toFixed(2));
	
	// convert to decimal Month
	//$("#allowance").val(parseFloat(al).toFixed(2));
	
	return true;
}


function onItemSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.stringify(response);
		if (status == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet);
		} else if (status == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	} $("#hidItemIDSave").val("");
	$("#formItem")[0].reset();
}
