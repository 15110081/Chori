$(document).ready(function(){	
	//------------------------Load dropdownbox-------------------------
	
	//load AccessorySupplier  to dropdownbox
	function loadDropDownAccessorySupplier(){
    	$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/accessorysupplier/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#ddlAccSupplierCode').append($('<option>', {
                            value: value.accessorysuppliercode,
                            text: value.shortname
                        }));
                    });
				}else{
					alert('This alert should never show!');
				}
			},
			error: function(){
				alert('Error');
			}
    	});
    }
	//load Factory  to dropdownbox
	function loadDropDownFactory(){
    	$.ajax({
    		
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/factory/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#ddlFactoryCode').append($('<option>', {
                            value: value.factorycode,
                            text: value.shortname
                        }));
                        
                        
                    });
				}else{
					alert('This alert should never show!');
				}
			},
			error: function(){
				alert('Error');
			}
    	});
    }
	// datetimepicker Add
	$(function() {	
		   var dateFormat = "dd/mm/yy",
		   estdeliverydate = $("#txtEstDeliveryDate")
		        .datepicker({
		        
		          changeMonth: true,
		          dateFormat : 'dd/mm/yy',
		          numberOfMonths: 1
		        })
		        .on( "change", function() {
		        	actualdeliverydate.datepicker( "option", "minDate", getDate( this ) );
		        }),
		       actualdeliverydate = $("#txtActualDeliveryDate").datepicker({
		       
		        changeMonth: true,
		        dateFormat : 'dd/mm/yy',
		        numberOfMonths: 1
		      }),
		      
		      orderdate = $("#txtOrderDate").datepicker({
		    	  changeMonth: true,
			        dateFormat : 'dd/mm/yy',
			        numberOfMonths: 1
		      })
		      

		 
		    function getDate( element ) {
		      var date;
		      try {
		        date = $.datepicker.parseDate( dateFormat, element.value );
		      } catch( error ) {
		        date = null;
		      }
		 
		      return date;
		    }
		  });
	//function OrderSheetNo (DD/MM/YYYY/AutoIncrement
	 function AutoIncrementId()
	 {
		 var today = new Date() ;
		 var dd = today.getDate();
		 var mm = today.getMonth()+1; 
		 var yyyy = today.getFullYear();

		 if(dd<10) {
		     dd='0'+dd
		 } 
		 if(mm<10) {
		     mm='0'+mm
		 } 
		 today = dd + ""+ mm + ""+ yyyy;
		
		 var count =1;
		 $.ajax({	    		
	    		dataType: "json",
				type: 'GET',
				data:{},
				contentType: "application/json",
				url: "/Chori/orderinternalaccessory/list",
				success: function(data){
					if(data.status == "ok"){
						$.each( data.list, function( key, value ) {					
							if(value.orderSheetNo.slice(3,11)== today)
							{
								if(value.orderSheetNo.slice(11) >= count)
								{
									count = parseInt(value.orderSheetNo.slice(11)) +1;
								}							
							}
	                    });
						 $("#txtOrderSheetNo").val("INT"+today+count);
					}else{
						alert('This alert should never show!');
					}
				},
				error: function(){
					alert('Error');
				}
	    	});
		 
	 }
	 
	 //Assign Accessory
	 var AccessoryCodeArray = [];
		$('#btnAdd').on('click',function(){
			var accessorysuppliercode = $("#ddlAccSupplierCode").val();
			$.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url: "/Chori/orderinternalaccessory/listaccessory/"+accessorysuppliercode,
				contentType: "application/json",
				success: function(data){
					$("#listAccessory").dataTable().fnDestroy();
					$('#listAccessory tbody').empty();
					var i = 1;
					if(data.list.length==0){
//						alert("Table have no data..");
					}
					$.each(data.list,function(key,value){
						AccessoryCodeArray.push(value.accessorycode);
						$('<tr>').append(
								value.checkedForOrderInternal==true?//xem xem có ở bảng dưới ko? nếu có mới check
								$('<td>').html('<input type="checkbox" name="chkListAcc" id="chk'+value.accessorycode+'" value="'+value.accessorycode+'" checked/>'):
									$('<td>').html('<input type="checkbox" name="chkListAcc" id="chk'+value.accessorycode+'" value="'+value.accessorycode+'" />'),
								$('<td>').text(i++),
								$('<td>').text(value.name),							
								$('<td>').text(value.accessorycode)
								
						).appendTo('#listAccessory');
						
					});
					$('#listAccessory').DataTable( {
						"pagingType": "full_numbers"
				    });
				},
				error: function(){
					alert("Can not get data!");
				}
			
			});
			$("#dialogOrderInternalAccessoryDetail").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Internal Accessories ",
				height: 500,
				width: 1200,
				buttons:{
					"Save": function(){
						var accessoryArray_isChecked=[];
						var accessoryArray_isUnChecked=[];
						
						//delete duplicate accessory code in array
						AccessoryCodeArray = AccessoryCodeArray.reduce(function(a,b){if(a.indexOf(b)<0)a.push(b);return a;},[]);
						//to know which checkbox is checked and push its accessory code to array
						for(var i =0;i<AccessoryCodeArray.length;i++)
						{
					        if($("#chk"+AccessoryCodeArray[i]+"").is(":checked")) {
					        	accessoryArray_isChecked.push({
					        		accessorycode:AccessoryCodeArray[i],
					        		accessorysuppliercode: $("#ddlAccSupplierCode").val(),
					        		orderDate: $("#txtOrderDate").val()
					        	});

						    }
					        else if(!$("#chk"+AccessoryCodeArray[i]+"").is(":checked")){
					        	accessoryArray_isUnChecked.push(AccessoryCodeArray[i]);			        	
					        }
						}
						
						console.log(accessoryArray_isChecked);
						// add Accessory in orderinternalaccessoryDetail Controler
						$.ajax({
							dataType: "json",
							type: 'POST',
							data: JSON.stringify(accessoryArray_isChecked),
							contentType: "application/json",
							url: "/Chori/orderinternalaccessoryDetail/add",
							success: function(data){
								if(data.status=="ok"){
										callMessageDialog("Message", "Assign Accessory successfully!");
										loadListOrderInternalAccessoryDetailModel();
								}else{
									alert("Error");
								}
							},
							error: function(){
								alert("failed");
							}
						});
						// clear all model table Accessory when save add order internal accessory   
						$.ajax({
							dataType: "json",
							type: 'GET',
							data: {},
							url: "/Chori/orderinternalaccessory/clearDataAfterCloseAddOrderInternalAccDetail",
							contentType: "application/json",
							success: function(data){
//								alert("clear ok");
							},
							error: function(){
								alert("Can't get data");
							}
						});
						///
					},
					"Cancel": function(){
						$("#dialogOrderInternalAccessoryDetail").dialog("close");
						$("#listAccessory").dataTable().fnDestroy();
						$('#listAccessory tbody').empty();
					}
				},
				close: function(){
					
				}
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
			}
			
		})
			
		//hàm lưu accessory code list cuối để add
		var finalAccessoryDetailList = [];
		/**
		 * Hàm load list order Internal Accessory detail for add dialog
		 */
		function loadListOrderInternalAccessoryDetailModel(){
			//xóa tạo lại table
			$("#OrderInternalAccessoryDetailTableCover").html('');
			$("#OrderInternalAccessoryDetailTableCover").append('<table class="table table-striped table-bordered display responsive"'+
						'id="listOrderInternalDetail">'+
						'<thead>'+
						'<h4 align="center"> Order Accessories </h4> ' + 
							'<tr>'+
								'<th>Acessories name </th>'+
								'<th>Code</th>'+
								'<th>Order qty</th>'+
								'<th>Act delv qty</th>'+
								'<th>Unit</th>'+
								'<th>Unit Price</th>'+
								'<th>Refer Unit Price</th>'+
								'<th>Price</th>'+
								'<th>Currency</th>'+
								'<th>Photo</th>'+
							'</tr>'+
						'</thead>'+
					'</table>');
			
			$.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url: "/Chori/orderinternalaccessoryDetailAdd/list",
				contentType: "application/json",
				success: function(data){
					if(data.listOrderInternalDetailAdd.length==0){

					}
					$.each(data.listOrderInternalDetailAdd,function(key,value){
						$('<tr>').append(
								$('<td>').text(value.accessoryname),
								$('<td>').text(value.accessorycode),
								$('<td>').html('<input type="number" min="1" class="form-control" id="txtOrderQty'  + value.accessorycode +'" name="txtOrderQty" value="'+(value.orderquantity==null?"":value.orderquantity)+'">'),
								$('<td>').html('<input type="number" min="0" class="form-control" id="txtActDelvQty' + value.accessorycode +'" name="txtActDelvQty" value="'+(value.actualdelvquantity==null?"0":value.actualdelvquantity)+'">'),
								$('<td>').text(value.unit),
								$('<td>').html('<input type="number" min="1" class="form-control" id="txtUnitPrice' + value.accessorycode +'" name="txtUnitPrice" value="'+ value.unitprice +'" >'),
								$('<td>').text(value.unitprice==null?"":parseFloat(value.unitprice)),
								$('<td>').html('<label  id="txtPrice' + value.accessorycode +'"  >'+(value.price==null?"":value.price)+'</label>'),
								$('<td>').text(value.currency==null?"":value.currency),
								$('<td>').html(((value.image==null)||(value.image.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.image+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.image+'" data-id="choriAccessoryImage/'+value.image+'" /></a>'))
						).appendTo('#listOrderInternalDetail');	
						
//						 
						
						/**
						 * Allow input numeric only in column OrderQty form
						 */
						
						 $("#listOrderInternalDetail,#OrderInternalAccessoryDetailTableCover").on('keydown', '#txtOrderQty'+value.accessorycode,
									function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
								||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
								||35<=e.keyCode&&40>=e.keyCode
								||(e.shiftKey||48>e.keyCode||57<e.keyCode)
								&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
						 
						 /**
							 * Allow input numeric only in column ActDelvQty form
							 */
						 
						 $("#listOrderInternalDetail,#OrderInternalAccessoryDetailTableCover").on('keydown', '#txtActDelvQty'+value.accessorycode,
									function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
								||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
								||35<=e.keyCode&&40>=e.keyCode
								||(e.shiftKey||48>e.keyCode||57<e.keyCode)	
								&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
						 
						 /**
							 * Allow input numeric only in column UnitPrice form
							 */
						 
						 $("#listOrderInternalDetail,#OrderInternalAccessoryDetailTableCover").on('keydown', '#txtUnitPrice'+value.accessorycode,
									function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
								||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
								||35<=e.keyCode&&40>=e.keyCode
								||(e.shiftKey||48>e.keyCode||57<e.keyCode)	
								&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
							

						//event for change orderQty value
						$("#txtOrderQty"+value.accessorycode+"").on('input propertychange paste', function (e) {
							var orderQty = $("#txtOrderQty"+value.accessorycode+"").val();
							var unitPrice = $("#txtUnitPrice"+value.accessorycode+"").val();
							var actDelvQty = $("#txtActDelvQty"+value.accessorycode+"").val()
							if(actDelvQty==0)
								$("#txtPrice"+value.accessorycode+"").text(orderQty*unitPrice);
							else
								$("#txtPrice"+value.accessorycode+"").text(actDelvQty*unitPrice);
						})
						
						$("#txtActDelvQty"+value.accessorycode+"").on('input propertychange paste', function (e) {
							var orderQty = $("#txtOrderQty"+value.accessorycode+"").val();
							var unitPrice = $("#txtUnitPrice"+value.accessorycode+"").val();
							var actDelvQty = $("#txtActDelvQty"+value.accessorycode+"").val()
							if(actDelvQty==0)
								$("#txtPrice"+value.accessorycode+"").text(orderQty*unitPrice);
							else
								$("#txtPrice"+value.accessorycode+"").text(actDelvQty*unitPrice);
						})
						
						//event for change unitPrice value
						$("#txtUnitPrice"+value.accessorycode+"").on('input propertychange paste', function (e) {
							var orderQty = $("#txtOrderQty"+value.accessorycode+"").val();
							var unitPrice = $("#txtUnitPrice"+value.accessorycode+"").val();
							var actDelvQty = $("#txtActDelvQty"+value.accessorycode+"").val()
							if(actDelvQty==0)
								$("#txtPrice"+value.accessorycode+"").text(orderQty*unitPrice);
							else
								$("#txtPrice"+value.accessorycode+"").text(actDelvQty*unitPrice);
						})
						

						
						//mới add để lưu code
						finalAccessoryDetailList.push(value.accessorycode);
						finalAccessoryDetailList = finalAccessoryDetailList.reduce(function(a,b){if(a.indexOf(b)<0)a.push(b);return a;},[]);
						
						
					});

					$('#listOrderInternalDetail').DataTable( {
						"pagingType": "full_numbers"
				    } );
				},
				error: function(){
					alert("Error");
				}
			});
		};
	 
	//handle click event for add button of table
	 function action(){	
			$('#btnSave').on('click', function (e) {	
				var accessorysuplier=$("#ddlAccSupplierCode").val();
				var factory=$("#ddlFactoryCode").val();

				var ordersheetno=$("#txtOrderSheetNo").val();
				var invoicenumber=$("#txtInvoiceNumber").val();
				
				var validateOrderQuantityStatus = true;
		
				var remark=$("#areaRemak").val();
				var status=$("#ddlStatus").val();
				
				
			
				var lstOrderInternalAccessoryDetailModel = [];
						
						$.each(finalAccessoryDetailList,function(key,value){
							if($("#txtOrderQty"+value+"").val() <=0)
								{
									callMessageDialog("Message", "Order Quantity must be greater than 0");
									validateOrderQuantityStatus = false;
								}
							if($("#txtUnitPrice"+value+"").val() <=0)
							{
								callMessageDialog("Message", "Unit Price must be greater than 0");
								validateOrderQuantityStatus = false;
							}
							
							lstOrderInternalAccessoryDetailModel.push({
								accessorycode: value,
								orderquantity: $("#txtOrderQty"+value+"").val(),
								unitprice: $("#txtUnitPrice"+value+"").val(),
								price: $("#txtPrice"+value+"").text(),
								actualdelvquantity: $("#txtActDelvQty"+value+"").val()
							});

						}); 
						console.log(lstOrderInternalAccessoryDetailModel);
						 if(!compareEstVsActualDay()){
							callMessageDialog("Message", "Actual Delivery Date can't smaller than Est Delivery Date ");
						}
						 else{
						//check orderquantity >0
						if(validateOrderQuantityStatus == true){
							//call ajax to save order
							$.ajax({
					    		dataType: "json",
								type: 'POST',
								data:
									JSON.stringify({
										orderSheetNo:ordersheetno,
										accsuplierCode:accessorysuplier,
										factoryCode:factory,
										orderDate:$("#txtOrderDate").datepicker('getDate'),
										estimatedevlDate:$("#txtEstDeliveryDate").datepicker('getDate'),
										actualdevlDate:$("#txtActualDeliveryDate").datepicker('getDate'),
										status:status,
										remark:remark,
										invoiceNumber:invoicenumber,
										lstOrderInternalAccessoryDetailModel: lstOrderInternalAccessoryDetailModel
										 //createDate,
									}),
								contentType: "application/json",
								url: "/Chori/orderinternalaccessory/save",
								success: function(data){
									window.location.href = "/Chori/listorderinternalaccessory";
									callMessageDialog("Message", "Add Order Successfully!");
								},
								error: function(){
									callMessageDialog("Message", "Add Order Unsuccessfully!");
								}
					    	});
						}
						 }

					
		    	});
			
			$('#btnCancel').on('click', function (e) {	
				window.location.href = "/Chori/listorderinternalaccessory";
				// clear all model table Accessory when cancel order internal accessory
				$.ajax({
					dataType: "json",
					type: 'GET',
					data: {},
					url: "/Chori/orderinternalaccessory/clearDataAfterCloseAddOrderInternalAccDetail",
					contentType: "application/json",
					success: function(data){
						//alert("ok");
					},
					error: function(){
						alert("Can't get data");
					}
				});
				
				//delete table inter accessory detail 
				$("#OrderInternalAccessoryDetailTableCover").html('');
			});
		
	 }
	 
	 function clearAtFirst(){
		 $.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url: "/Chori/orderinternalaccessory/clearDataAfterCloseAddOrderInternalAccDetail",
				contentType: "application/json",
				success: function(data){
					//alert("ok");
				},
				error: function(){
					alert("Can't get data");
				}
			});
	 }
	 
	 function compareEstVsActualDay(){
		 var EstDate = $("#txtEstDeliveryDate").val();
		 var ActDate = $("#txtActualDeliveryDate").val();
		 if (ActDate < EstDate)
			 return false;
		 return true;
	 };
	 
	
		

		

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
	 
	 function addSuccessMessageDialog(){
			var title = $("#SuccessTitle").text();
			$("#AddSuccessDialog").dialog({
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
			}).prev(".ui-widget-header").css("color","#333");
		}
		
		function addFailedMessageDialog(){
			var title = $("#ErrorTitle").text();
			$("#AddFailedDialog").dialog({
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
		
		$(".fancybox").fancybox();
	AutoIncrementId();
	loadDropDownFactory();
	loadDropDownAccessorySupplier();
	action();
	clearAtFirst();
});