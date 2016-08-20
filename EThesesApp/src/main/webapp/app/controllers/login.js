
app.controller('LoginController', ['$scope', '$http', '$rootScope', '$location', '$cookieStore', 'AuthenticationService', 'UserService', 'CourseService', 'Base64', function ($scope, $http, $rootScope, $location, $cookieStore, AuthenticationService, UserService, CourseService, Base64) {
        if ($rootScope.loggedIn) {
            $location.path('/main');
        }

        $scope.login = function () {
            AuthenticationService.login($scope.email, $scope.password, function (response) {
                if (response.success) {
                    var ticket = response.ticket;
                    var encodedTicket = Base64.encode(ticket);
                    $http.defaults.headers.common['ticket'] = ticket;
                    $cookieStore.put('cash', encodedTicket);
                    
                    $rootScope.errorMessage = "";
                    
                    $rootScope.loggedIn = true;
                    UserService.getUserInfo(function (response) {
                        $rootScope.user = response;
                        $rootScope.administrator = response.admin;
                    });
                    CourseService.getAllCourses(function (response) {
                        $rootScope.courses = response;
                    });
                    $location.path('/main');
                } else {
                    $rootScope.errorMessage = response.message;
                    $scope.error = response.message;
                    $rootScope.loggedIn = false;
                    $location.path('/login');
                }
            });
        };

        $scope.register = function () {

            $rootScope.errorMessage = "";
            $scope.password = $scope.passwordReg;
            $scope.password2 = $scope.passwordRegRep;

            if ($scope.password !== $scope.password2) {
                $rootScope.errorMessage += " Unete lozinke se moraju poklapati.";
            }
            
            if ($rootScope.errorMessage.length === 0) {
                AuthenticationService.registerUser($scope.emailReg, $scope.passwordReg, function (response) {
                    if (response.success === true) {
                        $scope.email = $scope.emailReg;
                        $scope.login();
                        $scope.errorMessage = "";
                    }
                });
            }
        };
    }]);