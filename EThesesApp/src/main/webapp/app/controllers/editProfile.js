app.controller('EditProfileController', ['$scope', '$location', 'UserService', 'CourseService', function ($scope, $location, UserService, CourseService) {

        UserService.getUserInfo(function (user) {

            $scope.user = user;
            CourseService.getAllCourses(function (response) {
                $scope.courses = response;
            });

            $scope.id = user.id;
            $scope.firstName = user.firstName;
            $scope.lastName = user.lastName;

            if (user.course) {
                $scope.courseName = user.course.name;
            }
            $scope.biography = user.biography;
            $scope.interests = user.interests;
            $scope.transcript = user.studentsTranscript;

        });

        $scope.updateUser = function () {
            $scope.user.id = $scope.id;
            $scope.user.firstName = $scope.firstName;
            $scope.user.lastName = $scope.lastName;
            $scope.user.courseName = $scope.courseName;
            $scope.user.biography = $scope.biography;
            $scope.user.interests = $scope.interests;
            $scope.user.studentsTranscript = $scope.transcript;


            UserService.updateUser($scope.user.id, $scope.user.firstName, $scope.user.lastName, $scope.user.studentsTranscript, $scope.user.courseName, $scope.user.biography, $scope.user.interests, function (response) {
                $location.path('/profile');
            });

        };

    }]);