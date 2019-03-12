/**
 * List Status
 */
$(document).ready(function(){	

function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/status/list",
			contentType: "application/json",
			success: function(data){
				if(data.list.length==0){
					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					$('<tr>').append(
							$('<td>').text(value.statuscode),
							$('<td>').text(value.statusname),
							$('<td>').text(value.statusdescription),			
							$('<td>').html('<select class="selectpicker selectOption" data-id="'
									+value.statuscode
									+'"><option value="Options" disabled selected>Options</option>'
									+ '<option value="Edit">Edit</option>'
									)
									
					).appendTo('#listStatus');
				});
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	loadData();
});