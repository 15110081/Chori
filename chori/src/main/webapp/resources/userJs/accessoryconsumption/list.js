//$(document).ready(function(){	
//	
//	//load factory to dropdownbox
//	function loadFactoryData(){
//		$.ajax({
//			dataType: "json",
//            type: 'GET',
//            data:{},
//            url: "/Chori/factory/list",
//            success: function (data) {
//            	$.each(data.list, function( key, value ) {
//                    $("#sltFactory").append($('<option>', {       	
//                        value: value.factorycode,
//                        text: value.shortname
//                    }));
//
//                });
//            },
//            error: function(){
//            	
//            }
//		});
//	}
//	
//	/**
//	 * --------------START: Load accessoryconsumption list & set data to table ------------------
//	 */
//	function loadData(){
//		$.ajax({
//			dataType: "json",
//			type: 'GET',
//			data: {},
//			url: "/Chori/accessoryconsumption/list",
//			contentType: "application/json",
//			success: function(data){
//				if(data.list.length==0){
//					alert("Table have no data.");
//				}			
//				$.each(data.list,function(key,value){
//					
////					//get short name by factory code to create <td>Short Name</td>
////					$.ajax({
////						dataType: "json",
////			            type: 'GET',
////			            data:{},
////			            url: "/Chori/factory/detail/" + value.factorycode,
////			            success: function (data) {
////			            	var factoryshortname = data.factory.shortname;
////			            	
////			            },
////			            error: function(){
////			            	alert("Can not get factory short name by factory code")
////			            }
////					});
//					$('<tr>').append(
//							$('<td>').text(value.factorycode),
//							$('<td>').text(value.factoryshortname),
//							$('<td>').text(value.accessorycode),
//							$('<td>').text(value.accessoryshortname),						
//							$('<td>').text(value.consumption),
//							$('<td>').html('<button class="btn btn-info btnEdit" data-factory="' + value.factorycode +'" data-accessorycode="' + value.accessorycode +'">Edit</button>'
//									+ '<button class="btn btn-danger btnDelete" data-factory="' + value.factorycode +'" data-accessorycode="' + value.accessorycode +'">Delete</button>')											
//					).appendTo('#listAccessoryConsumption');
//				});
//				action();
//
//				$('#listAccessoryConsumption').DataTable( {
//					"pagingType": "full_numbers"
//			    } );
//			},
//			error: function(){
//				alert("Can not get data!");
//			}
//		});
//	};
//	
//	//event when choose factory on dropdownbox
//	function sltFactoryCode_Change(){
//		$('#sltFactory').on('change', function (e) {
//			var optionSelected = $(this).find("option:selected");
//			var valueSelected  = optionSelected.val();
//			$.ajax({
//				dataType: "json",
//				type: 'GET',
//				data: {},
//				url: "/Chori/accessoryconsumption/list",
//				contentType: "application/json",
//				success: function(data){
//					$("#listAccessoryConsumption").dataTable().fnDestroy();
//					$('#listAccessoryConsumption tbody').empty();
//					if(data.list.length==0){
//						alert("Table have no data.");
//					}
//					else{
//					$.each(data.list,function(key,value){
//						if(valueSelected==value.factorycode) {
//								$('<tr>').append(
//										$('<td>').text(value.factorycode),
//										$('<td>').text(value.factoryshortname),
//										$('<td>').text(value.accessorycode),
//										$('<td>').text(value.accessoryshortname),
//										$('<td>').text(value.consumption),
//										$('<td>').html('<button class="btn btn-info btnEdit" data-factory="' + value.factorycode +'" data-accessorycode="' + value.accessorycode +'">Edit</button>'
//												+ '<button class="btn btn-danger btnDelete" data-factory="' + value.factorycode +'" data-accessorycode="' + value.accessorycode +'">Delete</button>')											
//								).appendTo('#listAccessoryConsumption');
//							
//						}			
//					});
//					}				
//					action();
//					$('#listAccessoryConsumption').DataTable( {
//						"pagingType": "full_numbers"
//				    } );
//				},
//				error: function(){
//					alert("Can not get data!");
//				}
//			});
//		});
//	};
//	
//	//handle click event for edit + delete button of table
//	function action(){
//		//When click edit
//		$('.btnEdit').on('click', function (e) {
//			var factorycode = $(this).data('factory');
//			var accessorycode = $(this).data('accessorycode');
//			$( "#dialogEditAccessoryConsumption" ).find("#txtFactoryCode").val(factorycode);
//			$( "#dialogEditAccessoryConsumption" ).find("#txtAccessoryCode").val(accessorycode);
//			$.ajax({
//				dataType: "json",
//				type: 'GET',
//				data:{},
//				contentType: "application/json",
//				url: "/Chori/accessoryconsumption/detail/" + factorycode + "/" + accessorycode,
//				success: function(data){
//					//if load detail success, then open edit dialog
//					if(data.status=="ok"){
//						//set consumption on textbox
//						$( "#dialogEditAccessoryConsumption" ).find("#txtConsumption").val(data.accessoryconsumption.consumption);		
//				        $( "#dialogEditAccessoryConsumption" ).dialog({
//				        	title: "Edit Accessory Consumption",
//				            show: {
//				                effect: "slide",
//				                duration: 300
//				            },
//				            height: 400,
//				            width: 320,
//				            modal: false,
//				            buttons: {
//				                "Save": {
//				                	text: "Save",
//				                	id: "btnEditAccesoryConsumption",
//				                	click: function(){
//				                	//get data from consumption textbox
//				                	var factorycode = $( "#dialogEditAccessoryConsumption" ).find("#txtFactoryCode").val();
//				                	var accessorycode = $( "#dialogEditAccessoryConsumption" ).find("#txtAccessoryCode").val();
//									var consumption = $( "#dialogEditAccessoryConsumption" ).find("#txtConsumption").val();	
//									//convert object to JSON format
//									$.ajax({
//										dataType: "json",
//										type: 'POST',
//										data:
//											JSON.stringify({
//												accessorycode: accessorycode,
//												factorycode: factorycode,
//												consumption: consumption
//											}),
//										contentType: "application/json",
//										url: "/Chori/accessoryconsumption/edit",
//										success: function(data){
//											if(data.editStatus == true){											
//												//close dialog and clear input
//												$("#dialogEditAccessoryConsumption").dialog("close");
//												
//												//reload table
//												$("#listAccessoryConsumption").dataTable().fnDestroy();
//												$('#listAccessoryConsumption tbody').empty();
//												loadData();
//												callMessageDialog("Message", 'Edit successfully !!!');
//											}else if(data.editStatus == false){
//												alert('This alert maybe never show! (4)');
//											}else{
//												alert('This alert maybe never show! (5)');
//											}
//										},
//										error: function(){
//											alert('This alert maybe never show! (3)');
//										}
//									});
//									//end edit	                   
//				                	}
//				                },
//				                "Cancel": function() {
//				                    $( "#dialogEditAccessoryConsumption" ).dialog( "close" );
//				                }
//				            },
//				            hide: {
//				                effect: "slide",
//				                duration: 300
//				            }
//				        });
//						}
//				},
//				error: function(){
//					callErrorMessageDialog("Error", 'Get accessory consumption detail failed!');
//				}
//			});
//		});
//		//When click delete
//		$('.btnDelete').on('click', function (e) {
//			var factorycode = $(this).data('factory');
//			var accessorycode = $(this).data('accessorycode');
//			//do delete when user choose OK 
//			$.ajax({
//				dataType: "json",
//				type: 'POST',
//				data:{},
//				contentType: "application/json",
//				url: "/Chori/accessoryconsumption/delete/" + factorycode + "/" + accessorycode,
//				success: function(data){
//					if(data.status=="ok"){
//						if(data.deleteStatus== true){
//							$("#listAccessoryConsumption").dataTable().fnDestroy();
//							$('#listAccessoryConsumption tbody').empty();
//							loadData();
//							callMessageDialog("Message", 'Delete successfully !!!');
//							
//						}else if(data.deleteStatus== false){
//							callErrorMessageDialog("Error", 'Delete Failed !!!');
//						}else{
//							alert("this cant be show, 243!");
//						}
//					}else{
//						alert("unexpected error! (2), 404");
//					}
//				},
//				error: function(){
//					alert("unexpected error!");
//				}
//			});
//		});
//	}
//	
//	//handle click Create Button
//	function btnCreate_Click(){
//		//load factory short name to dropdownbox
//		$.ajax({
//			dataType: "json",
//            type: 'GET',
//            data:{},
//            url: "/Chori/factory/list",
//            success: function (data) {
//            	$.each(data.list, function( key, value ) {
//                    $("#sltAddFactory").append($('<option>', {       	
//                        value: value.factorycode,
//                        text: value.shortname
//                    }));
//
//                });
//            },
//            error: function(){
//            	
//            }
//		});
//		//load accessory name to dropdownbox
//		$.ajax({
//			dataType: "json",
//            type: 'GET',
//            data:{},
//            url: "/Chori/accessory/list",
//            success: function (data) {
//            	$.each(data.list, function( key, value ) {
//                    $("#sltAddAccessoryCode").append($('<option>', {       	
//                        value: value.accessorycode,
//                        text: value.name
//                    }));
//
//                });
//            },
//            error: function(){
//            	
//            }
//		});
//		//When click create
//		$('#btnCreate').on('click', function (e) {
//			$("#dialogCreateAccessoryConsumption").find("#txtFactoryCode").val("");
//			$("#dialogCreateAccessoryConsumption").find("#txtAccessoryCode").val("");
//			$("#dialogCreateAccessoryConsumption").find("#txtConsumption").val("");
//			$("#dialogCreateAccessoryConsumption").find("#txtConsumption").css("border-left", "gray 1px solid");
//	        $( "#dialogCreateAccessoryConsumption" ).dialog({
//	        	title: "Create New Accessory Consumption",
//	            show: {
//	                effect: "slide",
//	                duration: 300
//	            },
//	            height: 400,
//	            width: 320,
//	            modal: false,
//	            buttons: {
//	                "Save":{
//	                	text: "Save",
//	                	id: "btnSaveNewAccesoryConsumption",
//	                	click: function(){
//						//check accessory consumption is existed
//						//var factorycode = $("#dialogCreateAccessoryConsumption").find("#txtFactoryCode").val();
//						var factoryOptionSelected = $("#sltAddFactory").find("option:selected");
//						var factorycode  = factoryOptionSelected.val();
//						//var accessorycode = $("#dialogCreateAccessoryConsumption").find("#txtAccessoryCode").val();
//						var accessoryOptionSelected = $("#sltAddAccessoryCode").find("option:selected");
//						var accessorycode  = accessoryOptionSelected.val();
//						$.ajax({
//							dataType: "json",
//							type: 'GET',
//							data:{},
//							contentType: "application/json",
//							url: "/Chori/accessoryconsumption/isExist/"+factorycode+"/"+accessorycode,
//							success: function(data){
//								if(data.isExisted == false){
//									var consumption = $("#dialogCreateAccessoryConsumption").find("#txtConsumption").val();									
//									//convert object to JSON format
//									$.ajax({
//										dataType: "json",
//										type: 'POST',
//										data:
//											JSON.stringify({
//												factorycode: factorycode,
//												accessorycode: accessorycode,
//												consumption: consumption
//											}),
//										contentType: "application/json",
//										url: "/Chori/accessoryconsumption/add",
//										success: function(data){
//											if(data.addStatus == true){																					
//												$( "#dialogCreateAccessoryConsumption" ).dialog( "close" );												
//												//reload table
//												$("#listAccessoryConsumption").dataTable().fnDestroy();
//												$('#listAccessoryConsumption tbody').empty();
//												loadData();
//												callMessageDialog("Message", 'Create Accessory Consumption successfully!');
//												
//											}else if(data.addStatus == false){
//												alert('This alert maybe never show! (4)');
//											}else{
//												alert('This alert maybe never show! (5)');
//											}
//										},
//										error: function(){
//											callErrorMessageDialog("Error", 'Create Accessory Consumption Failed!');
//										}
//									});
//									//end edit
//								}
//								else if(data.isExisted == true){
//									callMessageDialog("Warning", 'Accessory Consumption is existed!');
//								}
//							}
//							});	
//	                	
//	                	
//	                }},
//	                "Cancel": function() {
//	                    $( "#dialogCreateAccessoryConsumption" ).dialog( "close" );
//	                }
//	            },
//	            hide: {
//	                effect: "slide",
//	                duration: 300
//	            }
//	        });
//		});
//	}
//	
//	/**
//	 * This function is used to call and set params for message dialog.
//	 */
//	function callMessageDialog(title, messageContent){
//		$("#messageDialog").find("#messageContent").text(messageContent);
//		$("#messageDialog").dialog({
//			show:{
//				effect:"slide",
//				duration: 500
//			},
//			title: title,
//			height: 250,
//			width: 300,
//			hide: {
//				effect: "slide",
//				duration: 500
//			},
//			buttons:{
//				"OK": function(){
//					$(this).dialog("close");
//				}
//			}
//		}).prev(".ui-widget-header").css("color","#333");
//	}
//	
//	function callErrorMessageDialog(title, messageContent){
//		$("#messageDialog").find("#messageContent").text(messageContent);
//		$("#messageDialog").dialog({
//			show:{
//				effect:"slide",
//				duration: 500
//			},
//			title: title,
//			height: 250,
//			width: 300,
//			hide: {
//				effect: "slide",
//				duration: 500
//			},
//			buttons:{
//				"OK": function(){
//					$(this).dialog("close");
//				}
//			}
//		}).prev(".ui-widget-header").css("color","#f5321c");
//	}
//	//------------------------VALIDATION-------------------------
//	//DO NOT INPUT NON-NUMBER in Consumption
//	$("#dialogCreateAccessoryConsumption").on('keydown', '#txtConsumption', function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
//	$("#dialogEditAccessoryConsumption").on('keydown', '#txtConsumption', function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
//	//validate for create form
//	$("#dialogCreateAccessoryConsumption").find('#txtConsumption').on('input propertychange paste', function (e) {
//		var input= $("#dialogCreateAccessoryConsumption").find("#txtConsumption").val();
//		if(input.length>10 || input.length<1){
//			$("#dialogCreateAccessoryConsumption").find("#txtConsumption").css("border-left", "red 5px solid");
//			$("#dialogCreateAccessoryConsumption").find("#errorTxtConsumption").text("Consumption must be between 1-10 characters");
//			$('#btnSaveNewAccesoryConsumption').prop( "disabled", true );
//			}
//		else {
//			$("#dialogCreateAccessoryConsumption").find("#txtConsumption").css("border-left", "blue 5px solid");
//			$("#dialogCreateAccessoryConsumption").find("#errorTxtConsumption").text("");
//			$("#btnSaveNewAccesoryConsumption").prop( "disabled", false  );
//			
//		}
//	});
//	//validate for edit form
//	$("#dialogEditAccessoryConsumption").find('#txtConsumption').on('input propertychange paste', function (e) {
//		var input= $("#dialogEditAccessoryConsumption").find("#txtConsumption").val();
//		if(input.length>10 || input.length<1){
//			$("#dialogEditAccessoryConsumption").find("#txtConsumption").css("border-left", "red 5px solid");
//			$("#dialogEditAccessoryConsumption").find("#errorTxtConsumption").text("Consumption must be between 1-10 characters");
//			$('#btnEditAccesoryConsumption').prop( "disabled", true );
//			}
//		else {
//			$("#dialogEditAccessoryConsumption").find("#txtConsumption").css("border-left", "blue 5px solid");
//			$("#dialogEditAccessoryConsumption").find("#errorTxtConsumption").text("");
//			$("#btnEditAccesoryConsumption").prop( "disabled", false  );
//			
//		}
//	});
//	btnCreate_Click();
//	sltFactoryCode_Change();
//	loadFactoryData();
//	loadData();
//});


