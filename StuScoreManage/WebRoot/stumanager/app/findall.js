var app = angular.module('myApp', []);


app.controller('nm',function ($scope,$http, $filter) {

    var Originaldata=[];
    $http.get('http://localhost:8080/StuScoreManage/find/stufindall.action')
        .success(function (response) {
             Originaldata = response;//保存元数据
             $scope.dataList = Originaldata;//必须在这写如果在外面写 就不会在$scope里面寻找dataList
        })
        .error(function(){
            console.log("没有请求成功");
        });

    //$scope.Originaldata =
    //    [{"id": 1, "java": 0, "linux": 0, "math": 0, "stuclass": null, "username": null}, {
    //        "id": 2,
    //        "java": 0,
    //        "linux": 0,
    //        "math": 0,
    //        "stuclass": "qw",
    //        "username": "mxy"
    //    }, {"id": 3, "java": 20, "linux": 0, "math": 90, "stuclass": null, "username": null}]
    //$scope.dataList = Originaldata; BUG在这
    //因为没有数据中没有平均成绩 平均成绩没法排序，要想排序需要自己写一个排序，
    // 或者给riginaldata数据或者datalist数据，加上这个属性
    //以后完善fuSort()函数
    $scope.fnSort = function (arg) {
        arguments.callee['fnSork' + arg] = !arguments.callee['fnSork' + arg];//点 一下 正 两下为反/
        $scope.dataList = $filter('orderBy')($scope.dataList, arg, arguments.callee['fnSork' + arg]);

    };
    $scope.fnFilter = function () {
        $scope.dataList = $filter('filter')(Originaldata, $scope.filterVal);
    };


});
