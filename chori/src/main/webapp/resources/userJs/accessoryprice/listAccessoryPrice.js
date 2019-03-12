$(document).ready(function(){	
	/**
	 * --------------START: Load accessory price list & set data to table ------------------
	 */
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "accessoryprice/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
//					alert("Table have no data.");
				}
					$.each(data.list,function(key,value){
						var fromdatevalue = value.fromdate.split("-");
						var formatformdate = fromdatevalue[2] +"-"+fromdatevalue[1]+"-"+fromdatevalue[0];
						if(value.todate!=null)
						{
						var todatevalue = value.todate.split("-");			
						var formattodate = todatevalue[2] +"-"+todatevalue[1]+"-"+todatevalue[0];
						}
						if(value.status=="Non-Expired"){
						//alert(date);
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.name),
								$('<td>').text(value.mode),
								$('<td>').text(value.accessorysuppliername),
								$('<td>').text(formatformdate),		
								$('<td>').text(formattodate),
								$('<td>').text(value.unitcode),
								$('<td>').text(value.unitpriceperunit),
								$('<td>').text(value.currencycode),
								$('<td>').text(value.status),
								$('<td>').text(value.remark),
								
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
												
							).appendTo('#listAccessoryPrice');
						}
						});
					
				action();

				$('#listAccessoryPrice').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Can not get data!");
			}
		});
	};
	
	
	
	//------------------------Load dropdownbox-------------------------
	
	//load accessory  to dropdownbox
	function loadDropDownAccessory(){
    	$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: getAbsolutePath() +  "accessory/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#dialogAddAccessoryPrice').find('#txtAccessoriesCode').append($('<option>', {
                            value: value.accessorycode,
                            text: value.name
                        }));
                        
                        $('#dialogEditAccessoryPrice').find('#txtAccessoriesCode').append($('<option>', {
                            value: value.accessorycode,
                            text: value.name
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
	//load accessory supplier to dropdownbox
	function loadDropDownAccessorySupplier(){
    	$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: getAbsolutePath() +  "accessorysupplier/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#dialogAddAccessoryPrice').find('#txtAccSupplierCode').append($('<option>', {
                            value: value.accessorysuppliercode,
                            text: value.shortname
                        }));
                        
                        $('#dialogEditAccessoryPrice').find('#txtAccSupplierCode').append($('<option>', {
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
	//load currency to dropdownbox
	function loadDropDownCurrency(){
    	$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: getAbsolutePath() +  "currency/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#dialogAddAccessoryPrice').find('#txtCurrencyCode').append($('<option>', {
                            value: value.currencycode,
                            text: value.currencycode
                        }));
                        
                        $('#dialogEditAccessoryPrice').find('#txtCurrencyCode').append($('<option>', {
                            value: value.currencycode,
                            text: value.currencycode
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
	
	//load Accessory Group to dropdownbox
	function loadDropDownAccGroup(){
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() +  "accessorygroup/list",
            success: function (data) {
            	$.each(data.list, function( key, value ) {
                    $("#sltAccessoryGroup").append($('<option>', {       	
                        value: value.accessorygroupCode,
                        text: value.accessorygroupCode
                    }));
                    	
                });
            	
            },
            error: function(){
            	alert("Error");
            }
		});
	}
	
	//handle click event for edit + delete + add button of table
	function action(){	
		//When click add
		$('#btnAdd').on('click', function (e) {	
			$("#dialogAddAccessoryPrice").find("#txtAccessoriesCode").val("");
			$("#dialogAddAccessoryPrice").find("#txtAccSupplierCode").val("");
			$("#dialogAddAccessoryPrice").find("#txtFromDate").val("");	
			$("#dialogAddAccessoryPrice").find("#txtToDate").val("");
			$("#dialogAddAccessoryPrice").find("#txtUnitPrice").val("0");
			$("#dialogAddAccessoryPrice").find("#txtCurrencyCode").val("");
			$("#dialogAddAccessoryPrice").find("#txtFromDate").val("");
			$("#dialogAddAccessoryPrice").find("#txtToDate").val("");
			$("#dialogAddAccessoryPrice").find("#areaRemark").val("");
//			$("#dialogAddAccessoryPrice").find("#txtFromDate").datepicker({dateFormat: 'dd-mm-yy'});
//		    $("#dialogAddAccessoryPrice").find("#txtToDate").datepicker({dateFormat: 'dd-mm-yy' });
		    $("#dialogAddAccessoryPrice").dialog({
		    	modal: true,
		    	show:{
					effect:"blind",
					duration: 500
				},
				title: "Add New Accessory Price",
				height: 500,
				width: 700,
				
				buttons:{
					"Save": function(){
						if(!isValidationInputFromToDateAdd()){
							callMessageDialog("Message", "Select date from!");
						}
						else if(!isValidationOverRangeWhenAddUnitPrice()){
							callMessageDialog("Message", "Unit Price  only 8 characters long!");
						}
						else if(!isValidationOverRangeWhenAddRemark()){
							callMessageDialog("Message", "Remark  only 200 characters long!");
						}
						else{
						var accessorycode= $("#dialogAddAccessoryPrice").find("#txtAccessoriesCode").val();
						var accessorysuppliercode= $("#dialogAddAccessoryPrice").find("#txtAccSupplierCode").val();
						var unitpriceperunit= $("#dialogAddAccessoryPrice").find("#txtUnitPrice").val();
						var currencycode= $("#dialogAddAccessoryPrice").find("#txtCurrencyCode").val();
						var remark= $("#dialogAddAccessoryPrice").find("#areaRemark").val();
						var fromdate= $("#dialogAddAccessoryPrice").find("#txtFromDate").datepicker('getDate');						
						var todate=$("#dialogAddAccessoryPrice").find("#txtToDate").datepicker('getDate');
						
						$.ajax({
				    		dataType: "json",
							type: 'POST',
							data:
								JSON.stringify({
									accessorycode: accessorycode,
									accessorysuppliercode: accessorysuppliercode,
									unitpriceperunit: unitpriceperunit,
									currencycode: currencycode,
									fromdate: $.datepicker.formatDate('yy-mm-dd', fromdate),
									todate: $.datepicker.formatDate('yy-mm-dd', todate),
									remark: remark
								}),
							contentType: "application/json",
							url: getAbsolutePath() +  "accessoryprice/add",
							success: function(data){
								if(data.addAccessoryPrice==false){
									callMessageDialog("Message", "Add Accessory Price unsuccessfully !");
									return ;
									
								}
								else{
									reloadTableWithStatus();
									callMessageDialog("Message", "Add Accessory Price successfully !");
									$("#dialogAddAccessoryPrice").dialog("close");
								}
							},
							error: function(){
								callMessageDialog("Message", "Add Accessory Price unsuccessfully !");
							}
				    	});
						}
					},
					"Cancel": function(){
						$("#dialogAddAccessoryPrice").dialog("close");
					}
					
				}
				
		    })
		});
		
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    var accessorypricecode= $(this).data('id');
		    $("#dialogEditAccessoryPrice").find("#errorAreaRemark").text("");
			$("#dialogEditAccessoryPrice").find("#areaRemark").css("border-color", "");
		    $(".selectOption").val("Options");
		    
		    //If user choose edit option
		    if(valueSelected=="Edit"){
				$.ajax({
		    		dataType: "json",
					type: 'POST',
					data:JSON.stringify({
						accessorypricecode: accessorypricecode
					}),
					contentType: "application/json",
					url: getAbsolutePath() +  "accessoryprice/detail",
					success: function(data){
						if(data.status=="ok"){
							var fromdatetmp =data.currentaccessoryprice.fromdate.split("-");
							fromdate = new Date(fromdatetmp[0], fromdatetmp[1] - 1, fromdatetmp[2]); 
							
							   
							
							if( data.currentaccessoryprice.todate==null)
							{
								$("#dialogEditAccessoryPrice").find("#txtToDateEdit").val(""); // format to show
							   
							}
							else{
								var todatetmp = data.currentaccessoryprice.todate.split("-");
								todate = new Date(todatetmp[0], todatetmp[1] - 1, todatetmp[2]);
								$("#dialogEditAccessoryPrice").find("#txtToDateEdit").datepicker({ dateFormat: 'yy/mm/dd' }); // format to show
							    $("#dialogEditAccessoryPrice").find("#txtToDateEdit").datepicker('setDate', todate);
							}
						    $("#dialogEditAccessoryPrice").find("#txtFromDateEdit").datepicker({ dateFormat: 'yy/mm/dd' }); // format to show
						    $("#dialogEditAccessoryPrice").find("#txtFromDateEdit").datepicker('setDate', fromdate);
							$("#dialogEditAccessoryPrice").find("#txtAccessoriesCode").val(data.currentaccessoryprice.accessorycode);
							$("#dialogEditAccessoryPrice").find("#txtAccSupplierCode").val(data.currentaccessoryprice.accessorysuppliercode);
							$("#dialogEditAccessoryPrice").find("#txtUnitPrice").val(data.currentaccessoryprice.unitpriceperunit);
							$("#dialogEditAccessoryPrice").find("#txtCurrencyCode").val(data.currentaccessoryprice.currencycode);
							$("#dialogEditAccessoryPrice").find("#areaRemark").val(data.currentaccessoryprice.remark);
						
							//to set minDate for "toDate"
					    	var minDate= $("#dialogEditAccessoryPrice").find("#txtFromDateEdit").datepicker('getDate');
						      $("#dialogEditAccessoryPrice").find("#txtToDateEdit").datepicker( "option", "minDate",minDate ); 
						}else{
							alert('This alert should never show!');
						}
					},
					error: function(){
						alert('Cant get role detail!');
					}
		    	});
				 $("#dialogEditAccessoryPrice").dialog({
					 modal: true,
						show:{
							effect:"blind",
							duration: 500
						},
						title: "Edit Accessory Price",
						height: 500,
						width: 700,
						buttons:{
							"Edit": function(){
								if(!isValidationInputFromToDateEdit){
									callMessageDialog("Message","Select date from!");
								}
								else if(!isValidationOverRangeWhenEditUnitPrice()){
									callMessageDialog("Message", " Unit Price  only 8 characters long!");
							}
								else if(!isValidationOverRangeWhenEditRemark()){
										callMessageDialog("Message", " Remark  only 200 characters long!");
								}
								else{
									var accessorycode= $("#dialogEditAccessoryPrice").find("#txtAccessoriesCode").val();
									var accessorysuppliercode= $("#dialogEditAccessoryPrice").find("#txtAccSupplierCode").val();
									var fromdate= $("#dialogEditAccessoryPrice").find("#txtFromDateEdit").datepicker('getDate');
									var todate=$("#dialogEditAccessoryPrice").find("#txtToDateEdit").datepicker('getDate');
									var unitpriceperunit=$("#dialogEditAccessoryPrice").find("#txtUnitPrice").val();
									var currencycode=$("#dialogEditAccessoryPrice").find("#txtCurrencyCode").val();
									var remark=$("#dialogEditAccessoryPrice").find("#areaRemark").val();
								
									//
									$.ajax({
							    		dataType: "json",
										type: 'POST',
										data:
											JSON.stringify({
												accessorypricecode: accessorypricecode,
												accessorycode: accessorycode,
												accessorysuppliercode: accessorysuppliercode,
												unitpriceperunit: unitpriceperunit,
												currencycode: currencycode,
												fromdate: $.datepicker.formatDate('yy-mm-dd', fromdate),
												todate: $.datepicker.formatDate('yy-mm-dd', todate),
												remark: remark
											}),
										contentType: "application/json",
										url: getAbsolutePath() +  "accessoryprice/edit",
										success: function(data){
											if(data.editAccessoryPrice==false){
												callMessageDialog("Message", "Edit Accessory Price unsuccessfully!");
												return ;
											}
											else{
												reloadTableWithStatus();
												callMessageDialog("Message", "Edit Accessory Price successfully!");
												$("#dialogEditAccessoryPrice").dialog("close");
											}
										},
										error: function(){
											callMessageDialog("Message", "Edit Accessory Price unsuccessfully!");
										}
							    	});
								}
								
							},
							"Cancel": function(){
								$("#dialogEditAccessoryPrice").dialog("close");
							}
						}
					});
		    };
		    
		    //if user choose delete option
		    if(valueSelected=="Delete"){
		    	var accessorypricecode= $(this).data('id');
		    	var text = "";
		    	$.ajax({
		    		dataType: "json",
					type: 'POST',
					data:JSON.stringify({
						accessorypricecode: accessorypricecode
					}),
					contentType: "application/json",
					url: getAbsolutePath() +  "accessoryprice/detail",
					success: function(data){
						if(data.status=="ok"){
							text = data.currentaccessoryprice.name;
							$("#dialogDeleteAccessoryPrice").find("#messageContent").text('Are you sure you want to delete Accessory Price  "' + text + '"?');
						}
					}
		    	})
		    	
		    	
				//var accessorypricecode= $(this).data('id');
				//$("#dialogDeleteAccessoryPrice").find("#messageContent").text('Are you sure you want to delete Accessory Price  "' + accessorypricecode + '"?');
			    
				$("#dialogDeleteAccessoryPrice").dialog({
					modal: true,
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
									accessorypricecode: accessorypricecode
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "accessoryprice/delete",
								success: function(data){
									if(data.status=="ok"){
										if(data.deleteAccessoryPrice== true){
											callMessageDialog("Message", "Delete successfully!");
											$("#dialogDeleteAccessoryPrice").dialog("close");
											reloadTableWithStatus();
										}
										else if(data.deleteAccessoryPrice== false){
											callMessageDialog("Message", 'Can\'t Delete Accessory Price "'+ '"!');
											$("#dialogDeleteAccessoryPrice").dialog("close");
										}else{
											callMessageDialog("Message", 'Can\'t Delete Accessory Price "'+ '"!');
										}
									}
									else{
										callMessageDialog("Message", 'Can\'t Delete Accessory Price "'+ '"!');
									}
								},
								
								error: function(){
									callMessageDialog("Message", 'Can\'t Delete Accessory Price "'+ '"!');
									
								}
					    	});
						},
						"No": function(){
							$("#dialogDeleteAccessoryPrice").dialog("close");
						}
					}
				});
			};
		    //end if user choose delete option
		    
		});
	};
	
	//------------------------Load Value Dropdownlist-------------------------
	
		//function get drop down list Status Value 
	function getddlStatusValue(){
		var optionSelected = $('#ddlStatus').find("option:selected");
		var valueSelected  = optionSelected.val();
		return valueSelected;
	}
	//function get drop down list Accessorygroup Value 
	function getddlGroupValue(){
		var optionSelected = $('#sltAccessoryGroup').find("option:selected");
		var valueSelected  = optionSelected.val();
		return valueSelected;
	}
	
	//------------------------Datetimepicker-------------------------
	
	// function datetimepicker Search
	$(function() {	
		   var dateFormat = "dd/mm/yy",
		      from = $( "#txtSearchFrom" )
		        .datepicker({
		      //    defaultDate: "+1w",
		          changeMonth: true,
		          dateFormat : 'dd/mm/yy',
		          numberOfMonths: 1
		        })
		        .on( "change", function() {
		          to.datepicker( "option", "minDate", getDate( this ) );
		        }),
		      to = $( "#txtSearchTo" ).datepicker({
		       // defaultDate: "+1w",
		        changeMonth: true,
		        dateFormat : 'dd/mm/yy',
		        numberOfMonths: 1
		      })
//		      .on( "change", function() {
//		        from.datepicker( "option", "maxDate", getDate( this ) );
//		      });
		 
		    function getDate( element ) {
		      var date;
		      try {
		        date = $.datepicker.parseDate( dateFormat, element.value );
		      } catch( error ) {
		        date = null;
		      }
		 
		      return date;
		    }
		  } );
	
	
