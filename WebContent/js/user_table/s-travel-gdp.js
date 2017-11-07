$(document).ready(function() {
	$(".calculate").css("display", "initial");
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
var dataObj;
var start = 0;
var end = 100;
// TODO:计算
function calculate() {
	if (bg_load != null) {
		start = 0;
		end = 100;
		window.clearInterval(bg_load);
	}
	$(".body").css("display", "block");
	loading("正在计算...");
	var code = '';
	var url = '/Statistic/TravelGdpCalculate/subTravelGdpCaculate';
	var json = '';

	$.ajax({
		url : url,
		type : "post",
		dataType : "json",
		data : json,
		cache : true,
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
				} else {
					dataObj = data;
				}
				// 遍历数据生成表格
				$.each(data, function(i, n) {
					if (i >= start && i <= end) {
						code += '<tr>';
						code += '<td>' + n.smcode + '</td>';
						code += '<td>' + n.smname + '</td>';
						code += '<td>' + n.stgdp + '</td>';
						code += '</tr> ';
					} else {
						return;
					}
				});
				$(".body table tbody tr").remove();
				$(".body table tbody").append(code);
				// 加载成功
				loadSuccess();
				start = end + 1;
				end += 100;
				bgLoad();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			failure("计算失败");
		}
	});
}
// TODO:后台继续加载剩余数据
var bg_load;
function bgLoad() {

	bg_load = setInterval(function() {
		var code = '';
		$.each(dataObj, function(i, n) {

			if (i >= start && i <= end) {
				code += '<tr>';
				code += '<td>' + n.smcode + '</td>';
				code += '<td>' + n.smname + '</td>';
				code += '<td>' + n.stgdp + '</td>';
				code += '</tr> ';
			} else {
				return;
			}
			if (i == dataObj.length - 1) {
				window.clearInterval(bg_load);
				return;
			}
		});
		start = end + 1;
		end += 100;
		$(".body table tbody").append(code);
	}, 1500);

}