$(document).ready(function() {
	$(".find").css("display", "initial");
	init();
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

// TODO：查找
function find() {
	showBody();
	loading("正在查询...");
	var code = '';
	var url = '/Statistic/TravelGdpCalculate/subTravelGdpCaculate';
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
				if (data == "小类gdp未计算") {
					failure("小类gdp未计算");
					return;
				} else if (data == "当量系数参照表未上传") {
					failure("当量系数参照表未上传");
					return;
				} else if (data == "gf系数未添加") {
					failure("gf系数未添加");
					return;
				} else if (data == "代码库未上传") {
					failure("代码库未上传");
					return;
				}
				// 遍历数据生成表格
				$.each(data, function(i, n) {
					code += '<tr>';
					code += '<td>' + n.smcode + '</td>';
					code += '<td>' + n.smname + '</td>';
					code += '<td>' + n.stgdp + '</td>';
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