$(document).ready(function(){
	
	
	
	var lotNumber="Lot1";
	var pigridCode;
	$("#dialogShowPigrid").find("#btnEdit").on('click',function(){
		$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/pigrid/checkPigrid/" +lotNumber,
			success: function(data){
				if(data.checkPigrid!=0){
				pigridCode=data.checkPigrid;
				$("#dialogShowPigrid").find("txtPigrid").val(data.checkPigrid);
				$.ajax({
					dataType: "json",
					type: 'GET',
					data: {},
					url: "/Chori/pigriddetail/getallbypigrid/"+pigridCode,
					contentType: "application/json",
					success: function(data){
						var i = 1;
						
						
						
//						function loadPigriddetail(){
							$("#dialogShowPigrid2").dialog({
								show:{
									effect:"blind",
									duration: 500
								},
								height: 600,
								width: 900,
								title: "Pigrid Detail",
								modal :true,
								buttons:{
									"Cancel": function(){
										$("#dialogShowPigrid2").dialog("close");
									}
								}
						});

							$("#dialogShowPigrid2").find('#listPigriddetail').dataTable().fnDestroy();
							$('#listPigriddetail tbody').empty();
						$.each(data.list,function(key,value){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.pigrid),		
									$('<td>').text(value.sizename),
									$('<td>').text(value.garmentstyle),
									$('<td>').text(value.colorName),
									$('<td>').text(value.pcs),
									$('<td>').text(value.barCode),

									$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.pigriddetail+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option></select>'))
											.appendTo($("#dialogShowPigrid2").find('#listPigriddetail'));
						});
						action();
						
							$("#dialogShowPigrid2").find('#listPigriddetail').DataTable({
							"pagingType": "full_numbers"
					    });
					}
					
					
					,
					error: function(){
						alert("Can not get data!");
					}
					
					
				});
			}
				else{
					$.ajax({
			    		dataType: "json",
						type: 'GET',
						
							contentType: "application/json",
							url:"/Chori/pigrid/add",
							success: function(data){
								
								
								$.ajax({
						    		dataType: "json",
									type: 'POST',
									data:
										JSON.stringify({
											lotnumber:lotNumber,
											pigridcode: data.pigridcode,
											
										}),
									contentType: "application/json",
									url:  "/Chori/pigrid/editPi",
									success: function(data){
									//	alert('edit success');
										console.log(data);

										$("#dialogShowPigrid2").find('#listPigriddetail').dataTable().fnDestroy();
										$('#listPigriddetail tbody').empty();
										loadData();
								
										$("#dialogShowPigrid2").dialog({
											show:{
												effect:"blind",
												duration: 500
											},
											height: 800,
											width: 900,
											title: "Pigrid Detail",
											modal :true,
											buttons:{
												"Cancel": function(){
													$("#dialogShowPigrid2").dialog("close");
												}
											}
									});
									},
									error: function(){
										console.log(data);
									}
						    	});
								
							}
					
					});
					
				}
			}
			
				
			
			
		});
		
	});
	function action(){
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
			var pigriddetail= $(this).data('id');
		    $(".selectOption").val("Options");
		// button edit pigriddetail
		    if(valueSelected=="Edit"){
			//	var unitCode= $(this).data('id');

		//	alert(unitCode);
		$.ajax({
	    		dataType: "json",
				type: 'POST',
				data:
					JSON.stringify({
						pigriddetail: pigriddetail,
						
					}),
				contentType: "application/json",
				url: "/Chori/pigriddetail/detail",
				success: function(data){
					if(data.PiGridDetail=="ok"){
						$("#dialogEditPigriddetail").find("#txtGarmentstyle").val(data.currentPigriddetail.garmentstyle);
						$("#dialogEditPigriddetail").find("#txtColor").val(data.currentPigriddetail.color);
						$("#dialogEditPigriddetail").find("#txtSize").val(data.currentPigriddetail.sizecode);
						$("#dialogEditPigriddetail").find("#txtPcs").val(data.currentPigriddetail.pcs);
						$("#dialogEditPigriddetail").find("#txtBarcode").val(data.currentPigriddetail.barCode);
					}else{
						alert('This alert should never show!');
					}
				},
				error: function(){
					alert('Cant get pi grid detail!'+pigriddetail);
				}
	    	});
			
			//show dialog edit pigrid detail
			$("#dialogEditPigriddetail").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Edit Pigrid Detail",
				height: 600,
				width: 400,
				buttons:{
					"Save": {
						text:"Save",
						id:"btnSave",
						click: function(){
						var garmentstyle=  $('#dialogEditPigriddetail').find('#txtGarmentstyle').val();
						var color= $("#dialogEditPigriddetail").find("#txtColor").val();
						var size= $("#dialogEditPigriddetail").find("#txtSize").val();
						var pcs= $("#dialogEditPigriddetail").find("#txtPcs").val();
						var barcode= $("#dialogEditPigriddetail").find("#txtBarcode").val();
						$.ajax({
				    		dataType: "json",
							type: 'POST',
							data:
								JSON.stringify({
									garmentstyle: garmentstyle,
									color: color,
									sizecode: size,
									pcs: pcs,
									barCode: barcode,
									pigrid:pigridCode,
									pigriddetail: pigriddetail,
									
								}),
							contentType: "application/json",
							url:  "/Chori/pigriddetail/edit",
							success: function(data){
							//	alert('edit success');
								console.log(data);
								
								callMessageDialog("Message", "Edit pi grid detail successfully!");
								
								$("#dialogShowPigrid2").find('#listPigriddetail').dataTable().fnDestroy();
								$('#listPigriddetail tbody').empty();
								loadData();
							$("#dialogEditPigriddetail").dialog("close");
							},
							error: function(){
								callMessageDialog("Message", "Edit pi grid detail unsuccessfully!");
							}
				    	});
						},
					},
					"Cancel": function(){
						$("#dialogEditPigriddetail").dialog("close");
					}
				}
			});
		    }
		    if(valueSelected=="Delete"){
				$("#dialogDeletePigriddetail").find("#messageContent").text('Are you sure you want to delete pi grid detail "' +pigriddetail+'"?');

		//	var unitCode= $(this).data('id');
			$("#dialogDeletePigriddetail").dialog({
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
							data:JSON.stringify({
									pigriddetail: pigriddetail,
								}),
							contentType: "application/json",
							url:"/Chori/pigriddetail/delete",
							success: function(data){
								//console.log(data);
								callMessageDialog("Message", "Delete pi grid detail successfully!");

								$("#dialogShowPigrid2").find('#listPigriddetail').dataTable().fnDestroy();
								$('#listPigriddetail tbody').empty();
								$("#dialogDeletePigriddetail").dialog("close");
								
								
								loadData();
								
							},
							error: function(){
								callMessageDialog("Message", "Can't delete!");
								$("#dialogDeletePigriddetail").dialog("close");
							}
						
				    	});
						
					},
					"No": function(){
						$("#dialogDeletePigriddetail").dialog("close");
					}
				}
			});
		}
		})
	}  
	$('#btnAdd').on('click', function (e) {
		$("#dialogAddPigriddetail").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Add Pigrid Detail",
			height: 600,
			width: 400,
			buttons:{
				"Save": {
					text:"Save",
					id:"btnSave",
					click: function(){
					var garmentstyle=  $('#dialogAddPigriddetail').find('#txtGarmentstyle').val();
					var color= $("#dialogAddPigriddetail").find("#txtColor").val();
					var size= $("#dialogAddPigriddetail").find("#txtSize").val();
				
						var pcs =$("#dialogAddPigriddetail").find("#txtPcs").val();
					var barcode= $("#dialogAddPigriddetail").find("#txtBarcode").val();
					$.ajax({
			    		dataType: "json",
						type: 'POST',
						data:
							JSON.stringify({
								garmentstyle: garmentstyle,
								color: color,
								sizecode: size,
								pcs: pcs,
								barCode: barcode,
								pigrid:pigridCode,
								
							}),
						contentType: "application/json",
						url:  "/Chori/pigriddetail/add",
						success: function(data){
						//	alert('edit success');
							console.log(data);
							
							callMessageDialog("Message", "Add pigrid detail successfully!");
							
							$("#dialogShowPigrid2").find('#listPigriddetail').dataTable().fnDestroy();
							$('#listPigriddetail tbody').empty();
							loadData();
						$("#dialogAddPigriddetail").dialog("close");
						},
						error: function(){
							callMessageDialog("Message", "Add pigrid detail unsuccessfully!");
						}
					
			    	});
					
				},
				},
				"Cancel": function(){
					$("#dialogAddPigriddetail").dialog("close");
				}
			}
		});
	});

	//ajax load list garment in dropdownbox
	function loadDropDownGarmentStyle(){
    	$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: getAbsolutePath() +  "garmentstyle/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#dialogEditPigriddetail').find('#txtGarmentstyle').append($('<option>', {
                            value: value.garmentstylecode,
                            text: value.garmentstylecode
                        }));
                        $('#dialogAddPigriddetail').find('#txtGarmentstyle').append($('<option>', {
                            value: value.garmentstylecode,
                            text: value.garmentstylecode
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
	
	//ajax load list color in dropdownbox
	function loadDropDownColor(){
    	$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: getAbsolutePath() +  "color/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#dialogEditPigriddetail').find('#txtColor').append($('<option>', {
                            value: value.colorcode,
                            text: value.description
                        }));
                        $('#dialogAddPigriddetail').find('#txtColor').append($('<option>', {
                            value: value.colorcode,
                            text: value.description
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
	//ajax load list size in dropdownbox 
	function loadDropDownSize(){
    	$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: getAbsolutePath() +  "size/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#dialogEditPigriddetail').find('#txtSize').append($('<option>', {
                            value: value.sizecode,
                            text: value.sizename
                        }));
                        $('#dialogAddPigriddetail').find('#txtSize').append($('<option>', {
                            value: value.sizecode,
                            text: value.sizename
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
	
	function loadData(){
		$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/pigrid/checkPigrid/" +lotNumber,
			success: function(data){
				pigridCode=data.checkPigrid;
				$("#dialogShowPigrid").find("txtPigrid").val(data.checkPigrid);
				$.ajax({
					dataType: "json",
					type: 'GET',
					data: {},
					url: "/Chori/pigriddetail/getallbypigrid/"+pigridCode,
					contentType: "application/json",
					success: function(data){
						var i = 1;
						
						if(data.list.length==0){
							alert("Table have no data.");
						}
					else{
						
//						function loadPigriddetail(){
//							$("#dialogShowPigrid2").dialog({
//								show:{
//									effect:"blind",
//									duration: 500
//								},
//								height: 800,
//								width: 900,
//								title: "Pigrid Detail",
//								modal :true,
//								buttons:{
//									"Cancel": function(){
//										$("#dialogShowPigrid2").dialog("close");
//									}
//								}
//						});
						
						
						$("#dialogShowPigrid2").find('#listPigriddetail').dataTable().fnDestroy();
						$('#listPigriddetail tbody').empty();
						$.each(data.list,function(key,value){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.pigrid),		
									$('<td>').text(value.sizename),
									$('<td>').text(value.garmentstyle),
									$('<td>').text(value.colorName),
									$('<td>').text(value.pcs),
									$('<td>').text(value.barCode),

									$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.pigriddetail+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option></select>'))
											.appendTo($("#dialogShowPigrid2").find('#listPigriddetail'));
						});
						action();
						
							$("#dialogShowPigrid2").find('#listPigriddetail').DataTable({
							"pagingType": "full_numbers"
					    });
					}
					
					}
					,
					error: function(){
						alert("Can not get data!");
					}
					
					
				});
			}
				
			
			
		});
	};
	$("#dialogAddPigriddetail").find('#txtBarcode').on('input propertychange paste', function (e) {
		var barCode =	$("#dialogAddPigriddetail").find('#txtBarcode').val();
		if(barCode.length>50 ) {
			$("#dialogAddPigriddetail").find('#txtBarcode').css("border-left", "red 5px solid");
			$("#dialogAddPigriddetail").find("#errorTxtBarcode").text("Barcode must be between 1-50 characters");
			$('#btnSave').prop( "disabled", true );
		}
	});
	$("#dialogEditPigriddetail").find('#txtBarcode').on('input propertychange paste', function (e) {
		var barCode =	$("#dialogEditPigriddetail").find('#txtBarcode').val();
		if(barCode.length>50 ) {
			$("#dialogEditPigriddetail").find('#txtBarcode').css("border-left", "red 5px solid");
			$("#dialogEditPigriddetail").find("#errorTxtBarcode").text("Barcode must be between 1-50 characters");
			$('#btnSave').prop( "disabled", true );
		}
	});
	
	$("#dialogEditPigriddetail").on('keydown', '#txtPcs',
			function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
		||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
		||35<=e.keyCode&&40>=e.keyCode
		||(e.shiftKey||48>e.keyCode||57<e.keyCode)
		&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
	
	
	$("#dialogAddPigriddetail").on('keydown', '#txtPcs' ,
			function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
		||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
		||35<=e.keyCode&&40>=e.keyCode
		||(e.shiftKey||48>e.keyCode||57<e.keyCode)
		&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
	
	$(".integer").keypress(function(e) {
	    // between 0 and 9
	    if (e.which < 48 || e.which > 57) {
	        showAdvice(this, "Integer values only");
	        return(false);  // stop processing
	    }
	});
	function showAdvice(obj, msg) {
	    $("#singleAdvice").stop(true, false).remove();  // remove any prev msg
	    $('<span id="singleAdvice" class="advice"  style="color: red;">' + msg + '</span>').insertAfter(obj);
	    $("#singleAdvice").delay(4000).fadeOut(1500);  // show for 4 seconds, then fade out
	}
//	loadData();
	loadDropDownGarmentStyle();
	loadDropDownColor();
	loadDropDownSize();
	
});