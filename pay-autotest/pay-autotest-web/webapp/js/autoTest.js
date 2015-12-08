$(function(){
	
	$(".testSea").blur(function(){
		$(this).removeClass("am-field-valid");
	});
	
	//加载系统信息
    gigold.pay.interFace.ajaxHandler({
			"url":"getAllSysInfo.do",
			"onSuccess":function(data){
				if (data.rspCd == "00000") {
					 var optionStr='<option value="0" selected>请选择系统</option>';
                    $.each(data.sysList, function(index, sysData) {
                        optionStr += '<option value="'+sysData.id+'"">'
                                + sysData.sysName + '</option>';
                    });
                    $("#ifSysId").html(optionStr);
                }
			}
		});
	//
    $('#ifSysId').change(function(){ 
		var sysId=	$(this).children('option:selected').val();
		var sendData={
				"interFacePro":{
					   "sysId":sysId
					}
				};
		//加载产品信息
		  gigold.pay.interFace.ajaxHandler({
				"url":"getProInfoBySysId.do",
				"data":JSON.stringify(sendData),
				"onSuccess":function(data){
					console.log("sssssss");
					console.log(data);
					if (data.rspCd == "00000") {
						$("#ifProId").removeAttr("disabled");  
						 var optionStr='<option value="0" selected>请选择产品</option>';
						$.each(data.proList, function(index, proData) {
	                        optionStr += '<option value="'+proData.id+'">'
	                                + proData.proName + '</option>';
	                       
	                    });
						 $("#ifProId").html(optionStr);
	              }
				}
			});
		  
		  getAjaxData({
				"pageNum":1,
				"ifName":$("#ifName").val(),
				"ifSysId":sysId
			});
		  
		});
	
    
    
  //搜索事件
	$("#search").click(function() {
		$('#srearchFrom').submit(function() {
			 var postData={
						"pageNum":1,
						"ifName":$("#ifName").val(),
						"ifSysId":$("#ifSysId").val(),
						"ifProId":$("ifProId").val(),
						"ifName":$("#ifName").val()
					};
			 getAjaxData(postData);

			return false;
		});
	});
    
	//加载接口信息
	getAjaxData({
		"pageNum":1
	});
	
	 $('#ifProId').change(function(){ 
		 var proId=	$(this).children('option:selected').val();
		 getAjaxData({
				"pageNum":1,
				"ifName":$("#ifName").val(),
				"ifSysId":$("#ifSysId").val(),
				"ifProId":proId
				
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
			textNum = pages;
		}
		if ("下一页" == textNum) {
			textNum = pageNum + 1;
		}

		getAjaxData({
			"pageNum":textNum,
			"ifName":$("#ifName").val(),
			"ifSysId":$("#ifSysId").val(),
			"ifProId":$("ifProId").val(),
			"ifName":$("#ifName").val()
		});

	});
	
	
   
});

function getAjaxData(sendData){
	gigold.pay.interFace.ajaxHandler({
		"url" : "autotest/getallifsys.do",
		"data" : JSON.stringify(sendData),
		"onSuccess" : function(data) {
			console.log(data);
			if (data.rspCd == "00000") {
				loadInterFaceInfoByPage(data.pageInfo);
			} else {
				alert(data.rspInf);
			}
		}
	});
}

function loadInterFaceInfoByPage(pageInfo){
	var listStr = "";
	$.each(pageInfo.list,
			function(index, rowData) {
				listStr += '<tr data-id=' + rowData.id + '>' +
				 '<td  width=60 class="am-primary">' + (index + 1) + '</td>' + 
				 '<td class="textLeft">' + rowData.ifName + '</td>' + 
				 '<td class="textLeft">' + rowData.sysName + 
				 '</td>' + '<td class="textLeft">' + rowData.proName + '</td>' + 
				 '<td width=200>' + '<a href="#" class="">编辑</a>' + '</td>' + '</tr>';
			});
		$("#propDateShowBody").html(listStr);
	
		//添加页码
		var pagesStr = '';
		pageNum = parseInt(pageInfo.pageNum);
		pages = parseInt(pageInfo.pages);
		if (pageInfo.isFirstPage || pages == 0) {
			pagesStr += '<li class="am-pagination-first am-disabled">';
		} else {
			pagesStr += '<li class="am-pagination-first">';
		}
		pagesStr += '<a class="">第一页</a></li>';
		if (pageInfo.hasPreviousPage) {
			pagesStr += '<li class="am-pagination-prev"><a class="">上一页</a></li>'
		} else {
			pagesStr += '<li class="am-pagination-prev am-disabled"><a class="">上一页</a></li>'
		}
		//添加分页信息
		$.each(pageInfo.navigatepageNums, function(index, pageInfo) {
			//添加页码
			if (pageInfo.pageNum == pageInfo) {
				pagesStr += '<li class="am-active"><a class="">' + pageInfo + '</a></li>';
			} else {
				pagesStr += '<li class=""><a class="">' + pageInfo + '</a></li>';
			}
		});
		if (pageInfo.hasNextPage) {
			pagesStr += '<li class="am-pagination-next "><a class="">下一页</a></li>'
		} else {
			pagesStr += '<li class="am-pagination-next am-disabled"><a class="">下一页</a></li>';
		}
		if (pageInfo.isLastPage) {
			pagesStr += '<li class="am-pagination-last am-disabled"><a class="">最末页</a></li>';
		} else {
			pagesStr += '<li class="am-pagination-last"><a class="">最末页</a></li>';
		}
		$(".pageUl").html(pagesStr);
	
}