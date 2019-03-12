$(document).ready(function(){
	/**
	 * This function is used to load data into table
	 */
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "role/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.roleid),
							$('<td>').text(value.rolename),
							$('<td>').text(value.description==null?'':value.description),
							$('<td>').text(value.creator),
							$('<td>').text(value.createdate==null?'':value.createdate),
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.roleid+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option><option value="Delete">Delete</option>'
									+'<option value="AssignFunction">Assign Function</option></select>')
					).appendTo('#listRole');
				});
				console.log(data);
				action();

				$('#listRole').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	
	/**
	 * This function is used to load list all function
	 */
	function loadCheckBoxFunction(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "function/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				
				$("#listOfFunctions").dataTable().fnDestroy();
				$('#listOfFunctions tbody').empty();
				
				$.each(data.list,function(key,value){
//					$("#assignFunctionDialog").find("#listOfFunctions").append('<input type="checkbox" name="functions" value="'+value.functionid+'" />'+ value.functionname+'<br/>');
					
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.functionname),
							$('<td>').html('<input type="checkbox" name="functions" value="'+value.functionid+'" />')
					).appendTo('#listOfFunctions');
				});
				
				$('#listOfFunctions').DataTable( {
					"pagingType": "full_numbers",
					"bLengthChange": false,
					"bPaginate": false,
//					"searching": false
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	}
	
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
	
	/**
	 * This function is write to handle on change of dropdown list on each record
	 */
	function action(){
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
			var roleIdSeleted= $(this).data('id');//this is RoleID of each record.
		    //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $(".selectOption").val("Options");
		    
		    //If user choose edit option
		    if(valueSelected=="Edit"){
		    	//get detail of 1 Role by call ajax
		    	$.ajax({
		    		dataType: "json",
		    		type: 'POST',
					data: JSON.stringify({
						roleid: roleIdSeleted
					}),
					contentType: "application/json",
					url: getAbsolutePath() +  "role/detail",
					success: function(data){
						if(data.status== "ok"){
							$("#editRoleDialog").find("#txtRoleId").val(data.role.roleid);
							$("#editRoleDialog").find("#txtRoleName").val(data.role.rolename);
							$("#editRoleDialog").find("#txtDescription").val(data.role.description);
						}else{
							alert('This alert should never show!');
						}
					},
					error: function(){
						alert('Cant get role detail!');
					}
		    	});
		    	
		    	//show edit Role dialog.
		    	$("#editRoleDialog").dialog({
		    		modal: true,
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Edit Role",
					height: 500,
					width: 250,
					buttons:{
						"Edit": function(){
							//same with add
							//check if there no required fields left.
//							if(!validateRequiredFieldForEdit()){
//								//when still left required field, set the text of message dialog, after that show it
//								callMessageDialog("Warning message", "Please enter required fields!");
//								//do css in each textBox
//								$("#editRoleDialog").find("#errorTxtRoleName").text("Please enter Role Name!");
//								$("#editRoleDialog").find("#errorTxtRoleName").css("color", "red");
//								$("#editRoleDialog").find("#txtRoleName").css("background-color", "red");
//							}else{
								//when all required fields is ok
//								if(!validateOverRangeWhenAddRole()){
//									//if user input is over range
//									callMessageDialog("Warning message", "Your input is over range!");
//								}else{
									//do edit here
									//get values from input tags, do edit role ($.ajax)
									var roleId= $("#editRoleDialog").find("#txtRoleId").val();
									var roleName= $("#editRoleDialog").find("#txtRoleName").val();
									var description= $("#editRoleDialog").find("#txtDescription").val();
									//alert(roleId+roleName+description);
									$.ajax({
										dataType: "json",
										type: 'POST',
										data:
											JSON.stringify({
												roleid: roleId,
												rolename: roleName,
												description: description
											}),
										contentType: "application/json",
										url: getAbsolutePath() +  "role/edit",//đang fix đến đây
										success: function(data){
											if(data.editStatus == true){
												//if add into db successfully
												callMessageDialog("Message", "Edit role successfully!");
												//close dialog and clear input
												$("#editRoleDialog").dialog("close");
												
												resetAfterEdit();
												
												//after add reload table
												$("#listRole").dataTable().fnDestroy();
												$('#listRole tbody').empty();
												loadData();
											}else if(data.editStatus == false){
												alert('This alert maybe never show! (4)');
											}else{
												alert('This alert maybe never show! (5)');
											}
										},
										error: function(){
											alert('This alert maybe never show! (3)');
										}
									});
									//end edit
//								}
//							}
						},
						"Cancel": function(){
							$("#editRoleDialog").dialog("close");
						}
					},
					hide:{
						effect:"explode",
						duration: 500
					}
		    	});
		    }
		    
		    //if user choose delete option
		    if(valueSelected=="Delete"){
		    	//set confirm message
		    	$("#deleteRoleDialog").find("#messageContent").text('Are you sure you want to delete role "' + roleIdSeleted+'"?');
		    	//show delete Role dialog.
		    	$("#deleteRoleDialog").dialog({
		    		modal: true,
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Delete Role Confirm",
					height: 200,
					width: 400,
					buttons:{
						"OK": function(){
							//do delete when user choose OK here, call ajax
							$.ajax({
								dataType: "json",
								type: 'POST',
								data: JSON.stringify({
									roleid: roleIdSeleted
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "role/delete",
								success: function(data){
									if(data.status=="ok"){
										if(data.deleteStatus== true){
											//show delete status to user
											callMessageDialog("Message", 'Delete role "'+ roleIdSeleted+ '" successfully!');
											//close dialog
											$("#deleteRoleDialog").dialog("close");
											//reload table
											$("#listRole").dataTable().fnDestroy();
											$('#listRole tbody').empty();
											loadData();
										}else if(data.deleteStatus== false){
											callMessageDialog("Message", 'Can\'t delete role "'+ roleIdSeleted+ '"!');
											$("#deleteRoleDialog").dialog("close");
										}else{
											alert("this cant be show, 243!");
										}
									}else{
										alert("unexpected error! (2), 404");
									}
								},
								error: function(){
//									alert("unexpected error!");
									callMessageDialog("Message", 'Can\'t delete role "'+ roleIdSeleted+ '"!');
									$("#deleteRoleDialog").dialog("close");
								}
							});
						},
						"Cancle": function(){
							$("#deleteRoleDialog").dialog("close");
						}
					},
					hide:{
						effect:"explode",
						duration: 500
					}
		    	});
		    }
		    //end if user choose delete option
		    
		    //if use choose AssignFunction
		    if(valueSelected=="AssignFunction"){
		    	//gọi ajax lấy dữ liệu trc
		    	$.ajax({
					dataType: "json",
					type: 'POST',
					data: JSON.stringify({
						roleid: roleIdSeleted
					}),
					contentType: "application/json",
					url: getAbsolutePath() +  "role/detailAndListFunc",
					success: function(data){
						if(data.status=="ok"){
							//gán dữ liệu vô các textBox
							$("#assignFunctionDialog").find("#txtRoleID").val(data.role.roleid);
							$("#assignFunctionDialog").find("#txtRoleName").val(data.role.rolename);
							
							//VERY IMPORTANT
//							$('input[name="functions"]').each(function () {
//						           //if (this.checked) {
//						               console.log($(this).val());
//						               $(this).prop('checked', true);
//						           //}
//							});
							
//							loadCheckBoxFunction();
							
							//lặp qua listFunction của 1 Role, nếu trùng value vs checkBox nào thì check cái cbox đó.
							$.each(data.listFunction,function(key,value){
								//lặp qua danh sách các checkBox trong group
								$('input[name="functions"]').each(function () {
									//this.val() là value của mỗi checkBox trong checkBox group
									if(value.functionid == $(this).val()){
										$(this).prop('checked', true);
									}
								});
							});
							
						}else{
							alert('Lỗi trong code? (Xử lý Assign Functions)');
						}
					},
					error: function(){
						alert('Lỗi 404? (Xử lý Assign Functions)');
					}
		    	});
		    	
		    	//show assign Function Dialog.
		    	$("#assignFunctionDialog").dialog({
		    		modal: true,
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Assign Function",
					height: 550,
					width: 600,
					close: function(){
						//Khi nhấn [x] thì reset hết textBox về chưa check
						$('input[name="functions"]').each(function () {
					           //console.log($(this).val());
				               $(this).prop('checked', false);
						});
					},
					buttons:{
						"Update": function(){
//							//Xử lý khi nhấn nút [update], tạo ra 1 array? để lưu các function đc check
//							var listFunction =[];
//							//duyệt qua r đẩy vô hết trong array
//							$('input[name="functions"]:checked').each(function () {
//								listFunction.push({ 'functionid': $(this).val() });
//							});
//							//console.log(JSON.stringify(listFunction));
//							//lấy giá trị của roleId
//							var chosenRoleId = $("#assignFunctionDialog").find("#txtRoleID").val();
//							
//							//sau khi có array r thì gọi ajax về controller
//							$.ajax({
//								dataType: "json",
//								type: 'POST',
//								data: JSON.stringify(listFunction),
//								contentType: "application/json",
//								url: getAbsolutePath() +  "role/AssignFuncForRole/" + chosenRoleId,
//								success: function(data){
//									if(data.status=="ok"){
//										if(data.assignStatus==true){
//											callMessageDialog("Message", "Assign function successfully!");
//										}else if(data.assignStatus==false){
//											callMessageDialog("Warning Message", "Assign function failed!");
//										}else{
//											alert("never show!");
//										}
//									}else{
//										alert("vấn đề code, 356");
//									}
//								},
//								error: function(){
//									alert("failed on /role/AssignFuncForRole/");
//								}
//							});
//							//kết thúc
							
							/**
							 * Test
							 */
							//Xử lý khi nhấn nút [update], tạo ra 1 array? để lưu các function đc check
							var listFunction =[];
							//duyệt qua r đẩy vô hết trong array
							$('input[name="functions"]:checked').each(function () {
								listFunction.push({ 'functionid': $(this).val() });
							});
							//console.log(JSON.stringify(listFunction));
							//lấy giá trị của roleId
							var chosenRoleId = $("#assignFunctionDialog").find("#txtRoleID").val();
							
							var role = {
									roleid: chosenRoleId,
									listFunction: listFunction
							};
							
							//sau khi có array r thì gọi ajax về controller
							$.ajax({
								dataType: "json",
								type: 'POST',
								data: JSON.stringify(role),
								contentType: "application/json",
								url: getAbsolutePath() +  "role/AssignFuncForRole",
								success: function(data){
									if(data.status=="ok"){
										if(data.assignStatus==true){
											callMessageDialog("Message", "Assign function successfully!");
										}else if(data.assignStatus==false){
											callMessageDialog("Warning Message", "Assign function failed!");
										}else{
											alert("never show!");
										}
									}else{
										alert("vấn đề code, 356");
									}
								},
								error: function(){
									alert("failed on /role/AssignFuncForRole/");
								}
							});
							//kết thúc
							/**
							 * Test
							 */
						},
						"Cancel": function(){
							//Khi nhấn cancel thì reset hết textBox về chưa check r đóng dialog
							$('input[name="functions"]').each(function () {
						           //console.log($(this).val());
					               $(this).prop('checked', false);
							});
							$("#assignFunctionDialog").dialog("close");
						}
					},
					hide:{
						effect:"explode",
						duration: 500
					}
		    	});
		    }
		    //end if use choose AssignFunction
		});
	}

	/**
	 * Function show add dialog
	 */
	$('#btnAddNewRole').click(function(){
		$("#addRoleDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Add New Group",
			height: 500,
			width: 250,
			buttons:{
				"Save": function(){
					
					var roleid = $('#addRoleDialog').find('#txtRoleId').val();
					//check if user have entered all required fields
					if(!validateRequiredFieldForAdd()){
						callMessageDialog("Warning message", "Please enter required fields!");
						
						//if user dont enter code or enter just space
						if(roleid.trim() === '' || roleid == null){
							$("#addRoleDialog").find("#errorTxtRoleId").text("Please enter group id!");
							$("#addRoleDialog").find("#errorTxtRoleId").css("color", "red");
							$("#addRoleDialog").find("#txtRoleId").css("background-color", "red");
						}
					}else{
						if(!validateWhitespace()){
							callMessageDialog("Warning message", "Prefix and suffix don't contain whitespace!");
						}else{
								$.ajax({
									dataType: "json",
									type: 'POST',
									data: JSON.stringify({
										roleid: roleid
									}),
									contentType: "application/json",
									url: getAbsolutePath() +  "role/isExist",
									success: function(data){
										if(data.isExisted==true){
											//if code is existed
											$("#addRoleDialog").find("#errorTxtRoleId").text("Group id is existed, please choose different one!");
											$("#addRoleDialog").find("#errorTxtRoleId").css("color", "red");
											$("#addRoleDialog").find("#txtRoleId").css("background-color", "red");
											
											callMessageDialog("Warning message", "Group id is existed, please choose different one!");
										}else if(data.isExisted==false){
											//alert("all input is ok!");
											$("#addRoleDialog").find("#errorTxtRoleId").text("Group id is valid!");
											$("#addRoleDialog").find("#errorTxtRoleId").css("color", "green");
											$("#addRoleDialog").find("#txtRoleId").css("background-color", "white");
											
											//start adding
											var roleid = $('#addRoleDialog').find('#txtRoleId').val();
											var rolename = $('#addRoleDialog').find('#txtRoleName').val();
											var description = $('#addRoleDialog').find('#txtDescription').val();

											var roleModel = {
													roleid: roleid,
													rolename: rolename,
													description: description
											};
											
											console.log(JSON.stringify(roleModel));
											
											//gọi ajax để add
											$.ajax({
												dataType: "json",
												type: 'POST',
												data: JSON.stringify(roleModel),
												contentType: "application/json",
												url: getAbsolutePath() +  "role/add",
												success: function(data){
													if(data.addStatus==true){
														callMessageDialog("Message", 'Add new role successfully!');
														$("#addRoleDialog").dialog("close");
														resetAfterAdd();
														
														$("#listRole").dataTable().fnDestroy();
														$('#listRole tbody').empty();
														loadData();
													}
												},
												error: function(){
													alert("Lỗi cmnr");
												}
											});
											//end adding
										}
									},error: function(){
										
									}
								});
								//end ajax
						}
					}
				},
				"Cancel": function(){
					$("#addRoleDialog").dialog("close");
					resetAfterAdd();
				}
			},
			close: function(){
				resetAfterAdd();
			}
		});
	});
	/**
	 * end adding
	 */
	
	/**
	 * This function is used to validate required fields before adding
	 */
	function validateRequiredFieldForAdd(){
		var roleid= $("#addRoleDialog").find("#txtRoleId").val();
		if(roleid.trim() === '' || roleid == null)
			return false;
		return true;
	}
	
	/**
	 * Validate if prefix or suffix has space
	 */
	function validateWhitespace(){
		if($("#addRoleDialog").find("#txtRoleId").val().length!=$("#addRoleDialog").find("#txtRoleId").val().trim().length)
			return false;
		return true;
	}
	
	//hàm reset các trường trong bảng sau khi thêm mới
	function resetAfterAdd(){
		$('#addRoleDialog').find('#txtRoleId').val('');
		$('#addRoleDialog').find('#txtRoleName').val('');
		$('#addRoleDialog').find('#txtDescription').val('');
		//$("#tblFactoryContactList > tbody").html("");
		
		//reset css
		$("#addRoleDialog").find("#errorTxtRoleId").text("");
		$("#addRoleDialog").find("#txtRoleId").css("background-color", "white");
		$("#addRoleDialog").find("#errorTxtRoleName").text("");
		$("#addRoleDialog").find("#txtRoleName").css("background-color", "white");
		$("#addRoleDialog").find("#errorTxtDescription").text("");
		$("#addRoleDialog").find("#txtDescription").css("background-color", "white");
	}
	
	//hàm reset các trường trong bảng sau khi thêm mới
	function resetAfterEdit(){
		$('#editRoleDialog').find('#txtRoleId').val('');
		$('#editRoleDialog').find('#txtRoleName').val('');
		$('#editRoleDialog').find('#txtDescription').val('');
		//$("#tblFactoryContactList > tbody").html("");
		
		//reset css
		$("#editRoleDialog").find("#errorTxtRoleId").text("");
		$("#editRoleDialog").find("#txtRoleId").css("background-color", "white");
		$("#editRoleDialog").find("#errorTxtRoleName").text("");
		$("#editRoleDialog").find("#txtRoleName").css("background-color", "white");
		$("#editRoleDialog").find("#errorTxtDescription").text("");
		$("#editRoleDialog").find("#txtDescription").css("background-color", "white");
		
		
	}
	
	//do not allow input " character 
	$("#addRoleDialog").find("#txtRoleId").keydown(function(e) {
		if(e.keyCode==222) return false;
	});
	
	/**
	 * [AddDialog] On keyup role id (4 cases)
	 */
	$("#addRoleDialog").find("#txtRoleId").on('change keyup paste',function(){
		var roleId= $(this).val();
		
		//if user dont enter or enter just space
		if(roleId.trim() === '' || roleId == null){
			$("#addRoleDialog").find("#errorTxtRoleId").text("Please enter group id!");
			$("#addRoleDialog").find("#errorTxtRoleId").css("color", "red");
			$(this).css("background-color", "red");
		}
		
		//if user enter space on prefix, suffix
		if($("#addRoleDialog").find("#txtRoleId").val().length!=$("#addRoleDialog").find("#txtRoleId").val().trim().length){
			$("#addRoleDialog").find("#errorTxtRoleId").text("You can't use space on prefix and suffix!");
			$("#addRoleDialog").find("#errorTxtRoleId").css("color", "red");
			$(this).css("background-color", "red");
		}
		
		//if input of user is over range
//		if(roleId.length>50){
//			$("#addRoleDialog").find("#errorTxtRoleId").text("The username field's length is 50. Your input is over range!");
//			$("#addRoleDialog").find("#errorTxtRoleId").css("color", "red");
//			$(this).css("background-color", "red");
//		}
		
		//check if accessorycode is existed
		$.ajax({
			dataType: "json",
			type: 'POST',
			data: JSON.stringify({
				roleid: roleId
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "role/isExist",
			success: function(data){
				if(data.isExisted==true){// if username is existed, prevent ad add new user
					//if username is existed
					$("#addRoleDialog").find("#errorTxtRoleId").text("Group id is existed, please choose different one!");
					$("#addRoleDialog").find("#errorTxtRoleId").css("color", "red");
					$("#addRoleDialog").find("#txtRoleId").css("background-color", "red");
				}else if(data.isExisted==false&&$("#addRoleDialog").find("#txtRoleId").val().length<21&&
						$("#addRoleDialog").find("#txtRoleId").val().length==$("#addRoleDialog").find("#txtRoleId").val().trim().length){
					//if username is not existed and valid length and not have space at prefix, suffix
					$("#addRoleDialog").find("#errorTxtRoleId").text("Group id is valid!");
					$("#addRoleDialog").find("#errorTxtRoleId").css("color", "green");
					$("#addRoleDialog").find("#txtRoleId").css("background-color", "white");
					//if user dont enter or enter just space
					if(roleId.trim() === '' || roleId == null){
						$("#addRoleDialog").find("#errorTxtRoleId").text("Please enter group id!");
						$("#addRoleDialog").find("#errorTxtRoleId").css("color", "red");
						$(this).css("background-color", "red");
					}
				}
			},
			error: function(){
				//when user clear the code
				$("#addRoleDialog").find("#errorTxtRoleId").text("Please enter group id!");
				$("#addRoleDialog").find("#errorTxtRoleId").css("color", "red");
				$("#addRoleDialog").find("#txtRoleId").css("background-color", "red");
			}
		});
	});
	/**
	 * 
	 */
	
	loadData();
	loadCheckBoxFunction();
})