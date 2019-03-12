$(document).ready(function(){	
	/**
	 * This function is used to load data into table
	 */
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "unit/list",
			contentType: "application/json",
			success: function(data){
				var i=1;
				if(data.list.length==0){
					alert("Table have no data.");
				}
				$.each(data.list,function(key,value){
					$('<tr>').append(
							$('<td>').text(i++),

							$('<td>').text(value.unitcode),
							$('<td>').text(value.name),

							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.unitcode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
									
					).appendTo('#listUnit');
				});
				action();
				$('#listUnit').DataTable({
					"pagingType": "full_numbers"
			    });
			},
			error: function(){
				alert("Can not get data!");
			}
		});
	};

	
	
	
//	function action(){	
//		// button edit unit
//		$('.btnEdit').on('click', function (e) {	
//			var unitCode= $(this).data('id');
//		//	alert(unitCode);
//		$.ajax({
//	    		dataType: "json",
//				type: 'GET',
//				data:{},
//				contentType: "application/json",
//				url: "/Chori/unit/detail/"+unitCode,
//				success: function(data){
//					if(data.status=="ok"){
//						$("#dialogEditUnit").find("#txtUnitID").val(data.currentunit.unitcode);
//						$("#dialogEditUnit").find("#txtUnitName").val(data.currentunit.name);
//					}else{
//						alert('This alert should never show!');
//					}
//				},
//				error: function(){
//					alert('Cant get Unit detail!'+unitCode);
//				}
//	    	});
//			
//			//show dialog edit unit
//			$("#dialogEditUnit").dialog({
//				show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Edit Unit",
//				height: 300,
//				width: 400,
//				buttons:{
//					"Save": function(){
//						
//						var unitCode= $("#dialogEditUnit").find("#txtUnitID").val();
//						var unitName= $("#dialogEditUnit").find("#txtUnitName").val();
//						
//						if(!isValidationRequiredFieldEditUnit()){
//							callMessageDialog("Message", "Please update required form!");
//						}
//						else if(!isValidationOverRangeWhenEditUnit()){
//							callMessageDialog("Message", "Unit Name ID only 20 characters long!");
//						}	
//						else{
//							//call ajax to update
//
//						$.ajax({
//				    		dataType: "json",
//							type: 'POST',
//							data:
//								JSON.stringify({
//									unitcode: unitCode,
//									name: unitName,
//								}),
//							contentType: "application/json",
//							url: "/Chori/unit/edit",
//							success: function(data){
//							//	alert('edit success');
//								console.log(data);
//								
//								callMessageDialog("Message", "Edit unit successfully!");
//								
//								$("#listUnit").dataTable().fnDestroy();
//								$('#listUnit tbody').empty();
//								loadData();
//								$("#dialogEditUnit").dialog("close");
//							},
//							error: function(){
//								alert('Cant get role detail!');
//							}
//				    	});
//						}
//					},
//					"Cancel": function(){
//						$("#dialogEditUnit").dialog("close");
//					}
//				}
//			});
//	});
//		//button delete to delete a unit
//		$('.btnDelete').on('click', function (e) {	
//			var unitCode= $(this).data('id');
//			$("#dialogDeleteUnit").dialog({
//				show:{
//					effect:"slide",
//					duration: 500
//				},
//				// show dialog confirm before delete
//				title: "Delete Confirm",
//				height: 300,
//				width: 400,
//				buttons:{
//					"Yes": function(){
//						//call ajax to delete 
//
//						$.ajax({
//				    		dataType: "json",
//							type: 'POST',
//							data:{},
//							contentType: "application/json",
//							url: "/Chori/unit/delete/"+unitCode,
//							success: function(data){
//								callMessageDialog("Message", "Delete unit successfully!");
//
//								$("#listUnit").dataTable().fnDestroy();
//								$('#listUnit tbody').empty();
//								$("#dialogDeleteUnit").dialog("close");
//								loadData();
//								
//							},
//							error: function(){
//								callMessageDialog("Message", "Delete unit unsuccessfully!");
//								$("#dialogDeleteUnit").dialog("close");
//							}
//						
//				    	});
//						
//					},
//					"No": function(){
//						$("#dialogDeleteUnit").dialog("close");
//					}
//				}
//			});
//		});
//		//Create a new unit
//		$('#btnAdd').click(function () {
////clear all input
//			$('#dialogAddUnit').find('#txtUnitID').val("");
//			$('#dialogAddUnit').find('#txtUnitName').val("");
//	//show dialog add unit
//			$("#dialogAddUnit").dialog({
//				show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Create New Unit",
//				height: 300,
//				width: 400,
//				buttons:{
//					"Save": {
//					text:"Save",
//					id:"btnSave",
//					
//					click: function(){
//						var unitcode= $("#dialogAddUnit").find("#txtUnitID").val();
//						var name= $("#dialogAddUnit").find("#txtUnitName").val();
//						if(!isValidationRequiredFieldAddUnit()){
//							callMessageDialog("Message", "Please insert required form!");
//						}
//						else if(!isValidationOverRangeWhenAddUnit()){
//							callMessageDialog("Message", "Unit code and Unit name only 20 characters long !");
//						}else{	
//						//ajax check is isExist
//						$.ajax({
//				    		dataType: "json",
//							type: 'GET',
//							data:{},
//							contentType: "application/json",
//							url: "/Chori/unit/isExist/" + unitcode,
//							success: function(data){
//							 if(data.isExisted==false){
//									//call ajax to add 
//
//								$.ajax({
//						    		dataType: "json",
//									type: 'POST',
//									data:
//										JSON.stringify({
//											unitcode: unitcode,
//											name: name,
//										}),
//									contentType: "application/json",
//									url: "/Chori/unit/add",
//									success: function(data){
//										
//										//alert('edit success');
//										//console.log(data);
//										
//										callMessageDialog("Message", "Save Unit successfully!");
//										
//										$("#listUnit").dataTable().fnDestroy();
//										$('#listUnit tbody').empty();
//										$("#dialogAddUnit").dialog("close");
//										loadData();
//									},
//									error: function(){
//										callMessageDialog("Message", "Save Unit unsuccessfully!");
//										
//									}
//						    	});}
//								else{
//									callMessageDialog("Message", " UnitCode Already Exist!");
//								}
//								//end ajax create unit
//								
//							},
//							
//							error: function(){
//							//	alert('Error');
//							}
//				    	});
//
//						}
//
//					},
//					},
//					"Cancel": function(){
//						$("#dialogAddUnit").dialog("close");
//					}
//				
//				}
//			});
//		});
		
	function action(){	
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
			var unitCode= $(this).data('id');
		    $(".selectOption").val("Options");
		// button edit unit
		    if(valueSelected=="Edit"){
			//	var unitCode= $(this).data('id');
		    	$("#dialogEditUnit").find("#txtUnitName").css("border-left", "");
				$("#dialogEditUnit").find("#errorTxtUnitName").text("");
		//	alert(unitCode);
		$.ajax({
	    		dataType: "json",
				type: 'POST',
				data:
					JSON.stringify({
						unitcode: unitCode,
						
					}),
				contentType: "application/json",
				url: getAbsolutePath() +  "unit/detail/",
				success: function(data){
					if(data.status=="ok"){
						$("#dialogEditUnit").find("#txtUnitID").val(data.currentunit.unitcode);
						$("#dialogEditUnit").find("#txtUnitName").val(data.currentunit.name);
					}else{
						alert('This alert should never show!');
					}
				},
				error: function(){
					alert('Cant get unit detail!'+unitCode);
				}
	    	});
			
			//show dialog edit unit
			$("#dialogEditUnit").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Edit Unit",
				height: 300,
				width: 400,
				buttons:{
					"Save": {
						text:"Save",
						id:"btnSave",
						
						click: function(){
						
						var unitCode= $("#dialogEditUnit").find("#txtUnitID").val();
						var unitName= $("#dialogEditUnit").find("#txtUnitName").val();
						
						if(!isValidationRequiredFieldEditUnit()){
							callMessageDialog("Message", "Please update required form!");
						}
						else if(isValidationOverRangeWhenEditUnit()!= null){
							callMessageDialog("Message", isValidationOverRangeWhenEditUnit());
						}	
						else{
							//call ajax to update

						$.ajax({
				    		dataType: "json",
							type: 'POST',
							data:
								JSON.stringify({
									unitcode: unitCode,
									name: unitName,
								}),
							contentType: "application/json",
							url: getAbsolutePath() +  "unit/edit",
							success: function(data){
							//	alert('edit success');
								console.log(data);
								
								callMessageDialog("Message", "Edit unit successfully!");
								
								$("#listUnit").dataTable().fnDestroy();
								$('#listUnit tbody').empty();
								loadData();
								$("#dialogEditUnit").dialog("close");
							},
							error: function(){
								alert('Cant get role detail!');
							}
				    	});
						}
					},
					},
					"Cancel": function(){
						$("#dialogEditUnit").dialog("close");
					}
					
				}
			});
			
		    }
		    
		//button delete to delete a unit
		    if(valueSelected=="Delete"){
				$("#dialogDeleteUnit").find("#messageContent").text('Are you sure you want to delete unit "' +unitCode+'"?');

		//	var unitCode= $(this).data('id');
			$("#dialogDeleteUnit").dialog({
				modal: true,
				show:{
					effect:"slide",
					duration: 500
				},
				// show dialog confirm before delete
				title: "Delete Confirm",
				height: 300,
				width: 400,
				buttons:{
					"Yes": function(){
						//call ajax to delete 

						$.ajax({
				    		dataType: "json",
							type: 'POST',
							data:
								JSON.stringify({
									unitcode: unitCode,
									
								}),
							contentType: "application/json",
							url: getAbsolutePath() +  "unit/delete/",
							success: function(data){
								callMessageDialog("Message", "Delete unit successfully!");

								$("#listUnit").dataTable().fnDestroy();
								$('#listUnit tbody').empty();
								$("#dialogDeleteUnit").dialog("close");
								loadData();
								
							},
							error: function(){
								callMessageDialog("Message", "Can't delete!");
								$("#dialogDeleteUnit").dialog("close");
							}
						
				    	});
						
					},
					"No": function(){
						$("#dialogDeleteUnit").dialog("close");
					}
				}
			});
		}
		});
		//Create a new unit
		$('#btnAdd').click(function () {
			$("#dialogAddUnit").find("#errorTxtUnitCode").text("");
			$("#dialogAddUnit").find("#txtUnitName").css("border-left", "");
			$("#dialogAddUnit").find("#txtUnitID").css("border-left", "");
			$("#dialogAddUnit").find("#errorTxtUnitName").text("");
//clear all input
			$('#dialogAddUnit').find('#txtUnitID').val("");
			$('#dialogAddUnit').find('#txtUnitName').val("");
	//show dialog add unit
			$("#dialogAddUnit").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Add New Unit",
				height: 300,
				width: 400,
				buttons:{
					"Save": {
					text:"Save",
					id:"btnSave",
					
					click: function(){
						var unitCode= $("#dialogAddUnit").find("#txtUnitID").val();
						var name= $("#dialogAddUnit").find("#txtUnitName").val();
						if(!isValidationRequiredFieldAddUnit()){
							callMessageDialog("Message", "Please insert required form!");
						}
						else if(isValidationOverRangeWhenAddUnit() != null){
							callMessageDialog("Message", isValidationOverRangeWhenAddUnit());
						}else{	
						//ajax check is isExist
						$.ajax({
				    		dataType: "json",
							type: 'POST',
							data:
								JSON.stringify({
									unitcode: unitCode,
									
								}),
							contentType: "application/json",
							url: getAbsolutePath() +  "unit/isExist/",
							success: function(data){
							 if(data.isExisted==false){
									//call ajax to add 

								$.ajax({
						    		dataType: "json",
									type: 'POST',
									data:
										JSON.stringify({
											unitcode: unitCode,
											name: name,
										}),
									contentType: "application/json",
									url: getAbsolutePath() +  "unit/add",
									success: function(data){
										
										//alert('edit success');
										//console.log(data);
										
										callMessageDialog("Message", "Add unit successfully!");
										
										$("#listUnit").dataTable().fnDestroy();
										$('#listUnit tbody').empty();
										$("#dialogAddUnit").dialog("close");
										loadData();
									},
									error: function(){
										callMessageDialog("Message", "Add unit unsuccessfully!");
										
									}
						    	});}
								else{
									callMessageDialog("Message", " Unit code is existed!");
								}
								//end ajax create unit
								
							},
							
							error: function(){
							//	alert('Error');
							}
				    	});

						}

					},
					},
					"Cancel": function(){
						$("#dialogAddUnit").dialog("close");
					}
				
				}
			});
		});
		
		
		
		
		
	
	// This function is used to call validate cusID when add order dialog.
	function ShowDialog_CannotFindCusID() {
        $( "#dialogEditSuccess" ).dialog({
        	modal: true,
        	title: "Message",
            show: {
                effect: "slide",
                duration: 300
            },
            height: 200,
            width: 320,
            modal: false,
            buttons: {
                "OK": function() {
                    $( "#dialogEditSuccess" ).dialog( "close" );
                }
            },
            hide: {
                effect: "slide",
                duration: 300
            }
        });
    };
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
}
    
	
	
	/**
	 * This function allow to validate before add
	 */
	
	function isValidationRequiredFieldAddUnit(){
		var unitCode= $("#listUnit,#dialogAddUnit").find("#txtUnitID").val();
		if(unitCode.trim() == '' || unitCode == null)
			return false;
		
		var unitName = $('#listUnit,#dialogAddUnit').find('#txtUnitName').val();
		if(unitName.trim() == '' || unitName == null) 
			return false;
		
		return true;
	}
	
	/**
	 * This function allow to validate if data over range
	 */
	
	function isValidationOverRangeWhenAddUnit(){
		var unitCodeLength= $("#listUnit,#dialogAddUnit").find("#txtUnitID").val().length;
		var unitNameLength= $("#listUnit,#dialogAddUnit").find("#txtUnitName").val().length;

		if(unitCodeLength<1||unitCodeLength>20)
			return "Unit code must be between 1-20 characters";
		else if(unitNameLength<1||unitNameLength>50)
			return "Unit name must be between 1-50 characters";
		return null;
	}
	
	/**
	 * This function allow to validate before edit
	 */
	
	function isValidationRequiredFieldEditUnit(){
		var unitName = $('#listUnit,#dialogEditUnit').find('#txtUnitName').val();
		if(unitName == '' || unitName == null) 
			return false;
		
		return true;
	}
	function isValidationOverRangeWhenEditUnit(){
	//	var unitCodeLength= $("#listUnit,#dialogAddUnit").find("#txtUnitID").val().length;
		var unitNameLength= $("#listUnit,#dialogEditUnit").find("#txtUnitName").val().length;

		if(unitNameLength<1||unitNameLength>50)
			return "Unit name must be between 1-50 characters";
		return null;
	}
	//------------------------VALIDATION-------------------------
	
