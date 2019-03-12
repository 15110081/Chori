$(document).ready(function(){
	/**
	 * --------------START: Load garmentkind list & set data to table ------------------
	 */
	function loadData(){
		//list
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "garmentkind/list",
			contentType: "application/json",
			success: function(data){
				//alert(data.length);
				var i = 1;
				if(data.list.length==0){
					alert("Table have no data.");
				}
				$.each(data.list,function(key,value){
					//alert(value.createdate);
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.garmentkindcode),
							$('<td>').text(value.description),
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.garmentkindcode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
									
					).appendTo('#listGarmentkind');
				});
				action();
				
				$('#listGarmentkind').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Can not get data!");
			}
		});
	};
	
	
	//handle click event for edit + delete + add button of table
	function action(){
		//When lick create new garmentkind
		$('#btnAdd').on('click', function (e) {
			
			$("#dialogAddGarmentKind").find("#txtKind").val('');
			$("#dialogAddGarmentKind").find("#txtDescription").val('');
			
			$("#listGarmentkind,#dialogAddGarmentKind").find("#errorTxtKind").text("");
			$("#listGarmentkind,#dialogAddGarmentKind").find("#txtKind").css("border-color", "");
			
			$("#listGarmentkind,#dialogAddGarmentKind").find("#errorTxtDescription").text("");
			$("#listGarmentkind,#dialogAddGarmentKind").find("#txtDescription").css("border-color", "");
			
			$("#dialogAddGarmentKind").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Add New Garment Kind",
				height: 350,
				width: 450,
				buttons:{
					"Save":{
						text: "Save",
						id : "btnSaveKind",
						click: function(){
						var garmentkindcode= $("#dialogAddGarmentKind").find("#txtKind").val();
						
						if(!isValidationRequiredFieldAddGarmentkind()){
							callMessageDialog("Message", "Please enter kind!");
						}
						
						else if(!isValidationOverRangeWhenAddGarmentkind()){
								callMessageDialog("Message", "Kind  only 50 characters long!");
							}
						else if($.trim(garmentkindcode) !== garmentkindcode){
							callMessageDialog("Message", "Not allow to type the space as a first/last character on Garment Kind Code!");
						}
						
						else{
								var garmentkindcode= $("#dialogAddGarmentKind").find("#txtKind").val();
								var description= $("#dialogAddGarmentKind").find("#txtDescription").val();
								$.ajax({
									dataType: "json",
									type: 'POST',
									data:JSON.stringify({
										garmentkindcode: garmentkindcode
									}),
									contentType: "application/json",
									url: getAbsolutePath() +  "garmentkind/isExist/",
									success: function(data){
										if(data.isExisted==false){
											$.ajax({
									    		dataType: "json",
												type: 'POST',
												data:
													JSON.stringify({
														garmentkindcode: garmentkindcode,
														description: description,
													}),
												contentType: "application/json",
												url: getAbsolutePath() +  "garmentkind/add",
												success: function(data){
													//alert('edit success');
													//console.log(data);
													
													callMessageDialog("Message", "Add Gament Kind successfully !");
													
													$("#listGarmentkind").dataTable().fnDestroy();
													$('#listGarmentkind tbody').empty();
													$("#dialogAddGarmentKind").dialog("close");
													loadData();
												},
												error: function(){
													callMessageDialog("Message", "Add Gament Kind unsuccessfully !");
												}
									    	});
										}else{
											callMessageDialog("Message", "Kind Already Existed!");
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
						$("#dialogAddGarmentKind").dialog("close");
					}
				}
			});
		});
		
		//when click Edit
//		$('.btnEdit').on('click', function (e) {	
//			var garmentkindcode= $(this).data('id');
//			$("#dialogEditGarmentkind").find("#errorTxtDescription").text("");
//			$("#dialogEditGarmentkind").find("#txtDescription").css("border-color", "");
//			
//			$.ajax({
//	    		dataType: "json",
//				type: 'GET',
//				data:{},
//				contentType: "application/json",
//				url: "/Chori/garmentkind/detail/"+garmentkindcode,
//				success: function(data){
//					if(data.status=="ok"){
//						$("#dialogEditGarmentkind").find("#txtKind").val(data.currentgarmentkind.garmentkindcode);
//						$("#dialogEditGarmentkind").find("#txtDescription").val(data.currentgarmentkind.description);
//					}else{
//						alert('This alert should never show!');
//					}
//				},
//				error: function(){
//					alert('Cant get role detail!');
//				}
//	    	});
//			
//			$("#dialogEditGarmentkind").dialog({
//				show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Edit Garmentkind",
//				height: 300,
//				width: 400,
//				buttons:{
//					"Save":{
//						text: "Save",
//						id : "btnSaveKind",
//						click : function(){
//						var garmentkindcode= $("#dialogEditGarmentkind").find("#txtKind").val();
//						var description= $("#dialogEditGarmentkind").find("#txtDescription").val();
//						//
//							$.ajax({
//					    		dataType: "json",
//								type: 'POST',
//								data:
//									JSON.stringify({
//										garmentkindcode: garmentkindcode,
//										description: description,
//									}),
//								contentType: "application/json",
//								url: "/Chori/garmentkind/edit",
//								success: function(data){
//								//	alert('edit success');
//									//console.log(data);
//									
//									callMessageDialog("Message", "Edit Garmentkind successfully!");
//									
//									$("#listGarmentkind").dataTable().fnDestroy();
//									$('#listGarmentkind tbody').empty();
//									$("#dialogEditGarmentkind").dialog("close");
//									loadData();
//									
//								},
//								error: function(){
//									alert('Cant get role detail!');
//								}
//					    	});
//						}
//						
//					},
//					"Cancel": function(){
//						$("#dialogEditGarmentkind").dialog("close");
//					}
//				}
//			});
//		});
		
		//When click Delete
//		$('.btnDelete').on('click', function (e) {				
//			var garmentkindcode= $(this).data('id');
//			$("#deleteGarmentkindDialog").find("#messageContent").text('Are you sure you want to delete Garment Kind  "' + garmentkindcode+'"?');
//	    
//			$("#deleteGarmentkindDialog").dialog({
//				show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Delete Garmentkind Confirm",
//				height: 200,
//				width: 400,
//				buttons:{
//					"OK": function(){
//						$.ajax({
//							dataType: "json",
//							type: 'POST',
//							data:{},
//							contentType: "application/json",
//							url: "/Chori/garmentkind/delete/" + garmentkindcode,
//							success: function(data){
//								callMessageDialog("Message", "Delete Garment Kind successfully!");
//								$("#listGarmentkind").dataTable().fnDestroy();
//								$('#listGarmentkind tbody').empty();
//								loadData();
//								$("#deleteGarmentkindDialog").dialog("close");
//							}
//						});
//					},
//					"NO": function(){
//						$("#deleteGarmentkindDialog").dialog("close");
//					}
//				}
//			});
//		});
		
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    var garmentkindcode= $(this).data('id');//this is RoleID of each record.
		    //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $(".selectOption").val("Options");
		    
		    
		    //If user choose edit option
		    if(valueSelected=="Edit"){
		    	
				$.ajax({
		    		dataType: "json",
					type: 'POST',
					data:JSON.stringify({
						garmentkindcode: garmentkindcode
					}),
					contentType: "application/json",
					url: getAbsolutePath() +  "garmentkind/detail/",
					success: function(data){
						if(data.status=="ok"){
							$("#dialogEditGarmentkind").find("#txtKind").val(data.currentgarmentkind.garmentkindcode);
							$("#dialogEditGarmentkind").find("#txtDescription").val(data.currentgarmentkind.description);
						}else{
							alert('This alert should never show!');
						}
					},
					error: function(){
						alert('Cant get role detail!');
					}
		    	});
				
				$("#dialogEditGarmentkind").dialog({
					modal : true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Edit Garmentkind",
					height: 350,
					width: 450,
					buttons:{
						"Save":{
							text: "Save",
							id : "btnSaveKind",
							click : function(){
							var garmentkindcode= $("#dialogEditGarmentkind").find("#txtKind").val();
							var description= $("#dialogEditGarmentkind").find("#txtDescription").val();
							//
								$.ajax({
						    		dataType: "json",
									type: 'POST',
									data:
										JSON.stringify({
											garmentkindcode: garmentkindcode,
											description: description,
										}),
									contentType: "application/json",
									url: getAbsolutePath() +  "garmentkind/edit",
									success: function(data){
									//	alert('edit success');
										//console.log(data);
										
										callMessageDialog("Message", "Edit Garment Kind successfully!");
										
										$("#listGarmentkind").dataTable().fnDestroy();
										$('#listGarmentkind tbody').empty();
										$("#dialogEditGarmentkind").dialog("close");
										loadData();
										
									},
									error: function(){
										callMessageDialog("Message", "Edit Kind unsuccessfully!");
									}
						    	});
							}
							
						},
						"Cancel": function(){
							$("#dialogEditGarmentkind").dialog("close");
						}
					}
				});
		    };
		    
		    //if user choose delete option
		    if(valueSelected=="Delete"){				
		    	var garmentkindcode= $(this).data('id');
				$("#deleteGarmentkindDialog").find("#messageContent").text('Are you sure you want to delete Garment Kind  "' + garmentkindcode+'"?');
		    
				$("#deleteGarmentkindDialog").dialog({
					modal : true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Delete Confirm",
					height: 200,
					width: 400,
					buttons:{
						"OK": function(){
							$.ajax({
								dataType: "json",
								type: 'POST',
								data:JSON.stringify({
									garmentkindcode: garmentkindcode
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "garmentkind/delete/",
								success: function(data){
									if(data.status=="ok"){
										if(data.deleteGarmentkind== true){
											callMessageDialog("Message", "Delete Garment Kind successfully!");
											$("#listGarmentkind").dataTable().fnDestroy();
											$('#listGarmentkind tbody').empty();
											loadData();
											$("#deleteGarmentkindDialog").dialog("close");
										}
										else if(data.deleteGarmentkind== false){
											callMessageDialog("Message", 'Can\'t Delete Garment Kind "'+ garmentkindcode + '"!');
											$("#deleteGarmentkindDialog").dialog("close");
										}else{
											callMessageDialog("Message", 'Can\'t Delete Garment Kind "'+ garmentkindcode + '"!');
										}
									}
								},
								error: function(){
									callMessageDialog("Message", 'Can\'t Delete Garment Kind "'+ garmentkindcode + '"!');
								}
							});
						},
						"Cancel": function(){
							$("#deleteGarmentkindDialog").dialog("close");
						}
					}
				});
			};
		    //end if user choose delete option
		    
		});
		
	}
	//------------------------VALIDATION-------------------------
	/**
	 * This function allow to validate before add
	 */
	
	function isValidationRequiredFieldAddGarmentkind(){
		var garmentkindCode= $("#dialogAddGarmentKind").find("#txtKind").val();
		if(garmentkindCode.trim()== '' || garmentkindCode == null)
			return false;
		return true;
	}
	
	/**
	 * This function allow to validate if data over range
	 */
	
	function isValidationOverRangeWhenAddGarmentkind(){
		var garmentkindCodeLength= $("#listGarmentkind,#dialogAddGarmentKind").find("#txtKind").val().length;
		if(garmentkindCodeLength>50)
			return false;
		
		var garmentkindDescriptionLength = $("#dialogAddGarmentKind").find("#txtDescription").val().length;
		if(garmentkindDescriptionLength>500)
			return false;
		return true;
	}
	
	/**
	 * This function overRange to validate if data over range Edit
	 */
	function isValidationOverRangeWhenEditGarmentkind(){
		
		var garmentkindDescriptionLength = $("#dialogEditGarmentKind").find("#txtDescription").val().length;
		if(garmentkindDescriptionLength>500)
			return false;
		return true;
}
	//validate for create form
	$("#listGarmentkind,#dialogAddGarmentKind").find("#txtKind").on('input propertychange paste',function(e){
		var input= $(this).val();
		//error  overrange garmentkindcode
		if(input.length>50||input.length<1){
			$("#listGarmentkind,#dialogAddGarmentKind").find("#errorTxtKind").text("Your input between 1 - 50 characters");
			$("#listGarmentkind,#dialogAddGarmentKind").find("#errorTxtKind").css("color", "red");
			$("#listGarmentkind,#dialogAddGarmentKind").find("#txtKind").css("border-left", "red 5px solid");
			$('#dialogAddGarmentKind,#btnSaveKind').prop( "disabled", true );
		}
		//error Not allow to type the space as a first/last character
		else if($.trim(input) !== input){
			$("#listGarmentkind,#dialogAddGarmentKind").find("#errorTxtKind").text("Not allow to type the space as a first/last character");
			$("#listGarmentkind,#dialogAddGarmentKind").find("#errorTxtKind").css("color", "red");
			$("#listGarmentkind,#dialogAddGarmentKind").find("#txtKind").css("border-left", "red 5px solid");
			$('#dialogAddDestination,#btnSaveKind').prop( "disabled", true );
		}
		else{
			$("#listGarmentkind,#dialogAddGarmentKind").find("#errorTxtKind").text("");
			$("#listGarmentkind,#dialogAddGarmentKind").find("#txtKind").css("border-left", "blue 5px solid");
			$('#dialogAddGarmentKind,#btnSaveKind').prop( "disabled", false );
		}
	})
	//error Existed garmentkindcode
		$("#listGarmentkind,#dialogAddGarmentKind").find("#txtKind").keyup(function(){
		var garmentkindcode= $("#listGarmentkind,#dialogAddGarmentKind").find("#txtKind").val();
		$.ajax({
			dataType: "json",
			type: 'POST',
			data:JSON.stringify({
				garmentkindcode: garmentkindcode
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "garmentkind/isExist/",
			success: function(data){
				if(data.isExisted==false){
					if(garmentkindcode.length>50){
						$("#listGarmentkind,#dialogAddGarmentKind").find("#errorTxtKind").text("Your input between 1 - 50 characters");
						$("#listGarmentkind,#dialogAddGarmentKind").find("#errorTxtKind").css("color", "red");
						$("#listGarmentkind,#dialogAddGarmentKind").find("#txtKind").css("border-left", "red 5px solid");
						$('#dialogAddGarmentKind,#btnSaveKind').prop( "disabled", true );
					}
					else if($.trim(garmentkindcode) !== garmentkindcode){
						$("#listGarmentkind,#dialogAddGarmentKind").find("#errorTxtKind").text("Not allow to type the space as a first/last character");
						$("#listGarmentkind,#dialogAddGarmentKind").find("#errorTxtKind").css("color", "red");
						$("#listGarmentkind,#dialogAddGarmentKind").find("#txtKind").css("border-left", "red 5px solid");
						$('#dialogAddGarmentKind,#btnSaveKind').prop( "disabled", true );
					}else{
						$("#listGarmentkind,#dialogAddGarmentKind").find("#errorTxtKind").text("Kind is valid!");
						$("#dialogAddGarmentKind").find("#errorTxtKind").css("color", "green");
						$("#listGarmentkind,#dialogAddGarmentKind").find("#txtKind").css("border-left", "blue 5px solid");
						$('#dialogAddGarmentKind,#btnSaveKind').prop( "disabled", false );
						if(garmentkindcode.trim() === '' || garmentkindcode == null){
							$("#dialogAddGarmentKind").find("#errorTxtKind").text("Please enter kind !");
							$("#dialogAddGarmentKind").find("#errorTxtKind").css("color", "red");
							$("#listGarmentkind,#dialogAddGarmentKind").find("#txtKind").css("border-left", "red 5px solid");
						}
					}
				}else{
					$("#listGarmentkind,#dialogAddGarmentKind").find("#errorTxtKind").text("Kind Already Existed!");
					$("#listGarmentkind,#dialogAddGarmentKind").find("#errorTxtKind").css("color", "red");
					$("#listGarmentkind,#dialogAddGarmentKind").find("#txtKind").css("border-left", "red 5px solid");
					$('#dialogAddGarmentKind,#btnSaveKind').prop( "disabled", true );
					
				}
			},
			error: function(){
				//	alert('Error');
				}
		});

	});
		
		$("#dialogAddGarmentKind").find("#txtDescription").keyup(function(){
			var description= $("#dialogAddGarmentKind").find("#txtDescription").val();
			//error  overrange txtDescription
			if(description.length>500){
				$("#dialogAddGarmentKind").find("#errorTxtDescription").text("Your input is over range");
				$("#dialogAddGarmentKind").find("#errorTxtDescription").css("color", "red");
				$("#dialogAddGarmentKind").find("#txtDescription").css("border-left", "red 5px solid");
				$('#dialogAddGarmentKind,#btnSaveKind').prop( "disabled", true );
			}else{
				$("#dialogAddGarmentKind").find("#errorTxtDescription").text("");
				$("#dialogAddGarmentKind").find("#txtDescription").css("border-left", "blue 5px solid");
				$('#dialogAddGarmentKind,#btnSaveKind').prop( "disabled", false );
			}
		});
		//validate for edit form
		$("#dialogEditGarmentkind").find("#txtDescription").keyup(function(){
			var description= $("#dialogEditGarmentkind").find("#txtDescription").val();
			if(description.length>500){
				$("#dialogEditGarmentkind").find("#errorTxtDescription").text("Your input is over range");
				$("#dialogEditGarmentkind").find("#errorTxtDescription").css("color", "red");
				$("#dialogEditGarmentkind").find("#txtDescription").css("border-left", "red 5px solid");
				$('#dialogAddGarmentKind,#btnSaveKind').prop( "disabled", true );
			}else{
				$("#dialogEditGarmentkind").find("#errorTxtDescription").text("");
				$("#dialogEditGarmentkind").find("#txtDescription").css("border-left", "blue 5px solid");
				$('#dialogAddGarmentKind,#btnSaveKind').prop( "disabled", false );
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