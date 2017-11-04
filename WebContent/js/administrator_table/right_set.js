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
	loading("正在加载用户权限信息...");
	var code = '';
	var url = '/Statistic/User/alluser';
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
					var powerCode = '';
					var dataInsert = n.dainsert == 1 ? "checked" : "";// 数据输入
					var dataSearch = n.dasearch == 1 ? "checked" : "";// 数据查询
					var dataCheck = n.dacheck == 1 ? "checked" : "";// 数据汇总
					var dataMath = n.damath == 1 ? "checked" : "";// 数据计算
					powerCode += '<input type="checkbox"' + dataInsert
							+ '/> 数据输入&nbsp;&nbsp;&nbsp;&nbsp;';
					powerCode += '<input type="checkbox"' + dataSearch
							+ '/> 数据查询&nbsp;&nbsp;&nbsp;&nbsp;';
					powerCode += '<input type="checkbox"' + dataCheck
							+ '/> 数据汇总&nbsp;&nbsp;&nbsp;&nbsp;';
					powerCode += '<input type="checkbox"' + dataMath
							+ '/> 数据计算';
					code += '<tr>';
					code += '<td>' + n.usname + '</td>';
					code += '<td>' + powerCode + '</td>';
					code += '</tr> ';
				});console.log(code)
				$(".body table tbody tr").remove();
				$(".body table tbody").append(code);
				// 加载成功
				loadSuccess();
				powerChangeEvent();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// 加载失败
			failure("加载失败");
		}
	});
}

// TODO:权限修改事件
var row = 0;
function powerChangeEvent() {
	var parent = $(".body table tbody input");
	for (var i = 0; i < parent.length; i++) {
		parent[i].pos = i;
		parent.eq(i).change(function() {
			row = parseInt(this.pos / 4);
			changeConfirm();
		});
	}
}

// TODO:保存
function save() {
	loading("正在保存...");
	var parent = $(".table-div table tbody td input");
	var username = $(".table-div table tbody td").eq(row * 2).text();
	var datainsert = parent.eq(row*4).prop('checked') == true ? 1 : 0;
	var datamath = parent.eq(3+row*4).prop('checked') == true ? 1 : 0;
	var datasearch = parent.eq(1+row*4).prop('checked') == true ? 1 : 0;
	var datacheck = parent.eq(2+row*4).prop('checked') == true ? 1 : 0;

	var url = '/Statistic/User/userpowerupdate';
	var json = '{';
	json += '"username":"' + username + '"';
	json += ',"datainsert":"' + datainsert + '"';
	json += ',"datamath":"' + datamath + '"';
	json += ',"datasearch":"' + datasearch + '"';
	json += ',"datacheck":"' + datacheck + '"';
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
			if (data == "modifySuccess") {
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

// 显示提示框
function showTipBox() {
	$(".tip").css("display", "block");
	$(".tip").css("display");
	$(".tip").css("opacity", "1");
}

// TODO：修改确认框
function changeConfirm() {
	var r = confirm("权限已修改,是否提交保存！");
	if (r == true) {
		save();
	} else {
	}
}