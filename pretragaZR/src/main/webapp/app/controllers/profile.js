app.controller('ProfileController', ['$scope', '$rootScope', 'UserService', 'StudiesService', 'CourseService', function ($scope, $rootScope, UserService, StudiesService, CourseService) {

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

        UserService.getUserInfo(function (user) {

            $rootScope.user = user;
        });

    }
]);