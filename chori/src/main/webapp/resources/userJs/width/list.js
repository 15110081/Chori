$(document).ready(function(){	
	/**
	 * This function is used to load data into table
	 */
function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "width/list",
			contentType: "application/json",
			success: function(data){
				if(data.list.length==0){
//					alert("Table have no data.");
				}
				$.each(data.list,function(key,value){
					$('<tr>').append(
							$('<td>').text(value.widthcode),
							$('<td>').text(value.unitname),
							$('<td>').text(value.widthvalues),			
//							$('<td>').html('<select class="selectpicker selectOption" data-id="'
//									+value.statuscode
//									+'"><option value="Options" disabled selected>Options</option>'
//									+ '<option value="Edit">Edit</option>'
//									)
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.widthcode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
																		
					).appendTo('#listWidth');
				});
				action();
				
				$('#listWidth').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
	//			alert("Can not get data!");
			}
		});
	};
	
	$("#listWidth").find("#btnEdit").click(function(e){
	//	alert("Can not get data!");
		
	});
	
	//bảng warning và user
	function action(){	
		//When click select
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
			var widthcode= $(this).data('id');	
			$(".selectOption").val("Options");
			// Select option = Edit
			if(valueSelected=="Edit"){
			$.ajax({
	    		dataType: "json",
				type: 'POST',
				data:JSON.stringify({
					widthcode: widthcode
				}),
				contentType: "application/json",
				url: getAbsolutePath() +  "width/detail",
				success: function(data){
					if(data.width== "ok"){
						//load data into input of dialog
						$("#dialogEditWidth").find("#txtWidthCode").val(data.currentwidth.widthcode);
						$("#dialogEditWidth").find("#txtUnitId").val(data.currentwidth.unitcode);
						$("#dialogEditWidth").find("#txtWidthValue").val(data.currentwidth.widthvalues);
					}else{
//						alert('This alert should never show!');
					}
				},
				error: function(){
//					alert('Cant get detail xxx');
				}
	    	});
			$("#listWidth,#dialogEditWidth").find("#errorTxtWidthValue").text("");
			$("#listWidth,#dialogEditWidth").find("#txtWidthValue").css("border-color", "");
			
			$("#dialogEditWidth").dialog({
				modal:true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Edit Width",
				height: 270,
				width: 700,
				buttons:{
					"Edit":{
						text: "Edit",
	                	id: "btnEditNewWidth",
						click: function(){
						
						var widthcode= $("#dialogEditWidth").find("#txtWidthCode").val();
						var unitId= $("#dialogEditWidth").find("#txtUnitId").val();
						var widthValue= $("#dialogEditWidth").find("#txtWidthValue").val();	
						
						if(!isValidationRequiredFieldEditWidth()){
							callMessageDialog("Message", "Please insert Width Value!");
						}else if(!isValidationOverRangeWhenEditWidth()){
							callMessageDialog("Message", "Width Value only 20 characterss long !");
						}else if (widthValue < 0){
							callMessageDialog("Message", "Width Value must not below 0 !");
						}
						else{
						$.ajax({
				    		dataType: "json",
							type: 'POST',
							cache: true,
							data:
								JSON.stringify({
									widthcode: widthcode,
									unitcode: unitId,
									widthvalues: widthValue
								}),
							contentType: "application/json",
							url: getAbsolutePath() +  "width/edit",
							success: function(data){
								callMessageDialog("Message", "Edit Width successfully!");
								
								$("#listWidth").dataTable().fnDestroy();
								$('#listWidth tbody').empty();
								$("#dialogEditWidth").dialog("close");
								loadData();
							},
							error: function(){
	//							alert('Cant get role detail!');
							}
				    	});
						}
						}
					},
					"Cancel": function(){
						$("#dialogEditWidth").dialog("close");
					}
				}
			});
			
		}
	
	
		// Select option = Delete	
		 if(valueSelected=="Delete"){
			var widthId= $(this).data('id');	 
			$("#dialogDeleteWidth").find("#messageContent").text('Are you sure you want to delete width "' + widthId +'"?');
			$("#dialogDeleteWidth").dialog({
				modal:true,
				show:{
					effect:"slide",
					duration: 500
				},
				title: "Delete Confirm",
				height: 300,
				width: 400,
				buttons:{
					"Yes": function(){
						$.ajax({
				    		dataType: "json",
							type: 'POST',
							data:JSON.stringify({
								widthcode: widthId
							}),
							contentType: "application/json",
							url: getAbsolutePath() +  "width/delete/",
							cache: true,
							success: function(data){
								if(data.width== "ok"){
								callMessageDialog("Message", "Delete successfully!");
								$("#listWidth").dataTable().fnDestroy();
								$('#listWidth tbody').empty();
								$("#dialogDeleteWidth").dialog("close");
								loadData();
								}
								else 
									callMessageDialog("Message", "This message shold never show!");
							},
							error: function(){
								//callMessageDialog('Delete Failed!');
								//deleteFailedMessageDialog();
								callMessageDialog("Message", 'Can\'t Delete Width "'+ widthId +'"!');
								$("#dialogDeleteWidth").dialog("close");
							}
						
				    	});
						
					},
					"No": function(){
						$("#dialogDeleteWidth").dialog("close");
					}
				}
			});
		};
	});
	}
	
	// Click button add
	$('#btnAdd').on('click', function (e) {
		
		$("#dialogAddWidth").find("#txtWidthCode").val('');
		$("#dialogAddWidth").find("#txtWidthValue").val('');
		
		$("#listWidth,#dialogAddWidth").find("#errorTxtWidthCode").text("");
		$("#listWidth,#dialogAddWidth").find("#txtWidthCode").css("background-color", "white");
		$("#listWidth,#dialogAddWidth").find("#txtWidthCode").css("border-color", "");
		
		$("#listWidth,#dialogAddWidth").find("#errorTxtWidthValue").text("");
		$("#listWidth,#dialogAddWidth").find("#txtWidthValue").css("border-color", "");
		
		$("#dialogAddWidth").dialog({
			modal:true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Add New Width",
			height: 270,
			width: 700,
			buttons:{
				"Save":{
				text: "Save",
            	id: "btnSaveNewWidth",
				click: function(){	
					if(!isValidationRequiredFieldAddWidth()){
							callMessageDialog("Message", "Please insert required form!");
						}
					else if(!isValidationOverRangeWhenAddWidth()){
							callMessageDialog("Message", "Width Code or Width Value is over range please check again !");
						}	
					else if($("#dialogAddWidth").find("#txtWidthValue").val()<0){
							callMessageDialog("Message", "Width Value must not below 0 !");
						}
					else{
					var widthcode= $("#dialogAddWidth").find("#txtWidthCode").val();
					var unitId= $("#dialogAddWidth").find("#txtUnitId").val();
					var widthValue= $("#dialogAddWidth").find("#txtWidthValue").val();		
					$.ajax({
			    		dataType: "json",
						type: 'POST',
						data:JSON.stringify({
							widthcode: widthcode
						}),
						contentType: "application/json",
						url: getAbsolutePath() +  "width/isExist/",
						success: function(data){
							if(data.isExisted ==false){
//								callMessageDialog("Message", "Add Accessory Price unsuccessfully !");
//								return ;
								
							$.ajax({
					    		dataType: "json",
								type: 'POST',
								data:
									JSON.stringify({
										widthcode: widthcode,
										unitcode: unitId,
										widthvalues: widthValue
									}),
								contentType: "application/json",
								url: getAbsolutePath() +  "width/add",
								success: function(data){
									//alert('edit success');
									//console.log(data);
									if(data.addWidth==false){
										callMessageDialog("Message", "Add Width unsuccessfully!");
										return ;
									}
									else{
									callMessageDialog("Message", "Add Width successfully!");
									
									$("#listWidth").dataTable().fnDestroy();
									$('#listWidth tbody').empty();
									$("#dialogAddWidth").dialog("close");
									loadData();
									}
								},
								error: function(){
									callMessageDialog("Message", "Add Width unsuccessfully!");
									
								}
					    	});
							}
							else{
								callMessageDialog("Message", "Width Code Already Existed!");
							}
							//end ajax create unit
							
						},
						error: function(){
						//	alert('Error');
						}
			    	});
					}
				}
				},	
				"Cancel": function(){
					$("#dialogAddWidth").dialog("close");
				}
			}
		});
	});
	
	
	
	
	//Function load Dropdown box from unit
    function loadDropDownUnit(){
    	$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: getAbsolutePath() +  "unit/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#dialogEditWidth').find('#txtUnitId').append($('<option>', {
                            value: value.unitcode,
                            text: value.name
                        }));
                        
                        $('#dialogAddWidth').find('#txtUnitId').append($('<option>', {
                            value: value.unitcode,
                            text: value.name
                        }));
                    });
				}else{
					alert('This alert should never show!');
				}
			},
			error: function(){
				alert('Error !!');
			}
    	});
    }
    
    /**
	 * This function is used to call and set params for message dialog.
	 */
	function callMessageDialog(title, messageContent){
		$("#messageDialog").find("#messageContent").text(messageContent);
		$("#messageDialog").dialog({
			modal:true,
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
	
	function isValidationRequiredFieldAddWidth(){
		var widthId= $("#listWidth,#dialogAddWidth").find("#txtWidthCode").val();
		if(widthId.trim() == '' || widthId == null)
			return false;
		
		var widthValue = $('#listWidth,#dialogAddWidth').find('#txtWidthValue').val();
		if(widthValue == '' || widthValue == null) 
			return false;
		
		return true;
	}
	
	/**
	 * This function allow to validate if data over range
	 */
	
	function isValidationOverRangeWhenAddWidth(){
		var widthIdLength= $("#listWidth,#dialogAddWidth").find("#txtWidthCode").val().length;
		if(widthIdLength>20)
			return false;
		var widthValue= $("#listWidth,#dialogAddWidth").find("#txtWidthValue").val().length;
		if(widthValue>20)
			return false;
		return true;
	}
	function isValidationOverRangeWhenEditWidth(){
		var widthValue= $("#listWidth,#dialogEditWidth").find("#txtWidthValue").val().length;
		if(widthValue>20)
			return false;
		return true;
	}
	
	/**
	 * This function allow to validate before edit
	 */
	
	function isValidationRequiredFieldEditWidth(){
		var widthValue = $('#listWidth,#dialogEditWidth').find('#txtWidthValue').val();
		if(widthValue == '' || widthValue == null) 
			return false;
		
		return true;
	}
	
	/**
	 * Allow input numeric only in Add form
	 */
	$("#listWidth,#dialogAddWidth").on('keydown', '#txtWidthValue' ,
			function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
		||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
		||35<=e.keyCode&&40>=e.keyCode
		||(e.shiftKey||48>e.keyCode||57<e.keyCode)
		&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
	
	/**
	 * Allow input numeric only in Edit form
	 */
	
	$("#listWidth,#dialogEditWidth").on('keydown', '#txtWidthValue' ,
			function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
		||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
		||35<=e.keyCode&&40>=e.keyCode
		||(e.shiftKey||48>e.keyCode||57<e.keyCode)
		&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
	
	//Check range input data when add and space in input field
//	$("#listWidth,#dialogAddWidth").find("#txtWidthCode").on('input propertychange paste',function(e){
//		var input= $(this).val();
//		if(input.length>20||input.length<1){
//			$("#listWidth,#dialogAddWidth").find("#errorTxtWidthCode").text("Your input between 1 - 20 characters");
//			$("#listWidth,#dialogAddWidth").find("#errorTxtWidthCode").css("color", "red");
//			$("#listWidth,#dialogAddWidth").find("#txtWidthCode").css("border-color", "red");
//			$('#listWidth,#btnSaveNewWidth').prop( "disabled", true );
//		}
//		else if($.trim(input) !== input){
//			$("#listWidth,#dialogAddWidth").find("#txtWidthCode").css("border-left", "red 5px solid");
//			$("#listWidth,#dialogAddWidth").find("#errorTxtWidthCode").text("Not allow to type the space as a first/last character");
//			$('#listWidth,#btnSaveNewWidth').prop( "disabled", true );
//		}
//		else{
//			$("#listWidth,#dialogAddWidth").find("#errorTxtWidthCode").text("");
//			$("#listWidth,#dialogAddWidth").find("#txtWidthCode").css("border-color", "green");
//			$('#listWidth,#btnSaveNewWidth').prop( "disabled", false );
//		}
//	})
	//Check range input data when add
	$("#listWidth,#dialogAddWidth").find("#txtWidthValue").keyup(function(){
		var input= $(this).val();
		if(input.length>20){
			$("#listWidth,#dialogAddWidth").find("#errorTxtWidthValue").text("Your input is over range");
			$("#listWidth,#dialogAddWidth").find("#errorTxtWidthValue").css("color", "red");
			$("#listWidth,#dialogAddWidth").find("#txtWidthValue").css("border-color", "red");
			$('#listWidth,#btnSaveNewWidth').prop( "disabled", true );
		}else{
			$("#listWidth,#dialogAddWidth").find("#txtWidthValue").text("");
			$("#listWidth,#dialogAddWidth").find("#errorTxtWidthValue").css("border-color", "green");
			$('#listWidth,#btnSaveNewWidth').prop( "disabled", false );
		}
	})
	//Check range input data when edit
	$("#listWidth,#dialogEditWidth").find("#txtWidthValue").keyup(function(){
		var input= $(this).val();
		if(input.length>20){
			$("#listWidth,#dialogEditWidth").find("#errorTxtWidthValue").text("Your input is over range");
			$("#listWidth,#dialogEditWidth").find("#errorTxtWidthValue").css("color", "red");
			$("#listWidth,#dialogEditWidth").find("#txtWidthValue").css("border-color", "red");
			$('#listWidth,#btnEditNewWidth').prop( "disabled", true );
		}else{
			$("#listWidth,#dialogEditWidth").find("#errorTxtWidthValue").text("");
			$("#listWidth,#dialogEditWidth").find("#txtWidthValue").css("border-color", "green");
			$('#listWidth,#btnEditNewWidth').prop( "disabled", false );
		}
	})
	//Function check widthCode isExist add form
	$("#dialogAddWidth").find("#txtWidthCode").keyup(function(){
		var widthcode = $("#dialogAddWidth").find("#txtWidthCode").val();
		$.ajax({
			dataType: "json",
			type: 'POST',
			data:JSON.stringify({
				widthcode: widthcode
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "width/isExist/",
			success: function(data){
				if(data.isExisted==false){
					if(widthcode.length>20||widthcode.length<1){
						$("#dialogAddWidth").find("#errorTxtWidthCode").text("Your input between 1 - 20 characters");
						$("#dialogAddWidth").find("#errorTxtWidthCode").css("color", "red");
						$("#dialogAddWidth").find("#txtWidthCode").css("border-color", "red");
						$("#dialogAddWidth").find("#txtWidthCode").css("background-color", "white");
						$('#listWidth,#btnSaveNewWidth').prop( "disabled", true );
					}
					else if($.trim(widthcode) !== widthcode){
						$("#dialogAddWidth").find("#errorTxtWidthCode").text("Not allow to type the space as a first/last character");
						$("#dialogAddWidth").find("#errorTxtWidthCode").css("color", "red");
						$("#dialogAddWidth").find("#txtWidthCode").css("border-color", "red");
						$("#dialogAddWidth").find("#txtWidthCode").css("background-color", "white");
						$('#listWidth,#btnSaveNewWidth').prop( "disabled", true );
					}else{
						$("#dialogAddWidth").find("#errorTxtWidthCode").text("Width code is valid!");
						$("#dialogAddWidth").find("#errorTxtWidthCode").css("color", "green");
						$("#dialogAddWidth").find("#txtWidthCode").css("border-color", "green");
						$("#dialogAddWidth").find("#txtWidthCode").css("background-color", "white");
						$('#listWidth,#btnSaveNewWidth').prop( "disabled", false );
					}
				}else{
					$("#dialogAddWidth").find("#errorTxtWidthCode").text("Width code has been created!");
					$("#dialogAddWidth").find("#errorTxtWidthCode").css("color", "red");
					$("#dialogAddWidth").find("#txtWidthCode").css("border-color", "red");
					$("#dialogAddWidth").find("#txtWidthCode").css("background-color", "red");
					$('#listWidth,#btnSaveNewWidth').prop( "disabled", true );
				}
			},
			error: function(){
				$("#dialogAddWidth").find("#errorTxtWidthCode").text("Please enter width code!");
				$("#dialogAddWidth").find("#errorTxtWidthCode").css("color", "red");
				$("#dialogAddWidth").find("#txtWidthCode").css("background-color", "red");
				$('#listWidth,#btnSaveNewWidth').prop( "disabled", true );
				}
		});
		
	});
	
	loadData();
	loadDropDownUnit();
});