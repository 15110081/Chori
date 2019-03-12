$(document).ready(function(){
	/**
	 * --------------START: Load color list & set data to table ------------------
	 */
	function loadData(){
		//list
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "color/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
					alert("Table have no data.");
				}
				$.each(data.list,function(key,value){
					//alert(value.createdate);
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.colorcode),
							$('<td>').text(value.description),
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.colorcode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
									
					).appendTo('#listColor');
				});
				action();

				$('#listColor').DataTable({
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
		//When lick create new color
		$('#btnAdd').on('click', function (e) {
			
			$("#dialogAddColor").find("#txtColorCode").val('');
			$("#dialogAddColor").find("#txtDescription").val('');
			

			$("#listColor,#dialogAddColor").find("#errorTxtColorCode").text("");
			$("#listColor,#dialogAddColor").find("#txtColorCode").css("border-color", "");
			
			$("#listColor,#dialogAddColor").find("#errorTxtDescription").text("");
			$("#listColor,#dialogAddColor").find("#txtDescription").css("border-color", "");
			
			$("#dialogAddColor").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Add New Color",
				height: 350,
				width: 450,
				buttons:{
					"Save": {
						text: "Save",
						id : "btnSaveColor",
						click: function(){
						var colorcode= $("#dialogAddColor").find("#txtColorCode").val();
						
						if(!isValidationRequiredFieldAddColor()){
							callMessageDialog("Message", "Please enter color code!");
						}
						
						else if(!isValidationOverRangeWhenAddColor()){
								callMessageDialog("Message", "Color Code or Description  only 20 characters long!");
						}
						else if($.trim(colorcode) !== colorcode){
							callMessageDialog("Message", "Not allow to type the space as a first/last character on Color Code!");
						}
						
						else{
							var colorcode= $("#dialogAddColor").find("#txtColorCode").val();
							var description= $("#dialogAddDestination").find("#txtDescription").val();
							$.ajax({
								dataType: "json",
								type: 'POST',
								data:JSON.stringify({
									colorcode: colorcode
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "color/isExist/",
								success: function(data){
									if(data.isExisted==false){
										var colorcode= $("#dialogAddColor").find("#txtColorCode").val();
										//alert(colorid);
										var description= $("#dialogAddColor").find("#txtDescription").val();
									
										$.ajax({
											dataType: "json",
											type : 'POST',
											data:
												JSON.stringify({
													colorcode: colorcode,
													description: description,
												}),
											contentType: "application/json",
											url: getAbsolutePath() +  "color/add",
											success: function(data){
												$("#dialogAddColor").dialog("close");
												callMessageDialog("Message", "Add Color successfully !");
												$("#listColor").dataTable().fnDestroy();
												$('#listColor tbody').empty();
											
												loadData();
											},
											error: function(){
												callMessageDialog("Message", "Add Color unsuccessfully !");
											}
										});
									}
									else{
										callMessageDialog("Message", "Color Code Already Existed!");
									}
								},
								error: function(){
								}
							});
						}					
					}
					},
					"Cancel": function(){
						$("#dialogAddColor").dialog("close");
					}
				}
			});
			
		});
		//when click Edit
//		$('.btnEdit').on('click', function (e){
//			var colorcode= $(this).data('id');
//			
			
//			
//			$.ajax({
//	    		dataType: "json",
//				type: 'GET',
//				data:{},
//				contentType: "application/json",
//				url: "/Chori/color/detail/"+colorcode,
//				success: function(data){
//					//if load detail success, then open edit dialog
//					if(data.status== "ok"){
//						$("#dialogEditColor").find("#txtColorCode").val(data.currentcolor.colorcode);
//						$("#dialogEditColor").find("#txtDescription").val(data.currentcolor.description);
//						
//						
//					}else{
//						alert('This alert should never show!');
//					}
//				},
//				error: function(){
//					alert('Cant get role detail!');
//				}
//	    	});
//			$("#dialogEditColor").dialog({
//				show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Edit Color",
//				height: 500,
//				width: 700,
//				buttons:{
//					"Save": {
//						text: "Save",
//						id : "btnSaveColor",
//						click: function(){
//						var colorcode= $("#dialogEditColor").find("#txtColorCode").val();
//						var description= $("#dialogEditColor").find("#txtDescription").val();
//						
//						 if(!isValidationOverRangeWhenEditColor()){
//							callMessageDialog("Message", " Description  only 20 characters long!");
//							 }else{
//							$.ajax({
//					    		dataType: "json",
//								type: 'POST',
//								data:
//									JSON.stringify({
//										colorcode: colorcode,
//										description: description,
//									}),
//								contentType: "application/json",
//								url: "/Chori/color/edit",
//								success: function(data){
//									callMessageDialog("Message", "Edit Color successfully!");
//									
//									$("#listColor").dataTable().fnDestroy();
//									$('#listColor tbody').empty();
//									$("#dialogEditColor").dialog("close");
//									loadData();
//								},
//								error: function(){
//									alert('Cant get role detail!');
//								}
//					    	});
//							 }
//						}
//						//
//					},
//					"Cancel": function(){
//						$("#dialogEditColor").dialog("close");
//					}
//				}
//			});
//		});
	

		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    var colorcode= $(this).data('id');//this is RoleID of each record.
		    //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $("#listColor,#dialogEditColor").find("#errorTxtDescription").text("");
			$("#listColor,#dialogEditColor").find("#txtDescription").css("border-color", "");
		    $(".selectOption").val("Options");
		    
		    
		    //If user choose edit option
		    if(valueSelected=="Edit"){
		    	var colorcode= $(this).data('id');
				$.ajax({
		    		dataType: "json",
					type: 'POST',
					data:JSON.stringify({
						colorcode: colorcode
					}),
					contentType: "application/json",
					url: getAbsolutePath() +  "color/detail",
					success: function(data){
					
						//if load detail success, then open edit dialog
						if(data.status== "ok"){
							$("#dialogEditColor").find("#txtColorCode").val(data.currentcolor.colorcode);
							$("#dialogEditColor").find("#txtDescription").val(data.currentcolor.description);
							
							
						}else{
							alert('This alert should never show!');
						}
					},
					error: function(){
						alert('Cant get role detail!');
					}
		    	});
				$("#dialogEditColor").dialog({
					modal: true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Edit Color",
					height: 350,
					width: 450,
					buttons:{
						"Save": {
							text: "Save",
							id : "btnSaveColor",
							click: function(){
							var colorcode= $("#dialogEditColor").find("#txtColorCode").val();
							var description= $("#dialogEditColor").find("#txtDescription").val();
							
							 if(!isValidationOverRangeWhenEditColor()){
								callMessageDialog("Message", " Description  only 200 characters long!");
								 }else{
								$.ajax({
						    		dataType: "json",
									type: 'POST',
									data:
										JSON.stringify({
											colorcode: colorcode,
											description: description,
										}),
									contentType: "application/json",
									url: getAbsolutePath() +  "color/edit",
									success: function(data){
										callMessageDialog("Message", "Edit Color successfully!");
										
										$("#listColor").dataTable().fnDestroy();
										$('#listColor tbody').empty();
										$("#dialogEditColor").dialog("close");
										loadData();
									},
									error: function(){
										alert('Cant get role detail!');
									}
						    	});
								 }
							}
							//
						},
						"Cancel": function(){
							$("#dialogEditColor").dialog("close");
						}
					}
				});
		    };
		    
		    //if user choose delete option
		    if(valueSelected=="Delete"){
		    	var colorcode= $(this).data('id');
		    	$("#deleteColorDialog").find("#messageContent").text('Are you sure you want to delete Color "' + colorcode+'"?');
				
				$("#deleteColorDialog").dialog({
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
									colorcode: colorcode
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "color/delete/",
								success: function(data){
									if(data.status=="ok"){
										if(data.deleteColor== true){
											callMessageDialog("Message", "Delete successfully!");
											$("#listColor").dataTable().fnDestroy();
											$('#listColor tbody').empty();
											loadData();
											$("#deleteColorDialog").dialog("close");
										}else if(data.deleteColor== false){
											callMessageDialog("Message", 'Can\'t Delete Color "'+ colorcode +'"!');
											$("#deleteColorDialog").dialog("close");
										}else{
											callMessageDialog("Message", 'Can\'t Delete Color "'+ colorcode +'"!');
										}
									}
									else{
										callMessageDialog("Message", 'Can\'t Delete Color "'+ colorcode +'"!');
									}
								},
								error: function(){
									callMessageDialog("Message", 'Can\'t Delete Color "'+ colorcode +'"!');
								}
							});
						},
						"NO": function(){
							$("#deleteColorDialog").dialog("close");
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
	
	function isValidationRequiredFieldAddColor(){
		var colorCode= $("#dialogAddColor").find("#txtColorCode").val();
		if(colorCode.trim()== '' || colorCode == null)
			return false;
		return true;
	}
	
	/**
	 * This function OverRange to validate if data over range
	 */
	
	function isValidationOverRangeWhenAddColor(){
		var colorCodeLength= $("#listColor,#dialogAddColor").find("#txtColorCode").val().length;
		if(colorCodeLength>20)
			return false;
		
		var colorDescriptionLength = $("#listColor,#dialogAddColor").find("#txtDescription").val().length;
		if(colorDescriptionLength>200)
			return false;
		return true;
	}
	/**
	 * This function overRange to validate if data over range Edit
	 */
	function isValidationOverRangeWhenEditColor(){
		
		var colorDescriptionLength = $("#listColor,#dialogEditColor").find("#txtDescription").val().length;
		if(colorDescriptionLength>200)
			return false;
		return true;
	}
	
	//validate for create form
	
	$("#dialogAddColor").find("#txtColorCode").on('input propertychange paste',function(e){
		var input= $(this).val();
		//error  overrange colorcode
		if(input.length>20||input.length<1){
			$("#listColor,#dialogAddColor").find("#errorTxtColorCode").text("Your input between 1 - 20 characters");
			$("#listColor,#dialogAddColor").find("#errorTxtColorCode").css("color", "red");
			$("#listColor,#dialogAddColor").find("#txtColorCode").css("border-left", "red 5px solid");
			$('#dialogAddColor,#btnSaveColor').prop( "disabled", true );
		}
		//error Not allow to type the space as a first/last character
		else if($.trim(input) !== input){
			$("#dialogAddColor").find("#errorTxtColorCode").text("Not allow to type the space as a first/last character");
			$("#dialogAddColor").find("#errorTxtColorCode").css("color", "red");
			$("#dialogAddColor").find("#txtColorCode").css("border-left", "red 5px solid");
			$('#dialogAddColor,#btnSaveColor').prop( "disabled", true );
		}
		else{
			$("#dialogAddColor").find("#errorTxtColorCode").text("");
			$("#dialogAddColor").find("#txtColorCode").css("border-left", "blue 5px solid");
			$('#dialogAddColor,#btnSaveColor').prop( "disabled", false );
		}
	})
	//error Existed colorcode
	$("#dialogAddColor").find("#txtColorCode").keyup(function(){
		var colorcode = $("#dialogAddColor").find("#txtColorCode").val();
		$.ajax({
			dataType: "json",
			type: 'POST',
			data:JSON.stringify({
				colorcode: colorcode
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "color/isExist/",
			success: function(data){
				if(data.isExisted==false){
					if(colorcode.length>20){
						$("#listColor,#dialogAddColor").find("#errorTxtColorCode").text("Your input between 1 - 20 characters");
						$("#listColor,#dialogAddColor").find("#errorTxtColorCode").css("color", "red");
						$("#listColor,#dialogAddColor").find("#txtColorCode").css("border-left", "red 5px solid");
						$('#dialogAddColor,#btnSaveColor').prop( "disabled", true );
					}
					else if($.trim(colorcode) !== colorcode){
						$("#dialogAddColor").find("#errorTxtColorCode").text("Not allow to type the space as a first/last character");
						$("#dialogAddColor").find("#errorTxtColorCode").css("color", "red");
						$("#dialogAddColor").find("#txtColorCode").css("border-left", "red 5px solid");
						$('#dialogAddColor,#btnSaveColor').prop( "disabled", true );
					}
					else{
						$("#dialogAddColor").find("#errorTxtColorCode").text("Color code is valid!");
						$("#dialogAddColor").find("#errorTxtColorCode").css("color", "green");
						$("#dialogAddColor").find("#txtColorCode").css("border-left", "blue 5px solid");
						$('#dialogAddColor,#btnSaveColor').prop( "disabled", false );
						if(colorcode.trim() === '' || colorcode == null){
							$("#dialogAddColor").find("#errorTxtColorCode").text("Please enter color code!");
							$("#dialogAddColor").find("#errorTxtColorCode").css("color", "red");
							$("#dialogAddColor").find("#txtCode").css("border-left", "red 5px solid");
						}
					}
				}else{
					$("#dialogAddColor").find("#errorTxtColorCode").text("Color Code Already Existed!");
					$("#dialogAddColor").find("#errorTxtColorCode").css("color", "red");
					$("#dialogAddColor").find("#txtColorCode").css("border-left", "red 5px solid");
					$('#dialogAddColor,#btnSaveColor').prop( "disabled", true );
				}
			},
			error: function(){
				//	alert('Error');
				}
		});
	});
	
	$("#dialogAddColor").find("#txtDescription").keyup(function(){
		var description= $("#dialogAddColor").find("#txtDescription").val();
		//error  overrange txtColor
		if(description.length>200){
			$("#listColor,#dialogAddColor").find("#errorTxtDescription").text("Your input is over range");
			$("#listColor,#dialogAddColor").find("#errorTxtDescription").css("color", "red");
			$("#listColor,#dialogAddColor").find("#txtDescription").css("border-left", "red 5px solid");
			$('#dialogAddColor,#btnSaveColor').prop( "disabled", true );
			
		}else{
			$("#dialogAddColor").find("#errorTxtDescription").text("");
			$("#dialogAddColor").find("#txtDescription").css("border-left", "blue 5px solid");
			$('#dialogAddColor,#btnSaveColor').prop( "disabled", false );
		}
	});
	//validate for edit form
	
	$("#dialogEditColor").find("#txtDescription").keyup(function(){
		var description= $("#dialogEditColor").find("#txtDescription").val();
		//error  overrange txtColor
		if(description.length>200){
			$("#listColor,#dialogEditColor").find("#errorTxtDescription").text("Your input is over range");
			$("#listColor,#dialogEditColor").find("#errorTxtDescription").css("color", "red");
			$("#listColor,#dialogEditColor").find("#txtDescription").css("border-left", "red 5px solid");
			$('#dialogEditColor,#btnSaveColor').prop( "disabled", true );
		}else{
			$("#dialogEditColor").find("#errorTxtDescription").text("");
			$("#dialogEditColor").find("#txtDescription").css("border-left", "blue 5px solid");
			$('#dialogEditColor,#btnSaveColor').prop( "disabled", false );
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
	
	loadData();
});