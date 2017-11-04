$(document).ready(function() {
	$(".find").css("display", "initial");
	var url =window.parent.document.getElementById("iframe").src ;
	/*$(".table-div table thead  span").eq(0).text(year);
	$(".table-div table thead  span").eq(1).text(city);*/
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
	var url = '/Statistic/TravelTaxCaculate/subTravelTaxGet';
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
				} else if (data == "当量系数参照表未上传") {
					failure("当量系数参照表未上传");
					return;
				} else if (data == "gf系数未计算") {
					failure("gf系数未计算");
					return;
				} else if (data == "行业代码库未上传") {
					failure("行业代码库未上传");
					return;
				}

				// 遍历数据生成表格
				$.each(data, function(i, n) {
					code += '<tr>';
					code += '<td contentEditable="true">' + n.smcode + '</td>';
					code += '<td contentEditable="true">' + n.smname + '</td>';
					code += '<td contentEditable="true">' + n.sttax + '</td>';
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