$(document).ready(function(){	
	/**
	 * This function is used to load data into table
	 */
function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "ctnrtype/list",
			contentType: "application/json",
			success: function(data){
				if(data.list.length==0){
//					alert("Table have no data.");
				}
				$.each(data.list,function(key,value){
					$('<tr>').append(
							$('<td>').text(value.ctnrcode),
							$('<td>').text(value.description),		
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.ctnrcode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
									
					).appendTo('#listCtnr');
				});
				action();
				$('#listCtnr').DataTable({
					"pagingType": "full_numbers"
			    });
			},
			error: function(){
//				alert("Can not get data!");
			}
		});
	};
	
	$("#listCtnr").find("#btnEdit").click(function(e){
//		alert("Can not get data!");
		
	});
	
	//bảng warning và user
	function action(){	
		//When click edit
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
			var ctnrcode= $(this).data('id');
			
			$("#listCtnr,#dialogEditCtnr").find("#errorTxtDescription").text("");
			$("#listCtnr,#dialogEditCtnr").find("#txtDescription").css("border-color", "");
			
			$(".selectOption").val("Options");
		
			// Select option = Edit
			if(valueSelected=="Edit"){
			$.ajax({
	    		dataType: "json",
				type: 'POST',
				data:JSON.stringify({
					ctnrcode: ctnrcode
				}),
				contentType: "application/json",
				url: getAbsolutePath() +  "ctnrtype/detail",
				success: function(data){
					if(data.status== "ok"){
						$("#dialogEditCtnr").find("#txtCtnrtypeCode").val(data.currentctnrtype.ctnrcode);
						$("#dialogEditCtnr").find("#txtDescription").val(data.currentctnrtype.description);
					}else{
//						alert('This alert should never show!');
					}
				},
				error: function(){
//					alert('Cant get detail');
				}
	    	});
			
			$("#dialogEditCtnr").dialog({
				modal: true,
				resizable: false,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Edit Container Type",
				height: 300,
				width: 450,
				buttons:{
					"Edit":{
						text: "Save",
	                	id: "btnEditNewCtnr",
	                	click: function(){
						var ctnrcode= $("#dialogEditCtnr").find("#txtCtnrtypeCode").val();
						var description= $("#dialogEditCtnr").find("#txtDescription").val();
						
						if(!isValidationRequiredFieldEditCtnr()){
							callMessageDialog("Message", "Please insert Container Type Code!");
						}
						else {
						
						$.ajax({
				    		dataType: "json",
							type: 'POST',
							cache: true,
							data:
								JSON.stringify({
									ctnrcode: ctnrcode,
									description: description
								}),
							contentType: "application/json",
							url: getAbsolutePath() +  "ctnrtype/edit",
							success: function(data){
								callMessageDialog("Message", "Edit Container Type successfully!");
								
								$("#listCtnr").dataTable().fnDestroy();
								$('#listCtnr tbody').empty();
								$("#dialogEditCtnr").dialog("close");
								loadData();
							},
							error: function(){
//								alert('Cant get role detail!');
							}
				    	});
						}
					}
					},
					"Cancel": function(){
						$("#dialogEditCtnr").dialog("close");
					}
				}
			});
			
		}
		
		
		// Select option = Delete
		 if(valueSelected=="Delete"){
			var ctnrcode= $(this).data('id');
			$("#dialogDeleteCtnr").find("#messageContent").text('Are you sure you want to delete Container Type "' + ctnrcode+'"?');
			$("#dialogDeleteCtnr").dialog({
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
								ctnrcode: ctnrcode
							}),
							contentType: "application/json",
							url: getAbsolutePath() +  "ctnrtype/delete/",
							success: function(data){
								callMessageDialog("Message", "Delete successfully!");
								$("#listCtnr").dataTable().fnDestroy();
								$('#listCtnr tbody').empty();
								$("#dialogDeleteCtnr").dialog("close");
								loadData();
								
							},
							error: function(){
								callMessageDialog("Message", 'Can\'t Delete Accessory Supplier "'+'"!');
								$("#dialogDeleteCtnr").dialog("close");
							}
						
				    	});
						
					},
					"No": function(){
						$("#dialogDeleteCtnr").dialog("close");
					}
				}
			});
		};
	});
	}
	
	//when click add button
	$('#btnAdd').on('click', function (e) {
		
		$("#dialogAddCtnr").find("#txtCtnrtypeCode").val('');
		$("#dialogAddCtnr").find("#txtDescription").val('');
		
		$("#listCtnr,#dialogAddCtnr").find("#errorTxtCtnrtypeCode").text("");
		$("#listCtnr,#dialogAddCtnr").find("#txtCtnrtypeCode").css("border-color", "");
		
		$("#listCtnr,#dialogAddCtnr").find("#errorTxtDescription").text("");
		$("#listCtnr,#dialogAddCtnr").find("#txtDescription").css("border-color", "");
		
		$("#dialogAddCtnr").dialog({
			modal: true,
			resizable: false,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Add New Container Type",
			height: 300,
			width: 450,
			buttons:{
				"Save":{
					text: "Save",
                	id: "btnSaveNewCtnr",
                	click: function(){	
					var ctnrcode= $("#dialogAddCtnr").find("#txtCtnrtypeCode").val();		
					if(!isValidationRequiredFieldAddCtrn()){
							callMessageDialog("Message", "Please insert required form!");
						}
					else if(!isValidationOverRangeWhenAddCtrn()){
							callMessageDialog("Message", "Container Type code is over range please check again !");
						}
					//	else if()
					else{
					var ctnrcode= $("#dialogAddCtnr").find("#txtCtnrtypeCode").val();
					var description= $("#dialogAddCtnr").find("#txtDescription").val();
					$.ajax({
			    		dataType: "json",
						type: 'POST',
						data:JSON.stringify({
							ctnrcode: ctnrcode
						}),
						contentType: "application/json",
						url: getAbsolutePath() +  "ctnrtype/isExist/",
						success: function(data){
							if(data.isExisted ==false){
							//ajax create a new unit
							$.ajax({
					    		dataType: "json",
								type: 'POST',
								data:
									JSON.stringify({
										ctnrcode: ctnrcode,
										description: description
									}),
								contentType: "application/json",
								url: getAbsolutePath() +  "ctnrtype/add",
								success: function(data){
									//alert('edit success');
									//console.log(data);
									
									callMessageDialog("Message", "Save Container Type successfully!");
									
									$("#listCtnr").dataTable().fnDestroy();
									$('#listCtnr tbody').empty();
									$("#dialogAddCtnr").dialog("close");
									loadData();
								},
								error: function(){
									callMessageDialog("Message", "Save Container Type unsuccessfully!");
									
								}
					    	});
							}
							else{
								callMessageDialog("Message", " Container Type Already Exist!");
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
					$("#dialogAddCtnr").dialog("close");
				}
			}
		});
	});
  
    /**
	 * This function is used to call and set params for message dialog.
	 */
	function callMessageDialog(title, messageContent){
		$("#messageDialog").find("#messageContent").text(messageContent);
		$("#messageDialog").dialog({
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
	
	function isValidationRequiredFieldAddCtrn(){
		var ctnrCode= $("#listCtnr,#dialogAddCtnr").find("#txtCtnrtypeCode").val();
		if(ctnrCode.trim() == '' || ctnrCode == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function allow to validate if data over range
	 */
	
	function isValidationOverRangeWhenAddCtrn(){
		var ctnrCodeLength= $("#listCtnr,#dialogAddCtnr").find("#txtCtnrtypeCode").val().length;
		if(ctnrCodeLength>20)
			return false;
		var descriptionLength= $("#listCtnr,#dialogAddCtnr").find("#txtCtnrtypeCode").val().length;
		if(descriptionLength>200)
			return false;
		return true;
	}
	function isValidationRequiredFieldEditCtnr(){
		var description= $("#listCtnr,#dialogEditCtnr").find("#txtDescription").val().length;
		if(description>200)
			return false;
		return true;
	}
		//check if data input between 1 - 20 and space character .
//	$("#listCtnr,#dialogAddCtnr").find("#txtCtnrtypeCode").on('input propertychange paste',function(e){
//		var input= $(this).val();
//		if(input.length>20||input.length<1){
//			$("#listCtnr,#dialogAddCtnr").find("#errorTxtCtnrtypeCode").text("Your input between 1 - 20 characters");
//			$("#listCtnr,#dialogAddCtnr").find("#errorTxtCtnrtypeCode").css("color", "red");
//			$("#listCtnr,#dialogAddCtnr").find("#txtCtnrtypeCode").css("border-color", "red");
//			$('#listCtnr,#btnSaveNewCtnr').prop( "disabled", true );
//		}
//		else if($.trim(input) !== input){
//			$("#listCtnr,#dialogAddCtnr").find("#txtCtnrtypeCode").css("border-left", "red 5px solid");
//			$("#listCtnr,#dialogAddCtnr").find("#errorTxtCtnrtypeCode").text("Not allow to type the space as a first/last character");
//			$('#listCtnr,#btnSaveNewCtnr').prop( "disabled", true );
//		}
//		else{
//			$("#listWidth,#dialogAddCtnr").find("#errorTxtCtnrtypeCode").text("");
//			$("#listWidth,#dialogAddCtnr").find("#txtCtnrtypeCode").css("border-color", "green");
//			$('#listCtnr,#btnSaveNewCtnr').prop( "disabled", false );
//		}
//	})
	
	$("#listCtnr,#dialogAddCtnr").find("#txtDescription").on('input propertychange paste',function(e){
		var input= $(this).val();
		if(input.length>200){
			$("#listCtnr,#dialogAddCtnr").find("#errorTxtDescription").text("Your input overange");
			$("#listCtnr,#dialogAddCtnr").find("#errorTxtDescription").css("color", "red");
			$("#listCtnr,#dialogAddCtnr").find("#txtDescription").css("border-color", "red");
			$('#listCtnr,#btnSaveNewCtnr').prop( "disabled", true );
		}
//		else if($.trim(input) !== input){
//			$("#listCtnr,#dialogAddCtnr").find("#errorTxtDescription").css("border-left", "red 5px solid");
//			$("#listCtnr,#dialogAddCtnr").find("#errorTxtDescription").text("Not allow to type the space as a first/last character");
//			$('#listCtnr,#btnSaveNewCtnr').prop( "disabled", true );
//		}
		else{
			$("#listWidth,#dialogAddCtnr").find("#errorTxtDescription").text("");
			$("#listWidth,#dialogAddCtnr").find("#txtDescription").css("border-color", "green");
			$('#listCtnr,#btnSaveNewCtnr').prop( "disabled", false );
		}
	})
	
	$("#listCtnr,#dialogEditCtnr").find("#txtDescription").on('input propertychange paste',function(e){
		var input= $(this).val();
		if(input.length>200){
			$("#listCtnr,#dialogEditCtnr").find("#errorTxtDescription").text("Your input overange");
			$("#listCtnr,#dialogEditCtnr").find("#errorTxtDescription").css("color", "red");
			$("#listCtnr,#dialogEditCtnr").find("#txtDescription").css("border-color", "red");
			$('#listCtnr,#btnEditNewCtnr').prop( "disabled", true );
		}
//		else if($.trim(input) !== input){
//			$("#listCtnr,#dialogEditCtnr").find("#errorTxtDescription").css("border-left", "red 5px solid");
//			$("#listCtnr,#dialogEditCtnr").find("#errorTxtDescription").text("Not allow to type the space as a first/last character");
//			$('#listCtnr,#btnEditNewCtnr').prop( "disabled", true );
//		}
		else{
			$("#listWidth,#dialogEditCtnr").find("#errorTxtDescription").text("");
			$("#listWidth,#dialogEditCtnr").find("#txtDescription").css("border-color", "green");
			$('#listCtnr,#btnEditNewCtnr').prop( "disabled", false );
		}
	})
	
	//check ctnrtype code isExist
		$("#dialogAddCtnr").find("#txtCtnrtypeCode").keyup(function(){
		var ctnrcode = $("#dialogAddCtnr").find("#txtCtnrtypeCode").val();
		$.ajax({
			dataType: "json",
			type: 'POST',
			data:JSON.stringify({
				ctnrcode: ctnrcode
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "ctnrtype/isExist/",
			success: function(data){
				if(data.isExisted==false){
					if(ctnrcode.length>20||ctnrcode.length<1){
						$("#dialogAddCtnr").find("#errorTxtCtnrtypeCode").text("Your input must between 1 - 20 characters");
						$("#dialogAddCtnr").find("#errorTxtCtnrtypeCode").css("color", "red");
						$("#dialogAddCtnr").find("#txtCtnrtypeCode").css("border-color", "red");
						$("#dialogAddCtnr").find("#txtCtnrtypeCode").css("background-color", "white");
						$('#listCtnr,#btnSaveNewCtnr').prop( "disabled", true );
					}
					else if($.trim(ctnrcode) !== ctnrcode){
						$("#dialogAddCtnr").find("#errorTxtCtnrtypeCode").text("Not allow to type the space as a first/last character");
						$("#dialogAddCtnr").find("#errorTxtCtnrtypeCode").css("color", "red");
						$("#dialogAddCtnr").find("#txtCtnrtypeCode").css("border-color", "red");
						$("#dialogAddCtnr").find("#txtCtnrtypeCode").css("background-color", "white");
						$('#listCtnr,#btnSaveNewCtnr').prop( "disabled", true );
					}else{
						$("#dialogAddCtnr").find("#errorTxtCtnrtypeCode").text("Container type code is valid!");
						$("#dialogAddCtnr").find("#errorTxtCtnrtypeCode").css("color", "green");
						$("#dialogAddCtnr").find("#txtCtnrtypeCode").css("border-color", "green");
						$("#dialogAddCtnr").find("#txtCtnrtypeCode").css("background-color", "white");
						$('#listCtnr,#btnSaveNewCtnr').prop( "disabled", false );
					}
				}else{
					$("#dialogAddCtnr").find("#errorTxtCtnrtypeCode").text("Container code has been created!");
					$("#dialogAddCtnr").find("#errorTxtCtnrtypeCode").css("color", "red");
					$("#dialogAddCtnr").find("#txtCtnrtypeCode").css("border-color", "red");
					$("#dialogAddCtnr").find("#txtCtnrtypeCode").css("background-color", "red");
					$('#listCtnr,#btnSaveNewCtnr').prop( "disabled", true );
				}
			},
			error: function(){
				$("#dialogAddCtnr").find("#errorTxtCtnrtypeCode").text("Please enter container type code!");
				$("#dialogAddCtnr").find("#errorTxtCtnrtypeCode").css("color", "red");
				$("#dialogAddCtnr").find("#txtCtnrtypeCode").css("background-color", "red");
				$('#listCtnr,#btnSaveNewCtnr').prop( "disabled", true );
				}
		});
		
	});
	
	loadData();
});