app.controller('UserController', ['$scope', '$routeParams', 'StudiesService', 'UserService', 'CourseService', function ($scope, $routeParams, StudiesService, UserService, CourseService) {


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

        $scope.userId = $routeParams.userId;
        UserService.getUserById($scope.userId, function (user) {
            $scope.user = user;

        });

    }]);