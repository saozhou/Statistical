$(document).ready(function() {
	$("body").css("opacity", "1");
	$(".find").css("display", "initial");
	init();
});

function resize() {
	var _width = $('.table-div').width();
	$('.table-div th:first-child').width(_width * 0.3);
	$('.table-div td:first-child').width(_width * 0.3);
	$('.table-div th:nth-child(2)').width(_width * 0.3);
	$('.table-div td:nth-child(2)').width(_width * 0.3);
	$('.table-div th:nth-child(3)').width(_width * 0.4);
	$('.table-div td:nth-child(3)').width(_width * 0.4);
}

// TODO：查找
function find() {
	showBody();
	loading("正在查询...");
	var code = '';
	var url = '/Statistic/GdpCaculate/subGdpCaculate';
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
				if (data == "小类税收未计算") {
					failure("小类税收未计算");
					return;
				} else if (data == "gdp表未上传") {
					failure("gdp表未上传");
					return;
				}
				// 遍历数据生成表格
				$.each(data, function(i, n) {
					code += '<tr>';
					code += '<td>' + n.smcode + '</td>';
					code += '<td>' + n.smname + '</td>';
					code += '<td>' + n.smgdp + '</td>';
					code += '</tr> ';
				});
				$(".body table tbody tr").remove();
				$(".body table tbody").append(code);
				// 加载成功
				loadSuccess();
				$(".body table tbody").append(code);
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