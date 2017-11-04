$(document).ready(function() {
	$(".find").css("display", "initial");
	init();
});

function resize() {
	var _width = $('.table-div').width();
	$('.table-div th:first-child').width(_width * 0.2);
	$('.table-div td:first-child').width(_width * 0.2);
	$('.table-div th:nth-child(2)').width(_width * 0.4);
	$('.table-div td:nth-child(2)').width(_width * 0.4);
	$('.table-div th:nth-child(3)').width(_width * 0.4);
	$('.table-div td:nth-child(3)').width(_width * 0.4);
}

// TODO：查找
function find() {
	showBody();
	loading("正在查询...");
	var code = '';
	var url = '/Statistic/BaseQuery/gdpGet';
	var json = '';

	$.ajax({
		url : url,
		type : "post",
		dataType : "json",
		data : json,
		cache : false,
		async : true,
		contentType : "application/json; charset=utf-8",
		success : function(data, textStatus, jqXHR) {
			if ('success' == textStatus) {
				if (data == "gdp表未上传") {
					alert("gdp表未上传");
					return;
				}
				// 遍历数据生成表格
				$.each(data, function(i, n) {
					code += '<tr>';
					code += '<td>' + n.gdpcode + '</td>';
					code += '<td>' + n.gdpname + '</td>';
					code += '<td>' + n.gdp + '</td>';
					code += '</tr> ';
				});
				$(".body table tbody tr").remove();
				$(".body table tbody").append(code);
				// 加载成功
				loadSuccess();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			failure("无查询结果");
		}
	});
}

// TODO:打印
function print() {

}