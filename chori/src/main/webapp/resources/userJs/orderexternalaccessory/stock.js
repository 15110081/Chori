$(document).ready(function(){
	var lotNumber = "Lot1";
	var accessoryCode = "BTNR";
	var piAssignExtCode="3.3"
	var factoryCode = "FAC0001";	
	function showOrderextaccByStock(){
		$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/externalaccessorystock/isExist/" + accessoryCode,
			success: function(data){
				if(data.isExisted){
					//$('#dialogShowStock2').css('display', 'initial');
					
					$("#dialogShowStock2").dialog({
						show:{
							effect:"blind",
							duration: 500
						},
						height: 250,
						width: 350,
						title: "Order Accessories",
						modal :true,
						buttons:{
							"Cancel": function(){
								$("#dialogShowStock2").dialog("close");
							}
						}
					});
					
                      
				}
				else{
					//$('#dialogShowStock1').css('display', 'initial');
					$("#dialogShowStock1").dialog({
						show:{
							effect:"blind",
							duration: 500
						},
						height: 250,
						width: 350,
						title: "Order Accessories",
						modal :true,
						buttons:{
							"Cancel": function(){
								$("#dialogShowStock1").dialog("close");
							}
						}
					});
				}
				
				
				$("#txtLotNumber").val(lotNumber);
				$("#txtAccessoryCode").val(accessoryCode);
			},
			error: function(){
				alert('error (23)');
			}
    	});
	}

		$("#dialogShowStock2").find("#btnNew").on('click', function (e){
			var lot=$("#txtLotNumber").val();
			var acc=$("#txtAccessoryCode").val();				
			$("#dialogOrderExtAccc").dialog({
				show:{
					effect:"blind",
					duration: 500
				},
				height: 600,
				width: 1366,
				title: "Order Accessories",
				modal :true,
			});
			 $.ajax({
		    		dataType: "json",
					type: 'GET',
					data:{},
					contentType: "application/json",
					url: "/Chori/externalaccessorytock/getEstimateQuantity/"+lotNumber+"/"+accessoryCode,
					success: function(data){
						$("#dialogOrderExtAccc").find("#txtEstimateQuantity").val(data);
						},
					
					error: function(){
						alert('error (54)');
					}
		    	});
		
			 $.ajax({
		    		dataType: "json",
					type: 'GET',
					data:{},
					contentType: "application/json",
					url: "/Chori/externalaccessorytock/getActualAssignQuantity/"+lotNumber+"/"+accessoryCode,
					success: function(data){
						$("#dialogOrderExtAccc").find("#txtActualAssignQuantity").val(data);
						},
					
					error: function(){
						alert('error (54)');
					}
		    	});	
			 
			 	$.ajax({
		    		dataType: "json",
					type: 'GET',
					data:{},
					contentType: "application/json",
					url: "/Chori/externalaccessorystock/getShortageQuantity/"+lotNumber+"/"+accessoryCode,
					success: function(data){
						$("#dialogOrderExtAccc").find("#txtOrderQuantity").val(data);
						},
					
					error: function(){
						alert('error (54)');
					}
		    	});	
			 	
				$("#dialogOrderExtAccc").find('#btnSendMail').on('click', function (e) {	
					$("#sendMailDialog").dialog({
						modal: true,
						show:{
							effect:"blind",
							duration: 500
						},
						title: "Send Mail",
						height: 600,
						width: 400
					});
				});
				$("#sendMailDialog").find('#btnCancelSendMail').on('click', function (e) {	
						$("#sendMailDialog").dialog("close");
				});
				$("#dialogOrderExtAccc").find('#btnCancelOrderExtAcc').on('click', function (e) {	
					$("#dialogOrderExtAccc").dialog("close");
				});
				$("#dialogOrderExtAccc").find('#btnSave').on('click', function (e) {	
					var accessorysuplier=$("#txtAccessorysuplier").val();
					var factory=$("#txtFactory").val();
					//var lotnumber="P001";
					var ordersheetno=$("#txtOrdersheetno").val();
					var accessory="BTNR";
					var estimatequantity=$("#txtEstimateQuantity").val();
					var orderqty = $("#txtOrderQuantity").val();
					var estimategrossquantity=$("#txtEstimateGrossQuantity").val();
					var actualdeliveriedgrossquantity=$("#txtActualDeliveriedGrossQuantity").val();
					var Ordate = $("#txtOrderDate").datepicker('getDate');
					var orderdate = ($.datepicker.formatDate('yy-mm-dd', Ordate));
					if(Ordate==null || Ordate =='' )  {
						var orderdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
						}
					var Estdate = $("#txtEstDeliveryDate").datepicker('getDate');
					var estdate = ($.datepicker.formatDate('yy-mm-dd', Estdate));
					if(Estdate==null || Estdate =='' )  {
						var estdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
						}
					var Actdate = $("#txtActualDeliveryDate").datepicker('getDate');
					var actdate = ($.datepicker.formatDate('yy-mm-dd', Actdate));
					var payment=$("#txtPaymentStatus").val();
					if(Actdate==null || Actdate =='' )  {
					var actdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
					}
						var remark=$("#txtRemark").val();
						var status=$("#txtStatus").val();
						var price=$("#txtPrice").val();
					
						if(orderqty>0)
						{
							/**ajax call method save from orderexternalaccessorycontroller **/ 
							$.ajax({
					    		dataType: "json",
								type: 'POST',
								data:
									JSON.stringify({
										orderSheetNo:ordersheetno,
										// lotNumber:lotnumber,
										 accsuplierCode:accessorysuplier,
										 factoryCode:factory,
										 accessoryCode:accessory,
										 //creator:,
										 orderQuantity:orderqty,
										 actualdevlQuantity:actualdeliveriedgrossquantity,
										 orderDate:orderdate,
										 price:price,
										 estimatedevlDate:estdate,
										 actualdevlDate:actdate,
										 status:status,
										 remark:remark,
										 paymentStatus:payment,
										 lotNumber: lotNumber,
										 //createDate,
									}),
								contentType: "application/json",
								url: "/Chori/orderexternalaccessory/save",
								success: function(data){
									$.ajax({
										dataType: "json",
										type: 'GET',
										data: {},
										url: "/Chori/orderexternalaccessory/updateOrderQtyOfAPiAssignExternalAccessory/"+accessoryCode + "/" +lotNumber + "/" +orderqty + "/" + ordersheetno,
										contentType: "application/json",
										success: function(data){	
											callMessageDialog("Message", "Save external accessory successfully!");
											$("#dialogOrderExtAccc").close;
										
										},
										error: function(){
											//alert("Không lấy đc dữ liệu!");
										}
									});
								},
								error: function(){
									callMessageDialog("Message", "Edit external accessory  unsuccessfully!");
									
								}
					    	});				
						}
						else{
							alert("Order Quantity must be greater than 0!");
						}
		    	});
		});
		
		

			
			
