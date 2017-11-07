//TODO:关闭提示信息
function closeTip() {
	window.clearInterval(rotate);
	$(".tip").css("opacity", "0");
	$(".tip").css("display");
	$(".tip").css("display", "none");
}

// TODO：加载成功显示表格
function loadSuccess() {
	closeTip();
	$(".table-div").css("visibility", "visible");
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

// TODO:上传失败
function uploadSuccess() {
}
// TODO:显示提示信息
function showTip() {
	window.clearInterval(rotate);
	$(".tip").css("opacity", "1");
	$(".tip").css("display");
	$(".tip").css("display", "block");
}

var rotate;

function rotateLoading() {
	if (rotate != null)
		window.clearInterval(rotate);
}
function loading(str) {
	showTip();
	$(".tip img").attr("src", "../../img/loading.png");
	$(".tip p").text(str);

	var deg = 30;
	var count = 0;
	rotate = setInterval(function() {
		$(".tip img").css("transform", "rotate(" + count * deg + "deg)");
		count++;
		if (count == 360 / deg)
			count = 1;
	}, 100);
}

// TODO：加载成功
function loadSuccess() {
	$(".tip img").attr("src", "");
	$(".tip p").text("加载成功");
	setTimeout(function() {
		closeTip();
	}, 3000);
	$(".body").css("display", "block");
	$(".table-div").css("visibility", "visible");
	$(".table-div table").css("display", "block");
	$(".table-div table").css("display");
	$(".table-div table").css("opacity", "1");
	showDownloadBt();
	showUploadBt();
	resize();
}
// TODO:失败
function failure(str) {
	window.clearInterval(rotate);
	$(".tip img").attr("src", "../../img/failure.png");
	$(".tip img").css("transform", "rotate(0deg)");
	$(".tip p").text(str);
}
// TODO:上传成功
function uploadSuccess() {
	closeTip();
}
// TODO:上传失败
function uploadFailure() {
	window.clearInterval(rotate);
	$(".tip img").attr("src", "../../img/failure.png");
	$(".tip img").css("transform", "rotate(0deg)");
	$(".tip p").text("上传失败");
}

// TODO:显示上传按钮
function showUploadBt() {
	$(".upload").css("display", "initial");
}

// TODO:显示下载按钮
function showDownloadBt() {
	$(".download").css("display", "initial");
}

// TODO:显示打印按钮
function showPrintBt() {
	$(".print").css("display", "initial");
}

// TODO:隐藏打印按钮
function hiddenPrintBt() {
	$(".print").css("display", "none");
}