$(document).ready(function(){	
	
	//load factory to dropdownbox
//	function loadFactoryData(){
//		$.ajax({
//			dataType: "json",
//            type: 'GET',
//            data:{},
//            url: "/Chori/factory/list",
//            success: function (data) {
//            	$.each(data.list, function( key, value ) {
//                    $("#sltFactory").append($('<option>', {       	
//                        value: value.factorycode,
//                        text: value.shortname
//                    }));
//
//                });
//            },
//            error: function(){
//            	
//            }
//		});
//	}
	
	
	/**
	 * --------------START: Load accessoryconsumption list & set data to table ------------------
	 */
	
	//load factory to dropdownbox
	function loadFactoryData(){
		$("#sltFactory").empty();
        $("#sltFactory").append($('<option>', {       	
            value:"All",
            text:"All"
        }));
		var factoryArray = [];
		var factoryNameArray = [];
		var factoryArrayUnique = [];
		var factoryNameArrayUnique = [];
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() + "accessoryconsumption/list",
			contentType: "application/json",
			success: function(data){	
				$.each(data.list,function(key,value){	
					factoryArray.push(value.factorycode);
					factoryNameArray.push(value.factoryshortname);
				})
				factoryArrayUnique = factoryArray.reduce(function(a,b){if(a.indexOf(b)<0)a.push(b);return a;},[]);
				factoryNameArrayUnique = factoryNameArray.reduce(function(a,b){if(a.indexOf(b)<0)a.push(b);return a;},[]);
				for(var i =0; i<factoryArrayUnique.length;i++)
				{
                    $("#sltFactory").append($('<option>', {       	
                        value:factoryArrayUnique[i],
                        text:factoryNameArrayUnique[i] 
                    }));
				}
			},
			error: function(){
				callMessageDialog("Error", "Can not get factory for drop down box!");
			}
		});
	}
	
	function loadData(){
		$("#listAccessoryConsumption").dataTable().fnDestroy();
		$('#listAccessoryConsumption tbody').empty();
		var factoryArray = [];
		var factoryNameArray = [];
		var factoryArrayUnique = [];
		var factoryNameArrayUnique = [];
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "accessoryconsumption/list",
			contentType: "application/json",
			success: function(data){
				if(data.list.length==0){
//					alert("Table have no data.");
				}			
				$.each(data.list,function(key,value){	
					factoryArray.push(value.factorycode);
					factoryNameArray.push(value.factoryshortname);
				})
				factoryArrayUnique = factoryArray.reduce(function(a,b){if(a.indexOf(b)<0)a.push(b);return a;},[]);
				factoryNameArrayUnique = factoryNameArray.reduce(function(a,b){if(a.indexOf(b)<0)a.push(b);return a;},[]);

				var noNumber=1;
				for(var i =0; i<factoryArrayUnique.length;i++)
				{
					$('<tr>').append(
							$('<td>').text(noNumber),
							$('<td>').text(factoryArrayUnique[i]),
							$('<td>').text(factoryNameArrayUnique[i]),
//							$('<td>').html('<button style="margin-left:10px" class="btn btn-info btnViewDetail" data-factory="' + factoryArrayUnique[i] +'" data-factoryname="' + factoryNameArrayUnique[i] +'">View Detail</button>'
//									+ '<button style="margin-left:10px" class="btn btn-info btnEdit" data-factory="' + factoryArrayUnique[i] +'" data-factoryname="' + factoryNameArrayUnique[i] +'">Edit</button>'
//									+ '<button style="margin-left:10px" class="btn btn-danger btnDelete" data-factory="' + factoryArrayUnique[i] +'">Delete</button>')				
							$('<td>').html('<select class="selectpicker selectOption" data-factory="' + factoryArrayUnique[i] +'" data-factoryname="' + factoryNameArrayUnique[i] +'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option>'
									+'<option value="ViewDetail">View Detail</option></select>')
					).appendTo('#listAccessoryConsumption');
					noNumber++;
				}
				action();
				$('#listAccessoryConsumption').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				CanNotGetDataDialogMessageDialog();
			}
		});
	};
	
	//event when choose factory on dropdownbox
	function sltFactoryCode_Change(){
		$("#listAccessoryConsumption").dataTable().fnDestroy();
		$('#listAccessoryConsumption tbody').empty();
		$('#sltFactory').on('change', function (e) {
			$("#listAccessoryConsumption").dataTable().fnDestroy();
			$('#listAccessoryConsumption tbody').empty();
			var optionSelected = $(this).find("option:selected");
			var valueSelected  = optionSelected.val();
			if(valueSelected=="All"){
				loadData();
			}
			else {
				var factoryArray = [];
				var factoryNameArray = [];
				var factoryArrayUnique = [];
				var factoryNameArrayUnique = [];
				$.ajax({
					dataType: "json",
					type: 'GET',
					data: {},
					url: getAbsolutePath() +  "accessoryconsumption/list",
					contentType: "application/json",
					success: function(data){
						if(data.list.length==0){
//							alert("Table have no data.");
						}			
						$.each(data.list,function(key,value){	
							factoryArray.push(value.factorycode);
							factoryNameArray.push(value.factoryshortname);
						})
						factoryArrayUnique = factoryArray.reduce(function(a,b){if(a.indexOf(b)<0)a.push(b);return a;},[]);
						factoryNameArrayUnique = factoryNameArray.reduce(function(a,b){if(a.indexOf(b)<0)a.push(b);return a;},[]);
						var noNumber=1;
						for(var i =0; i<factoryArrayUnique.length;i++)
						{
							if(valueSelected==factoryArrayUnique[i]) {
								$('<tr>').append(
										$('<td>').text(noNumber),
										$('<td>').text(factoryArrayUnique[i]),
										$('<td>').text(factoryNameArrayUnique[i]),
//										$('<td>').html('<button style="margin-left:10px" class="btn btn-info btnViewDetail" data-factory="' + factoryArrayUnique[i] +'">View Detail</button>'
//												+ '<button style="margin-left:10px" class="btn btn-info btnEdit" data-factory="' + factoryArrayUnique[i] +'">Edit</button>'
//												+ '<button style="margin-left:10px" class="btn btn-danger btnDelete" data-factory="' + factoryArrayUnique[i] +'">Delete</button>')											
										$('<td>').html('<select class="selectpicker selectOption" data-factory="' + factoryArrayUnique[i] +'" data-factoryname="' + factoryNameArrayUnique[i] +'">'
												+'<option value="Options" disabled selected>Options</option>'
												+'<option value="Edit">Edit</option>'
												+'<option value="Delete">Delete</option>'
												+'<option value="ViewDetail">View Detail</option></select>')
								).appendTo('#listAccessoryConsumption');
								noNumber++;
							}
						}
						action();
						$('#listAccessoryConsumption').DataTable( {
							"pagingType": "full_numbers"
					    });
					},
					error: function(){
						CanNotGetDataDialogMessageDialog();
					}
				});
			}
		});
	};
	
	function btnCreate_Click(){
		var accessoryGroupToAddArray = [];
		var accessoryToAddArray = [];
		//load factory short name to dropdownbox
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() +  "factory/list",
            success: function (data) {
            	$("#sltAddFactory").empty();
            	$.each(data.list, function( key, value ) {
                    $("#sltAddFactory").append($('<option>', {       	
                        value: value.factorycode,
                        text: value.shortname
                    }));
                    accessoryGroupToAddArray.push(value.accessorygroupCode);
                });
            },
            error: function(){
            	
            }
		});
		
		//load accessory group to table
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() +  "accessorygroup/list",
            success: function (data) {
				//load accessory to table
				$.ajax({
					dataType: "json",
		            type: 'GET',
		            data:{},
		            url: getAbsolutePath() +  "accessory/list",
		            success: function (data) {
		        		$("#listAddAccessoryConsumptionDetail").dataTable().fnDestroy();
		        		$('#listAddAccessoryConsumptionDetail tbody').empty();
		            	$.each(data.list, function( key, value ) {        		
							$('<tr>').append(
									$('<td>').text(value.accessorycode),
									$('<td>').text(value.accessorygroupcode),
									$('<td>').html('<input type="number" step="any" min="0" max="100" class="form-control" data-accessoryCode="' + value.accessorycode +'" id="txtAccessoryCode' + value.accessorycode +'" value="0" name="txtConsumption">')												
							).appendTo('#listAddAccessoryConsumptionDetail');
							accessoryToAddArray.push(value.accessorycode);				
		                });
						$('#listAddAccessoryConsumptionDetail').DataTable( {
		        		    deferRender: true,
		        		    scrollY:     300,
							scroller: true,
						    paging: false
					    });
		            },
		            error: function(){
		            	callMessageDialog("Error", "Can not load accessory to table!");
		            }
				});
				$("#listAddAccessoryGroup").dataTable().fnDestroy();
				$('#listAddAccessoryGroup tbody').empty();
            	$.each(data.list, function( key, value ) {        		
					$('<tr>').append(
							$('<td>').text(value.accessorygroupCode),
							$('<td>').html('<input type="number" step="any" min="0" max="100" class="form-control" data-accessorygroupCode="' + value.accessorygroupCode +'" id="txtAccessoryGroupCode' + value.accessorygroupCode +'" value="0" name="txtConsumption">')												
					).appendTo('#listAddAccessoryGroup');
					var tmpaccessorygroupCode = value.accessorygroupCode;
					//event for change consumption value of accessory by group
					$("#dialogCreateAccessoryConsumption").find("input[id='txtAccessoryGroupCode" + value.accessorygroupCode + "']").on('input propertychange paste', function (e) {						
						$.ajax({
							dataType: "json",
				            type: 'GET',
				            data:{},
				            url: getAbsolutePath() + "accessory/list",
				            success: function (data) {
				            	$.each(data.list, function( key, value ) {  
				            		//if group code of accessory = groupcode on dropdown, then set equal consumption				      
				            		if(value.accessorygroupcode == tmpaccessorygroupCode){
				            			var tmpConsumption = $("#dialogCreateAccessoryConsumption").find("input[id='txtAccessoryGroupCode" + value.accessorygroupcode + "']").val();
				            			$("#dialogCreateAccessoryConsumption").find("input[id='txtAccessoryCode" + value.accessorycode + "']").val(tmpConsumption);
				            		}			
				                });
				            },
				            error: function(){
				            	callMessageDialog("Error", "Cannot load accessory to table");
				            }
						});
					});
					accessoryGroupToAddArray.push(value.accessorygroupCode);
                });
				$('#listAddAccessoryGroup').DataTable( {
				    paging: false
			    });
            },
            error: function(){
            	callMessageDialog("Error", "Cannot load accessory group name to table");
            }
		});
		
