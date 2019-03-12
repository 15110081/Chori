
$(document).ready(function(){
	/**
	 * function to show list orderexternalAccessory 
	 * **/
	function ShowListOrderExternalAccessory(){
		/**ajax to call method get list orderexternalaccessory 
		 * **/
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/orderexternalaccessory/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				var accsuplier;
				if(data.list.length==0){
					alert("Table have no data.");
				}
				$.each(data.list,function(key,value){
					var accsuplierCode=value.accsuplierCode;
					

					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.orderSheetNo),
						
							
							$('<td>').text(value.accsuplierName),
							$('<td>').text(value.factoryName),
							$('<td>').text(value.accessoryName),
							$('<td>').text(value.status),

							$('<td>').html('<button class="btn btn-primary btnEdit " data-id='+value.orderSheetNo+'>'
									
									+'Edit</button>')
									
					).appendTo('#listOrderExtAcc');
				});
				action();
				$('#listOrderExtAcc').DataTable({
					"pagingType": "full_numbers"
			    });
			},
			error: function(){
				alert("Can not get data!");
			}
		});
		
	};
	/** function to show all suplier in dropdownbox 
	 * **/
	 function loadDropDownSuplier(){
		 /**ajax to call method get list accessorysuplier 
			 * **/
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
	/** function to show price and currency with param are accessorysuplier,accessorycode and orderdate  
	 * **/
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
			var accessoryCode= "BTNR";
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
						alert('error (159)');
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
			var accessorycode= "BTNR";
			/**ajax call method detail from accessorycontroller
			 * **/
		 	$.ajax({
	    		dataType: "json",
				type: 'GET',
				data:{},
				contentType: "application/json",
				url: "/Chori/accessory/detail/"+accessorycode,
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
		 lotnumber="Lot1";
		 $.ajax({	    		
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/orderexternalaccessory/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {					
						
							if(value.orderSheetNo.substring(4) >= count)
							{
								count = parseInt(value.orderSheetNo.substring(4)) +1;
							}							
						
                    });
				
					 $("#txtOrdersheetno").val(lotnumber+count);
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
		 var pi="Lot1";
		 /** ajax call method detail from from picontroller **/
		 $.ajax({
	    		dataType: "json",
				type: 'GET',
				data:{},
				contentType: "application/json",
				url: "/Chori/pi/detail/"+pi,
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
		var code="BTNR"
			/** ajax call method detail from accessorycontroller**/
			$.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url: "/Chori/accessory/detail/"+code,
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
					
					action();

					
				},
				error: function(){
					alert("Không lấy đc dữ liệu!");
				}
			});
		};
		
		 function action(){	
			 /** when click save button **/
				$("#frmOrderextacc").find('#btnSave').on('click', function (e) {	
					var accessorysuplier=$("#txtAccessorysuplier").val();
					var factory=$("#txtFactory").val();
					//var lotnumber="P001";
					var ordersheetno=$("#txtOrdersheetno").val();
					var accessory="BTNR";
					var estimatequantity=$("#txtEstimateQuantity").val();
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
										 orderQuantity:estimatequantity,
										 actualdevlQuantity:actualdeliveriedgrossquantity,
										 orderDate:orderdate,
										 price:price,
										 estimatedevlDate:estdate,
										 actualdevlDate:actdate,
										 status:status,
										 remark:remark,
										 paymentStatus:payment,
										 //createDate,
									}),
								contentType: "application/json",
								url: "/Chori/orderexternalaccessory/save",
								success: function(data){
									
									callMessageDialog("Message", "Save external accessory successfully!");
									
								
								
								},
								error: function(){
									callMessageDialog("Message", "Edit external accessory  unsuccessfully!");
								}
					    	});
					
				
			    	});
				$("#frmOrderextacc").find('#btnCancel').on('click', function (e) {	
					window.location.href = "/Chori";
				});
				/**when click edit button in list orderexternalaccessory**/
				$('.btnEdit').click(function(){
					var osn= $(this).data('id');
					//alert(osn);
					/** ajax call method detail from orderexternalaccessorycontroller **/
					$.ajax({
						dataType: "json",
						type: 'GET',
						data: {},
						url: "/Chori/orderexternalaccessory/detail/"+osn,
						contentType: "application/json",
						success: function(data){
											
							if(data.status=="ok"){
								

							$('#dialogEditOrderExtAccc').find('#txtAccessorysuplier').val(data.current.accsuplierCode);
							$('#dialogEditOrderExtAccc').find('#txtOrdersheetno').val(data.current.orderSheetNo);
							$('#dialogEditOrderExtAccc').find('#txtFactory').val(data.current.factoryCode);
							$('#dialogEditOrderExtAccc').find('#txtAccessoryName').val(data.current.accessoryName);
							$('#dialogEditOrderExtAccc').find('#txtEstimateQuantity').val(data.current.orderQuantity);
							$('#dialogEditOrderExtAccc').find('#txtAccessoryCode').val(data.current.accessoryCode);
							$('#dialogEditOrderExtAccc').find('#txtStatus').val(data.current.status);	
							$('#dialogEditOrderExtAccc').find('#txtActualDeliveriedQuantity').val(data.current.actualdevlQuantity);
							$('#dialogEditOrderExtAccc').find('#txtOrderDate').val(data.current.orderDate);
							$('#dialogEditOrderExtAccc').find('#txtEstDeliveryDate').val(data.current.estimatedevlDate);
							$('#dialogEditOrderExtAccc').find('#txtActualDeliveryDate').val(data.current.actualdevlDate);
							$('#dialogEditOrderExtAccc').find('#txtRemark').val(data.current.remark);
							$('#dialogEditOrderExtAccc').find('#txtOrderQuantity').val(data.current.orderQuantity);
							
							
							// method to get price and currency by accessory suplier and orderdate
							var accessoryCodeE= $('#dialogEditOrderExtAccc').find("#txtAccessoryCode").val();
							var accessorySuplierE=$('#dialogEditOrderExtAccc').find("#txtAccessorysuplier").val();
							var dateE = $('#dialogEditOrderExtAccc').find("#txtOrderDate").val();
							
						
							/**
							 * ajax to call method get from orderexternalaccessory controller 
							 * **/
							 	$.ajax({
						    		dataType: "json",
									type: 'GET',
									data:{},
									contentType: "application/json",
									url:"/Chori/orderexternalaccessory/get/"+accessoryCodeE+"/"+accessorySuplierE+"/"+dateE,
									success: function(data){
										if (data.status == "ok") {
											$.each(data.list,function(key,value){
												$('#dialogEditOrderExtAccc').find("#lblCurrency").text(value.currencycode);
												$('#dialogEditOrderExtAccc').find("#lblPrice").text(value.unitpriceperunit);
											

											});
												
								
										}
									},
									
									error: function(){
										alert('Error (472)');
									}
						    	});
							 	//close method
							
							 	
							 	
								var accessory=$("#txtAccessoryCode").val();
								var lotNumber=osn.split("_",1);
								
								$.ajax({
									dataType: "json",
									type: 'GET',
									data: {},
									url: "/Chori/orderexternalaccessory/getActualAssignQuantity/"+accessory+"/"+lotNumber+"/"+osn,
									contentType: "application/json",
									success: function(data){
														
										
											$('#dialogEditOrderExtAccc').find("#txtActAssignQuantity").val(data)
										
										}
										});
							
							}else{
								alert('This alert should never show!');
							}					
						},
						error: function(){
							callMessageDialog("Message", "Can not get ID!");	
						}
					});
				
					
					$("#dialogEditOrderExtAccc").dialog({
						show:{
							effect:"blind",
							duration: 500
						},
						title: " Order Accessories ",
						height: 600,
						width: 1200,
						buttons:{
							"Send Mail": function(){
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
								$("#sendMailDialog").find('#btnCancelSendMail').on('click', function (e) {	
									$("#sendMailDialog").dialog("close");
								});
							},
							"Save": function(){						
								var orderSheetNoEdit = $("#dialogEditOrderExtAccc").find("#txtOrdersheetno").val();
								var optionSelected = $("#txtStatus").find("option:selected");
								var actualdeliveriedquantity=$("#dialogEditOrderExtAccc").find("#txtActualDeliveriedQuantity").val();
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
								if(Actdate==null || Actdate =='' )  {
									var actdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
									}
								var remark=$("#dialogEditOrderExtAccc").find("#txtRemark").val();
								var statusEdit  = optionSelected.val();
								var orderQuantityE=$('#dialogEditOrderExtAccc').find('#txtOrderQuantity').val();
								if(statusEdit=="Cancel"){
									orderQuantityE=0;
								}
								$.ajax({
						    		dataType: "json",
									type: 'GET',
									data:{},
									contentType: "application/json",
									url:"/Chori/orderexternalaccessory/updateorderquantity/"+orderSheetNoEdit+"/"+orderQuantityE,
									success: function(data){
										}
									
									
									});
								
								
								
								
								
								//ajax call to update
								$.ajax({
						    		dataType: "json",
									type: 'POST',
									data:
										JSON.stringify({
											orderSheetNo: orderSheetNoEdit,
											actualdevlQuantity:actualdeliveriedquantity,
											orderDate:orderdate,
											estimatedevlDate:estdate,
											actualdevlDate:actdate,
											remark:remark,
											status: statusEdit,
											orderQuantity:orderQuantityE,
											
										}),
									contentType: "application/json",
									url: "/Chori/orderexternalaccessory/edit",
									success: function(data2){
									
										if(data2.editStatus==true)
											{
											callMessageDialog("Message", "Edit ExternalAccessory successfully!");
											
										
										$("#listOrderExtAcc").dataTable().fnDestroy();
										$('#listOrderExtAcc tbody').empty();
										ShowListOrderExternalAccessory();
										$("#dialogEditOrderExtAccc").dialog("close");
											}
									},
									error: function(){
										callMessageDialog("Message", "Edit ExternalAccessory Faild!");
									}
						    	});
								
							
								},
							"Cancel": function(){
								$("#dialogEditOrderExtAccc").dialog("close");
							}
							}
						});
				});
			}
		$(".fancybox").fancybox();

	 ShowListOrderExternalAccessory();
	 showAccessoryDetail();
	 loadDropDownSuplier();
	 makeid();
	 loadAccessoryName();
	 loadDropDownFactory();
	 loadloadfactorybypi();
	
	 loadPriceandCurency();
action();
});