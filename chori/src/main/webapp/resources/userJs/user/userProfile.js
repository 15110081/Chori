$(document).ready(function(){
	/**
	 * This function is used to load data into table
	 */
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() + "user/getUserDetail",
			contentType: "application/json",
			success: function(data){
				
				if(data.status== "ok"){
					$("#userProfile").find("#userName").val(data.user.username);
					$("#userProfile").find("#firstName").val(data.user.firstname);
					$("#userProfile").find("#lastName").val(data.user.lastname);
					$("#userProfile").find("#email").val(data.user.email);
					$("#userProfile").find("#choriShortName").val(data.user.agentShortname);
					$("#userProfile").find("#phone").val(data.user.phone);
//					$("#userProfile").find("#status").val(data.user.status);
//					$("#userProfile").find("#group").val(data.user.rolename);
					if(data.user.ccmailstring)
						$('#userProfile').find('#chkCCmailstring').prop('checked', true);
				}else{
					alert('This alert should never show!');
				}
				
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	
	/**
	 * Function handle when click "Change Password" button
	 */
	$("#btnEditPassword").click(function(){
        ///
		$("#editPasswordDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Edit Password",
			height: 400,
			width: 250,
			buttons:{
				"Edit": function(){
//					alert($("#editPasswordDialog").find("#currentPassword").val());
					if(!checkIfAllFieldsIsEntered()){
						callMessageDialog("Warning Message", "Please enter all required fields!");
					} else{
						if(!validateWhitespace()){
							callMessageDialog("Warning Message", "You can't enter white space at prefix, suffix!");
						}else{
							if(!checkMatchNewAndRetype()){
								callMessageDialog("Warning Message", "Password and Retype password not match!");
							}else{
								//thỏa
								var username = $("#userName").val();
								var password = $("#editPasswordDialog").find("#currentPassword").val();
								var newPassword = $("#editPasswordDialog").find("#newPassword").val();
								
								var UserModel ={
										username: username,
										password: password,
										newPassword: newPassword
								}
								
								//gọi về xem user name vs pass có match ko?
								$.ajax({
									dataType: 'json',
									type: 'POST',
									data: JSON.stringify(UserModel),
									contentType: 'application/json',
									url: getAbsolutePath() + 'user/isCurrentPasswordMatch',
									success: function(data){
										if(data.isCurrentPasswordMatch==true){
											//nếu password match
//											callMessageDialog("Message", 'Password match!');
											//get newPassword
//											var newPassword = $("#editPasswordDialog").find("#newPassword").val();
											
											///do change password
											$.ajax({
												dataType: 'json',
												type: 'POST',
												data: JSON.stringify(UserModel),
												contentType: 'application/json',
												url: getAbsolutePath() + 'user/editPassword',
												success: function(data){
													if(data.editPasswordStatus==true){
														callMessageDialog("Message", 'Change password successfully!');
														$("#editPasswordDialog").dialog("close");
														clearCssAfterCloseEditPasswordDialog();
													}
												},
												error: function(){
													alert("Lỗi cmnr 96");
												}
											});
											///
										}else if(data.isCurrentPasswordMatch==false){
											callMessageDialog("Message", 'Your current password is incorrect!');
										}
									},
									error: function(){
										alert("Lỗi cmnr 105");
									}
								});
								//gọi về xem user name vs pass có match ko?
							}
						}
					}
				},
				"Cancel": function(){
					$("#editPasswordDialog").dialog("close");
					clearCssAfterCloseEditPasswordDialog();
				}
			},
			close: function(){
				//
				clearCssAfterCloseEditPasswordDialog();
			}
		});
		///
    });
	
	/**
	 * Function handle when user click edit user profile
	 */
	$("#btnEditProfile").click(function(){
		//get user name to fnd by its id
		var username= $("#userName").val();
		
        //disable email's password fields
		$("#editUserDialog").find("#txtEmailPassword").val('');
		$("#editUserDialog").find("#txtEmailPassword").prop('disabled', true);
		
		///
		//lấy thông tin qua id gán vào edit dialog
		$.ajax({
			dataType: "json",
			type: 'POST',
			data: JSON.stringify({
				username: username
			}),
			url: getAbsolutePath() + "user/detail",
			contentType: "application/json",
			success: function(data){
				$('#editUserDialog').find('#txtFirstName').val(data.user.firstname);
				$('#editUserDialog').find('#txtLastName').val(data.user.lastname);
				$('#editUserDialog').find('#txtEmail').val(data.user.email);
				$('#editUserDialog').find('#txtPhone').val(data.user.phone);
//				$('#editUserDialog').find('#txtCCEmail').val(data.user.ccmailstring);
			},
			error: function(){
				alert("Lỗi lấy by id")
			}
		});
		//end lấy thông tin qua id gán vào edit dialog
		///
		
		$("#editUserDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Edit User Profile",
			height: 550,
			width: 300,
			buttons:{
				"Edit": function(){
					//1st validate if user enter white space
					if(!validateWhitespaceEditUserProfile()){
						callMessageDialog("Warning message", "Prefix and suffix don't contain whitespace!");
					}else{
						////
						//start editing
						var firstname = $('#editUserDialog').find('#txtFirstName').val();
						var lastname = $('#editUserDialog').find('#txtLastName').val();
						var email = $('#editUserDialog').find('#txtEmail').val();
						var phone = $('#editUserDialog').find('#txtPhone').val();
						var emailpassword = $('#editUserDialog').find('#txtEmailPassword').val();
//						var ccmailstring = $('#editUserDialog').find('#txtCCEmail').val();

						var userModel = {
								username: username,
								firstname: firstname,
								lastname: lastname,
								email: email,
								phone: phone,
								emailpassword: emailpassword
//								ccmailstring: ccmailstring
						};
						
						console.log(JSON.stringify(userModel));
						
						//gọi ajax để edit
						$.ajax({
							dataType: 'json',
							type: 'POST',
							data: JSON.stringify(userModel),
							contentType: 'application/json',
							url: getAbsolutePath() + 'user/editUserProfile',
							success: function(data){
								if(data.editStatus==true){
//									reloadTableWithStatus();
									loadData();
									callMessageDialog("Message", 'Edit User successfully!');
									$("#editUserDialog").dialog("close");
									resetAfterEdit();
								}
							},
							error: function(){
								alert("Lỗi cmnr 209");
							}
						});
						//end gọi ajax để edit
						//end editing
					}
					
					
				},
				"Cancel": function(){
					$("#editUserDialog").dialog("close");
					resetAfterEdit();
					$("#editUserDialog").find("#txtEmailPassword").val('');
					$("#editUserDialog").find("#txtEmailPassword").prop('disabled', true);
					$("#editUserDialog").find("#chkEmailPassword").prop('checked', false);
				}
			},
			close: function(){
				//
				resetAfterEdit();
				$("#editUserDialog").find("#txtEmailPassword").val('');
				$("#editUserDialog").find("#txtEmailPassword").prop('disabled', true);
				$("#editUserDialog").find("#chkEmailPassword").prop('checked', false);
			}
		});
		///
    });
	
	//hàm reset các trường trong bảng sau khi thêm mới
	function resetAfterEdit(){
		$('#editUserDialog').find('#txtFirstName').val('');
		$('#editUserDialog').find('#txtLastName').val('');
		$('#editUserDialog').find('#txtEmail').val('');
		$('#editUserDialog').find('#txtEmailPassword').val('');
		$('#editUserDialog').find('#txtPhone').val('');
		
		//reset css
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
	 * Validate when change password
	 */
	/**
	 * This function validate if user have enter all fields
	 */
	function checkIfAllFieldsIsEntered(){
		var currentPassword= $("#editPasswordDialog").find("#currentPassword").val();
		var newPassword= $("#editPasswordDialog").find("#newPassword").val();
		var retypePassword= $("#editPasswordDialog").find("#retypePassword").val();
		if(currentPassword.trim() === '' || currentPassword == null)
			return false;
		
		if(newPassword.trim() === '' || newPassword == null)
			return false;
		
		if(retypePassword.trim() === '' || retypePassword == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate if user enter white space at suffix, prefix
	 */
	function validateWhitespace(){
		if($("#editPasswordDialog").find("#currentPassword").val().length!=$("#editPasswordDialog").find("#currentPassword").val().trim().length||
				$("#editPasswordDialog").find("#newPassword").val().length!=$("#editPasswordDialog").find("#newPassword").val().trim().length||
				$("#editPasswordDialog").find("#retypePassword").val().length!=$("#editPasswordDialog").find("#retypePassword").val().trim().length)
			return false;
		return true;
	}
	
	/**
	 * Funtion return true if newPass and retypePass matched!
	 */
	function checkMatchNewAndRetype(){
		var newPassword= $("#editPasswordDialog").find("#newPassword").val();
		var retypePassword= $("#editPasswordDialog").find("#retypePassword").val();
		if(newPassword.localeCompare(retypePassword)==0)
			return true;
		return false;
	}
	
	/**
	 * Function for checkBox email's password
	 * hàm xử lý cho check box đổi password email
	 */
	$('#editUserDialog').find('#chkEmailPassword').on('click', function () {
		if($(this).is(':checked')){
			$("#editUserDialog").find("#txtEmailPassword").prop('disabled', false);
		}else if(!$(this).is(':checked')){
			$("#editUserDialog").find("#txtEmailPassword").val('');
			$("#editUserDialog").find("#txtEmailPassword").prop('disabled', true);
			//đổi css về bình thg
			$("#editUserDialog").find("#errorTxtEmailPassword").text("");
			$("#editUserDialog").find("#txtEmailPassword").css("background-color", "white");
		}
	});
	/**
	 * --------------------End validate when change password
	 */
	
	/**
	 * ---Validate when edit profile
	 */
	/**
	 * Validate if user enter white space at prefix, suffix
	 * Hàm validate xem người dùng có nhập space khi input lúc edit user profile
	 */
	function validateWhitespaceEditUserProfile(){
		if($("#editUserDialog").find("#txtEmailPassword").val().length!=$("#editUserDialog").find("#txtEmailPassword").val().trim().length||
				$("#editUserDialog").find("#txtFirstName").val().length!=$("#editUserDialog").find("#txtFirstName").val().trim().length||
				$("#editUserDialog").find("#txtLastName").val().length!=$("#editUserDialog").find("#txtLastName").val().trim().length||
				$("#editUserDialog").find("#txtEmail").val().length!=$("#editUserDialog").find("#txtEmail").val().trim().length||
				$("#editUserDialog").find("#txtPhone").val().length!=$("#editUserDialog").find("#txtPhone").val().trim().length)
			return false;
		return true;
	}
	
	/**
	 * this function validate email
	 * Hàm validation email
	 */
	 function isValidationEmail($email) {
		 var emailReg = /^([\w-\.]+@([\w-])+([\w-]+\.)+[\w-]{2,4})?$/;
		 return emailReg.test( $email );
	 }
	
	/**
	 * --- onclick css part
	 */
	
	/**
	 * Onclick current password
	 */
	$("#editPasswordDialog").find("#currentPassword").on('change keyup paste',function(){
		var password= $(this).val();
		
		//if user dont enter or enter just space
		if(password.trim() === '' || password == null){
			$("#editPasswordDialog").find("#errorCurrentPassword").text("Please enter current password!");
			$("#editPasswordDialog").find("#errorCurrentPassword").css("color", "red");
			$(this).css("background-color", "red");
		}else if($("#editPasswordDialog").find("#currentPassword").val().length!=$("#editPasswordDialog").find("#currentPassword").val().trim().length){
			//if user enter space on prefix, suffix
				$("#editPasswordDialog").find("#errorCurrentPassword").text("You can't use space on prefix and suffix!");
				$("#editPasswordDialog").find("#errorCurrentPassword").css("color", "red");
				$(this).css("background-color", "red");
			}
		//if input of user is over range
		else{//valid input
			$("#editPasswordDialog").find("#errorCurrentPassword").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * Onclick new password
	 */
	$("#editPasswordDialog").find("#newPassword").on('change keyup paste',function(){
		var password= $(this).val();
		
		//if user dont enter or enter just space
		if(password.trim() === '' || password == null){
			$("#editPasswordDialog").find("#errorNewPassword").text("Please enter new password!");
			$("#editPasswordDialog").find("#errorNewPassword").css("color", "red");
			$(this).css("background-color", "red");
		}else if($("#editPasswordDialog").find("#newPassword").val().length!=$("#editPasswordDialog").find("#newPassword").val().trim().length){
			//if user enter space on prefix, suffix
				$("#editPasswordDialog").find("#errorNewPassword").text("You can't use space on prefix and suffix!");
				$("#editPasswordDialog").find("#errorNewPassword").css("color", "red");
				$(this).css("background-color", "red");
			}
		//if input of user is over range
		else{//valid input
			$("#editPasswordDialog").find("#errorNewPassword").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * Onclick retype password
	 */
	$("#editPasswordDialog").find("#retypePassword").on('change keyup paste',function(){
		var password= $(this).val();
		var newPassword= $("#editPasswordDialog").find("#newPassword").val();
		
		//if user dont enter or enter just space
		if(password.trim() === '' || password == null){
			$("#editPasswordDialog").find("#errorRetypePassword").text("Please enter retype password!");
			$("#editPasswordDialog").find("#errorRetypePassword").css("color", "red");
			$(this).css("background-color", "red");
		}else if($("#editPasswordDialog").find("#retypePassword").val().length!=$("#editPasswordDialog").find("#retypePassword").val().trim().length){
			//if user enter space on prefix, suffix
			$("#editPasswordDialog").find("#errorRetypePassword").text("You can't use space on prefix and suffix!");
			$("#editPasswordDialog").find("#errorRetypePassword").css("color", "red");
			$(this).css("background-color", "red");
		}else if(newPassword.localeCompare(password)==0){
			$("#editPasswordDialog").find("#errorRetypePassword").text("Re-type password match!");
			$("#editPasswordDialog").find("#errorRetypePassword").css("color", "green");
			$(this).css("background-color", "white");
		}else if(newPassword.localeCompare(password)!=0){
			$("#editPasswordDialog").find("#errorRetypePassword").text("Re-type password isn't match!");
			$("#editPasswordDialog").find("#errorRetypePassword").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		else{//valid input
			$("#editPasswordDialog").find("#errorRetypePassword").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * function handle clear input, css after user close change password dialog
	 * hàm clear css sau khi đóng dialog
	 */
	function clearCssAfterCloseEditPasswordDialog(){
		$("#editPasswordDialog").find("#errorCurrentPassword").text("");
		$("#editPasswordDialog").find("#errorNewPassword").text("");
		$("#editPasswordDialog").find("#errorRetypePassword").text("");
		$("#editPasswordDialog").find("#currentPassword").css("background-color", "white");
		$("#editPasswordDialog").find("#newPassword").css("background-color", "white");
		$("#editPasswordDialog").find("#retypePassword").css("background-color", "white");
		
		$("#editPasswordDialog").find("#currentPassword").val('');
		$("#editPasswordDialog").find("#newPassword").val('');
		$("#editPasswordDialog").find("#retypePassword").val('');
	}
	/**
	 * ----------------------------End css for change password
	 */
	
	/**
	 * ----------------------------css for change user profile
	 */
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
	
	/**
	 * cc email on keyup function() to check if over range, no white space (1 cases)
	 */
	$("#editUserDialog").find("#txtCCEmail").on('change keyup paste',function(){
		var ccemail= $(this).val();
		
		if($("#editUserDialog").find("#txtCCEmail").val().length!=$("#editUserDialog").find("#txtCCEmail").val().trim().length){
			//if user enter space on prefix, suffix
			$("#editUserDialog").find("#errorTxtCCEmail").text("You can't use space on prefix and suffix!");
			$("#editUserDialog").find("#errorTxtCCEmail").css("color", "red");
			$(this).css("background-color", "red");
		}else if(ccemail.length<51){//valid input
			$("#editUserDialog").find("#errorTxtCCEmail").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * end css 4 change user profile
	 */
	
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
	
	loadData();
})