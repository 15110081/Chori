<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="resources/userJs/garmentstyle/dataTables.rowsGroup.js"></script>
<script>
$(document).ready(function(){
	function loadColorErrorList()
	{
		//list
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "importpifile/colorerrorlist",
			contentType: "application/json",
			success: function(data){
				if(data.colorerrorlist.length<1){
					return;
				}
				else
				{
					$("#listColorCover").html("");
					//create list assign table
					$("#listColorCover").append('<table class="table table-striped table-bordered display responsive"'+
								'id="listColor">'+
								'<thead>'+
									'<tr>'+
										'<th>Missing Color</th>' +		
										'<th>Error Position</th>' +					
									'</tr>'+
								'</thead>'+
							'</table>'
								+ '<a href="<c:url value='/listcolor'/>" target="_blank">Go to Color Management Page</a>');
					$.each(data.colorerrorlist,function(key,value){
						if(value!=null)
						{
							$('<tr>').append(
									$('<td>').text(value.split("___",1)),
									$('<td>').text(value.split("___")[1])						
							).appendTo('#listColor');
						}
					});

					$('#listColor').DataTable({
						"paging":   false,
						"searching":false,
						"ordering": false,
				        "info":     false,
				        rowsGroup: [0]
				    });
				}
			},
			error: function(){
			}
		});
	};
	function loadGarmentStyleErrorList()
	{
		//list
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "importpifile/garmentstyleerrorlist",
			contentType: "application/json",
			success: function(data){
				
				if(data.garmentstyleerrorlist.length<1){
					return;
				}
				else
				{
					$("#listGarmentStyleCover").html("");
					//create list assign table
					$("#listGarmentStyleCover").append('<table class="table table-striped table-bordered display responsive"'+
								'id="listGarmentStyle">'+
								'<thead>'+
									'<tr>'+
										'<th>Missing Garment Style</th>' +	
										'<th>Error Position</th>' +						
									'</tr>'+
								'</thead>'+
							'</table>'
								+'<a href="<c:url value='/listGarmentstyle'/>" target="_blank">Go to Garment Style Management Page</a>');
					$.each(data.garmentstyleerrorlist,function(key,value){
						if(value!=null)
						{
							$('<tr>').append(
									$('<td>').text(value.split("___",1)),	
									$('<td>').text(value.split("___")[1])								
							).appendTo('#listGarmentStyle');
						}
					});
					
					$('#listGarmentStyle').DataTable({
						"paging":   false,
						"searching":false,
						"ordering": false,
				        "info":     false,
				        rowsGroup: [0]
				    });
				}
			},
			error: function(){
			}
		});
	};
	function loadSizeErrorList()
	{
		//list
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "importpifile/sizeerrorlist",
			contentType: "application/json",
			success: function(data){
				if(data.sizeerrorlist.length<1){
					return;
				}
				else
				{
					$("#listSizeCover").html("");
					//create list assign table
					$("#listSizeCover").append('<table class="table table-striped table-bordered display responsive"'+
								'id="listSize">'+
								'<thead>'+
									'<tr>'+
										'<th>Missing Size Name</th>' +		
										'<th>Garment Style</th>' +		
										'<th>Error Position</th>' +				
									'</tr>'+
								'</thead>'+
							'</table>'
								+'<a href="<c:url value='/Size'/>" target="_blank">Go to Size Management Page</a>');
					
					$.each(data.sizeerrorlist,function(key,value){
						if(value!=null)
						{
							$('<tr>').append(
									$('<td>').text(value.sizename.split("___",1)),
									$('<td>').text(value.garmentstyle.split("@@@")[1].split("___",1)),	
									$('<td>').text(value.sizename.split("___")[1])					
							).appendTo('#listSize');
						}
					});
					$('#listSize').DataTable({
						"paging":   false,
						"searching":false,
						"ordering": false,
				        "info":     false,
				        rowsGroup: [1]
				    });
				}
			},
			error: function(){
			}
		});
	};

	//check if all data in import file is existed, then show import success, if not then show import failed
	function loadForm()
	{
		//list
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "importpifile/lstvalidatestatus",
			contentType: "application/json",
			success: function(data){
				if(data.status == true){
					$("#ImportMissingForm").hide();
					$("#ImportSuccessForm").show();			
				}
				else
				{
					$("#ImportSuccessForm").hide();
					$("#ImportMissingForm").show();
					loadColorErrorList();
					loadGarmentStyleErrorList();
					loadSizeErrorList();
				}
			},
			error: function(){
			}
		});
	};


	$("#ImportSuccessForm").hide();
	$("#ImportMissingForm").hide();	
	loadForm();	

});
</script>


<div class="row-fluid" id="ImportMissingForm">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Some data of import file not existed! (See more below)</div>			
		</div>
		<div class="form-group" style="margin-left:20px;margin-top:10px">
	      	<div class="col-sm-9">
	      		<a href="<c:url value='/listpi'/>">Go back to PI List</a>
     		</div>     		
		 </div>
		 <div class="block-content collapse in">
			<div class="span12">
		<div class="form-group">
			<div class="span4">
				<div id="listSizeCover">
				<div style="display:none">
				<table class="table table-striped table-bordered display responsive"
					id="listSize">
					<thead>
						<tr>
							<th>Missing Size</th>	
							<th>Error Position</th>				
						</tr>
					</thead>
				</table>				
				<a href="<c:url value='/Size'/>" target="_blank">Go to Size Management Page</a>
				</div>
				</div>
			</div>
			<div class="span4">
				<div id="listColorCover">
				<div style="display:none">
				<table class="table table-striped table-bordered display responsive"
					id="listColor">
					<thead>
						<tr>
							<th>Missing Color</th>
							<th>Error Position</th>					
						</tr>
					</thead>
				</table>
				<a href="<c:url value='/listcolor'/>" target="_blank">Go to Color Management Page</a>
				</div>
				</div>
			</div>
			<div class="span4">
				<div id="listGarmentStyleCover">
				<div style="display:none">
				<table class="table table-striped table-bordered display responsive"
					id="listGarmentStyle">
					<thead>
						<tr>
							<th>Missing Garment Style</th>	
							<th>Error Position</th>				
						</tr>
					</thead>
				</table>			
				<a href="<c:url value='/listGarmentstyle'/>" target="_blank">Go to Garment Style Management Page</a>
				</div>
				</div>
			</div>
		</div>
			</div>
		</div>
		
	</div>
	<!-- /block -->
</div>

<div class="row-fluid" id="ImportSuccessForm">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Import Success</div>
		</div>
	</div>
</div>