//	$('#datetimepicker').datetimepicker({
//	    format: 'yyyy-mm-dd hh:ii'
//	});
//	 $("#dialogAddAccessoryPrice").find("#txtFromDate").datetimepicker({
//		 dateFormat : 'dd/mm/yy',
//		 format: 'DD-MM-YYYY 00:00:00'
//		 
//		});
//	 $("#dialogAddAccessoryPrice").find("#txtToDate").datetimepicker({
//		 format: 'DD-MM-YYYY 23:59:59'
//			 
//		});
	
	// datetimepicker Add
	$(function() {	
		   var dateFormat = "dd/mm/yy",
		      from = $("#dialogAddAccessoryPrice").find("#txtFromDate")
		        .datepicker({
		        	defaultDate: +7 , 
		          changeMonth: true,
		          dateFormat : 'dd/mm/yy',
		          numberOfMonths: 1
		        })
		        .on( "change", function() {
		          to.datepicker( "option", "minDate", getDate( this ) );
		        }),
		      to = $("#dialogAddAccessoryPrice").find("#txtToDate").datepicker({
		    	  defaultDate: +7 , 
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
		  } );
	
	// datetimepicker Edit
	function loadDatePickerForEdit() {	
		   var dateFormat = "dd/mm/yy",
		      from = $("#dialogEditAccessoryPrice").find("#txtFromDateEdit")
		        .datepicker({
		     //     defaultDate: "+1w",
		          changeMonth: true,
		          dateFormat : 'dd/mm/yy',
		          numberOfMonths: 1
		        })
		        .on( "change", function() {
		          to.datepicker( "option", "minDate", getDate( this ) );
		        }),
		      to = $("#dialogEditAccessoryPrice").find("#txtToDateEdit").datepicker({
		      //  defaultDate: "+1w",
		        changeMonth: true,
		        dateFormat : 'dd/mm/yy',
		        numberOfMonths: 1
		      })
//		      .on( "change", function() {
//		        from.datepicker( "option", "maxDate", getDate( this ) );
//		      });
		 
		    function getDate( element ) {
		      var date;
		      try {
		        date = $.datepicker.parseDate( dateFormat, element.value );
		      } catch( error ) {
		        date = null;
		      }
		      return date;
		    }
		  };
	loadDatePickerForEdit();
	//------------------------ Search -------------------------
	
	//event when choose status on dropdownbox
	$('#ddlStatus').on('change',function(){
		//lấy ra value đc chọn
		var optionSelected = $(this).find("option:selected");
		var valueSelected  = optionSelected.val();
		
		//
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "accessoryprice/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listAccessoryPrice").dataTable().fnDestroy();
				$('#listAccessoryPrice tbody').empty();
				
				var i = 1;
				var valueGroupSelect = getddlGroupValue();
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){					
					var fromyear = value.fromdate.substring(0, 4);
					var frommonth = value.fromdate.substring(5, 7);
					var fromday = value.fromdate.substring(8,10);														
					var formatformdate = fromday + "-" + frommonth + "-" + fromyear;
					
					if(value.todate!=null)
					{
					var toyear = value.todate.substring(0, 4);
					var tomonth = value.todate.substring(5, 7);
					var today = value.todate.substring(8,10);														
					var formattodate = today + "-" + tomonth + "-" + toyear;
					}
					
					
					//nếu value là all, 
					if(valueSelected=='All' && valueGroupSelect =="All"  ){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.name),
								$('<td>').text(value.mode),
								$('<td>').text(value.accessorysuppliername),
								$('<td>').text(formatformdate),		
								$('<td>').text(formattodate),
								$('<td>').text(value.unitcode),
								$('<td>').text(value.unitpriceperunit),
								$('<td>').text(value.currencycode),
								$('<td>').text(value.status),
								$('<td>').text(value.remark),
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAccessoryPrice');
					}
					if(valueSelected==value.status && valueGroupSelect =="All"  ){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.name),
								$('<td>').text(value.mode),
								$('<td>').text(value.accessorysuppliername),
								$('<td>').text(formatformdate),		
								$('<td>').text(formattodate),
								$('<td>').text(value.unitcode),
								$('<td>').text(value.unitpriceperunit),
								$('<td>').text(value.currencycode),
								$('<td>').text(value.status),
								$('<td>').text(value.remark),
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAccessoryPrice');
						
					}
					if(valueSelected=="All" && valueGroupSelect == value.accessorygroupcode  ){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.name),
								$('<td>').text(value.mode),
								$('<td>').text(value.accessorysuppliername),
								$('<td>').text(formatformdate),		
								$('<td>').text(formattodate),
								$('<td>').text(value.unitcode),
								$('<td>').text(value.unitpriceperunit),
								$('<td>').text(value.currencycode),
								$('<td>').text(value.status),
								$('<td>').text(value.remark),
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAccessoryPrice');
					}
					if(valueSelected==value.status && valueGroupSelect ==value.accessorygroupcode  ){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.name),
								$('<td>').text(value.mode),
								$('<td>').text(value.accessorysuppliername),
								$('<td>').text(formatformdate),		
								$('<td>').text(formattodate),
								$('<td>').text(value.unitcode),
								$('<td>').text(value.unitpriceperunit),
								$('<td>').text(value.currencycode),
								$('<td>').text(value.status),
								$('<td>').text(value.remark),
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAccessoryPrice');
						
					}
				});
				action();

				$('#listAccessoryPrice').DataTable( {
					"pagingType": "full_numbers",
					"scrollX": true
			    } );
			},
			error: function(){
				alert("Can not get data!");
			}
		});
	});
	
	function reloadTableWithStatus(){
		//lấy ra value đc chọn
		var optionSelected = $('#ddlStatus').find("option:selected");
		var valueSelected  = optionSelected.val();
		
		//
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "accessoryprice/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listAccessoryPrice").dataTable().fnDestroy();
				$('#listAccessoryPrice tbody').empty();
				
				var i = 1;
				var valueGroupSelect = getddlGroupValue();
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){					
					var fromyear = value.fromdate.substring(0, 4);
					var frommonth = value.fromdate.substring(5, 7);
					var fromday = value.fromdate.substring(8,10);														
					var formatformdate = fromday + "-" + frommonth + "-" + fromyear;
					
					if(value.todate!=null)
					{
					var toyear = value.todate.substring(0, 4);
					var tomonth = value.todate.substring(5, 7);
					var today = value.todate.substring(8,10);														
					var formattodate = today + "-" + tomonth + "-" + toyear;
					}
					
					
					//nếu value là all, 
					if(valueSelected=='All' && valueGroupSelect =="All"  ){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.name),
								$('<td>').text(value.mode),
								$('<td>').text(value.accessorysuppliername),
								$('<td>').text(formatformdate),		
								$('<td>').text(formattodate),
								$('<td>').text(value.unitcode),
								$('<td>').text(value.unitpriceperunit),
								$('<td>').text(value.currencycode),
								$('<td>').text(value.status),
								$('<td>').text(value.remark),
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAccessoryPrice');
					}
					if(valueSelected==value.status && valueGroupSelect =="All"  ){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.name),
								$('<td>').text(value.mode),
								$('<td>').text(value.accessorysuppliername),
								$('<td>').text(formatformdate),		
								$('<td>').text(formattodate),
								$('<td>').text(value.unitcode),
								$('<td>').text(value.unitpriceperunit),
								$('<td>').text(value.currencycode),
								$('<td>').text(value.status),
								$('<td>').text(value.remark),
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAccessoryPrice');
						
					}
					if(valueSelected=="All" && valueGroupSelect == value.accessorygroupcode  ){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.name),
								$('<td>').text(value.mode),
								$('<td>').text(value.accessorysuppliername),
								$('<td>').text(formatformdate),		
								$('<td>').text(formattodate),
								$('<td>').text(value.unitcode),
								$('<td>').text(value.unitpriceperunit),
								$('<td>').text(value.currencycode),
								$('<td>').text(value.status),
								$('<td>').text(value.remark),
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAccessoryPrice');
					}
					if(valueSelected==value.status && valueGroupSelect ==value.accessorygroupcode  ){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.name),
								$('<td>').text(value.mode),
								$('<td>').text(value.accessorysuppliername),
								$('<td>').text(formatformdate),		
								$('<td>').text(formattodate),
								$('<td>').text(value.unitcode),
								$('<td>').text(value.unitpriceperunit),
								$('<td>').text(value.currencycode),
								$('<td>').text(value.status),
								$('<td>').text(value.remark),
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAccessoryPrice');
						
					}
				});
				action();

				$('#listAccessoryPrice').DataTable( {
					"pagingType": "full_numbers",
					"scrollX": true
			    } );
			},
			error: function(){
				alert("Can not get data!");
			}
		});
	};
	
	//event when choose accessory group on dropdownbox
	$('#sltAccessoryGroup').on('change', function (e) {
		var optionSelected = $(this).find("option:selected");
		var valueSelected  = optionSelected.val();
		
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "accessoryprice/list" ,
			contentType: "application/json",
			success: function(data){
				$("#listAccessoryPrice").dataTable().fnDestroy();
				$('#listAccessoryPrice tbody').empty();
				var i = 1;
				var valueStatusSelect = getddlStatusValue();
				if(data.list.length==0){
//					alert("Table have no data.");
				}
				else{
				$.each(data.list,function(key,value){
					var fromyear = value.fromdate.substring(0, 4);
					var frommonth = value.fromdate.substring(5, 7);
					var fromday = value.fromdate.substring(8,10);														
					var formatformdate = fromday + "-" + frommonth + "-" + fromyear;
					
					if(value.todate!=null)
					{
					var toyear = value.todate.substring(0, 4);
					var tomonth = value.todate.substring(5, 7);
					var today = value.todate.substring(8,10);														
					var formattodate = today + "-" + tomonth + "-" + toyear;
					}
					if(valueSelected=='All' && valueStatusSelect=='All'){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.name),
								$('<td>').text(value.mode),
								$('<td>').text(value.accessorysuppliername),
								$('<td>').text(formatformdate),		
								$('<td>').text(formattodate),
								$('<td>').text(value.unitcode),
								$('<td>').text(value.unitpriceperunit),
								$('<td>').text(value.currencycode),
								$('<td>').text(value.status),
								$('<td>').text(value.remark),
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAccessoryPrice');
						
						
					}
						
					if(valueSelected==value.accessorygroupcode && valueStatusSelect=="All"  ){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.name),
								$('<td>').text(value.mode),
								$('<td>').text(value.accessorysuppliername),
								$('<td>').text(formatformdate),		
								$('<td>').text(formattodate),
								$('<td>').text(value.unitcode),
								$('<td>').text(value.unitpriceperunit),
								$('<td>').text(value.currencycode),
								$('<td>').text(value.status),
								$('<td>').text(value.remark),
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAccessoryPrice');
						
					}
					if(valueSelected=="All" && valueStatusSelect ==value.status  ){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.name),
								$('<td>').text(value.mode),
								$('<td>').text(value.accessorysuppliername),
								$('<td>').text(formatformdate),		
								$('<td>').text(formattodate),
								$('<td>').text(value.unitcode),
								$('<td>').text(value.unitpriceperunit),
								$('<td>').text(value.currencycode),
								$('<td>').text(value.status),
								$('<td>').text(value.remark),
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAccessoryPrice');
					}
					if(valueSelected==value.accessorygroupcode && valueStatusSelect ==value.status  ){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.name),
								$('<td>').text(value.mode),
								$('<td>').text(value.accessorysuppliername),
								$('<td>').text(formatformdate),		
								$('<td>').text(formattodate),
								$('<td>').text(value.unitcode),
								$('<td>').text(value.unitpriceperunit),
								$('<td>').text(value.currencycode),
								$('<td>').text(value.status),
								$('<td>').text(value.remark),
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAccessoryPrice');
						
					}
								
				});
				}				
				action();
				$('#listAccessoryPrice').DataTable( {
					"pagingType": "full_numbers",
					"scrollX": true
			    } );
			},
			error: function(){
				alert("Can not get data!");
			}
		});
	});
	
	// when click Search button
	function searchByFromTo(){
		$('#btnSearch').click(function(){
			if(!isValidationInputFromDate())
				callMessageDialog("Message","Select from date");
			else{
				$("#listAccessoryPrice").dataTable().fnDestroy();
			    $('#listAccessoryPrice tbody').empty();
				var from = $("#txtSearchFrom").datepicker('getDate');
				var to = $("#txtSearchTo").datepicker('getDate');
				var parseFrom = ($.datepicker.formatDate('yy-mm-dd', from));
				var parseTo = ($.datepicker.formatDate('yy-mm-dd', to));
				if(to==null || to =='' )  {
					var parseTo = ($.datepicker.formatDate('yy-mm-dd', new Date()));
				}
				var valueSelected=getddlStatusValue();
				var valueGroupSelected=getddlGroupValue();
				$.ajax({
					dataType: "json",
					type: 'GET',
					data: {},
					url: getAbsolutePath() +  "accessoryprice/list",
					contentType: "application/json",
					success: function(data){
						var i = 1;
						$.each(data.list,function(key, value) {
							var fromdate = value.fromdate;
							var todate = value.todate;
							if( valueGroupSelected=="All" && valueSelected=='All' && Date.parse(fromdate) >= Date.parse(parseFrom) && Date.parse(todate) <= Date.parse(parseTo)){
								var fromyear = value.fromdate.substring(0, 4);
								var frommonth = value.fromdate.substring(5, 7);
								var fromday = value.fromdate.substring(8,10);														
								var formatformdate = fromday + "-" + frommonth + "-" + fromyear;
								
								if(value.todate!=null)
								{
								var toyear = value.todate.substring(0, 4);
								var tomonth = value.todate.substring(5, 7);
								var today = value.todate.substring(8,10);														
								var formattodate = today + "-" + tomonth + "-" + toyear;
								}
								$('<tr>').append(
										$('<td>').text(i++),
										$('<td>').text(value.name),
										$('<td>').text(value.mode),
										$('<td>').text(value.accessorysuppliername),
										$('<td>').text(formatformdate),		
										$('<td>').text(formattodate),
										$('<td>').text(value.unitcode),
										$('<td>').text(value.unitpriceperunit),
										$('<td>').text(value.currencycode),
										$('<td>').text(value.status),
										$('<td>').text(value.remark),
										$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
												+'<option value="Options" disabled selected>Options</option>'
												+'<option value="Edit">Edit</option>'
												+'<option value="Delete">Delete</option></select>')
									).appendTo('#listAccessoryPrice');
								action();
							}
							if( valueGroupSelected=="All" && valueSelected==value.status && Date.parse(fromdate) >= Date.parse(parseFrom) && Date.parse(todate) <= Date.parse(parseTo)){
								var fromyear = value.fromdate.substring(0, 4);
								var frommonth = value.fromdate.substring(5, 7);
								var fromday = value.fromdate.substring(8,10);														
								var formatformdate = fromday + "-" + frommonth + "-" + fromyear;
								
								if(value.todate!=null)
								{
								var toyear = value.todate.substring(0, 4);
								var tomonth = value.todate.substring(5, 7);
								var today = value.todate.substring(8,10);														
								var formattodate = today + "-" + tomonth + "-" + toyear;
								}
								$('<tr>').append(
										$('<td>').text(i++),
										$('<td>').text(value.name),
										$('<td>').text(value.mode),
										$('<td>').text(value.accessorysuppliername),
										$('<td>').text(formatformdate),		
										$('<td>').text(formattodate),
										$('<td>').text(value.unitcode),
										$('<td>').text(value.unitpriceperunit),
										$('<td>').text(value.currencycode),
										$('<td>').text(value.status),
										$('<td>').text(value.remark),
										$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
												+'<option value="Options" disabled selected>Options</option>'
												+'<option value="Edit">Edit</option>'
												+'<option value="Delete">Delete</option></select>')
									).appendTo('#listAccessoryPrice');
								action();
							} 
							if( valueGroupSelected==value.accessorygroupcode && valueSelected=="All" && Date.parse(fromdate) >= Date.parse(parseFrom) && Date.parse(todate) <= Date.parse(parseTo)){
								var fromyear = value.fromdate.substring(0, 4);
								var frommonth = value.fromdate.substring(5, 7);
								var fromday = value.fromdate.substring(8,10);														
								var formatformdate = fromday + "-" + frommonth + "-" + fromyear;
								
								if(value.todate!=null)
								{
								var toyear = value.todate.substring(0, 4);
								var tomonth = value.todate.substring(5, 7);
								var today = value.todate.substring(8,10);														
								var formattodate = today + "-" + tomonth + "-" + toyear;
								}
								$('<tr>').append(
										$('<td>').text(i++),
										$('<td>').text(value.name),
										$('<td>').text(value.mode),
										$('<td>').text(value.accessorysuppliername),
										$('<td>').text(formatformdate),		
										$('<td>').text(formattodate),
										$('<td>').text(value.unitcode),
										$('<td>').text(value.unitpriceperunit),
										$('<td>').text(value.currencycode),
										$('<td>').text(value.status),
										$('<td>').text(value.remark),
										$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
												+'<option value="Options" disabled selected>Options</option>'
												+'<option value="Edit">Edit</option>'
												+'<option value="Delete">Delete</option></select>')
									).appendTo('#listAccessoryPrice');
								action();
							} 
							if( valueGroupSelected==value.accessorygroupcode && valueSelected==value.status && Date.parse(fromdate) >= Date.parse(parseFrom) && Date.parse(todate) <= Date.parse(parseTo)){
								var fromyear = value.fromdate.substring(0, 4);
								var frommonth = value.fromdate.substring(5, 7);
								var fromday = value.fromdate.substring(8,10);														
								var formatformdate = fromday + "-" + frommonth + "-" + fromyear;
								
								if(value.todate!=null)
								{
								var toyear = value.todate.substring(0, 4);
								var tomonth = value.todate.substring(5, 7);
								var today = value.todate.substring(8,10);														
								var formattodate = today + "-" + tomonth + "-" + toyear;
								}
								$('<tr>').append(
										$('<td>').text(i++),
										$('<td>').text(value.name),
										$('<td>').text(value.mode),
										$('<td>').text(value.accessorysuppliername),
										$('<td>').text(formatformdate),		
										$('<td>').text(formattodate),
										$('<td>').text(value.unitcode),
										$('<td>').text(value.unitpriceperunit),
										$('<td>').text(value.currencycode),
										$('<td>').text(value.status),
										$('<td>').text(value.remark),
										$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorypricecode+'">'
												+'<option value="Options" disabled selected>Options</option>'
												+'<option value="Edit">Edit</option>'
												+'<option value="Delete">Delete</option></select>')
									).appendTo('#listAccessoryPrice');
								action();
							} 
						});
						$('#listAccessoryPrice').DataTable( {
							"pagingType": "full_numbers",
							"scrollX": true
					    });
					}
				});
