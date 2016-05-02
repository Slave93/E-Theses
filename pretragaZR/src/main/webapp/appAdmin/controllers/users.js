/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


app.controller('UsersController', ['$scope', '$rootScope', 'UserService', 'AuthenticationService', 'CourseService', 'StudiesService', 'DTOptionsBuilder', function ($scope, $rootScope, UserService, AuthenticationService, CourseService, StudiesService, DTOptionsBuilder) {





        StudiesService.getAllStudies(function (response) {
            $scope.studies = response;
            $scope.studiesDict = new Array();
            for (i = 0; i < $scope.studies.length; i++) {
                var item = $scope.studies[i];
                $scope.studiesDict[item.id] = item.name;
            }
        });

        CourseService.getAllCourses(function (response) {
            $scope.courses = response;
            $scope.coursesDict = new Array();
            for (i = 0; i < $scope.courses.length; i++) {
                var item = $scope.courses[i];
                $scope.coursesDict[item.id] = item;

            }
        });
        //INIT
        UserService.getAllUsers(function (users) {
            $scope.users = users;
        });

        $scope.dtOptions = DTOptionsBuilder.newOptions()
                .withPaginationType('full_numbers')
                .withDisplayLength(10);
        //END INIT

        $scope.newUser;
        //USER OPERATIONS
        $scope.updateUser = function () {


            if (!$scope.newUser.course) {
                $scope.newUserCourseName = "";
            } else {
                $scope.newUserCourseName = $scope.newUser.course.name;
            }
            UserService.updateUser($scope.newUser.id, $scope.newUser.firstName, $scope.newUser.lastName, $scope.newUser.studentsTranscript, $scope.newUserCourseName, $scope.newUser.biography, $scope.newUser.interests, function (response) {
                UserService.grantAdmin($scope.newUser.id, $scope.newUser.admin, function (response) {
                    $scope.users.splice(findIndexById($scope.users, $scope.newUser.id), 1);
                    $scope.users.push(response);
                });


                /*
                 $scope.newUserName = "";
                 $scope.newUserLastName = "";
                 $scope.newUserTranscript = "";
                 $scope.newUserBiography = "";
                 $scope.newUserInterests = "";
                 */
            });
        };

        $scope.deleteUser = function (user) {
            if (confirm("Da li zelite da obrisete korisnika " + user.email + " ?")) {
                UserService.deleteUser(user.id, function (response) {
                    $scope.users.splice(findIndexById($scope.users, user.id), 1);
                });
            }
        };

        $scope.createUser = function () {
            $rootScope.errorMessage = "";
            $scope.password = $scope.passwordReg;
            $scope.password2 = $scope.passwordRegRep;

            if ($scope.password !== $scope.password2) {
                $rootScope.errorMessage += " Unete lozinke se moraju poklapati.";
            }

            if ($rootScope.errorMessage.length === 0) {
                AuthenticationService.registerUser($scope.emailReg, $scope.passwordReg, function (response) {
                    $scope.users.push(response.user);
                    $scope.emailReg = "";
                    $scope.passwordReg = "";
                    $scope.passwordRegRep = "";
                });
            }
        }
        //HELPER METHODS
        $scope.editUserModalInit = function (user) {
            CourseService.getAllCourses(function (response) {
                $scope.courses = response;
            });
            $scope.newUser = user;
            /*$scope.newUserId = user.id;
             $scope.newUserName = user.firstName;
             $scope.newUserLastName = user.lastName;
             if (user.course) {
             $scope.newUserCourseName = user.course.name;
             }
             $scope.newUserTranscript = user.studentsTranscript;
             $scope.newUserBiography = user.biography;
             $scope.newUserInterests = user.interests;
             $scope.admin = user.admin;
             */
        }

        $scope.reinit = function () {
            $scope.infoUser = {};
        }

        $scope.getInfoModalInit = function (user) {

            $scope.infoUser = user;
            console.log($scope.infoUser.theses);
        };

        var findIndexById = function (collection, id) {
            for (i = 0; i < collection.length; i++) {
                if (collection[i].id === id) {
                    return i;
                }
            }
        };
    }]);