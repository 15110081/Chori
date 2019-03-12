$(document).ready(function(){	

function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "status/list",
			contentType: "application/json",
			success: function(data){
				if(data.list.length==0){
					alert("Table have no data.");
				}
				$.each(data.list,function(key,value){
					$('<tr>').append(
							$('<td>').text(value.statuscode),
							$('<td>').text(value.statusname),
							$('<td>').text(value.statusdescription),			
//							$('<td>').html('<select class="selectpicker selectOption" data-id="'
//									+value.statuscode
//									+'"><option value="Options" disabled selected>Options</option>'
//									+ '<option value="Edit">Edit</option>'
//									)
							$('<td>').html('<button class="btn btn-info btnEdit" data-id="'
									+value.statuscode
									+'">Edit</button>'
									)
									
					).appendTo('#listStatus');
				});
				action();
			},
			error: function(){
				alert("Can not get data!");
			}
		});
	};
	//bảng warning và user
	function action(){	
		//When click select
		$('.btnEdit').on('click', function (e) {	
			var statuscode= $(this).data('id');
			ShowDialog_CannotFindCusID();
			alert(statuscode);
		});
	};
	// This function is used to call validate cusID when add order dialog.
	function ShowDialog_CannotFindCusID() {
        $( "#dialogEditSuccess" ).dialog({
        	title: "Message",
            show: {
                effect: "slide",
                duration: 300
            },
            height: 200,
            width: 320,
            modal: false,
            buttons: {
                "OK": function() {
                    $( "#dialogEditSuccess" ).dialog( "close" );
                }
            },
            hide: {
                effect: "slide",
                duration: 300
            }
        });
    };
	loadData();
});