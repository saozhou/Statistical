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

// TODO：获取表格内容
function getContent() {
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
				// 遍历数据生成表格
				$.each(data, function(i, n) {
					code += '<tr>';
					code += '<td>' + n.incode + '</td>';
					code += '<td>' + n.inname + '</td>';
					code += '</tr> ';
				});
				$(".body table tbody tr").remove();
				$(".body table tbody").append(code);
				// 加载成功
				loadSuccess();
				showUploadBt();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// 加载失败
			failure("无内容,请上传");
			showUploadBt();
		}
	});
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