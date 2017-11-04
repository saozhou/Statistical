$(document).ready(function() {
	$(".find").css("display", "initial");
	init();
});

function resize() {
	var _width = $('.table-div').width();
	$('.table-div th:first-child').width(_width * 0.15);
	$('.table-div td:first-child').width(_width * 0.15);
	$('.table-div th:nth-child(2)').width(_width * 0.1);
	$('.table-div td:nth-child(2)').width(_width * 0.1);
	$('.table-div th:nth-child(3)').width(_width * 0.15);
	$('.table-div td:nth-child(3)').width(_width * 0.15);
	$('.table-div th:nth-child(4)').width(_width * 0.20);
	$('.table-div td:nth-child(4)').width(_width * 0.20);
	$('.table-div th:nth-child(5)').width(_width * 0.15);
	$('.table-div td:nth-child(5)').width(_width * 0.15);
	$('.table-div th:nth-child(6)').width(_width * 0.15);
	$('.table-div td:nth-child(6)').width(_width * 0.15);
	$('.table-div th:nth-child(7)').width(_width * 0.05);
	$('.table-div td:nth-child(7)').width(_width * 0.05);
	$('.table-div th:nth-child(8)').width(_width * 0.05);
	$('.table-div td:nth-child(8)').width(_width * 0.05);
}

// TODO：查找
function find() {
	showBody();
	loading("正在查询...");
	var code = '';
	var url = '/Statistic/BaseQuery/gfCoefficientGet';
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
				if (data == "gf系数未上传") {
					alert("gf系数未上传");
					return;
				}

				code += '<tr>';
				code += '<td>' + data.ysday + '</td>';
				code += '<td>' + data.avspend + '</td>';
				code += '<td>' + data.spday + '</td>';
				code += '<td>' + data.cpaspend + '</td>';
				code += '<td>' + data.lipeople + '</td>';
				code += '<td>' + data.tpsum + '</td>';
				code += '<td>' + data.gsta + '</td>';
				code += '<td>' + data.fsta + '</td>';
				code += '</tr> ';

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

