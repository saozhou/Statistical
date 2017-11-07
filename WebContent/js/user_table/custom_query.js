$(document).ready(function() {
	$(".table-div").css("visibility", "hidden");
	init();
});

var checkbox_name = new Array("税收", "旅游税收", "GDP", "旅游GDP");
var thead;
// TODO:确定按钮点击事件
function asure() {
	hiddenTable();
	createTable();
}
// TODO:根据用户选择生成相应的表格
function createTable() {
	var checkbox = $(".target input");
	thead = new Array("行业代码", "行业指标");
	for (var i = 0; i < checkbox.length; i++) {
		if (checkbox.eq(i).is(":checked") == true) {
			thead[thead.length] = checkbox_name[i];
		}
	}
	// 未选择指标返回
	if (thead.length == 2) {
		$(".tip").css("display", "none");
		alert("请选择指标");
		return;
	}

	// 创建表头
	var code = '<tr>';
	for (var i = 0; i < thead.length; i++) {
		code += '<th>' + thead[i] + '</th>';
	}
	code += '</tr> ';
	$(".table-div table thead tr").remove();
	$(".table-div table thead").append(code);
	getContent();
}
// TODO:自定义查询中表格的自适应
function resize() {
	var pro = 0.9 / (thead.length - 1);
	var _width = $('.table-div').width();
	$('.table-div th').eq(0).width(_width * 0.1);
	$('.table-div td').eq(0).width(_width * 0.1);
	for (var i = 1; i < thead.length; i++) {
		$('.table-div th').eq(i).width(_width * pro);
		$('.table-div td').eq(i).width(_width * pro);
	}
}

// TODO：获取表格内容
var type = 0;
function getContent() {
	hiddenPrintBt();
	$(".tip").css("visibility", "visible");
	loading("正在查询");
	// 获取行业规模
	var industry_scale = $("input[name='industry-scale']:checked").val();
	// 未选择行业规模返回
	if (industry_scale == null) {
		$(".tip").css("display", "none");
		alert("请选择行业规模");
		return;
	}
	var code = '';
	var json='';
	var url = '/Statistic/SelfDefineSearch/Searche';
	type = 0;
	if (industry_scale == "小类")
		type += 32;
	else if (industry_scale == "大类")
		type += 16;
	else if (industry_scale == "门类")
		type += 64;

	for (var i = 2; i < thead.length; i++) {
		if (thead[i] == "旅游GDP")
			type += 1;
		else if (thead[i] == "旅游税收")
			type += 2;
		else if (thead[i] == "GDP")
			type += 4;
		else if (thead[i] == "税收")
			type += 8;
	}
	json += '{"type":"' + type + '"}';
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
				showTable();
				// 遍历数据生成表格
				$.each(data, function(i, n) {
					code += '<tr>';
					for (var j = 0; j < thead.length; j++) {
						switch (thead[j]) {
						case "行业代码":
							code += '<td>' + n.code + '</td>';
							break;
						case "行业指标":
							code += '<td>' + n.name + '</td>';
							break;
						case "税收":
							code += '<td>' + n.tax + '</td>';
							break;
						case "旅游税收":
							code += '<td>' + n.travetax + '</td>';
							break;
						case "GDP":
							code += '<td>' + n.gdp + '</td>';
							break;
						case "旅游GDP":
							code += '<td>' + n.travegdp + '</td>';
							break;
						default:
							break;
						}
					}
					code += '</tr> ';
				});
				$(".body table tbody tr").remove();
				$(".body table tbody").append(code);
				if (data == "") {
					failure("内容为空");
				} else {
					// 加载成功
					loadSuccess();
					showPrintBt();
				}

			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// 加载失败
			failure("查询失败");
		}
	});

}

// 显示表格
function showTable() {
	$(".result-body").css("visibility", "visible");
	$(".tip").css("display", "block");
	$(".table-div table").css("display", "block");
	$(".table-div table").css("display");
	$(".table-div table").css("opacity", "1");
}

// 隐藏表格
function hiddenTable() {
	$(".result-body").css("visibility", "hidden");
	$(".table-div table").css("display", "none");
	$(".table-div table").css("display");
	$(".table-div table").css("opacity", "0");
}

// TODO:下载
function custom_download() {
	var url = '/Statistic/SelfDefineSearch/Download?type=' + type;
	location.href = url;
}

//TODO:打印
function　printPager(){
	var link='<link rel="stylesheet" href="../../css/bootstrap.min.css"/>'+
	'<link rel="stylesheet" href="../../css/user_table.css"/>';
	$("head link").remove();
	$("head style").remove();
	$(".table-div").printArea();
	$("head").append(link);
}