$(document).ready(function() {
	$(".find").css("display", "initial");
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

// TODO：查找
function find() {
	hiddenPrintBt();
	showBody();
	loading("正在查询...");
	var code = '';
	var url = '/Statistic/IntegratedQuery/industryGdpContributeSearch';
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
				if (data == "大类gdp未计算") {
					failure("大类gdp未计算");
					return;
				} else if (data == "产业旅游gdp未计算") {
					failure("产业旅游gdp未计算");
					return;
				}
				// 遍历数据生成表格
				$.each(data, function(i, n) {
					code += '<tr>';
					code += '<td>' + n.incode + '</td>';
					code += '<td>' + n.inname + '</td>';
					code += '<td>' + n.gdp + '</td>';
					code += '<td>' + n.trgdp + '</td>';
					code += '<td>' + n.rate + '</td>';
					code += '</tr> ';
				});
				$(".body table tbody tr").remove();
				$(".body table tbody").append(code);
				// 加载成功
				loadSuccess();
				showPrintBt();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			failure("无查询结果");
		}
	});
}

// TODO:打印
function　printPager(){
	var link='<link rel="stylesheet" href="../../css/bootstrap.min.css"/>'+
	'<link rel="stylesheet" href="../../css/user_table.css"/>';
	$("head link").remove();
	$("head style").remove();
	$(".table-div").printArea();
	$("head").append(link);
}