$(document).ready(function(){	

function loadData(){
	//ajax show list type
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "type/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
					alert("Table have no data.");
				}
				$.each(data.list,function(key,value){
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.typeCode),
							$('<td>').text(value.description),

							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.typeCode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
									
					).appendTo('#listType');
				});
				action();
				$('#listType').DataTable({
					"pagingType": "full_numbers"
			    });
			},
			error: function(){
				alert("Can not get data!");
			}
		});
	};
	
	
	// bảng warning và user
//	function action(){	
//		// button edit type
//		$('.btnEdit').on('click', function (e) {	
//			var typeCode= $(this).data('id');
//			
//			// ajax get type by typeCode
//		$.ajax({
//	    		dataType: "json",
//				type: 'GET',
//				data:{},
//				contentType: "application/json",
//				url: "/Chori/type/detail/"+typeCode,
//				success: function(data){
//					if(data.status=="ok"){
//						$("#dialogEditType").find("#txtTypeCode").val(data.currentType.typeCode);
//						$("#dialogEditType").find("#txaDescription").val(data.currentType.description);
//					}else{
//						alert('This alert should never show!');
//					}
//				},
//				error: function(){
//					alert('Cant get Type detail!'+typeCode);
//				}
//	    	});
//			
//			//show dialog update type
//			$("#dialogEditType").dialog({
//				show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Edit Type",
//				height: 300,
//				width: 400,
//				buttons:{
//					"Save": function(){
//						
//						var typeCode= $("#dialogEditType").find("#txtTypeCode").val();
//						var description= $("#dialogEditType").find("#txaDescription").val();
//						
//						if(!isValidationRequiredFieldEditType()){
//							callMessageDialog("Message", "Please update required form!");
//						}
//						else if(!isValidationOverRangeWhenEditType()){
//							callMessageDialog("Message", "Description only 20 characters long!");
//						}	
//						else{
//						//ajax call to update
//						$.ajax({
//				    		dataType: "json",
//							type: 'POST',
//							data:
//								JSON.stringify({
//									typeCode: typeCode,
//									description: description,
//								}),
//							contentType: "application/json",
//							url: "/Chori/type/edit",
//							success: function(data){
//							//	alert('edit success');
//								//console.log(data);
//								
//								callMessageDialog("Message", "Edit type successfully!");
//								
//								$("#listType").dataTable().fnDestroy();
//								$('#listType tbody').empty();
//								loadData();
//								$("#dialogEditType").dialog("close");
//							},
//							error: function(){
//								alert('Cant get role detail!');
//							}
//				    	});
//						}
//					},
//					"Cancel": function(){
//						$("#dialogEditType").dialog("close");
//					}
//				}
//			});
//	});
//		//button to detete type
//		$('.btnDelete').on('click', function (e) {	
//			var typeCode= $(this).data('id');
//			//show dialog confirm before delete
//			$("#dialogDeleteType").dialog({
//				show:{
//					effect:"slide",
//					duration: 500
//				},
//				title: "Delete Confirm",
//				height: 300,
//				width: 400,
//				buttons:{
//					"Yes": function(){
//						//ajax call delete type
//						$.ajax({
//				    		dataType: "json",
//							type: 'POST',
//							data:{},
//							contentType: "application/json",
//							url: "/Chori/type/delete/"+typeCode,
//							success: function(data){
//								callMessageDialog("Message", "Delete type successfully!");
//
//								$("#listType").dataTable().fnDestroy();
//								$('#listType tbody').empty();
//								$("#dialogDeleteType").dialog("close");
//								loadData();
//								
//							},
//							error: function(){
//								callMessageDialog("Message", "Delete type unsuccessfully!");
//								$("#dialogDeleteType").dialog("close");
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
	
	
	function action(){	
		// button edit type
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
			var typeCode= $(this).data('id');
		    $(".selectOption").val("Options");
		    if(valueSelected=="Edit"){

		    	$("#dialogEditType").find("#txaDescription").css("border-left", "");
				$("#dialogEditType").find("#errorTxtDescription").text("");
			// ajax get type by typeCode
		$.ajax({
	    		dataType: "json",
				type: 'POST',
				data:JSON.stringify({
					typeCode : typeCode
				}),
				contentType: "application/json",
				url: getAbsolutePath() +  "type/detail/",
				success: function(data){
					if(data.status=="ok"){
						$("#dialogEditType").find("#txtTypeCode").val(data.currentType.typeCode);
						$("#dialogEditType").find("#txaDescription").val(data.currentType.description);
					}else{
						alert('This alert should never show!');
					}
				},
				error: function(){
					alert('Cant get Type detail!'+typeCode);
				}
	    	});
			
			//show dialog update type
			$("#dialogEditType").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Edit Size Group",
				height: 400,
				width: 500,
				buttons:{
					"Save": {
						text:"Save",
						id:"btnSaveEdit",
						click: function(){
						
						var typeCode= $("#dialogEditType").find("#txtTypeCode").val();
						var description= $("#dialogEditType").find("#txaDescription").val();
						
					
						//ajax call to update
						$.ajax({
				    		dataType: "json",
							type: 'POST',
							data:
								JSON.stringify({
									typeCode: typeCode,
									description: description,
								}),
							contentType: "application/json",
							url: getAbsolutePath() +  "type/edit",
							success: function(data){
							//	alert('edit success');
								//console.log(data);
								
								callMessageDialog("Message", "Edit size group  successfully!");
								
								$("#listType").dataTable().fnDestroy();
								$('#listType tbody').empty();
								loadData();
								$("#dialogEditType").dialog("close");
							},
							error: function(){
								alert('Cant get role detail!');
							}
						});
				    	},
						
					},
					"Cancel": function(){
						$("#dialogEditType").dialog("close");
					}
				}
			});
	}
		//button to detete type
  if(valueSelected=="Delete"){
 
			//show dialog confirm before delete
		$("#dialogDeleteType").find("#messageContent").text('Are you sure you want to delete size group "' +typeCode+'"?');

			$("#dialogDeleteType").dialog({
				modal: true,
				show:{
					effect:"slide",
					duration: 500
				},
				title: "Delete Confirm",
				height: 300,
				width: 400,
				buttons:{
					"Yes": function(){
						//ajax call delete type
						$.ajax({
				    		dataType: "json",
							type: 'POST',
							data:JSON.stringify({
								typeCode : typeCode
							}),
							contentType: "application/json",
							url: getAbsolutePath() +  "type/delete/",
							success: function(data){
								callMessageDialog("Message", "Delete size group successfully!");

								$("#listType").dataTable().fnDestroy();
								$('#listType tbody').empty();
								$("#dialogDeleteType").dialog("close");
								loadData();
								
							},
							error: function(){
								callMessageDialog("Message", "Can't delete!");
								$("#dialogDeleteType").dialog("close");
							}
						
				    	});
						
					},
					"No": function(){
						$("#dialogDeleteType").dialog("close");
					}
				}
			});
		}
		});
	
		//Create a new type
		$('#btnAdd').click(function () {
			$("#dialogAddType").find("#txtTypeCode").css("border-left", "");
			$("#dialogAddType").find("#errorTxtTypeCode").text("");
			$("#dialogAddType").find("#txaDescription").css("border-left", "");
			$("#dialogAddType").find("#errorTxtDescription").text("");
//clear all input
			$('#dialogAddType').find('#txtTypeCode').val("");
			$('#dialogAddType').find('#txaDescription').val("");
	// show dialog add type
			
			$("#dialogAddType").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Add New Size Group ",
				height: 400,
				width: 500,
				buttons:{
					"Save": {
					text:"Save",
					id:"btnSave",
					
					click: function(){
						var typeCode= $("#dialogAddType").find("#txtTypeCode").val();
						var description= $("#dialogAddType").find("#txaDescription").val();
						if(!isValidationRequiredFieldAddSizeGroup()){
							callMessageDialog("Message", "Please insert required form!");

						}else{
						//ajax check is isExist
						$.ajax({
				    		dataType: "json",
							type: 'POST',
							data:JSON.stringify({
								typeCode : typeCode
							}),
							contentType: "application/json",
							url: getAbsolutePath() +  "type/isExist/",
							success: function(data){
							 if(data.isExisted==false){
								// ajax call add type
								$.ajax({
						    		dataType: "json",
									type: 'POST',
									data:
										JSON.stringify({
											typeCode: typeCode,
											description: description,
										}),
									contentType: "application/json",
									url: getAbsolutePath() +  "type/add",
									success: function(data){
										
										//alert('edit success');
										//console.log(data);
										
										callMessageDialog("Message", "Add size group successfully!");
										
										$("#listType").dataTable().fnDestroy();
										$('#listType tbody').empty();
										$("#dialogAddType").dialog("close");
										loadData();
									},
									error: function(){
										callMessageDialog("Message", "Add size group unsuccessfully!");
										
									}
						    	});}
								else{
									callMessageDialog("Message", " Size group code is Existed!");
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
						$("#dialogAddType").dialog("close");
					}
				
				}
			});
			
		});
		}
		
	
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
	 * This function allow to validate if data null
	 */
	function isValidationRequiredFieldAddSizeGroup(){
		var sizegroupCode= $("#dialogAddType").find("#txtTypeCode").val();
		if(sizegroupCode.trim()== '' || sizegroupCode == null)
			return false;
		return true;
	}
	
	
	
	//------------------------VALIDATION-------------------------
	
//	//DO NOT INPUT NON-NUMBER in Tel, Fax, Tax No
//	$("#addShippinglineDialog,editShippinglineDialog").on('keydown', '#txtTel,#txtFax,#txtTaxNo', function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
//	$("#addShippinglineDialog,editShippinglineDialog").on('keydown', '#txtTel,#txtFax,#txtTaxNo', function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
	
	$("#dialogAddType").find('#txtTypeCode').on('input propertychange paste', function (e) {
	
		var typeCode = $("#dialogAddType").find("#txtTypeCode").val();
		//ajax check isExist Unit Code
		$.ajax({
			dataType: "json",
			type: 'POST',
			data:JSON.stringify({
				typeCode : typeCode
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "type/isExist/",
			success: function(data){
				if(data.isExisted==true){
					//if code is existed
					$("#dialogAddType").find("#errorTxtTypeCode").text("Size group code you enter is existed!");
					$("#dialogAddType").find("#errorTxtTypeCode").css("color", "red");
					$('#btnSave').prop( "disabled", true );

				}
			}
		});
		if(typeCode.length>20 || typeCode.length<1) {
			
			$("#dialogAddType").find("#txtTypeCode").css("border-left", "red 5px solid");
			$("#dialogAddType").find("#errorTxtTypeCode").text("Size group code must be between 1-20 characters");
			$("#dialogAddType").find("#errorTxtTypeCode").css("color", "red");

			$('#btnSave').prop( "disabled", true );
		}
		else if ($.trim(typeCode) !== typeCode){
			$("#dialogAddType").find("#txtTypeCode").css("border-left", "red 5px solid");
			$("#dialogAddType").find("#errorTxtTypeCode").text("Not allow to type the space as a first/last character");
			$('#btnSave').prop( "disabled", true );
		}
		else{
			$("#dialogAddType").find("#txtTypeCode").css("border-left", "blue 5px solid");

			$("#dialogAddType").find("#errorTxtTypeCode").text("Size group code is valid!");
			$("#dialogAddType").find("#errorTxtTypeCode").css("color", "green");
			$("#dialogAddType").find("#txtTypeCode").css("background-color", "white");
			$('#btnSave').prop( "disabled", false );
		}
	});
	$("#dialogAddType").find('#txaDescription').on('input propertychange paste', function (e) {
		var shortname = $("#dialogAddType").find("#txaDescription").val();
		if(shortname.length>500 ) {
			$("#dialogAddType").find("#txaDescription").css("border-left", "red 5px solid");
			$("#dialogAddType").find("#errorTxtDescription").text("Description must be between 1-500 characters");
			$('#btnSave').prop( "disabled", true );
		}
		else{				
			$("#dialogAddType").find("#txaDescription").css("border-left", "blue 5px solid");
			$("#dialogAddType").find("#errorTxtDescription").text("");
			$('#btnSave').prop( "disabled", false );
		}
	});

	
	
	$("#dialogEditType").find("#txaDescription").on('input propertychange paste', function (e) {
		var shortname = $("#dialogEditType").find("#txaDescription").val();
		if(shortname.length>500 ) {
			$("#dialogEditType").find("#txaDescription").css("border-left", "red 5px solid");
			$("#dialogEditType").find("#errorTxtDescription").text("Description must be between 1-500 characters");
			$('#btnSaveEdit').prop( "disabled", true );
		}
		else{				
			$("#dialogEditType").find("#txaDescription").css("border-left", "blue 5px solid");
			$("#dialogEditType").find("#errorTxtDescription").text("");
			$('#btnSaveEdit').prop( "disabled", false );
		}
	});
			
		
		
			
	loadData();
});
	
