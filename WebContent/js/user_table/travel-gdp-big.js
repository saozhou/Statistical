$(document).ready(function() {
	$("body").css("opacity", "1");
	$(".calculate").css("display", "initial");
	init();
});

function resize() {
	var _width = $('.table-div').width();
	$('.table-div th:first-child').width(_width * 0.1);
	$('.table-div td:first-child').width(_width * 0.1);
	$('.table-div th:nth-child(2)').width(_width * 0.3);
	$('.table-div td:nth-child(2)').width(_width * 0.3);
	$('.table-div th:nth-child(3)').width(_width * 0.2);
	$('.table-div td:nth-child(3)').width(_width * 0.2);
	$('.table-div th:nth-child(4)').width(_width * 0.2);
	$('.table-div td:nth-child(4)').width(_width * 0.2);
	$('.table-div th:nth-child(5)').width(_width * 0.2);
	$('.table-div td:nth-child(5)').width(_width * 0.2);
}

// TODO:计算
function calculate() {
	loading("正在计算...");
	var code = '';
	var url = '/Statistic/IntegratedQuery/largeGdpContributeSearch';
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
				} else if (data == "小类旅游gdp未计算") {
					failure("小类旅游gdp未计算");
					return;
				}
				// 遍历数据生成表格
				$.each(data, function(i, n) {
					code += '<tr>';
					code += '<td>' + n.lacode + '</td>';
					code += '<td>' + n.laname + '</td>';
					code += '<td>' + n.gdp + '</td>';
					code += '<td>' + n.trgdp + '</td>';
					code += '<td>' + n.rate + '</td>';
					code += '</tr> ';
				});
				$(".body table tbody tr").remove();
				$(".body table tbody").append(code);
				// 加载成功
				loadSuccess();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			failure("计算失败");
		}
	});
}

// TODO:上传
function upload() {
	// 触发文件选择框
	$(".upload-input").trigger("click");
	// 获取文件路径
	var url = '';
	$(".upload-input").change(function() {
		url = $(".upload-input").val();
	});

}

// TODO:保存
function save() {

}
