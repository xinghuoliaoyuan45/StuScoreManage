/**
 * Created by mxy on 2016/6/9.
 */
var app = angular.module('myApp', []);
app.controller('outputstu', function ($scope,$http) {

    $scope.output=function(){
        $http({
                url: "http://localhost:8080/StuScoreManage/analyze/outputstu.action",
                method: "post"
            }
        )
            .error(function(data,status,headers,config){
                if(status==404){
                    alert("输出数据库中的stu信息到outputstu.xlsx成功");
                    alert("你可以检查outputstu.xlsx中的信息")
                }
                else{
                    alert("输出失败")
                }
            });
    }


});
