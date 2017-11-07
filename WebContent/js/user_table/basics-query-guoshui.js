$(document).ready(function() {
	$(".find").css("display", "initial");
	init();
});

function resize() {
	var _width = $('.table-div').width();
	$('.table-div th:first-child').width(_width * 0.2);
	$('.table-div td:first-child').width(_width * 0.2);
	$('.table-div th:nth-child(2)').width(_width * 0.5);
	$('.table-div td:nth-child(2)').width(_width * 0.5);
	$('.table-div th:nth-child(3)').width(_width * 0.3);
	$('.table-div td:nth-child(3)').width(_width * 0.3);
}
var dataObj;
var start = 0;
var end = 100;
// TODO：查找
function find() {
	if (bg_load != null) {
		start = 0;
		end = 100;
		window.clearInterval(bg_load);
	}
	hiddenPrintBt();
	showBody();
	loading("正在查询...");
	var code = '';
	var url = '/Statistic/BaseQuery/centralTaxGet';
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
				if (data == "国税表未上传") {
					failure("国税表未上传");
					showUploadBt();
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
						code += '<td>' + n.cntax + '</td>';
						code += '</tr> ';
					} else {
						return;
					}
				});  
				
				$("table tbody tr").remove();
				$("table tbody").append(code);
				// 加载成功
				loadSuccess();
				start = end + 1;
				end += 100;
				bgLoad();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			failure("无查询结果");
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
				code += '<td>' + n.cntax + '</td>';
				code += '</tr> ';
			} else {
				return;
			}
			if (i == dataObj.length - 1) {
				window.clearInterval(bg_load);
				showPrintBt();
				return;
			}
		});
		start = end + 1;
		end += 100;
		$("table tbody").append(code);
	}, 500);

}
// TODO:打印
function　printPager(){
	var link='<link rel="stylesheet" href="../../css/bootstrap.min.css" />'+
	'<link rel="stylesheet" href="../../css/user_table.css" />';
	$("head link").remove();
	$("head style").remove();
	$(".table-div").printArea();
	$("head").append(link);
}