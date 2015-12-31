var pageNum;
	var mockPageNum;
$(function() {

	$(".testSea").blur(function() {
		$(this).removeClass("am-field-valid");
	});
	

	//加载系统信息
	gigold.pay.interFace.ajaxHandler({
		"url": "getAllSysInfo.do",
		"onSuccess": function(data) {
			if (data.rspCd == "00000") {
				var optionStr = '<option value="0" selected="selected">选择系统</option>';
				$.each(data.sysList, function(index, sysData) {
					optionStr += '<option value="' + sysData.id + '"">' + sysData.sysName + '</option>';
				});
				$("#ifSysId").html(optionStr);
			}
		}
	});

	$('#ifSysId').change(function() {
		var sysId = $(this).children('option:selected').val();
		var sendData = {
			"interFacePro": {
				"sysId": sysId
			}
		};
		//加载产品信息
		gigold.pay.interFace.ajaxHandler({
			"url": "getProInfoBySysId.do",
			"data": JSON.stringify(sendData),
			"onSuccess": function(data) {
				if (data.rspCd == "00000") {
					$("#ifProId").removeAttr("disabled");
					var optionStr = '<option value="0" selected="selected">选择产品</option>';
					$.each(data.proList, function(index, proData) {
						optionStr += '<option value="' + proData.id + '">' + proData.proName + '</option>';

					});
					$("#ifProId").html(optionStr);
				}
			}
		});

		getAjaxData({
			"pageNum": 1,
			"ifName": $("#ifName").val(),
			"ifSysId": sysId
		});

	});
	$('#ifProId').change(function() {
		var proId = $(this).children('option:selected').val();
		getAjaxData({
			"pageNum": 1,
			"ifName": $("#ifName").val(),
			"ifSysId": $("#ifSysId").val(),
			"ifProId": proId

		});
	});



	//禁止表单提交
	document.querySelector("#srearchFrom").onsubmit = function(e) {
		e.preventDefault();
	}
	window.onload = function() {
		var bHeight = $(document).height();
		var bWidth = $(document).width();
		$("#doc-modal-rely").css({
			"height": bHeight,
			"width": bWidth
		});
		$("#doc-modal-rely .am-modal-dialog").css({
			"height": bHeight,
			"width": bWidth
		});
	}
	window.onresize = function() {
		var bHeight = $(document).height();
		var bWidth = $(document).width();
		$("#doc-modal-rely").css({
			"height": bHeight,
			"width": bWidth
		});
		$("#doc-modal-rely .am-modal-dialog").css({
			"height": bHeight,
			"width": bWidth
		});
	}


	//点击添加数据依赖关系
	$(document).on("click",".addDepenBtn",function(){
		var codObj = {};
		var curEle = $(this).parent().parent();
		codObj.refMockId = $(curEle).find("td").eq(0).text();
		codObj.mockId = $("#mockDateShowBody").attr("data-id");
		gigold.pay.interFace.ajaxHandler({
			"url": "addmockrefer.do",
			"data": JSON.stringify(codObj),
			"onSuccess": function(data) {
				if (data.rspCd == "00000") {
					relyAjaxFn(codObj.mockId);
				}
			}
		});
	});
	//点击保存测试数据调用顺序
	$(document).on("click",".mockSaveBtn",function(){
		var curEle = $(this).parent().parent();
		var sendData = {};
		sendData.id = $(curEle).attr("data-id");
		sendData.ordNum = $(curEle).find(".orderRely").val();
		console.log(sendData)
		gigold.pay.interFace.ajaxHandler({
			"url": "updpatemockrefer.do",
			"data": JSON.stringify(sendData),
			"onSuccess": function(data) {
				if (data.rspCd == "00000") {
					alert("保存成功");
				}else{
					alert("保存失败");
				}
			}
		});
	});
	
	
		//点击删除测试数据
	$(document).on("click",".mockDelBtn",function(){
		var curEle = $(this).parent().parent();
		var sendData = {};
		sendData.id = $(curEle).attr("data-id");
		gigold.pay.interFace.ajaxHandler({
			"url": "deletemockrefer.do",
			"data": JSON.stringify(sendData),
			"onSuccess": function(data) {
				if (data.rspCd == "00000") {
					$(curEle).remove();
				}else{
					alert("删除失败")
				}
			}
		});
	});
	//添加依赖
	$(document).on("click", ".relyBtn", function() {
		
		//加载系统信息
		gigold.pay.interFace.ajaxHandler({
			"url": "getAllSysInfo.do",
			"onSuccess": function(data) {
				if (data.rspCd == "00000") {
					var optionStr = '<option value="0" selected="selected">选择系统</option>';
					$.each(data.sysList, function(index, sysData) {
						optionStr += '<option value="' + sysData.id + '"">' + sysData.sysName + '</option>';
					});
					$("#ifmockSysId").html(optionStr);
				}
			}
		});
		//加载列表信息
		ajaxMockData({
			"pageNum": 1
		});

		var morkId = $(this).parent().parent().find(".hideInp").attr("data-id");
		var opernames = $(this).parent().parent().find(".hideInp").attr("data-opername");
		var rspcds = $(this).parent().parent().find(".hideInp").attr("data-rspcd");
		$("#mockDateShowBody").attr("data-id",morkId);
		$(".tabName").html('<ol class="am-breadcrumb breadNav"> <li><a>'+opernames+'</a></li><li><a>'+rspcds+'</a></li><li><a>数据依赖关系表</a></li></ol>');
		relyAjaxFn(morkId);

		$("#doc-modal-rely").modal({
			relatedTarget: this,
			closeViaDimmer: false
		});
	});
	//搜索事件
	$("#search").click(function() {
		var postData = {
			"pageNum": 1,
			"ifName": $("#ifName").val(),
			"ifSysId": $("#ifSysId").val(),
			"ifProId": $("ifProId").val(),
			"ifName": $("#ifName").val()
		};
		getAjaxData(postData);
	});

	//回车搜索
	$("#ifName").focus(function() {
		document.onkeydown = function(event) {
			var e = event || window.event || arguments.callee.caller.arguments[0];
			if (e && e.keyCode == 13) {
				var postData = {
					"pageNum": 1,
					"ifName": $("#ifName").val(),
					"ifSysId": $("#ifSysId").val(),
					"ifProId": $("ifProId").val(),
					"ifName": $("#ifName").val()
				};
				getAjaxData(postData);
				return false;
			}
		};
	})

	//添加状态码模块
	$(".addBtn").on("click", function() {
		var sendData = {};
		sendData.ifId = $(this).attr("data-ifid");
		gigold.pay.interFace.ajaxHandler({
			"url": "autotest/getrspcdbyifid.do",
			"data": JSON.stringify(sendData),
			"onSuccess": function(data) {
				if (data.rspCd == "00000") {
					addCodMod(data);
				}
			}
		});
	});

	//点击修改按钮进入可编辑状态
	$(document).on("click", ".upDaBtn", function() {
		var $ele = $(this).parent().parent();
		$ele.find("pre").attr("contenteditable", "true")
		$ele.find("pre")[0].focus();
		$ele.find(".addRspBtn").removeClass("am-disabled");
	});

	//保存修改的测试用例数据
	$(document).on("click", ".addRspBtn", function() {

		var $ele = $(this).parent().parent();
		var sendData = {};
		sendData.id = $ele.find(".hideInp").attr("data-id");
		sendData.ifId = $(".addBtn").attr("data-ifId");
		sendData.rspCodeId = $ele.find("select").val();
		sendData.caseName = $ele.find("input[name=caseName]").val();
		var rspCode = $ele.find("option:selected").text();

		sendData.requestJson = $ele.find(".reqJson").html();
		sendData.responseJson = $ele.find(".rspJson").html();
		console.log(sendData);
		gigold.pay.interFace.ajaxHandler({
			"url": "autotest/updateifsysmock.do",
			"data": JSON.stringify(sendData),
			"onSuccess": function(data) {
				console.log(data);
				if (data.rspCd == "00000") {
					alert("保存成功");
					$ele.find(".hideInp").attr("data-id", data.ifSysMock.id);
					$ele.find(".addRspBtn").addClass("am-disabled");
					$ele.find(".rspCdP").find("select").remove();
					$ele.find(".rspCdP").find(".rspCd").html(data.ifSysMock.rspCode);
					$ele.find(".rspCdP").find(".rspCdDesc").html(data.ifSysMock.rspCodeDesc);
					$ele.find(".relyBtn").removeAttr("disabled");
				}
			}
		});


	});

	//删除返回码模块
	$(document).on("click", ".delBtn", function() {
		var $ele = $(this).parent().parent();
		var sendData = {};
		sendData.id = $ele.find(".hideInp").attr("data-id");
		gigold.pay.interFace.ajaxHandler({
			"url": "autotest/deleteifsysmockbyid.do",
			"data": JSON.stringify(sendData),
			"onSuccess": function(data) {
				if (data.rspCd == "00000") {
					$($ele).remove();
				}
			}
		});
	});

	//加载接口信息
	getAjaxData({
		"pageNum": 1
	});


	$(document).on("click", ".pageUl li", function() {
		var textNum = $(this).text();
		if ("第一页" == textNum) {
			textNum = 1;
		}
		if ("上一页" == textNum) {
			textNum = --pageNum;
		}
		if ("最末页" == textNum) {
			textNum = $(this).attr("data-pages");
		}
		if ("下一页" == textNum) {
			textNum = ++pageNum;
		}
		var lis = $(".pageUl li");
		lis.each(function(a, b) {
			if ($(b).find("a").html() == pageNum) {
				$(this).addClass("am-active");
				$(this).siblings().removeClass("am-active");
			}
		});
		getAjaxData({
			"pageNum": textNum,
			"ifName": $("#ifName").val(),
			"ifSysId": $("#ifSysId").val(),
			"ifProId": $("ifProId").val(),
			"ifName": $("#ifName").val()
		});

	});

	//点击编辑按钮渲染mork数据
	$(document).on("click", "#propDateShow a", function() {
		var sendData = {
			"ifId": $(this).attr("id")
		};
		$(".addBtn").attr("data-ifid", $(this).attr("id"));
		gigold.pay.interFace.ajaxHandler({
			"url": "autotest/getifsysmockbyifid.do",
			"data": JSON.stringify(sendData),
			"onSuccess": function(data) {
				if (data.rspCd == "00000") {
					var htmlStr = "";
					var size = data.interFaceInfo.mockList.length;
					$(".operName").html(data.interFaceInfo.ifName);
					$.each(data.interFaceInfo.mockList, function(index, mock) {
						htmlStr += '<div class="rspBox">';
						htmlStr += '<input type="hidden" class="hideInp" data-id="' + mock.id + '" data-ifId="' + mock.ifId + '" data-operName="' + data.interFaceInfo.ifName + '" data-rspCd="' + mock.rspCode + '" />';
						htmlStr += '<p><span class="rspCd">' + mock.rspCode + ':</span>';
						htmlStr += '<code class="rspCdDesc">' + mock.rspCodeDesc + '</code>';
						htmlStr +='<button class="am-btn am-radius relyBtn am-btn-xs am-btn-secondary">依赖</button></p><hr />';
						htmlStr += '<p><span >('+mock.id+')用例名称:<input name="caseName" value="'+mock.caseName+'"</span></p>';
						htmlStr += '<p><span >入参:</span><pre class="reqJson">' + mock.requestJson + '</pre></p>';
						htmlStr += '<p><span >出参:</span><pre class="rspJson">' + mock.responseJson + '</pre></p>';
						htmlStr += '<div class="bianjiBtn">';
						htmlStr += '<button type="button" class="am-btn am-btn-default upDaBtn">修改</button>';
						htmlStr += '<button type="button" class="am-btn am-btn-default addRspBtn am-disabled">保存</button>';
						htmlStr += '<button type="button" class="am-btn am-btn-danger delBtn">删除</button>';
						htmlStr += '</div></div>';
					});
					$(".modalCd").html(htmlStr);
				}
			}
		});
		$("#doc-modal").modal({
			relatedTarget: this,
			closeViaDimmer: false
		});

	});
});

