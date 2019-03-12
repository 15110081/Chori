$(document).ready(function(){
	/**
	 * This function is used to load data into table
	 */
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "agent/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
	//				alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){		
					var tmp ='';
					if(value.agentcontactModellist.length>0){
						tmp+='<table border="0">';
						$.each(value.agentcontactModellist,function(key,value1){
							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td><td>'+value1.tel+'</td></tr>';
						});
						tmp+='</table>';
					}
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.shortname),
							$('<td>').text(value.longname),
							$('<td>').text(value.address),
							$('<td>').text(value.tel),
							$('<td>').text(value.fax),
							$('<td>').text(value.taxno),							
							$('<td>').html(value.agentcontactModellist.length==0?'':tmp),
							$('<td>').text(value.remark == null ? " " : value.remark),
//							$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.agentcode+'">Edit</button>'),
//							$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.agentcode+'">Delete</button>')
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.agentcode+'" data-shortname="'+value.shortname+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
					).appendTo('#listAgent');
				});
				action();
				reloadTableWithStatus();

				$('#listAgent').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
	//			alert("Can not get data!");
			}
		});
	};
	
	function action(){
		//When click select
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();		
			
			//lấy ra data-id
			var agentCode= $(this).data('id');
			
			$(".selectOption").val("Options");
			//lấy thông tin qua id gán vào edit dialog
			if(valueSelected=="Edit"){
			$.ajax({
				dataType: "json",
				type: 'POST',
				data: JSON.stringify({
					agentcode: agentCode
				}),
				url: getAbsolutePath() +  "agent/detail",
				contentType: "application/json",
				success: function(data){
					//alert(data.agent.agentcode);
					// gán các giá trị vào dialog					
					if(data.status=="ok"){
					$('#editAgentDialog').find('#txtShortName').val(data.currentagent.shortname);
					$('#editAgentDialog').find('#txtLongName').val(data.currentagent.longname);
					$('#editAgentDialog').find('#txtAddress').val(data.currentagent.address);
					$('#editAgentDialog').find('#txtTel').val(data.currentagent.tel);
					$('#editAgentDialog').find('#txtFax').val(data.currentagent.fax);
					$('#editAgentDialog').find('#txtTaxNo').val(data.currentagent.taxno);
					$('#editAgentDialog').find('#txtStatus').val(data.currentagent.status);	
					$('#editAgentDialog').find('#txtAgentCode').val(data.currentagent.agentcode);
					$('#editAgentDialog').find('#txtRemark').val(data.currentagent.remark);	
					
					//duyệt qua mảng:
					$.each(data.currentagent.agentcontactModellist,function(key,value){
						//tmp+='<tr><td>'+value.name+'</td><td>'+value.email+'</td></tr>';
						$("#tblAgentContactList tbody").append('<tr data-id="'+value.agentcontactcode+'"><td class="fcName">'+value.name+'</td><td class="fcEmail">'+value.email+'</td><td class="fcTel">'+value.tel+'</td><td><button class="btn btn-primary btnEditFC">Edit</button><button class="btn btn-danger btnDeleteFC">Delete</button></td></tr>');
						
						$(".btnEditFC").bind("click", EditContact);
						$(".btnDeleteFC").bind("click", DeleteContact);
					});
					
					}else{
	//					alert('This alert should never show!');
					}					
				},
				error: function(){
	//				callMessageDialog("Message", "Can not get ID!");	
				}
			});
			//end lấy thông tin qua id gán vào edit dialog
			
			$("#editAgentDialog").dialog({
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Edit Office",
				height: 700,
				width: 950,
				modal: true,
				buttons:{
					"Save": function(){
						
						//check if user have entered required fields
						if(!validateRequiredFieldForEdit()){
							callMessageDialog("Warning message", "Please enter required fields!");
							
							//get value of name
							
							var shortName= $('#editAgentDialog').find('#txtShortName').val();
							
						
							//if user don't enter name or enter just space
							if(shortName.trim() === '' || shortName == null){
								$("#editAgentDialog").find("#errorTxtShortName").text("Please enter office short name!");
								$("#editAgentDialog").find("#errorTxtShortName").css("color", "red");
								$("#editAgentDialog").find("#txtShortName").css("background-color", "red");
							}
						}else{
							//if user have entered all required fields, then check if input is overRange
							if(!validateOverRangeWhenEdit()){
								callMessageDialog("Warning message", "Your input is over range!");
							}else{
								//all inputs are valid here.
								
								//edit part when inputs are valid
								var agentcode = $('#editAgentDialog').find('#txtAgentCode').val();
								var shortname = $('#editAgentDialog').find('#txtShortName').val();
								var longname = $('#editAgentDialog').find('#txtLongName').val();
								var address = $('#editAgentDialog').find('#txtAddress').val();
								var tel = $('#editAgentDialog').find('#txtTel').val();
								var fax = $('#editAgentDialog').find('#txtFax').val();
								var taxno = $('#editAgentDialog').find('#txtTaxNo').val();
								var status = $('#editAgentDialog').find('#txtStatus').val();
								var remark = $('#editAgentDialog').find('#txtRemark').val();
								
								var agentcontactModellist = [];
								//lặp qua table contact
								$('#editAgentDialog').find('#tblAgentContactList tr').each(function() {
									var agentcontactcode = $(this).data('id');
									var fcName = $(this).find(".fcName").html();
									var fcEmail = $(this).find(".fcEmail").html();
									var fcTel = $(this).find(".fcTel").html();
									if(typeof fcName === "undefined"){
										return true;//continue
									}
									//console.log(x+' '+y);
									agentcontactModellist.push({
								          "agentcontactcode": agentcontactcode,
								          "agentCode": $('#editAgentDialog').find('#txtAgentCode').val(),
								          "creator": null,
								          "name": fcName,
								          "email": fcEmail,
								          "tel" : fcTel,
								          "createdate": null
								        });
		
									//console.log(JSON.stringify(factorycontactModellist));
								 });
								//end lặp qua table contact
								
								var agent = {
										agentcode: agentcode,
										shortname: shortname,
										longname: longname,
										address: address,
										tel: tel,
										fax: fax,
										taxno: taxno,
										status: status,
										remark: remark,
										agentcontactModellist: agentcontactModellist
								};
								
								console.log(JSON.stringify(agent));
								//
								
								//gọi ajax để edit
								$.ajax({
									dataType: "json",
									type: 'POST',
									data: JSON.stringify(agent),
									contentType: "application/json",
									url: getAbsolutePath() +  "agent/edit",
									success: function(data){
										if(data.editStatus==true){
											//alert("Edit thành công");
											
											reloadTableWithStatus();
											callMessageDialog("Message", 'Edit office successfully!');
											$("#editAgentDialog").dialog("close");
											resetAfterEdit();
										}else if(data.editStatus==false){
											callMessageDialog("Message", "Edit office unsuccessfully!");
										}
									},
									error: function(){
					//					callMessageDialog("Message", "Error!");
									}
								});
								//end edit part when inputs are valid
								
							}
						}
										
					},
					"Cancel": function(){
						$("#editAgentDialog").dialog("close");
						//Xóa các thông tin trong bảng contact
						resetAfterEdit();
					}
				},
				close: function(){
					resetAfterEdit();
				}
			});
		};
				
		if(valueSelected=="Delete"){
			var agentCode= $(this).data('id');
			var shortName= $(this).data('shortname');
			
			$("#deleteAgentDialog").find("#messageContent").text('Are you sure you want to delete office "' + shortName+'"?');
			
			$("#deleteAgentDialog").dialog({
	    		show:{
					effect:"blind",
					duration: 500
				},
				title: "Delete Confirm",
				height: 200,
				width: 400,
				buttons:{
					"OK": function(){
						$.ajax({
							dataType: "json",
							type: 'POST',
							data:JSON.stringify({
								agentcode: agentCode
							}),
							contentType: "application/json",
							url: getAbsolutePath() +  "agent/delete",
							success: function(data){
								if(data.status=="ok"){
									if(data.deleteStatus== true){
										//show delete status to user
										callMessageDialog("Message", 'Delete successfully!');
										//close dialog
										$("#deleteAgentDialog").dialog("close");
										//reload table
//										$("#listFabricSupplier").dataTable().fnDestroy();
//										$('#listFabricSupplier tbody').empty();
//										loadData();
										reloadTableWithStatus();
									}else if(data.deleteStatus== false){
										callMessageDialog("Message", 'Can\'t delete!');
										$("#deleteAgentDialog").dialog("close");
									}else{
					//					alert("this cant be show, 243!");
									}
								}else{
					//				alert("unexpected error! (2), 404");
								}
							},
							error: function(){
								$("#deleteAgentDialog").dialog("close");
								callMessageDialog("Message", 'Can\'t delete!');
								
							}
						});
					},
					"Cancel": function(){
						$("#deleteAgentDialog").dialog("close");
					}
				}
			});
		};
		//end if user choose delete option
		});
		}
	
	/**
	 * This function is used to change table data when choose status
	 */
	$('#ddlStatus').on('change',function(){
		//lấy ra value đc chọn
		var optionSelected = $(this).find("option:selected");
		var valueSelected  = optionSelected.val();
		
		//
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "agent/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listAgent").dataTable().fnDestroy();
				$('#listAgent tbody').empty();
				
				var i = 1;
				if(data.list.length==0){
	//				alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){					
					var tmp = '';
					if(value.agentcontactModellist.length>0){
						tmp+='<table border="0">';
						$.each(value.agentcontactModellist,function(key,value1){
							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td><td>'+value1.tel+'</td></tr>';
						});
						tmp+='</table>';
					}
					//nếu value là all, 
					if(valueSelected=='All'){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.shortname),
								$('<td>').text(value.longname),
								$('<td>').text(value.address),
								$('<td>').text(value.tel),
								$('<td>').text(value.fax),
								$('<td>').text(value.taxno),							
								$('<td>').html(value.agentcontactModellist.length==0?'':tmp),
								$('<td>').text(value.remark == null ? " " : value.remark),
//								$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.agentcode+'">Edit</button>'),
//								$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.agentcode+'">Delete</button>')
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.agentcode+'" data-shortname="'+value.shortname+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
						).appendTo('#listAgent');
						
					}else{//không thì xuất ra theo cái status
//						$("#listAgent").dataTable().fnDestroy();
//						$('#listAgent tbody').empty();
						
						if(valueSelected==value.status){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.shortname),
									$('<td>').text(value.longname),
									$('<td>').text(value.address),
									$('<td>').text(value.tel),
									$('<td>').text(value.fax),
									$('<td>').text(value.taxno),							
									$('<td>').html(value.agentcontactModellist.length==0?'':tmp),
									$('<td>').text(value.remark == null ? " " : value.remark),
//									$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.agentcode+'">Edit</button>'),
//									$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.agentcode+'">Delete</button>')
									$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.agentcode+'" data-shortname="'+value.shortname+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAgent');
						}
					}
				});
				action();

				$('#listAgent').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
	//			alert("Can not get data!");
			}
		});
		//
	});
	
	/**
	 * reload lại table vs status
	 */
	function reloadTableWithStatus(){
		//lấy ra value đc chọn
		var optionSelected = $('#ddlStatus').find("option:selected");
		var valueSelected  = optionSelected.val();
		
		//
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "agent/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listAgent").dataTable().fnDestroy();
				$('#listAgent tbody').empty();
				
				var i = 1;
				if(data.list.length==0){
		//			alert("Table have no data !");
				}
				$.each(data.list,function(key,value){
					
					var tmp = '';
					if(value.agentcontactModellist.length>0){
						tmp+='<table border="0">';
						$.each(value.agentcontactModellist,function(key,value1){
							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td><td>'+value1.tel+'</td></tr>';
						});
						tmp+='</table>';
					}
					//nếu value là all, 
					if(valueSelected=='All'){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.shortname),
								$('<td>').text(value.longname),
								$('<td>').text(value.address),
								$('<td>').text(value.tel),
								$('<td>').text(value.fax),
								$('<td>').text(value.taxno),							
								$('<td>').html(value.agentcontactModellist.length==0?'':tmp),
								$('<td>').text(value.remark == null ? " " : value.remark),
//								$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.agentcode+'">Edit</button>'),
//								$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.agentcode+'">Delete</button>')
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.agentcode+'" data-shortname="'+value.shortname+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
						).appendTo('#listAgent');
						
					}else{//không thì xuất ra theo cái status
//						$("#listAgent").dataTable().fnDestroy();
//						$('#listAgent tbody').empty();
						
						if(valueSelected==value.status){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.shortname),
									$('<td>').text(value.longname),
									$('<td>').text(value.address),
									$('<td>').text(value.tel),
									$('<td>').text(value.fax),
									$('<td>').text(value.taxno),							
									$('<td>').html(value.agentcontactModellist.length==0?'':tmp),
									$('<td>').text(value.remark == null ? " " : value.remark),
//									$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.agentcode+'">Edit</button>'),
//									$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.agentcode+'">Delete</button>')
									$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.agentcode+'" data-shortname="'+value.shortname+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAgent');
						}
					}
				});
				action();

				$('#listAgent').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
	//			alert("Can not get data!");
			}
		});
		//
	}
	
	/**
	 * Hàm hiển thị lên add dialog để nhập 
	 */
	$('#btnAddNewAgent').click(function(){
		
		$("#addAgentDialog").dialog({
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Add New Office",
			height: 700,
			width: 950,
			modal: true,
			buttons:{
				"Save": function(){
					var shortnameInput = $('#addAgentDialog').find('#txtShortName').val();
					//check if user have entered all required fields
					if(!validateRequiredFieldForAdd()){
						callMessageDialog("Warning message", "Please enter required fields!");
						
						
						var shortName= $('#addAgentDialog').find('#txtShortName').val();
											
						//if user dont enter name or enter just space
						if(shortName.trim() === '' || shortName == null){
							$("#addAgentDialog").find("#errorTxtShortName").text("Please enter office short name!");
							$("#addAgentDialog").find("#errorTxtShortName").css("color", "red");
							$("#addAgentDialog").find("#txtShortName").css("background-color", "red");
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
									shortname: shortnameInput
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "agent/isExist",
								success: function(data){
									if(data.isExisted==true){
										//if code is existed
										$("#addAgentDialog").find("#errorTxtShortName").text("Office short name is existed, please choose the different one!");
										$("#addAgentDialog").find("#errorTxtShortName").css("color", "red");
										$("#addAgentDialog").find("#txtShortName").css("background-color", "red");
									}else if(data.isExisted==false){
										//alert("all input is ok!");
										$("#addAgentDialog").find("#errorTxtShortName").text("Office code is valid!");
										$("#addAgentDialog").find("#errorTxtShortName").css("color", "green");
										$("#addAgentDialog").find("#txtShortName").css("background-color", "white");
										
										//add new agent here
										//main code when input is already valid
										var shortname = $('#addAgentDialog').find('#txtShortName').val();
										var longname = $('#addAgentDialog').find('#txtLongName').val();
										var address = $('#addAgentDialog').find('#txtAddress').val();
										var tel = $('#addAgentDialog').find('#txtTel').val();
										var fax = $('#addAgentDialog').find('#txtFax').val();
										var taxno = $('#addAgentDialog').find('#txtTaxNo').val();
										var status = $('#addAgentDialog').find('#txtStatus').val();	
										var remark = $('#addAgentDialog').find('#txtRemark').val();		
										
										var agentcontactModellist = [];
										//lặp qua table contact
										$('#tblAgentContactList tr').each(function() {
											var fcName = $(this).find(".fcName").html();
											var fcEmail = $(this).find(".fcEmail").html();
											var fcTel = $(this).find(".fcTel").html();
											if(typeof fcName === "undefined"){
												return true;//continue
											}
											//console.log(x+' '+y);
											agentcontactModellist.push({										          
										          "agentCode": $('#addAgentDialog').find('#txtAgentCode').val(),
										          
										          "name": fcName,
										          "email": fcEmail,
										          "tel": fcTel
										       
										        });
					
											//console.log(JSON.stringify(fabricsuppliercontactModelList));
										 });
										//end lặp qua table contact
										
										var agent = {
												
												shortname: shortname,
												longname: longname,
												address: address,
												tel: tel,
												fax: fax,
												taxno: taxno,
												status: status,
												remark: remark,
												
												agentcontactModellist: agentcontactModellist
										};
										
										console.log(JSON.stringify(agent));
										//gọi ajax để add
										$.ajax({
											dataType: "json",
											type: 'POST',
											data: JSON.stringify(agent),
											contentType: "application/json",
											url: getAbsolutePath() +  "agent/add",//làm đến đây
											success: function(data){
												//alert("OK");
												if(data.addStatus==true){
													//if want to return to status all after add
													// $('#ddlStatus').val("All");
													reloadTableWithStatus();
													callMessageDialog("Message", 'Add office successfully!');
													$("#addAgentDialog").dialog("close");
													resetAfterAdd();
												}
											},
											error: function(){
									//			alert("Error");
											}
										});
										//end main code when input is already valid
									}
								},
								error: function(){
									
									//here user delete code
								}
							});
						}
					}
					

				},
				"Cancel": function(){
					$("#addAgentDialog").dialog("close");
					resetAfterAdd();
				}
			},
			close: function(){
				resetAfterAdd();
			}
		});
	});
	
	//hàm reset các trường trong bảng sau khi thêm mới
	function resetAfterAdd(){
		$('#addAgentDialog').find('#txtShortName').val('');
		$('#addAgentDialog').find('#txtLongName').val('');
		$('#addAgentDialog').find('#txtAddress').val('');
		$('#addAgentDialog').find('#txtTel').val('');
		$('#addAgentDialog').find('#txtFax').val('');
		$('#addAgentDialog').find('#txtTaxNo').val('');
		$('#addAgentDialog').find('#txtStatus').val('');	
		$('#addAgentDialog').find('#txtRemark').val('');
		$("#tblAgentContactList > tbody").html("");
		
		//reset css
		$("#addAgentDialog").find("#errorTxtShortName").text("");
		$("#addAgentDialog").find("#txtShortName").css("background-color", "white");
		$("#addAgentDialog").find("#errorTxtLongName").text("");
		$("#addAgentDialog").find("#txtLongName").css("background-color", "white");
		$("#addAgentDialog").find("#errorTxtAddress").text("");
		$("#addAgentDialog").find("#txtAddress").css("background-color", "white");
		$("#addAgentDialog").find("#errorTxtTel").text("");
		$("#addAgentDialog").find("#txtTel").css("background-color", "white");
		$("#addAgentDialog").find("#errorTxtFax").text("");
		$("#addAgentDialog").find("#txtFax").css("background-color", "white");
		$("#addAgentDialog").find("#errorTxtTaxNo").text("");
		$("#addAgentDialog").find("#txtTaxNo").css("background-color", "white");
		$("#addAgentDialog").find("#errorTxtRemark").text("");
		$("#addAgentDialog").find("#txtRemark").css("background-color", "white");
	}
	//end hàm reset các trường trong bảng sau khi thêm mới
	
	//hàm reset các trường trong bảng sau khi edit
	function resetAfterEdit(){
		$('#editAgentDialog').find('#txtShortName').val('');
		$('#editAgentDialog').find('#txtLongName').val('');
		$('#editAgentDialog').find('#txtAddress').val('');
		$('#editAgentDialog').find('#txtTel').val('');
		$('#editAgentDialog').find('#txtFax').val('');
		$('#editAgentDialog').find('#txtTaxNo').val('');
		$('#editAgentDialog').find('#txtStatus').val('');		
		$('#editAgentDialog').find('#txtRemark').val('');
		$("#tblAgentContactList > tbody").html("");
		
		//reset css
		$("#editAgentDialog").find("#errorTxtShortName").text("");
		$("#editAgentDialog").find("#txtShortName").css("background-color", "white");
		$("#editAgentDialog").find("#errorTxtLongName").text("");
		$("#editAgentDialog").find("#txtLongName").css("background-color", "white");
		$("#editAgentDialog").find("#errorTxtAddress").text("");
		$("#editAgentDialog").find("#txtAddress").css("background-color", "white");
		$("#editAgentDialog").find("#errorTxtTel").text("");
		$("#editAgentDialog").find("#txtTel").css("background-color", "white");
		$("#editAgentDialog").find("#errorTxtFax").text("");
		$("#editAgentDialog").find("#txtFax").css("background-color", "white");
		$("#editAgentDialog").find("#errorTxtTaxNo").text("");
		$("#editAgentDialog").find("#txtTaxNo").css("background-color", "white");
		$("#editAgentDialog").find("#errorTxtRemark").text("");
		$("#editAgentDialog").find("#txtRemark").css("background-color", "white");
	}
	//end hàm reset các trường trong bảng sau khi edit
	
	