//		//load accessory to table
//		$.ajax({
//			dataType: "json",
//            type: 'GET',
//            data:{},
//            url: "/Chori/accessory/list",
//            success: function (data) {
//            	$.each(data.list, function( key, value ) {        		
//					$('<tr>').append(
//							$('<td>').text(value.accessorycode),
//							$('<td>').text(value.accessorygroupcode),
//							$('<td>').html('<input type="number" step="any" min="0" max="100" class="form-control" data-accessoryCode="' + value.accessorycode +'" id="txtAccessoryCode' + value.accessorycode +'" value="0" name="txtConsumption">')												
//					).appendTo('#listAddAccessoryConsumptionDetail');
//					accessoryToAddArray.push(value.accessorycode);				
//                });
//				$('#listAddAccessoryConsumptionDetail').DataTable( {
//					"pagingType": "full_numbers"
//			    });
//            },
//            error: function(){
//            	alert("Cannot load accessory to table");
//            }
//		});
		
		//When click create
		$('#btnCreate').on('click', function (e) {
			btnCreate_Click();
		    $( "#dialogCreateAccessoryConsumption" ).dialog({
		    	title: "Add New Wasted Percentage",
		        show: {
		            effect: "slide",
		            duration: 300
		        },
		        height: 550,
		        width: 800,
		        modal: true,
		        buttons: {
		            "Save":{
		            	text: "Save",
		            	id: "btnSaveNewAccesoryConsumption",
		            	click: function(){
		            		
		            	var validateWastedPercentageValue = true;
		            		
		            	//add consumption by accessory group
	            		$.ajax({
	            			dataType: "json",
	                        type: 'GET',
	                        data:{},
	                        url: getAbsolutePath() + "accessory/list",
	                        success: function (data) {
		                		var factorycodeOptionSelected = $("#dialogCreateAccessoryConsumption").find("#sltAddFactory").find("option:selected");
								var factorycode  = factorycodeOptionSelected.val();
								var consumptionByAccessoryCode;
								var consumptionByAccessoryGroup;
	                			$("#listAddAccessoryConsumptionDetail").find("#txtAccessoryCode").val("");
	                			$("#dialogCreateAccessoryConsumption").find("#txtConsumption").val("");
	                			
	                			//validate consumption must  >=0 && <100 
	                			$.each(data.list, function( key, value ) {     
	                				var validateAccConsumptionValue = $("#dialogCreateAccessoryConsumption").find("input[id='txtAccessoryCode" + value.accessorycode + "']").val();
	                				if( validateAccConsumptionValue < 0 
	                						|| validateAccConsumptionValue >100
	                						|| validateAccConsumptionValue=="" 
	                						|| validateAccConsumptionValue==null){
	                					callMessageDialog("Error", "Wasted Percentage value must between 0 - 100.");
	                					validateWastedPercentageValue = false;
	                					return; 
	                				}
	                			});
	                			
	                			if (validateWastedPercentageValue == true){
		                        	$.each(data.list, function( key, value ) {        		
		                        		consumptionByAccessoryGroup = $("#dialogCreateAccessoryConsumption").find("input[id='txtAccessoryGroupCode" + value.accessorygroupcode + "']").val();
		        						//add new consumption by accessory group	       
		                        		$.ajax({
			    							dataType: "json",
			    							type: 'POST',
											data:
												JSON.stringify({
													accessorycode: value.accessorycode,
													factorycode: factorycode
												}),
			    							contentType: "application/json",
			    							url: getAbsolutePath() + "accessoryconsumption/isExist/",
			    							success: function(data){
			    								if(data.isExisted == false){							
			    									//convert object to JSON format
			    									$.ajax({
			    										dataType: "json",
			    										type: 'POST',
			    										data:
			    											JSON.stringify({
			    												factorycode: factorycode,
			    												accessorycode: value.accessorycode,
			    												consumption: consumptionByAccessoryGroup
			    											}),
			    										contentType: "application/json",
			    										url: getAbsolutePath() + "accessoryconsumption/add",
			    										success: function(data){
			    											if(data.addStatus == true){	
			    												consumptionByAccessoryCode = $("#dialogCreateAccessoryConsumption").find("input[id='txtAccessoryCode" + value.accessorycode + "']").val();
			    												//edit consumption by accesory code
			    												$.ajax({
			    													dataType: "json",
			    													type: 'POST',
			    													data:
			    														JSON.stringify({
			    															accessorycode: value.accessorycode,
			    															factorycode: factorycode,
			    															consumption: consumptionByAccessoryCode
			    														}),
			    													contentType: "application/json",
			    													url: getAbsolutePath() + "accessoryconsumption/edit",
			    													success: function(data){
			    														if(data.editStatus == true){											
			    															//close dialog and clear input	    															
			    		    												$( "#dialogCreateAccessoryConsumption" ).dialog( "close" );	
			    		    												addSuccessMessageDialog();
			    		    												//callMessageDialog("Message", 'Create Accessory Consumption successfully!');
			    		    												
			    														}else if(data.editStatus == false){
			    															addFailedMessageDialog();
			    														}else{
			    															addFailedMessageDialog();
			    														}
			    													},
			    													error: function(){
			    														addFailedMessageDialog();
			    													}
			    												});	    												
			    											}else if(data.addStatus == false){
			    												addFailedMessageDialog();
			    											}else{
			    												addFailedMessageDialog();
			    											}
			    										},
			    										error: function(){
			    											addFailedMessageDialog();
			    										}
			    									});
			    								}
			    								else if(data.isExisted == true){
			    									IsExistedMessageDialog();
			    									return;
			    								}
			    							}
		    							});	
		                            });
//		                        	//reload table
//		                        	loadData();
	                			}
	                        },
	                        error: function(){
	                        	callMessageDialog("Error", "Cannot load accessory to table.");
	                        }
	            		});	       		
		            }},
		            "Cancel": function() {
		                $( "#dialogCreateAccessoryConsumption" ).dialog( "close" );
		            }
		        },
		        hide: {
		            effect: "slide",
		            duration: 300
		        }
		    });
		});
	}
	
	//handle click event for edit + delete button of table
	function action(){
		//-------------ACTION BY DROPDOWNBOX--------------
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    $(".selectOption").val("Options");
		    if(valueSelected=="Delete"){
				var factorycode = $(this).data('factory');
				$("#deleteAccessoryConsumptionDialog").find("#messageContent").text('Are you sure you want to delete wasted percentage of "' + factorycode+'"?');
				//do delete when user choose OK 
				$("#deleteAccessoryConsumptionDialog").dialog({
		    		show:{
						effect:"slide",
						duration: 500
					},
					title: "Delete Wasted Percentage Confirm",
					height: 300,
					width: 400,
					modal: true,
					buttons:{
						"OK": function(){
							$.ajax({
								dataType: "json",
								type: 'GET',
								data: {},
								url: getAbsolutePath() + "accessoryconsumption/list",
								contentType: "application/json",
								success: function(data){	
									$.each(data.list,function(key,value){	
										if(factorycode == value.factorycode)
										{
											$.ajax({
												dataType: "json",
												type: 'POST',
												data:
													JSON.stringify({
														accessorycode: value.accessorycode,
														factorycode: factorycode
													}),
												contentType: "application/json",
												url: getAbsolutePath() + "accessoryconsumption/delete",
												success: function(data){
													deleteSuccessMessageDialog();
													$("#deleteAccessoryConsumptionDialog").dialog("close");
												},
												error: function(){
													deleteFailedMessageDialog();
													$("#deleteAccessoryConsumptionDialog").dialog("close");
												}
											});
										}
									})																	
								},
								error: function(){
									deleteFailedMessageDialog();
								}
							});
						},
						"Cancel": function(){
							$("#deleteAccessoryConsumptionDialog").dialog("close");
						}
					}
				});
		    }
		    
		    if(valueSelected=="Edit"){
				//clear list
	    		$("#listEditAccessoryGroup").dataTable().fnDestroy();
	    		$('#listEditAccessoryGroup tbody').empty();
	    		$("#listEditAccessoryConsumptionDetail").dataTable().fnDestroy();
	    		$('#listEditAccessoryConsumptionDetail tbody').empty();
	    		
				var factorycode = $(this).data('factory');
				var factoryname = $(this).data('factoryname');
				$( "#dialogEditAccessoryConsumption" ).find("#txtEditFactory").val(factorycode);
				$('#txtEditFactory').prop( "disabled", true );
				$( "#dialogEditAccessoryConsumption" ).find("#txtEditFactoryName").val(factoryname);
				$('#txtEditFactoryName').prop( "disabled", true );
				
				//load accessory group to table
				$.ajax({
					dataType: "json",
		            type: 'GET',
		            data:{},
		            url: getAbsolutePath() + "accessorygroup/list",
		            success: function (data) {
		            	$.each(data.list, function( key, value ) {  
							$('<tr>').append(
									$('<td>').text(value.accessorygroupCode),
									$('<td>').html('<input type="number" step="any" min="0" max="100" class="form-control" data-accessorygroupCode="' + value.accessorygroupCode +'" id="txtAccessoryGroupCode' + value.accessorygroupCode +'" value="0" name="txtConsumption">')												
							).appendTo('#listEditAccessoryGroup');
							var tmpaccessorygroupCode = value.accessorygroupCode;
							//event for change consumption value of accessory by group
							$("#dialogEditAccessoryConsumption").find("input[id='txtAccessoryGroupCode" + value.accessorygroupCode + "']").on('input propertychange paste', function (e) {						
								$.ajax({
									dataType: "json",
						            type: 'GET',
						            data:{},
						            url: getAbsolutePath() + "accessory/list",
						            success: function (data) {
						            	$.each(data.list, function( key, value ) {  
						            		//if group code of accessory = groupcode on dropdown, then set equal consumption				      
						            		if(value.accessorygroupcode == tmpaccessorygroupCode){
						            			var tmpConsumption = $("#dialogEditAccessoryConsumption").find("input[id='txtAccessoryGroupCode" + value.accessorygroupcode + "']").val();
						            			$("#dialogEditAccessoryConsumption").find("input[id='txtAccessoryCode" + value.accessorycode + "']").val(tmpConsumption);
						            		}			
						                });
						            },
						            error: function(){
						            	callMessageDialog("Error", "Cannot load accessory to table.");
						            }
								});
							});
		                });
						$('#listEditAccessoryGroup').DataTable( {
						    paging: false
					    });
		            },
		            error: function(){
		            	callMessageDialog("Error", "Cannot load accessory group to table.");
		            }
				});
				
				//load accessory to table
				$.ajax({
					dataType: "json",
		            type: 'GET',
		            data:{},
		            url: getAbsolutePath() + "accessory/list",
		            success: function (data) {
		            	var consumptionByAccessoryCode;
		            	var x;
		            	
		            	//to count because issue on load DataTable
		            	var countEdit=0;
		            	var listLength = data.list.length;
		            	
		            	$.each(data.list, function( key, value ) {      
		        			$.ajax({
		        				dataType: "json",
		        				type: 'POST',
								data:
									JSON.stringify({
										accessorycode: value.accessorycode,
										factorycode: factorycode
									}),
		        				contentType: "application/json",
		        				url: getAbsolutePath() + "accessoryconsumption/detail",
		        				success: function(data){
		        					if(data.status=="ok"){
			        					consumptionByAccessoryCode = data.accessoryconsumption.consumption;
			    						$('<tr>').append(
			    								$('<td>').text(value.accessorycode),
			    								$('<td>').text(value.accessorygroupcode),
			    								$('<td>').html('<input type="number" step="any" min="0" max="100" class="form-control" data-accessoryCode="' + value.accessorycode +'" id="txtAccessoryCode' + value.accessorycode +'" value="' + consumptionByAccessoryCode +'" name="txtConsumption">')												
			    						).appendTo('#listEditAccessoryConsumptionDetail');
			    						countEdit++;
			    						if(countEdit===listLength)
			    						{
				    		        		$("#listEditAccessoryConsumptionDetail").dataTable( {
				    		        		    deferRender: true,
				    		        		    scrollY:     300,
				    							scroller: true,
				    						    paging: false
				    		        	    });
				    		        		
			    						}
		        					}
		        				},
		        				error: function(){
		        					callErrorMessageDialog("Error", 'Get accessory consumption detail failed!');
		        				}
		        			});	      			
//			        		$('#listEditAccessoryConsumptionDetail').DataTable( {
//			        			"pagingType": "full_numbers"
//			        	    });
		                });
	
				        $( "#dialogEditAccessoryConsumption" ).dialog({
				        	title: "Edit Wasted Percentage",
				            show: {
				                effect: "slide",
				                duration: 300
				            },
				            height: 550,
				            width: 800,
				            modal: true,
				            buttons: {
				                "Save": {
				                	text: "Save",
				                	id: "btnEditAccesoryConsumption",
				                	click: function(){ 		
				                		var validateWastedPercentageValue = true;
					            		$.ajax({
					            			dataType: "json",
					                        type: 'GET',
					                        data:{},
					                        url: getAbsolutePath() + "accessory/list",
					                        success: function (data) {
					                			//validate consumption must  >=0 && <100 
					                			$.each(data.list, function( key, value ) {     
					                				
					                				var validateAccConsumptionValue = $("#dialogEditAccessoryConsumption").find("input[id='txtAccessoryCode" + value.accessorycode + "']").val();
					                				if(validateAccConsumptionValue <0 
					                						|| validateAccConsumptionValue >100 
					                						|| validateAccConsumptionValue=="" 
						                					|| validateAccConsumptionValue==null){
					                					callMessageDialog("Error", "Wasted Percentage value must between 0 - 100.");
					                					validateWastedPercentageValue = false;
					                					return; 
					                				}
					                			});
					                			
					                			if (validateWastedPercentageValue == true){
						                        	$.each(data.list, function( key, value ) {
								                		consumptionByAccessoryCode = $("#dialogEditAccessoryConsumption").find("input[id='txtAccessoryCode" + value.accessorycode + "']").val();
														$.ajax({
															dataType: "json",
															type: 'POST',
															data:
																JSON.stringify({
																	accessorycode: value.accessorycode,
																	factorycode: factorycode,
																	consumption: consumptionByAccessoryCode
																}),
															contentType: "application/json",
															url: getAbsolutePath() + "accessoryconsumption/edit",
															success: function(data){
																if(data.editStatus == true){											
																	//close dialog and clear input	    															
																	$( "#dialogEditAccessoryConsumption" ).dialog( "close" );												
																	editSuccessMessageDialog();
																	
																}else if(data.editStatus == false){
																	editFailedMessageDialog();
																}else{
																	editFailedMessageDialog();
																}
															},
															error: function(){
																editFailedMessageDialog();
															}
														});	
						                        	});
					                			}
					                        },
											error: function(){
												editFailedMessageDialog();
											}
										});	 				                      
				                	}
				                },
				                "Cancel": function() {
				                    $( "#dialogEditAccessoryConsumption" ).dialog( "close" );
				                }
				            },
				            hide: {
				                effect: "slide",
				                duration: 300
				            }
				        });
		            },
		            error: function(){
		            	callMessageDialog("Error", "Cannot load accessory to table.");
		            }
				});
		    }
		    if(valueSelected=="ViewDetail"){
				//clear list
	    		$("#listViewDetailAccessoryConsumptionDetail").dataTable().fnDestroy();
	    		$('#listViewDetailAccessoryConsumptionDetail tbody').empty();
	    		
				var factorycode = $(this).data('factory');
				var factoryname = $(this).data('factoryname');
				$( "#dialogViewDetailAccessoryConsumption" ).find("#txtViewDetailFactory").val(factorycode);
				$('#txtViewDetailFactory').prop( "disabled", true );
				$( "#dialogViewDetailAccessoryConsumption" ).find("#txtViewDetailFactoryName").val(factoryname);
				$('#txtViewDetailFactoryName').prop( "disabled", true );
				
				//load accessory to table
				$.ajax({
					dataType: "json",
		            type: 'GET',
		            data:{},
		            url: getAbsolutePath() + "accessory/list",
		            success: function (data) {
		            	
		            	//to count because issue on load DataTable
		            	var countViewDetail=0;
		            	var listLength = data.list.length;
		            	
		            	$.each(data.list, function( key, value ) {      
		        			$.ajax({
		        				dataType: "json",
		        				type: 'POST',
								data:
									JSON.stringify({
										accessorycode: value.accessorycode,
										factorycode: factorycode
									}),
		        				contentType: "application/json",
		        				url: getAbsolutePath() + "accessoryconsumption/detail",
		        				success: function(data){
		        					if(data.status=="ok"){
			    						$('<tr>').append(
			    								$('<td>').text(value.accessorycode),
			    								$('<td>').text(value.accessorygroupcode),
			    								$('<td>').text(data.accessoryconsumption.consumption)												
			    						).appendTo('#listViewDetailAccessoryConsumptionDetail');
			    						countViewDetail++;
			    						if(countViewDetail===listLength)
			    						{
				    		        		$("#listViewDetailAccessoryConsumptionDetail").dataTable( {
				    		        			"pagingType": "full_numbers"
				    		        	    });
			    						}
		        					}
		        				},
		        				error: function(){
		        					callErrorMessageDialog("Error", 'Get accessory consumption detail failed!');
		        				}
		        			});	      			
	
		                });
	
				        $( "#dialogViewDetailAccessoryConsumption" ).dialog({
				        	title: "View Detail Wasted Percentage",
				            show: {
				                effect: "slide",
				                duration: 300
				            },
				            height: 550,
				            width: 800,
				            modal: true,
				            buttons: {
				                "Cancel": function() {
				                    $( "#dialogViewDetailAccessoryConsumption" ).dialog( "close" );
				                }
				            },
				            hide: {
				                effect: "slide",
				                duration: 300
				            }
				        });
				        
	
		            },
		            error: function(){
		            	callMessageDialog("Error", "Cannot load accessory to table.");
		            }
				});
		    }
		});
		
		//-------------ACTION BY BUTTON--------------