function getAjaxData(sendData) {
	gigold.pay.interFace.ajaxHandler({
		"url": "autotest/getallifsys.do",
		"data": JSON.stringify(sendData),
		"onSuccess": function(data) {
			if (data.rspCd == "00000") {
				loadInterFaceInfoByPage(data.pageInfo);
			} else {
				alert(data.rspInf);
			}
		}
	});
}

function loadInterFaceInfoByPage(pageInfo) {
	var listStr = "";
	$.each(pageInfo.list,
		function(index, rowData) {
			listStr += '<tr data-id=' + rowData.id + '>' +
				'<td  width=60 class="am-primary">' + rowData.id + '</td>' +
				'<td class="textLeft">' + rowData.ifName + '</td>' +
				'<td class="textLeft">' + rowData.sysName +
				'</td>' + '<td class="textLeft">' + rowData.proName + '</td>' +
				'<td width=200>' + '<a id=' + rowData.id + '>编辑</a>' + '</td>' + '</tr>';
		});
	$("#propDateShowBody").html(listStr);

	var str = "";
	//添加页码
	var pagesStr = '';
	pageNum = parseInt(pageInfo.pageNum);
	var pages = parseInt(pageInfo.pages);
	var hasFirPage = pageInfo.isFirstPage ? "am-disabled" : "";
	pagesStr += '<li class="am-pagination-first ' + hasFirPage + '"><a>第一页</a></li>';
	var hasPrePage = pageInfo.hasPreviousPage ? "" : "am-disabled";
	pagesStr += '<li class="am-pagination-prev ' + hasPrePage + '"><a>上一页</a></li>';
	$.each(pageInfo.navigatepageNums, function(index, pageInfos) {
		//获取当前页样式
		if (pageInfo.pageNum == pageInfos) {
			pagesStr += '<li class="am-active"><a class="">' + pageInfos + '</a></li>';
		} else {
			pagesStr += '<li class=""><a class="">' + pageInfos + '</a></li>';
		}
	});
	var hasNexPage = pageInfo.hasNextPage ? "" : "am-disabled";
	pagesStr += '<li class="am-pagination-next ' + hasNexPage + '"><a class="">下一页</a></li>';
	var hasLastPage = pageInfo.isLastPage ? "am-disabled" : "";
	pagesStr += '<li class="am-pagination-last ' + hasLastPage + '" data-pages=' + pages + '><a class="">最末页</a></li>';
	$(".pageUl").html(pagesStr);

}
//渲染分页加载测试用例
function loadMockInfoByPage(pageInfo) {
	var listStr = "";
	$.each(pageInfo.list,
		function(index, rowData) {
			listStr += '<tr data-id=' + rowData.id + '>' 
			+'<td  width=60 class="">' + rowData.id + '</td>' 
			+'<td class="textLeft">' + rowData.caseName + '</td>' 
			+'<td class="textLeft">' + rowData.ifName + '</td>' 
			+'<td class="textLeft">' + rowData.rspCode +'</td>' 
			+ '<td class="textLeft">' + rowData.rspCodeDesc + '</td>' 
			+ '<td class="textLeft"><button type="button" data-ifid class="am-btn am-btn-success addDepenBtn">添加</button></td>' 
			+'</tr>';
		});
	$("#mockDateShowBody").html(listStr);

	var str = "";
	//添加页码
	var pagesStr = '';
	mockPageNum = parseInt(pageInfo.pageNum);
	var pages = parseInt(pageInfo.pages);

	//判断当前页是否为首页
	var hasFirPage = pageInfo.isFirstPage ? "am-disabled" : "";
	pagesStr += '<li class="am-pagination-first ' + hasFirPage + '"><a>第一页</a></li>';

	//判断当前页是否有上一页
	var hasPrePage = pageInfo.hasPreviousPage ? "" : "am-disabled";
	pagesStr += '<li class="am-pagination-prev ' + hasPrePage + '"><a>上一页</a></li>';


	//添加分页信息
	$.each(pageInfo.navigatepageNums, function(index, pageInfos) {
		//获取当前页样式
		if (pageInfo.pageNum == pageInfos) {
			pagesStr += '<li class="am-active"><a class="">' + pageInfos + '</a></li>';
		} else {
			pagesStr += '<li class=""><a class="">' + pageInfos + '</a></li>';
		}
	});
	//判断当前页是否有下一页
	var hasNexPage = pageInfo.hasNextPage ? "" : "am-disabled";
	pagesStr += '<li class="am-pagination-next ' + hasNexPage + '"><a class="">下一页</a></li>';

	//判断当前页是否为最后一页
	var hasLastPage = pageInfo.isLastPage ? "am-disabled" : "";
	pagesStr += '<li class="am-pagination-last ' + hasLastPage + '" data-pages=' + pages + '><a class="">最末页</a></li>';
	$(".pageMockUl").html(pagesStr);

}



