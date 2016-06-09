var app = angular.module('myApp', []);

app.controller('mm', function ($scope, $http, $filter) {
    $scope.dataList = [];
    var Originaldata = [];

    $http.get('http://localhost:8080/StuScoreManage/find/stufindall.action')
        .success(function (response) {
            Originaldata = response;//保存元数据
            $scope.dataList = Originaldata;//必须在这写如果在外面写 就不会在$scope里面寻找dataList
        })
        .error(function () {
            console.log("没有请求成功");
        });

    var findIndex = function (id) {
        var index = -1;
        angular.forEach($scope.dataList, function (item, key) {
            //item 是遍历的dataList 里面的某一个data，key是data在dataList的索引 angular 的foreach
            if (item.id == id) {
                index = key;
                return index;
            }
        });
        return index;
    };
    var fnupdata = function (data) {
        // var jsondata= jQuery.parseJSON(data);
        // var jsondata = JSON.stringify( data );
        // var jsondata=eval("("+data+")");
        ////在这里必须把 几个是 long 或者 double 类型的数据 加上引号 要不jackjson  是解析不出来的
        //data转换成适合的jsondata
        //
        // var jsondata = JSON.stringify( data );
        //console.log(jsondata);
       jsonconvert(data);
       // var jsondata = JSON.stringify(jsondata);
        console.log(data);//在这里测试一下提交的json是否正确
        $http({
                url: "http://localhost:8080/StuScoreManage/updata/upallstu.action",
                method: "post",
                data: data
            }
        )
            .error(function (data, status, headers, config) {
                if (status == 404) {
                    //alert("数据发送成功"); 不能这样写因为 总提交时候会调用多次这个方法产生大量的alert影响用户体验
                    console.log("数据发送成功");
                } else {
                    alert("数据发送失败");
                }
            });
    }
    $scope.reducelinux = function (id) {
        var index = findIndex(id);

        if (index !== -1) {
            if ($scope.dataList[index].linux > 0) {
                --$scope.dataList[index].linux;
            } else {
                alert('学生的linux成绩不能小于0');
            }
        }
    };
    $scope.reducemath = function (id) {
        var index = findIndex(id);
        if (index !== -1) {
            if ($scope.dataList[index].math > 0) {
                --$scope.dataList[index].math;
            } else {
                alert('学生的高数成绩不能小于0');
            }
        }
    };
    $scope.reducejava = function (id) {
        var index = findIndex(id);
        if (index !== -1) {
            if ($scope.dataList[index].java > 0) {
                --$scope.dataList[index].java;
            } else {
                alert('学生的java成绩不能小于0');
            }
        }
    };
    $scope.addlinux = function (id) {
        var index = findIndex(id);
        if (index !== -1) {
            ++$scope.dataList[index].linux;
        }
    };
    $scope.addmath = function (id) {
        var index = findIndex(id);
        if (index !== -1) {
            ++$scope.dataList[index].math;
        }
    };
    $scope.addjava = function (id) {
        var index = findIndex(id);
        if (index !== -1) {
            ++$scope.dataList[index].java;
        }
    };
    $scope.submit = function (id) {
        var index = findIndex(id);
        fnupdata($scope.dataList[index]);
    };
    $scope.submitall = function () {
        //fnupdata($scope.dataList);
        for(var i = 0; i < $scope.dataList.length; i++)
        {
            fnupdata($scope.dataList[i]);
        }
    };
    $scope.fnFilter = function () {
        $scope.dataList = $filter('filter')(Originaldata, $scope.filterVal);
    };
    var jsonconvert = function (data) {
        var jsonarray = data;
            for (var x in jsonarray) {
                //如果$scope.dataList直接用这种方式转换只会成功length=1
              //  console.log(x + "=" + jsonarray[x]);
                if (x=="id"){
                    jsonarray[x]=""+jsonarray[x]+""
                }
                if (x=="java"){
                    jsonarray[x]=""+jsonarray[x]+""
                }
                if (x=="math"){
                    jsonarray[x]=""+jsonarray[x]+""
                }
                if (x=="linux"){
                    jsonarray[x]=""+jsonarray[x]+""
                }
            }
        }
    });