//			});
				
			}	
		});
	};
	//------------------------VALIDATION-------------------------
	
	/**
	 * This function OverRange to validate if data over range
	 */
	
	function isValidationOverRangeWhenAddRemark(){
		
		var remarkLength = $("#dialogAddAccessoryPrice").find("#areaRemark").val().length;
		if(remarkLength>200)
			return false;
		return true;
	}
	
	/**
	 * This function OverRange to validate if data over range
	 */
	
	function isValidationOverRangeWhenAddUnitPrice(){
		
		var unitPriceLength = $("#dialogAddAccessoryPrice").find("#txtUnitPrice").val().length;
		if(unitPriceLength>8)
			return false;
		return true;
	}
	/**
	 * This function overRange to validate if data over range Edit
	 */
	function isValidationOverRangeWhenEditRemark(){
		
		var remarkLength = $("#dialogEditAccessoryPrice").find("#areaRemark").val().length;
		if(remarkLength>200)
			return false;
		return true;
	}
	
	/**
	 * This function overRange to validate if data over range Edit
	 */
	function isValidationOverRangeWhenEditUnitPrice(){
		
		var unitPriceLength = $("#dialogEditAccessoryPrice").find("#txtUnitPrice").val().length;
		if(unitPriceLength>8)
			return false;
		return true;
	}
	
	
	//validation fromdate to search
	function isValidationInputFromDate(){
		var dateFrom = $("#txtSearchFrom").val();
		if(dateFrom == null || dateFrom.trim() == '')
			return false;
		return true;
	}
	
	//validation fromdate todate to add
	function isValidationInputFromToDateAdd(){
		var dateFrom = $("#dialogAddAccessoryPrice").find("#txtFromDate").val();
		if(dateFrom == null || dateFrom.trim() === ''){
			return false;
		}
		return true;
	}
	
