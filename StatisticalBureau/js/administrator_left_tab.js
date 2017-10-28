//一级标签灰色图标
var firstTabGrayIcon = new Array("../img/gray_redact.png", "../img/gray_contrast.png",
	"../img/gray_code_storage.png", "../img/gray_user_manage.png");
//一级标签白色图标
var firstTabWhiteIcon = new Array("../img/white_redact.png", "../img/white_contrast.png",
	"../img/white_code_storage.png", "../img/white_user_manage.png");
//记录当前点击的一级标签索引
var cur_first_tab_index = -1;
//TODO:一级标签点击事件
function firstTabClick(index) {
	if(index != 3) {
		$("#user-manage-second-list").collapse("hide");
		$(".first-tab").eq(3).find("img").eq(1).css("transform", "rotate(0deg)");
	} else {
		$(".first-tab").eq(index).find("img").eq(1).css("transform", "rotate(180deg)");
	}
	closeSecondTab();
	$(".first-tab").eq(index).css("background-color", "rgb(104, 223, 240)");
	$(".first-tab").eq(index).find("img").eq(0).attr("src", firstTabWhiteIcon[index]);
	$(".first-tab").eq(index).css("color", "white");
	cur_first_tab_index = index;
	//还原其它一级标签
	for(var i = 0; i < 4; i++) {
		if(i != index) {
			$(".first-tab").eq(i).css("background-color", "transparent");
			$(".first-tab").eq(i).find("img").eq(0).attr("src", firstTabGrayIcon[i]);
			$(".first-tab").eq(i).css("color", "#aeb2b7");
		}
	}

	//一级标签显示表
	switch(index) {
		case 0:
			$(".content-div iframe").attr("src", "administrator_table/check_formula.html");
			break;
		case 1:
			$(".content-div iframe").attr("src", "administrator_table/feature_coefficient.html");
			break;
		case 2:
			$(".content-div iframe").attr("src", "administrator_table/industry_code_base.html");
			break;
		default:
			break;
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
var secondTabName = new Array(".info-change", ".right-set", ".new-user");
//记录当前点击的二级标签索引
var cur_second_tab_index = -1;
//当前点击的二级标签是否显示
var cur_second_tab_isShow = false;
//TODO:二级标签点击事件
function secondTabClick(index) {

	$(secondTabName[index]).css("color", "white");

	cur_second_tab_index = index;
	cur_second_tab_isShow = true;
	//将其它二级标签收起
	for(var i = 0; i < secondTabName.length; i++) {
		if(i != index) {
			$(secondTabName[i]).css("color", "#aeb2b7");
		}
	}
	//二级标签显示表
	switch(index) {
		case 0:
			$(".content-div iframe").attr("src", "administrator_table/info_change.html");
			break;
		case 1:
			$(".content-div iframe").attr("src", "administrator_table/right_set.html");
			break;
		case 2:
			$(".content-div iframe").attr("src", "administrator_table/new_user.html");
			break;
		default:
			break;
	}

}

//关闭所有的二级标签
function closeSecondTab() {
	for(var i = 0; i < secondTabName.length; i++) {
		$(secondTabName[i]).css("color", "#aeb2b7");
	}
}