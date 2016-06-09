/**
 * Created by mxy on 2016/6/2.
 */
var app = angular.module('myApp', []);
app.controller('deleteid', function ($scope,$http) {

	$scope.fndelete=function(stuid){
		$http({
			url: "http://localhost:8080/StuScoreManage/delete/deletebyid.action",
			method: "get",
			params: {
				stuid: stuid
			}
		}
		)
		.success(function(data,status,headers,config){
			alert("数据发送成功");
			//不可能出现success 因为页面上没有任何东西只是处理传递进去的参数

		})
		.error(function(data,status,headers,config){
			if(status==404){
				alert("数据发送成功")
			}
			else{
				alert("发送数据失败")
			}
		});
	}
	$scope.reset=function(){
		$scope.stuid=null;
	}

});
app.controller('deletename', function ($scope,$http) {

	$scope.fndelete=function(stuname){
		$http({
			url: "http://localhost:8080/StuScoreManage/delete/deletebyname.action",
			method: "get",
			params: {
				stuname: stuname
			}
		}
		)
		.success(function(data,status,headers,config){
			alert("数据发送成功");
		})
		.error(function(data,status,headers,config){			
			if(status==404){
				alert("数据发送成功")
			}
			else{
				alert("发送数据失败")
			}
		});
	}
	$scope.reset=function(){
		$scope.stuname=null;
	}
});
