$(function(){
	
	$("#csbtn").click(function(){
		var data = {
				"interFaceField":{
					"ifId":131,
					"fieldFlag":"1"
				}
		};
		gigold.pay.interFace.ajaxHandler({
			"url": contextPath+"getInterFaceFieldsJson.do",
			"data": JSON.stringify(data),
			"onSuccess": function(data) {
				console.log(data);
				if (data.rspCd == "00000") {
					//处理
				} else {
					alert("失败处理");
				}
			}
		});
		
	});
	
	
	
	
});