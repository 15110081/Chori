$(document).ready(function() {
//load list data currencyexchange
					function loadData() {
						$.ajax({
									dataType : "json",
									type : 'GET',
									data : {},
									url : getAbsolutePath() +  "currencyexchange/list",
									contentType : "application/json",
									success : function(data) {
										var count = 1;
										if (data.list.length == 0) {
//											alert("Table have no data.");
										}
										var flag = true;
										//when click button search
										$('#btnSearch').click(function(){
											if (data.list.length == 0) {
//												alert("Table have no data.");
											}
											else if(!isValidationBeforeSearch())
												callMessageDialog("Message","Select day to search");
											else if(!isValidationSearchRange())
												callMessageDialog("Message","Date of From cant greater than Date of To");
											else {
											$("#listCurrencyExchange").dataTable().fnDestroy();
										    $('#listCurrencyExchange tbody').empty();
											var from = $("#searchFrom").datepicker('getDate');
											var to = $("#searchTo").datepicker('getDate');										
											var parseTo = Date.parse($.datepicker.formatDate('yy-mm-dd', to));
											var parseFrom = Date.parse($.datepicker.formatDate('yy-mm-dd', from));
											
											$.each(data.list,function(key, value) {
														var year = value.exchangedate.substring(0, 4);
														var month = value.exchangedate.substring(5, 7);
														var day = value.exchangedate.substring(8);														
														var date = day + "/" + month + "/" + year;
														var y = Date.parse(value.exchangedate);
//														var x = value.amount;
//														x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
														if(y <= parseTo && y >= parseFrom){
														$('<tr>')
																.append(
																		$('<td>').text(count++),
																		$('<td>').text(value.currencycodesource),
																		$('<td>').text(value.currencycodedestination),
																		$('<td>').text(value.amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")),
																		$('<td>').text(date),																			
																		$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.currencyexchangecode+'">'
																			+'<option value="Options" disabled selected>Options</option>'
																			+'<option value="Edit">Edit</option>'
																			+'<option value="Delete">Delete</option></select>'))
																.appendTo('#listCurrencyExchange');
														}
													});
											action();
										}
										});
										// load data list without button search
										if(flag==true){
										$.each(data.list,function(key, value) {
															var year = value.exchangedate.substring(0, 4);
															var month = value.exchangedate.substring(5, 7);
															var day = value.exchangedate.substring(8);														
															var date = day + "/" + month + "/" + year;
//															var str = value.amount;
//															str.split(".");
															$('<tr>')
																	.append(
																			$('<td>').text(count++),
																			$('<td>').text(value.currencycodesource),
																			$('<td>').text(value.currencycodedestination),
//																			$('<td>').text(str[1]),
																			$('<td>').text(value.amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")),
																			$('<td>').text(date),
																			$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.currencyexchangecode+'">'
																					+'<option value="Options" disabled selected>Options</option>'
																					+'<option value="Edit">Edit</option>'
																					+'<option value="Delete">Delete</option></select>'))
																	.appendTo('#listCurrencyExchange');
														});
									}
										else{}								
										action();
									},
									error : function() {
	//									alert("Can not get data!");
									}
								});
					};

					$("#listCurrencyExchange").find("#btnEdit").click(
							function(e) {
	//							alert("Can not get data!");

							});

					// bảng warning và user
					function action() {
						// When click button edit
						$('.selectOption').on('change', function (e) {
							var optionSelected = $(this).find("option:selected");
						    var valueSelected  = optionSelected.val();
						    var currencyexCode = $(this).data('id');
						    var date ="";
							$(".selectOption").val("Options");	
							// Select option = Delete
							 if(valueSelected=="Delete"){
								 var curId = $(this).data('id');
								 $.ajax({
							    		dataType: "json",
										type: 'GET',
										data:{},
										contentType: "application/json",
										url: getAbsolutePath() +  "currencyexchange/detail/"+curId,
										success: function(data){
											if(data.status== "ok"){
												var year = data.currencyexchange.exchangedate.substring(0, 4);
												var month = data.currencyexchange.exchangedate.substring(5, 7);
												var day = data.currencyexchange.exchangedate.substring(8);														
												date = day + "/" + month + "/" + year;
												$("#dialogDeleteCurrencyExchange").find("#messageContent").text('Are you sure you want to delete currency exchange "' + date +'"?');
												//alert(date);
												
											}else{
//												alert('This alert should never show!');
											}
										},
										error: function(){
//											alert('Cant get detail');
										}
							    	});								 
												//alert(date);
												$("#dialogDeleteCurrencyExchange")
														.dialog({show : {
																		effect : "slide",
																		duration : 500
																	},
																	title : "Delete Confirm",
																	height : 300,
																	width : 400,
																	buttons : {
																		"Yes" : function() {
																			$.ajax({
																						dataType : "json",
																						type : 'POST',
																						data : {},
																						contentType : "application/json",
																						url : getAbsolutePath() +  "currencyexchange/delete/"
																								+ curId,
																						success : function(
																								data) {
																							callMessageDialog(
																									"Message",
																									"Delete successfully!");
																							$(
																									"#listCurrencyExchange")
																									.dataTable()
																									.fnDestroy();
																							$(
																									'#listCurrencyExchange tbody')
																									.empty();
																							$("#dialogDeleteCurrencyExchange").dialog("close");
																							loadData();

																						},
																						error : function() {
																							callMessageDialog("Message", 'Can\'t Delete Accessory Supplier "'+ '"!');
																							$("#dialogDeleteCurrencyExchange").dialog("close");
																						}

																					});

																		},
																		"No" : function() {
																			$(
																					"#dialogDeleteCurrencyExchange")
																					.dialog(
																							"close");
																		}
																	}
																});
											};
							
							// Select option = Edit
							if(valueSelected=="Edit"){
								var temp ;
								clearDropdownbox();			
								$.ajax({				dataType : "json",
														type : 'GET',
														data : {},
														contentType : "application/json",
														url : getAbsolutePath() +  "currencyexchange/detail/" + currencyexCode,
														success : function(data) {
															if (data.status == "ok") {
																var year = data.currencyexchange.exchangedate.substring(0, 4);
																var month = data.currencyexchange.exchangedate.substring(5, 7);
																var day = data.currencyexchange.exchangedate.substring(8);														
																var date = day + "/" + month + "/" + year;
																$("#dialogEditCurrencyExchange").find("#txtCurrencyExchangeCode").val(data.currencyexchange.currencyexchangecode);
																$("#dialogEditCurrencyExchange").find("#txtCurrencyExchangeDate").val(date);
																$("#dialogEditCurrencyExchange").find("#txtFrom").val(data.currencyexchange.currencycodesource);
																$("#dialogEditCurrencyExchange").find("#txtTo").val(data.currencyexchange.currencycodedestination);
																temp = data.currencyexchange.currencycodedestination;
																$("#dialogEditCurrencyExchange").find("#txtAmount").val(data.currencyexchange.amount);
//																alert(data.currencyexchange.amount);
//																var stringAmount = data.currencyexchange.amount.replace("," , ".");
//																$("#dialogEditCurrencyExchange").find("#txtAmount").val(stringAmount);
															} else {
//																alert('This alert should never show!');
															}
														},
														error : function() {
															alert('Cant get detail');
														}
													});
											$("#listCurrencyExchange,#dialogEditCurrencyExchange").find("#errortxtCurrencyExchangeDate").text("");
											$("#listCurrencyExchange,#dialogEditCurrencyExchange").find("#txtCurrencyExchangeDate").css("border-color", "");

											$("#dialogEditCurrencyExchange").dialog({
																modal: true,
																show : {
																	effect : "blind",
																	duration : 500
																},
																title : "Edit Currency Exchange",
																height : 350,
																width : 600,
																buttons : {
																	"Edit" : function() {
																		var currencyCode = $("#dialogEditCurrencyExchange").find("#txtCurrencyExchangeCode").val();
																		var currencyexDate = $("#dialogEditCurrencyExchange").find("#txtCurrencyExchangeDate").datepicker('getDate');
																		var from = $("#dialogEditCurrencyExchange").find("#txtFrom").val();
																		var to = $("#dialogEditCurrencyExchange").find("#txtTo").val();
																		var amount = $("#dialogEditCurrencyExchange").find("#txtAmount").val();
																		var roundAmount = (Math.round(amount * 1000) / 1000).toFixed(3);
																		
																		if(amount<0 || amount == "" || amount == null)
																			{
																			callMessageDialog("Message","Please check amount value!");
																			}
																		else if (!isValidationRequiredFieldEdit()) {
																			callMessageDialog("Message","Please insert Amount!");
//																		} else if (isCurrencyExistEdit()){
//																			callMessageDialog("Message","Data already Exist!");
																		} else if (from == to){
																			callMessageDialog("Message","Currency From Must Not Same As To!");
																		} else if (!isValidationOverRangeWhenEdit()) {
																			callMessageDialog(
																					"Message","Amount Value only 20 characters long !");
																		} else if (amount < 0) {
																			callMessageDialog("Message","Amount must not below 0 !");
																		}
																		else {

																			$.ajax({
																						dataType : "json",
																						type : 'POST',
																						cache : true,
																						data : JSON
																								.stringify({
																									currencyexchangecode : currencyCode,
																									exchangedate : $.datepicker.formatDate('yy-mm-dd' , currencyexDate) ,
																									currencycodesource : from,
																									currencycodedestination : to,
																									amount : roundAmount
																								}),
																						contentType : "application/json",
																						url : getAbsolutePath() +  "currencyexchange/edit",
																						success : function(
																								data) {
																							callMessageDialog(
																									"Message",
																									"Edit Currency Exchange successfully!");

																							$("#listCurrencyExchange").dataTable().fnDestroy();
																							$('#listCurrencyExchange tbody').empty();
																							$("#dialogEditCurrencyExchange").dialog("close");
																							loadData();
																						},
																						error : function() {
	//																						alert('Cant get role detail!');
																						}
																					});
																		}
																	},
																	"Cancel" : function() {
																		$("#dialogEditCurrencyExchange").find("#txtTo").val('noneTo').prop("disabled", true);
																		$("#dialogEditCurrencyExchange").find("#txtTo").val(temp);
																		$("#dialogEditCurrencyExchange").dialog("close");		
																	}
																}
															});
						}
										});
						
					};
					//when click button add
					$('#btnAdd').on('click',
							function(e) {$("#dialogAddCurrencyExchange").find("#txtCurrencyExchangeDateAdd").val('');
								$("#dialogAddCurrencyExchange").find("#txtAmount").val('');
								$("#dialogAddCurrencyExchange").find("#txtFrom").val('noneFrom');
								$("#dialogAddCurrencyExchange").find("#txtTo").val('noneTo').prop("disabled", true);

								$("#listCurrencyExchange,#dialogAddCurrencyExchange").find("#errortxtCurrencyExchangeDate").text("");
								$("#listCurrencyExchange,#dialogAddCurrencyExchange").find("#txtCurrencyExchangeDateAdd").css("border-color", "");

								$("#listCurrencyExchange,#dialogAddCurrencyExchange").find("#errorTxtAmount").text("");
								$("#listCurrencyExchange,#dialogAddCurrencyExchange").find("#txtAmount").css("border-color", "");

								$("#dialogAddCurrencyExchange").dialog({
													modal: true,
													show : {
														effect : "blind",
														duration : 500
													},
													title : "Add New Currency Exchange",
													height : 350,
													width : 600,
													buttons : {
														"Save" : {
															text : "Save",
															id : "btnSaveNew",
															click : function() {
																var amount = $("#dialogAddCurrencyExchange").find("#txtAmount").val();
																if(amount<0 || amount == "" || amount == null)
																	{
																	callMessageDialog("Message","Please check amount value!");
																	}
																else if (!isValidationRequiredFieldAdd()) {
																	callMessageDialog("Message","Please insert required form!");
//																	var amount = $("#dialogAddCurrencyExchange").find("#txtAmount").val();
//																	alert(amount);
//																	var print = parseFloat(Math.round(amount * 10000) / 10000).toFixed(4);
//																	alert(print);
																} else if (isCurrencyExistAdd()){
																	callMessageDialog("Message","Data already Exist!");
//																} else if ($("#dialogAddCurrencyExchange").find("#txtCurrencyExchangeDateAdd").val() == null || $("#dialogAddCurrencyExchange").find("#txtCurrencyExchangeDateAdd").val() ==""){
//																	callMessageDialog("Message","Please insert required form!");
																} else if (!isValidationOverRangeWhenAdd()) {
																	callMessageDialog("Message","Amount is over range please check again !");
																}
																 else if ($("#dialogAddCurrencyExchange").find("#txtAmount").val() < 0) {
																	callMessageDialog("Message","Amount must not below 0 !");
																}
																else {
																	var exchangedate =$("#dialogAddCurrencyExchange").find("#txtCurrencyExchangeDateAdd").datepicker('getDate');
																	var from = $("#dialogAddCurrencyExchange").find("#txtFrom").val();
																	var to = $("#dialogAddCurrencyExchange").find("#txtTo").val();
																	var amount = $("#dialogAddCurrencyExchange").find("#txtAmount").val();
																	var roundAmount = (Math.round(amount * 1000) / 1000).toFixed(3);
																	$.ajax({
															    		dataType: "json",
																		type: 'POST',
																		data:
																			JSON.stringify({
																				currencycodesource: from,
																				currencycodedestination: to,
																				amount: roundAmount,
																				exchangedate :$.datepicker.formatDate('yy-mm-dd' , exchangedate)
																			}),
																		contentType: "application/json",
																		url: getAbsolutePath() +  "currencyexchange/add",
																		success: function(data){
																			if(data.addStatus==false){
																				callMessageDialog("Message", "Save Currency Exchange unsuccessfully!");
																				return ;
																			}
																			else{
																			callMessageDialog("Message", "Save Currency Exchange successfully!");
																			
																			$("#listCurrencyExchange").dataTable().fnDestroy();
																			$('#listCurrencyExchange tbody').empty();
																			$("#dialogAddCurrencyExchange").dialog("close");
																			loadData();
																			}
																		},
																		error: function(){
																			callMessageDialog("Message", "Save Currency Exchange unsuccessfully!");
																			
																		}
															    	});
																}
															
															}
														},
														"Cancel" : function() {
															$(
																	"#dialogAddCurrencyExchange")
																	.dialog(
																			"close");
														}
													}
												});
							});
					
					
					
//load dropdown box from currency list
					function loadDropDown() {
						$.ajax({
									dataType : "json",
									type : 'GET',
									data : {},
									contentType : "application/json",
									url : getAbsolutePath() +  "currency/list",
									success : function(data) {
										if (data.status == "ok") {
											$.each(data.list,function(key, value) {
																$("#dialogEditCurrencyExchange").find("#txtTo").prop("disabled", true);
																$('#dialogAddCurrencyExchange').find('#txtFrom').append($('<option>',
																						{
																							value : value.currencycode,
																							text : value.name
																						}));
																$('#dialogEditCurrencyExchange').find('#txtFrom').append($('<option>',{
																							value : value.currencycode,
																							text : value.name
																						}));
																$('#dialogEditCurrencyExchange').find('#txtTo').append($('<option>',{
																	value : value.currencycode,
																	text : value.name
																}));
															});
										} else {
											alert('This alert from should never show!');
										}
									},
									error : function() {
										alert('BoBiTa');
									}
								});
					}
					//clear dropdownbox when edit and add
					function clearDropdownbox() {
						var fromadd = $('dialogAddCurrencyExchange').find('txtFrom');
						var fromedit = $('dialogEditCurrencyExchange').find('txtFrom');
						var toadd = $('dialogAddCurrencyExchange').find('txtTo');
						var toedit = $('dialogEditCurrencyExchange').find('txtTo');

						fromedit.empty();
						fromadd.empty();
						toadd.empty();
						toedit.empty();
					}

					/**
					 * This function is used to call and set params for message
					 * dialog.
					 */
					function callMessageDialog(title, messageContent) {
						$("#messageDialog").find("#messageContent").text(
								messageContent);
						$("#messageDialog").dialog({
							show : {
								effect : "blind",
								duration : 500
							},
							title : title,
							height: 200,
							width: 350,
							hide : {
								effect : "explode",
								duration : 500
							},
							buttons : {
								"OK" : function() {
									$("#messageDialog").dialog("close");
								}
							}
						});
					}

		
					/**
					 * This function allow to validate if data over range
					 */

					function isValidationOverRangeWhenAdd() {
						var amount = $("#listCurrencyExchange,#dialogAddCurrencyExchange").find("#txtAmount").val().length;
						if (amount > 30)
							return false;
						return true;
					}
					function isValidationOverRangeWhenEdit() {
						var amount = $("#listCurrencyExchange,#dialogEditCurrencyExchange").find("#txtAmount").val().length;
						if (amount > 30)
							return false;
						return true;
					}

					/**
					 * This function allow to validate before edit
					 */

					function isValidationRequiredFieldEdit() {
						var amount = $('#listCurrencyExchange,#dialogEditCurrencyExchange').find('#txtAmount').val();
						if (amount == '' || amount == null)
							return false;

						return true;
					}
					function isValidationRequiredFieldAdd() {
						var from = $('#listCurrencyExchange,#dialogAddCurrencyExchange').find('#txtFrom').val();
						if (from == "noneFrom" || from == null)
							return false;
						
						var amount = $('#listCurrencyExchange,#dialogAddCurrencyExchange').find('#txtAmount').val();
						if (amount == '' || amount == null)
							return false;
						return true;
					}

					/**
					 * Allow input numeric only in Add form
					 */
					$("#listCurrencyExchange,#dialogAddCurrencyExchange")
							.on(
									'keydown',
									'#txtAmount',
									function(e) {
										-1 !== $.inArray(e.keyCode, [ 46, 8, 9,
												27, 13, 110, 190 ])
												|| /65|67|86|88/
														.test(e.keyCode)
												&& (!0 === e.ctrlKey || !0 === e.metaKey)
												|| 35 <= e.keyCode
												&& 40 >= e.keyCode
												|| (e.shiftKey
														|| 48 > e.keyCode || 57 < e.keyCode)
												&& (96 > e.keyCode || 105 < e.keyCode)
												&& e.preventDefault()
									});

					/**
					 * Allow input numeric only in Edit and Add form
					 */

					$("#listCurrencyExchange,#dialogAddCurrencyExchange").on(
									'keydown',
									'#txtAmount',
									function(e) {
										-1 !== $.inArray(e.keyCode, [ 46, 8, 9,
												27, 13, 110, 190 ])
												|| /65|67|86|88/
														.test(e.keyCode)
												&& (!0 === e.ctrlKey || !0 === e.metaKey)
												|| 35 <= e.keyCode
												&& 40 >= e.keyCode
												|| (e.shiftKey
														|| 48 > e.keyCode || 57 < e.keyCode)
												&& (96 > e.keyCode || 105 < e.keyCode)
												&& e.preventDefault()
									});
					$("#listCurrencyExchange,#dialogEditCurrencyExchange").on(
							'keydown',
							'#txtAmount',
							function(e) {
								-1 !== $.inArray(e.keyCode, [ 46, 8, 9,
										27, 13, 110, 190 ])
										|| /65|67|86|88/
												.test(e.keyCode)
										&& (!0 === e.ctrlKey || !0 === e.metaKey)
										|| 35 <= e.keyCode
										&& 40 >= e.keyCode
										|| (e.shiftKey
												|| 48 > e.keyCode || 57 < e.keyCode)
										&& (96 > e.keyCode || 105 < e.keyCode)
										&& e.preventDefault()
							});

					

					$("#listCurrencyExchange,#dialogAddCurrencyExchange").find("#txtAmount").on('input propertychange paste',
									function(e) {
										var input = $(this).val();
										if (input.length > 30) {
											$("#listCurrencyExchange,#dialogAddCurrencyExchange").find("#errorTxtAmount").text("Your input is over range");
											$("#listCurrencyExchange,#dialogAddCurrencyExchange").find("#errorTxtAmount").css("color", "red");
											$("#listCurrencyExchange,#dialogAddCurrencyExchange").find("#txtAmount").css("border-color","red");
											$('#listCurrencyExchange,#btnSaveNew').prop("disabled", true);
										} else {
											$("#listCurrencyExchange,#dialogAddCurrencyExchange").find("#txtAmount").text("");
											$("#listCurrencyExchange,#dialogAddCurrencyExchange").find("#errorTxtAmount").css("border-color","green");
											$('#listCurrencyExchange,#btnSaveNew').prop("disabled", false);
										}
									})
									
					$("#listCurrencyExchange,#dialogEditCurrencyExchange").find("#txtAmount").on('input propertychange paste',
									function(e) {
										var input = $(this).val();
										if (input.length > 30) {
											$("#listCurrencyExchange,#dialogEditCurrencyExchange").find("#errorTxtAmount").text("Your input is over range");
											$("#listCurrencyExchange,#dialogEditCurrencyExchange").find("#errorTxtAmount").css("color", "red");
											$("#listCurrencyExchange,#dialogEditCurrencyExchange").find("#txtAmount").css("border-color","red");
											$('#listCurrencyExchange,#btnSaveNew').prop("disabled", true);
										} else {
											$("#listCurrencyExchange,#dialogEditCurrencyExchange").find("#txtAmount").text("");
											$("#listCurrencyExchange,#dialogEditCurrencyExchange").find("#errorTxtAmount").css("border-color","green");
											$('#listCurrencyExchange,#btnSaveNew').prop("disabled", false);
										}
									})

					$("#listCurrencyExchange,#dialogEditCurrencyExchange").find("#txtCurrencyExchangeDate")
						.keyup(
									function() {
										var input = $(this).val();
										if (input.length > 20) {
											$("#listCurrencyExchange,#dialogEditCurrencyExchange").find("#errorTxtAmount").text("Your input is over range");
											$("#listCurrencyExchange,#dialogEditCurrencyExchange").find("#errorTxtAmount").css("color", "red");
											$("#listCurrencyExchange,#dialogEditCurrencyExchange").find("#txtAmount").css("border-color","red");
										} else {
											$("#listCurrencyExchange,#dialogEditCurrencyExchange").find("#errorTxtAmount").text("");
											$("#listCurrencyExchange,#dialogEditCurrencyExchange").find("#txtAmount").css("border-color","green");
										}
									})

									//check if Currency is Exist add and edit form
									function isCurrencyExistAdd(){
										var isCurrencyExistAddStatus = false;
										$.ajax({
											dataType: "json",
											type: 'GET',
											data: {},
											async: false,
											url: getAbsolutePath() +  "currencyexchange/list",
											contentType: "application/json",
											success: function(data){
												var from = $('#listCurrencyExchange,#dialogAddCurrencyExchange').find('#txtFrom').val();
												var to =  $('#listCurrencyExchange,#dialogAddCurrencyExchange').find('#txtTo').val();
												var date = $('#listCurrencyExchange,#dialogAddCurrencyExchange').find('#txtCurrencyExchangeDateAdd').val();
												var day = date.substring(0, 2);
												var month = date.substring(3, 5);
												var year = date.substring(6, 10);	
												var y = 1;
												
												if(data.list.length==0){
													alert("Table have no data.");
												}
												$.each(data.list,function(key,value){
													var yearCompare = value.exchangedate.substring(0, 4);
													var monthCompare = value.exchangedate.substring(5, 7);
													var dayCompare = value.exchangedate.substring(8);
													if(day == dayCompare
															&& month == monthCompare && year == yearCompare
															&& value.currencycodesource == from && value.currencycodedestination == to){
														isCurrencyExistAddStatus = true;
													
													}
												});
											},
											error: function(){
											}
										});
										return isCurrencyExistAddStatus;
									}
								
//					function isCurrencyExistEdit(){
//						var isCurrencyExistEditStatus = false;
//						$.ajax({
//							dataType: "json",
//							type: 'GET',
//							data: {},
//							async: false,
//							url: getAbsolutePath() +  "currencyexchange/list",
//							contentType: "application/json",
//							success: function(data){
//								var from = $('#listCurrencyExchange,#dialogEditCurrencyExchange').find('#txtFrom').val();
//								var to =  $('#listCurrencyExchange,#dialogEditCurrencyExchange').find('#txtTo').val();
//								var date = $('#listCurrencyExchange,#dialogEditCurrencyExchange').find('#txtCurrencyExchangeDate').val();
//								var day = date.substring(0, 2);
//								var month = date.substring(3, 5);
//								var year = date.substring(6, 10);	
//								var y = 1;
//								
//								if(data.list.length==0){
//									alert("Table have no data.");
//								}
//								$.each(data.list,function(key,value){
//									var yearCompare = value.exchangedate.substring(0, 4);
//									var monthCompare = value.exchangedate.substring(5, 7);
//									var dayCompare = value.exchangedate.substring(8);
//									if(day == dayCompare
//											&& month == monthCompare && year == yearCompare
//											&& value.currencycodesource == from && value.currencycodedestination == to){
//										isCurrencyExistEditStatus = true;
//									
//									}
//								});
//							},
//							error: function(){
//							}
//						});
//						return isCurrencyExistEditStatus;
//					}
									
									
					//function select dropdown box add form

					$("#dialogAddCurrencyExchange").find("#txtFrom").on('change', function() {
								//your stuff 
							}).click(function() {
										if ($("#dialogAddCurrencyExchange").find("#txtFrom").val() == "noneFrom") {
											$("#dialogAddCurrencyExchange").find("#txtTo").val("noneTo");
											$("#dialogAddCurrencyExchange").find("#txtTo").prop("disabled", true);
											//$("#dialogAddCurrencyExchange").find("#txtTo").prop('selected', 'noneTo');
											$("#dialogAddCurrencyExchange").find("#txtTo").empty();
											$("#dialogAddCurrencyExchange").find("#txtTo").append($('<option>',{
												value : "noneTo",
												text : "Not Select"
											}));		
//											$('#listCurrencyExchange,#btnSaveNew').prop("disabled", true);
										} else if ($("#dialogAddCurrencyExchange").find("#txtFrom").val() != "noneFrom") {
											$("#dialogAddCurrencyExchange").find("#txtTo").prop("disabled", false);
											var currencyCode = $("#dialogAddCurrencyExchange").find("#txtFrom").val();
											var dropdown = $("#dialogAddCurrencyExchange").find("#txtTo");
											var dropdown2 = $("#dialogEditCurrencyExchange").find("#txtTo");
											dropdown.empty();
											$
													.ajax({
														dataType : "json",
														type : 'GET',
														data : {},
														contentType : "application/json",
														url : getAbsolutePath() +   "currency/list",
														success : function(data) {
															if (data.status == "ok") {
																$.each(data.list,function(key,value) {
																					if (value.currencycode != currencyCode) {
																						dropdown.append($(
																										'<option>',
																										{
																											value : value.currencycode,
																											text : value.name
																										}));
																						dropdown2.append($(
																										'<option>',
																										{
																											value : value.currencycode,
																											text : value.name
																										}));
																					}
																				});
															} else {
																alert('This alert from should never show!');
															}
														},
														error : function() {
															alert('BoBiTa');
														}
													});
										}
									});
					//function select dropdown box edit form
					$("#dialogEditCurrencyExchange").find("#txtFrom").on('change', function() {
						//your stuff 
					}).click(function() {
								$("#dialogEditCurrencyExchange")
								.find("#txtTo").prop("disabled", false);
									var currencyCode = $("#dialogEditCurrencyExchange").find("#txtFrom").val();
									var dropdown2 = $("#dialogEditCurrencyExchange").find("#txtTo");
									dropdown2.empty();
									$.ajax({
												dataType : "json",
												type : 'GET',
												data : {},
												contentType : "application/json",
												url : getAbsolutePath() +  "currency/list",
												success : function(data) {
													if (data.status == "ok") {
														$.each(data.list,function(key,value) {
																			if (value.currencycode != currencyCode) {
																				dropdown2
																						.append($(
																								'<option>',
																								{
																									value : value.currencycode,
																									text : value.name
																								}));
																			}
																		});
													} else {
														alert('This alert from should never show!');
													}
												},
												error : function() {
													alert('BoBiTa');
												}
											});								
							});
					$("#dialogEditCurrencyExchange").find("#txtTo").on('change', function() {
						//your stuff 
					}).click(function() {
						//		$("#dialogEditCurrencyExchange").find("#txtTo").prop("disabled", false);
									var currencyexchangeCode = $("#dialogEditCurrencyExchange").find("#txtCurrencyExchangeCode").val();
									var currencyCodeFrom = $("#dialogEditCurrencyExchange").find("#txtFrom").val();
									var dropdown2 = $("#dialogEditCurrencyExchange").find("#txtTo");
									var currencyCodeTo = $("#dialogEditCurrencyExchange").find("#txtTo").val();
									var exchangedateEdit = $("#dialogEditCurrencyExchange").find("#txtTo").val();
									alert(currencyCode);
									alert(exchangedateEdit);
								//	dropdown2.empty();
									$.ajax({
												dataType : "json",
												type : 'GET',
												data : {},
												contentType : "application/json",
												url : getAbsolutePath() +  "currencyexchange/list",
												success : function(data) {
													if (data.status == "ok") {
														$.each(data.list,function(key,value) {
//															alert(value.exchangedate);
//																			if (value.currencycode == currencyCode ) {
//																			if (value.exchangedate == exchangedateEdit ) {
//																				alert("Have same data date");
//																				dropdown2
//																						.append($(
//																								'<option>',
//																								{
//																									value : value.currencycode,
//																									text : value.name
//																								}));
																				
//																			}
																		});
													} else {
														alert('This alert from should never show!');
													}
												},
												error : function() {
													alert('BoBiTa');
												}
											});								
							});				
					
					
					$(function() {	
					   var dateFormat = "dd/mm/yy",
					      from = $( "#searchFrom" ).datepicker({
					          defaultDate: "+1w",
					          changeMonth: true,
					          dateFormat : 'dd/mm/yy',
					          numberOfMonths: 1
					        })
					        .on( "change", function() {
					          to.datepicker( "option", "minDate", getDate( this ));
					        }),
					      to = $( "#searchTo" ).datepicker({
					        defaultDate: "+1w",
					        changeMonth: true,
					        dateFormat : 'dd/mm/yy',
					        numberOfMonths: 1
					      })
					    $("#dialogAddCurrencyExchange").find("#txtCurrencyExchangeDateAdd").datepicker({
				          defaultDate: "+1w",
				          changeMonth: true,
				          dateFormat : 'dd/mm/yy',
				          numberOfMonths: 1
				        })
				        $("#dialogEditCurrencyExchange").find("#txtCurrencyExchangeDate").datepicker({
					          defaultDate: "+1w",
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
					
					
				//validation button before click button Search
					
					function isValidationBeforeSearch() {
						var dateFrom = $("#searchFrom").val();
						if(dateFrom == null||dateFrom.trim() === ''){
							return false;
						}
						var dateTo = $("#searchTo").val();
						if(dateTo == null||dateTo.trim() === ''){
							return false;
						}
						return true;
					}
					function isValidationSearchRange() {
						var dateFrom = $("#searchFrom").val();
						var dateTo = $("#searchTo").val();
						if(Date.parse(dateFrom) > Date.parse(dateTo))
							return false;
						return true;
					}				
					
					loadData();
					loadDropDown();
				});