//	function isCheckExistCurrencyWhenAdd(){
//	
//		var currencycode = $('#dialogAddAccessoryPrice').find('#txtCurrencyCode').val();
//
//		$.ajax({
//			dataType: "json",
//			type: 'GET',
//			data: {},
//			url: getAbsolutePath() +  "currency/list",
//			contentType: "application/json",
//			success: function(data){
//				$.each(data.list,function(key,value){
//					$.ajax({
//						dataType: "json",
//						type: 'GET',
//						data:{},
//						contentType: "application/json",
//						url: getAbsolutePath() +  "currency/isExist/"+value.currencycode,
//						success: function(data2){
//							if(currencycode = data2.currency.currencycode){
//								return true;
//							}
//							else
//								return false;
//						},
//						
//					});
//				
//				});
//			}
//		});
//	}
	
	
	//validation fromdate todate to edit
	function isValidationInputFromToDateEdit(){
		var dateFrom = $("#dialogEditAccessoryPrice").find("#txtFromDateEdit").val();
		if(dateFrom == null || dateFrom.trim() === ''){
			return false;
		}
		
		return true;
	}
	/**
	 * Allow input numeric only in Add form
	 */
	$("#dialogAddAccessoryPrice").on('keydown', '#txtUnitPrice' ,
			function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
		||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
		||35<=e.keyCode&&40>=e.keyCode
		||(e.shiftKey||48>e.keyCode||57<e.keyCode)
		&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
	
	/**
	 * Allow input numeric only in Edit form
	 */
	$("#dialogEditAccessoryPrice").on('keydown', '#txtUnitPrice' ,
			function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
		||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
		||35<=e.keyCode&&40>=e.keyCode
		||(e.shiftKey||48>e.keyCode||57<e.keyCode)
		&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
	
	/**
	 * Error  overrange areaRemark AddAccessoryPrice
	 */
	$("#dialogAddAccessoryPrice").find("#areaRemark").keyup(function(){
		var remark= $("#dialogAddAccessoryPrice").find("#areaRemark").val();
		//error  overrange areaRemark
		if(remark.length>200){
			$("#dialogAddAccessoryPrice").find("#errorAreaRemark").text("Your input is over range");
			$("#dialogAddAccessoryPrice").find("#errorAreaRemark").css("color", "red");
			$("#dialogAddAccessoryPrice").find("#areaRemark").css("border-left", "red 5px solid");
		}else{
			$("#dialogAddAccessoryPrice").find("#errorAreaRemark").text("");
			$("#dialogAddAccessoryPrice").find("#areaRemark").css("border-left", "blue 5px solid");
			
		}
	});
	
	/**
	 * Error  overrange UnitPrice AddAccessoryPrice
	 */
	$("#dialogAddAccessoryPrice").find("#txtUnitPrice").keyup(function(){
		var unitPrice= $("#dialogAddAccessoryPrice").find("#txtUnitPrice").val();
		//error  overrange areaRemark
		if(unitPrice.length>8){
			$("#dialogAddAccessoryPrice").find("#errorUnitPrice").text("Your input is over range");
			$("#dialogAddAccessoryPrice").find("#errorUnitPrice").css("color", "red");
			$("#dialogAddAccessoryPrice").find("#txtUnitPrice").css("border-left", "red 5px solid");
		}else{
			$("#dialogAddAccessoryPrice").find("#errorUnitPrice").text("");
			$("#dialogAddAccessoryPrice").find("#txtUnitPrice").css("border-left", "blue 5px solid");
			
		}
	});
	
	/**
	 * Error  overrange areaRemark EditAccessoryPrice
	 */
	$("#dialogEditAccessoryPrice").find("#areaRemark").keyup(function(){
		var remark= $("#dialogEditAccessoryPrice").find("#areaRemark").val();
		//error  overrange areaRemark
		if(remark.length>200){
			$("#dialogEditAccessoryPrice").find("#errorAreaRemark").text("Your input is over range");
			$("#dialogEditAccessoryPrice").find("#errorAreaRemark").css("color", "red");
			$("#dialogEditAccessoryPrice").find("#areaRemark").css("border-left", "red 5px solid");
		}else{
			$("#dialogEditAccessoryPrice").find("#errorAreaRemark").text("");
			$("#dialogEditAccessoryPrice").find("#areaRemark").css("border-left", "blue 5px solid");
		}
	});
	
	/**
	 * Error  overrange UnitPrice EditAccessoryPrice
	 */
	$("#dialogEditAccessoryPrice").find("#txtUnitPrice").keyup(function(){
		var unitPrice= $("#dialogEditAccessoryPrice").find("#txtUnitPrice").val();
		//error  overrange areaRemark
		if(unitPrice.length>8){
			$("#dialogEditAccessoryPrice").find("#errorUnitPrice").text("Your input is over range");
			$("#dialogEditAccessoryPrice").find("#errorUnitPrice").css("color", "red");
			$("#dialogEditAccessoryPrice").find("#txtUnitPrice").css("border-left", "red 5px solid");
		}else{
			$("#dialogEditAccessoryPrice").find("#errorUnitPrice").text("");
			$("#dialogEditAccessoryPrice").find("#txtUnitPrice").css("border-left", "blue 5px solid");
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
	};
	
	function addSuccessMessageDialog(){
		var title = $("#SuccessTitle").text();
		$("#AddSuccessDialog").dialog({
			modal: true,
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
			modal: true,
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
	
	function editSuccessMessageDialog(){
		var title = $("#SuccessTitle").text();
		$("#EditSuccessDialog").dialog({
			modal: true,
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
	
	function editFailedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#EditFailedDialog").dialog({
			modal: true,
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
	function deleteSuccessMessageDialog(){
		var title = $("#SuccessTitle").text();
		$("#DeleteSuccessDialog").dialog({
			modal: true,
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
	
	function deleteFailedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#DeleteFailedDialog").dialog({
			modal: true,
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
	
	function CanNotGetDataDialogMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#CanNotGetDataDialog").dialog({
			modal: true,
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
	
	function IsExistedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#IsExistedDialog").dialog({
			modal: true,
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
	
	loadData();
	loadDropDownAccessory();
	loadDropDownAccessorySupplier();
	loadDropDownCurrency();
	searchByFromTo();
	loadDropDownAccGroup();
	//sltAccGroupCode_Change();
});