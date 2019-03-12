$(document).ready(function(){
	/**
	 * --------------START: Load packingguide list & set data to table ------------------
	 */
	function loadData(){
		//list
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "packingguide/list",
			contentType: "application/json",
			success: function(data){
				//alert(data.length);
				var i = 1;
				if(data.list.length==0){
					
				}
				$.each(data.list,function(key,value){
					//alert(value.createdate);
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.packingguidecode),
							$('<td>').text(value.description),
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.packingguidecode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
									
					).appendTo('#listPackingguide');
				});
				action();
				
				$('#listPackingguide').DataTable( {
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
		//When lick create new packingguide
		$('#btnAdd').on('click', function (e) {
			
			$("#dialogAddPackingguide").find("#txtCode").val('');
			$("#dialogAddPackingguide").find("#txtDescription").val('');
			
			$("#dialogAddPackingguide").find("#errorTxtCode").text("");
			$("#dialogAddPackingguide").find("#txtCode").css("border-color", "");
			
			$("#dialogAddPackingguide").find("#errorTxtDescription").text("");
			$("#dialogAddPackingguide").find("#txtDescription").css("border-color", "");
			
			$("#dialogAddPackingguide").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Add New Packing Guide",
				height: 350,
				width: 450,
				buttons:{
					"Save":{
						text: "Save",
						id : "btnSaveKind",
						click: function(){
						var packingguidecode= $("#dialogAddPackingguide").find("#txtCode").val();
						
						if(!isValidationRequiredFieldAddPackingguide()){
							callMessageDialog("Message", "Please enter packing guide code!");
						}
						
						else if(!isValidationOverRangeWhenAddPackingguide()){
								callMessageDialog("Message", "Code  only 50 characters long!");
							}
						else if($.trim(packingguidecode) !== packingguidecode){
							callMessageDialog("Message", "Not allow to type the space as a first/last character on  Code!");
						}
						
						else{
								var packingguidecode= $("#dialogAddPackingguide").find("#txtCode").val();
								var description= $("#dialogAddPackingguide").find("#txtDescription").val();
								$.ajax({
									dataType: "json",
									type: 'POST',
									data:JSON.stringify({
										packingguidecode: packingguidecode
									}),
									contentType: "application/json",
									url: getAbsolutePath() +  "packingguide/isExist/",
									success: function(data){
										if(data.isExisted==false){
											$.ajax({
									    		dataType: "json",
												type: 'POST',
												data:
													JSON.stringify({
														packingguidecode: packingguidecode,
														description: description
													}),
												contentType: "application/json",
												url: getAbsolutePath() +  "packingguide/add",
												success: function(data){
													callMessageDialog("Message", "Add Packing Guide successfully !");
													
													$("#listPackingguide").dataTable().fnDestroy();
													$('#listPackingguide tbody').empty();
													$("#dialogAddPackingguide").dialog("close");
													loadData();
												},
												error: function(){
													callMessageDialog("Message", "Add Packing Guide unsuccessfully !");
												}
									    	});
										}else{
											callMessageDialog("Message", "Packing Guide Code Already Existed!");
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
						$("#dialogAddPackingguide").dialog("close");
					}
				}
			});
		});
		
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    var packingguidecode= $(this).data('id');//this is RoleID of each record.
		    //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $(".selectOption").val("Options");
		    
		    
		    //If user choose edit option
		    if(valueSelected=="Edit"){
		    	
				$.ajax({
		    		dataType: "json",
					type: 'POST',
					data:JSON.stringify({
						packingguidecode: packingguidecode
					}),
					contentType: "application/json",
					url: getAbsolutePath() +  "packingguide/detail/",
					success: function(data){
						if(data.status=="ok"){
							$("#dialogEditPackingguide").find("#txtCode").val(data.currentpackingguide.packingguidecode);
							$("#dialogEditPackingguide").find("#txtDescription").val(data.currentpackingguide.description);
						}else{
							alert('This alert should never show!');
						}
					},
					error: function(){
						alert('Cant get role detail!');
					}
		    	});
				
				$("#dialogEditPackingguide").dialog({
					modal : true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Edit Packing Guide",
					height: 350,
					width: 450,
					buttons:{
						"Save":{
							text: "Save",
							id : "btnSaveKind",
							click : function(){
							var packingguidecode= $("#dialogEditPackingguide").find("#txtCode").val();
							var description= $("#dialogEditPackingguide").find("#txtDescription").val();
							//
								$.ajax({
						    		dataType: "json",
									type: 'POST',
									data:
										JSON.stringify({
											packingguidecode: packingguidecode,
											description: description,
										}),
									contentType: "application/json",
									url: getAbsolutePath() +  "packingguide/edit",
									success: function(data){
									//	alert('edit success');
										//console.log(data);
										
										callMessageDialog("Message", "Edit Packing Guide successfully!");
										
										$("#listPackingguide").dataTable().fnDestroy();
										$('#listPackingguide tbody').empty();
										$("#dialogEditPackingguide").dialog("close");
										loadData();
										
									},
									error: function(){
										callMessageDialog("Message", "Edit Packing Guide unsuccessfully!");
									}
						    	});
							}
							
						},
						"Cancel": function(){
							$("#dialogEditPackingguide").dialog("close");
						}
					}
				});
		    };
		    
		    //if user choose delete option
		    if(valueSelected=="Delete"){				
		    	var packingguidecode= $(this).data('id');
				$("#deletePackingguideDialog").find("#messageContent").text('Are you sure you want to delete Packing Guide  "' + packingguidecode+'"?');
		    
				$("#deletePackingguideDialog").dialog({
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
									packingguidecode: packingguidecode
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "packingguide/delete/",
								success: function(data){
									if(data.status=="ok"){
										if(data.deletePackingguide== true){
											callMessageDialog("Message", "Delete successfully!");
											$("#listPackingguide").dataTable().fnDestroy();
											$('#listPackingguide tbody').empty();
											loadData();
											$("#deletePackingguideDialog").dialog("close");
										}
										else if(data.deletePackingguide== false){
											callMessageDialog("Message", 'Can\'t Delete Packing Guide "'+ '"!');
											$("#deletePackingguideDialog").dialog("close");
										}else{
											callMessageDialog("Message", 'Can\'t Delete Packing Guide  "'+ '"!');
										}
									}
								},
								error: function(){
									callMessageDialog("Message", 'Can\'t Delete Packing Guide  "'+ '"!');
								}
							});
						},
						"NO": function(){
							$("#deletePackingguideDialog").dialog("close");
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
	
	function isValidationRequiredFieldAddPackingguide(){
		var packingguideCode= $("#dialogAddPackingguide").find("#txtCode").val();
		if(packingguideCode.trim()== '' || packingguideCode == null)
			return false;
		return true;
	}
	
	/**
	 * This function allow to validate if data over range
	 */
	
	function isValidationOverRangeWhenAddPackingguide(){
		var packingguideCodeLength= $("#listPackingguide,#dialogAddPackingguide").find("#txtCode").val().length;
		if(packingguideCodeLength>50)
			return false;
		return true;
	}

	//validate for create form
	$("#listPackingguide,#dialogAddPackingguide").find("#txtCode").on('input propertychange paste',function(e){
		var input= $(this).val();
		//error  overrange packingguidecode
		if(input.length>50||input.length<1){
			$("#listPackingguide,#dialogAddPackingguide").find("#errorTxtCode").text("Your input between 1 - 50 characters");
			$("#listPackingguide,#dialogAddPackingguide").find("#errorTxtCode").css("color", "red");
			$("#listPackingguide,#dialogAddPackingguide").find("#txtCode").css("background-color", "red");
			$('#dialogAddPackingguide,#btnSaveKind').prop( "disabled", true );
		}
		//error Not allow to type the space as a first/last character
		else if($.trim(input) !== input){
			$("#listPackingguide,#dialogAddPackingguide").find("#errorTxtCode").text("Not allow to type the space as a first/last character");
			$("#listPackingguide,#dialogAddPackingguide").find("#errorTxtCode").css("color", "red");
			$("#listPackingguide,#dialogAddPackingguide").find("#txtCode").css("background-color", "red");
			$('#dialogAddDestination,#btnSaveKind').prop( "disabled", true );
		}
		else{
			$("#listPackingguide,#dialogAddPackingguide").find("#errorTxtCode").text("Packing guide code is valid!");
			$("#dialogAddPackingguide").find("#errorTxtCode").css("color", "green");
			$("#listPackingguide,#dialogAddPackingguide").find("#txtCode").css("background-color", "white");
			$('#dialogAddPackingguide,#btnSaveKind').prop( "disabled", false );
//			if($.trim() === '' || $ == null){
//				$("#dialogAddPackingguide").find("#errorTxtCode").text("Please enter factory code!");
//				$("#dialogAddPackingguide").find("#errorTxtCode").css("color", "red");
//				$(this).css("background-color", "red");
//			}
		}
	})
	//error Existed garmentkindcode
		$("#listPackingguide,#dialogAddPackingguide").find("#txtCode").keyup(function(){
		var packingguidecode= $("#listPackingguide,#dialogAddPackingguide").find("#txtCode").val();
		$.ajax({
			dataType: "json",
			type: 'POST',
			data:JSON.stringify({
				packingguidecode: packingguidecode
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "packingguide/isExist/",
			success: function(data){
				if(data.isExisted==false){
					if(packingguidecode.length>50){
						$("#listPackingguide,#dialogAddPackingguide").find("#errorTxtCode").text("Your input between 1 - 50 characters");
						$("#listPackingguide,#dialogAddPackingguide").find("#errorTxtCode").css("color", "red");
						$("#listPackingguide,#dialogAddPackingguide").find("#txtCode").css("background-color", "red");
						$('#dialogAddPackingguide,#btnSaveKind').prop( "disabled", true );
					}
					else if($.trim(packingguidecode) !== packingguidecode){
						$("#listPackingguide,#dialogAddPackingguide").find("#errorTxtCode").text("Not allow to type the space as a first/last character");
						$("#listPackingguide,#dialogAddPackingguide").find("#errorTxtCode").css("color", "red");
						$("#listPackingguide,#dialogAddPackingguide").find("#txtCode").css("background-color", "red");
						$('#dialogAddPackingguide,#btnSaveKind').prop( "disabled", true );
					}else{
						$("#listPackingguide,#dialogAddPackingguide").find("#errorTxtCode").text("Packing guide code is valid!");
						$("#dialogAddPackingguide").find("#errorTxtCode").css("color", "green");
						$("#listPackingguide,#dialogAddPackingguide").find("#txtCode").css("background-color", "white");
						$('#dialogAddPackingguide,#btnSaveKind').prop( "disabled", false );
						if(packingguidecode.trim() === '' || packingguidecode == null){
							$("#dialogAddPackingguide").find("#errorTxtCode").text("Please enter packing guide code!");
							$("#dialogAddPackingguide").find("#errorTxtCode").css("color", "red");
							$("#listPackingguide,#dialogAddPackingguide").find("#txtCode").css("background-color", "red");
						}
					}
				}else{
					$("#listPackingguide,#dialogAddPackingguide").find("#errorTxtCode").text("Packing Guide Code Already Existed!");
					$("#listPackingguide,#dialogAddPackingguide").find("#errorTxtCode").css("color", "red");
					$("#listPackingguide,#dialogAddPackingguide").find("#txtCode").css("background-color", "red");
					$('#dialogAddPackingguide,#btnSaveKind').prop( "disabled", true );
				}
			},
			error: function(){
				//	alert('Error');
				}
		});

	});
		
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
})