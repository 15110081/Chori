$(document).ready(function(){
	/**
	 * --------------START: Load AccessoryGroup list & set data to table ------------------
	 */
	function loadData(){
		//list
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "accessorygroup/list",
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
							$('<td>').text(value.accessorygroupCode),
							$('<td>').text(value.description),
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorygroupCode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
									
					).appendTo('#listAccessoryGroup');
				});
				action();

				$('#listAccessoryGroup').DataTable({
					"pagingType": "full_numbers"
			    });
			},
			error: function(){
				alert("Can not get data!");
			}
		});
	};
	
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
	
	
	//handle click event for edit + delete + add button of table
	function action(){
		//When lick create new AccessoryGroup
		$('#btnAdd').on('click', function (e) {
			
			$("#dialogAddAccessoryGroup").find("#txtAccessoryGroupCode").val('');
			$("#dialogAddAccessoryGroup").find("#txtDescription").val('');
			

			$("#listAccessoryGroup,#dialogAddAccessoryGroup").find("#errorTxtAccessoryGroupCode").text("");
			$("#listAccessoryGroup,#dialogAddAccessoryGroup").find("#txtAccessoryGroupCode").css("border-color", "");
			
			$("#listAccessoryGroup,#dialogAddAccessoryGroup").find("#errorTxtDescription").text("");
			$("#listAccessoryGroup,#dialogAddAccessoryGroup").find("#txtDescription").css("border-color", "");
			
			$("#dialogAddAccessoryGroup").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Add New Accessory Group",
				height: 400,
				width: 500,
				buttons:{
					"Save": {
						text: "Save",
						id : "btnSaveAccessoryGroup",
						click: function(){
						var accessorygroupCode= $("#dialogAddAccessoryGroup").find("#txtAccessoryGroupCode").val();
						
						
							var accessorygroupCode= $("#dialogAddAccessoryGroup").find("#txtAccessoryGroupCode").val();
							var description= $("#dialogAddAccessoryGroup").find("#txtDescription").val();
							if(!isValidationRequiredFieldAddAccessoryGroup()){
								callMessageDialog("Message", "Please insert required form!");

							}else{
							$.ajax({
								dataType: "json",
								type: 'POST',
								data:JSON.stringify({
									accessorygroupCode : accessorygroupCode
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "accessorygroup/isExist/",
								success: function(data){
									if(data.isExisted==false){
										var accessorygroupCode= $("#dialogAddAccessoryGroup").find("#txtAccessoryGroupCode").val();
										var description= $("#dialogAddAccessoryGroup").find("#txtDescription").val();
									
										$.ajax({
											dataType: "json",
											type : 'POST',
											data:
												JSON.stringify({
													accessorygroupCode: accessorygroupCode,
													description: description,
												}),
											contentType: "application/json",
											url: getAbsolutePath() +  "accessorygroup/add",
											success: function(data){
												callMessageDialog("Message", "Add  accessory group successfully!");
												$("#listAccessoryGroup").dataTable().fnDestroy();
												$('#listAccessoryGroup tbody').empty();
												$("#dialogAddAccessoryGroup").dialog("close");
												loadData();
											},
											error: function(){
												
												callMessageDialog("Message", "Add accessory group unccessfully!");
											}
										});
									}
									else{
										callMessageDialog("Message", "Accessory group code is existed!");
									}
								},
								error: function(){
								}
							});
							}
						}					
					},
				
					"Cancel": function(){
						$("#dialogAddAccessoryGroup").dialog("close");
					}
				}
				
			});
			
		});
		//when click Edit
//		$('.btnEdit').on('click', function (e){
//			var accessorygroupCode= $(this).data('id');
//			
//			$("#listAccessoryGroup,#dialogEditAccessoryGroup").find("#errorTxtDescription").text("");
//			$("#listAccessoryGroup,#dialogEditAccessoryGroup").find("#txtDescription").css("border-color", "");
//			
//			$.ajax({
//	    		dataType: "json",
//				type: 'GET',
//				data:{},
//				contentType: "application/json",
//				url: "/Chori/accessorygroup/detail/"+accessorygroupCode,
//				success: function(data){
//					//if load detail success, then open edit dialog
//					if(data.status== "ok"){
//						$("#dialogEditAccessoryGroup").find("#txtAccessoryGroupCode").val(data.currentAccessoryGroup.accessorygroupCode);
//						$("#dialogEditAccessoryGroup").find("#txtDescription").val(data.currentAccessoryGroup.description);
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
//			$("#dialogEditAccessoryGroup").dialog({
//				show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Edit AccessoryGroup",
//				height: 500,
//				width: 700,
//				buttons:{
//					"Save": {
//						text: "Save",
//						id : "btnSaveColor",
//						click: function(){
//						var accessorygroupCode= $("#dialogEditAccessoryGroup").find("#txtAccessoryGroupCode").val();
//						var description= $("#dialogEditAccessoryGroup").find("#txtDescription").val();
//						
//						 if(!isValidationOverRangeWhenEditAccessoryGroup()){
//							callMessageDialog("Message", " Description  only 20 characters long!");
//							 }else{
//							$.ajax({
//					    		dataType: "json",
//								type: 'POST',
//								data:
//									JSON.stringify({
//										accessorygroupCode: accessorygroupCode,
//										description: description,
//									}),
//								contentType: "application/json",
//								url: "/Chori/accessorygroup/edit",
//								success: function(data){
//									callMessageDialog("Message", "Edit AccessoryGroup successfully!");
//									
//									$("#listAccessoryGroup").dataTable().fnDestroy();
//									$('#listAccessoryGroup tbody').empty();
//									$("#dialogEditAccessoryGroup").dialog("close");
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
//						$("#dialogEditAccessoryGroup").dialog("close");
//					}
//				}
//			});
//		});
//		//When click Delete
//		$('.btnDelete').on('click', function (e) {				
//			var accessorygroupCode= $(this).data('id');
//			$("#deleteAccessoryGroupDialog").find("#messageContent").text('Are you sure you want to delete AccessoryGroup "' + accessorygroupCode+'"?');
//	    
//			$("#deleteAccessoryGroupDialog").dialog({
//				show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Delete AccessoryGroup Confirm",
//				height: 200,
//				width: 400,
//				buttons:{
//					"OK": function(){
//						$.ajax({
//							dataType: "json",
//							type: 'POST',
//							data:{},
//							contentType: "application/json",
//							url: "/Chori/accessorygroup/delete/" + accessorygroupCode,
//							success: function(data){
//								callMessageDialog("Message", "Delete AccessoryGroup successfully!");
//								$("#listAccessoryGroup").dataTable().fnDestroy();
//								$('#listAccessoryGroup tbody').empty();
//								loadData();
//								$("#deleteAccessoryGroupDialog").dialog("close");
//							}
//						});
//					},
//					"NO": function(){
//						$("#deleteAccessoryGroupDialog").dialog("close");
//					}
//				}
//			});
//		});
		
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
			var accessorygroupCode= $(this).data('id');
		    $(".selectOption").val("Options");
		    if(valueSelected=="Edit"){
			
			$("#listAccessoryGroup,#dialogEditAccessoryGroup").find("#errorTxtDescription").text("");
			$("#listAccessoryGroup,#dialogEditAccessoryGroup").find("#txtDescription").css("border-color", "");
			$.ajax({
				dataType: "json",
				type: 'POST',
				data:
					JSON.stringify({
						accessorygroupCode : accessorygroupCode
					}),
				url: getAbsolutePath() +  "accessorygroup/detail/",
				contentType: "application/json",
				success: function(data){
					//if load detail success, then open edit dialog
					if(data.status== "ok"){
						$("#dialogEditAccessoryGroup").find("#txtAccessoryGroupCode").val(data.currentAccessoryGroup.accessorygroupCode);
						$("#dialogEditAccessoryGroup").find("#txtDescription").val(data.currentAccessoryGroup.description);
						
						
					}else{
						alert('This alert should never show!');
					}
				},
				error: function(){
					alert('Cant get role detail!');
				}
	    	});
			$("#dialogEditAccessoryGroup").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Edit Accessory Group",
				height: 400,
				width: 500,
				buttons:{
					"Save": {
						text: "Save",
						id : "btnSave",
						click: function(){
						var accessorygroupCode= $("#dialogEditAccessoryGroup").find("#txtAccessoryGroupCode").val();
						var description= $("#dialogEditAccessoryGroup").find("#txtDescription").val();
						
						 
							$.ajax({
					    		dataType: "json",
								type: 'POST',
								data:
									JSON.stringify({
										accessorygroupCode: accessorygroupCode,
										description: description,
									}),
								contentType: "application/json",
								url: getAbsolutePath() +  "accessorygroup/edit",
								success: function(data){
									callMessageDialog("Message", "Edit accessory group successfully!");
									
									$("#listAccessoryGroup").dataTable().fnDestroy();
									$('#listAccessoryGroup tbody').empty();
									$("#dialogEditAccessoryGroup").dialog("close");
									loadData();
								},
								error: function(){
									callMessageDialog("Message", "Edit accessory group failed!");

								}
					    	});
							 }					
						},
						//
				"Cancel": function(){
					$("#dialogEditAccessoryGroup").dialog("close");
				}
					},
			
					
				
			});
		}
		//When click Delete
		    if(valueSelected=="Delete"){		
			var accessorygroupCode= $(this).data('id');
			$("#deleteAccessoryGroupDialog").find("#messageContent").text('Are you sure you want to delete accessory group "' + accessorygroupCode+'"?');
	    
			$("#deleteAccessoryGroupDialog").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Delete Confirm",
				height: 250,
				width: 350,
				buttons:{
					"OK": function(){
						$.ajax({
							dataType: "json",
							type: 'POST',
							data:
								JSON.stringify({
									accessorygroupCode : accessorygroupCode
								}),
							contentType: "application/json",
							url: getAbsolutePath() +  "accessorygroup/delete/",
							success: function(data){
								callMessageDialog("Message", "Delete accessory group successfully!");
								$("#listAccessoryGroup").dataTable().fnDestroy();
								$('#listAccessoryGroup tbody').empty();
								loadData();
								$("#deleteAccessoryGroupDialog").dialog("close");
							},
							error:function(){
								callMessageDialog("Message", "Can't delete!");

							}
						});
					},
					"NO": function(){
						$("#deleteAccessoryGroupDialog").dialog("close");
					}
				}
			});
		}
	});
	}
	//------------------------VALIDATION-------------------------
	/**
	 * This function allow to validate before add
	 */
	
	function isValidationRequiredFieldAddAccessoryGroup(){
		var accessorygroupCode= $("#dialogAddAccessoryGroup").find("#txtAccessoryGroupCode").val();
		if(accessorygroupCode.trim()== '' || accessorygroupCode == null)
			return false;
		return true;
	}
	
	/**
	 * This function OverRange to validate if data over range
	 */
	

	
	
	
	//error Existed AccessoryGroupcode
	$("#dialogAddAccessoryGroup").find("#txtAccessoryGroupCode").keyup(function(){
		var accessorygroupCode = $("#dialogAddAccessoryGroup").find("#txtAccessoryGroupCode").val();
		$.ajax({
			dataType: "json",
			type: 'POST',
			data:
				JSON.stringify({
					accessorygroupCode : accessorygroupCode
				}),
			contentType: "application/json",
			url: getAbsolutePath() +  "accessorygroup/isExist/",
			success: function(data){
				if(data.isExisted==false){
					if(accessorygroupCode.length>100||accessorygroupCode.length<1){
						$("#listAccessoryGroup,#dialogAddAccessoryGroup").find("#errorTxtAccessoryGroupCode").text("Your input between 1 - 100 characters");
						$("#listAccessoryGroup,#dialogAddAccessoryGroup").find("#errorTxtAccessoryGroupCode").css("color", "red");
						$("#listAccessoryGroup,#dialogAddAccessoryGroup").find("#txtAccessoryGroupCode").css("border-left", "red 5px solid");
						$('#dialogAddAccessoryGroup,#btnSaveAccessoryGroup').prop( "disabled", true );
					}
					else if($.trim(accessorygroupCode) !== accessorygroupCode){
						$("#dialogAddAccessoryGroup").find("#errorTxtAccessoryGroupCode").text("Not allow to type the space as a first/last character");
						$("#dialogAddAccessoryGroup").find("#errorTxtAccessoryGroupCode").css("color", "red");
						$("#dialogAddAccessoryGroup").find("#txtAccessoryGroupCode").css("border-left", "red 5px solid");
						$('#dialogAddAccessoryGroup,#btnSaveAccessoryGroup').prop( "disabled", true );
					}
					else{
						$("#dialogAddAccessoryGroup").find("#errorTxtAccessoryGroupCode").text("Accessory group code is valid");
						$("#dialogAddAccessoryGroup").find("#txtAccessoryGroupCode").css("border-left", "blue 5px solid");
						$("#dialogAddAccessoryGroup").find("#errorTxtAccessoryGroupCode").css("color", "green");

						$('#dialogAddAccessoryGroup,#btnSaveAccessoryGroup').prop( "disabled", false );
					}
				}else{
					$("#dialogAddAccessoryGroup").find("#errorTxtAccessoryGroupCode").text("Accessory group code has been created!");
					$("#dialogAddAccessoryGroup").find("#errorTxtAccessoryGroupCode").css("color", "red");
					$("#dialogAddAccessoryGroup").find("#txtAccessoryGroupCode").css("border-left", "red 5px solid");
					$('#dialogAddAccessoryGroup,#btnSaveAccessoryGroup').prop( "disabled", true );
				}
			},
			error: function(){
				//	alert('Error');
				}
		});
	});
	
	$("#dialogAddAccessoryGroup").find("#txtDescription").keyup(function(){
		var description= $("#dialogAddAccessoryGroup").find("#txtDescription").val();
		//error  overrange txtDescription
		if(description.length>500){
			$("#listAccessoryGroup,#dialogAddAccessoryGroup").find("#errorTxtDescription").text("Your input between 1 - 500 characters");
			$("#listAccessoryGroup,#dialogAddAccessoryGroup").find("#errorTxtDescription").css("color", "red");
			$("#listAccessoryGroup,#dialogAddAccessoryGroup").find("#txtDescription").css("border-left", "red 5px solid");
			$('#dialogAddAccessoryGroup,#btnSaveAccessoryGroup').prop( "disabled", true );
			
		}else{
			$("#dialogAddAccessoryGroup").find("#errorTxtDescription").text("");
			$("#dialogAddAccessoryGroup").find("#txtDescription").css("border-left", "blue 5px solid");
			$('#dialogAddAccessoryGroup,#btnSaveAccessoryGroup').prop( "disabled", false );
		}
	});
	//validate for edit form
	
	$("#dialogEditAccessoryGroup").find("#txtDescription").keyup(function(){
		var description= $("#dialogEditAccessoryGroup").find("#txtDescription").val();
		//error  overrange txtDescription
		if(description.length>500){
			$("#listAccessoryGroup,#dialogEditAccessoryGroup").find("#errorTxtDescription").text("Your input between 1 - 500 characters");
			$("#listAccessoryGroup,#dialogEditAccessoryGroup").find("#errorTxtDescription").css("color", "red");
			$("#listAccessoryGroup,#dialogEditAccessoryGroup").find("#txtDescription").css("border-left", "red 5px solid");
			$('#dialogEditAccessoryGroup,#btnSave').prop( "disabled", true );
		}else{
			$("#dialogEditAccessoryGroup").find("#errorTxtDescription").text("");
			$("#dialogEditAccessoryGroup").find("#txtDescription").css("border-left", "blue 5px solid");
			$('#dialogEditAccessoryGroup,#btnSave').prop( "disabled", false );
		}
	});
	
	
	
	loadData();
});