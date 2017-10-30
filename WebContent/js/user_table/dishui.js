$(document).ready(function() {
	$("body").css("opacity", "1");
	var code = '';
	for(var i = 0; i < 58; i++) {
		code += '<tr>';
		code += '<td contentEditable="true">壹</td>';
		code += '<td contentEditable="true">贰</td>';
		code += '<td contentEditable="true">仨</td>';
		code += '</tr> ';
	}
	$(".body table tbody").append(code);
	init();
	getContent();
});

function resize() {
	var _width = $('.table-div').width();
	$('.table-div th:first-child').width(_width * 0.4);
	$('.table-div td:first-child').width(_width * 0.4);
	$('.table-div th:nth-child(2)').width(_width * 0.3);
	$('.table-div td:nth-child(2)').width(_width * 0.3);
	$('.table-div th:nth-child(3)').width(_width * 0.3);
	$('.table-div td:nth-child(3)').width(_width * 0.3);
}

//TODO：获取表格内容
function getContent() {
	rotateLoading();
	var code = '';
	var url = 'http://192.168.1.102:8080/Statistic/FileUpload/gfCorfficientmath';
	var json = '{';
	json += 'yearAvergeSpendDay: ' + 1;
	json += ',avergeSpend: ' + 1;
	json += ',travelSpendday: ' + 1;
	json += ',townAvergeSpend: ' + 1;
	json += ',livePeople: ' + 1;
	json += ',dayTravlePeople: ' + 1;
	json += '}';

	$.ajax({
		url: url,
		type: "post",
		dataType: "json",
		data: json,
		cache: false,
		async: true,
		timeout: 10000,
		contentType: "application/json; charset=utf-8",
		success: function(data) {
			//加载成功
			loadSuccess();
			$.each(data, function(i, n) {

				//遍历数据生成表格
				code += '<tr>';
				code += '<td contentEditable="true">' + '</td>';
				code += '<td contentEditable="true">' + '</td>';
				code += '<td contentEditable="true">' + '</td>';
				code += '</tr> ';

			});
			$(".body table tbody").append(code);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			//加载失败
			loadFailure();
		}
	});
}

//TODO:上传
function upload() {
	//触发文件选择框
	$(".upload-input").trigger("click");
	//获取文件路径
	var url = '';
	$(".upload-input").change(function() {
		url = $(".upload-input").val();
	});

}

//TODO:保存
function save() {

}