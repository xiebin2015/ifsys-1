$(function() {

	$(".testSea").blur(function() {
		$(this).removeClass("am-field-valid");
	});
	
	//加载系统信息
	gigold.pay.interFace.ajaxHandler({
		"url": "getAllSysInfo.do",
		"onSuccess": function(data) {
			if (data.rspCd == "00000") {
				var optionStr = '<option value="0" selected="selected">请选择系统</option>';
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
					var optionStr = '<option value="0" selected="selected">请选择产品</option>';
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
	$("#ifName").focus(function(){
		document.onkeydown=function(event){   
			 var e = event || window.event || arguments.callee.caller.arguments[0];
	         if(e && e.keyCode==13){ 
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


	//点击修改按钮进入可编辑状态
	$(document).on("click",".upDaBtn",function(){
		var $ele = $(this).parent().parent();
		$ele.find("pre").attr("contenteditable","true")
		$ele.find("pre")[0].focus(); 
		$ele.find(".addRspBtn").removeClass("am-disabled");
	});

	//保存修改的返回码数据
	$(document).on("click",".addRspBtn",function(){
		var $ele = $(this).parent().parent();
		var sendData = {};
		sendData.id = $ele.find(".hideInp").attr("data-id");
		sendData.ifId = $ele.find(".hideInp").attr("data-ifId");
		sendData.requestJson = $ele.find(".reqJson").html();
		sendData.responseJson = $ele.find(".rspJson").html();
		
		gigold.pay.interFace.ajaxHandler({
				"url":"autotest/updateifsysmock.do",
				"data":JSON.stringify(sendData),
				"onSuccess":function(data){
					if (data.rspCd == "00000") {
						alert("保存成功");
						$ele.find(".addRspBtn").addClass("am-disabled");
	              }
				}
			});
			
	});
	
	//删除返回码模块
	$(document).on("click",".delBtn",function(){
		var $ele = $(this).parent().parent();
		var sendData = {};
		sendData.id = $ele.find(".hideInp").attr("data-id");
		gigold.pay.interFace.ajaxHandler({
				"url":"autotest/deleteifsysmockbyid.do",
				"data":JSON.stringify(sendData),
				"onSuccess":function(data){
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
			textNum = pageNum--;
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
	$(document).on("click", "#propDateShow a", function() {
		var sendData={"ifId":$(this).attr("id")};
		  gigold.pay.interFace.ajaxHandler({
				"url":"autotest/getifsysmockbyifid.do",
				"data":JSON.stringify(sendData),
				"onSuccess":function(data){
					if (data.rspCd == "00000") {
						var htmlStr="";
						var size=data.interFaceInfo.mockList.length;
						$.each(data.interFaceInfo.mockList,function(index,mock){
							htmlStr+='<div class="rspBox">';
							htmlStr+='<input type="hidden" class="hideInp" data-id="'+mock.id+'" data-ifId="'+mock.ifId+'" />';
							htmlStr+='<p><span class="rspCd">'+mock.rspCode+':</span>';
							htmlStr+='<code class="rspCdDesc">'+mock.rspCodeDesc+'</code></p><hr />';
							htmlStr+='<p><span >入参:</span><pre class="reqJson">'+mock.requestJson+'</pre></p>';
							htmlStr+='<p><span >出参:</span><pre class="rspJson">'+mock.responseJson+'</pre></p>';
							htmlStr+='<div class="bianjiBtn">';
							htmlStr+='<button type="button" class="am-btn am-btn-default upDaBtn">修改</button>';
							htmlStr+='<button type="button" class="am-btn am-btn-default addRspBtn am-disabled">保存</button>';
							htmlStr+='<button type="button" class="am-btn am-btn-danger delBtn">删除</button>';
							htmlStr+='</div></div>';
						});
						$(".am-modal-bd").html(htmlStr);
	              }
				}
			});
		$("#doc-modal").modal({
			relatedTarget: this,
			closeViaDimmer:false
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
				'<td width=200>' + '<a id='+rowData.id+'>编辑</a>' + '</td>' + '</tr>';
		});
	$("#propDateShowBody").html(listStr);	

		var str = "";
		//添加页码
		var pagesStr = '';
		pageNum = parseInt(pageInfo.pageNum);
		var pages = parseInt(pageInfo.pages);
		
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