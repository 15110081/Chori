$(document).ready(function(){
	/**
	 * This function is used to load data into table
	 */
	var fabricNoCheckBoxArray= [];
	$('#btnAssignFabricForPi').on('click', function (e){
		$("#fabricAssignmentListDialog").dialog({
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Fabric Assignment List",
			height: 600,
			width: 1100,
			modal: true,
			close: function(){
				
			}
		});
	});
	function loadData(){
		$("#listFabricAssignment").dataTable().fnDestroy();
		$('#listFabricAssignment tbody').empty();
		$("#txtLotNumber").val("Lot1");
		$("#txtCustomerName").val("Asafa");
		$("#txtFactoryName").val("FAC0001");
		$("#txtCustomerCode").val("Asafa");
		$("#txtFactoryCode").val("FAC0001");
		var customerName = $("#txtCustomerName").val();
		var factoryName = $("#txtFactoryName").val();
		var customerCode = $("#txtCustomerCode").val();
		var factoryCode = $("#txtFactoryCode").val();
		var lotNumber = $("#txtLotNumber").val();

		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/fabricassignment/list/" + customerCode +"/" + factoryCode,
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
					alert("Bảng hiện tại chưa có dữ liệu.");
				}
            	var countLoadList=0;
            	var listLength = data.list.length;
				$.each(data.list,function(key,value){	
					var str="";
					if(value.ischori == true) {str= value.fabricsupplier};
//					if(value.customer === customerName && value.factory === factoryName)
//					{
						fabricNoCheckBoxArray.push(value.fabricno);
						
						//check if piassignfabric is existed, to check checkbox
						$.ajax({
							dataType: "json",
							type: 'POST',
							data:
								JSON.stringify({
									lotnumber: lotNumber,
									fabricno: value.fabricno
								}),
							contentType: "application/json",
							url: "/Chori/piassignfabric/isExist",
							success: function(data2){
								//use to mark which is assigned and check/uncheck the checkbox
								var markIsExisted="";
								if(data2.isExisted == true){
									markIsExisted = "checked";
								}
								else{
									markIsExisted = "";
								}
								
								$('<tr>').append(
										$('<td>').html('<input type="checkbox" id="chk'+value.fabricno+'" data-id="'+value.fabricno+'" value="" '+markIsExisted+'>'),
										$('<td>').text(value.fabricno),
										$('<td>').text(value.fabricitem),
										$('<td>').text(value.fabricinvoiceno),
										$('<td>').text(value.fabricsupplier),
										$('<td>').text(str),
										$('<td>').text(value.customer),
										$('<td>').html('<button class="btn btn-primary btnFabricAssignment" data-id="'+value.fabricno+'">Fabric Assignment</button>')
								).appendTo('#listFabricAssignment');
								
								countLoadList++;
	    						if(countLoadList === listLength)
	    						{
	    							action();
	    							$('#listFabricAssignment').DataTable( {
	    							"sPaginationType": "full_numbers",
	    							paging: true,
	    							});		    		        		
	    						}
							},
							error: function(){
								
							}
						});
				});				
			},
			error: function(){
				alert("Can not get data! 12121");
			}
		});
	};
	
	$('#btnSubmit').on('click', function (e) {
		var lotnumber = $("#txtLotNumber").val();
		var fabricno = $("#txtFabricNo").val();
		var fabricnoArrayIsChecked=[]; 
		var fabricnoArrayIsNotChecked =[];
		//to know which checkbox is checked
		for(var i =0;i<fabricNoCheckBoxArray.length;i++)
		{
	        if($("#chk"+fabricNoCheckBoxArray[i]+"").is(":checked")) {
	        	fabricnoArrayIsChecked.push(fabricNoCheckBoxArray[i]);
		    }
	        if(!($("#chk" + fabricNoCheckBoxArray[i]+"").is(":checked"))){
	        	fabricnoArrayIsNotChecked.push(fabricNoCheckBoxArray[i]);
	        }
	    }
		for(var i =0;i<fabricnoArrayIsChecked.length;i++)
		{
			var fabricno = $("#chk"+fabricnoArrayIsChecked[i]+"").data("id");
			$.ajax({
				dataType: "json",
				type: 'POST',
				data:
					JSON.stringify({
						lotnumber: lotnumber,
						fabricno: fabricno,					
					}),
				contentType: "application/json",
				url: "/Chori/piassignfabric/assign",
				success: function(data){
					if(data.addStatus == true){																					
						//callMessageDialog("Message", 'Assign fabric to PI Successfully!');
						
					}else if(data.addStatus == false){
						alert('This alert maybe never show! (4)');
					}else{
						alert('This alert maybe never show! (5)');
					}
				},
				error: function(){
					//callErrorMessageDialog("Error", 'Assign fabric to PI Failed!');
				}
			});
		}
		
		for(var i =0;i<fabricnoArrayIsNotChecked.length;i++)
		{
			var fabricno = $("#chk"+fabricnoArrayIsNotChecked[i]+"").data("id");
			$.ajax({
				dataType: "json",
				type: 'POST',
				data:
					JSON.stringify({
						lotnumber: lotnumber,
						fabricno: fabricno,					
					}),
				contentType: "application/json",
				url: "/Chori/piassignfabric/delete/" + lotnumber + "/" + fabricno,
				success: function(data){
					if(data.deleteStatus == true){																					
						//callMessageDialog("Message", 'Delete fabric assignment Successfully!');
						
					}else if(data.deleteStatus == false){
						alert('This alert maybe never show! (4)');
					}else{
						alert('This alert maybe never show! (5)');
					}
				},
				error: function(){
					//callErrorMessageDialog("Error", 'Delete fabric assignment for PI Failed!');
				}
			});
		}
		loadData();

	});
	
	$('#btnCancel').on('click', function (e) {
		$( "#fabricAssignmentListDialog" ).dialog( "close" );
	});
	
	/**
	 * This function is used to call and set params for message dialog.
	 */
	function callMessageDialog(title, messageContent){
		$("#messageDialog").find("#messageContent").text(messageContent);
		$("#messageDialog").dialog({
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
					loadData();
					//loadFactoryData();
				}
			}
		}).prev(".ui-widget-header").css("color","#333");
	}
	
	function callErrorMessageDialog(title, messageContent){
		$("#messageDialog").find("#messageContent").text(messageContent);
		$("#messageDialog").dialog({
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
	
	function action(){
		//hàm cho nút assign fabric
		$('.btnFabricAssignment').on('click', function (e){
			
			$("#listFabricAssignmentDetailCover").html("");
			$("#listFabricAssignmentDetailCover").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listFabricAssignmentDetail">'+
					'<thead>'+
						'<tr>'+
							'<th>Lot No</th>' +
							'<th>Garment Style</th>' +
							'<th>Color</th>' +
							'<th>Yard in Used</th>' +
							'<th>Ship</th>' +					
						'</tr>'+
					'</thead>'+
				'</table>');
			
			//lấy ra data-id
			var fabricNo = $(this).data('id');
			$("#fabricAssignmentDialog").find("#txtFabricNo").val(fabricNo);

			//lấy thông tin qua id gán vào fabric assignment dialog
			$.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url: "/Chori/piassignfabricdetail/detail/"+fabricNo,
				contentType: "application/json",
				success: function(data){
	            	if(data.piassignfabricdetail.length>0)
            		{
	            		
        			//to fix dataTable error (cause by init DataTable in loop)
                	var countList=0;
                	var listLength = data.piassignfabricdetail.length;
					$('#fabricAssignmentDialog').find('#txtFabricNo').val(fabricNo);
						        	
	            	$.each(data.piassignfabricdetail, function( key, value ) {  
						var garmentStyle = (value.garmentstyle==null?"":value.garmentstyle);
						var color = (value.color==null?"":value.color);
						$('<tr>').append(
							$('<td>').text(value.lotnumber),
							$('<td>').text(garmentStyle),	
							$('<td>').text(color),	
							$('<td>').text(value.assignquantity== null ?" " : value.assignquantity),	
							$('<td>').text(value.shipping)
							).appendTo('#listFabricAssignmentDetail');
						countList++;
	                });

	            	if(countList==listLength)
	            		{
		    			$('#listFabricAssignmentDetail').DataTable( {
							"sPaginationType": "full_numbers",
							paging: true,
					        rowsGroup: [0,4]
						});
	            		}
            		}
					
				},
				error: function(){
					callMessageDialog("Message", "Can not get ID!");	
				}
			});
			//end lấy thông tin qua id gán vào edit dialog
			
			$("#fabricAssignmentDialog").dialog({
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Fabric Assignment History",
				height: 500,
				width: 700,
				modal: true,
				buttons:{
					"Exit": function(){
						$("#fabricAssignmentDialog").dialog("close");						
						
					}
				},
				close: function(){
					
				}
			});
		});
	}
	//when click add New Fabric Assignment
	$("#fabricAssignmentDialog").find('#btnAddNewAssignment').on('click', function (e){
		//lay fabricno value
		var fabricNo =  $("#fabricAssignmentDialog").find("#txtFabricNo").val();
		$('#addNewFabricAssignmentDialog').find('#txtFabricNo').val(fabricNo);
		var fabricNo2 = $('#addNewFabricAssignmentDialog').find('#txtFabricNo').val();	
		
		//truyen gia tri
		 var lotNumber = $("#txtLotNumber").val();
		 $('#addNewFabricAssignmentDialog').find('#txtLotNo').val(lotNumber);
		 		
		var assignQtyPercent = [] ;
				 
		 //refresh table
		 $("#listAddNewFabricAssignment").dataTable().fnDestroy();
		 $('#listAddNewFabricAssignment tbody').empty();
		 $("#listAddNewFabricAssignmentCover").html("");
			$("#listAddNewFabricAssignmentCover").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listAddNewFabricAssignment">'+
					'<thead>'+
						'<tr>'+
							'<th>Color</th>' +
							'<th>Garment Style</th>' +
							'<th>Total yard in BL</th>' +
							'<th>Using Value</th>'+	
							'<th>Inventory remained</th>' +					
							'<th>Available to Assign</th>	' +					
							'<th>Assign Quantity</th>'+	
							'<th>Assign Quantity Percent</th>'+						
						'</tr>'+
					'</thead>'+
				'</table>');
			$.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url: "/Chori/listfabricassignmentquantity/" + fabricNo2 + "/" + lotNumber,
				contentType: "application/json",
				success: function(data){
						var i=0					
						$.each(data.list,function(key,value){					
							var assignQtyText = value.assignquantity==null?"":value.assignquantity;
							var garmentStyleString = value.garmentstyle;
							garmentStyleString= garmentStyleString.replace(/ /g,'');
							assignQtyPercent.push("" + value.color+garmentStyleString+i);
    						$('<tr>').append(   								
    								$('<td>').text(value.color),
    								$('<td>').text(value.garmentstyle),
    								$('<td>').text(value.yardinbl),
    								$('<td>').text(value.usingvalue),
    								$('<td>').text(value.inventoryremained),
    								$('<td>').text(value.availableassign==null?"":value.availableassign),
    								$('<td>').html('<label id="txtAssignQty'+ value.color+garmentStyleString+i+'" data-garmentstyle="'+value.garmentstyle+'" > '+assignQtyText+'</label>'),						
    								$('<td>').html('<input type="number" step="any" min="0" max="'+(100 / value.maxQtyPercentValue)+'" class="form-control" data-color="'+value.color+'"  data-garmentstyle="'+value.garmentstyle+'"  id="txtAssignQuantityPercent' + value.color+garmentStyleString+i+ '" value="'+value.assignqtypercent+'">')	
    						).appendTo('#listAddNewFabricAssignment');	    						
    						
    						$("#txtAssignQuantityPercent" + value.color+garmentStyleString +i).on('input propertychange paste', function (e) {	
    							var calQtyByPercent = parseInt($("#listAddNewFabricAssignment").find("#txtAssignQuantityPercent" + value.color+garmentStyleString+i+"").val() * value.availableassign /100 );
    							$("#listAddNewFabricAssignment").find("#txtAssignQty" + value.color+garmentStyleString+i+"" ).text(calQtyByPercent);
    						});
    						
    						$("#btn100PercentAssign").on('click', function (e) {  		
    							var max = parseFloat($("#listAddNewFabricAssignment").find("#txtAssignQuantityPercent" + value.color+garmentStyleString+i+"").prop('max')).toFixed(2);
    							var qtyPercent = parseFloat($("#listAddNewFabricAssignment").find("#txtAssignQuantityPercent" + value.color+garmentStyleString+i+"").val(max));
    							var calQtyByPercent =  parseInt($("#listAddNewFabricAssignment").find("#txtAssignQuantityPercent" + value.color+garmentStyleString+i+"").val() * value.availableassign /100 );
    							$("#listAddNewFabricAssignment").find("#txtAssignQty" + value.color+garmentStyleString+i+"" ).text(calQtyByPercent);
    						});
    						
    						$("#btnReset100Percent").on('click', function (e) {  
    							var qtyPercent = $("#listAddNewFabricAssignment").find("#txtAssignQuantityPercent" + value.color+garmentStyleString+i+"").val(0);
    							qtyPercent = 0;
    							var calQtyByPercent = qtyPercent * value.availableassign / 100 ;
    							$("#listAddNewFabricAssignment").find("#txtAssignQty" + value.color+garmentStyleString+i+"").text(calQtyByPercent);
    						});      						
						});
						$('#listAddNewFabricAssignment').DataTable( {
						"sPaginationType": "full_numbers",
						paging: true,
				        rowsGroup: [0,2,3,4,5]
						});
				},
				error: function(){
					alert("Can not get data!");
				}
			});
			
		$("#addNewFabricAssignmentDialog").dialog({
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Fabric Assignment Quantity",
			height: 500,
			width: 1050,
			modal: true,
			buttons:{			
				"Save": function(){
											
						for(var i=0;i<assignQtyPercent.length;i++)
						{
							var lotNo = $('#addNewFabricAssignmentDialog').find('#txtLotNo').val();
							var fabricNo =  $('#addNewFabricAssignmentDialog').find('#txtFabricNo').val();
							var color = $("#txtAssignQuantityPercent"+assignQtyPercent[i]+"").data('color');
							var garmentStyle = $("#txtAssignQuantityPercent"+assignQtyPercent[i]+"").data('garmentstyle');
							var assignqty = parseInt($("#txtAssignQty"+assignQtyPercent[i]+"").text());
							var assignqtypercent = $("#txtAssignQuantityPercent"+assignQtyPercent[i]+"").val();
															
								//alert(color + "/" +garmentStyle + "/" +assignqty + "/" +assignqtypercent);
								
								 //edit
								$.ajax({
									dataType: "json",
									type: 'POST',
									async: false,					
									data:
										JSON.stringify({
											lotnumber : lotNo,
											fabricno : fabricNo,
											garmentstyle: garmentStyle,
											color: color,
											assignquantity: assignqty,		
											assignqtypercent: assignqtypercent
										}),
									contentType: "application/json",
									url: "/Chori/piassignfabricdetail/save",
									success: function(data){										
											if(data.saveStatus == true){	

												$("#addNewFabricAssignmentDialog").dialog("close");
												
											}else if(data.saveStatus == false){
												alert("Save failsadasd");
											}else{
												alert("Save fail");
											}
						            	
									},
									error: function(){
										//alert("Edit failed3213");
									}
								});	
						}
						alert ("Save successfully");
						loadData();
						$("#addNewFabricAssignmentDialog").dialog("close");
						
						//reload history list
						$("#listFabricAssignmentDetailCover").html("");
						$("#listFabricAssignmentDetailCover").append('<table class="table table-striped table-bordered display responsive"'+
								'id="listFabricAssignmentDetail">'+
								'<thead>'+
									'<tr>'+
										'<th>Lot No</th>' +
										'<th>Garment Style</th>' +
										'<th>Color</th>' +
										'<th>Yard in Used</th>' +
										'<th>Ship</th>' +					
									'</tr>'+
								'</thead>'+
							'</table>');
						
						//lấy ra data-id
						var fabricNo = $('#addNewFabricAssignmentDialog').find('#txtFabricNo').val();
						$("#fabricAssignmentDialog").find("#txtFabricNo").val(fabricNo);
						//lấy thông tin qua id gán vào fabric assignment dialog
						$.ajax({
							dataType: "json",
							type: 'GET',
							data: {},
							url: "/Chori/piassignfabricdetail/detail/"+fabricNo,
							contentType: "application/json",
							success: function(data){
				            	if(data.piassignfabricdetail.length>0)
			            		{				            		
			        			//to fix dataTable error (cause by init DataTable in loop)
			                	var countList=0;
			                	var listLength = data.piassignfabricdetail.length;
								$('#fabricAssignmentDialog').find('#txtFabricNo').val(fabricNo);
									        	
				            	$.each(data.piassignfabricdetail, function( key, value ) {  
									var garmentStyle = (value.garmentstyle==null?"":value.garmentstyle);
									var color = (value.color==null?"":value.color);
									$('<tr>').append(
										$('<td>').text(value.lotnumber),
										$('<td>').text(garmentStyle),	
										$('<td>').text(color),	
										$('<td>').text(value.assignquantity== null ?" " : value.assignquantity),	
										$('<td>').text(value.shipping)
										).appendTo('#listFabricAssignmentDetail');
									countList++;
				                });

				            	if(countList==listLength)
				            		{
					    			$('#listFabricAssignmentDetail').DataTable( {
										"sPaginationType": "full_numbers",
										paging: true,
								        rowsGroup: [0,4]
									});
				            		}
			            		}								
							},
							error: function(){
								callMessageDialog("Message", "Can not get ID!");	
							}
						});						
				},				
				"Exit": function(){
					$("#addNewFabricAssignmentDialog").dialog("close");				
				}
			},
			close: function(){			
			}
		});
	});	
	loadData();
});
