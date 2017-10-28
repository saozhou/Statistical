var yearListIsShow = false; //年份选择下拉框列表项目是否显示
//年份选择按钮点击事件
function yearSelectClick() {
	createYearSpinner();
	var visiblity = yearListIsShow == false ? "visible" : "hidden";
	var opacity = yearListIsShow == false ? 1 : 0;
	yearListIsShow = yearListIsShow == false ? true : false;
	$(".year ul").eq(0).css("visibility", visiblity);
	$(".year ul").eq(0).css("opacity", opacity);
}

//创建年份下拉列表
function createYearSpinner() {
	var min_year = 1949; //最小可选择年份,默认为1949
	var date = new Date();
	//获取当前年
	var cur_year = date.getFullYear();
	var code = "";
	for(var i = cur_year; i >= min_year; i--) {
		code += '<li  onclick=year_item_click(' + i + ')> ';
		code += i;
		code += '</li>';
	}
	$(".year ul").find("li").remove();
	$(".year ul").append(code);
}

function year_item_click(year) {
	var str = $(".year span").eq(0).text(year);
	$(".year ul").eq(0).css("visibility", "hidden");
	$(".year ul").eq(0).css("opacity", "0");
	yearListIsShow = false;
	$(".cur-year  span").text(year);
}