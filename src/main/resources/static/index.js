(function () {
    angular
        .module('myApp', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'index.html',
                controller: 'myCtrl'
            })
    }

    function run($rootScope, $http, $localStorage) {

        //подшиваем токен авторизации ко всем запросам
        if($localStorage.webUser) {
            //перед тем как привязать токен к повторному запросу, необходимо проверить что токен актуальный(или же удалить данные из локалсториджа и убрать автоподшивку токена)
            try {
                let jwt = $localStorage.webUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime()/1000);
                if(currentTime > payload.exp) {
                    console.log("Token has expired!")
                    delete $localStorage.webUser;
                    $http.defaults.headers.common['Authorization'] = '';
                } else {
                    $http.defaults.headers.common['Authorization'] = 'Bearer ' + $localStorage.webUser.token;
                };
            }catch (e) {
            }
        };
    }

})();


angular.module('myApp').controller('myCtrl', function ($scope, $http, $localStorage, $rootScope, $location) {

    //авторизация
    $scope.tryToAuth = function () {
        $http.post('http://localhost:8081/app/login', $scope.user)
            .then(function successCallback(response) {
                console.log(response.data);
                if(response.data.token) {
                    //сразу подшиваем хедер авторизации с токеном
                    $http.defaults.headers.common['Authorization'] = 'Bearer ' + response.data.token;

                    $localStorage.webUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;

                    $location.path('/');
                };
            },function errorCallback(response) {
            });
    };

    $rootScope.isUserLoggedIn = function () {
        if($localStorage.webUser) {
            return true;
        }else {
            return false;
        }
    };
});
