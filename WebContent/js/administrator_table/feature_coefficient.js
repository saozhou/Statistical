$(document).ready(function() {
	$("body").css("opacity", "1");
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

// TODO：查找
function find() {
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
					return;
				}
				// 遍历数据生成表格
				$.each(data, function(i, n) {
					code += '<tr>';
					code += '<td>' + n.incode + '</td>';
					code += '<td>' + n.inname + '</td>';
					code += '<td contentEditable="true">' + n.incoefficient
							+ '</td>';
					code += '</tr> ';
				});
				$(".body table tbody tr").remove();
				$(".body table tbody").append(code);
				// 加载成功
				loadSuccess();
				tdChangeEvent();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// 查询失败
			failure("查询失败");

		}
	});
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
// TODO：修改确认框
function changeConfirm() {
	var r = confirm("内容已修改,是否提交保存！");
	if (r == true) {
		save();
	} else {
	}
}