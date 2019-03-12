<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
$(document).ready(function(){

	//to handle option value
	$('input[type=radio][name=rdooption1]').change(function() {
        if (this.value == '1') {
        	$("#txtOption").val(1);
        }
        else if (this.value == '2') {
        	 $("#txtOption").val(2);
        }
    });

    //when submit form
	$("#btnSubmitImportPIFile").on("click",	function(e)
	{
		var txtLotNumberForImportPIFile = $("#txtLotNumberForImportPIFile").val();
	    //prevent submit
		e.preventDefault();

		//checking if pi grid is existed
		$.ajax({
			dataType: "json",
			type: 'POST',
			data: 	
				JSON.stringify({									
					lotnumber: txtLotNumberForImportPIFile
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "importpifile/checkpigridisexistedornot",
			success: function(data){
				//if pigrid is existed -> show message for confirm
				if(data.status==true){
					ShowWarningDialogPIGridIsExisted();
				}
				else if(data.status==false){
					$('form').unbind('submit').submit();
				}
			},
			error: function(){
			}
		});
	});

	//show warning dialog when pi grid is existed
	function ShowWarningDialogPIGridIsExisted(){
		$("#dialogWarningPiGridIsExisted").dialog({
			modal: true,
			show:{
				effect:"slide",
				duration: 500
			},
			title: "Message",
			height: 300,
			width: 350,
			hide: {
				effect: "slide",
				duration: 500
			},
			buttons:{
				"OK": function(){
					//continue to submit form
					$('form').unbind('submit').submit();
					$(this).dialog("close");
				},
				"Cancel": function(e){
					
					$(this).dialog("close");
				}
			}
		});
	}

	
});
</script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Excel Import Demo</div>
		</div>

		<div class="block-content collapse in">
			<div class="span12">
				<form:form method="POST" modelAttribute="excelImportDemo2"
					enctype="multipart/form-data" class="form-horizontal">

					<div class="form-group">
						<div class="col-sm-6">
							<form:input type="text" path="lotnumber" id="txtLotNumberForImportPIFile" value="Lot4"
								class="form-control input-sm"/>
						</div>
					</div>
					<br/>
					<div class="form-group">
						<div class="col-sm-6">
						Select import option:
							<br/>
				        	<input type="radio" name="rdooption1" value="1" checked="checked"/>Option 1: Only insert data when all row of import file is valid
				        	<br/>
				        	<input type="radio" name="rdooption1" value="2"/>Option 2: Insert data in which row of import file is valid
				        	<form:input type="number" path="option" value="1" id="txtOption" style="display: none;"/>
						</div>
					</div>
					
				<br/>
        
					<div class="form-group">
						<div class="col-sm-6">
							<form:input type="file" path="file" id=""
								class="form-control input-sm" />
						</div>
					</div>
					<br />
					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Import"
								class="btn btn-primary btn-sm" id="btnSubmitImportPIFile">
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<div id="dialogWarningPiGridIsExisted" style="display: none;" class="form-horizontal">
	<span>This PI already have pi grid data. Do you want to override it?</span>
</div>