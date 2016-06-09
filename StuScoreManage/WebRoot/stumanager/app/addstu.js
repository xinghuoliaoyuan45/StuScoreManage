/**
 * Created by mxy on 2016/5/29.
 */
var app = angular.module('myApp', []);
app.controller('addstu', function ($scope,$http) {

    $scope.fnadd=function(data){
        console.log(data);
        //data1=JSON.stringify(data);
        $http({
        		contentType:"application/json", 
                url: "http://localhost:8080/StuScoreManage/add/addstu.action",
                method: "post",
                data:data
            }
        )
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
        $scope.data=null;
    }

});