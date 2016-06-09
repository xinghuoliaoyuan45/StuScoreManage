/**
 * Created by mxy on 2016/5/29.
 */
var app = angular.module('myApp', []);
app.controller('analyzestu', function ($scope,$http) {

    $scope.analyze=function(){      
        $http({       		
            url: "http://localhost:8080/StuScoreManage/analyze/analyzestu.action",
        	method: "post"              
            }
        )
        .error(function(data,status,headers,config){
			if(status==404){
				alert("excel解析完成,如果您严格按照表格规范则解析成功,反之，则没有")
			}
			else{
				alert("excel信息解析没有发送")
			}
		});
    }


});