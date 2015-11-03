var pageNum = 1;
	var pages = 0;
	var pageSize = 20;
	function handlerData(data) {
		var listStr = "";
		$.each(data.pageInfo.list,
						function(index, rowData) {
							listStr += '<tr data-id=' + rowData.id + '>'
									+ '<td  width=60 class="am-primary">'
									+ (index + 1)
									+ '</td>'
									+ '<td class="textLeft">'
									+ rowData.ifName
									+ '</td>'
									+ '<td class="textLeft">'
									+ rowData.sysName
									+ '</td>'
									+ '<td class="textLeft">'
									+ rowData.proName
									+ '</td>'
									+ '<td class="textLeft">'
									+ rowData.designName
									+ '</td>'
									+ '<td class="textLeft">'
									+ rowData.ifDesc
									+ '</td>'
									+ '<td width=200>'
									+ '<a href="interFaceDetail.html?id='
									+ rowData.id+"&userName="+$.getUrlParam("userName")
									+ '" class="">查看详情</a>&nbsp;&nbsp;'
									+ '<a href="interFaceUpdate.html?id='
									+ rowData.id+"&userName="+$.getUrlParam("userName")
									+ '" class="">修改</a>&nbsp;&nbsp;'
									+ '<a href="#" id="' + rowData.id + '" class="deleteBtn">删除</a>'
									+ '</td>' + '</tr>';
						});
		$("#propDateShowBody").html(listStr);
		//添加页码
		var pagesStr = '';
		pageNum = parseInt(data.pageInfo.pageNum);
		pages = parseInt(data.pageInfo.pages);
		if (data.pageInfo.isFirstPage || pages == 0) {
			pagesStr += '<li class="am-pagination-first am-disabled">';
		} else {
			pagesStr += '<li class="am-pagination-first">';
		}
		pagesStr += '<a class="">第一页</a></li>';
		if (data.pageInfo.hasPreviousPage) {
			pagesStr += '<li class="am-pagination-prev"><a class="">上一页</a></li>'
		} else {
			pagesStr += '<li class="am-pagination-prev am-disabled"><a class="">上一页</a></li>'
		}
		//添加分页信息
		$.each(data.pageInfo.navigatepageNums, function(index, pageInfo) {
			//添加页码
			if (data.pageInfo.pageNum == pageInfo) {
				pagesStr += '<li class="am-active"><a class="">' + pageInfo
						+ '</a></li>';
			} else {
				pagesStr += '<li class=""><a class="">' + pageInfo
						+ '</a></li>';
			}
		});
		if (data.pageInfo.hasNextPage) {
			pagesStr += '<li class="am-pagination-next "><a class="">下一页</a></li>'
		} else {
			pagesStr += '<li class="am-pagination-next am-disabled"><a class="">下一页</a></li>';
		}
		if (data.pageInfo.isLastPage) {
			pagesStr += '<li class="am-pagination-last am-disabled"><a class="">最末页</a></li>';
		} else {
			pagesStr += '<li class="am-pagination-last"><a class="">最末页</a></li>';
		}
		$(".pageUl").html(pagesStr);
	}

	function loadInterFacePage(params) {
		params.data.pageSize = pageSize;
		gigold.pay.interFace.ajaxHandler({
	        "url" : params.url,
	        "data" : JSON.stringify(params.data),
	        "onSuccess" : function(data) {
	        	if (data.rspCd == "00000") {
                    handlerData(data);
                } else {
                    alert("失败处理");
                }
	        }
	    });
	}

	$(function() {
		
		$(".loggxnxi").html("登录用户：<span class='colorBlue'>" + $.getUrlParam("userName") + " </span>");
	    // 查询接口（首页）,用户信息
	    $(".QueryInterface").on("click",function(){
	        window.location.href="main.html?userName="+$.getUrlParam("userName");
	    });
	    //添加接口，用户信息
	    $(".AddInterface").on("click",function(){
	        window.location.href="interFaceBaseInfo.html?userName="+$.getUrlParam("userName");
	    });
	    //修改页面，用户信息
	    $(".ModifyInterface").on("click",function(){
	        window.location.href="interFaceUpDate.html?userName="+$.getUrlParam("userName");
	    });
	    //接口详情，用户信息
	    $(".InterfaceDetails").on("click",function(){
	        window.location.href="interFaceDetail.html?userName="+$.getUrlParam("userName");
	    });
	    
	    var ifName = $("#ifName").val();
		var url = "getInterFaceByPage.do";
		var sendData = {
			"pageInfo" : {
				"pageNum" : $("#pageNum").val(),
				"pageSize" : pageSize
			}
		};
		if (ifName != null&&ifName!='') {
			url = "queryByCondition.do";
			sendData = {
				"pageInfo" : {
					"pageNum" : $("#pageNum").val(),
					"pageSize" : pageSize
				},
				"interFaceInfo" : {
					"ifName" : $("#ifName").val()
				}
			}
		};
	    
		 loadInterFacePage({
			url : url,
			data : sendData
		}); 
		 
		 
		$("#search").click(function() {
			console.log("hhehe");
			var paramData = {
				"pageInfo" : {
					"pageNum" : 1,
					"pageSize" : pageSize
				},
				"interFaceInfo" : {
					"ifName": $("#ifName").val()
				}
			};
			
			
			 $('#srearchFrom').submit(function() {
				 gigold.pay.interFace.ajaxHandler({
		                "url" : "queryByCondition.do",
		                "data" : JSON.stringify(paramData),
		                "onSuccess" : function(data) {
		                	console.log(data);
		                    if (data.rspCd == "00000") {
		                        handlerData(data);
		                    } else {
		                        alert("失败处理");
		                    }
		                }
		            });
	                return false;
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
			var ifName = $("#ifName").val();
			var url = "getInterFaceByPage.do";
			var sendData = {
				"pageInfo" : {
					"pageNum" : textNum,
					"pageSize" : pageSize
				}
			};
			if (ifName != null&&ifName!='') {
				url = "queryByCondition.do";
				sendData = {
					"pageInfo" : {
						"pageNum" : textNum,
						"pageSize" : pageSize
					},
					"interFaceInfo" : {
						"ifName" : $("#ifName").val()
					}
				}
			};
			console.log(url);
			gigold.pay.interFace.ajaxHandler({
	            "url" : url,
	            "data" : JSON.stringify(sendData),
	            "onSuccess" : function(data) {
	            	console.log(data);
	                if (data.rspCd == "00000") {
	                    handlerData(data);
	                } else {
	                    alert("失败处理");
	                }
	            }
	        });
			
		});
		$("#propDateShow")
				.on(
						"click",
						".deleteBtn",
						function() {
							$('#my-confirm')
									.modal(
											{
												relatedTarget : this,
												onConfirm : function(options) {
													var deleteId = $(
															this.relatedTarget)
															.parent().parent()
															.attr("data-id");
													var sendData = {
														"interFaceInfo" : {
															"id" : deleteId
														}
													}
													$
															.ajax({
																type : "POST",
																url : "deleteInterFaceById.do",
																dataType : "json",
																contentType : "application/json",
																data : JSON
																		.stringify(sendData),
																async : false,
																error : function(
																		request) {
																	alert("Connection error");
																},
																success : function(
																		data) {
																	if (data.rspCd == "00000") {
																		alert("删除成功");
																		self.location.href = "main.html";
																	} else {
																		alert("失败处理");
																	}
																}
															});
												}
											});
						});
	});