//			$("#dialogShowStock2").find("#btnAssign").on('click',function (e){
//
//				$("#assignForm").dialog({
//					show:{
//						effect:"blind",
//						duration: 500
//					},
//					height: 600,
//					width: 1366,
//				});
//				$.ajax({
//		    		dataType: "json",
//					type: 'GET',
//					data:{},
//					contentType: "application/json",
//					url: "/Chori/accessory/detail/"+accessoryCode,
//					success: function(data){
//						$("#assignForm").find("#lblAccessoryName").text(data.accessory.name);
//						$("#assignForm").find("#lblUnit").text(data.accessory.unitcode);
//
//						},
//					
//					error: function(){
//						alert('error(140)');
//					}
//		    	});
//				$.ajax({
//		    		dataType: "json",
//					type: 'GET',
//					data:{},
//					contentType: "application/json",
//					url: "/Chori/externalaccessorytock/getInventoryQuantity/"+accessoryCode,
//					success: function(data){
//						$("#assignForm").find("#lblInventoryquantity").text(data);
//						
//
//						},
//					
//					error: function(){
//						alert('error(155)');
//					}
//		    	});		
//			});
			
		$("#dialogShowStock1").find("#btnNew").on('click', function (e){
			var lot=$("#txtLotNumber").val();
			var acc=$("#txtAccessoryCode").val();			
			$("#dialogOrderExtAccc").dialog({
				show:{
					effect:"blind",
					duration: 500
				},
				height: 600,
				width: 1366,
				title: "Order Accessories",
				modal :true,
			});
			
			 $.ajax({
	    		dataType: "json",
				type: 'GET',
				data:{},
				contentType: "application/json",
				url: "/Chori/externalaccessorytock/getEstimateQuantity/"+lotNumber+"/"+accessoryCode,
				success: function(data){
					$("#dialogOrderExtAccc").find("#txtEstimateQuantity").val(data);
					},
				
				error: function(){
					alert('error (54)');
				}
		    });
				
			 $.ajax({
	    		dataType: "json",
				type: 'GET',
				data:{},
				contentType: "application/json",
				url: "/Chori/externalaccessorytock/getActualAssignQuantity/"+lotNumber+"/"+accessoryCode,
				success: function(data){
					$("#dialogOrderExtAccc").find("#txtActualAssignQuantity").val(data);
					$("#dialogOrderExtAccc").find("#txtOrderQuantity").val(data);
					},
				
				error: function(){
					alert('error (54)');
				}
		    });
			
		 	$.ajax({
	    		dataType: "json",
				type: 'GET',
				data:{},
				contentType: "application/json",
				url: "/Chori/externalaccessorystock/getShortageQuantity/"+lotNumber+"/"+accessoryCode,
				success: function(data){
					$("#dialogOrderExtAccc").find("#txtOrderQuantity").val(data);
					},
				
				error: function(){
					alert('error (54)');
				}
	    	});	
		 	
			$("#dialogOrderExtAccc").find('#btnSendMail').on('click', function (e) {	
				$("#sendMailDialog").dialog({
					modal: true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Send Mail",
					height: 600,
					width: 400
				});
			});
			$("#sendMailDialog").find('#btnCancelSendMail').on('click', function (e) {	
				$("#sendMailDialog").dialog("close");
			});
			
			$("#dialogOrderExtAccc").find('#btnCancelOrderExtAcc').on('click', function (e) {	
				$("#dialogOrderExtAccc").dialog("close");
			});
			
			$("#dialogOrderExtAccc").find('#btnSave').on('click', function (e) {	
				var accessorysuplier=$("#txtAccessorysuplier").val();
				var factory=$("#txtFactory").val();
				//var lotnumber="P001";
				var ordersheetno=$("#txtOrdersheetno").val();
				var accessory="BTNR";
				var estimatequantity=$("#txtEstimateQuantity").val();
				var orderqty = $("#txtOrderQuantity").val();
				var estimategrossquantity=$("#txtEstimateGrossQuantity").val();
				var actualdeliveriedgrossquantity=$("#txtActualDeliveriedGrossQuantity").val();
				var Ordate = $("#txtOrderDate").datepicker('getDate');
				var orderdate = ($.datepicker.formatDate('yy-mm-dd', Ordate));
				if(Ordate==null || Ordate =='' )  {
					var orderdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
					}
				var Estdate = $("#txtEstDeliveryDate").datepicker('getDate');
				var estdate = ($.datepicker.formatDate('yy-mm-dd', Estdate));
				if(Estdate==null || Estdate =='' )  {
					var estdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
					}
				var Actdate = $("#txtActualDeliveryDate").datepicker('getDate');
				var actdate = ($.datepicker.formatDate('yy-mm-dd', Actdate));
				var payment=$("#txtPaymentStatus").val();
				if(Actdate==null || Actdate =='' )  {
					var actdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
					}
				var remark=$("#txtRemark").val();
				var status=$("#txtStatus").val();
				var price=$("#txtPrice").val();
					
				if(orderqty>0)
				{
					/**ajax call method save from orderexternalaccessorycontroller **/ 
					$.ajax({
			    		dataType: "json",
						type: 'POST',
						data:
							JSON.stringify({
								orderSheetNo:ordersheetno,
								// lotNumber:lotnumber,
								 accsuplierCode:accessorysuplier,
								 factoryCode:factory,
								 accessoryCode:accessory,
								 //creator:,
								 orderQuantity:orderqty,
								 actualdevlQuantity:actualdeliveriedgrossquantity,
								 orderDate:orderdate,
								 price:price,
								 estimatedevlDate:estdate,
								 actualdevlDate:actdate,
								 status:status,
								 remark:remark,
								 paymentStatus:payment,
								 lotNumber: lotNumber,
								 //createDate,
							}),
						contentType: "application/json",
						url: "/Chori/orderexternalaccessory/save",
						success: function(data){
							$.ajax({
								dataType: "json",
								type: 'GET',
								data: {},
								url: "/Chori/orderexternalaccessory/updateOrderQtyOfAPiAssignExternalAccessory/"+accessoryCode + "/" +lotNumber + "/" +orderqty + "/" + ordersheetno,
								contentType: "application/json",
								success: function(data){	
									callMessageDialog("Message", "Save external accessory successfully!");
									$("#dialogOrderExtAccc").close;
								
								},
								error: function(){
									//alert("Không lấy đc dữ liệu!");
								}
							});
						},
						error: function(){
							callMessageDialog("Message", "Edit external accessory  unsuccessfully!");
							
						}
			    	});				
				}
				else{
					alert("Order Quantity must be greater than 0!");
				}
		    });			
		});		
	
	 function loadDropDownSuplier(){
		 /**ajax to call method get list accessorysuplier * **/
    	$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/accessorysupplier/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#txtAccessorysuplier').append($('<option>', {
                            value: value.accessorysuppliercode,
                            text: value.shortname
                        }));
                        
                        
                    });
				}else{
					alert('This alert should never show!');
				}
			},
			error: function(){
				alert('error (68)');
			}
    	});
    }
	/** function to show price and currency with param are accessorysuplier,accessorycode and orderdate  * **/
	 function loadPriceandCurency(){
		$("#txtAccessorysuplier")
		.on('change', function() {
			var accessoryCode= "BTNR";
			var accessorySuplier=$("#txtAccessorysuplier").val();
			var date = $("#txtOrderDate").datepicker('getDate');
			var parseDate = ($.datepicker.formatDate('yy-mm-dd', date));
			

			if(date==null || date =='' )  {
				var parseDate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
				}
			/** ajax call method get in orderexternalaccessoryController
			 * **/
			 	$.ajax({
		    		dataType: "json",
					type: 'GET',
					data:{},
					contentType: "application/json",
					url:"/Chori/orderexternalaccessory/get/"+accessoryCode+"/"+accessorySuplier+"/"+parseDate,
					success: function(data){
						if (data.status == "ok") {
							$.each(data.list,function(key,value){
								$("#lblCurrency").text(value.currencycode);
								$("#txtPrice").val(value.unitpriceperunit);
							

							});
								
				
						}
					},
					
					error: function(){
						alert('error (108)'+accessoryCode +" "+accessorySuplier+" "+parseDate);
					}
		    	});
			
		});
		/**
		 * when orderdate change 
		 * **/
		$("#txtOrderDate")
		.on('change', function() {
			var accessorySuplier=$("#txtAccessorysuplier").val();
		//	alert("ha" + accessorySuplier);
			var date = $("#txtOrderDate").datepicker('getDate');
			var parseDate = ($.datepicker.formatDate('yy-mm-dd', date));
			if(date==null || date =='' )  {
				var parseDate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
				}
			/**
			 * ajax to call method get from orderexternalaccessory controller 
			 * **/

			 	$.ajax({
		    		dataType: "json",
					type: 'GET',
					data:{},
					contentType: "application/json",
					url:"/Chori/orderexternalaccessory/get/"+accessoryCode+"/"+accessorySuplier+"/"+parseDate,
					success: function(data){
						if (data.status == "ok") {
							$.each(data.list,function(key,value){
								$("#lblCurrency").text(value.currencycode);
								$("#txtPrice").val(value.unitpriceperunit);

							});
								
				
						}
					},
					
					error: function(){
						alert('error (144)');
					}
		    	});
			
		});	
	 }
	 /**
	  * function to show all factory in drop down box
	  * **/
	 function loadDropDownFactory(){
		 /** ajax call method list from factorycontroller
		  * **/
	    	$.ajax({
	    		
	    		dataType: "json",
				type: 'GET',
				data:{},
				contentType: "application/json",
				url: "/Chori/factory/list",
				success: function(data){
					if(data.status == "ok"){
						$.each( data.list, function( key, value ) {
	                        $('#txtFactory').append($('<option>', {
	                            value: value.factorycode,
	                            text: value.shortname
	                        }));
	                        
	                        
	                    });
					}else{
						alert('This alert should never show!');
					}
				},
				error: function(){
					alert('error (173)');
				}
	    	});
	    }
	 /**function to show accessoryname
	  * **/
	 function loadAccessoryName(){
			/**ajax call method detail from accessorycontroller
			 * **/
		 	$.ajax({
	    		dataType: "json",
				type: 'GET',
				data:{},
				contentType: "application/json",
				url: "/Chori/accessory/detail/"+accessoryCode,
				success: function(data){
					$("#txtAccessoryName").val(data.accessory.name);
					
					},
				
				error: function(){
					alert('error(191)');
				}
	    	});
	    
	    }
	 
	
