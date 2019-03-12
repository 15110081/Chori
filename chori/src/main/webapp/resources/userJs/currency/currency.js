$(document).ready(function(){
	/**
	 * This function is used to load data into table
	 */
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "currency/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.currencycode),
							$('<td>').text(value.name),
//							$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.currencycode+'">Edit</button>'),
//							$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.currencycode+'">Delete</button>')
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.currencycode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
					).appendTo('#listCurrency');
				});
				action();

				$('#listCurrency').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	
//	function action(){
//		//hàm cho nút edit list factory
//		$('.btnEdit').click(function(){
//			
//			//lấy ra data-id
//			var currencyCode= $(this).data('id');
//			
//			//lấy thông tin qua id gán vào edit dialog
//			$.ajax({
//				dataType: "json",
//				type: 'GET',
//				data: {},
//				url: "/Chori/currency/detail/"+currencyCode,
//				contentType: "application/json",
//				success: function(data){
//					//gán các giá trị vào dialog
//					$('#editCurrencyDialog').find('#txtCurrencyCode').val(data.currency.currencycode);
//					$('#editCurrencyDialog').find('#txtDescription').val(data.currency.name);
//				},
//				error: function(){
//					alert("Lỗi lấy by id")
//				}
//			});
//			//end lấy thông tin qua id gán vào edit dialog
//			
//			$("#editCurrencyDialog").dialog({
//				show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Edit Currency",
//				height: 500,
//				width: 700,
//				buttons:{
//					"Edit": function(){
//							//if user have entered all required fields, then check if input is overRange
//							if(!validateOverRangeWhenEdit()){
//								callMessageDialog("Warning message", "Your input is over range!");
//							}else{
//								//all inputs are valid here.
//								var currencycode = $('#editCurrencyDialog').find('#txtCurrencyCode').val();
//								var name = $('#editCurrencyDialog').find('#txtDescription').val();
//								
//								var currency = {
//										currencycode: currencycode,
//										name: name,
//								};
//								
//								console.log(JSON.stringify(currency));
//								//gọi ajax để add
//								$.ajax({
//									dataType: "json",
//									type: 'POST',
//									data: JSON.stringify(currency),
//									contentType: "application/json",
//									url: "/Chori/currency/edit",//làm đến đây
//									success: function(data){
//										if(data.editStatus==true){
//											$("#listCurrency").dataTable().fnDestroy();
//											$('#listCurrency tbody').empty();
//											loadData();
//											callMessageDialog("Message", 'Edit currency successfully!');
//											$("#editCurrencyDialog").dialog("close");
//											resetAfterEdit();
//										}
//									},
//									error: function(){
//										alert("Lỗi khi gọi ajax để edit currency");
//									}
//								});
//							}
//					},
//					"Cancel": function(){
//						$("#editCurrencyDialog").dialog("close");
//						//Xóa các thông tin trong bảng contact
//						resetAfterEdit();
//					}
//				},
//				close: function(){
//					resetAfterEdit();
//				}
//			});
//		});
//		
//		$('.btnDelete').click(function(){
//			var currencyCode= $(this).data('id');
//			$("#deleteCurrencyDialog").find("#messageContent").text('Are you sure you want to delete currency "' + currencyCode+'"?');
//			
//			$("#deleteCurrencyDialog").dialog({
//	    		show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Delete Currency Confirm",
//				height: 200,
//				width: 400,
//				buttons:{
//					"OK": function(){
//						$.ajax({
//							dataType: "json",
//							type: 'POST',
//							data:{},
//							contentType: "application/json",
//							url: "/Chori/currency/delete/" + currencyCode,
//							success: function(data){
//								if(data.status=="ok"){
//									if(data.deleteStatus== true){
//										//show delete status to user
//										callMessageDialog("Message", 'Delete currency "'+ currencyCode+ '" successfully!');
//										//close dialog
//										$("#deleteCurrencyDialog").dialog("close");
//										//reload table
//										$("#listCurrency").dataTable().fnDestroy();
//										$('#listCurrency tbody').empty();
//										loadData();
//									}else if(data.deleteStatus== false){
//										callMessageDialog("Message", 'Can\'t delete currency "'+ currencyCode+ '"!');
//										$("#deleteCurrencyDialog").dialog("close");
//									}else{
//										alert("this cant be show, 243!");
//									}
//								}else{
//									alert("unexpected error! (2), 404");
//								}
//							},
//							error: function(){
//								alert("unexpected error!");
//							}
//						});
//					},
//					"Cancel": function(){
//						$("#deleteCurrencyDialog").dialog("close");
//					}
//				}
//			});
//		});
//	}
	
	//chuyển hàm action qua dropdownlist
	function action(){
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		  //lấy ra data-id
			var currencyCode= $(this).data('id');
		    //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $(".selectOption").val("Options");
		    
		    
		    //If user choose edit option
		    if(valueSelected=="Edit"){
				
				//lấy thông tin qua id gán vào edit dialog
				$.ajax({
					dataType: "json",
					type: 'POST',
					data: JSON.stringify({
						currencycode: currencyCode
					}),
					url: getAbsolutePath() +  "currency/detail",
					contentType: "application/json",
					success: function(data){
						//gán các giá trị vào dialog
						$('#editCurrencyDialog').find('#txtCurrencyCode').val(data.currency.currencycode);
						$('#editCurrencyDialog').find('#txtDescription').val(data.currency.name);
					},
					error: function(){
						alert("Lỗi lấy by id")
					}
				});
				//end lấy thông tin qua id gán vào edit dialog
				
				$("#editCurrencyDialog").dialog({
					modal: true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Edit Currency",
					height: 300,
					width: 300,
					buttons:{
						"Edit": function(){
								//if user have entered all required fields, then check if input is overRange
								if(!validateOverRangeWhenEdit()){
									callMessageDialog("Warning message", "Your input is over range!");
								}else{
									//all inputs are valid here.
									var currencycode = $('#editCurrencyDialog').find('#txtCurrencyCode').val();
									var name = $('#editCurrencyDialog').find('#txtDescription').val();
									
									var currency = {
											currencycode: currencycode,
											name: name,
									};
									
									console.log(JSON.stringify(currency));
									//gọi ajax để add
									$.ajax({
										dataType: "json",
										type: 'POST',
										data: JSON.stringify(currency),
										contentType: "application/json",
										url: getAbsolutePath() +  "currency/edit",//làm đến đây
										success: function(data){
											if(data.editStatus==true){
												$("#listCurrency").dataTable().fnDestroy();
												$('#listCurrency tbody').empty();
												loadData();
												callMessageDialog("Message", 'Edit currency successfully!');
												$("#editCurrencyDialog").dialog("close");
												resetAfterEdit();
											}
										},
										error: function(){
											alert("Lỗi khi gọi ajax để edit currency");
										}
									});
								}
						},
						"Cancel": function(){
							$("#editCurrencyDialog").dialog("close");
							//Xóa các thông tin trong bảng contact
							resetAfterEdit();
						}
					},
					close: function(){
						resetAfterEdit();
					}
				});
		    };
		    
		    //if user choose delete option
		    if(valueSelected=="Delete"){
		    	$("#deleteCurrencyDialog").find("#messageContent").text('Are you sure you want to delete currency "' + currencyCode + '"?');
				
				$("#deleteCurrencyDialog").dialog({
					modal: true,
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Delete Currency Confirm",
					height: 200,
					width: 400,
					buttons:{
						"OK": function(){
							$.ajax({
								dataType: "json",
								type: 'POST',
								data: JSON.stringify({
									currencycode: currencyCode
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "currency/delete",
								success: function(data){
									if(data.status=="ok"){
										if(data.deleteStatus== true){
											//show delete status to user
											callMessageDialog("Message", 'Delete currency "'+ currencyCode+ '" successfully!');
											//close dialog
											$("#deleteCurrencyDialog").dialog("close");
											//reload table
											$("#listCurrency").dataTable().fnDestroy();
											$('#listCurrency tbody').empty();
											loadData();
										}else if(data.deleteStatus== false){
											callMessageDialog("Message", 'Can\'t delete currency "'+ currencyCode+ '"!');
											$("#deleteCurrencyDialog").dialog("close");
										}else{
											alert("this cant be show, 243!");
										}
									}else{
										alert("unexpected error! (2), 404");
									}
								},
								error: function(){
//									alert("unexpected error!");
									callMessageDialog("Message", 'Can\'t delete currency "'+ currencyCode+ '"!');
									$("#deleteCurrencyDialog").dialog("close");
								}
							});
						},
						"Cancel": function(){
							$("#deleteCurrencyDialog").dialog("close");
						}
					}
				});
		    };
		    //end if user choose delete option
		    
		});
	};
	//end chuyển action qua dropdownlist
	
	/**
	 * This function is used to open add currency dialog
	 */
	$('#btnAddNewCurrency').click(function(){
		$("#addCurrencyDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Add New Currency",
			height: 300,
			width: 300,
			buttons:{
				"Save": function(){
					var currencyCodeInput = $('#addCurrencyDialog').find('#txtCurrencyCode').val();
					//check if user have entered all required fields
					if(!validateRequiredFieldForAdd()){
						callMessageDialog("Warning message", "Please enter required fields!");
						
						//get value of code and name
						var currencyCode = $('#addCurrencyDialog').find('#txtCurrencyCode').val();
						
						//if user dont enter code or enter just space
						if(currencyCode.trim() === '' || currencyCode == null){
							$("#addCurrencyDialog").find("#errorTxtCurrencyCode").text("Please enter currency code!");
							$("#addCurrencyDialog").find("#errorTxtCurrencyCode").css("color", "red");
							$("#addCurrencyDialog").find("#txtCurrencyCode").css("background-color", "red");
						}
					}else{
						//if user have entered all required fields, then check if input is overRange
						if(!validateOverRangeWhenAdd()){
							callMessageDialog("Warning message", "Your input is over range!");
							//return false;
						}else{//check if the code is not existed
							$.ajax({
								dataType: "json",
								type: 'POST',
								data: JSON.stringify({
									currencycode: currencyCodeInput
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "currency/isExist",
								success: function(data){
									if(data.isExisted==true){
										//if code is existed
										$("#addCurrencyDialog").find("#errorTxtCurrencyCode").text("Currency code you enter is existed, please choose different one!");
										$("#addCurrencyDialog").find("#errorTxtCurrencyCode").css("color", "red");
										$("#addCurrencyDialog").find("#txtCurrencyCode").css("background-color", "red");
										callMessageDialog("Warning message", "Currency code you enter is existed, please choose different one!");
									}else if(data.isExisted==false){
										//alert("all input is ok!");
//										$("#addCurrencyDialog").find("#errorTxtCurrencyCode").text("Currency code is valid!");
//										$("#addCurrencyDialog").find("#errorTxtCurrencyCode").css("color", "green");
//										$("#addCurrencyDialog").find("#txtCurrencyCode").css("background-color", "white");
										
										//add new currency here
										//main code when input is already valid
										var currencycode = $('#addCurrencyDialog').find('#txtCurrencyCode').val();
										var name = $('#addCurrencyDialog').find('#txtDescription').val();
										
										var currency = {
												currencycode: currencycode,
												name: name,
										};
										
										console.log(JSON.stringify(currency));
										//gọi ajax để add
										$.ajax({
											dataType: "json",
											type: 'POST',
											data: JSON.stringify(currency),
											contentType: "application/json",
											url: getAbsolutePath() +  "currency/add",//làm đến đây
											success: function(data){
												if(data.addStatus==true){
													$("#listCurrency").dataTable().fnDestroy();
													$('#listCurrency tbody').empty();
													loadData();
													callMessageDialog("Message", 'Add new currency successfully!');
													$("#addCurrencyDialog").dialog("close");
													resetAfterAdd();
												}
											},
											error: function(){
												alert("Lỗi khi gọi ajax để add currency");
											}
										});
										//end main code when input is already valid
									}
								},
								error: function(){
									//alert("Lỗi cmnr");
									//here user delete code
								}
							});
						}
					}
				},
				"Cancel": function(){
					$("#addCurrencyDialog").dialog("close");
					resetAfterAdd();
				}
			},
			close: function(){
				resetAfterAdd();
			}
		});
	});
	
	//function reset css for dialog after add
	function resetAfterAdd(){
		$('#addCurrencyDialog').find('#txtCurrencyCode').val('');
		$('#addCurrencyDialog').find('#txtDescription').val('');
		
		//reset css
		$("#addCurrencyDialog").find("#errorTxtCurrencyCode").text("");
		$("#addCurrencyDialog").find("#txtCurrencyCode").css("background-color", "white");
		$("#addCurrencyDialog").find("#errorTxtDescription").text("");
		$("#addCurrencyDialog").find("#txtDescription").css("background-color", "white");
	}
	//end function reset css for dialog after add
	
	//function reset css for dialog after edit
	function resetAfterEdit(){
		$('#editCurrencyDialog').find('#txtCurrencyCode').val('');
		$('#editCurrencyDialog').find('#txtDescription').val('');
		
		//reset css
		$("#editCurrencyDialog").find("#errorTxtCurrencyCode").text("");
		$("#editCurrencyDialog").find("#txtCurrencyCode").css("background-color", "white");
		$("#editCurrencyDialog").find("#errorTxtDescription").text("");
		$("#editCurrencyDialog").find("#txtDescription").css("background-color", "white");
	}
	//end function reset css for dialog after edit
	
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
					$(this).dialog("close");
				}
			}
		});
	}
	
	/**
	 * -----------------------------------------
	 * 
	 */
	
	/**
	 * This function is used to validate required fields before adding
	 */
	function validateRequiredFieldForAdd(){
		var currencyCode= $("#addCurrencyDialog").find("#txtCurrencyCode").val();
		if(currencyCode.trim() === '' || currencyCode == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before add new currency
	 */
	function validateOverRangeWhenAdd(){
		if($("#addCurrencyDialog").find("#txtCurrencyCode").val().length>20 || $("#addCurrencyDialog").find("#txtDescription").val().length>50)
			return false;
		return true;
	}
	
	//do not allow input " character 
	$("#addCurrencyDialog").find("#txtCurrencyCode").keydown(function(e) {
		if(e.keyCode==222) return false;
	});
	
	/**
	 * currency code on keyup function() to check if user entered, over range, currency code is existed (3 cases)
	 */
	$("#addCurrencyDialog").find("#txtCurrencyCode").on('change keyup paste',function(){
		var currencyCode= $(this).val();
		
		//if user dont enter or enter just space
		if(currencyCode.trim() === '' || currencyCode == null){
			$("#addCurrencyDialog").find("#errorTxtCurrencyCode").text("Please enter currency code!");
			$("#addCurrencyDialog").find("#errorTxtCurrencyCode").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		if(currencyCode.length>20){
			$("#addCurrencyDialog").find("#errorTxtCurrencyCode").text("The code field's length is 20. Your input is over range!");
			$("#addCurrencyDialog").find("#errorTxtCurrencyCode").css("color", "red");
			$(this).css("background-color", "red");
		}
		
		//check if currency code is existed
		$.ajax({
			dataType: "json",
			type: 'POST',
			data: JSON.stringify({
				currencycode: currencyCode
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "currency/isExist",
			success: function(data){
				if(data.isExisted==true){// if code is existed, prevent user add new accessory
					//if code is existed
					$("#addCurrencyDialog").find("#errorTxtCurrencyCode").text("Currency code you enter is existed, please choose different one!");
					$("#addCurrencyDialog").find("#errorTxtCurrencyCode").css("color", "red");
					$("#addCurrencyDialog").find("#txtCurrencyCode").css("background-color", "red");
					
					if($("#addCurrencyDialog").find("#txtCurrencyCode").val().length!=$("#addCurrencyDialog").find("#txtCurrencyCode").val().trim().length){
						$("#addCurrencyDialog").find("#errorTxtCurrencyCode").text("You can't enter currency code with space on prefix or suffix!");
						$("#addCurrencyDialog").find("#errorTxtCurrencyCode").css("color", "red");
						$("#addCurrencyDialog").find("#txtCurrencyCode").css("background-color", "red");
					}
					
				}else if(data.isExisted==false&&$("#addCurrencyDialog").find("#txtCurrencyCode").val().length<21){
					//if code is not existed and valid length, don't have prefix, suffix space
					if($("#addCurrencyDialog").find("#txtCurrencyCode").val().length==$("#addCurrencyDialog").find("#txtCurrencyCode").val().trim().length){
						$("#addCurrencyDialog").find("#errorTxtCurrencyCode").text("Currency code is valid!");
						$("#addCurrencyDialog").find("#errorTxtCurrencyCode").css("color", "green");
						$("#addCurrencyDialog").find("#txtCurrencyCode").css("background-color", "white");
					}else{
						$("#addCurrencyDialog").find("#errorTxtCurrencyCode").text("You can't enter currency code with white space on prefix or suffix!");
						$("#addCurrencyDialog").find("#errorTxtCurrencyCode").css("color", "red");
						$("#addCurrencyDialog").find("#txtCurrencyCode").css("background-color", "red");
					}
					//if user dont enter or enter just space
					if(currencyCode.trim() === '' || currencyCode == null){
						$("#addCurrencyDialog").find("#errorTxtCurrencyCode").text("Please enter currency code!");
						$("#addCurrencyDialog").find("#errorTxtCurrencyCode").css("color", "red");
						$(this).css("background-color", "red");
					}
				}
			},
			error: function(){
				//when user clear the code
				$("#addCurrencyDialog").find("#errorTxtCurrencyCode").text("Please enter currency code!");
				$("#addCurrencyDialog").find("#errorTxtCurrencyCode").css("color", "red");
				$("#addCurrencyDialog").find("#txtCurrencyCode").css("background-color", "red");
			}
		});
	});
	
	/**
	 * currency description on keyup function() to check if over range (1 cases)
	 */
	$("#addCurrencyDialog").find("#txtDescription").on('change keyup paste',function(){
		var description= $(this).val();
		
		//if input of user is over range
		if(description.length>50){
			$("#addCurrencyDialog").find("#errorTxtDescription").text("The description field's length is 50. Your input is over range!");
			$("#addCurrencyDialog").find("#errorTxtDescription").css("color", "red");
			$(this).css("background-color", "red");
		}else if(description.length<21){//valid input
			$("#addCurrencyDialog").find("#errorTxtDescription").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * -------------------------------------
	 * Edit part:
	 */
	/**
	 * This function validate user input if it's over range before edit currency
	 */
	function validateOverRangeWhenEdit(){
		if($("#editCurrencyDialog").find("#txtDescription").val().length>50)
			return false;
		return true;
	}
	
	/**
	 * currency description on keyup function() to check if over range (1 cases)
	 */
	$("#editCurrencyDialog").find("#txtDescription").on('change keyup paste',function(){
		var description= $(this).val();
		
		//if input of user is over range
		if(description.length>50){
			$("#editCurrencyDialog").find("#errorTxtDescription").text("The description field's length is 50. Your input is over range!");
			$("#editCurrencyDialog").find("#errorTxtDescription").css("color", "red");
			$(this).css("background-color", "red");
		}else if(description.length<21){//valid input
			$("#editCurrencyDialog").find("#errorTxtDescription").text("");
			$(this).css("background-color", "white");
		}
	});
	
	//
	loadData();
})