//	function Bind4Edit(){
//		$(".btnEditFC").bind("click", Edit);
//		$(".btnDeleteFC").bind("click", Delete);
//		$("#btnAddNewAgentContact").bind("click", Add);
//	}
	

	//
	
	//function for validate email format
//	function validateEmail(email) {
//	  var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
//	  return re.test(email);
//	}
	
	$(function contact(){
		//Add, Save, Edit and Delete functions code
		$(".btnEditFC").bind("click", EditContact);
		$(".btnDeleteFC").bind("click", DeleteContact);
		$("#btnAddNewAgentContact").bind("click", AddContact);
		$('#editAgentDialog').find('#btnAddNewAgentContact').bind("click", AddContact);
	});
	
	//hàm add 1 dòng vào table CustomerContactList
	function AddContact(){
		$("#tblAgentContactList tbody").append(
				"<tr data-id='0'>"+
				"<td><input maxlength='50' type='text'/></td>"+
				"<td><input maxlength='100' type='text'/></td>"+
				"<td><input maxlength='50' type='text'/></td>"+
				"<td><button class='btn btn-primary btnSaveFC'>Save</button><button class='btn btn-danger btnDeleteFC'>Delete</button></td>"+
				"</tr>");		
			
				$(".btnSaveFC").bind("click", SaveContact);		
				$(".btnDeleteFC").bind("click", DeleteContact);
	};
	
	//hàm add dòng đã nhập vào table
	function SaveContact(){
		var par = $(this).parent().parent(); //tr
		var tdName = par.children("td:nth-child(1)");
		var tdEmail = par.children("td:nth-child(2)");
		var tdTel = par.children("td:nth-child(3)");
		var tdButtons = par.children("td:nth-child(4)");

//		if(!validateEmail(tdEmail.children("input[type=text]").val().toString()))
//		{
//			callMessageDialog("Warning", 'Invalid email format!');
//		}
//		else {	
			tdName.html(tdName.children("input[type=text]").val());
			tdName.addClass( "fcName" );
			tdEmail.html(tdEmail.children("input[type=text]").val());
			tdEmail.addClass( "fcEmail" );
			tdTel.html(tdTel.children("input[type=text]").val());
			tdTel.addClass( "fcTel" );
			tdButtons.html("<button class='btn btn-primary btnEditFC'>Edit</button><button class='btn btn-danger btnDeleteFC'>Delete</button>");
	
			$(".btnEditFC").bind("click", EditContact);
			$(".btnDeleteFC").bind("click", DeleteContact);
//		}
	};
	//end hàm add dòng đã nhập vào table
	
	//
	function EditContact(){
		var par = $(this).parent().parent(); //tr
		var tdName = par.children("td:nth-child(1)");
		var tdEmail = par.children("td:nth-child(2)");
		var tdTel = par.children("td:nth-child(3)");
		var tdButtons = par.children("td:nth-child(4)");

		tdName.html("<input maxlength='50' type='text' id='txtName' value='"+tdName.html()+"'/>");
		tdEmail.html("<input maxlength='100' type='text' id='txtEmail' value='"+tdEmail.html()+"'/>");
		tdTel.html("<input maxlength='50' type='text' id='txtTel' value='"+tdTel.html()+"'/>");
		tdButtons.html("<button class='btn btn-primary btnSaveFC'>Save</button>");

		$(".btnSaveFC").bind("click", SaveContact);
		$(".btnEditFC").bind("click", EditContact);
		$(".btnDeleteFC").bind("click", DeleteContact);
	};
	
	//
	function DeleteContact(){
		var par = $(this).parent().parent(); //tr
		par.remove();
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
		
		var shortName= $("#addAgentDialog").find("#txtShortName").val();
		if(shortName.trim() === '' || shortName == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before add new agent
	 */
	function validateOverRangeWhenAdd(){
		if($("#addAgentDialog").find("#txtShortName").val().length>50 
				|| $("#addAgentDialog").find("#txtLongName").val().length>100 || $("#addAgentDialog").find("#txtAddress").val().length>200
				|| $("#addAgentDialog").find("#txtTel").val().length>50 || $("#addAgentDialog").find("#txtFax").val().length>50
				|| $("#addAgentDialog").find("#txtTaxNo").val().length>50 || $("#addAgentDialog").find("#txtRemark").val().length>500)
			return false;
		return true;
	}
	
	/**
	 * agent code on keyup function() to check if user entered, over range, agent is existed (3 cases)
	 */
	
	/**
	 * agent's short name on keyup function() to check if user entered, over range (2 cases)
	 */
	$("#addAgentDialog").find("#txtShortName").on('change keyup paste',function(){
		var shortName= $(this).val();
		//if user dont enter or enter just space
		if(shortName.trim() === '' || shortName == null){
			$("#addAgentDialog").find("#errorTxtShortName").text("Please enter office short name!");
			$("#addAgentDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		else if(shortName.length>50){
			$("#addAgentDialog").find("#errorTxtShortName").text("The short name field's length is 50. Your input is over range!");
			$("#addAgentDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}else{//valid input
			$("#addAgentDialog").find("#errorTxtShortName").text("");
			$(this).css("background-color", "white");
		}
		//check if agent short name is existed
//		$.ajax({
//			dataType: "json",
//			type: 'GET',
//			data:{},
//			contentType: "application/json",
//			url: "/Chori/agent/isExist/"+shortName,
//			success: function(data){
//				if(data.isExisted==true){// if short name is existed, prevent user add new agent
//					//if code is existed
//					$("#addAgentDialog").find("#errorTxtShortName").text("Please enter another agent short name!");
//					$("#addAgentDialog").find("#errorTxtShortName").css("color", "red");
//					$("#addAgentDialog").find("#txtShortName").css("background-color", "red");
//				}else if(data.isExisted==false&&$("#addAgentDialog").find("#txtShortName").val().length<21){
//					//if code is not existed and valid length
//					$("#addAgentDialog").find("#errorTxtShortName").text("Agent short name is valid!");
//					$("#addAgentDialog").find("#errorTxtShortName").css("color", "green");
//					$("#addAgentDialog").find("#txtShortName").css("background-color", "white");
//				}
//			},
//			error: function(){
//				//when user clear the code
//				$("#addAgentDialog").find("#errorTxtShortName").text("Please enter short name!");
//				$("#addAgentDialog").find("#errorTxtShortName").css("color", "red");
//				$("#addAgentDialog").find("#txtShortName").css("background-color", "red");
//			}
//		});
	});
	
	/**
	 * agent long name on keyup function() to check if over range (1 cases)
	 */
	$("#addAgentDialog").find("#txtLongName").on('change keyup paste',function(){
		var longName= $(this).val();
		
		//if input of user is over range
		if(longName.length>100){
			$("#addAgentDialog").find("#errorTxtLongName").text("The long name field's length is 100. Your input is over range!");
			$("#addAgentDialog").find("#errorTxtLongName").css("color", "red");
			$(this).css("background-color", "red");
		}else if(longName.length<101){//valid input
			$("#addAgentDialog").find("#errorTxtLongName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * agent address on keyup function() to check if over range (1 cases)
	 */
	$("#addAgentDialog").find("#txtAddress").on('change keyup paste',function(){
		var address= $(this).val();
		
		//if input of user is over range
		if(address.length>200){
			$("#addAgentDialog").find("#errorTxtAddress").text("The address field's length is 200. Your input is over range!");
			$("#addAgentDialog").find("#errorTxtAddress").css("color", "red");
			$(this).css("background-color", "red");
		}else if(address.length<201){//valid input
			$("#addAgentDialog").find("#errorTxtAddress").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * agent tel on keyup function() to check if over range (1 cases)
	 */
	$("#addAgentDialog").find("#txtTel").on('change keyup paste',function(){
		var tel= $(this).val();
		
		//if input of user is over range
		if(tel.length>50){
			$("#addAgentDialog").find("#errorTxtTel").text("The telephone field's length is 50. Your input is over range!");
			$("#addAgentDialog").find("#errorTxtTel").css("color", "red");
			$(this).css("background-color", "red");
		}else if(tel.length<51){//valid input
			$("#addAgentDialog").find("#errorTxtTel").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * agent fax on keyup function() to check if over range (1 cases)
	 */
	$("#addAgentDialog").find("#txtFax").on('change keyup paste',function(){
		var fax= $(this).val();
		
		//if input of user is over range
		if(fax.length>50){
			$("#addAgentDialog").find("#errorTxtFax").text("The fax field's length is 50. Your input is over range!");
			$("#addAgentDialog").find("#errorTxtFax").css("color", "red");
			$(this).css("background-color", "red");
		}else if(fax.length<51){//valid input
			$("#addAgentDialog").find("#errorTxtFax").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * agent tax no on keyup function() to check if over range (1 cases)
	 */
	$("#addAgentDialog").find("#txtTaxNo").on('change keyup paste',function(){
		var taxNo= $(this).val();
		
		//if input of user is over range
		if(taxNo.length>50){
			$("#addAgentDialog").find("#errorTxtTaxNo").text("The tax no field's length is 50. Your input is over range!");
			$("#addAgentDialog").find("#errorTxtTaxNo").css("color", "red");
			$(this).css("background-color", "red");
		}else if(taxNo.length<51){//valid input
			$("#addAgentDialog").find("#errorTxtTaxNo").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * agent remark on keyup function() to check if over range (1 cases)
	 */
	$("#addAgentDialog").find("#txtRemark").on('change keyup paste',function(){
		var remark= $(this).val();
		
		//if input of user is over range
		if(remark.length>500){
			$("#addAgentDialog").find("#errorTxtRemark").text("The remark field's length is 500. Your input is over range!");
			$("#addAgentDialog").find("#errorTxtRemark").css("color", "red");
			$(this).css("background-color", "red");
		}else if(remark.length<501){//valid input
			$("#addAgentDialog").find("#errorTxtRemark").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * ------------------End part for adding-------------------
	 */
	
	/**
	 * This function is used to validate required fields before editing
	 */
	function validateRequiredFieldForEdit(){
//		var fabricSupplierCode= $("#editFabricSupplierDialog").find("#txtFabricSupplierCode").val();
//		if(fabricSupplierCode.trim() === '' || fabricSupplierCode == null)
//			return false;
		
		var shortName= $("#editAgentDialog").find("#txtShortName").val();
		if(shortName.trim() === '' || shortName == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before edit fabric supplier
	 */
	function validateOverRangeWhenEdit(){
		if($("#editAgentDialog").find("#txtShortName").val().length>50 
				|| $("#editAgentDialog").find("#txtLongName").val().length>100 || $("#editAgentDialog").find("#txtAddress").val().length>200
				|| $("#editAgentDialog").find("#txtTel").val().length>50 || $("#editAgentDialog").find("#txtFax").val().length>50
				|| $("#editAgentDialog").find("#txtTaxNo").val().length>50 || $("#editAgentDialog").find("#txtRemark").val().length>500)
			return false;
		return true;
	}
	
	/**
	 * [Edit part] agent's short name on keyup function() to check if user entered, over range (2 cases)
	 */
	$("#editAgentDialog").find("#txtShortName").on('change keyup paste',function(){
		var shortName= $(this).val();
		
		//if user dont enter or enter just space
		if(shortName.trim() === '' || shortName == null){
			$("#editAgentDialog").find("#errorTxtShortName").text("Please enter office short name!");
			$("#editAgentDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		else if(shortName.length>50){
			$("#editAgentDialog").find("#errorTxtShortName").text("The short name field's length is 50. Your input is over range!");
			$("#editAgentDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}else{//valid input
			$("#editAgentDialog").find("#errorTxtShortName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] agent's long name on keyup function() to check if over range (1 cases)
	 */
	$("#editAgentDialog").find("#txtLongName").on('change keyup paste',function(){
		var longName= $(this).val();
		
		//if input of user is over range
		if(longName.length>100){
			$("#editAgentDialog").find("#errorTxtLongName").text("The long name field's length is 100. Your input is over range!");
			$("#editAgentDialog").find("#errorTxtLongName").css("color", "red");
			$(this).css("background-color", "red");
		}else if(longName.length<101){//valid input
			$("#editAgentDialog").find("#errorTxtLongName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] agent's address on keyup function() to check if over range (1 cases)
	 */
	$("#editAgentDialog").find("#txtAddress").on('change keyup paste',function(){
		var address= $(this).val();
		
		//if input of user is over range
		if(address.length>200){
			$("#editAgentDialog").find("#errorTxtAddress").text("The address field's length is 200. Your input is over range!");
			$("#editAgentDialog").find("#errorTxtAddress").css("color", "red");
			$(this).css("background-color", "red");
		}else if(address.length<201){//valid input
			$("#editAgentDialog").find("#errorTxtAddress").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] agent's tel on keyup function() to check if over range (1 cases)
	 */
	$("#editAgentDialog").find("#txtTel").on('change keyup paste',function(){
		var tel= $(this).val();
		
		//if input of user is over range
		if(tel.length>50){
			$("#editAgentDialog").find("#errorTxtTel").text("The telephone field's length is 50. Your input is over range!");
			$("#editAgentDialog").find("#errorTxtTel").css("color", "red");
			$(this).css("background-color", "red");
		}else if(tel.length<51){//valid input
			$("#editAgentDialog").find("#errorTxtTel").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] agent's fax on keyup function() to check if over range (1 cases)
	 */
	$("#editAgentDialog").find("#txtFax").on('change keyup paste',function(){
		var fax= $(this).val();
		
		//if input of user is over range
		if(fax.length>50){
			$("#editAgentDialog").find("#errorTxtFax").text("The fax field's length is 50. Your input is over range!");
			$("#editAgentDialog").find("#errorTxtFax").css("color", "red");
			$(this).css("background-color", "red");
		}else if(fax.length<51){//valid input
			$("#editAgentDialog").find("#errorTxtFax").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] agent's tax no on keyup function() to check if over range (1 cases)
	 */
	$("#editAgentDialog").find("#txtTaxNo").on('change keyup paste',function(){
		var taxNo= $(this).val();
		
		//if input of user is over range
		if(taxNo.length>50){
			$("#editAgentDialog").find("#errorTxtTaxNo").text("The tax no field's length is 50. Your input is over range!");
			$("#editAgentDialog").find("#errorTxtTaxNo").css("color", "red");
			$(this).css("background-color", "red");
		}else if(taxNo.length<51){//valid input
			$("#editAgentDialog").find("#errorTxtTaxNo").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] agent's remark on keyup function() to check if over range (1 cases)
	 */
	$("#editAgentDialog").find("#txtRemark").on('change keyup paste',function(){
		var remark= $(this).val();
		
		//if input of user is over range
		if(remark.length>500){
			$("#editAgentDialog").find("#errorTxtRemark").text("The remark field's length is 500. Your input is over range!");
			$("#editAgentDialog").find("#errorTxtRemark").css("color", "red");
			$(this).css("background-color", "red");
		}else if(remark.length<501){//valid input
			$("#editAgentDialog").find("#errorTxtRemark").text("");
			$(this).css("background-color", "white");
		}
	});
	
	//
	loadData();
})