function AssignInternalAccessoriesMainFunction(){
	$("#txtLotNumberInPi").val($("#editPIdialog").find("#lotnumber").val());
	$("#txtFactoryCodeInPi").val($("#editPIdialog").find("#factorycode").val());;
	//load list accessory is assigned in PI view
	function loadAssignInternalAccessoriesList(){
		$(".btnChooseOrder").unbind("click");
		$("#listInternalAccessoryCoverInPi").html("");
		//create list assign table
		$("#listInternalAccessoryCoverInPi").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listInternalAccessoryInPi">'+
					'<thead>'+
						'<tr>'+
							'<th>Name</th>' +
							'<th>Color</th>' +
							'<th>Dimension</th>' +
							'<th>Code</th>' +
							'<th>Group Name</th>' +					
							'<th>Kind</th>	' +					
							'<th>Mode</th>'+	
							'<th>Inventory Qty</th>'+	
							'<th>Available Qty</th>'+	
							'<th>Images</th>'+	
							'<th>Action</th>'+	
							'<th>Assign For</th>'+	
							'<th>Size</th>'+	
							'<th>Assign Qty</th>'+							
						'</tr>'+
					'</thead>'+
				'</table>');
		var lotNumberForLoadList = $("#txtLotNumberInPi").val();
		var factoryCodeForLoadList = $("#txtFactoryCodeInPi").val();
		//get all internal accessory and its quantity
		$.ajax({
			dataType: "json",
			type: 'POST',
			data:
				JSON.stringify({
					lotnumber: lotNumberForLoadList,
					factorycode: factoryCodeForLoadList
				}),
			url: getAbsolutePath() +  "assignedinternalaccessoryofpi/list",
			contentType: "application/json",
			success: function(data){
				if(data.list.length==0){
					$("#listInternalAccessoryCoverInPi").hide();
					//alert("Table have no data.");
					return;
				}
				$("#listInternalAccessoryCoverInPi").show();
				//var lotnumber = $("#txtLotNumberInPi").val();
            	
            	//to count because issue on load DataTable
				$.each(data.list,function(key,value){
					//get GarmentStyle + Color to string
					var piGarmentStyleTmp = (value.piGarmentStyle==null?"":value.piGarmentStyle+",").split("@@@",1);
					var piColorTmp = (value.piColor==null?"":value.piColor);
					
					//if garmentstyle or color is null, then not append to table
					if(value.piGarmentStyle!=null && value.piColor!=null)
					{
						$('<tr>').append(
								$('<td>').text(value.name),							
								$('<td>').text(value.colorcode),
								$('<td>').text(value.dimension),
								$('<td>').text(value.accessorycode),
								$('<td>').text(value.accessorygroupcode),							
								$('<td>').text(value.kind),						
								$('<td>').text(value.mode),
								$('<td>').text(value.inventoryQty),
								$('<td>').text(value.availableQty),
								$('<td>').html(((value.imgurl1==null)||(value.imgurl1.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl1+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl1+'" data-id="choriAccessoryImage/'+value.imgurl1+'" /></a>')+((value.imgurl2==null)||(value.imgurl2.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl2+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl2+'" data-id="choriAccessoryImage/'+value.imgurl2+'" /></a>')),
								$('<td>').html('<button type="button" class="btn btn-info btnEditAssignQty" data-accessorycode="' + value.accessorycode +'" data-availableqty="' + value.availableQty +'" data-inventoryqty="' + value.inventoryQty +'">Edit Assign Qty</button>'),
								$('<td>').text(piGarmentStyleTmp + ", " + piColorTmp),
								//$('<td>').text(getSizeNameBySizeCode(value.sizecode==null?"":value.sizecode)),
								$('<td>').text(value.sizename == null ? "" : value.sizename),
								$('<td>').text(value.piAssignQty==null?"":value.piAssignQty)							
						).appendTo('#listInternalAccessoryInPi');
					}					
				})
				editAssignQtyAction();
    			$('#listInternalAccessoryInPi').DataTable( {
					"sPaginationType": "full_numbers",
					paging: true,
			        rowsGroup: [0,1,2,3,4,5,6,7,8,9,10,11]
				});
			},
			error: function(){
				alert("Can not get data1!");
			}
		});
	}
	
	
	function editAssignQtyAction(){
		$('.btnEditAssignQty').on('click',function(){
			//get data by this "btnDetail"
			var lotnumber = $("#txtLotNumberInPi").val();
			var accessorycode = $(this).data("accessorycode");
			var availableQtyFromStock = $(this).data("availableqty");
			var inventoryQty = $(this).data("inventoryqty");
			//set data for lable
			$("#lblEditLotNumber").text($("#txtLotNumberInPi").val());
			$("#lblEditAccessory").text(accessorycode);
			$("#lblEditInventoryQty").text(inventoryQty);
			$("#lblEditAvailableQtyFromStock").text(availableQtyFromStock);
				
			//clear div table
			$('#dialogEditAssignInternalAccessories').find("#listEditAssignInternalAccessoryQtyCover").html("");
			$('#dialogEditAssignInternalAccessories').find("#listOrderIsAssignedCover").html("");
			
			//create list assign table
			$('#dialogEditAssignInternalAccessories').find("#listEditAssignInternalAccessoryQtyCover").append('<table class="table table-striped table-bordered display responsive"'+
						'id="listEditAssignInternalAccessoryQty">'+
						'<thead>'+
							'<tr>'+
								'<th>Assign For</th>' +
								'<th>Size</th>' +
								'<th>Used Value</th>' +
								'<th>Pcs</th>' +
								'<th>Recommend Qty</th>' +
								'<th>Assign Qty</th>' +
							'</tr>'+
						'</thead>'+
					'</table>');
			//create list order table
			$('#dialogEditAssignInternalAccessories').find("#listOrderIsAssignedCover").append('<table class="table table-striped table-bordered display responsive"'+
						'id="listOrderIsAssigned">'+
						'<thead>'+
							'<tr>'+								
								'<th>Order Sheet No</th>' +				
								'<th>Order Assign Qty</th>'+	
							'</tr>'+
						'</thead>'+
					'</table>');
			
			//to mark id for AssignQty textbox
			var countEdit=0;
			
			//ajax to load pi assign internal accessory detail
			$.ajax({
				dataType: "json",
				type: 'POST',
				data:
					JSON.stringify({
						lotnumber: lotnumber,
						accessory: accessorycode
					}),	
				contentType: "application/json",
				url: getAbsolutePath() + "piassigninternalaccessoriesdetail/detail/",
				success: function(data){
					$.each(data.piassigninternalaccessoriesdetail,function(key,value){
						//get GarmentStyle + Color to string
						var garmentStyleTmp = (value.garmentstyle==null?"":value.garmentstyle+",").split("@@@",1);
						var colorTmp = (value.color==null?"":value.color);
						//if garmentstyle or color is null, then not append to table
						if(value.garmentstyle!=null && value.color!=null)
						{
							$('<tr>').append(
									$('<td>').text(garmentStyleTmp + ", " + colorTmp),
									//$('<td>').text(getSizeNameBySizeCode(value.sizecode)),
									$('<td>').text(value.sizename == null ? "" : value.sizename),
									$('<td>').text(value.usedvalue),	
									$('<td>').text(value.pcs),	
									$('<td>').html('<label id="recommendQtyEdit'+ countEdit +'">'+value.recommendQty +'</label>'),	
									$('<td>').html('<input type="number" step="any" min="0" max="'+availableQtyFromStock+'" class="form-control" value="'+value.assignquantity+'" data-garmentstyle="' + value.garmentstyle + '" data-color="' + value.color + '" id="detailEdit'+ countEdit +'" data-piassigninternalaccessories="' + value.piassigninternalaccessories + '"  data-sizecode="' + value.sizecode + '">')
							).appendTo('#listEditAssignInternalAccessoryQty');
							countEdit++;
						}
					})	
					
					//datatable
	    			$('#listEditAssignInternalAccessoryQty').DataTable( {
	        		    deferRender: true,
	        		    scrollY:     300,
						scroller: true,
					    paging: false,
				        rowsGroup: [0]
					});	
					
					//set event for use recommend qty button
					$('#dialogEditAssignInternalAccessories').find('.btnUseRecommendQty').on('click',function(){
						for(var i =0; i<countEdit;i++)
						{							
							$("#detailEdit"+i+"").val(parseInt($("#recommendQtyEdit"+i+"").text()));
						}
					});
					
					//set event for detailEdit
					for(var i =0; i<countEdit;i++)
					{							
						$("#detailEdit"+i+"").on("change input propertychange",function(){
							var totalAssign =0;
							//calculate total assign
							for(var i =0; i<countEdit;i++)
							{
								totalAssign += parseInt($("#detailEdit"+i+"").val());
							}
							$('#dialogEditAssignInternalAccessories').find("#txtTotalAssign").val(totalAssign);
						});
					}
			        
				},
				error: function(){
					alert("Can not get data2!");
				}
			});
						
			//ajax to load order is assigned
			$.ajax({
				dataType: "json",
				type: 'POST',
				data:
					JSON.stringify({
						accessory: accessorycode,
						lotnumber: lotnumber
					}),
				url: getAbsolutePath() + "assigninternalaccessory/list/orderlist",
				contentType: "application/json",
				success: function(data){
					$.each(data.list,function(key,value){
						if(value.assignQty>0)
						{
							$('<tr>').append(
									$('<td>').text(value.orderSheetNo),
									$('<td>').text(value.assignQty)
							).appendTo('#listOrderIsAssigned');
						}
					});
					$("#dialogEditAssignInternalAccessories").find("#lblEditAvailableQtyByChoosing").text(data.availableQtyValue);
					$('#listOrderIsAssigned').DataTable( {
					});				        
				},
				error: function(){
				}
			});
			
			//show edit dialog
			$("#dialogEditAssignInternalAccessories").dialog({
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Edit General Accessory Quantity",
				height: 700,
				width: 850,
				modal :true,
				buttons:{
					"Save": function(){
						var garmentstyle;
						var color;
						var assignQty;
						var piassigninternalaccessories;
						//loop AssignQty textbox
						var checkavailableQtyWhenEdit=0;
						//loop AssignQty textbox
						for(var j =0; j<countEdit;j++)
						{							
							checkavailableQtyWhenEdit+=parseInt($("#detailEdit"+j+"").val()) ;
						}
						if(checkavailableQtyWhenEdit> parseInt($("#dialogEditAssignInternalAccessories").find("#lblEditAvailableQtyByChoosing").text()))
						{
							AvailableQtyIsNotEnough();
//							for(var j =0; j<countEdit;j++)
//							{
//								$("#detailEdit"+j+"").val(0);
//							}
							return;
						}
						else{
							//create array for edit
							arrayForEditPIAssignAccDetail = [];
							for(var i =0; i<countEdit;i++)
							{				
								garmentstyle = $("#detailEdit"+i+"").data('garmentstyle');
								color = $("#detailEdit"+i+"").data('color');
								sizecode = $("#detailEdit"+i+"").data('sizecode');
								assignQty = $("#detailEdit"+i+"").val();
								piassigninternalaccessories = $("#detailEdit"+i+"").data('piassigninternalaccessories');
								 
								arrayForEditPIAssignAccDetail.push ({
									garmentstyle: garmentstyle,
									color: color,
									piassigninternalaccessories: piassigninternalaccessories,
									sizecode : sizecode,
									assignquantity: assignQty
								});
							}
							
						    //call ajax for edit
							$.ajax({
								dataType: "json",
								type: 'POST',
								async: false,
								data: JSON.stringify(arrayForEditPIAssignAccDetail),
								contentType: "application/json",
								url: getAbsolutePath() + "piassigninternalaccessoriesdetail/edit",
								success: function(data){										
									if(data.editStatus == true){
										//location.reload();
										$("#dialogEditAssignInternalAccessories").dialog("close");										
									}else if(data.editStatus == false){
										alert("Edit failed");
									}else{
										alert("Edit failed");
									}				          	
								},
								error: function(){
									//alert("Edit failed3213");
								}
							});	
							
						}
						
						loadAssignInternalAccessoriesList();
					},
					"Cancel": function(){					
						$("#dialogEditAssignInternalAccessories").dialog("close");
					}
				},
				close: function(){
					$("#dialogEditAssignInternalAccessories").dialog("close");
				}
			});
			
			

		});
		
		//when click button "Choose Order"
		$('.btnChooseOrder').on('click',function(){
			//clear div
			$('#dialogListOrderInternalAccessoriesForAssign').find("#listOrderForAssignCover").html("");
			//get current accessorycode
			$('#dialogListOrderInternalAccessoriesForAssign').find("#txtAccessoryCode").val($('#lblEditAccessory').text());
			var accessoryCodeForListOrder = $('#dialogListOrderInternalAccessoriesForAssign').find("#txtAccessoryCode").val();			
			var lotNoForListOrder = $("#txtLotNumberInPi").val();
			
			
			//create list order table
			$('#dialogListOrderInternalAccessoriesForAssign').find("#listOrderForAssignCover").append('<table class="table table-striped table-bordered display responsive"'+
						'id="listOrderForAssign">'+
						'<thead>'+
							'<tr>'+								
								'<th>Order Sheet No</th>' +
								'<th>Order Qty</th>' +
								'<th>Actual Delv Qty</th>' +
								'<th>Price</th>' +					
								'<th>Available Qty</th>	' +					
								'<th>Order Assign Qty</th>'+	
							'</tr>'+
						'</thead>'+
					'</table>');
			
			var countEditOrder=0;
			var orderSheetNoArray = [];
			//load order list
			$.ajax({
				dataType: "json",
				type: 'POST',
				data:
					JSON.stringify({
						accessory: accessoryCodeForListOrder,
						lotnumber: lotNoForListOrder
					}),
				url: getAbsolutePath() + "assigninternalaccessory/list/orderlist",
				contentType: "application/json",
				success: function(data){
					//clear array
					orderSheetNoArray = [];
					
					//begin loop
					$.each(data.list,function(key,value){
						orderSheetNoArray.push(value.orderSheetNo);
						var maxInputQty = value.availableQty + value.assignQty;
						$('<tr>').append(
								$('<td>').text(value.orderSheetNo),
								$('<td>').text(value.orderquantity),	
								$('<td>').text(value.actualdelvquantity),
								$('<td>').text(value.price),
								$('<td>').text(value.availableQty),
								$('<td>').html('<input type="number" step="any" min="0" max="'+ maxInputQty
										+'" class="form-control" value="'+value.assignQty
										+'" data-ordersheetno="' + value.orderSheetNo 
										+ '" data-color="' + value.color 
										+ '" id="detailEdit'+ value.orderSheetNo 
										+'" data-piassigninternalaccessories="' + value.piassigninternalaccessories + '">')
						).appendTo('#listOrderForAssign');
						
						//set value = max value if input value > max value allow
						$("#dialogListOrderInternalAccessoriesForAssign").find("#detailEdit"+value.orderSheetNo +"").on('input propertychange paste'
							, function (e) {	
								if($("#detailEdit"+value.orderSheetNo +"").val() >  parseInt($("#detailEdit"+value.orderSheetNo +"").prop('max'))) {
									$("#detailEdit"+value.orderSheetNo +"").val(parseInt($("#detailEdit"+value.orderSheetNo +"").prop('max')));
								}
						});
						countEditOrder++;
					});
					$('#listOrderForAssign').DataTable( {
						"sPaginationType": "full_numbers",
						paging: true,
					});
			        
				},
				error: function(){
					alert("Can not get data3!");
				}
			});
			
			//show edit dialog
			$("#dialogListOrderInternalAccessoriesForAssign").dialog({
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Choose Order",
				height: 500,
				width: 850,
				modal: true,
				buttons:{
					"Save": function(){
						var ordersheetno;
						var orderassignquantity;
						var piInternalAccessoriesId;
						
						//validate when edit order chose quantity, if edit quantity (sum) < assigned quantity, then alert		
						var sumOfOrderAssignQuantity = 0;
						$.ajax({
							dataType: "json",
							type: 'POST',
							data:
								JSON.stringify({
									lotnumber: lotNoForListOrder,
									accessory: accessoryCodeForListOrder
								}),
							url: getAbsolutePath() + "piassigninternalaccessory/calculatesumofassignquantity",
							contentType: "application/json",
							success: function(data){
								
								//calculate sum of orderassignquantity
								for(var i =0; i<orderSheetNoArray.length;i++ ){
									//set data
									sumOfOrderAssignQuantity =  sumOfOrderAssignQuantity + parseInt($("#dialogListOrderInternalAccessoriesForAssign").find("#detailEdit"+orderSheetNoArray[i]+"").val());
								}	
								//if assigned quantity is greater than new orderassignqty, then alert
								if(sumOfOrderAssignQuantity<parseInt(data))
								{
									//alert("Order Chose Quantity is not valid");
									OrderQtyNotValid();
									return;
								}
								//else, then add/edit quantity
								else {
									//find piassigninternalaccessory id by lotnumber, accessorycode
									$.ajax({
										dataType: "json",
										type: 'POST',
										data:
											JSON.stringify({
												lotnumber: lotNoForListOrder,
												accessory: accessoryCodeForListOrder
											}),
										url: getAbsolutePath() + "piassigninternalaccessory/findbyid",
										contentType: "application/json",
										success: function(data){
											var orderSheetNoLength = 0;
											for(var i =0; i<orderSheetNoArray.length;i++ ){
												//set data
												ordersheetno = $("#dialogListOrderInternalAccessoriesForAssign").find("#detailEdit"+orderSheetNoArray[i]+"").data('ordersheetno');
												orderassignquantity = $("#dialogListOrderInternalAccessoriesForAssign").find("#detailEdit"+orderSheetNoArray[i]+"").val();
												
												piInternalAccessoriesId = data.id;		
												//save piassigninternalaccessoriesoforders
												$.ajax({
													dataType: "json",
													type: 'POST',
													data:
														JSON.stringify({
															piInternalAccessories: piInternalAccessoriesId,
															orderSheetNo: ordersheetno,
															assignQty: orderassignquantity
														}),
													contentType: "application/json",
													url: getAbsolutePath() + "piassigninternalaccessoriesoforders/save",
													success: function(data){
														orderSheetNoLength++;
														//when save last element of orderSheetNoArray,load table (order is assigned), 
														if(orderSheetNoLength == orderSheetNoArray.length)
														{
															//clear table (order is assigned)
															$('#dialogEditAssignInternalAccessories').find("#listOrderIsAssignedCover").html("");
															$('#dialogEditAssignInternalAccessories').find("#listOrderIsAssignedCover").append('<table class="table table-striped table-bordered display responsive"'+
																	'id="listOrderIsAssigned">'+
																	'<thead>'+
																		'<tr>'+								
																			'<th>Order Sheet No</th>' +				
																			'<th>Order Assign Qty</th>'+	
																		'</tr>'+
																	'</thead>'+
																'</table>');
															//ajax to load order is assigned
															$.ajax({
																dataType: "json",
																type: 'POST',
																data:
																	JSON.stringify({
																		accessory: accessoryCodeForListOrder,
																		lotnumber: lotNoForListOrder
																	}),
																url: getAbsolutePath() + "assigninternalaccessory/list/orderlist",
																contentType: "application/json",
																success: function(data){
																	$.each(data.list,function(key,value){
																		if(value.assignQty>0)
																		{
																			$('<tr>').append(
																					$('<td>').text(value.orderSheetNo),
																					$('<td>').text(value.assignQty)
																			).appendTo('#listOrderIsAssigned');
																		}
																	});
																	
																	$("#dialogEditAssignInternalAccessories").find("#lblEditAvailableQtyByChoosing").text(data.availableQtyValue);
																	$('#listOrderIsAssigned').DataTable( {
																	});				
																},
																error: function(){
																}
															});
														}
													},
													error: function(){
														alert("add error");
													}
												}); 	
											}
											orderSheetNoLength = 0;
											$( "#dialogListOrderInternalAccessoriesForAssign" ).dialog( "close" );	
										},
										error: function(){
											alert("Can not get data4!");
										}
									});	
								}
							},
							error: function(){
								return 0;
							}
						});
						
//						var sumOfAssignedQuantity = parseInt(CalculateSumOfAssignedQuantity(lotNoForListOrder,accessoryCodeForListOrder));
//						alert("sumOfAssignedQuantity: " +sumOfAssignedQuantity);
						


						
					
					},
					"Cancel": function(){				
						$("#dialogListOrderInternalAccessoriesForAssign").dialog("close");
					}
				},
				close: function(){
					$("#dialogListOrderInternalAccessoriesForAssign").dialog("close");
				}
			});
		});
		
	}
	
	//for validate When Edit Order Chose Quantity
	function CalculateSumOfAssignedQuantity(lotNumber,accessoryCode)
	{
		var result = 0;
		$.ajax({
			dataType: "json",
			type: 'POST',
			data:
				JSON.stringify({
					lotnumber: lotNumber,
					accessory: accessoryCode
				}),
			url: getAbsolutePath() + "piassigninternalaccessory/calculatesumofassignquantity",
			contentType: "application/json",
			success: function(data){
				result = parseInt(data);
			},
			error: function(){
				return 0;
			}
		});
		return result;
	}
	
	
	
	var accessoryCodeCheckBoxArray= [];
	//load list internal accessory to assign 
	$("#editPIdialog").find('#btnAssignInternalAccessories').on('click',function(){	
		//destroy table
		$("#listInternalAccessoryCover").html('');
		//create list assign table
		$("#listInternalAccessoryCover").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listInternalAccessory">'+
					'<thead>'+
						'<tr>'+
							'<th></th>' +
							'<th>Name</th>' +
							'<th>Color</th>' +
							'<th>Dimension</th>' +
							'<th>Code</th>' +
							'<th>Group Name</th>' +					
							'<th>Kind</th>	' +					
							'<th>Mode</th>'+	
							'<th>Inventory Qty</th>'+	
							'<th>Available Qty</th>'+	
//							'<th>Assign For</th>'+	
//							'<th>Size</th>'+	
//							'<th>Assign Qty</th>'+	
							'<th>Images</th>'+	
						'</tr>'+
					'</thead>'+
				'</table>');
		var lotNumberForLoadList = $("#txtLotNumberInPi").val();
		var factoryCodeForLoadList = $("#txtFactoryCodeInPi").val();
		/**
		 * This function is used to load internal accessory list into table
		 */
		//get all internal accessory and its quantity
		$.ajax({
			dataType: "json",
			type: 'POST',
			data:
				JSON.stringify({
					lotnumber: lotNumberForLoadList,
					factorycode: factoryCodeForLoadList
				}),
			url: getAbsolutePath() + "assigninternalaccessory/list",
			contentType: "application/json",
			success: function(data){
				if(data.list.length==0){
					//alert("Table have no data.");
					return;
				}
				var lotnumber = $("#txtLotNumberInPi").val();
				
            	//to count because issue on load DataTable
            	var countLoadList=0;
            	var listLength = data.list.length;
            	
				$.each(data.list,function(key,value){
					accessoryCodeCheckBoxArray.push(value.accessorycode);
					//var childTableHtml = getChildTable(value.get);		
					            	
					//check if accessory x lotnumber is not existed, then load to table
					$.ajax({
						dataType: "json",
						type: 'POST',
						data:
							JSON.stringify({
								accessory: value.accessorycode,
								pi: lotnumber
							}),
						contentType: "application/json",
						url: getAbsolutePath() + "piassigninternalaccessories/isExist/",
						success: function(data){
							//use to mark which is assigned and check/uncheck the checkbox
							var markIsExisted="";
							if(data.isExisted == true){
								markIsExisted = "checked";
							}
							else{
								markIsExisted = "";
							}
							//get GarmentStyle + Color to string
							var piGarmentStyleTmp = (value.piGarmentStyle==null?"":value.piGarmentStyle+",");
							var piColorTmp = (value.piColor==null?"":value.piColor);
							//if garmentstyle or color is null, then not append to table
							$('<tr>').append(
									$('<td>').html('<input type="checkbox" id="chk'+value.accessorycode+'" data-id="'+value.accessorycode+'" value="" '+markIsExisted+'>'),
									$('<td>').text(value.name),							
									$('<td>').text(value.colorcode),
									$('<td>').text(value.dimension),
									$('<td>').text(value.accessorycode),
									$('<td>').text(value.accessorygroupcode),							
									$('<td>').text(value.kind),						
									$('<td>').text(value.mode),
									$('<td>').text(value.inventoryQty),
									$('<td>').text(value.availableQty),
//									$('<td>').text(piGarmentStyleTmp + " " + piColorTmp),
//									$('<td>').text(value.sizecode==null?"":value.sizecode),
//									$('<td>').text(value.piAssignQty==null?"":value.piAssignQty),						
									$('<td>').html(((value.imgurl1==null)||(value.imgurl1.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl1+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl1+'" data-id="choriAccessoryImage/'+value.imgurl1+'" /></a>')+((value.imgurl2==null)||(value.imgurl2.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl2+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl2+'" data-id="choriAccessoryImage/'+value.imgurl2+'" /></a>'))
									
							).appendTo('#listInternalAccessory');						
							countLoadList++;
    						if(countLoadList === listLength)
    						{
    							$('#listInternalAccessory').DataTable( {
    							"sPaginationType": "full_numbers",
    							paging: true,
    					        rowsGroup: [0,1,2,3,4,5,6,7,8,9,10]
    							});
	    		        		
    						}
						},
						error: function(){
							
						}
					});
//					$('#listInternalAccessory').DataTable( {
//						"sPaginationType": "full_numbers",
//						paging: true,
//				        rowsGroup: [0,1,2,3,4,5,6,7,8,9,13]
//				    });

				});


//				$('#listInternalAccessory').DataTable( {
//					"pagingType": "full_numbers"
//			    } );
			},
			error: function(){
				alert("Can not get data5!");
			}
		});
		//show internal acc checklist
		$("#dialogAssignInternalAccessories").dialog({
			show:{
				effect:"blind",
				duration: 500
			},
			modal: true,
			title: "General Accessory Checklist",
			height: 600,
			//width: 1450,
			width: "auto",
			create: function( event, ui ) {
			// Set maxWidth
			$(this).css("maxWidth", "1450px");
			},
			buttons:{
				"Assign": function(){
					var lotnumber = $("#txtLotNumberInPi").val();
					//get check and uncheck checkbox
					var accessoryArray_isChecked=[]; 
					var accessoryArray_isUnChecked=[]; 
					var accessoryArrayUnique_isChecked=[]; 
					var accessoryArrayUnique_isUnChecked=[]; 
					//to know which checkbox is checked and push its accessory code to array
					for(var i =0;i<accessoryCodeCheckBoxArray.length;i++)
					{
				        if($("#chk"+accessoryCodeCheckBoxArray[i]+"").is(":checked")) {
				        	accessoryArray_isChecked.push(accessoryCodeCheckBoxArray[i]);    	
					    }
				        else if(!$("#chk"+accessoryCodeCheckBoxArray[i]+"").is(":checked")){
				        	accessoryArray_isUnChecked.push(accessoryCodeCheckBoxArray[i]);			        	
				        }
					}
					//get unique accessory code array to add
		        	accessoryArrayUnique_isChecked = accessoryArray_isChecked.reduce(function(a,b){if(a.indexOf(b)<0)a.push(b);return a;},[]);
		        	accessoryArrayUnique_isUnChecked = accessoryArray_isUnChecked.reduce(function(a,b){if(a.indexOf(b)<0)a.push(b);return a;},[]);
		        	
		        	var accessoryArrayUnique_isCheckedToAdd = [] ;
		        	var accessoryArrayUnique_isUnCheckedToDelete = [] ;
		        	
		        	//if checkbox is uncheck, then delete a pi assign internal accessories
		        	for(var i =0;i<accessoryArrayUnique_isUnChecked.length;i++)
					{				
		        		//get accessorycode in list
						var accessorycode_uncheck = $("#chk"+accessoryArrayUnique_isUnChecked[i]+"").data("id");
						
		        		accessoryArrayUnique_isUnCheckedToDelete.push ({
							pi: lotnumber,
							accessory: accessorycode_uncheck
						});
					}
									
					//delete
					$.ajax({
						dataType: "json",
						type: 'POST',
						async: false, 
						data:
							JSON.stringify(accessoryArrayUnique_isUnCheckedToDelete),
						contentType: "application/json",
						url: getAbsolutePath() + "piassigninternalaccessories/delete",
						success: function(data){
						},
						error: function(){
						}
					});
					
		        	//if checkbox is check, then add to pi assign internal accessories table
					for(var i =0;i<accessoryArrayUnique_isChecked.length;i++)
					{				
		        		//get accessorycode in list
						var accessorycode_ischecked = $("#chk"+accessoryArrayUnique_isChecked[i]+"").data("id");
						
						accessoryArrayUnique_isCheckedToAdd.push ({
							pi: lotnumber,
							accessory: accessorycode_ischecked
						});
					}
											
					//add
					$.ajax({
						dataType: "json",
						type: 'POST',
						async: false, 
						data:
							JSON.stringify(accessoryArrayUnique_isCheckedToAdd),
						contentType: "application/json",
						url: getAbsolutePath() + "assigninternalaccessory/add",
						success: function(data){
//							if(data.addStatus == true){																													
//							}else if(data.addStatus == false){
//								alert('This alert maybe never show! (4)');
//							}else{
//								alert('This alert maybe never show! (5)');
//							}
						},
						error: function(){
						}
					});
					
					loadAssignInternalAccessoriesList();
					$("#dialogAssignInternalAccessories").dialog("close");
					//location.reload();
					
					
					
					
				},
				"Cancel": function(){
					loadAssignInternalAccessoriesList();
					$("#dialogAssignInternalAccessories").dialog("close");
					
				}
			},
			close: function(){
			}
		});
	});
	
	//Messagebox available qty not enough dialog
	function AvailableQtyIsNotEnough(){
		$("#dialogAvailableQtyNotEnough").dialog({
			show:{
				effect:"slide",
				duration: 500
			},
			title: "Assign Failed",
			height: 250,
			width: 400,
			modal: true,
			hide: {
				effect: "slide",
				duration: 500
			},
			buttons:{
				"OK": function(){
					//window.location.href = "/Chori/orderinternalaccessory";
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#f5321c");
	}
	
	function getSizeNameBySizeCode(sizecode)
	{
		var result ="";
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            async: false,
            url: getAbsolutePath() + "assigninternalaccessory/getsizenamebysizecode/"+sizecode,
            success: function (data) {
            	result = data.result;
            },
            error: function(){
            }
		});
		return result;
	}
	
	//-------------MESSAGE DIALOG------------
	function OrderQtyNotValid(){
		$("#dialogOrderQtyNotValid").dialog({
			modal: true,
			show:{
				effect:"slide",
				duration: 500
			},
			title: "Message",
			height: 300,
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
		});
	}
	
	loadAssignInternalAccessoriesList();
	$(".fancybox").fancybox();
};