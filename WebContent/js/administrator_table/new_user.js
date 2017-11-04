$(document).ready(function() {
	$("body").css("opacity", "1");
});
// TODO:添加超级用户
function addSuperUser() {
	$(".super-user").css("display", "block");
	$(".ordinary-user").css("display", "none");
	showTable();
	hiddenBtGroup();
	superUserResize();
}
// TODO:添加普通用户
function addOrdinaryUser() {
	$(".ordinary-user").css("display", "block");
	$(".super-user").css("display", "none");
	showTable();
	hiddenBtGroup();
	ordinaryUserResize();
	area_ganged_spinner();
	or_area_event();
}
// TODO:页面自适应
function resize() {
	superUserResize();
	ordinaryUserResize();
}

// 超级用户添加表自适应
function superUserResize() {
	var _width = $('.super-user').width();
	$('.super-user th:first-child').width(_width * 0.4);
	$('.super-user td:first-child').width(_width * 0.4);
	$('.super-user th:nth-child(2)').width(_width * 0.4);
	$('.super-user td:nth-child(2)').width(_width * 0.4);
	$('.super-user th:nth-child(3)').width(_width * 0.2);
	$('.super-user td:nth-child(3)').width(_width * 0.2);
}

// 普通用户添加表自适应
function ordinaryUserResize() {
	var _width = $('.ordinary-user').width();
	$('.ordinary-user th:first-child').width(_width * 0.2);
	$('.ordinary-user td:first-child').width(_width * 0.2);
	$('.ordinary-user th:nth-child(2)').width(_width * 0.2);
	$('.ordinary-user td:nth-child(2)').width(_width * 0.2);
	$('.ordinary-user th:nth-child(3)').width(_width * 0.35);
	$('.ordinary-user td:nth-child(3)').width(_width * 0.35);
	$('.ordinary-user th:nth-child(4)').width(_width * 0.15);
	$('.ordinary-user td:nth-child(4)').width(_width * 0.15);
	$('.ordinary-user th:nth-child(5)').width(_width * 0.1);
	$('.ordinary-user td:nth-child(5)').width(_width * 0.1);
}

// 隐藏按钮组
function hiddenBtGroup() {
	$(".select-bt-group").css("display", "none");
}

// 返回
function back() {
	$(".super-user").css("display", "none");
	$(".ordinary-user").css("display", "none");
	$(".select-bt-group").css("display", "block");
}

// 显示提示框
function showTipBox() {
	$(".tip").css("display", "block");
	$(".tip").css("display");
	$(".tip").css("opacity", "1");
}

// TODO:显示表格
function showTable() {
	$(".table-div table").css("display", "block");
	$(".table-div table").css("display");
	$(".table-div table").css("opacity", "1");
}
// TODO:隐藏表格
function hiddenTable() {
	$(".table-div table").css("display", "none");
	$(".table-div table").css("display");
	$(".table-div table").css("opacity", "0");
}

// TODO:超级用户继续添加
function su_continue_add() {
	var parent = $(".super-user table tbody td");
	parent.eq(0).text("");
	parent.eq(1).text("");
}

// TODO:超级用户确认添加保存
function su_add() {

	var parent = $(".super-user table tbody td");
	var username = parent.eq(0).text();
	var password = parent.eq(1).text();
	var userpower = 2;
	if (username == "" || password == "") {
		alert("用户名和密码不能为空");
		return;
	}
	var url = '/Statistic/User/useradd';
	var json = '{';
	json += '"username":"' + username + '"';
	json += ',"password":"' + password + '"';
	json += ',"userpower":"' + userpower + '"';
	json += '}';

	loading("正在保存...");
	$.ajax({
		url : url,
		type : "post",
		dataType : "text",
		data : json,
		cache : false,
		async : true,
		contentType : "application/json; charset=utf-8",
		success : function(data) {
			isAdding = false;
			if (data == "用户名已存在") {
				failure("用户名已存在");
				return;
			} else if (data == "添加成功") {
				$(".tip img").attr("src", "");
				$(".tip p").text("添加成功");
				setTimeout(function() {
					closeTip();
				}, 3000);
				return;
			}

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			failure("保存失败");
			isAdding = false;
		}
	});
}

// TODO:保存普通用户添加信息
function or_add() {
	var parent = $(".ordinary-user table tbody td");
	var username = parent.eq(0).text();
	var password = parent.eq(1).text();
	var city = $(".ordinary-user table tbody td span").eq(2).text();
	city = city.replace(" ", "");
	var county = $(".ordinary-user table tbody td span").eq(3).text();
	county = county.replace(" ", "");
	var userpower = 3;
	var power_parent = $(".ordinary-user table tbody td input");
	var datainsert = power_parent.eq(0).prop('checked') == true ? 1 : 0;
	var datamath = power_parent.eq(3).prop('checked') == true ? 1 : 0;
	var datasearch = power_parent.eq(1).prop('checked') == true ? 1 : 0;
	var datacheck = power_parent.eq(2).prop('checked') == true ? 1 : 0;
	var url = '/Statistic/User/useradd';
	var json = '{';
	json += '"username":"' + username + '"';
	json += ',"password":"' + password + '"';
	json += ',"userpower":"' + userpower + '"';
	json += ',"datainsert":"' + datainsert + '"';
	json += ',"datamath":"' + datamath + '"';
	json += ',"datasearch":"' + datasearch + '"';
	json += ',"datacheck":"' + datacheck + '"';
	if (county != "县区")
		json += ',"county":"' + county + '"';
	else
		json += ',"city":"' + city + '"';
	json += '}';

	loading("正在保存...");
	$.ajax({
		url : url,
		type : "post",
		dataType : "text",
		data : json,
		cache : false,
		async : true,
		contentType : "application/json; charset=utf-8",
		success : function(data) {
			isAdding = false;
			if (data == "用户名已存在") {
				failure("用户名已存在");
				return;
			} else if (data == "添加成功") {
				$(".tip img").attr("src", "");
				$(".tip p").text("添加成功");
				setTimeout(function() {
					closeTip();
				}, 3000);
				return;
			}

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			failure("保存失败");
			isAdding = false;
		}
	});
}

// TODO:普通用户继续添加
function or_continue_add() {
	var parent = $(".ordinary-user table tbody td");
	parent.eq(0).text("");
	parent.eq(1).text("");

	var area_parent = $(".ordinary-user table tbody span");
	area_parent.eq(0).text("国家");
	area_parent.eq(1).text("省份");
	area_parent.eq(2).text("城市");
	area_parent.eq(3).text("县区");

	var power_parent = $(".ordinary-user table tbody input");
	power_parent.eq(0).prop("checked", false);
	power_parent.eq(1).prop("checked", false);
	power_parent.eq(2).prop("checked", false);
	power_parent.eq(3).prop("checked", false);
}

// TODO:普通用户添加地区选择事件
function or_area_event() {
	$(".country-bt").click(function() {
		$(".country ul").css("display", "block");
	});
	$(".province-bt").click(function() {
		$(".province ul").css("display", "block");
	});
	$(".city-bt").click(function() {
		$(".city ul").css("display", "block");
	});
	$(".county-bt").click(function() {
		$(".county ul").css("display", "block");
	});
}