var app = angular.module('myApp', []);

/**
 * 实现分页
 */
app.controller('nm', function ($scope,$http) {

    //$http({
    //    method:'JSONP',
    //    url:'http://localhost:63342/angularjs-code/code/stumanager/fuwu.html'
    //}) .success(function (response) {
    //        $scope.Originaldata = response;
    //    });

    $http.get('http://localhost:63342/angularjs-code/code/stumanager/123.html')
        .success(function (response) {
            $scope.dataList = response;
        })
        .error(function(){
            console.log("没有请求成功");
        });


});
