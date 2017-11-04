$(document).ready(function() {
	$("body").css("opacity", "1");
	init();
	getContent();
});

// TODO:表格自适应处理
function resize() {
	var _width = $('.table-div').width();
	$('.table-div th:first-child').width(_width * 0.15);
	$('.table-div td:first-child').width(_width * 0.15);
	$('.table-div th:nth-child(2)').width(_width * 0.1);
	$('.table-div td:nth-child(2)').width(_width * 0.1);
	$('.table-div th:nth-child(3)').width(_width * 0.15);
	$('.table-div td:nth-child(3)').width(_width * 0.15);
	$('.table-div th:nth-child(4)').width(_width * 0.20);
	$('.table-div td:nth-child(4)').width(_width * 0.20);
	$('.table-div th:nth-child(5)').width(_width * 0.15);
	$('.table-div td:nth-child(5)').width(_width * 0.15);
	$('.table-div th:nth-child(6)').width(_width * 0.15);
	$('.table-div td:nth-child(6)').width(_width * 0.15);
	$('.table-div th:nth-child(7)').width(_width * 0.05);
	$('.table-div td:nth-child(7)').width(_width * 0.05);
	$('.table-div th:nth-child(8)').width(_width * 0.05);
	$('.table-div td:nth-child(8)').width(_width * 0.05);
}

// TODO：获取表格内容
function getContent() {
	loading("正在加载...");
	var code = '';
	var url = '/Statistic/BaseQuery/gfCoefficientGet';
	var json = '';

	$.ajax({
		url : url,
		type : "post",
		dataType : "json",
		data : json,
		cache : false,
		async : true,
		contentType : "application/json; charset=utf-8",
		success : function(data) {
			if (data == "gf系数未上传") {
				failure("gf系数未上传");
				addData();
				return;
			}

			code += '<tr>';
			code += '<td contentEditable="true">' + data.ysday + '</td>';
			code += '<td contentEditable="true">' + data.avspend + '</td>';
			code += '<td contentEditable="true">' + data.spday + '</td>';
			code += '<td contentEditable="true">' + data.cpaspend + '</td>';
			code += '<td contentEditable="true">' + data.lipeople + '</td>';
			code += '<td contentEditable="true">' + data.tpsum + '</td>';
			code += '<td>' + data.gsta + '</td>';
			code += '<td>' + data.fsta + '</td>';
			code += '</tr> ';

			$(".body table tbody tr").remove();
			$(".body table tbody").append(code);
			// 加载成功
			loadSuccess();
			showSaveBt();
			showUploadBt();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			failure("无内容,请添加后上传");
			addData();
		}
	});

}

// TODO:上传
function upload() {
	loading("正在上传...");
	var parent = $(".table-div table tbody td");
	var yearAvergeSpendDay = parent.eq(0).text();
	var avergeSpend = parent.eq(1).text();
	var travelSpendday = parent.eq(2).text();
	var townAvergeSpend = parent.eq(3).text();
	var livePeople = parent.eq(4).text();
	var dayTravlePeople = parent.eq(5).text();
	var url = '/Statistic/FileUpload/gfCorfficientmath';
	var json = '{';
	json += '"yearAvergeSpendDay":" ' + yearAvergeSpendDay + '"';
	json += ',"avergeSpend":" ' + avergeSpend + '"';
	json += ',"travelSpendday":" ' + travelSpendday + '"';
	json += ',"townAvergeSpend":" ' + townAvergeSpend + '"';
	json += ',"livePeople":" ' + livePeople + '"';
	json += ',"dayTravlePeople":" ' + dayTravlePeople + '"';
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
			$(".tip img").attr("src", "");
			$(".tip p").text("上传成功");
			setTimeout(function() {
				closeTip();
			}, 3000);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			failure("上传失败");
		}
	});

}

// TODO:保存
function save() {
	loading("正在保存...");
	var parent = $(".table-div table tbody td");
	var Avspend = parent.eq(1).text();
	var Cpaspend = parent.eq(3).text();
	var Fsta = parent.eq(7).text();
	var Gsta = parent.eq(6).text();
	var Lipeople = parent.eq(4).text();
	var Spday = parent.eq(2).text();
	var Tpsum = parent.eq(5).text();
	var Ysday = parent.eq(0).text();
	var url = '/Statistic/ChangeToSave/gfCoefficient';
	var json = '{';
	json += '"Avspend":" ' + Avspend + '"';
	json += ',"Cpaspend":" ' + Cpaspend + '"';
	json += ',"Fsta":" ' + Fsta + '"';
	json += ',"Gsta":" ' + Gsta + '"';
	json += ',"Lipeople":" ' + Lipeople + '"';
	json += ',"Spday":" ' + Spday + '"';
	json += ',"Tpsum":" ' + Tpsum + '"';
	json += ',"Ysday":" ' + Ysday + '"';
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

// TODO:加载无内容,添加数据
function addData() {
	$(".table-div table").css("display", "block");
	$(".table-div table").css("display");
	$(".table-div table").css("opacity", "1");
	var code = '';
	code += '<tr>';
	code += '<td contentEditable="true">' + "" + '</td>';
	code += '<td contentEditable="true">' + "" + '</td>';
	code += '<td contentEditable="true">' + "" + '</td>';
	code += '<td contentEditable="true">' + "" + '</td>';
	code += '<td contentEditable="true">' + "" + '</td>';
	code += '<td contentEditable="true">' + "" + '</td>';
	code += '<td>' + "" + '</td>';
	code += '<td>' + "" + '</td>';
	code += '</tr> ';
	$(".body table tbody").append(code);
	resize();
	showUploadBt();
}