/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module("aplikacija", ['ngRoute', 'ngCookies', 'ngMaterial']);

app.config(['$routeProvider', '$httpProvider', '$mdThemingProvider',
    function ($routeProvider, $httpProvider, $mdThemingProvider) {
        $routeProvider.when('/main', {
            templateUrl: 'app/partials/main.html',
            controller: 'MainController'
        }).when('/about', {
            templateUrl: 'app/partials/about.html',
            controller: 'AboutController'
        }).when('/login', {
            templateUrl: 'app/partials/login.html',
            controller: 'LoginController'
        }).when('/profile', {
            templateUrl: 'app/partials/profile.html',
            controller: 'ProfileController'
        }).when('/rad/:thesisId', {
            templateUrl: 'app/partials/rad.html',
            controller: 'RadController'
        }).when('/user/:userId', {
            templateUrl: 'app/partials/user.html',
            controller: 'UserController'
        }).when('/editprofile', {
            templateUrl: 'app/partials/editprofile.html',
            controller: 'EditProfileController'
        }).when('/advanced_search', {
            templateUrl: 'app/partials/advanced_search.html',
            controller: 'AdvancedSearchController'
        }).otherwise({
            redirectTo: '/main'
        });

        $httpProvider.interceptors.push('rejectionInterceptor');

        $mdThemingProvider.theme('default')
                .primaryPalette('blue', {'default': '800'})
                .accentPalette('blue', {'default': '800'});
    }]);

app.run(['$rootScope', '$location', '$http', '$cookieStore', 'UserService', 'StudiesService', 'ThesisService', 'Base64', function ($rootScope, $location, $http, $cookieStore, UserService, StudiesService, ThesisService, Base64) {
        $rootScope.application = "guest";
        $rootScope.administrator = false;
        $rootScope.loggedIn = false;
        $rootScope.errorMessage = "";

        $rootScope.webApiPath = '/zavrsni_radovi/webapi/';
        //global search expression
        $rootScope.expression = "";

        StudiesService.getAllStudies(function (response) {
            $rootScope.studies = response;
        });

        //fetch 5 most viewed theses
        var numberOfTopItems = 5;
        var pageNumber = 1;
        ThesisService.getThesesPage(pageNumber, numberOfTopItems, null, [], null, null, null, 'viewCount', null, null, null, null, function (response) {
            $rootScope.topTheses = response.content;
        });

        // LOGOUT
        $rootScope.logout = function () {
            $rootScope.loggedIn = false;
            $rootScope.administrator = false;
            $http.defaults.headers.common['ticket'] = undefined;
            $cookieStore.remove('cash');
            $rootScope.user = null;
            $location.path('/main');
        }

        // STAYING LOGGED IN AFTER PAGE REFRESH
        if ($cookieStore.get('cash') !== undefined) {
            $http.defaults.headers.common['ticket'] = Base64
                    .decode($cookieStore.get('cash'));
            UserService.getUserInfo(function (user) {
                $rootScope.user = user;
                $rootScope.administrator = user.admin;
                $rootScope.loggedIn = true;
            });
        }
        $rootScope.$on('$locationChangeStart', function (event, next,
                current) {
            $rootScope.errorMessage = "";
            //workaround for hiding search box, courses and subjects
            //when displaying login/signup form. There is a better way probably.
            if ($location.path() === '/login') {
                $rootScope.logingIn = true;
            } else {
                $rootScope.logingIn = false;
            }
        });

        $rootScope.initialised = true;
    }]);