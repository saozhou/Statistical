$(document).ready(function() {
	$("body").css("opacity", "1");
	$(".find").css("display", "initial");
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
	showTipBox();
	loading("正在查询...");
	var code = '';
	var url = '../../IntegratedQuery/gfReferenceGdpSearch';
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
				if (data == "当量系数对照表未上传") {
					failure("当量系数对照表未上传");
					showUploadBt();
					return;
				} else {
					dataObj = data;
				}
				// 遍历数据生成表格
				$.each(data, function(i, n) {
					if (i >= start && i <= end) {
						code += '<tr>';
						code += '<td>' + n.incode + '</td>';
						code += '<td>' + n.inname + '</td>';
						code += '<td contentEditable="true">' + n.incoefficient
								+ '</td>';
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
				tdChangeEvent();
				start = end + 1;
				end += 100;
				bgLoad();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// 查询失败
			failure("查询失败");
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
				code += '<td contentEditable="true">' + n.incoefficient
						+ '</td>';
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
		tdChangeEvent();

	}, 500);

}
// 显示提示框
function showTipBox() {
	$(".tip").css("display", "block");
	$(".tip").css("display");
	$(".tip").css("opacity", "1");
}

// TODO:保存
function save() {
	loading("正在保存...");
	var parent = $(".table-div table tbody td");
	var Incoefficient = parent.eq(2 + 3 * row).text();
	var Incode = parent.eq(3 * row).text();
	var url = '/Statistic/ChangeToSave/gfReference';
	var json = '{';
	json += '"Incoefficient":"' + Incoefficient + '"';
	json += ',"Incode":"' + Incode + '"';
	json += '}';

	$.ajax({
		url : url,
		type : "post",
		dataType : "text",
		data : json,
		cache : false,
		async : true,
		contentType : "application/json; charset=utf-8",
		success : function(data) {
			if (data == "保存成功") {
				$(".tip img").attr("src", "");
				$(".tip p").text("保存成功");
				setTimeout(function() {
					closeTip();
				}, 3000);
				return;
			}

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			failure("保存失败");
		}
	});
}

// TODO:单元格内容更改事件处理
var str = '';// 记录修改前的内容
var row = 0;// 修改的行下标
function tdChangeEvent() {
	removeTdChangeEvent();
	var parent = $(".body table tbody td");
	for (var i = 0; i < parent.length; i++) {
		parent[i].pos = i;
		parent.eq(i).blur(function(i) {
			if (str != $(".body table tbody td").eq(this.pos).text()) {
				changeConfirm();
			}
		});
		parent.eq(i).focus(function(i) {
			str = $(".body table tbody td").eq(this.pos).text();
			row = parseInt(this.pos / 3);
			return;
		});
	}

}
// TODO:移除单元格内容更改事件
function removeTdChangeEvent() {
	var parent = $(".body table tbody td");
	for (var i = 0; i < parent.length; i++) {
		parent.eq(i).unbind("blur");
		parent.eq(i).unbind("focus");
	}
}
// TODO：修改确认框
function changeConfirm() {
	var r = confirm("内容已修改,是否提交保存！");
	if (r == true) {
		save();
	} else {
	}
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
			url : '/Statistic/FileUpload/gfReferenceUpload',
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
					find();
				}, 2000);
				return;
			}
		}).fail(function(res) {
			showTip();
			failure("上传失败");
		});
	});

}


// TODO:打印
function　printPager(){
	var link='<link rel="stylesheet" href="../../css/bootstrap.min.css" />'+
	'<link rel="stylesheet" href="../../css/administrator_table.css"/>';
	$("head link").remove();
	$("head style").remove();
	$(".table-div").printArea();
	$("head").append(link);
}