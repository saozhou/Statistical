$(document).ready(function() {
	$("body").css("opacity", "1");
	getContent();
});

function resize() {
	var _width = $('.table-div').width();
	$('.table-div th:first-child').width(_width * 0.3);
	$('.table-div td:first-child').width(_width * 0.3);
	$('.table-div th:nth-child(2)').width(_width * 0.3);
	$('.table-div td:nth-child(2)').width(_width * 0.3);
	$('.table-div th:nth-child(3)').width(_width * 0.4);
	$('.table-div td:nth-child(3)').width(_width * 0.4);
}

// TODO：获取表格内容
function getContent() {
	showTipBox();
	loading("正在加载用户信息...");
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
					var alterCode = '';
					alterCode += '<button>修改</button>';
					code += '<tr>';
					code += '<td>' + n.usname + '</td>';
					code += '<td contentEditable="true">' + n.uspassword
							+ '</td>';
					code += '<td>' + '<div class="area-div">' + '<span>'
							+ n.usplace + '</span>' + alterCode + '</div>'
							+ '</td>';
					code += '</tr> ';
				});
				$(".body table tbody tr").remove();
				$(".body table tbody").append(code);
				// 加载成功
				loadSuccess();
				tdChangeEvent();
				areaBtEvent();
				area_ganged_spinner();
				area_event();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// 加载失败
			failure("加载失败");
		}
	});
}

// TODO:保存
function save() {
	loading("正在保存...");
	var parent = $(".table-div table tbody td");
	var span = $("table tbody td span");
	var username = parent.eq(3 * row).text();
	var password = parent.eq(1 + 3 * row).text();
	var address = span.eq(row).text();
	var url = '/Statistic/User/userupdate';
	var json = '{';
	json += '"username":"' + username + '"';
	json += ',"password":"' + password + '"';
	json += ',"address":"' + address + '"';
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

// TODO:地区修改按钮事件的注册
function areaBtEvent() {
	var parent = $("table tbody td button");
	var span = $("table tbody td span");
	for (var i = 0; i < parent.length; i++) {
		parent[i].pos = i;
		parent.eq(i).click(function() {
			row = this.pos;
			showAreaSelectPop();
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

// 显示提示框
function showTipBox() {
	$(".tip").css("display", "block");
	$(".tip").css("display");
	$(".tip").css("opacity", "1");
}

// TODO:普通用户添加地区选择事件
function area_event() {
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

// TODO:显示地区选择弹窗
function showAreaSelectPop() {
	$(".area-select-cover").css("display", "block");
}

// TODO:隐藏地区选择弹窗
function hiddenAreaSelectPop() {
	$(".area-select-cover").css("display", "none");
}

// TODO:获取选择的地区信息

var ic_city = '城市';
var ic_county = '县区';
function getArea() {
	if (ic_city == "城市") {
		alert("请选择城市");
		return;
	}
	var span = $("table tbody td span");
	if (ic_county != "县区") {
		ic_county = ic_county.replace(" ", "");
		span.eq(row).text(ic_county);
	} else {
		ic_city = ic_city.replace(" ", "");
		span.eq(row).text(ic_city);
	}
	hiddenAreaSelectPop();
	save();
	ic_city = '城市';
	ic_county = '县区';
	
	$(".country span").text("国家");
	$(".province span").text("省份");
	$(".city span").text("城市");
	$(".county span").text("县区");
	$(".province").find("li").remove();
	$(".city").find("li").remove();
	$(".county").find("li").remove();
}