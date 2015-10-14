


$(function(){
	var userId = $.getUrlParam("id");
	//var userId = 7;
	perHandel(userId);
	
})


function perHandel(userId){
	
	var perReq = perRequest(userId);
	perReq.request();
	
	var perReqQq = perRequestQq(userId);
	perReqQq.requestQq();
	
	var perReqXy = perRequestXy(userId);
	perReqXy.requestXy();
	
}

//获取接口基本信息
function perRequest(userId){
	var data = {
			"interFaceInfo":{
				"id":userId
			}
			
	};
	console.log(addParam(data));
	data.request=function (perData){
		$.ajax({
			type:"post",
			url:"queryInterFaceById.do",
			dataType:'json',
			contentType : "application/json",
			async:"false",
			data:addParam(data),
			success:function(result){
				var perData = perRander(result);
				perData.rander();
			},
			error:function(e){
				console.log(e);
			}
		});
		
	}
	return data;
}
//渲染接口基本信息
function perRander(result){
	console.log(result);
	var backData = {};
	backData = result;
	
	backData.rander = function(){
		$(".propName").html(this.interFaceInfo.ifName);
		$(".propDesc").html(this.interFaceInfo.ifDesc);
		$(".propUrl").html(this.interFaceInfo.ifUrl);
		$(".propSystem").html(this.interFaceInfo.interFaceSysTem.sysName);
		$(".propProduct").html(this.interFaceInfo.interFacePro.proName);
		$(".propHttp").html(this.interFaceInfo.ifProtocol);
		$(".propRequest").html(this.interFaceInfo.ifType);
		$(".userName").html(this.interFaceInfo.userInfo.userName);
	}
	return backData;
}


//获取接口请求信息
function perRequestQq(userId){
	
	var data = {
			"interFaceField":{
				"ifId":userId,
				"fieldFlag":"1"
			}
	};
	
	data.requestQq=function (perData){
		
		$.ajax({
			type:"post",
			url:"getInterFaceFieldsJson.do",
			dataType:'json',
			contentType : "application/json",
			async:"false",
			data:addParam(data),
			success:function(result){
				var perData = perRanderQq(result);
				perData.rander();
				
			},
			error:function(e){
				console.log(e);
			}
		});
	}
	return data;
}

function perRanderQq(result){
	var backData = {};
	backData = result;
	backData.rander = function(){
		$(".reqPre").html(this.jsonStr);
	}
	return backData;
}



function perRequestXy(userId){
	var data = {
			"interFaceField":{
				"ifId":userId,
				"fieldFlag":"2"
			}
	};
	
	
	data.requestXy=function (perData){
		
		$.ajax({
			type:"post",
			url:"getInterFaceFieldsJson.do",
			dataType:'json',
			contentType : "application/json",
			async:"false",
			data:addParam(data),
			success:function(result){
				var perData = perRanderXy(result);
				perData.rander();
				
			},
			error:function(e){
				console.log(e);
			}
		});
	}
	return data;
}

function perRanderXy(result){
	var backData = {};
	backData = result;
	backData.rander = function(){
		$(".response").html(this.jsonStr);
	}
	return backData;
}







