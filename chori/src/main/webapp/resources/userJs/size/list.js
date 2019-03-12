$(document).ready(function(){	
	/**
	 * -----------------START: Function for List Size---------------------
	 */
	//load customer to dropdownbox
	function loadCustomerData(){
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() + "customer/list",
            success: function (data) {
            	$.each(data.list, function( key, value ) {
            		if(value.status == "Active")
            		{
	                    $("#sltCustomer").append($('<option>', {       	
	                        value: value.customercode,
	                        text: value.shortname
	                    }));
            		}
                });
            },
            error: function(){
            	
            }
		});
	}
	
	//load garment kind to dropdownbox
	function loadGarmentKindData(){
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() +  "garmentkind/list",
            success: function (data) {
            	$.each(data.list, function( key, value ) {
                    $("#sltGarmentKind").append($('<option>', {       	
                        value: value.garmentkindcode,
                        text: value.garmentkindcode
                    }));

                });
            },
            error: function(){
            	
            }
		});
	}
	function loadList()
	{
		var optionSelected = $("#sltCustomer").find("option:selected");
		var valueSelected  = optionSelected.val();
		if(valueSelected !== "")
		{
			var i= 1;
			$.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url:  getAbsolutePath() +  "size/list",
				contentType: "application/json",
				success: function(data){
					$("#listSizeCover").html('');
					//create list assign table
					$("#listSizeCover").append('<table class="table table-striped table-bordered display responsive"'+
								'id="listSize">'+
								'<thead>'+
									'<tr>'+
										//'<th>'+ $("#lblno").text() +'</th>' +
										'<th>'+ $("#lblgarmentKind").text() +'</th>' +
										'<th>'+ $("#lbltype").text() +'</th>' +
										'<th>'+ $("#lblsize").text() +'</th>' +	
										'<th>'+ $("#lblaction").text() +'</th>' +
									'</tr>'+
								'</thead>'+
							'</table>');
					if(data.list.length==0){
						//alert("Table have no data.");
					}
					else{
					$.each(data.list,function(key,value){
						if(valueSelected==value.customer) {
								$('<tr>').append(
										//$('<td>').text(i++),
										$('<td>').text(value.garmentkind),
										$('<td>').text(value.type),
										$('<td>').text(value.sizename),
//										$('<td>').html('<button class="btn btn-info btnEdit" data-sizecode="' + value.sizecode + '">Edit</button>'
//												+ '<button class="btn btn-danger btnDelete" data-sizecode="' + value.sizecode + '">Delete</button>')											
										$('<td>').html('<select class="selectpicker selectOption" data-sizecode="'+value.sizecode+'">'
												+'<option value="Options" disabled selected>Options</option>'
												+'<option value="Edit">Edit</option>'
												+'<option value="Delete">Delete</option></select>')
								).appendTo('#listSize');
							
						}			
					});
//					DeleteOnList_Click();
//					EditOnList_Click();
					action();
					}				
					$('#listSize').DataTable( {
						"sPaginationType": "full_numbers",
						paging: true,
						rowsGroup: [0,1]
				    } );
				},
				error: function(){
					CanNotGetDataDialogMessageDialog();
				}
			});
		}
	}
	//event when choose customer on dropdownbox
	function sltCustomerCode_Change(){
		$('#sltGarmentKind').prop( "disabled", true );
		$('#sltCustomer').on('change', function (e) {
			//enable Choose Garment Style
			$('#sltGarmentKind').prop( "disabled", false );
			$("#sltGarmentKind").val("All");
			var optionSelected = $(this).find("option:selected");
			var valueSelected  = optionSelected.val();
			var i =1;
			$.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url: getAbsolutePath() + "size/list",
				contentType: "application/json",
				success: function(data){
					$("#listSizeCover").html('');
					//create list assign table
					$("#listSizeCover").append('<table class="table table-striped table-bordered display responsive"'+
								'id="listSize">'+
								'<thead>'+
									'<tr>'+
										//'<th>'+ $("#lblno").text() +'</th>' +
										'<th>'+ $("#lblgarmentKind").text() +'</th>' +
										'<th>'+ $("#lbltype").text() +'</th>' +
										'<th>'+ $("#lblsize").text() +'</th>' +	
										'<th>'+ $("#lblaction").text() +'</th>' +
									'</tr>'+
								'</thead>'+
							'</table>');
					if(data.list.length==0){
						//alert("Table have no data.");
					}
					else{
					$.each(data.list,function(key,value){
						if(valueSelected==value.customer) {
								$('<tr>').append(
										//$('<td>').text(i++),
										$('<td>').text(value.garmentkind),
										$('<td>').text(value.type),
										$('<td>').text(value.sizename),
//										$('<td>').html('<button class="btn btn-info btnEdit" data-sizecode="' + value.sizecode + '">Edit</button>'
//												+ '<button class="btn btn-danger btnDelete" data-sizecode="' + value.sizecode + '">Delete</button>')											
										$('<td>').html('<select class="selectpicker selectOption" data-sizecode="'+value.sizecode+'">'
												+'<option value="Options" disabled selected>Options</option>'
												+'<option value="Edit">Edit</option>'
												+'<option value="Delete">Delete</option></select>')
								).appendTo('#listSize');
							
						}			
					});
//					DeleteOnList_Click();
//					EditOnList_Click();
					action();
					}				
					$('#listSize').DataTable( {
						"sPaginationType": "full_numbers",
						paging: true,
						rowsGroup: [0,1]
				    } );
				},
				error: function(){
					CanNotGetDataDialogMessageDialog();
				}
			});
		});
	};
	
	//event when choose garment kind on dropdownbox
	function sltGarmentKind_Change(){
		//enable Choose Garment kind
		$('#sltGarmentKind').prop( "disabled", false );
		$('#sltGarmentKind').on('change', function (e) {
			var garKindOptionSelected = $("#sltGarmentKind").find("option:selected");
			var garKindValueSelected  = garKindOptionSelected.val();
			
			var customerOptionSelected = $("#sltCustomer").find("option:selected");
			var customerValueSelected  = customerOptionSelected.val();
			
			//if choose garment kind all
			if(garKindValueSelected=="All"){
				var i =1;
				$.ajax({
					dataType: "json",
					type: 'GET',
					data: {},
					url: getAbsolutePath() + "size/list",
					contentType: "application/json",
					success: function(data){
						$("#listSizeCover").html('');
						//create list assign table
						$("#listSizeCover").append('<table class="table table-striped table-bordered display responsive"'+
									'id="listSize">'+
									'<thead>'+
										'<tr>'+
											//'<th>'+ $("#lblno").text() +'</th>' +
											'<th>'+ $("#lblgarmentKind").text() +'</th>' +
											'<th>'+ $("#lbltype").text() +'</th>' +
											'<th>'+ $("#lblsize").text() +'</th>' +	
											'<th>'+ $("#lblaction").text() +'</th>' +
										'</tr>'+
									'</thead>'+
								'</table>');
						if(data.list.length==0){
							//alert("Table have no data.");
						}
						else{
						$.each(data.list,function(key,value){
							if((customerValueSelected == value.customer )) {
									$('<tr>').append(
											//$('<td>').text(i++),
											$('<td>').text(value.garmentkind),
											$('<td>').text(value.type),
											$('<td>').text(value.sizename),
//											$('<td>').html('<button class="btn btn-info btnEdit" data-sizecode="' + value.sizecode + '">Edit</button>'
//													+ '<button class="btn btn-danger btnDelete" data-sizecode="' + value.sizecode + '">Delete</button>')											
											$('<td>').html('<select class="selectpicker selectOption" data-sizecode="'+value.sizecode+'">'
													+'<option value="Options" disabled selected>Options</option>'
													+'<option value="Edit">Edit</option>'
													+'<option value="Delete">Delete</option></select>')
									).appendTo('#listSize');
								
							}			
						});
//						DeleteOnList_Click();
//						EditOnList_Click();
						action();
						}				
						$('#listSize').DataTable( {
							"sPaginationType": "full_numbers",
							paging: true,
							rowsGroup: [0,1]
					    } );
					},
					error: function(){
						alert("Can not get data!");
					}
				});
			}
			//if garment kind not all
			else{
				var i =1;
				$.ajax({
					dataType: "json",
					type: 'GET',
					data: {},
					url: getAbsolutePath() + "size/list",
					contentType: "application/json",
					success: function(data){
						$("#listSizeCover").html('');
						//create list assign table
						$("#listSizeCover").append('<table class="table table-striped table-bordered display responsive"'+
									'id="listSize">'+
									'<thead>'+
										'<tr>'+
											//'<th>'+ $("#lblno").text() +'</th>' +
											'<th>'+ $("#lblgarmentKind").text() +'</th>' +
											'<th>'+ $("#lbltype").text() +'</th>' +
											'<th>'+ $("#lblsize").text() +'</th>' +	
											'<th>'+ $("#lblaction").text() +'</th>' +
										'</tr>'+
									'</thead>'+
								'</table>');
						if(data.list.length==0){
							//alert("Table have no data.");
						}
						else{
						$.each(data.list,function(key,value){
							if((customerValueSelected == value.customer )&& (garKindValueSelected == value.garmentkind)) {
									$('<tr>').append(
											//$('<td>').text(i++),
											$('<td>').text(value.garmentkind),
											$('<td>').text(value.type),
											$('<td>').text(value.sizename),
//											$('<td>').html('<button class="btn btn-info btnEdit" data-sizecode="' + value.sizecode + '">Edit</button>'
//													+ '<button class="btn btn-danger btnDelete" data-sizecode="' + value.sizecode + '">Delete</button>')	
											$('<td>').html('<select class="selectpicker selectOption" data-sizecode="'+value.sizecode+'">'
													+'<option value="Options" disabled selected>Options</option>'
													+'<option value="Edit">Edit</option>'
													+'<option value="Delete">Delete</option></select>')
									).appendTo('#listSize');
								
							}			
						});
//						DeleteOnList_Click();
//						EditOnList_Click();
						action();
						}				
						$('#listSize').DataTable( {
							"sPaginationType": "full_numbers",
							paging: true,
							rowsGroup: [0,1]
					    } );
					},
					error: function(){
						CanNotGetDataDialogMessageDialog();
					}
				});
			}
				
		

		});
	};
	
	//fuction action when choose dropdown box
	function action(){
		//edit by dropdownbox
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    $(".selectOption").val("Options");
		    if(valueSelected=="Delete"){
				var sizecode = $(this).data('sizecode');
				//do delete when user choose OK 
				$.ajax({
					dataType: "json",
					type: 'POST',
					data:{},
					contentType: "application/json",
					url: getAbsolutePath() +  "size/delete/" + sizecode,
					success: function(data){
						if(data.status=="ok"){
							if(data.deleteStatus== true){
								$("#listSize").dataTable().fnDestroy();
								$('#listSize tbody').empty();
								loadList();	
								//$("#sltCustomer").empty();
								//loadCustomerData();
								deleteSuccessMessageDialog();
								
							}else if(data.deleteStatus== false){
								deleteFailedMessageDialog();
							}else{
								deleteFailedMessageDialog();
							}
						}else{
							deleteFailedMessageDialog();
						}
					},
					error: function(){
						deleteFailedMessageDialog();
					}
				});
		    }
		    
		    if(valueSelected=="Edit"){
				loadTypeForEditSize()
				loadCustomerForEditSize();
				loadGarmentKindForEditSize();
				var sizecode = $(this).data('sizecode');
				$.ajax({
					dataType: "json",
					type: 'GET',
					data:{},
					contentType: "application/json",
					url: getAbsolutePath() +  "size/detail/" + sizecode,
					success: function(data){
						//if load detail success, then open edit dialog
						if(data.status=="ok"){
							//load data to dialog
							var sltEditCustomerValue = data.size.customer;
							$("#sltEditCustomer option").each(function() { this.selected = (this.text == sltEditCustomerValue); });
							$('#sltEditCustomer').prop( "disabled", true );
							var sltEditGarmentKindValue = data.size.garmentkind;
							$("#sltEditGarmentKind option").each(function() { this.selected = (this.text == sltEditGarmentKindValue); });
							$('#sltEditGarmentKind').prop( "disabled", true );
							var sltEditTypeValue = data.size.type;
							$("#sltEditType option").each(function() { this.selected = (this.text == sltEditTypeValue); });
							$('#sltEditType').prop( "disabled", true );
							$("#dialogEditSize").find("#txtEditSize").val(data.size.sizename);
							
							//Check size isexisted
					        $( "#dialogEditSize" ).dialog({
					        	title: "Edit Size",
					            show: {
					                effect: "slide",
					                duration: 300
					            },
					            height: 400,
					            width: 420,
					            modal: true,
					            buttons: {
					                "Save":{
					                	text: "Save",
					                	id: "btnEditSize",
					                	click: function(){
											//check size is existed
											var customerOptionSelected = $("#sltEditCustomer").find("option:selected");
											var customer  = customerOptionSelected.val();
											var garmentKindOptionSelected = $("#sltEditGarmentKind").find("option:selected");
											var garmentkind  = garmentKindOptionSelected.val();
											var typeOptionSelected = $("#sltEditType").find("option:selected");
											var type  = typeOptionSelected.val();
											var sizename =  $("#dialogEditSize").find("#txtEditSize").val();
																					
											//edit size
											$.ajax({
												dataType: "json",
												type: 'POST',
												data:
													JSON.stringify({
														customer: customer,
														garmentkind: garmentkind,
														type: type,
														sizename: sizename
													}),
												contentType: "application/json",
												url: getAbsolutePath() +  "size/isExist/",
												success: function(data){
													if(data.isExisted == false){
									                	if(validateSelectOption_EditSize() == true)
									                	{
															$.ajax({
																dataType: "json",
																type: 'POST',
																data:
																	JSON.stringify({
																		sizecode: sizecode,
																		customer: customer,
																		garmentkind: garmentkind,
																		type: type,
																		sizename: sizename
																	}),
																contentType: "application/json",
																url: getAbsolutePath() +  "size/edit",
																success: function(data){
							
																		if(data.editStatus == true){																					
																			$( "#dialogEditSize" ).dialog( "close" );												
																			//reload table																																		
																			loadList();
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
									                	}
									                	else callErrorMessageDialog("Error", 'Please input all require field!');
													}
													else if(data.isExisted == true){
														IsExistedMessageDialog();
													}
												}
											});	             	
					                	}
					                },
					                "Cancel": function() {
					                    $( "#dialogEditSize" ).dialog( "close" );
					                }
					            },
					            hide: {
					                effect: "slide",
					                duration: 300
					            }
					        });
						}
					},
					error: function(){
						//callErrorMessageDialog("Error", 'Get size detail failed!');
						CanNotGetDataDialogMessageDialog();
					}
				});
		    }
		});
	}
	//handle click event for edit + delete button of table
//	function DeleteOnList_Click(){
//		//delete by button
//		$('.btnDelete').on('click', function (e) {
//			var sizecode = $(this).data('sizecode');
//			//do delete when user choose OK 
//			$.ajax({
//				dataType: "json",
//				type: 'POST',
//				data:{},
//				contentType: "application/json",
//				url: getAbsolutePath() +  "size/delete/" + sizecode,
//				success: function(data){
//					if(data.status=="ok"){
//						if(data.deleteStatus== true){
//							$("#listSize").dataTable().fnDestroy();
//							$('#listSize tbody').empty();
//							loadList();	
////							$("#sltCustomer").empty();
////							loadCustomerData();
//							deleteSuccessMessageDialog();
//							
//						}else if(data.deleteStatus== false){
//							deleteFailedMessageDialog();
//						}else{
//							deleteFailedMessageDialog();
//						}
//					}else{
//						deleteFailedMessageDialog();
//					}
//				},
//				error: function(){
//					deleteFailedMessageDialog();
//				}
//			});
//		});
//	}
	
	/**
	 * -----------------START: Function for Edit New Size---------------------
	 */	
	//Load Customer Options
	function loadCustomerForEditSize()
	{
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() +  "customer/list",
            success: function (data) {
	            	$.each(data.list, function( key, value ) {
	            		if(value.status == "Active")
	            		{
		                    $("#sltEditCustomer").append($('<option>', {       	
		                        value: value.customercode,
		                        text: value.shortname
		                    }));
	            		}
	                });
        		
            },
            error: function(){
            	
            }
		});
	};
	
	//Load GarmentKind Options
	function loadGarmentKindForEditSize()
	{
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() +  "garmentkind/list",
            success: function (data) {
            	$.each(data.list, function( key, value ) {
                    $("#sltEditGarmentKind").append($('<option>', {       	
                        value: value.garmentkindcode,
                        text: value.garmentkindcode
                    }));

                });
            },
            error: function(){
            	
            }
		});
	};
	
	//Load Type Options
	function loadTypeForEditSize()
	{		
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() +  "type/list",
            success: function (data) {
            	$.each(data.list, function( key, value ) {
                    $("#sltEditType").append($('<option>', {       	
                        value: value.typeCode,
                        text: value.typeCode
                    }));

                });
            },
            error: function(){
            	
            }
		});
	};
	
	//When click Edit Size