function ajaxMockData(sendData) {

	gigold.pay.interFace.ajaxHandler({
		"url": "autotest/getmockbypage.do",
		"data": JSON.stringify(sendData),
		"onSuccess": function(data) {
			if (data.rspCd == "00000") {
				loadMockInfoByPage(data.pageInfo);
			}
		}
	});
}



$(document).on("click", ".pageMockUl li", function() {
	var textNum = $(this).text();
	
	if ("第一页" == textNum) {
		textNum = 1;
	}
	if ("上一页" == textNum) {
		textNum = --mockPageNum;
	}
	if ("最末页" == textNum) {
		textNum = $(this).attr("data-pages");
	}
	if ("下一页" == textNum) {
		textNum = ++mockPageNum;
	}
	var lis = $(".pageUl li");
	lis.each(function(a, b) {
		if ($(b).find("a").html() == mockPageNum) {
			$(this).addClass("am-active");
			$(this).siblings().removeClass("am-active");
		}
	});
	ajaxMockData({
		"pageNum": textNum,
		"ifName": $("#ifmockName").val(),
		"ifSysId": $("#ifmockSysId").val(),
		"ifProId": $("#ifmockProId").val(),
		"ifName": $("#ifmockName").val()
	});

});


$('#ifmockSysId').change(function() {
	var sysId = $(this).children('option:selected').val();
	var sendData = {
		"interFacePro": {
			"sysId": sysId
		}
	};
	//加载产品信息
	gigold.pay.interFace.ajaxHandler({
		"url": "getProInfoBySysId.do",
		"data": JSON.stringify(sendData),
		"onSuccess": function(data) {
			if (data.rspCd == "00000") {
				$("#ifProId").removeAttr("disabled");
				var optionStr = '<option value="0" selected="selected">选择产品</option>';
				$.each(data.proList, function(index, proData) {
					optionStr += '<option value="' + proData.id + '">' + proData.proName + '</option>';

				});
				$("#ifmockProId").html(optionStr);
			}
		}
	});

	ajaxMockData({
		"pageNum": 1,
		"ifName": $("#ifmockName").val(),
		"ifSysId": $("#ifmockSysId").val(),
		"ifProId": $("ifmockProId").val(),
		"ifName": $("#ifmockName").val()
	});

});
$('#ifmockProId').change(function() {
	var proId = $(this).children('option:selected').val();
	ajaxMockData({
		"pageNum": 1,
		"ifName": $("#ifmockName").val(),
		"ifSysId": $("#ifmockSysId").val(),
		"ifProId": proId,
		"ifName": $("#ifmockName").val()
	});
});

