//一级标签下拉表名称
var firstTabSpinner = new Array("#input-second-list", "#calculate-second-list", "#gather-second-list", "#query-second-list");
//一级标签灰色图标
var firstTabGrayIcon = new Array("../img/gray_data_input.png", "../img/gray_data_calculate.png",
	"../img/gray_data_gather.png", "../img/gray_data_query.png");
//一级标签白色图标
var firstTabWhiteIcon = new Array("../img/white_data_input.png", "../img/white_data_calculate.png",
	"../img/white_data_gather.png", "../img/white_data_query.png");
//记录当前点击的一级标签索引
var cur_first_tab_index = -1;
//TODO:一级标签点击事件
function firstTabClick(index) {
	if(cur_first_tab_index != -1) {
		$(".first-tab").eq(cur_first_tab_index).find("img").eq(1).css("transform", "rotate(360deg)");
	}
	closeSecondTab();
	closeThirdTab();
	closeFourthTab();
	$(".first-tab").eq(index).css("background-color", "rgb(104, 223, 240)");
	$(".first-tab").eq(index).find("img").eq(0).attr("src", firstTabWhiteIcon[index]);
	$(".first-tab").eq(index).css("color", "white");
	$(".first-tab").eq(index).find("img").eq(1).css("transform", "rotate(180deg)");
	cur_first_tab_index = index;
	//收起其它一级标签下拉列表
	for(var i = 0; i < firstTabSpinner.length; i++) {
		if(i != index) {
			$(firstTabSpinner[i]).collapse("hide");
			$(".first-tab").eq(i).css("background-color", "transparent");
			$(".first-tab").eq(i).find("img").eq(0).attr("src", firstTabGrayIcon[i]);
			$(".first-tab").eq(i).css("color", "#aeb2b7");
		}
	}
}

//TODO:一级标签鼠标悬浮事件
function firstTabMove(index) {
	$(".first-tab").eq(index).css("background-color", "rgb(104, 223, 240)");
	$(".first-tab").eq(index).find("img").eq(0).attr("src", firstTabWhiteIcon[index]);
	$(".first-tab").eq(index).css("color", "white");
}
//TODO:一级标签鼠标移出事件
function firstTabOut(index) {
	//当index等于当前点击的标签索引不予执行鼠标移出事件
	if(cur_first_tab_index == index)
		return;
	$(".first-tab").eq(index).css("background-color", "transparent");
	$(".first-tab").eq(index).find("img").eq(0).attr("src", firstTabGrayIcon[index]);
	$(".first-tab").eq(index).css("color", "#aeb2b7");
}
//所有二级标签名
var secondTabName = new Array(".gf", ".guoshui", ".dishui", ".total-gdp", ".s-tax", ".s-travel-tax", ".s-gdp", ".s-travel-gdp",
	".travel-gdp", ".travel-tax", ".basics-query", ".synthesis-query", ".custom-query");
//所有二级标签下拉列表名
var secondTabSpinner = new Array("", "", "", "", "", "", "", "", ".travel-gdp-list", ".travel-tax-list", ".basics-query-list", ".synthesis-query-list", "");
//记录当前点击的二级标签索引
var cur_second_tab_index = -1;
//当前点击的二级标签是否显示
var cur_second_tab_isShow = false;
//TODO:二级标签点击事件
function secondTabClick(index) {
	if(cur_second_tab_index == index && cur_second_tab_isShow == true) {
		$(secondTabName[index]).css("color", "#aeb2b7");
		$(secondTabSpinner[index]).css("display", "none");
		cur_second_tab_isShow = false;
		return;
	}
	if(cur_second_tab_index != -1) {
		$(secondTabName[cur_second_tab_index]).find("img").css("transform", "rotate(360deg)");
	}
	closeThirdTab();
	closeFourthTab();
	$(secondTabName[index]).css("color", "white");
	$(secondTabSpinner[index]).css("display", "block");
	$(secondTabName[index]).find("img").css("transform", "rotate(180deg)");

	cur_second_tab_index = index;
	cur_second_tab_isShow = true;
	//将其它二级标签收起
	for(var i = 0; i < secondTabName.length; i++) {
		if(i != index) {
			$(secondTabName[i]).css("color", "#aeb2b7");
			$(secondTabSpinner[i]).css("display", "none");
		}
	}
	//二级标签显示表
	switch(index) {
		case 0:
			$(".content-div iframe").attr("src", "user_table/gf.html");
			break;
		case 1:
			$(".content-div iframe").attr("src", "user_table/guoshui.html");
			break;
		case 2:
			$(".content-div iframe").attr("src", "user_table/dishui.html");
			break;
		case 3:
			$(".content-div iframe").attr("src", "user_table/total_gdp.html");
			break;
		case 4:
			$(".content-div iframe").attr("src", "user_table/s-tax.html");
			break;
		case 5:
			$(".content-div iframe").attr("src", "user_table/s-travel-tax.html");
			break;
		case 6:
			$(".content-div iframe").attr("src", "user_table/s-gdp.html");
			break;
		case 7:
			$(".content-div iframe").attr("src", "user_table/s-travel-gdp.html");
			break;
		case 12:
			$(".content-div iframe").attr("src", "user_table/custom_query.html");
			break;
		default:
			break;
	}

}

