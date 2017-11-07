$(document).ready(function() {
	$("body").css("opacity", "1");
	getContent();
});

function resize() {
	var _width = $('.table-div').width();
	$('.table-div th:first-child').width(_width * 0.4);
	$('.table-div td:first-child').width(_width * 0.4);
	$('.table-div th:nth-child(2)').width(_width * 0.6);
	$('.table-div td:nth-child(2)').width(_width * 0.6);
}
var dataObj;
var start = 0;
var end = 100;
// TODO：获取表格内容
function getContent() {
	$(".upload").css("margin-left","0px");
	if (bg_load != null) {
		start = 0;
		end = 100;
		window.clearInterval(bg_load);
	}
	hiddenPrintBt();
	showTipBox();
	loading("正在加载...");
	var code = '';
	var url = '/Statistic/IntegratedQuery/allCodeSearch';
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
				dataObj = data;
				// 遍历数据生成表格
				$.each(data, function(i, n) {
					if (i >= start && i <= end) {
						code += '<tr>';
						code += '<td>' + n.incode + '</td>';
						code += '<td>' + n.inname + '</td>';
						code += '</tr> ';
					} else {
						return;
					}
				});
				$(".body table tbody tr").remove();
				$(".body table tbody").append(code);
				// 加载成功
				loadSuccess();
				showUploadBt();
				start = end + 1;
				end += 100;
				bgLoad();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// 加载失败
			failure("无内容,请上传");
			showUploadBt();
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
				code += '<td>' + n.incode + '</td>';
				code += '<td>' + n.inname + '</td>';
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
		$(".body table tbody").append(code);

	}, 500);

}
// 显示提示框
function showTipBox() {
	$(".tip").css("display", "block");
	$(".tip").css("display");
	$(".tip").css("opacity", "1");
}

// TODO:上传
function upload() {
	// 触发文件选择框
	$(".file").trigger("click");
	// 获取文件路径
	var fileUrl = '';
	$(".file").change(function() {
		showTip();
		loading("正在上传...");
		$.ajax({
			url : '/Statistic/FileUpload/codeDictionaryUpload',
			type : 'POST',
			cache : false,
			data : new FormData($('.upload-form')[0]),
			processData : false,
			contentType : false
		}).done(function(res) {
			if (res == "上传成功") {
				showTip();
				$(".tip img").attr("src", "");
				$(".tip p").text("上传成功");
				setTimeout(function() {
					getContent();
				}, 2000);
				return;
			}
		}).fail(function(res) {
			showTip();
			failure("上传失败");
		});
	});

}

// TODO:下载
function download() {
	var url = '/Statistic/FileDownload/fileDownLoad?type=' + 16;
	location.href = url;
}

//TODO:打印
function　printPager(){
	var link='<link rel="stylesheet" href="../../css/bootstrap.min.css" />'+
	'<link rel="stylesheet" href="../../css/administrator_table.css"/>';
	$("head link").remove();
	$("head style").remove();
	$(".table-div").printArea();
	$("head").append(link);
}