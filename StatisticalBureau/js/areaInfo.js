//地区联动下拉框
var country_name_array = new Array();
var area_info_data;

function area_ganged_spinner() {
	$.getJSON("../json/district_zh.json", function(data) {
		area_info_data = data;
		var code = "";
		var country_name_array_length = 0;
		$.each(data.COUNTRIES, function(i, n) {
			code += '<li  onclick=country_list_item_click(' + country_name_array_length + ')> ';
			code += n;
			code += '</li>';
			country_name_array[country_name_array_length] = i;
			country_name_array_length++;
		});
		$(".country ul").append(code);
	});
}
//获取省级行政区信息
var province_name_array = new Array();

function getProvince(pos) {
	var code = "";
	var province_name_array_length = 0;
	$.each(area_info_data[country_name_array[pos]], function(i, n) {
		code += '<li  onclick=province_list_item_click(' + province_name_array_length + ')> ';
		code += n;
		code += '</li>';
		province_name_array[province_name_array_length] = i;
		province_name_array_length++;
	});
	$(".province").find("li").remove();
	$(".province ul").append(code);
}
//获取市级行政区信息
var city_name_array = new Array();

function getCity(pos) {
	var code = "";
	var city_name_array_length = 0;
	$.each(area_info_data[province_name_array[pos]], function(i, n) {
		code += '<li  onclick=city_list_item_click(' + city_name_array_length + ')> ';
		code += n;
		code += '</li>';
		city_name_array[city_name_array_length] = i;
		city_name_array_length++;
	});
	$(".city").find("li").remove();
	$(".city ul").append(code);
}

//获取县级行政区信息
function getCounty(pos) {
	var code = "";
	var sum = 0;
	$.each(area_info_data[city_name_array[pos]], function(i, n) {
		code += '<li  onclick=county_list_item_click(' + sum + ')> ';
		code += n;
		code += '</li>';
		sum++;
	});
	$(".county").find("li").remove();
	$(".county ul").append(code);
}

var countryListIsShow = false; //国家下拉选择框是否显示
var provinceListIsShow = false; //省级行政区下拉选择框是否显示
var cityListIsShow = false; //市级行政区下拉选择框是否显示
var countyListIsShow = false; //市级行政区下拉选择框是否显示
//注册事件
function select_bt_click(pos) {
	switch(pos) {
		//国家下拉选择框点击事件
		case 0:
			var visiblity = countryListIsShow == false ? "visible" : "hidden";
			var opacity = countryListIsShow == false ? 1 : 0;
			countryListIsShow = countryListIsShow == false ? true : false;
			$(".country ul").eq(0).css("visibility", visiblity);
			$(".country ul").eq(0).css("opacity", opacity);
			break;
			//省级行政区下拉选择框点击事件
		case 1:
			var visiblity = provinceListIsShow == false ? "visible" : "hidden";
			var opacity = provinceListIsShow == false ? 1 : 0;
			provinceListIsShow = provinceListIsShow == false ? true : false;
			$(".province ul").eq(0).css("visibility", visiblity);
			$(".province ul").eq(0).css("opacity", opacity);
			break;
			//市级行政区下拉选择框点击事件
		case 2:
			var visiblity = cityListIsShow == false ? "visible" : "hidden";
			var opacity = cityListIsShow == false ? 1 : 0;
			cityListIsShow = cityListIsShow == false ? true : false;
			$(".city ul").eq(0).css("visibility", visiblity);
			$(".city ul").eq(0).css("opacity", opacity);
			break;
			//县级行政区下拉选择框点击事件
		case 3:
			var visiblity = countyListIsShow == false ? "visible" : "hidden";
			var opacity = countyListIsShow == false ? 1 : 0;
			countyListIsShow = countyListIsShow == false ? true : false;
			$(".county ul").eq(0).css("visibility", visiblity);
			$(".county ul").eq(0).css("opacity", opacity);
			break;
	}

}

//国家选择列表选项被点击修改显示值
function country_list_item_click(pos) {
	var content = $(".country ul li").eq(pos).text();
	if(content.length > 4)
		content = content.substring(0, 5);
	$(".country span").eq(0).text(content);
	$(".country ul").eq(0).css("visibility", "hidden");
	$(".country ul").eq(0).css("opacity", "0");
	countryListIsShow = false;
	//复原省市县
	$(".province span").eq(0).text("省份");
	$(".city span").eq(0).text("城市");
	$(".county span").eq(0).text("县区");
	getProvince(pos);
}
//省级行政区选择列表选项被点击修改显示值
function province_list_item_click(pos) {
	var content = $(".province ul li").eq(pos).text();
	if(content.length > 4)
		content = content.substring(0, 5);
	$(".province span").eq(0).text(content);
	$(".province ul").eq(0).css("visibility", "hidden");
	$(".province ul").eq(0).css("opacity", "0");
	provinceListIsShow = false;
	$(".city span").eq(0).text("城市");
	$(".county span").eq(0).text("县区");
	getCity(pos);
}
//市级行政区选择列表选项被点击修改显示值
function city_list_item_click(pos) {
	var content = $(".city ul li").eq(pos).text();
	if(content.length > 4)
		content = content.substring(0, 5);
	$(".city span").eq(0).text(content);
	$(".city ul").eq(0).css("visibility", "hidden");
	$(".city ul").eq(0).css("opacity", "0");
	cityListIsShow = false;
	$(".county span").eq(0).text("县区");
	getCounty(pos);
}
//县级行政区选择列表选项被点击修改显示值
function county_list_item_click(pos) {
	var content = $(".county ul li").eq(pos).text();
	if(content.length > 4)
		content = content.substring(0, 5);
	$(".county span").eq(0).text(content);
	$(".county ul").eq(0).css("visibility", "hidden");
	$(".county ul").eq(0).css("opacity", "0");
	countyListIsShow = false;
}
//将四级联动全部重置为初始状态
function resetSpinner() {
	countryListIsShow = false;
	provinceListIsShow = false;
	cityListIsShow = false;
	countyListIsShow = false;
	//复原国省市县
	$(".country span").eq(0).text("国家");
	$(".province span").eq(0).text("省份");
	$(".city span").eq(0).text("城市");
	$(".county span").eq(0).text("县区");
	//可见度以及透明度复原
	$(".country ul").eq(0).css("visibility", "hidden");
	$(".country ul").eq(0).css("opacity", "0");
	$(".province ul").eq(0).css("visibility", "hidden");
	$(".province ul").eq(0).css("opacity", "0");
	$(".city ul").eq(0).css("visibility", "hidden");
	$(".city ul").eq(0).css("opacity", "0");
	$(".county ul").eq(0).css("visibility", "hidden");
	$(".county ul").eq(0).css("opacity", "0");
}