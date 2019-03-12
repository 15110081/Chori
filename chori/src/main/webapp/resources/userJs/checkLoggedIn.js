$(document).ready(function(){
	
//	function getAbsolutePath() {
//	    var loc = window.location;
//	    var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
//	    return loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
//	}
	
//	alert(getAbsolutePath());
	function checkLoggedIn(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() + "isLoggedIn",
			contentType: "application/json",
			success: function(data){
				if(data.isLoggedIn==true){
					//alert("Đã logged in");
					$('#LogInLogOut').append('<li class="dropdown"><a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">'
							+'<i class="icon-user"></i> '+data.userName+' <i class="caret"></i>'
							+'</a>'
							+'<ul class="dropdown-menu">'
							+'<li><a tabindex="-1" href="'+getAbsolutePath()+'userProfile">Profile</a></li>'
							+'<li class="divider"></li>'
							+'<li><a tabindex="-1" href="'+getAbsolutePath()+'logout">Logout</a></li>'
							+'</ul>'
							+'</li>');
					$('#mainMenu').show();
				}else{
					//alert("Chưa logged in");
					$('#LogInLogOut').append('<li class="dropdown"><a href="'+getAbsolutePath()+'login">'
							+'Log In'
							+'</a>');
					$('#mainMenu').hide();
				}
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	checkLoggedIn();
})