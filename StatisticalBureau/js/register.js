//初始化
function init() {
	register_event();
	area_ganged_spinner();
}

//给按钮注册事件
function register_event() {
	//注册按钮点击事件
	$(".login").click(function() {
		location.href="login.html";
		
	});
	
}

