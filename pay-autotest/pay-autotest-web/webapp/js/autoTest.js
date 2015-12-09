$(function() {

	$(".testSea").blur(function() {
		$(this).removeClass("am-field-valid");
	});
	
	
	
	//编辑按钮功能
	$(document).on("click", "#propDateShow a", function() {
		
		
		
		
		
		$("#doc-modal").modal({
			relatedTarget: this,
			closeViaDimmer:false
		});
		
	});


	//加载系统信息
	gigold.pay.interFace.ajaxHandler({
		"url": "getAllSysInfo.do",
		"onSuccess": function(data) {
			if (data.rspCd == "00000") {
				var optionStr = '<option value="0" selected>请选择系统</option>';
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
					var optionStr = '<option value="0" selected>请选择产品</option>';
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

	//禁止表单提交
	document.querySelector("#srearchFrom").onsubmit = function(e) {
		e.preventDefault();
	}

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
	document.onkeydown=function(event){   
		 var e = event || window.event || arguments.callee.caller.arguments[0];
         if(e && e.keyCode==13){ // enter 键
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



	//加载接口信息
	getAjaxData({
		"pageNum": 1
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

	$(document).on("click", ".pageUl li", function() {
		var textNum = $(this).text();
		if ("第一页" == textNum) {
			textNum = 1;
		}
		if ("上一页" == textNum) {
			textNum = pageNum - 1;
		}
		if ("最末页" == textNum) {
			textNum = $(this).attr("data-pages");
		}
		if ("下一页" == textNum) {
			textNum = pageNum + 1- 0;
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
				'<td  width=60 class="am-primary">' + (index + 1) + '</td>' +
				'<td class="textLeft">' + rowData.ifName + '</td>' +
				'<td class="textLeft">' + rowData.sysName +
				'</td>' + '<td class="textLeft">' + rowData.proName + '</td>' +
				'<td width=200>' + '<a data-id='+rowData.id+'>编辑</a>' + '</td>' + '</tr>';
		});
	$("#propDateShowBody").html(listStr);	

		var str = "";
		var pages = pageInfo.pages;
		//添加页码
		var pagesStr = '';
		pageNum = parseInt(pageInfo.pageNum);
		pages = parseInt(pageInfo.pages);
		
		//判断当前页是否为首页
		var hasFirPage = pageInfo.isFirstPage?"am-disabled":"";
		pagesStr += '<li class="am-pagination-first '+hasFirPage+'"><a>第一页</a></li>';
		
		//判断当前页是否有上一页
		var hasPrePage = pageInfo.hasPreviousPage?"":"am-disabled";
		pagesStr += '<li class="am-pagination-prev '+hasPrePage+'"><a>上一页</a></li>';
		
		
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
		var hasNexPage = pageInfo.hasNextPage?"":"am-disabled";
		pagesStr += '<li class="am-pagination-next '+hasNexPage+'"><a class="">下一页</a></li>';
		
		//判断当前页是否为最后一页
		var hasLastPage = pageInfo.isLastPage?"am-disabled":"";
		pagesStr += '<li class="am-pagination-last '+hasLastPage+'" data-pages=' + pages + '><a class="">最末页</a></li>';
		$(".pageUl").html(pagesStr);

}