//function to genarate order sheet no 
	 function makeid(){
		
		 var count=1;
		
		 $.ajax({	    		
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/orderexternalaccessory/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {					
						
							if(value.orderSheetNo.substring(5) >= count)
							{
								count = parseInt(value.orderSheetNo.substring(5)) +1;
							}							
						
                    });
					//alert(count);
					var id=lotNumber+"_"+count;
					 $("#txtOrdersheetno").val(id);
				}else{
					alert('This alert should never show!');
				}
			},
			error: function(){
				alert('error (430)');
			}
    	});
		
		 
	 }
	
//function show dialog to display alert message
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
//function to add datetimepicker for date field
	 $( function() {
		    $( "#txtOrderDate" ).datepicker();
		    $( "#txtEstDeliveryDate" ).datepicker();
		    $( "#txtActualDeliveryDate" ).datepicker();
		  } );
	 //function to load factory name by Lotnumer from pi
	 function loadloadfactorybypi(){
		 
		 /** ajax call method detail from from picontroller **/
		 $.ajax({
	    		dataType: "json",
				type: 'GET',
				data:{},
				contentType: "application/json",
				url: "/Chori/pi/detail/"+lotNumber,
				success: function(data){
					$("#txtFactory").val(data.currentpi.factorycode);
					},
				
				error: function(){
					alert('error (479)');
				}
	    	});
	    }
	 /** function to show detail of accessory **/
	 function showAccessoryDetail(){
			/** ajax call method detail from accessorycontroller**/
			$.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url: "/Chori/accessory/detail/"+accessoryCode,
				contentType: "application/json",
				success: function(data){
					var i = 1;							
						$('<tr>').append(
								$('<td>').text(i),
								$('<td>').text(data.accessory.accessorycode),
								$('<td>').text(data.accessory.name),
								$('<td>').text(data.accessory.kind),
								$('<td>').text(data.accessory.dimension),
								$('<td>').text(data.accessory.mode),
								$('<td>').text(data.accessory.unitcode),
								$('<td>').text(data.accessory.colorcode),
								$('<td>').html(((data.accessory.imgurl1==null)||(data.accessory.imgurl1.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+data.accessory.imgurl1+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+data.accessory.imgurl1+'" data-id="choriAccessoryImage/'+data.accessory.imgurl1+'" /></a>')+((data.accessory.imgurl2==null)||(data.accessory.imgurl2.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+data.accessory.imgurl2+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'
										+data.accessory.imgurl2+'" data-id="choriAccessoryImage/'+data.accessory.imgurl2+'" /></a>'))
				
						).appendTo('#listAccessory');
					
					//action();

					
				},
				error: function(){
					//alert("Không lấy đc dữ liệu!");
				}
			});
		};
			
		
		
		
		/**
		 * Allow input numeric only in order form
		 */
		$("#dialogOrderExtAccc").on('keydown','#txtEstimateQuantity',
				function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
			||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
			||35<=e.keyCode&&40>=e.keyCode
			||(e.shiftKey||48>e.keyCode||57<e.keyCode)
			&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
		
		
		$("#dialogOrderExtAccc").on('keydown','#txtPrice',
				function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
			||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
			||35<=e.keyCode&&40>=e.keyCode
			||(e.shiftKey||48>e.keyCode||57<e.keyCode)
			&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()}); 
		
		$("#dialogOrderExtAccc").on('keydown','#txtActualDeliveriedGrossQuantity',
				function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
			||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
			||35<=e.keyCode&&40>=e.keyCode
			||(e.shiftKey||48>e.keyCode||57<e.keyCode)
			&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
		
		
		
	//action();
	showOrderextaccByStock();
	 showAccessoryDetail();
	 loadDropDownSuplier();
	 makeid();
	 loadAccessoryName();
	 loadDropDownFactory();
	 loadloadfactorybypi();
	
	 loadPriceandCurency();
	 
	 
	 $("#dialogShowStock2").find("#btnAssign").on('click',function (e){	 
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/externalaccessorytock/getStockAvailableQuantityInformation/"+accessoryCode + "/" +factoryCode+ "/" +lotNumber,
			contentType: "application/json",
			success: function(data){	
				$("#dialogAssignFromStock").find("#lblAssignFromStockLotNumber").text(data.accessoryname);
				$("#dialogAssignFromStock").find("#lblAssignFromStockActualAssignQty").text(data.actualassignqty);
				$("#dialogAssignFromStock").find("#lblAssignFromStockShortageQty").text(data.shortageqty);
				$("#dialogAssignFromStock").find("#lblAssignFromStockAvailableStockQty").text(data.availableqtyfromstock);
				$("#dialogAssignFromStock").find("#txtAssignFromStockAssignQty").val(data.stockassignqty);
			},
			error: function(){
				//alert("Không lấy đc dữ liệu!");
			}
		});
			
//			//auto calculate stock available quantity when change stock assign quantity
//			$("#dialogAssignFromStock").find("#txtAssignFromStockAssignQty").on('input propertychange paste', function (e) {	
//				var stockAvailableQty = parseFloat($("#dialogAssignFromStock").find("#lblAssignFromStockAvailableStockQty").text());	
//				var stockAssignQty = parseFloat($("#dialogAssignFromStock").find("#txtAssignFromStockAssignQty").val());
//				var sum = stockAvailableQty + stockAssignQty
//				if(stockAssignQty>=0 && stockAssignQty<stockAvailableQty){
//				$("#dialogAssignFromStock").find("#lblAssignFromStockAvailableStockQty").text(sum);}
//			});
			
		//show edit dialog
		$("#dialogAssignFromStock").dialog({
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Assign From Stock",
			height: 400,
			width: 500,
			modal :true,
			buttons:{
				"Save": function(){		
					var assignQty = $("#dialogAssignFromStock").find("#txtAssignFromStockAssignQty").val();
		
					//ajax to add availableqty in stock when edit
					$.ajax({
						dataType: "json",
						type: 'GET',
						data: {},
						url: "/Chori/externalaccessorytock/updateStockAvailableQtyWhenEdit/"+accessoryCode + "/" +lotNumber,
						contentType: "application/json",
						success: function(data){	
							//ajax to save assignqtyfromstock
							$.ajax({
								dataType: "json",
								type: 'GET',
								data: {},
								url: "/Chori/externalaccessorytock/saveAssignFromStock/"+accessoryCode + "/" +factoryCode + "/" +assignQty,
								contentType: "application/json",
								success: function(data){	
									var stockAssignQty = 0;
									//set stockAssignQty
									data==0?stockAssignQty=assignQty:stockAssignQty=assignQty-data;
									//ajax to update StockAssignQty field of an assign 
									$.ajax({
										dataType: "json",
										type: 'GET',
										data: {},
										url: "/Chori/externalaccessorytock/updateStockAssignQtyOfAnAssign/"+accessoryCode + "/" +lotNumber + "/" +stockAssignQty,
										contentType: "application/json",
										success: function(data){	
											//check shortageQty, if it > 0,then show dialog make new order
											$.ajax({
												dataType: "json",
												type: 'GET',
												data: {},
												url: "/Chori/externalaccessorytock/getStockAvailableQuantityInformation/"+accessoryCode + "/" +factoryCode+ "/" +lotNumber,
												contentType: "application/json",
												success: function(data){	
													var shortageQty = data.shortageqty;
													if(shortageQty >0)
													{
														 $("#dialogMessageNotEnoughQtyFromStock").find("#lblAssignFromStockShortageQty").text(data.shortageqty);														
														//show message suggest make new order dialog
														$("#dialogMessageNotEnoughQtyFromStock").dialog({
															show:{
																effect:"blind",
																duration: 500
															},
															title: "Assign From Stock",
															height: 350,
															width: 500,
															modal :true,
															buttons:{
																"Yes": function(){		
																	//show make new order dialog
																	$("#dialogOrderExtAccc").dialog({
																		show:{
																			effect:"blind",
																			duration: 500
																		},
																		height: 600,
																		width: 1366,
																		title: "Order Accessories",
																		modal :true,
																	});
																	
																	$("#txtOrderQuantity").val(shortageQty);
		
																	//set estimate qty for make new order 
																	 $.ajax({
																    		dataType: "json",
																			type: 'GET',
																			data:{},
																			contentType: "application/json",
																			url: "/Chori/externalaccessorytock/getEstimateQuantity/"+lotNumber+"/"+accessoryCode,
																			success: function(data){
																				$("#dialogOrderExtAccc").find("#txtEstimateQuantity").val(data);
																				},
																			
																			error: function(){
																				alert('error (54)');
																			}
																    });
																	 
																	 $.ajax({
																    		dataType: "json",
																			type: 'GET',
																			data:{},
																			contentType: "application/json",
																			url: "/Chori/externalaccessorytock/getActualAssignQuantity/"+lotNumber+"/"+accessoryCode,
																			success: function(data){
																				$("#dialogOrderExtAccc").find("#txtActualAssignQuantity").val(data);
																				},
																			
																			error: function(){
																				alert('error (54)');
																			}
																    });
																	 
																 	$.ajax({
															    		dataType: "json",
																		type: 'GET',
																		data:{},
																		contentType: "application/json",
																		url: "/Chori/externalaccessorystock/getShortageQuantity/"+lotNumber+"/"+accessoryCode,
																		success: function(data){
																			$("#dialogOrderExtAccc").find("#txtOrderQuantity").val(data);
																			},
																		
																		error: function(){
																			alert('error (54)');
																		}
															    	});	
																 	
																	$("#dialogOrderExtAccc").find('#btnSendMail').on('click', function (e) {	
																		$("#sendMailDialog").dialog({
																			modal: true,
																			show:{
																				effect:"blind",
																				duration: 500
																			},
																			title: "Send Mail",
																			height: 600,
																			width: 400
																		});
																	});
																	
																	$("#sendMailDialog").find('#btnCancelSendMail').on('click', function (e) {	
																		$("#sendMailDialog").dialog("close");
																	});
																	
																	$("#dialogOrderExtAccc").find('#btnCancelOrderExtAcc').on('click', function (e) {	
																		$("#dialogOrderExtAccc").dialog("close");
																	});
																	
																	$("#dialogOrderExtAccc").find('#btnSave').on('click', function (e) {	
																		var accessorysuplier=$("#txtAccessorysuplier").val();
																		var factory=$("#txtFactory").val();
																		//var lotnumber="P001";
																		var ordersheetno=$("#txtOrdersheetno").val();
																		var accessory="BTNR";
																		var estimatequantity=$("#txtEstimateQuantity").val();
																		var orderqty = $("#txtOrderQuantity").val();
																		var estimategrossquantity=$("#txtEstimateGrossQuantity").val();
																		var actualdeliveriedgrossquantity=$("#txtActualDeliveriedGrossQuantity").val();
																		var Ordate = $("#txtOrderDate").datepicker('getDate');
																		var orderdate = ($.datepicker.formatDate('yy-mm-dd', Ordate));
																		if(Ordate==null || Ordate =='' )  {
																			var orderdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
																			}
																		var Estdate = $("#txtEstDeliveryDate").datepicker('getDate');
																		var estdate = ($.datepicker.formatDate('yy-mm-dd', Estdate));
																		if(Estdate==null || Estdate =='' )  {
																			var estdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
																		}
																		var Actdate = $("#txtActualDeliveryDate").datepicker('getDate');
																		var actdate = ($.datepicker.formatDate('yy-mm-dd', Actdate));
																		var payment=$("#txtPaymentStatus").val();
																		if(Actdate==null || Actdate =='' )  {
																			var actdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
																		}
																		
																		var remark=$("#txtRemark").val();
																		var status=$("#txtStatus").val();
																		var price=$("#txtPrice").val();
																		if(orderqty>0)
																		{
																			/**ajax call method save from orderexternalaccessorycontroller **/ 
																			$.ajax({
																	    		dataType: "json",
																				type: 'POST',
																				data:
																					JSON.stringify({
																						orderSheetNo:ordersheetno,
																						// lotNumber:lotnumber,
																						 accsuplierCode:accessorysuplier,
																						 factoryCode:factory,
																						 accessoryCode:accessory,
																						 //creator:,
																						 orderQuantity:orderqty,
																						 actualdevlQuantity:actualdeliveriedgrossquantity,
																						 orderDate:orderdate,
																						 price:price,
																						 estimatedevlDate:estdate,
																						 actualdevlDate:actdate,
																						 status:status,
																						 remark:remark,
																						 paymentStatus:payment,
																						 lotNumber: lotNumber,
																						 //createDate,
																					}),
																				contentType: "application/json",
																				url: "/Chori/orderexternalaccessory/save",
																				success: function(data){
																					$.ajax({
																						dataType: "json",
																						type: 'GET',
																						data: {},
																						url: "/Chori/orderexternalaccessory/updateOrderQtyOfAPiAssignExternalAccessory/"+accessoryCode + "/" +lotNumber + "/" +orderqty + "/" + ordersheetno,
																						contentType: "application/json",
																						success: function(data){	
																							callMessageDialog("Message", "Save external accessory successfully!");
																							$("#dialogOrderExtAccc").close;
																						
																						},
																						error: function(){
																							//alert("Không lấy đc dữ liệu!");
																						}
																					});
																				},
																				error: function(){
																					callMessageDialog("Message", "Edit external accessory  unsuccessfully!");
																					
																				}
																	    	});				
																		}
																		else{
																			alert("Order Quantity must be greater than 0!");
																		}
																	});	
																},
																"Cancel": function(){
																	
																	$("#dialogMessageNotEnoughQtyFromStock").dialog("close");
																}
															},
															close: function(){
															}
														});
													}															
												},
												error: function(){
													//alert("Không lấy đc dữ liệu!");
												}
											});
										},
										error: function(){
										}
									});
									
		
								},
								error: function(){
									//alert("Không lấy đc dữ liệu!");
								}
							});
						},
						error: function(){
						}
					});
					
		
				},
				"Cancel": function(){
					
					$("#dialogAssignFromStock").dialog("close");
				}
			},
			close: function(){
			}
		});
	 });
})