//		//When click delete
//		$('.btnDelete').on('click', function (e) {
//			var factorycode = $(this).data('factory');
//			//do delete when user choose OK 
//			$.ajax({
//				dataType: "json",
//				type: 'GET',
//				data: {},
//				url: getAbsolutePath() + "accessoryconsumption/list",
//				contentType: "application/json",
//				success: function(data){	
//					$.each(data.list,function(key,value){	
//						if(factorycode == value.factorycode)
//						{
//							$.ajax({
//								dataType: "json",
//								type: 'POST',
//								data:{},
//								contentType: "application/json",
//								url: getAbsolutePath() + "accessoryconsumption/delete/" + factorycode + "/" + value.accessorycode,
//								success: function(data){
//									deleteSuccessMessageDialog();
//								},
//								error: function(){
//									deleteFailedMessageDialog();
//								}
//							});
//						}
//					})
//					
//					
//				},
//				error: function(){
//					deleteFailedMessageDialog();
//				}
//				});
//		});
//		
//		//When click edit
//		$('.btnEdit').on('click', function (e) {
//			//clear list
//    		$("#listEditAccessoryGroup").dataTable().fnDestroy();
//    		$('#listEditAccessoryGroup tbody').empty();
//    		$("#listEditAccessoryConsumptionDetail").dataTable().fnDestroy();
//    		$('#listEditAccessoryConsumptionDetail tbody').empty();
//    		
//			var factorycode = $(this).data('factory');
//			var factoryname = $(this).data('factoryname');
//			$( "#dialogEditAccessoryConsumption" ).find("#txtEditFactory").val(factorycode);
//			$('#txtEditFactory').prop( "disabled", true );
//			$( "#dialogEditAccessoryConsumption" ).find("#txtEditFactoryName").val(factoryname);
//			$('#txtEditFactoryName').prop( "disabled", true );
//			
//			//load accessory group to table
//			$.ajax({
//				dataType: "json",
//	            type: 'GET',
//	            data:{},
//	            url: getAbsolutePath() + "accessorygroup/list",
//	            success: function (data) {
//	            	$.each(data.list, function( key, value ) {  
//						$('<tr>').append(
//								$('<td>').text(value.accessorygroupCode),
//								$('<td>').html('<input type="number" step="any" min="0" max="100" class="form-control" data-accessorygroupCode="' + value.accessorygroupCode +'" id="txtAccessoryGroupCode' + value.accessorygroupCode +'" value="0" name="txtConsumption">')												
//						).appendTo('#listEditAccessoryGroup');
//						var tmpaccessorygroupCode = value.accessorygroupCode;
//						//event for change consumption value of accessory by group
//						$("#dialogEditAccessoryConsumption").find("#txtAccessoryGroupCode"+value.accessorygroupCode+"").on('input propertychange paste', function (e) {						
//							$.ajax({
//								dataType: "json",
//					            type: 'GET',
//					            data:{},
//					            url: getAbsolutePath() + "accessory/list",
//					            success: function (data) {
//					            	$.each(data.list, function( key, value ) {  
//					            		//if group code of accessory = groupcode on dropdown, then set equal consumption				      
//					            		if(value.accessorygroupcode == tmpaccessorygroupCode){
//					            			var tmpConsumption = $("#dialogEditAccessoryConsumption").find("#txtAccessoryGroupCode"+value.accessorygroupcode+"").val();
//					            			$("#dialogEditAccessoryConsumption").find("#txtAccessoryCode"+value.accessorycode+"").val(tmpConsumption);
//					            		}			
//					                });
//					            },
//					            error: function(){
//					            	alert("Cannot load accessory to table");
//					            }
//							});
//						});
//	                });
//					$('#listEditAccessoryGroup').DataTable( {
//						"pagingType": "full_numbers"
//				    });
//	            },
//	            error: function(){
//	            	alert("Cannot load accessory group name to table");
//	            }
//			});
//			
//			//load accessory to table
//			$.ajax({
//				dataType: "json",
//	            type: 'GET',
//	            data:{},
//	            url: getAbsolutePath() + "accessory/list",
//	            success: function (data) {
//	            	var consumptionByAccessoryCode;
//	            	var x;
//	            	
//	            	//to count because issue on load DataTable
//	            	var countEdit=0;
//	            	var listLength = data.list.length;
//	            	
//	            	$.each(data.list, function( key, value ) {      
//	        			$.ajax({
//	        				dataType: "json",
//	        				type: 'GET',
//	        				data:{},
//	        				contentType: "application/json",
//	        				url: getAbsolutePath() + "accessoryconsumption/detail/" + factorycode + "/" + value.accessorycode,
//	        				success: function(data){
//	        					if(data.status=="ok"){
//		        					consumptionByAccessoryCode = data.accessoryconsumption.consumption;
//		    						$('<tr>').append(
//		    								$('<td>').text(value.accessorycode),
//		    								$('<td>').text(value.accessorygroupcode),
//		    								$('<td>').html('<input type="number" step="any" min="0" max="100" class="form-control" data-accessoryCode="' + value.accessorycode +'" id="txtAccessoryCode' + value.accessorycode +'" value="' + consumptionByAccessoryCode +'" name="txtConsumption">')												
//		    						).appendTo('#listEditAccessoryConsumptionDetail');
//		    						countEdit++;
//		    						if(countEdit===listLength)
//		    						{
//			    		        		$("#listEditAccessoryConsumptionDetail").dataTable( {
//			    		        			"pagingType": "full_numbers"
//			    		        	    });
//			    		        		
//		    						}
//	        					}
//	        				},
//	        				error: function(){
//	        					callErrorMessageDialog("Error", 'Get accessory consumption detail failed!');
//	        				}
//	        			});	      			
////		        		$('#listEditAccessoryConsumptionDetail').DataTable( {
////		        			"pagingType": "full_numbers"
////		        	    });
//	                });
//
//			        $( "#dialogEditAccessoryConsumption" ).dialog({
//			        	title: "Edit Wasted Percentage",
//			            show: {
//			                effect: "slide",
//			                duration: 300
//			            },
//			            height: 550,
//			            width: 800,
//			            modal: true,
//			            buttons: {
//			                "Save": {
//			                	text: "Save",
//			                	id: "btnEditAccesoryConsumption",
//			                	click: function(){ 			
//				            		$.ajax({
//				            			dataType: "json",
//				                        type: 'GET',
//				                        data:{},
//				                        url: getAbsolutePath() + "accessory/list",
//				                        success: function (data) {
//				                        	$.each(data.list, function( key, value ) {
//						                		consumptionByAccessoryCode = $("#dialogEditAccessoryConsumption").find("#txtAccessoryCode"+value.accessorycode+"").val();
//												$.ajax({
//													dataType: "json",
//													type: 'POST',
//													data:
//														JSON.stringify({
//															accessorycode: value.accessorycode,
//															factorycode: factorycode,
//															consumption: consumptionByAccessoryCode
//														}),
//													contentType: "application/json",
//													url: getAbsolutePath() + "accessoryconsumption/edit",
//													success: function(data){
//														if(data.editStatus == true){											
//															//close dialog and clear input	    															
//															$( "#dialogEditAccessoryConsumption" ).dialog( "close" );												
//															editSuccessMessageDialog();
//															
//														}else if(data.editStatus == false){
//															editFailedMessageDialog();
//														}else{
//															editFailedMessageDialog();
//														}
//													},
//													error: function(){
//														editFailedMessageDialog();
//													}
//												});	
//				                        	});
//				                        },
//										error: function(){
//											editFailedMessageDialog();
//										}
//									});	 				                      
//			                	}
//			                },
//			                "Cancel": function() {
//			                    $( "#dialogEditAccessoryConsumption" ).dialog( "close" );
//			                }
//			            },
//			            hide: {
//			                effect: "slide",
//			                duration: 300
//			            }
//			        });
//	            },
//	            error: function(){
//	            	alert("Cannot load accessory to table");
//	            }
//			});
//		});
//		
//		//When click View Detail
//		$('.btnViewDetail').on('click', function (e) {
//			//clear list
//    		$("#listViewDetailAccessoryConsumptionDetail").dataTable().fnDestroy();
//    		$('#listViewDetailAccessoryConsumptionDetail tbody').empty();
//    		
//			var factorycode = $(this).data('factory');
//			var factoryname = $(this).data('factoryname');
//			$( "#dialogViewDetailAccessoryConsumption" ).find("#txtViewDetailFactory").val(factorycode);
//			$('#txtViewDetailFactory').prop( "disabled", true );
//			$( "#dialogViewDetailAccessoryConsumption" ).find("#txtViewDetailFactoryName").val(factoryname);
//			$('#txtViewDetailFactoryName').prop( "disabled", true );
//			
//			//load accessory to table
//			$.ajax({
//				dataType: "json",
//	            type: 'GET',
//	            data:{},
//	            url: getAbsolutePath() + "accessory/list",
//	            success: function (data) {
//	            	
//	            	//to count because issue on load DataTable
//	            	var countViewDetail=0;
//	            	var listLength = data.list.length;
//	            	
//	            	$.each(data.list, function( key, value ) {      
//	        			$.ajax({
//	        				dataType: "json",
//	        				type: 'GET',
//	        				data:{},
//	        				contentType: "application/json",
//	        				url: getAbsolutePath() + "accessoryconsumption/detail/" + factorycode + "/" + value.accessorycode,
//	        				success: function(data){
//	        					if(data.status=="ok"){
//		    						$('<tr>').append(
//		    								$('<td>').text(value.accessorycode),
//		    								$('<td>').text(value.accessorygroupcode),
//		    								$('<td>').text(data.accessoryconsumption.consumption)												
//		    						).appendTo('#listViewDetailAccessoryConsumptionDetail');
//		    						countViewDetail++;
//		    						if(countViewDetail===listLength)
//		    						{
//			    		        		$("#listViewDetailAccessoryConsumptionDetail").dataTable( {
//			    		        			"pagingType": "full_numbers"
//			    		        	    });
//		    						}
//	        					}
//	        				},
//	        				error: function(){
//	        					callErrorMessageDialog("Error", 'Get accessory consumption detail failed!');
//	        				}
//	        			});	      			
//
//	                });
//
//			        $( "#dialogViewDetailAccessoryConsumption" ).dialog({
//			        	title: "View Detail Wasted Percentage",
//			            show: {
//			                effect: "slide",
//			                duration: 300
//			            },
//			            height: 550,
//			            width: 800,
//			            modal: true,
//			            buttons: {
//			                "Cancel": function() {
//			                    $( "#dialogViewDetailAccessoryConsumption" ).dialog( "close" );
//			                }
//			            },
//			            hide: {
//			                effect: "slide",
//			                duration: 300
//			            }
//			        });
//			        
//
//	            },
//	            error: function(){
//	            	alert("Cannot load accessory to table");
//	            }
//			});
//		});
	}

	/**
	 * This function is used to call and set params for message dialog.
	 */
	function callMessageDialog(title, messageContent){
		$("#messageDialog").find("#messageContent").text(messageContent);
		$("#messageDialog").dialog({
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 200,
			width: 350,
			hide: {
				effect: "slide",
				duration: 300
			},
			buttons:{
				"OK": function(){
					$(this).dialog("close");
					loadData();
					loadFactoryData();
				}
			}
		}).prev(".ui-widget-header").css("color","#333");
	}
	
	function callErrorMessageDialog(title, messageContent){
		$("#messageDialog").find("#messageContent").text(messageContent);
		$("#messageDialog").dialog({
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
			},
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#f5321c");
	}
	
	function addSuccessMessageDialog(){
		var title = $("#SuccessTitle").text();
		$("#AddSuccessDialog").dialog({
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
			},
			buttons:{
				"OK": function(){
					$(this).dialog("close");
					loadData();
					loadFactoryData();
				}
			}
		}).prev(".ui-widget-header").css("color","#333");
	}
	
	function addFailedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#AddFailedDialog").dialog({
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
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
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
			},
			buttons:{
				"OK": function(){
					$(this).dialog("close");
					loadData();
					loadFactoryData();
				}
			}
		}).prev(".ui-widget-header").css("color","#333");
	}
	
	function editFailedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#EditFailedDialog").dialog({
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
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
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
			},
			buttons:{
				"OK": function(){
					$(this).dialog("close");
					loadData();
					loadFactoryData();
				}
			}
		}).prev(".ui-widget-header").css("color","#333");
	}
	
	function deleteFailedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#DeleteFailedDialog").dialog({
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
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
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
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
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
			},
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#f5321c");
	}
	
	btnCreate_Click();
	sltFactoryCode_Change();
	loadFactoryData();
	loadData();
});