//搜索事件
$("#mocksearch").click(function() {
	ajaxMockData({
		"pageNum": 1,
		"ifName": $("#ifmockName").val(),
		"ifSysId": $("#ifmockSysId").val(),
		"ifProId": $("ifmockProId").val(),
		"ifName": $("#ifmockName").val()
	});
});

//回车搜索
$("#ifmockName").focus(function() {
	document.onkeydown = function(event) {
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if (e && e.keyCode == 13) {
			ajaxMockData({
				"pageNum": 1,
				"ifName": $("#ifmockName").val(),
				"ifSysId": $("#ifmockSysId").val(),
				"ifProId": $("ifmockProId").val(),
				"ifName": $("#ifmockName").val()
			});
			return false;
		}
	};
})


function relyAjaxFn(id){
	var sendData = {};
	sendData.mockId = id;
	gigold.pay.interFace.ajaxHandler({
		"url": "getreferList.do",
		"data": JSON.stringify(sendData),
		"onSuccess": function(data) {
			if (data.rspCd == "00000") {
					renderRely(data);
			}
		}
	});
}


//渲染依赖测试用例列表
function renderRely(data){
	console.log(data)
	var list = data.list;
	var listStr = "";
	for(var i=0;i<list.length;i++){
		 listStr +=  '<tr data-id='+list[i].id+'><td  class="">' + list[i].mockId + '</td>' 
			+'<td  class="">' + list[i].refMockId + '</td>' 
			+'<td class="textLeft">' + list[i].caseName + '</td>' 
			+'<td class="textLeft">' + list[i].ifName + '</td>' 
			+'<td class="textLeft">' + list[i].rspCode +'</td>' 
			+ '<td class="textLeft">' + list[i].rspCodeDesc + '</td>' 
			+ '<td class="textLeft"><input class="orderRely" value='+list[i].ordNum+'></td>'
			+ '<td class="textLeft"><button type="button" class="am-btn am-btn-success mockSaveBtn">保存</button>'
			+'<button type="button" class="am-btn am-btn-danger mockDelBtn">移除</button></td>'
			+'</tr>';
	}
		$("#mockDateShowTab").html(listStr);
}


