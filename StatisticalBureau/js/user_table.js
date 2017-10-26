//初始化
function init() {
	register_bt_event();
	register_drag_event();
}

//给底部按钮注册事件
function register_bt_event() {
	$(".find").click(function() {
		$(".download").css("visibility", "visible");
		$(".download").css("opacity", "1");
		$(".print").css("visibility", "visible");
		$(".print").css("opacity", "1");
		$(".body").css("visibility", "visible");
		$(".body").css("opacity", "1");
	});
}
var isDown = false; //图标是否向下
//TODO:给上下拉图片注册事件
function register_drag_event() {
	$("#drag").click(function() {
		var top = isDown == false ? "-160px" : "0px";
		var rotate = isDown == false ? "rotate(180deg)" : "rotate(0deg)";
		isDown = isDown == false ? true : false;
		$(".wrap").css("top", top);
		$("#drag").css("transform", rotate);
	});
}
var checkbox_name = new Array("税收", "旅游税收", "GDP", "旅游GDP");
var thead;
//自定义查询复选框点击事件
function custom_asure() {
	var checkbox = $(".target input");
	thead = new Array("行业代码", "行业指标");
	for(var i = 0; i < checkbox.length; i++) {
		if(checkbox.eq(i).is(":checked") == true) {
			thead[thead.length] = checkbox_name[i];
		}
	}

	var code = '<tr>';
	for(var i = 0; i < thead.length; i++) {
		code += '<th>' + thead[i] + '</th>';
	}
	code += '</tr> ';
	$(".table-div table thead tr").remove();
	$(".table-div table thead").append(code);

	code = '';
	code += '<tr>';

	for(var i = 0; i < 58; i++) {
		code += '<tr>';
		for(var j = 0; j < thead.length; j++) {
			code += '<td>' + "Test" + '</td>';
		}
		code += '</tr> ';
	}
	$(".table-div table tbody tr").remove();
	$(".table-div  table tbody").append(code);
	custom_resize();

}

function custom_resize() {
	var pro = 1 / thead.length;
	var _width = $('.table-div').width();
	for(var i = 0; i < thead.length; i++) {
		$('.table-div th').eq(i).width(_width * pro);
		$('.table-div td').eq(i).width(_width * pro);
	}
	$(".result-body").css("visibility", "visible");
}