//TODO:收起所有的二级标签
function closeSecondTab() {
	for(var i = 0; i < secondTabName.length; i++) {
		$(secondTabName[i]).css("color", "#aeb2b7");
		$(secondTabSpinner[i]).css("display", "none");
	}
}
//所有三级标签名
var thirdTabName = new Array(".travel-gdp-industry", ".travel-gdp-class", ".travel-gdp-big", ".travel-tax-industry", ".travel-tax-class", ".travel-tax-big",
	".basics-query-guoshui", ".basics-query-dishui", ".basics-query-gf", ".basics-query-totalgdp", ".synthesis-query-travelgdp", ".synthesis-query-traveltax",
	".synthesis-query-sgdp", ".synthesis-query-stax", ".synthesis-query-straveltax", ".synthesis-query-stravelgdp");
//所有三级级标签下拉列表名
var thirdTabSpinner = new Array("", "", "", "", "", "", "", "", "", "", ".synthesis-query-travelgdp-list", ".synthesis-query-traveltax-list", "", "", "", "");
//记录当前点击的二级标签索引
var cur_third_tab_index = -1;
//当前点击的二级标签是否显示
var cur_third_tab_isShow = false;
//TODO:三级标签点击事件
function thirdTabClick(index) {
	if(cur_third_tab_index == index && cur_third_tab_isShow == true) {
		$(thirdTabName[index]).css("color", "#aeb2b7");
		$(thirdTabSpinner[index]).css("display", "none");
		cur_third_tab_isShow = false;
		return;
	}
	$(thirdTabName[index]).css("color", "white");
	$(thirdTabSpinner[index]).css("display", "block");
	closeFourthTab();
	cur_third_tab_index = index;
	cur_third_tab_isShow = true;
	//将其它三级标签收起
	for(var i = 0; i < thirdTabName.length; i++) {
		if(i != index) {
			$(thirdTabName[i]).css("color", "#aeb2b7");
			$(thirdTabSpinner[i]).css("display", "none");
		}
	}

	//三级标签显示表
	switch(index) {
		case 0:
			$(".content-div iframe").attr("src", "user_table/travel-gdp-industry.html");
			break;
		case 1:
			$(".content-div iframe").attr("src", "user_table/travel-gdp-class.html");
			break;
		case 2:
			$(".content-div iframe").attr("src", "user_table/travel-gdp-big.html");
			break;
		case 3:
			$(".content-div iframe").attr("src", "user_table/travel-tax-industry.html");
			break;
		case 4:
			$(".content-div iframe").attr("src", "user_table/travel-tax-class.html");
			break;
		case 5:
			$(".content-div iframe").attr("src", "user_table/travel-tax-big.html");
			break;
		case 6:
			$(".content-div iframe").attr("src", "user_table/basics-query-guoshui.html");
			break;
		case 7:
			$(".content-div iframe").attr("src", "user_table/basics-query-dishui.html");
			break;
		case 8:
			$(".content-div iframe").attr("src", "user_table/basics-query-gf.html");
			break;
		case 9:
			$(".content-div iframe").attr("src", "user_table/basics-query-totalgdp.html");
			break;
		case 12:
			$(".content-div iframe").attr("src", "user_table/synthesis-query-sgdp.html");
			break;
		case 13:
			$(".content-div iframe").attr("src", "user_table/synthesis-query-stax.html");
			break;
		case 14:
			$(".content-div iframe").attr("src", "user_table/synthesis-query-straveltax.html");
			break;
		case 15:
			$(".content-div iframe").attr("src", "user_table/synthesis-query-stravelgdp.html");
			break;
		default:
			break;
	}
}
//TODO:收起所有的三级标签
function closeThirdTab() {
	for(var i = 0; i < thirdTabName.length; i++) {
		$(thirdTabName[i]).css("color", "#aeb2b7");
		$(thirdTabSpinner[i]).css("display", "none");
	}
}
var fourthTabName = new Array(".synthesis-query-travelgdp-industry", ".synthesis-query-travelgdp-class", ".synthesis-query-travelgdp-big",
	".synthesis-query-traveltax-industry", ".synthesis-query-traveltax-class", ".synthesis-query-traveltax-big");
//TODO:四级标签点击事件
function fourthTabClick(index) {
	$(fourthTabName[index]).css("color", "white");
	for(var i = 0; i < fourthTabName.length; i++) {
		if(i != index) {
			$(fourthTabName[i]).css("color", "#aeb2b7");
		}
	}

	//四级标签显示表
	switch(index) {
		case 0:
			$(".content-div iframe").attr("src", "user_table/synthesis-query-travelgdp-industry.html");
			break;
		case 1:
			$(".content-div iframe").attr("src", "user_table/synthesis-query-travelgdp-class.html");
			break;
		case 2:
			$(".content-div iframe").attr("src", "user_table/synthesis-query-travelgdp-big.html");
			break;
		case 3:
			$(".content-div iframe").attr("src", "user_table/synthesis-query-traveltax-industry.html");
			break;
		case 4:
			$(".content-div iframe").attr("src", "user_table/synthesis-query-traveltax-class.html");
			break;
		case 5:
			$(".content-div iframe").attr("src", "user_table/synthesis-query-traveltax-big.html");
			break;
		default:
			break;
	}
}
//TODO:关闭所有的四级标签
function closeFourthTab() {
	for(var i = 0; i < fourthTabName.length; i++)
		$(fourthTabName[i]).css("color", "#aeb2b7");
}