//渲染添加的返回码模块	
function addCodMod(data) {
	var listss = data.list;
	var htmlStr = "";
	htmlStr += '<div class="rspBox">';
	htmlStr += '<input type="hidden" class="hideInp" data-id="" data-ifId="" />'
	htmlStr += '<p class="rspCdP"><select data-am-selected id="">';
	for (var i = 0; i < listss.length; i++) {
		htmlStr += '<option value=' + listss[i].id + '>' + listss[i].rspCode + '--- '+listss[i].rspCodeDesc+'</option>';
	}
	htmlStr += '</select>';
	htmlStr += '<span class="rspCd"></span>';
	htmlStr += '<code class="rspCdDesc"></code><button class="am-btn am-radius relyBtn am-btn-xs am-btn-secondary" disabled>依赖</button></p><hr />';
	htmlStr += '<p><span >用例名称:<input name="caseName" value=""</span></p>';
	htmlStr += '<p><span >入参:</span><pre class="reqJson" contenteditable="true"></pre></p>';
	htmlStr += '<p><span >出参:</span><pre class="rspJson" contenteditable="true"></pre></p>';
	htmlStr += '<div class="bianjiBtn">';
	htmlStr += '<button type="button" class="am-btn am-btn-default upDaBtn">修改</button>';
	htmlStr += '<button type="button" class="am-btn am-btn-default addRspBtn">保存</button>';
	htmlStr += '<button type="button" class="am-btn am-btn-danger delBtn">删除</button>';
	htmlStr += '</div></div>';
	var rspBoxs = $(document).find(".rspBox");
	$(rspBoxs[0]).before(htmlStr);
}