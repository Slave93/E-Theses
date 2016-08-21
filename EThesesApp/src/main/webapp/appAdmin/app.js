
var app = angular.module("aplikacija", ['ngRoute', 'ngCookies', 'datatables']);

app.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
        $routeProvider.when('/main', {
            templateUrl: 'partials/home.html',
            controller: 'HomeController'

        }).when('/settings', {
            templateUrl: 'partials/settings.html',
            controller: 'SettingsController'

        }).when('/users', {
            templateUrl: 'partials/users.html',
            controller: 'UsersController'

        }).otherwise({
            redirectTo: '/main'
        })
        $httpProvider.interceptors.push('rejectionInterceptor');
    }]);

app.run(['$rootScope', '$location', '$http', '$cookieStore', 'UserService', 'Base64', function ($rootScope, $location, $http, $cookieStore, UserService, Base64) {
        $rootScope.application = "admin";
        $rootScope.errorMessage = "";
        $rootScope.webApiPath = '/e_theses/webapi/';
        // LOGOUT
        $rootScope.logout = function () {
            window.location = "../";
        }

        //GET USER AFTER FIRST REDIRECT FROM
        //GUEST PAGE AND
        //STAY LOGGED IN AFTER PAGE REFRESH
        if ($cookieStore.get('cash') !== undefined) {
            $http.defaults.headers.common['ticket'] = Base64
                    .decode($cookieStore.get('cash'));

            UserService.getUserInfo(function (user) {
                $rootScope.user = user;
                if (!user.admin) {
                    $rootScope.logout();
                }
            });
        } else {
            $rootScope.logout();
        }

        $rootScope.$on('$locationChangeStart', function (event, next,
                current) {
            $rootScope.errorMessage = "";
        });

        $rootScope.initialised = true;
    }]);