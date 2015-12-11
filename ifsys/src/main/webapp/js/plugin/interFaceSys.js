var gigold = gigold || {};
gigold.pay = gigold.pay || {};
gigold.pay.interFace = gigold.pay.interFace || {};

gigold.pay.interFace.ajaxHandler = function(options) {
	var defaults = {
		"type" : "POST",
		"url" : "",
		"dataType" : "json",
		"contentType" : "application/json",
		//"data" : null,
		"async" : false,
		"onError" : function(request) {
			alert("Connection error");
		},
		"onSuccess" : function(data) {
			//
		}
	};
	var defaults = $.extend({}, defaults, options);
	$.ajax({
		type : defaults.type,
		url : defaults.url,
		dataType : defaults.dataType,
		contentType : defaults.contentType,
		data : defaults.data,
		async : defaults.async,
		error : function(request) {
			defaults.onError(request);
		},
		success : function(data) {
			defaults.onSuccess(data)
		}
	});
}


/**
 * 利用 window.localStorage 实现本地缓存管理 可以用来页面之间传参
 */
var cacheService= (function(){
	return {
		getProterties:function(key){
			return window.localStorage[key];
			
		},
		setProterties:function(key,value){
			window.localStorage.key=value;
		}
	}
})()
	
	
	