//	function EditOnList_Click(){
//		//edit by button
//		$('.btnEdit').on('click', function (e) {
//			var sizecode = $(this).data('sizecode');
//			$.ajax({
//				dataType: "json",
//				type: 'GET',
//				data:{},
//				contentType: "application/json",
//				url: getAbsolutePath() +  "size/detail/" + sizecode,
//				success: function(data){
//					//if load detail success, then open edit dialog
//					if(data.status=="ok"){
//						//load data to dialog
//						var sltEditCustomerValue = data.size.customer;
//						$("#sltEditCustomer option").each(function() { this.selected = (this.text == sltEditCustomerValue); });
//						$('#sltEditCustomer').prop( "disabled", true );
//						var sltEditGarmentKindValue = data.size.garmentkind;
//						$("#sltEditGarmentKind option").each(function() { this.selected = (this.text == sltEditGarmentKindValue); });
//						$('#sltEditGarmentKind').prop( "disabled", true );
//						var sltEditTypeValue = data.size.type;
//						$("#sltEditType option").each(function() { this.selected = (this.text == sltEditTypeValue); });
//						$('#sltEditType').prop( "disabled", true );
//						$("#dialogEditSize").find("#txtEditSize").val(data.size.sizename);
//						
//						//Check size isexisted
//				        $( "#dialogEditSize" ).dialog({
//				        	title: "Edit Size",
//				            show: {
//				                effect: "slide",
//				                duration: 300
//				            },
//				            height: 400,
//				            width: 420,
//				            modal: true,
//				            buttons: {
//				                "Save":{
//				                	text: "Save",
//				                	id: "btnEditSize",
//				                	click: function(){
//										//check size is existed
//										var customerOptionSelected = $("#sltEditCustomer").find("option:selected");
//										var customer  = customerOptionSelected.val();
//										var garmentKindOptionSelected = $("#sltEditGarmentKind").find("option:selected");
//										var garmentkind  = garmentKindOptionSelected.val();
//										var typeOptionSelected = $("#sltEditType").find("option:selected");
//										var type  = typeOptionSelected.val();
//										var sizename =  $("#dialogEditSize").find("#txtEditSize").val();
//																				
//										//edit size
//										$.ajax({
//											dataType: "json",
//											type: 'POST',
//											data:
//												JSON.stringify({
//													customer: customer,
//													garmentkind: garmentkind,
//													type: type,
//													sizename: sizename
//												}),
//											contentType: "application/json",
//											url: getAbsolutePath() +  "size/isExist/",
//											success: function(data){
//												if(data.isExisted == false){
//								                	if(validateSelectOption_EditSize() == true)
//								                	{
//														$.ajax({
//															dataType: "json",
//															type: 'POST',
//															data:
//																JSON.stringify({
//																	sizecode: sizecode,
//																	customer: customer,
//																	garmentkind: garmentkind,
//																	type: type,
//																	sizename: sizename
//																}),
//															contentType: "application/json",
//															url: getAbsolutePath() +  "size/edit",
//															success: function(data){
//						
//																	if(data.editStatus == true){																					
//																		$( "#dialogEditSize" ).dialog( "close" );												
//																		//reload table																																		
//																		loadList();
//																		editSuccessMessageDialog();
//																		
//																	}else if(data.editStatus == false){
//																		editFailedMessageDialog();
//																	}else{
//																		editFailedMessageDialog();
//																	}
//											                	
//															},
//															error: function(){
//																editFailedMessageDialog();
//															}
//														});										
//								                	}
//								                	else callErrorMessageDialog("Error", 'Please choose all select field!');
//												}
//												else if(data.isExisted == true){
//													IsExistedMessageDialog();
//												}
//											}
//										});	             	
//				                	}
//				                },
//				                "Cancel": function() {
//				                    $( "#dialogEditSize" ).dialog( "close" );
//				                }
//				            },
//				            hide: {
//				                effect: "slide",
//				                duration: 300
//				            }
//				        });
//					}
//				},
//				error: function(){
//					//callErrorMessageDialog("Error", 'Get size detail failed!');
//					CanNotGetDataDialogMessageDialog();
//				}
//			});
//		});
//	};
	/**
	 * -----------------END: Function for Edit New Size---------------------
	 */
	
	/**
	 * -----------------END: Function for List Size---------------------
	 */
	
	/**
	 * -----------------START: Function for Add New Size---------------------
	 */	
	//Load Customer Options
	function loadCustomerForAddSize()
	{
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() + "customer/list",
            success: function (data) {
            	$.each(data.list, function( key, value ) {
        		if(value.status == "Active")
        		{
                    $("#sltAddCustomer").append($('<option>', {       	
                        value: value.customercode,
                        text: value.shortname
                    }));
        		}
        		});           	
            },
            error: function(){
            	
            }
		});
	};
	
	//Load GarmentKind Options
	function loadGarmentKindForAddSize()
	{
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() +  "garmentkind/list",
            success: function (data) {
            	$.each(data.list, function( key, value ) {
                    $("#sltAddGarmentKind").append($('<option>', {       	
                        value: value.garmentkindcode,
                        text: value.garmentkindcode
                    }));

                });
            },
            error: function(){
            	
            }
		});
	};
	
	//Load Type Options
	function loadTypeForAddSize()
	{		
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() +  "type/list",
            success: function (data) {
            	$.each(data.list, function( key, value ) {
                    $("#sltAddType").append($('<option>', {       	
                        value: value.typeCode,
                        text: value.typeCode
                    }));

                });
            },
            error: function(){
            	
            }
		});
	};
	
	//When click Create New Size
	function btnCreate_Click(){
		loadTypeForAddSize()
		loadCustomerForAddSize();
		loadGarmentKindForAddSize();
		$('#btnCreate').on('click', function (e) {
			//To auto choose customer
			var customerOnViewOptionSelected = $("#sltCustomer").find("option:selected");
			var customerOnViewSelected  = customerOnViewOptionSelected.val();
			var customer;
			if(customerOnViewSelected != -1) {
				$("#sltAddCustomer").val(customerOnViewSelected);
			}
	        $( "#dialogCreateSize" ).dialog({
	        	title: "Add New Size",
	            show: {
	                effect: "slide",
	                duration: 300
	            },
	            height: 450,
	            width: 420,
	            modal: true,
	            buttons: {
	                "Save":{
	                	text: "Save",
	                	id: "btnSaveNewSize",
	                	click: function(){
							//check size is existed
	        				var customerOptionSelected = $("#sltAddCustomer").find("option:selected");
	        				var customer  = customerOptionSelected.val();
							var garmentKindOptionSelected = $("#sltAddGarmentKind").find("option:selected");
							var garmentkind  = garmentKindOptionSelected.val();
							var typeOptionSelected = $("#sltAddType").find("option:selected");
							var type  = typeOptionSelected.val();
							var sizename =  $("#dialogCreateSize").find("#txtAddSize").val().trim();
							$.ajax({
								dataType: "json",
								type: 'POST',
								data:
									JSON.stringify({
										customer: customer,
										garmentkind: garmentkind,
										type: type,
										sizename: sizename
									}),
								contentType: "application/json",
								url: getAbsolutePath() +  "size/isExist/",
								success: function(data){
									if(data.isExisted == false){
					                	if(validateSelectOption_AddSize() == true)
					                	{
											$.ajax({
												dataType: "json",
												type: 'POST',
												data:
													JSON.stringify({
														customer: customer,
														garmentkind: garmentkind,
														type: type,
														sizename: sizename
													}),
												contentType: "application/json",
												url: getAbsolutePath() + "size/add",
												success: function(data){	
														if(data.addStatus == true){																					
															//$( "#dialogCreateSize" ).dialog( "close" );												
															//reload table																																		
															loadList();
//															$("#sltCustomer").empty();
//															loadCustomerData();
															addSuccessMessageDialog();
															
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
					                	else callErrorMessageDialog("Error", 'Please input all require field!');
									}
									else if(data.isExisted == true){
										IsExistedMessageDialog();
									}
								}
							});	             	
	                	}
	                },
	                "Cancel": function() {
	                    $( "#dialogCreateSize" ).dialog( "close" );
	                }
	            },
	            hide: {
	                effect: "slide",
	                duration: 300
	            }
	        });
		});
	};
	/**
	 * -----------------END: Function for Add New Size---------------------
	 */
	
	/**
	 * -----------------START: Function for Duplicate Size---------------------
	 */	
	//Load Customer From
	function loadCustomerFromForDuplicateSize()
	{
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() +  "customer/list",
            success: function (data) {
	            	$.each(data.list, function( key, value ) {
	            		if(value.status == "Active")
	            		{
		                    $("#sltDuplicateFrom").append($('<option>', {       	
		                        value: value.customercode,
		                        text: value.shortname
		                    }));
	            		}
	                });
        		
            },
            error: function(){    
            	CanNotGetDataDialogMessageDialog();
            }
		});
	};
	
	//Load Customer To
	function loadCustomerToForDuplicateSize(customerCodeToIgnore)
	{
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() +  "customer/list",
            success: function (data) {
	            	$.each(data.list, function( key, value ) {
	            		if(value.status == "Active" && customerCodeToIgnore !== value.customercode)
	            		{
		                    $("#sltDuplicateTo").append($('<option>', {       	
		                        value: value.customercode,
		                        text: value.shortname
		                    }));
	            		}
	                });
        		
            },
            error: function(){    
            	CanNotGetDataDialogMessageDialog();
            }
		});
	};
	function loadListDuplicateSizeByCustomer()
	{
		$('#sltDuplicateFrom').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
			var valueSelected  = optionSelected.val();
			$('#sltDuplicateTo').empty();
			loadCustomerToForDuplicateSize(valueSelected);
			var i =1;
			$.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url: getAbsolutePath() +  "size/list",
				contentType: "application/json",
				success: function(data){
					$("#listDuplicateSizeCover").html('');
					//create list assign table
					$("#listDuplicateSizeCover").append('<table class="table table-striped table-bordered display responsive"'+
								'id="listDuplicateSize">'+
								'<thead>'+
									'<tr>'+
										//'<th>'+ $("#lblno").text() +'</th>' +
										'<th>'+ $("#lblgarmentKind").text() +'</th>' +
										'<th>'+ $("#lbltype").text() +'</th>' +
										'<th>'+ $("#lblsize").text() +'</th>' +	
									'</tr>'+
								'</thead>'+
							'</table>');
					if(data.list.length==0){
						//alert("Table have no data.");
					}
					else{
					$.each(data.list,function(key,value){
						if(valueSelected==value.customer) {
								$('<tr>').append(
										//$('<td>').text(i++),
										$('<td>').text(value.garmentkind),
										$('<td>').text(value.type),
										$('<td>').text(value.sizename)										
								).appendTo('#listDuplicateSize');
							
						}			
					});
//					DeleteOnList_Click();
//					EditOnList_Click();
					action();
					}				
					$('#listDuplicateSize').DataTable( {
						"sPaginationType": "full_numbers",
						paging: true,
						rowsGroup: [0,1]
				    } );
				},
				error: function(){
					CanNotGetDataDialogMessageDialog();
				}
			});
		});	
	}
	
	//When click Duplicate Size
	function btnDuplicateSize_Click(){
		loadCustomerFromForDuplicateSize();
		loadListDuplicateSizeByCustomer();

		$('#btnDuplicate').on('click', function (e) {
	        $( "#dialogDuplicateSize" ).dialog({
	        	title: "Copy Size",
	            show: {
	                effect: "slide",
	                duration: 300
	            },
	            height: 550,
	            width: 700,
	            modal: true,
	            buttons: {
	                "Copy":{
	                	text: "Copy",
	                	id: "btnSaveDuplicateSize",
	                	click: function(){     
	                		
	                		var optionSelectedFrom = $('#sltDuplicateFrom').find("option:selected");
	                		var customerCodeFrom  = optionSelectedFrom.val();
	                		var optionSelectedTo = $('#sltDuplicateTo').find("option:selected");
	                		var customerCodeTo  = optionSelectedTo.val();
	                		
							$.ajax({
								dataType: "json",
								type: 'POST',
								data:
									JSON.stringify({
										customerCodeFrom: customerCodeFrom,
										customerCodeTo: customerCodeTo
									}),
								contentType: "application/json",
								url: getAbsolutePath() +  "size/duplicate/",
								success: function(data){
										if(data.duplicateStatus == true){																					
											$( "#dialogDuplicateSize" ).dialog( "close" );												
											//reload table																																		
											loadList();
											$("#sltCustomer").empty();
											loadCustomerData();
											addSuccessMessageDialog();										
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
	                },
	                "Cancel": function() {
	                    $( "#dialogDuplicateSize" ).dialog( "close" );
	                }
	            },
	            hide: {
	                effect: "slide",
	                duration: 300
	            }
	        });
		});
	};
	
	/**
	 * -----------------END: Function for Duplicate Size---------------------
	 */
	
	
	/**
	 * ------------START: Function for call message dialog------------------
	 */
	function callMessageDialog(title, messageContent){
		$("#messageDialog").find("#messageContent").text(messageContent);
		$("#messageDialog").dialog({
			modal: true,
			show:{
				effect:"slide",
				duration: 500
			},
			title: title,
			height: 200,
			width: 350,
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
	
	function callErrorMessageDialog(title, messageContent){
		$("#messageDialog").find("#messageContent").text(messageContent);
		$("#messageDialog").dialog({
			modal: true,
			show:{
				effect:"slide",
				duration: 500
			},
			title: title,
			height: 250,
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
	/**
	 * ------------END: Function for call message dialog------------------
	 */
	/**
	 * ------------START: Function for Validate------------------
	 */
	function validateSelectOption_AddSize()
	{
		var customerOptionSelected = $("#sltAddCustomer").find("option:selected");
		var customer  = customerOptionSelected.val();
		var garmentKindOptionSelected = $("#sltAddGarmentKind").find("option:selected");
		var garmentkind  = garmentKindOptionSelected.val();
		var typeOptionSelected = $("#sltAddType").find("option:selected");
		var type  = typeOptionSelected.val();
		var sizename =  $("#dialogCreateSize").find("#txtAddSize").val().trim();
		if(customer == -1
			|| garmentkind == -1
			|| type == -1
			|| sizename.length < 1
			)
		{
			return false;
		}
		return true;	
	}
	
	function validateSelectOption_EditSize()
	{
		return true;	
	}
	
	$("#dialogCreateSize").find('#txtAddSize').on('input propertychange paste', function (e) {
		var input= $("#dialogCreateSize").find("#txtAddSize").val().trim();
		if(input.length>20 || input.length<1){
			$("#dialogCreateSize").find("#txtAddSize").css("border-left", "red 5px solid");
			$("#dialogCreateSize").find("#errorTxtAddSize").text("Size name must be between 1-20 characters");
			$('#btnSaveNewSize').prop( "disabled", true );
			}
		else {
			$("#dialogCreateSize").find("#txtAddSize").css("border-left", "blue 5px solid");
			$("#dialogCreateSize").find("#errorTxtAddSize").text("");
			$("#btnSaveNewSize").prop( "disabled", false );		
		}
	});
	
	$('#txtEditSize').on('input propertychange paste', function (e) {
		var input= $("#txtEditSize").val();
		if(input.length>20 || input.length<1){
			$("#txtEditSize").css("border-left", "red 5px solid");
			$("#errorTxtEditSize").text("Size name must be between 1-20 characters");
			$('#btnEditSize').prop( "disabled", true );
			}
		else {
			$("#txtEditSize").css("border-left", "blue 5px solid");
			$("#errorTxtEditSize").text("");
			$("#btnEditSize").prop( "disabled", false );		
		}
	});
	
	
	//-------------MESSAGE DIALOG------------
	function addSuccessMessageDialog(){
		var title = $("#SuccessTitle").text();
		$("#AddSuccessDialog").dialog({
			modal: true,
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
		}).prev(".ui-widget-header").css("color","#333");
	}
	
	function addFailedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#AddFailedDialog").dialog({
			modal: true,
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
			modal: true,
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
		}).prev(".ui-widget-header").css("color","#333");
	}
	
	function editFailedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#EditFailedDialog").dialog({
			modal: true,
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
			modal: true,
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
		}).prev(".ui-widget-header").css("color","#333");
	}
	
	function deleteFailedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#DeleteFailedDialog").dialog({
			modal: true,
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
			modal: true,
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
			modal: true,
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
	loadGarmentKindData();
	loadCustomerData();
	sltCustomerCode_Change();
	sltGarmentKind_Change();
	btnDuplicateSize_Click();
	btnCreate_Click();
});
	
