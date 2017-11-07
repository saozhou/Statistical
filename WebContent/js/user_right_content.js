var cur_year;
$(document).ready(function() {
	init();
});
// 用户权限
var cur_year;
var datainsert;
var datamath;
var datasearch;
var datacheck;

// 初始化
function init() {
	var url = decodeURI(location.href);
	var userType = url.split("?")[1].split("=")[1];
	var area = url.split("?")[2].split("=")[1];
	cur_year = url.split("?")[3].split("=")[1];
	datainsert = url.split("?")[4].split("=")[1];
	datamath = url.split("?")[5].split("=")[1];
	datasearch = url.split("?")[6].split("=")[1];
	datacheck = url.split("?")[7].split("=")[1];

	var firstTab = $(".first-tab");
	for (var i = 0; i < 4; i++) {
		switch (i) {
		case 0:
			if (datainsert == 0) {
				firstTab.eq(i).removeAttr("data-target");
			}
			break;
		case 1:
			if (datamath == 0) {
				firstTab.eq(i).removeAttr("data-target");
			}
			break;
		case 2:
			if (datacheck == 0) {
				firstTab.eq(i).removeAttr("data-target");
			}
			break;
		case 3:
			if (datasearch == 0) {
				firstTab.eq(i).removeAttr("data-target");
			}
			break;
		default:
			break;
		}
	}

	if (userType == "普通用户") {
		$(".area").css("display", "none");
		$(".area-asure").css("display", "none");
	}
	$(".cur-year span").text(cur_year);
	var str = area.split(",");
	for (var i = 0; i < str.length; i++) {
		if (str[i] == "") {
			$(".cur-area span").eq(i).text("请选择");
			break;
		}
		$(".cur-area span").eq(i).text(str[i] + " ");
	}
	$(".logo p").text(userType);
	register_event();
	register_area_event();
	area_ganged_spinner();
}
var yearListIsShow = false; // 年份选择下拉框列表项目是否显示
var areaSelectShow = false; // 地区选择下拉框是否显示
// 事件注册
function register_event() {
	$(document).click(function() {
		// 隐藏年份下拉列表
		$(".year ul").css("visibility", "hidden");
		$(".year ul").css("opacity", "0");
		yearListIsShow = false;
		// 隐藏地区下拉列表
		$(".area-select").css("display", "none");
		areaSelectShow = false;

		$(".content-div .cover").css("display", "none");
	});
	// 年份选择
	$(".year-bt").click(function(e) {
		e.stopPropagation(); // 阻止冒泡
		createYearSpinner();
		var visiblity = yearListIsShow == false ? "visible" : "hidden";
		var opacity = yearListIsShow == false ? 1 : 0;
		var display = yearListIsShow == false ? "block" : "none";
		yearListIsShow = yearListIsShow == false ? true : false;
		$(".year ul").css("visibility", visiblity);
		$(".year ul").css("opacity", opacity);
		$(".content-div .cover").css("display", display);
	});
	// 地区选择
	$(".area-bt").click(function(e) {
		e.stopPropagation(); // 阻止冒泡
		var display = areaSelectShow == false ? "block" : "none";
		areaSelectShow = areaSelectShow == false ? true : false;
		$(".area-select").css("display", display);
		$(".content-div .cover").css("display", display);
	});
}

// 创建年份下拉列表
function createYearSpinner() {
	var min_year = 1949; // 最小可选择年份,默认为1949
	var date = new Date();
	// 获取当前年
	var cur_year = date.getFullYear();
	var code = "";
	for (var i = cur_year; i >= min_year; i--) {
		code += '<li  onclick=year_item_click(' + i + ')> ';
		code += i;
		code += '</li>';
	}
	$(".year ul").find("li").remove();
	$(".year ul").append(code);
}

function year_item_click(year) {
	$(".year span").eq(0).text(year);
	yearListIsShow = false;
	$(".cur-year  span").text(year);
	if (year == cur_year) {
		return;
	} else {
		cur_year = year;
		yearChangeSave();
	}
}

// TODO:年份修改保存
function yearChangeSave() {
	var url = '/Statistic/ChangeToSave/Session';
	var json = '{';
	json += '"year":"' + cur_year + '"';
	json += '}';

	$.ajax({
		url : url,
		type : "post",
		dataType : "text",
		data : json,
		cache : false,
		async : true,
		contentType : "application/json; charset=utf-8",
		success : function(data, textStatus, jqXHR) {
			if ('success' == textStatus) {
				if (data == "保存成功") {
					alert("年份修改成功");
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("年份修改失败");
		}
	});
}

// TODO:地区修改保存
function areaChangeSave() {
	$(".area-select").css("display", "none");
	var parent = $(".cur-area span");
	var country = parent.eq(0).text();
	country = country.replace(" ", "");
	var province = parent.eq(1).text();
	province = province.replace(" ", "");
	var city = parent.eq(2).text();
	city = city.replace("市", "");
	city = city.replace(" ", "");
	var county = parent.eq(3).text();
	county = county.replace(" ", "");
	var url = '/Statistic/ChangeToSave/Session';
	var json = '{';
	json += '"country":"' + country + '"';
	if (province != "")
		json += ',"province":"' + province + '"';
	if (city != "")
		json += ',"city":"' + city + '"';
	if (county != "" && county != "县区") {
		json += ',"county":"' + county + '"';
	} else {
		json += ',"county":"' + "" + '"';
	}
	json += '}';

	$.ajax({
		url : url,
		type : "post",
		dataType : "text",
		data : json,
		cache : false,
		async : false,
		contentType : "application/json; charset=utf-8",
		success : function(data, textStatus, jqXHR) {
			if ('success' == textStatus) {
				if (data == "保存成功") {
					alert("地区修改成功");
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("地区修改失败");
		}
	});
}