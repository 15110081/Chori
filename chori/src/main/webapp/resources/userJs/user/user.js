$(document).ready(function(){
	/**
	 * This function is used to load data into table
	 */
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "user/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.username),
							$('<td>').text(value.firstname+' '+value.lastname),
							$('<td>').text(value.email),
							$('<td>').text(value.phone),
							$('<td>').text(value.agentShortname),
							$('<td>').text(value.rolename),
							$('<td>').text(value.status),
//							$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.username+'">Edit</button>'),
//							$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.username+'">Delete</button>'),
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.username+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
					).appendTo('#listUser');
				});
				action();

				$('#listUser').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	
	/**
	 * Binding Action for edit, delete
	 */
//	function action(){
//		//hàm cho nút edit list factory
//		$('.btnEdit').click(function(){
//			
//			//lấy ra data-id
//			var username= $(this).data('id');
//			
//			//lấy thông tin qua id gán vào edit dialog
//			$.ajax({
//				dataType: "json",
//				type: 'GET',
//				data: {},
//				url: "/Chori/user/detail/"+username,
//				contentType: "application/json",
//				success: function(data){
//					$('#editUserDialog').find('#txtUserName').val(data.user.username);
//					$('#editUserDialog').find('#ddlAgent').val(data.user.agentid);
//					$('#editUserDialog').find('#ddlRole').val(data.user.roleid);
////					$('#editUserDialog').find('#txtPassword').val(data.user.password);
//					$('#editUserDialog').find('#txtFirstName').val(data.user.firstname);
//					$('#editUserDialog').find('#txtLastName').val(data.user.lastname);
//					$('#editUserDialog').find('#txtEmail').val(data.user.email);
//					$('#editUserDialog').find('#txtEmailPassword').val(data.user.emailpassword);
//					$('#editUserDialog').find('#txtPhone').val(data.user.phone);
//					$('#editUserDialog').find('#txtStatus').val(data.user.status);
//				},
//				error: function(){
//					alert("Lỗi lấy by id")
//				}
//			});
//			//end lấy thông tin qua id gán vào edit dialog
//			
//			$("#editUserDialog").find("#txtPassword").val('');
//			$("#editUserDialog").find("#txtPassword").prop('disabled', true);
//			
//			$("#editUserDialog").dialog({
//				modal: true,
//				show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Edit User",
//				height: 500,
//				width: 700,
//				buttons:{
//					"Edit": function(){
//						
//						//check if user have entered required fields
//						if(!validateRequiredFieldForEdit()){
//							callMessageDialog("Warning message", "Please enter required fields!");
//							
//							var password= $('#editUserDialog').find('#txtPassword').val();
//							
//							//if user dont enter name or enter just space
//							if(password.trim() === '' || password == null){
//								$("#editUserDialog").find("#errorTxtPassword").text("Please enter password!");
//								$("#editUserDialog").find("#errorTxtPassword").css("color", "red");
//								$("#editUserDialog").find("#txtPassword").css("background-color", "red");
//							}
//						}else{
//							//if user have entered all required fields, then check if input is overRange
//							if(!validateOverRangeWhenEdit()){
//								callMessageDialog("Warning message", "Your input is over range!");
//							}else{
//								if(!validateWhitespaceEdit()){
//									callMessageDialog("Warning message", "Prefix and suffix don't contain whitespace!");
//								}else{
//									//start editing
////									$("#editUserDialog").find("#errorTxtUserName").text("Username is valid!");
////									$("#editUserDialog").find("#errorTxtUserName").css("color", "green");
////									$("#editUserDialog").find("#txtUserName").css("background-color", "white");
//									
//									//start adding
//									var username = $('#editUserDialog').find('#txtUserName').val();
//									var agentid = $('#editUserDialog').find('#ddlAgent').val();
//									var roleid = $('#editUserDialog').find('#ddlRole').val();
//									var password = $('#editUserDialog').find('#txtPassword').val();
//									var firstname = $('#editUserDialog').find('#txtFirstName').val();
//									var lastname = $('#editUserDialog').find('#txtLastName').val();
//									var email = $('#editUserDialog').find('#txtEmail').val();
//									var phone = $('#editUserDialog').find('#txtPhone').val();
//									var status = $('#editUserDialog').find('#txtStatus').val();
//
//									var userModel = {
//											username: username,
//											agentid: agentid,
//											roleid: roleid,
//											password: password,
//											firstname: firstname,
//											lastname: lastname,
//											email: email,
//											phone: phone,
//											status: status
//									};
//									
//									console.log(JSON.stringify(userModel));
//									
//									//gọi ajax để add
//									$.ajax({
//										dataType: 'json',
//										type: 'POST',
//										data: JSON.stringify(userModel),
//										contentType: 'application/json',
//										url: '/Chori/user/edit',
//										success: function(data){
//											if(data.editStatus==true){
//												reloadTableWithStatus();
//												callMessageDialog("Message", 'Edit User successfully!');
//												$("#editUserDialog").dialog("close");
//												resetAfterEdit();
//											}
//										},
//										error: function(){
//											alert("Lỗi cmnr 158");
//										}
//									});
//									//end adding
//									//end editing
//								}
//							}
//						}
//						//
//					},
//					"Cancel": function(){
//						$("#editUserDialog").dialog("close");
//						//Xóa các thông tin trong bảng contact
//						resetAfterEdit();
//						//uncheck và xóa nội dung password
//						$("#editUserDialog").find("#txtPassword").val('');
//						$("#editUserDialog").find("#txtPassword").prop('disabled', true);
//						$("#editUserDialog").find("#chkReset").prop('checked', false);
//					}
//				},
//				close: function(){
//					resetAfterEdit();
//					//uncheck và xóa nội dung password
//					$("#editUserDialog").find("#txtPassword").val('');
//					$("#editUserDialog").find("#txtPassword").prop('disabled', true);
//					$("#editUserDialog").find("#chkReset").prop('checked', false);
//				}
//			});
//		});
//		
//		//Delete handling
//		$('.btnDelete').click(function(){
//			var username= $(this).data('id');
//			$("#deleteUserDialog").find("#messageContent").text('Are you sure you want to delete User "' + username+'"?');
//			
//			$("#deleteUserDialog").dialog({
//				modal: true,
//	    		show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Delete User Confirm",
//				height: 200,
//				width: 400,
//				buttons:{
//					"OK": function(){
//						$.ajax({
//							dataType: "json",
//							type: 'POST',
//							data:{},
//							contentType: "application/json",
//							url: "/Chori/user/delete/" + username,
//							success: function(data){
//								if(data.status=="ok"){
//									if(data.deleteStatus== true){
//										//show delete status to user
//										callMessageDialog("Message", 'Delete User "'+ username+ '" successfully!');
//										//close dialog
//										$("#deleteUserDialog").dialog("close");
//										//reload table
//										reloadTableWithStatus();
//									}else if(data.deleteStatus== false){
//										callMessageDialog("Message", 'Can\'t Delete User "'+ username+ '"!');
//										$("#deleteUserDialog").dialog("close");
//									}else{
//										alert("this cant be show, 243!");
//									}
//								}else{
//									alert("unexpected error! (2), 404");
//								}
//							},
//							error: function(){
////								alert("unexpected error!");
//								callMessageDialog("Message", 'Can\'t Delete User "'+ username+ '"!');
//								$("#deleteUserDialog").dialog("close");
//							}
//						});
//					},
//					"Cancel": function(){
//						$("#deleteUserDialog").dialog("close");
//					}
//				}
//			});
//		});
//	};
	
	function action(){
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    var username= $(this).data('id');//this is RoleID of each record.
		    //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $(".selectOption").val("Options");
		    
		    
		    //If user choose edit option
		    if(valueSelected=="Edit"){
		    	//lấy ra data-id
//				var username= $(this).data('id');
				
				//lấy thông tin qua id gán vào edit dialog
				$.ajax({
					dataType: "json",
					type: 'POST',
					data: JSON.stringify({
						username: username
					}),
					url: getAbsolutePath() +  "user/detail",
					contentType: "application/json",
					success: function(data){
						$('#editUserDialog').find('#txtUserName').val(data.user.username);
						$('#editUserDialog').find('#ddlAgent').val(data.user.agentid);
						$('#editUserDialog').find('#ddlRole').val(data.user.roleid);
						if(data.user.ccmailstring)
							$('#editUserDialog').find('#chkCCmailstring').prop('checked', true);
						$('#editUserDialog').find('#txtFirstName').val(data.user.firstname);
						$('#editUserDialog').find('#txtLastName').val(data.user.lastname);
						$('#editUserDialog').find('#txtEmail').val(data.user.email);
						$('#editUserDialog').find('#txtEmailPassword').val(data.user.emailpassword);
						$('#editUserDialog').find('#txtPhone').val(data.user.phone);
						$('#editUserDialog').find('#txtStatus').val(data.user.status);
					},
					error: function(){
						alert("Lỗi lấy by id")
					}
				});
				//end lấy thông tin qua id gán vào edit dialog
				
				$("#editUserDialog").find("#txtPassword").val('');
				$("#editUserDialog").find("#txtPassword").prop('disabled', true);
				
				$("#editUserDialog").dialog({
					modal: true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Edit User",
					height: 500,
					width: 700,
					buttons:{
						"Edit": function(){
							
							//check if user have entered required fields
							if(!validateRequiredFieldForEdit()){
								callMessageDialog("Warning message", "Please enter required fields!");
								
								var password= $('#editUserDialog').find('#txtPassword').val();
								
								//if user dont enter name or enter just space
								if(password.trim() === '' || password == null){
									$("#editUserDialog").find("#errorTxtPassword").text("Please enter password!");
									$("#editUserDialog").find("#errorTxtPassword").css("color", "red");
									$("#editUserDialog").find("#txtPassword").css("background-color", "red");
								}
							}else{
								//if user have entered all required fields, then check if input is overRange
								if(!validateOverRangeWhenEdit()){
									callMessageDialog("Warning message", "Your input is over range!");
								}else{
									if(!validateWhitespaceEdit()){
										callMessageDialog("Warning message", "Prefix and suffix don't contain whitespace!");
									}else{
										//start editing
//										$("#editUserDialog").find("#errorTxtUserName").text("Username is valid!");
//										$("#editUserDialog").find("#errorTxtUserName").css("color", "green");
//										$("#editUserDialog").find("#txtUserName").css("background-color", "white");
										
										//start adding
										var username = $('#editUserDialog').find('#txtUserName').val();
										var agentid = $('#editUserDialog').find('#ddlAgent').val();
										var roleid = $('#editUserDialog').find('#ddlRole').val();
										var password = $('#editUserDialog').find('#txtPassword').val();
										var firstname = $('#editUserDialog').find('#txtFirstName').val();
										var lastname = $('#editUserDialog').find('#txtLastName').val();
										var email = $('#editUserDialog').find('#txtEmail').val();
										var phone = $('#editUserDialog').find('#txtPhone').val();
										var status = $('#editUserDialog').find('#txtStatus').val();
//										var ccmailstring = $('#editUserDialog').find('#txtCCEmail').val();
										var ccmailstring = false;
										if($('#editUserDialog').find('#chkCCmailstring').is(':checked'))
											ccmailstring = true;

										var userModel = {
												username: username,
												agentid: agentid,
												roleid: roleid,
												password: password,
												firstname: firstname,
												lastname: lastname,
												email: email,
												phone: phone,
												status: status,
												ccmailstring: ccmailstring
										};
										
										console.log(JSON.stringify(userModel));
										
										//gọi ajax để add
										$.ajax({
											dataType: 'json',
											type: 'POST',
											data: JSON.stringify(userModel),
											contentType: 'application/json',
											url: getAbsolutePath() +  'user/edit',
											success: function(data){
												if(data.editStatus==true){
													reloadTableWithStatus();
													callMessageDialog("Message", 'Edit user successfully!');
													$("#editUserDialog").dialog("close");
													resetAfterEdit();
												}else if(data.editStatus==='invalid'){
													callMessageDialog("Message", "Invalid input!");
												}
											},
											error: function(){
												alert("Lỗi cmnr 158");
											}
										});
										//end adding
										//end editing
									}
								}
							}
							//
						},
						"Cancel": function(){
							$("#editUserDialog").dialog("close");
							//Xóa các thông tin trong bảng contact
							resetAfterEdit();
							//uncheck và xóa nội dung password
							$("#editUserDialog").find("#txtPassword").val('');
							$("#editUserDialog").find("#txtPassword").prop('disabled', true);
							$("#editUserDialog").find("#chkReset").prop('checked', false);
						}
					},
					close: function(){
						resetAfterEdit();
						//uncheck và xóa nội dung password
						$("#editUserDialog").find("#txtPassword").val('');
						$("#editUserDialog").find("#txtPassword").prop('disabled', true);
						$("#editUserDialog").find("#chkReset").prop('checked', false);
					}
				});
		    };
		    
		    //if user choose delete option
		    if(valueSelected=="Delete"){
		    	$("#deleteUserDialog").find("#messageContent").text('Are you sure you want to delete user "' + username+'"?');
				
				$("#deleteUserDialog").dialog({
					modal: true,
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Delete User Confirm",
					height: 200,
					width: 400,
					buttons:{
						"OK": function(){
							$.ajax({
								dataType: "json",
								type: 'POST',
								data: JSON.stringify({
									username: username
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "user/delete",
								success: function(data){
									if(data.status=="ok"){
										if(data.deleteStatus== true){
											//show delete status to user
											callMessageDialog("Message", 'Delete user "'+ username+ '" successfully!');
											//close dialog
											$("#deleteUserDialog").dialog("close");
											//reload table
											reloadTableWithStatus();
										}else if(data.deleteStatus== false){
											callMessageDialog("Message", 'Can\'t delete user "'+ username+ '"!');
											$("#deleteUserDialog").dialog("close");
										}else{
											alert("this cant be show, 243!");
										}
									}else{
										alert("unexpected error! (2), 404");
									}
								},
								error: function(){
//									alert("unexpected error!");
									callMessageDialog("Message", 'Can\'t delete user "'+ username+ '"!');
									$("#deleteUserDialog").dialog("close");
								}
							});
						},
						"Cancel": function(){
							$("#deleteUserDialog").dialog("close");
						}
					}
				});
		    };
		    //end if user choose delete option
		    
		});
	};
	
	//hàm reset các trường trong bảng sau khi edit
	function resetAfterEdit(){
		$('#editUserDialog').find('#txtUserName').val('');
		$('#editUserDialog').find('#txtPassword').val('');
		$('#editUserDialog').find('#txtFirstName').val('');
		$('#editUserDialog').find('#txtLastName').val('');
		$('#editUserDialog').find('#txtEmail').val('');
		$('#editUserDialog').find('#txtEmailPassword').val('');
		$('#editUserDialog').find('#txtPhone').val('');
		$('#editUserDialog').find('#ddlAgent').val('');
		$('#editUserDialog').find('#ddlRole').val('');
		$('#editUserDialog').find('#txtStatus').val('');
//		$('#editUserDialog').find('#txtCCEmail').val('');
		$('#editUserDialog').find('#chkCCmailstring').prop('checked', false);
		//$("#tblFactoryContactList > tbody").html("");
		
		//reset css
		$("#editUserDialog").find("#errorTxtUserName").text("");
		$("#editUserDialog").find("#txtUserName").css("background-color", "white");
		$("#editUserDialog").find("#errorTxtPassword").text("");
		$("#editUserDialog").find("#txtPassword").css("background-color", "white");
		$("#editUserDialog").find("#errorTxtFirstName").text("");
		$("#editUserDialog").find("#txtFirstName").css("background-color", "white");
		$("#editUserDialog").find("#errorTxtLastName").text("");
		$("#editUserDialog").find("#txtLastName").css("background-color", "white");
		$("#editUserDialog").find("#errorTxtEmail").text("");
		$("#editUserDialog").find("#txtEmail").css("background-color", "white");
		$("#editUserDialog").find("#errorTxtEmailPassword").text("");
		$("#editUserDialog").find("#txtEmailPassword").css("background-color", "white");
		$("#editUserDialog").find("#errorTxtPhone").text("");
		$("#editUserDialog").find("#txtPhone").css("background-color", "white");
	}
	
	/**
	 * This function is used to change table data when choose status
	 */
	$('#ddlStatus').on('change',function(){
		//get selected value
		var optionSelected = $(this).find("option:selected");
		var valueSelected  = optionSelected.val();
		
		//
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "user/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listUser").dataTable().fnDestroy();
				$('#listUser tbody').empty();
				
				var i = 1;
				if(data.list.length==0){
					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					
					//nếu value là all thì load tất cả
					if(valueSelected=='All'){
						
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.username),
								$('<td>').text(value.firstname+' '+value.lastname),
								$('<td>').text(value.email),
								$('<td>').text(value.phone),
								$('<td>').text(value.agentShortname),
								$('<td>').text(value.rolename),
								$('<td>').text(value.status),
//								$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.username+'">Edit</button>'),
//								$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.username+'">Delete</button>')
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.username+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
						).appendTo('#listUser');
						
					}else{//không thì xuất ra theo cái status
						
						if(valueSelected==value.status){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.username),
									$('<td>').text(value.firstname+' '+value.lastname),
									$('<td>').text(value.email),
									$('<td>').text(value.phone),
									$('<td>').text(value.agentShortname),
									$('<td>').text(value.rolename),
									$('<td>').text(value.status),
//									$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.username+'">Edit</button>'),
//									$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.username+'">Delete</button>')
									$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.username+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option></select>')
							).appendTo('#listUser');
						}
					}
				});
				action();

				$('#listUser').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
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
			url: getAbsolutePath() +  "user/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listUser").dataTable().fnDestroy();
				$('#listUser tbody').empty();
				
				var i = 1;
				if(data.list.length==0){
					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					
					//nếu value là all thì load tất cả
					if(valueSelected=='All'){
						
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.username),
								$('<td>').text(value.firstname+' '+value.lastname),
								$('<td>').text(value.email),
								$('<td>').text(value.phone),
								$('<td>').text(value.agentShortname),
								$('<td>').text(value.rolename),
								$('<td>').text(value.status),
//								$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.username+'">Edit</button>'),
//								$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.username+'">Delete</button>')
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.username+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
						).appendTo('#listUser');
						
					}else{//không thì xuất ra theo cái status
						
						if(valueSelected==value.status){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.username),
									$('<td>').text(value.firstname+' '+value.lastname),
									$('<td>').text(value.email),
									$('<td>').text(value.phone),
									$('<td>').text(value.agentShortname),
									$('<td>').text(value.rolename),
									$('<td>').text(value.status),
//									$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.username+'">Edit</button>'),
//									$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.username+'">Delete</button>')
									$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.username+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option></select>')
							).appendTo('#listUser');
						}
					}
				});
				action();

				$('#listUser').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	
	/**
	 * Function show add dialog
	 */
	$('#btnAddNewUser').click(function(){
		$("#addUserDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Add New User",
			height: 500,
			width: 700,
			buttons:{
				"Save": function(){
					
					var username = $('#addUserDialog').find('#txtUserName').val();
					//check if user have entered all required fields
					if(!validateRequiredFieldForAdd()){
						callMessageDialog("Warning message", "Please enter required fields!");
						
						//get value of username and password
						var usernameEnter = $('#addUserDialog').find('#txtUserName').val();
						var password= $('#addUserDialog').find('#txtPassword').val();
						
						//if user dont enter code or enter just space
						if(usernameEnter.trim() === '' || usernameEnter == null){
							$("#addUserDialog").find("#errorTxtUserName").text("Please enter user name!");
							$("#addUserDialog").find("#errorTxtUserName").css("color", "red");
							$("#addUserDialog").find("#txtUserName").css("background-color", "red");
						}
						//if user dont enter name or enter just space
						if(password.trim() === '' || password == null){
							$("#addUserDialog").find("#errorTxtPassword").text("Please enter password!");
							$("#addUserDialog").find("#errorTxtPassword").css("color", "red");
							$("#addUserDialog").find("#txtPassword").css("background-color", "red");
						}
					}else{
						if(!validateWhitespace()){
							callMessageDialog("Warning message", "Prefix and suffix don't contain whitespace!");
						}else{
							//if user have entered all required fields, then check if input is overRange
							if(!validateOverRangeWhenAdd()){
								callMessageDialog("Warning message", "Your input is over range!");
							}else{//check if the code is not existed
								$.ajax({
									dataType: "json",
									type: 'POST',
									data: JSON.stringify({
										username: username
									}),
									contentType: "application/json",
									url: getAbsolutePath() +  "user/isExist",
									success: function(data){
										if(data.isExisted==true){
											//if code is existed
											$("#addUserDialog").find("#errorTxtUserName").text("Username you enter is existed, please choose different one!");
											$("#addUserDialog").find("#errorTxtUserName").css("color", "red");
											$("#addUserDialog").find("#txtUserName").css("background-color", "red");
										}else if(data.isExisted==false){
											//alert("all input is ok!");
											$("#addUserDialog").find("#errorTxtUserName").text("Username is valid!");
											$("#addUserDialog").find("#errorTxtUserName").css("color", "green");
											$("#addUserDialog").find("#txtUserName").css("background-color", "white");
											
											//start adding
											var username = $('#addUserDialog').find('#txtUserName').val();
											var agentid = $('#addUserDialog').find('#ddlAgent').val();
											var roleid = $('#addUserDialog').find('#ddlRole').val();
											var password = $('#addUserDialog').find('#txtPassword').val();
											var firstname = $('#addUserDialog').find('#txtFirstName').val();
											var lastname = $('#addUserDialog').find('#txtLastName').val();
											var email = $('#addUserDialog').find('#txtEmail').val();
//											var ccmailstring = $('#addUserDialog').find('#txtCCEmail').val();
											var phone = $('#addUserDialog').find('#txtPhone').val();
											var status = $('#addUserDialog').find('#txtStatus').val();
											var ccmailstring = false;
											if($('#addUserDialog').find('#chkCCmailstring').is(':checked'))
												ccmailstring = true;

											var userModel = {
													username: username,
													agentid: agentid,
													roleid: roleid,
													password: password,
													firstname: firstname,
													lastname: lastname,
													email: email,
													ccmailstring: ccmailstring,
													phone: phone,
													status: status
											};
											
											console.log(JSON.stringify(userModel));
											
											//gọi ajax để add
											$.ajax({
												dataType: "json",
												type: 'POST',
												data: JSON.stringify(userModel),
												contentType: "application/json",
												url: getAbsolutePath() +  "user/add",
												success: function(data){
													if(data.addStatus==true){
														reloadTableWithStatus();
														callMessageDialog("Message", 'Add new user successfully!');
														$("#addUserDialog").dialog("close");
														resetAfterAdd();
													}else if(data.addStatus==='invalid'){
														callMessageDialog("Message", "Invalid input!");
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
							}
						}
					}
				},
				"Cancel": function(){
					$("#addUserDialog").dialog("close");
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
		$('#addUserDialog').find('#txtUserName').val('');
		$('#addUserDialog').find('#txtPassword').val('');
		$('#addUserDialog').find('#txtFirstName').val('');
		$('#addUserDialog').find('#txtLastName').val('');
		$('#addUserDialog').find('#txtEmail').val('');
		$('#addUserDialog').find('#txtEmailPassword').val('');
		$('#addUserDialog').find('#txtPhone').val('');
		$('#addUserDialog').find('#ddlAgent').val('');
		$('#addUserDialog').find('#ddlRole').val('');
		$('#addUserDialog').find('#txtStatus').val('');
		$('#addUserDialog').find('#chkCCmailstring').prop('checked', false);
		//$("#tblFactoryContactList > tbody").html("");
		
		//reset css
		$("#addUserDialog").find("#errorTxtUserName").text("");
		$("#addUserDialog").find("#txtUserName").css("background-color", "white");
		$("#addUserDialog").find("#errorTxtPassword").text("");
		$("#addUserDialog").find("#txtPassword").css("background-color", "white");
		$("#addUserDialog").find("#errorTxtFirstName").text("");
		$("#addUserDialog").find("#txtFirstName").css("background-color", "white");
		$("#addUserDialog").find("#errorTxtLastName").text("");
		$("#addUserDialog").find("#txtLastName").css("background-color", "white");
		$("#addUserDialog").find("#errorTxtEmail").text("");
		$("#addUserDialog").find("#txtEmail").css("background-color", "white");
		$("#addUserDialog").find("#errorTxtEmailPassword").text("");
		$("#addUserDialog").find("#txtEmailPassword").css("background-color", "white");
		$("#addUserDialog").find("#errorTxtPhone").text("");
		$("#addUserDialog").find("#txtPhone").css("background-color", "white");
	}
	
	/**
	 * This function is used to load data into dropDownLists
	 */
	function loadAllDropDownList() {
		
		//call ajax and load data into #ddlAgent
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "agent/list",
			contentType: "application/json",
			success: function(data){
				$.each(data.list,function(key,value){
					$('#ddlAgent').append($('<option>', {
						value : value.agentcode,
						text : value.shortname
					}));
					//edit part
					$("#editUserDialog").find('#ddlAgent').append($('<option>', {
						value : value.agentcode,
						text : value.shortname
					}));
				});
			},
			error: function(){
				alert('Lỗi load dữ liệu unit (40)!');
			}
		});
		
		//call ajax and load data into #ddlAgent
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "role/list",
			contentType: "application/json",
			success: function(data){
				$.each(data.list,function(key,value){
					$('#ddlRole').append($('<option>', {
						value : value.roleid,
						text : value.rolename
					}));
					//edit part
					$("#editUserDialog").find('#ddlRole').append($('<option>', {
						value : value.roleid,
						text : value.rolename
					}));
				});
			},
			error: function(){
				alert('Lỗi load dữ liệu unit (40)!');
			}
		});
		
	}
	
	$('#editUserDialog').find('#chkReset').on('click', function () {
		if($(this).is(':checked')){
			$("#editUserDialog").find("#txtPassword").prop('disabled', false);
		}else if(!$(this).is(':checked')){
			$("#editUserDialog").find("#txtPassword").val('');
			$("#editUserDialog").find("#txtPassword").prop('disabled', true);
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
					$(this).dialog("close");
				}
			}
		});
	}
	
	/**
	 *---------------------------------------------------
	 *
	 * Functions handle adding
	 */
	
	/**
	 * This function is used to validate required fields before adding
	 */
	function validateRequiredFieldForAdd(){
		var username= $("#addUserDialog").find("#txtUserName").val();
		if(username.trim() === '' || username == null)
			return false;
		
		var password= $("#addUserDialog").find("#txtPassword").val();
		if(password.trim() === '' || password == null)
			return false;
		
		return true;
	}
	
	/**
	 * Validate if prefix or suffix has space
	 */
	function validateWhitespace(){
		if($("#addUserDialog").find("#txtUserName").val().length!=$("#addUserDialog").find("#txtUserName").val().trim().length||
				$("#addUserDialog").find("#txtPassword").val().length!=$("#addUserDialog").find("#txtPassword").val().trim().length||
				$("#addUserDialog").find("#txtFirstName").val().length!=$("#addUserDialog").find("#txtFirstName").val().trim().length||
				$("#addUserDialog").find("#txtLastName").val().length!=$("#addUserDialog").find("#txtLastName").val().trim().length||
				$("#addUserDialog").find("#txtEmail").val().length!=$("#addUserDialog").find("#txtEmail").val().trim().length||
//				$("#addUserDialog").find("#txtEmailPassword").val().length!=$("#addUserDialog").find("#txtEmailPassword").val().trim().length||
				$("#addUserDialog").find("#txtPhone").val().length!=$("#addUserDialog").find("#txtPhone").val().trim().length)
			return false;
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before add new
	 */
	function validateOverRangeWhenAdd(){
		if($("#addUserDialog").find("#txtUserName").val().length>50 || $("#addUserDialog").find("#txtPassword").val().length>50 
				|| $("#addUserDialog").find("#txtFirstName").val().length>50 || $("#addUserDialog").find("#txtLastName").val().length>50
				|| $("#addUserDialog").find("#txtEmail").val().length>50 
//				|| $("#addUserDialog").find("#txtEmailPassword").val().length>50
				|| $("#addUserDialog").find("#txtPhone").val().length>20)
			return false;
		return true;
	}
	
	//do not allow input " character 
	$("#addUserDialog").find("#txtUserName").keydown(function(e) {
		if(e.keyCode==222) return false;
	});
	
	/**
	 * [AddDialog] On keyup user name (4 cases)
	 */
	$("#addUserDialog").find("#txtUserName").on('change keyup paste',function(){
		var username= $(this).val();
		
		//if user dont enter or enter just space
		if(username.trim() === '' || username == null){
			$("#addUserDialog").find("#errorTxtUserName").text("Please enter username!");
			$("#addUserDialog").find("#errorTxtUserName").css("color", "red");
			$(this).css("background-color", "red");
		}
		
		//if user enter space on prefix, suffix
		if($("#addUserDialog").find("#txtUserName").val().length!=$("#addUserDialog").find("#txtUserName").val().trim().length){
			$("#addUserDialog").find("#errorTxtUserName").text("You can't use space on prefix and suffix!");
			$("#addUserDialog").find("#errorTxtUserName").css("color", "red");
			$(this).css("background-color", "red");
		}
		
		//if input of user is over range
		if(username.length>50){
			$("#addUserDialog").find("#errorTxtUserName").text("The username field's length is 50. Your input is over range!");
			$("#addUserDialog").find("#errorTxtUserName").css("color", "red");
			$(this).css("background-color", "red");
		}
		
		//check if accessorycode is existed
		$.ajax({
			dataType: "json",
			type: 'POST',
			data: JSON.stringify({
				username: username
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "user/isExist",
			success: function(data){
				if(data.isExisted==true){// if username is existed, prevent ad add new user
					//if username is existed
					$("#addUserDialog").find("#errorTxtUserName").text("username you enter is existed, please choose different one!");
					$("#addUserDialog").find("#errorTxtUserName").css("color", "red");
					$("#addUserDialog").find("#txtUserName").css("background-color", "red");
				}else if(data.isExisted==false&&$("#addUserDialog").find("#txtUserName").val().length<51&&
						$("#addUserDialog").find("#txtUserName").val().length==$("#addUserDialog").find("#txtUserName").val().trim().length){
					//if username is not existed and valid length and not have space at prefix, suffix
					$("#addUserDialog").find("#errorTxtUserName").text("username is valid!");
					$("#addUserDialog").find("#errorTxtUserName").css("color", "green");
					$("#addUserDialog").find("#txtUserName").css("background-color", "white");
					//if user dont enter or enter just space
					if(username.trim() === '' || username == null){
						$("#addUserDialog").find("#errorTxtUserName").text("Please enter username!");
						$("#addUserDialog").find("#errorTxtUserName").css("color", "red");
						$(this).css("background-color", "red");
					}
				}
			},
			error: function(){
				//when user clear the code
				$("#addUserDialog").find("#errorTxtUserName").text("Please enter username!");
				$("#addUserDialog").find("#errorTxtUserName").css("color", "red");
				$("#addUserDialog").find("#txtUserName").css("background-color", "red");
			}
		});
	});
	
	/**
	 * password on keyup function() to check if user entered, over range, white space (3 cases)
	 */
	$("#addUserDialog").find("#txtPassword").on('change keyup paste',function(){
		var password= $(this).val();
		
		//if user dont enter or enter just space
		if(password.trim() === '' || password == null){
			$("#addUserDialog").find("#errorTxtPassword").text("Please enter password!");
			$("#addUserDialog").find("#errorTxtPassword").css("color", "red");
			$(this).css("background-color", "red");
		}else if($("#addUserDialog").find("#txtPassword").val().length!=$("#addUserDialog").find("#txtPassword").val().trim().length){
			//if user enter space on prefix, suffix
				$("#addUserDialog").find("#errorTxtPassword").text("You can't use space on prefix and suffix!");
				$("#addUserDialog").find("#errorTxtPassword").css("color", "red");
				$(this).css("background-color", "red");
			}
		//if input of user is over range
		else if(password.length>50){
			$("#addUserDialog").find("#errorTxtPassword").text("The password field's length is 50. Your input is over range!");
			$("#addUserDialog").find("#errorTxtPassword").css("color", "red");
			$(this).css("background-color", "red");
		}else{//valid input
			$("#addUserDialog").find("#errorTxtPassword").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * first name on keyup function() to check if over range, no white space (1 cases)
	 */
	$("#addUserDialog").find("#txtFirstName").on('change keyup paste',function(){
		var firstname= $(this).val();
		
		//if input of user is over range
		if(firstname.length>50){
			$("#addUserDialog").find("#errorTxtFirstName").text("The first name field's length is 50. Your input is over range!");
			$("#addUserDialog").find("#errorTxtFirstName").css("color", "red");
			$(this).css("background-color", "red");
		}else if($("#addUserDialog").find("#txtFirstName").val().length!=$("#addUserDialog").find("#txtFirstName").val().trim().length){
			//if user enter space on prefix, suffix
			$("#addUserDialog").find("#errorTxtFirstName").text("You can't use space on prefix and suffix!");
			$("#addUserDialog").find("#errorTxtFirstName").css("color", "red");
			$(this).css("background-color", "red");
		}else if(firstname.length<51){//valid input
			$("#addUserDialog").find("#errorTxtFirstName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * last name on keyup function() to check if over range, no white space (1 cases)
	 */
	$("#addUserDialog").find("#txtLastName").on('change keyup paste',function(){
		var lastname= $(this).val();
		
		//if input of user is over range
		if(lastname.length>50){
			$("#addUserDialog").find("#errorTxtLastName").text("The last name field's length is 50. Your input is over range!");
			$("#addUserDialog").find("#errorTxtLastName").css("color", "red");
			$(this).css("background-color", "red");
		}else if($("#addUserDialog").find("#txtLastName").val().length!=$("#addUserDialog").find("#txtLastName").val().trim().length){
			//if user enter space on prefix, suffix
			$("#addUserDialog").find("#errorTxtLastName").text("You can't use space on prefix and suffix!");
			$("#addUserDialog").find("#errorTxtLastName").css("color", "red");
			$(this).css("background-color", "red");
		}else if(lastname.length<51){//valid input
			$("#addUserDialog").find("#errorTxtLastName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * email on keyup function() to check if over range, no white space (1 cases)
	 */
	$("#addUserDialog").find("#txtEmail").on('change keyup paste',function(){
		var email= $(this).val();
		
		//if input of user is over range
		if(email.length>50){
			$("#addUserDialog").find("#errorTxtEmail").text("The email field's length is 50. Your input is over range!");
			$("#addUserDialog").find("#errorTxtEmail").css("color", "red");
			$(this).css("background-color", "red");
		}else{
			if($("#addUserDialog").find("#txtEmail").val().length!=$("#addUserDialog").find("#txtEmail").val().trim().length){
				//if user enter space on prefix, suffix
				$("#addUserDialog").find("#errorTxtEmail").text("You can't use space on prefix and suffix!");
				$("#addUserDialog").find("#errorTxtEmail").css("color", "red");
				$(this).css("background-color", "red");
			}else {
				if(!isValidationEmail(email)){//valid input
					$("#addUserDialog").find("#errorTxtEmail").text("Email pattern is wrong!");
					$("#addUserDialog").find("#errorTxtEmail").css("color", "red");
					$(this).css("background-color", "red");
				}else {
					if(email.length<51){//valid input
						$("#addUserDialog").find("#errorTxtEmail").text("");
						$(this).css("background-color", "white");
					}
				}
			}
		} 
	});
	
	/**
	 * email's password on keyup function() to check if over range, no white space (1 cases)
	 */
	$("#addUserDialog").find("#txtEmailPassword").on('change keyup paste',function(){
		var emailPass= $(this).val();
		
		//if input of user is over range
		if(emailPass.length>50){
			$("#addUserDialog").find("#errorTxtEmailPassword").text("The email's password field's length is 50. Your input is over range!");
			$("#addUserDialog").find("#errorTxtEmailPassword").css("color", "red");
			$(this).css("background-color", "red");
		}else if($("#addUserDialog").find("#txtEmailPassword").val().length!=$("#addUserDialog").find("#txtEmailPassword").val().trim().length){
			//if user enter space on prefix, suffix
			$("#addUserDialog").find("#errorTxtEmailPassword").text("You can't use space on prefix and suffix!");
			$("#addUserDialog").find("#errorTxtEmailPassword").css("color", "red");
			$(this).css("background-color", "red");
		}else if(emailPass.length<51){//valid input
			$("#addUserDialog").find("#errorTxtEmailPassword").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * phone on keyup function() to check if over range, no white space (1 cases)
	 */
	$("#addUserDialog").find("#txtPhone").on('change keyup paste',function(){
		var phone= $(this).val();
		
		//if input of user is over range
		if(phone.length>20){
			$("#addUserDialog").find("#errorTxtPhone").text("The phone field's length is 20. Your input is over range!");
			$("#addUserDialog").find("#errorTxtPhone").css("color", "red");
			$(this).css("background-color", "red");
		}else if($("#addUserDialog").find("#txtPhone").val().length!=$("#addUserDialog").find("#txtPhone").val().trim().length){
			//if user enter space on prefix, suffix
			$("#addUserDialog").find("#errorTxtPhone").text("You can't use space on prefix and suffix!");
			$("#addUserDialog").find("#errorTxtPhone").css("color", "red");
			$(this).css("background-color", "red");
		}else if(phone.length<21){//valid input
			$("#addUserDialog").find("#errorTxtPhone").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * this function validate email
	 */
	 function isValidationEmail($email) {
		 var emailReg = /^([\w-\.]+@([\w-])+([\w-]+\.)+[\w-]{2,4})?$/;
		 return emailReg.test( $email );
	 }
	
	 /**
	  * -----------------------------
	  * End add part
	  */
	 
	 /**
	 * This function is used to validate required fields before editing
	 */
	function validateRequiredFieldForEdit(){
		
//		var password= $("#editUserDialog").find("#txtPassword").val();
//		if(password.trim() === '' || password == null)
//			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before edit fabric supplier
	 */
	function validateOverRangeWhenEdit(){
		if($("#editUserDialog").find("#txtPassword").val().length>50 
				|| $("#editUserDialog").find("#txtFirstName").val().length>50 || $("#editUserDialog").find("#txtLastName").val().length>50
				|| $("#editUserDialog").find("#txtEmail").val().length>50 
//				|| $("#editUserDialog").find("#txtEmailPassword").val().length>50
				|| $("#editUserDialog").find("#txtPhone").val().length>20)
			return false;
		return true;
	}
	
	/**
	 * This function validate white space when edit
	 */
	function validateWhitespaceEdit(){
		if($("#editUserDialog").find("#txtPassword").val().length!=$("#editUserDialog").find("#txtPassword").val().trim().length||
				$("#editUserDialog").find("#txtFirstName").val().length!=$("#editUserDialog").find("#txtFirstName").val().trim().length||
				$("#editUserDialog").find("#txtLastName").val().length!=$("#editUserDialog").find("#txtLastName").val().trim().length||
				$("#editUserDialog").find("#txtEmail").val().length!=$("#editUserDialog").find("#txtEmail").val().trim().length||
//				$("#editUserDialog").find("#txtEmailPassword").val().length!=$("#editUserDialog").find("#txtEmailPassword").val().trim().length||
				$("#editUserDialog").find("#txtPhone").val().length!=$("#editUserDialog").find("#txtPhone").val().trim().length)
			return false;
		return true;
	}
	
	/**
	 * password on keyup function() to check if user entered, over range, white space (3 cases)
	 */
	$("#editUserDialog").find("#txtPassword").on('change keyup paste',function(){
		var password= $(this).val();
		
		//if user dont enter or enter just space
		if(password.trim() === '' || password == null){
			$("#editUserDialog").find("#errorTxtPassword").text("Please enter password!");
			$("#editUserDialog").find("#errorTxtPassword").css("color", "red");
			$(this).css("background-color", "red");
		}else if($("#editUserDialog").find("#txtPassword").val().length!=$("#editUserDialog").find("#txtPassword").val().trim().length){
			//if user enter space on prefix, suffix
				$("#editUserDialog").find("#errorTxtPassword").text("You can't use space on prefix and suffix!");
				$("#editUserDialog").find("#errorTxtPassword").css("color", "red");
				$(this).css("background-color", "red");
			}
		//if input of user is over range
		else if(password.length>50){
			$("#editUserDialog").find("#errorTxtPassword").text("The password field's length is 50. Your input is over range!");
			$("#editUserDialog").find("#errorTxtPassword").css("color", "red");
			$(this).css("background-color", "red");
		}else{//valid input
			$("#editUserDialog").find("#errorTxtPassword").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * first name on keyup function() to check if over range, no white space (1 cases)
	 */
	$("#editUserDialog").find("#txtFirstName").on('change keyup paste',function(){
		var firstname= $(this).val();
		
		//if input of user is over range
		if(firstname.length>50){
			$("#editUserDialog").find("#errorTxtFirstName").text("The first name field's length is 50. Your input is over range!");
			$("#editUserDialog").find("#errorTxtFirstName").css("color", "red");
			$(this).css("background-color", "red");
		}else if($("#editUserDialog").find("#txtFirstName").val().length!=$("#editUserDialog").find("#txtFirstName").val().trim().length){
			//if user enter space on prefix, suffix
			$("#editUserDialog").find("#errorTxtFirstName").text("You can't use space on prefix and suffix!");
			$("#editUserDialog").find("#errorTxtFirstName").css("color", "red");
			$(this).css("background-color", "red");
		}else if(firstname.length<51){//valid input
			$("#editUserDialog").find("#errorTxtFirstName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * last name on keyup function() to check if over range, no white space (1 cases)
	 */
	$("#editUserDialog").find("#txtLastName").on('change keyup paste',function(){
		var lastname= $(this).val();
		
		//if input of user is over range
		if(lastname.length>50){
			$("#editUserDialog").find("#errorTxtLastName").text("The last name field's length is 50. Your input is over range!");
			$("#editUserDialog").find("#errorTxtLastName").css("color", "red");
			$(this).css("background-color", "red");
		}else if($("#editUserDialog").find("#txtLastName").val().length!=$("#editUserDialog").find("#txtLastName").val().trim().length){
			//if user enter space on prefix, suffix
			$("#editUserDialog").find("#errorTxtLastName").text("You can't use space on prefix and suffix!");
			$("#editUserDialog").find("#errorTxtLastName").css("color", "red");
			$(this).css("background-color", "red");
		}else if(lastname.length<51){//valid input
			$("#editUserDialog").find("#errorTxtLastName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * email on keyup function() to check if over range, no white space (1 cases)
	 */
	$("#editUserDialog").find("#txtEmail").on('change keyup paste',function(){
		var email= $(this).val();
		
		//if input of user is over range
		if(email.length>50){
			$("#editUserDialog").find("#errorTxtEmail").text("The email field's length is 50. Your input is over range!");
			$("#editUserDialog").find("#errorTxtEmail").css("color", "red");
			$(this).css("background-color", "red");
		}else{
			if($("#editUserDialog").find("#txtEmail").val().length!=$("#editUserDialog").find("#txtEmail").val().trim().length){
				//if user enter space on prefix, suffix
				$("#editUserDialog").find("#errorTxtEmail").text("You can't use space on prefix and suffix!");
				$("#editUserDialog").find("#errorTxtEmail").css("color", "red");
				$(this).css("background-color", "red");
			}else {
				if(!isValidationEmail(email)){//valid input
					$("#editUserDialog").find("#errorTxtEmail").text("Email pattern is wrong!");
					$("#editUserDialog").find("#errorTxtEmail").css("color", "red");
					$(this).css("background-color", "red");
				}else {
					if(email.length<51){//valid input
						$("#editUserDialog").find("#errorTxtEmail").text("");
						$(this).css("background-color", "white");
					}
				}
			}
		} 
	});
	
	/**
	 * email's password on keyup function() to check if over range, no white space (1 cases)
	 */
	$("#editUserDialog").find("#txtEmailPassword").on('change keyup paste',function(){
		var emailPass= $(this).val();
		
		//if input of user is over range
		if(emailPass.length>50){
			$("#editUserDialog").find("#errorTxtEmailPassword").text("The email's password field's length is 50. Your input is over range!");
			$("#editUserDialog").find("#errorTxtEmailPassword").css("color", "red");
			$(this).css("background-color", "red");
		}else if($("#editUserDialog").find("#txtEmailPassword").val().length!=$("#editUserDialog").find("#txtEmailPassword").val().trim().length){
			//if user enter space on prefix, suffix
			$("#editUserDialog").find("#errorTxtEmailPassword").text("You can't use space on prefix and suffix!");
			$("#editUserDialog").find("#errorTxtEmailPassword").css("color", "red");
			$(this).css("background-color", "red");
		}else if(emailPass.length<51){//valid input
			$("#editUserDialog").find("#errorTxtEmailPassword").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * phone on keyup function() to check if over range, no white space (1 cases)
	 */
	$("#editUserDialog").find("#txtPhone").on('change keyup paste',function(){
		var phone= $(this).val();
		
		//if input of user is over range
		if(phone.length>20){
			$("#editUserDialog").find("#errorTxtPhone").text("The phone field's length is 20. Your input is over range!");
			$("#editUserDialog").find("#errorTxtPhone").css("color", "red");
			$(this).css("background-color", "red");
		}else if($("#editUserDialog").find("#txtPhone").val().length!=$("#editUserDialog").find("#txtPhone").val().trim().length){
			//if user enter space on prefix, suffix
			$("#editUserDialog").find("#errorTxtPhone").text("You can't use space on prefix and suffix!");
			$("#editUserDialog").find("#errorTxtPhone").css("color", "red");
			$(this).css("background-color", "red");
		}else if(phone.length<21){//valid input
			$("#editUserDialog").find("#errorTxtPhone").text("");
			$(this).css("background-color", "white");
		}
	});
	
	loadData();
	loadAllDropDownList();
})