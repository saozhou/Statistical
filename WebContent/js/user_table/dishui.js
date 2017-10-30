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
	var url = '';
	var json = '';

	$.ajax({
		url: url,
		type: "post",
		dataType: "json",
		data: json,
		cache: false,
		async: false,
		contentType: "application/json; charset=utf-8",
		success: function(data, textStatus, jqXHR) {
			if('success' == textStatus) {
				//加载成功
				loadSuccess();

				//遍历数据生成表格
				code += '<tr>';
				code += '<td contentEditable="true">' + '</td>';
				code += '<td contentEditable="true">' + '</td>';
				code += '<td contentEditable="true">' + '</td>';
				code += '</tr> ';

				$(".body table tbody").append(code);
			}
			return true;
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			//加载失败
			loadFailure();
			return false;
		}
	});
	return false;
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