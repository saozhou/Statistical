$(document).ready(function() {
	$(".table-div").css("visibility", "hidden");
	$(".custom-footer").css("visibility", "hidden");
	init();
});

var checkbox_name = new Array("税收", "旅游税收", "GDP", "旅游GDP");
var thead;
//TODO:确定按钮点击事件
function asure() {
	showTable();
	createTable();
}
//TODO:根据用户选择生成相应的表格
function createTable() {
	var checkbox = $(".target input");
	thead = new Array("行业代码", "行业指标");
	for(var i = 0; i < checkbox.length; i++) {
		if(checkbox.eq(i).is(":checked") == true) {
			thead[thead.length] = checkbox_name[i];
		}
	}
	//未选择指标返回
	if(thead.length == 2) {
		$(".tip").css("display", "none");
		alert("请选择指标");
		return;
	}

	//创建表头
	var code = '<tr>';
	for(var i = 0; i < thead.length; i++) {
		code += '<th>' + thead[i] + '</th>';
	}
	code += '</tr> ';
	$(".table-div table thead tr").remove();
	$(".table-div table thead").append(code);
	getContent();
	resize();
}
//TODO:自定义查询中表格的自适应
function resize() {
	var pro = 1 / thead.length;
	var _width = $('.table-div').width();
	for(var i = 0; i < thead.length; i++) {
		$('.table-div th').eq(i).width(_width * pro);
		$('.table-div td').eq(i).width(_width * pro);
	}
}

//TODO：获取表格内容
function getContent() {
	rotateLoading();
	//获取行业规模
	var industry_scale = $("input[name='industry-scale']:checked").val();
	var url = '';
	var json = '';
	var code = '';
	//未选择行业规模返回
	if(industry_scale == null) {
		$(".tip").css("display", "none");
		alert("请选择行业规模");
		return;
	}

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
				for(var j = 0; j < thead.length; j++) {
					code += '<td contentEditable="true">' + '</td>';
				}
				code += '</tr> ';

				$(".table-div table tbody tr").remove();
				$(".table-div  table tbody").append(code);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			//加载失败
			loadFailure();
		}
	});

}

//显示表格
function showTable() {
	$(".result-body").css("visibility", "visible");
	$(".tip").css("display","block");
	$(".table-div table").css("display", "block");
	$(".table-div table").css("display");
	$(".table-div table").css("opacity", "1");
}

//TODO:下载
function download() {
	var url = '';
	var json = '';

	$.ajax({
		url: url,
		type: "get",
		dataType: "json",
		data: json,
		cache: false,
		async: false,
		contentType: "application/json; charset=utf-8",
		success: function(data, textStatus, jqXHR) {
			if('success' == textStatus) {
				//下载成功
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			//下载失败

		}
	});
}

//TODO:打印
function print() {

}