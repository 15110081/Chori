$(document).ready(function() {

	function loadAllDropDownList() {
		//alert("Test OK!");

		//add <option> into select #kind
		$('#kind').append($('<option>', {
			value : 'Internal',
			text : 'Internal'
		}), $('<option>', {
			value : 'External',
			text : 'External'
		}));
		
		//add <option> into select #mode
		$('#mode').append($('<option>', {
			value : 'Packing',
			text : 'Packing'
		}), $('<option>', {
			value : 'Manufacturing',
			text : 'Manufacturing'
		}));
		
		//call ajax and load data into #unitcode
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "unit/list",
			contentType: "application/json",
			success: function(data){
				$.each(data.list,function(key,value){
					$('#unitcode').append($('<option>', {
						value : value.unitcode,
						text : value.name
					}));
				});
			},
			error: function(){
				alert('Lỗi load dữ liệu unit (40)!');
			}
		});
		
		//call ajax and load data into #colorcode
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "color/list",
			contentType: "application/json",
			success: function(data){
				$.each(data.list,function(key,value){
					$('#colorcode').append($('<option>', {
						value : value.colorcode,
						text : value.colorcode
					}));
				});
			},
			error: function(){
				alert('Lỗi load dữ liệu color (60)!');
			}
		});
	}

	loadAllDropDownList();
})