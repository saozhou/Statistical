//初始化
function init() {
	$("body").css("opacity", "1");
	register_drag_event();
}
//显示内容体
function showBody() {
	$(".body").css("visibility", "visible");
	$(".body").css("opacity", "1");
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

//TODO:加载失败
function loadFailure() {
	window.clearInterval(rotate);
	$(".tip img").attr("src", "../../img/load_failure.png");
	$(".tip img").css("transform", "rotate(0deg)");
	$(".tip p").text("加载失败");
}

//TODO:关闭提示信息
function closeTip() {
	window.clearInterval(rotate);
	$(".tip").css("display", "none");
}

//TODO：加载成功显示表格
function loadSuccess() {
	closeTip();
	$(".table-div table").css("display", "block");
	$(".table-div table").css("display");
	$(".table-div table").css("opacity", "1");
	$(".footer").css("opacity", "1");
	$(".download").css("visibility", "visible");
	$(".download").css("opacity", "1");
	$(".print").css("visibility", "visible");
	$(".print").css("opacity", "1");
	resize();
}

//TODO:上传失败
function uploadSuccess() {
	window.clearInterval(rotate);
	$(".tip img").attr("src", "../../img/load_failure.png");
	$(".tip img").css("transform", "rotate(0deg)");
	$(".tip p").text("上传失败");
}

var rotate;

function rotateLoading() {
	var deg = 30;
	var count = 0;
	rotate = setInterval(function() {
		$(".tip img").css("transform", "rotate(" + count * deg + "deg)");
		count++;
		if(count == 360 / deg)
			count = 1;
	}, 100);
}