//	//DO NOT INPUT NON-NUMBER in Tel, Fax, Tax No
//	$("#addShippinglineDialog,editShippinglineDialog").on('keydown', '#txtTel,#txtFax,#txtTaxNo', function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
//	$("#addShippinglineDialog,editShippinglineDialog").on('keydown', '#txtTel,#txtFax,#txtTaxNo', function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
	
	$("#dialogAddUnit").find('#txtUnitID').on('input propertychange paste', function (e) {
	
		var unitCode = $("#dialogAddUnit").find("#txtUnitID").val();
		//ajax check isExist Unit Code
		$.ajax({
			dataType: "json",
			type: 'POST',
			data:
				JSON.stringify({
					unitcode: unitCode,
					
				}),
			contentType: "application/json",
			url: getAbsolutePath() +  "unit/isExist/",
			success: function(data){
				if(data.isExisted==true){
					//if code is existed
					$("#dialogAddUnit").find("#errorTxtUnitCode").text("Unit code you enter is existed!");
					$("#dialogAddUnit").find("#errorTxtUnitCode").css("color", "red");
					$("#dialogAddUnit").find("#txtUnitID").css("border-left", "red 5px solid");

				//	$("#dialogAddUnit").find("#txtUnitID").css("background-color", "red");
					$('#btnSave').prop( "disabled", true );

				}
			}
		});
		if(unitCode.length>20 || unitCode.length<1) {
			$("#dialogAddUnit").find("#txtUnitID").css("border-left", "red 5px solid");
			$("#dialogAddUnit").find("#errorTxtUnitCode").text("Unit code must be between 1-20 characters");
			$("#dialogAddUnit").find("#errorTxtUnitCode").css("color", "red");
			$('#btnSave').prop( "disabled", true );
		}
		else if ($.trim(unitCode) !== unitCode){
			$("#dialogAddUnit").find("#txtUnitID").css("border-left", "red 5px solid");
			$("#dialogAddUnit").find("#errorTxtUnitCode").text("Not allow to type the space as a first/last character");
			$('#btnSave').prop( "disabled", true );
		} 
		else{				
			$("#dialogAddUnit").find("#txtUnitID").css("border-left", "blue 5px solid");
			$("#dialogAddUnit").find("#errorTxtUnitCode").text("Unit code is valid!");
			$("#dialogAddUnit").find("#errorTxtUnitCode").css("color", "green");
			$("#dialogAddUnit").find("#txtUnitID").css("background-color", "white");
			$('#btnSave').prop( "disabled", false );
		}
			
	$("#dialogAddUnit").find('#txtUnitName').on('input propertychange paste', function (e) {
		var shortname = $("#dialogAddUnit").find("#txtUnitName").val();
		if(shortname.length>50 || shortname.length<1) {
			$("#dialogAddUnit").find("#txtUnitName").css("border-left", "red 5px solid");
			$("#dialogAddUnit").find("#errorTxtUnitName").text("Unit name must be between 1-50 characters");
			$('#btnSave').prop( "disabled", true );
		}
		else if($.trim(shortname) !== shortname){
			$("#dialogAddUnit").find("#txtUnitName").css("border-left", "red 5px solid");
			$("#dialogAddUnit").find("#errorTxtUnitName").text("Not allow to type the space as a first/last character");
			$('#btnSave').prop( "disabled", true );
		}
		else{				
			$("#dialogAddUnit").find("#txtUnitName").css("border-left", "blue 5px solid");
			$("#dialogAddUnit").find("#errorTxtUnitName").text("");
			$('#btnSave').prop( "disabled", false );
		}
	});
	});
	

	
	$("#dialogEditUnit").find('#txtUnitName').on('input propertychange paste', function (e) {
		var shortname = $("#dialogEditUnit").find("#txtUnitName").val();
		if(shortname.length>50 || shortname.length<1) {
			$("#dialogEditUnit").find("#txtUnitName").css("border-left", "red 5px solid");
			$("#dialogEditUnit").find("#errorTxtUnitName").text("Unit name must be between 1-50 characters");
			$('#btnSave').prop( "disabled", true );
		}
		else if($.trim(shortname) !== shortname){
			$("#dialogEditUnit").find("#txtUnitName").css("border-left", "red 5px solid");
			$("#dialogEditUnit").find("#errorTxtUnitName").text("Not allow to type the space as a first/last character");
			$('#btnSave').prop( "disabled", true );
		}
		else{				
			$("#dialogEditUnit").find("#txtUnitName").css("border-left", "blue 5px solid");
			$("#dialogEditUnit").find("#errorTxtUnitName").text("");
			$('#btnSave').prop( "disabled", false );
		}
	});
		
	}	
	loadData();
});
	
