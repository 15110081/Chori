$(document).ready(function(){
	/**
	 * --------------START: Load destination list & set data to table ------------------
	 */
	function loadData(){
		
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "destination/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
//					alert("Table have no data.");
				}
				$.each(data.list,function(key,value){
					//alert(value.createdate);
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.destinationcode),
							$('<td>').text(value.description),
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.destinationcode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
									
					).appendTo('#listDestination');
				});
				action();
				$('#listDestination').DataTable({
					"pagingType": "full_numbers"
			    });
			},
			error: function(){
				alert("Can not get data!");
			}
		});
		
	};
	
	
	//handle click event for edit + delete + add button of table
	function action(){
		//When lick create new destination
		$('#btnAdd').on('click', function (e) {
			
			$("#dialogAddDestination").find("#txtDestinationCode").val('');
			$("#dialogAddDestination").find("#txtDescription").val('');
			
			$("#listDestination,#dialogAddDestination").find("#errorTxtDestinationCode").text("");
			$("#listDestination,#dialogAddDestination").find("#txtDestinationCode").css("border-color", "");
			
			$("#listDestination,#dialogAddDestination").find("#errorTxtDescription").text("");
			$("#listDestination,#dialogAddDestination").find("#txtDescription").css("border-color", "");
			
			$("#dialogAddDestination").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Add New Destination",
				height: 350,
				width: 450,
				buttons:{
					"Save": {
						text: "Save",
						id : "btnSaveDestination",
						click: function(){
						var destinationcode= $("#dialogAddDestination").find("#txtDestinationCode").val();
						
						if(!isValidationRequiredFieldAddDestination()){
							callMessageDialog("Message", "Please enter destination!");
						}
						
						else if(!isValidationOverRangeWhenAddDestination()){
								callMessageDialog("Message", "Destination  only 50 characters long!");
							}
						else if($.trim(destinationcode) !== destinationcode){
							callMessageDialog("Message", "Not allow to type the space as a first/last character on Destination !");
						}
						
						else{
							var destinationcode= $("#dialogAddDestination").find("#txtDestinationCode").val();
							var description= $("#dialogAddDestination").find("#txtDescription").val();
							$.ajax({
								dataType: "json",
								type: 'POST',
								data:JSON.stringify({
									destinationcode: destinationcode
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "destination/isExist/",
								success: function(data){
									if(data.isExisted==false){
										$.ajax({
								    		dataType: "json",
											type: 'POST',
											data:
												JSON.stringify({
													destinationcode: destinationcode,
													description: description,
												}),
											contentType: "application/json",
											url: getAbsolutePath() +  "destination/add",
											success: function(data){
												//alert('edit success');
												//console.log(data);
												
												callMessageDialog("Message", "Add Destination successfully !");
												
												$("#listDestination").dataTable().fnDestroy();
												$('#listDestination tbody').empty();
												$("#dialogAddDestination").dialog("close");
												loadData();
											},
											error: function(){
												callMessageDialog("Message", "Add Destination unsuccessfully !");
											}
								    	});
									}else{
										callMessageDialog("Message", "Destination Already Existed!");
									}
								},
								error: function(){
									//	alert('Error');
									}
							});
						}
						}
					},
					"Cancel": function(){
						$("#dialogAddDestination").dialog("close");
					}
				}
			});
			
		});
		
		
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    var destinationcode= $(this).data('id');
		    $("#dialogEditDestination").find("#errorTxtDescription").text("");
			$("#dialogEditDestination").find("#txtDescription").css("border-color", "");
		    $(".selectOption").val("Options");
		    
		    //If user choose edit option
		    if(valueSelected=="Edit"){
		    	
				$.ajax({
		    		dataType: "json",
					type: 'POST',
					data:JSON.stringify({
						destinationcode: destinationcode
					}),
					contentType: "application/json",
					url: getAbsolutePath() +  "destination/detail/",
					success: function(data){
						if(data.status=="ok"){
							$("#dialogEditDestination").find("#txtDestinationCode").val(data.currentdestination.destinationcode);
							$("#dialogEditDestination").find("#txtDescription").val(data.currentdestination.description);
						}else{
							alert('This alert should never show!');
						}
					},
					error: function(){
						alert('Cant get role detail!');
					}
		    	});
				$("#dialogEditDestination").dialog({
					modal: true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Edit Destination",
					height: 350,
					width: 450,
					buttons:{
						"Save": {
							text: "Save",
							id : "btnSaveDestination",
							click: function(){
							var destinationcode= $("#dialogEditDestination").find("#txtDestinationCode").val();
							var description= $("#dialogEditDestination").find("#txtDescription").val();

							 if(!isValidationOverRangeWhenEditDestination()){
									callMessageDialog("Message", " Country  only 50 characters long!");
								 }else{
								$.ajax({
						    		dataType: "json",
									type: 'POST',
									data:
										JSON.stringify({
											destinationcode: destinationcode,
											description: description,
										}),
									contentType: "application/json",
									url: getAbsolutePath() +  "destination/edit",
									success: function(data){
									//	alert('edit success');
										//console.log(data);
										
										callMessageDialog("Message", "Edit Destination successfully!");
										
										$("#listDestination").dataTable().fnDestroy();
										$('#listDestination tbody').empty();
										$("#dialogEditDestination").dialog("close");
										loadData();
										
									},
									error: function(){
										callMessageDialog("Message", "Edit Destination unsuccessfully!");
									}
						    	});
								 }
							}
						},
						"Cancel": function(){
							$("#dialogEditDestination").dialog("close");
						}
					}
				});
		    };
		    
		    //if user choose delete option
		    if(valueSelected=="Delete"){				
		    	var destinationcode= $(this).data('id');
				$("#deleteDestinationDialog").find("#messageContent").text('Are you sure you want to delete Destination  "' + destinationcode + '"?');
		    
				$("#deleteDestinationDialog").dialog({
					modal: true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Delete Confirm",
					height: 200,
					width: 400,
					buttons:{
						"YES": function(){
							$.ajax({
								dataType: "json",
								type: 'POST',
								data:JSON.stringify({
									destinationcode: destinationcode
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "destination/delete/",
								success: function(data){
									if(data.status=="ok"){
										if(data.deleteDestination== true){
											callMessageDialog("Message", "Delete successfully!");
											$("#listDestination").dataTable().fnDestroy();
											$('#listDestination tbody').empty();
											loadData();
											$("#deleteDestinationDialog").dialog("close");
										}else if(data.deleteDestination== false){
											callMessageDialog("Message", 'Can\'t Delete Destination "'+ destinationcode +'"!');
											$("#deleteDestinationDialog").dialog("close");
										}else{
											callMessageDialog("Message", 'Can\'t Delete Destination "'+ destinationcode +'"!');
										}
									}
									else{
										callMessageDialog("Message", 'Can\'t Delete Destination "'+ destinationcode +'"!');
									}
								},
								error: function(){
									callMessageDialog("Message", 'Can\'t Delete Destination "'+ destinationcode +'"!');
								}
							});
						},
						"NO": function(){
							$("#deleteDestinationDialog").dialog("close");
						}
					}
				});
			};
		    //end if user choose delete option
		    
		});
	};
	//------------------------VALIDATION-------------------------
	/**
	 * This function allow to validate before add
	 */
	
	function isValidationRequiredFieldAddDestination(){
		var destinationCode= $("#dialogAddDestination").find("#txtDestinationCode").val();
		if(destinationCode.trim()== '' || destinationCode == null)
			return false;
		return true;
	}
	
	/**
	 * This function overRange to validate if data over range
	 */
	
	function isValidationOverRangeWhenAddDestination(){
		var destinationCodeLength= $("#listDestination,#dialogAddDestination").find("#txtDestinationCode").val().length;
		if(destinationCodeLength>20)
			return false;
		
		var destinationDescriptionLength = $("#dialogAddDestination").find("#txtDescription").val().length;
		if(destinationDescriptionLength>200)
			return false;
		return true;
	}
	
	/**
	 * This function overRange to validate if data over range Edit
	 */
	function isValidationOverRangeWhenEditDestination(){
			
			var destinationDescriptionLength = $("#dialogEditDestination").find("#txtDescription").val().length;
			if(destinationDescriptionLength>200)
				return false;
			return true;
	}
	
	//validate for create form
	$("#dialogAddDestination").find("#txtDestinationCode").on('input propertychange paste',function(e){
		var input= $(this).val();
		//error  overrange destiantioncode
		if(input.length>20||input.length<1){
			$("#dialogAddDestination").find("#errorTxtDestinationCode").text("Your input between 1 - 20 characters");
			$("#dialogAddDestination").find("#errorTxtDestinationCode").css("color", "red");
			$("#dialogAddDestination").find("#txtDestinationCode").css("border-left", "red 5px solid");
			$('#dialogAddDestination,#btnSaveDestination').prop( "disabled", true );
		}
		//error Not allow to type the space as a first/last character
		else if($.trim(input) !== input){
			$("#dialogAddDestination").find("#errorTxtDestinationCode").text("Not allow to type the space as a first/last character");
			$("#dialogAddDestination").find("#errorTxtDestinationCode").css("color", "red");
			$("#dialogAddDestination").find("#txtDestinationCode").css("border-left", "red 5px solid");
			$('#dialogAddDestination,#btnSaveDestination').prop( "disabled", true );
		}
		else{
			$("#dialogAddDestination").find("#errorTxtDestinationCode").text("");
			$("#dialogAddDestination").find("#txtDestinationCode").css("border-left", "blue 5px solid");
			$('#dialogAddDestination,#btnSaveDestination').prop( "disabled", false );
		}
	})
	//error Existed destinationcode
	$("#dialogAddDestination").find("#txtDestinationCode").keyup(function(){
		var destinationcode = $("#dialogAddDestination").find("#txtDestinationCode").val();
		$.ajax({
			dataType: "json",
			type: 'POST',
			data:JSON.stringify({
				destinationcode: destinationcode
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "destination/isExist/",
			success: function(data){
				if(data.isExisted==false){
					if(destinationcode.length>20){
						$("#dialogAddDestination").find("#errorTxtDestinationCode").text("Your input between 1 - 20 characters");
						$("#dialogAddDestination").find("#errorTxtDestinationCode").css("color", "red");
						$("#dialogAddDestination").find("#txtDestinationCode").css("border-left", "red 5px solid");
						$('#dialogAddDestination,#btnSaveDestination').prop( "disabled", true );
					}
					else if($.trim(destinationcode) !== destinationcode){
						$("#dialogAddDestination").find("#errorTxtDestinationCode").text("Not allow to type the space as a first/last character");
						$("#dialogAddDestination").find("#errorTxtDestinationCode").css("color", "red");
						$("#dialogAddDestination").find("#txtDestinationCode").css("border-left", "red 5px solid");
						$('#dialogAddDestination,#btnSaveDestination').prop( "disabled", true );
					}else{
						$("#dialogAddDestination").find("#errorTxtDestinationCode").text("Destination is valid!");
						$("#dialogAddDestination").find("#errorTxtDestinationCode").css("color", "green");
						$("#dialogAddDestination").find("#txtDestinationCode").css("border-left", "blue 5px solid");
						$('#dialogAddDestination,#btnSaveDestination').prop( "disabled", false );
						if(destinationcode.trim() === '' || destinationcode == null){
							$("#dialogAddDestination").find("#errorTxtDestinationCode").text("Please enter destination!");
							$("#dialogAddDestination").find("#errorTxtDestinationCode").css("color", "red");
							$("#dialogAddDestination").find("#txtDestinationCode").css("border-left", "red 5px solid");
						}
					}
				}else{
					$("#dialogAddDestination").find("#errorTxtDestinationCode").text("Destination Already Existed!");
					$("#dialogAddDestination").find("#errorTxtDestinationCode").css("color", "red");
					$("#dialogAddDestination").find("#txtDestinationCode").css("border-left", "red 5px solid");
					$('#dialogAddDestination,#btnSaveDestination').prop( "disabled", true );
				}
			},
			error: function(){
				//	alert('Error');
				}
		});
		
	});
	
	$("#dialogAddDestination").find("#txtDescription").keyup(function(){
		var description= $("#dialogAddDestination").find("#txtDescription").val();
		//error  overrange txtDescription
		if(description.length>200){
			$("#dialogAddDestination").find("#errorTxtDescription").text("Your input is over range");
			$("#dialogAddDestination").find("#errorTxtDescription").css("color", "red");
			$("#dialogAddDestination").find("#txtDescription").css("border-left", "red 5px solid");
			$('#dialogAddDestination,#btnSaveDestination').prop( "disabled", true );
		}else{
			$("#dialogAddDestination").find("#errorTxtDescription").text("");
			$("#dialogAddDestination").find("#txtDescription").css("border-left", "blue 5px solid");
			$('#dialogAddDestination,#btnSaveDestination').prop( "disabled", false );
		}
	});
	//validate for edit form
	
	$("#dialogEditDestination").find("#txtDescription").keyup(function(){
		var description= $("#dialogEditDestination").find("#txtDescription").val();
		if(description.length>200){
			$("#dialogEditDestination").find("#errorTxtDescription").text("Your input is over range");
			$("#dialogEditDestination").find("#errorTxtDescription").css("color", "red");
			$("#dialogEditDestination").find("#txtDescription").css("border-left", "red 5px solid");
			$('#dialogAddDestination,#btnSaveDestination').prop( "disabled", true );
		}else{
			$("#dialogEditDestination").find("#errorTxtDescription").text("");
			$("#dialogEditDestination").find("#txtDescription").css("border-left", "blue 5px solid");
			$('#dialogAddDestination,#btnSaveDestination').prop( "disabled", false );
		}
	})
	/**
	 * This function is used to call and set params for message dialog.
	 */
	function callMessageDialog(title, messageContent){
		$("#messageDialog").find("#messageContent").text(messageContent);
		$("#messageDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: title,
			height: 200,
			width: 350,
			hide: {
				effect: "explode",
				duration: 500
			},
			buttons:{
				"OK": function(){
					$("#messageDialog").dialog("close");
				}
			}
		});
	};
	
	function addSuccessMessageDialog(){
		var title = $("#SuccessTitle").text();
		$("#AddSuccessDialog").dialog({
			modal: true,
			show:{
				effect:"slide",
				duration: 500
			},
			title: title,
			height: 150,
			width: 300,
			hide: {
				effect: "slide",
				duration: 500
			},
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#333");
	}
	
	function addFailedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#AddFailedDialog").dialog({
			modal: true,
			show:{
				effect:"slide",
				duration: 500
			},
			title: title,
			height: 150,
			width: 300,
			hide: {
				effect: "slide",
				duration: 500
			},
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#f5321c");
	}
	
	function editSuccessMessageDialog(){
		var title = $("#SuccessTitle").text();
		$("#EditSuccessDialog").dialog({
			modal: true,
			show:{
				effect:"slide",
				duration: 500
			},
			title: title,
			height: 150,
			width: 300,
			hide: {
				effect: "slide",
				duration: 500
			},
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#333");
	}
	
	function editFailedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#EditFailedDialog").dialog({
			modal: true,
			show:{
				effect:"slide",
				duration: 500
			},
			title: title,
			height: 150,
			width: 300,
			hide: {
				effect: "slide",
				duration: 500
			},
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#f5321c");
	}
	function deleteSuccessMessageDialog(){
		var title = $("#SuccessTitle").text();
		$("#DeleteSuccessDialog").dialog({
			modal: true,
			show:{
				effect:"slide",
				duration: 500
			},
			title: title,
			height: 150,
			width: 300,
			hide: {
				effect: "slide",
				duration: 500
			},
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#333");
	}
	
	function deleteFailedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#DeleteFailedDialog").dialog({
			modal: true,
			show:{
				effect:"slide",
				duration: 500
			},
			title: title,
			height: 150,
			width: 300,
			hide: {
				effect: "slide",
				duration: 500
			},
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#f5321c");
	}
	
	function CanNotGetDataDialogMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#CanNotGetDataDialog").dialog({
			modal: true,
			show:{
				effect:"slide",
				duration: 500
			},
			title: title,
			height: 150,
			width: 300,
			hide: {
				effect: "slide",
				duration: 500
			},
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#f5321c");
	}
	
	function IsExistedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#IsExistedDialog").dialog({
			modal: true,
			show:{
				effect:"slide",
				duration: 500
			},
			title: title,
			height: 150,
			width: 300,
			hide: {
				effect: "slide",
				duration: 500
			},
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#f5321c");
	}
	loadData();
});