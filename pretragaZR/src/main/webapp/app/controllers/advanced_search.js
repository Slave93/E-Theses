
app.controller('AdvancedSearchController', ['$scope', '$rootScope', '$routeParams', 'ThesisService', 'StudiesService', 'CourseService', 'TagService', "FieldOfStudyService", function ($scope, $rootScope, $routeParams, ThesisService, StudiesService, CourseService, TagService, FieldOfStudyService) {

        //init the lists
        StudiesService.getAllStudies(function (response) {
            $scope.studies = response;
        });
        CourseService.getAllCourses(function (response) {
            $scope.courses = response;
        });
        FieldOfStudyService.getAllFields(function (response) {
            $scope.fields = response;
            for (i = 0; i < $scope.fields.length; i++) {
                $scope.fields[i].checked = false;
            }
        });

        TagService.getAllTags(function (response) {
            $scope.tags = response;
            for (i = 0; i < $scope.tags.length; i++) {
                $scope.tags[i].checked = false;
            }
        });


        //flag to hide 'pretraga' and course list
        $rootScope.logingIn = true;

        $scope.numberOfItemsPerLoad = 3;
        $scope.pageNumber = 1;
        $scope.totalPages = -1;

        $scope.searchPerformed = false;

        $scope.searchStudyLevel = "";
        $scope.searchCourse = "";
        $scope.searchTags = [];

        $scope.searchKeyWord = "";
        $scope.searchFields = [];
        $scope.theses = [];
        $scope.newThesisDescription = "";
        $scope.descriptionMatchLimit = 1;

        if ($scope.tags) {
            for (i = 0; i < $scope.tags.length; i++) {
                $scope.tags[i].checked = false;
            }
        }
        if ($scope.fields) {
            for (i = 0; i < $scope.fields.length; i++) {
                $scope.fields[i].checked = false;
            }
        }

        if ($routeParams.expression) {
            $scope.expression = $routeParams.expression;
            ThesisService.getThesesPage(null, null, $scope.expression, [], null, null, null, null, null, null, null, null, function (response) {
                if (response.content.length === 0) {
                    $scope.noResults = true;
                }
                $scope.theses = response.content;
            });
            $rootScope.expression = "";
        }


        if ($routeParams.courseName) {
            $scope.searchCourseName = $routeParams.courseName;
            ThesisService.getThesesPage(null, null, null, [], null, $scope.searchCourseName, null, null, null, null, null, null, function (response) {
                if (response.content.length === 0) {
                    $scope.noResults = true;
                }
                $scope.theses = response.content;

            });
            $scope.searchCourseName = "";
        }

        if ($routeParams.field) {

            ThesisService.getThesesPage(null, null, null, null, null, null, null, null, [$routeParams.field], null, null, null, function (response) {
                if (response.content.length === 0) {
                    $scope.noResults = true;
                }
                $scope.theses = response.content;
            });
        }

        if ($routeParams.tag) {
            $scope.searchTag = [$routeParams.tag];
            ThesisService.getThesesPage(null, null, null, $scope.searchTag, null, null, null, null, null, null, null, null, function (response) {
                if (response.content.length === 0) {
                    $scope.noResults = true;
                }
                $scope.theses = response.content;
            });
            $scope.searchTag = [];
        }



//FUNCTIONS DEFINITION

        $scope.performSearch = function () {

            $scope.searchTags = [];
            $scope.theses = [];
            $scope.pageNumber = 1;


            $scope.searchFields = [];
            for (i = 0; i < $scope.fields.length; i++) {
                if ($scope.fields[i].checked) {
                    $scope.searchFields.push($scope.fields[i].name);
                }
            }



            $scope.newThesisDescription = $scope.newThesisDescription.trim();
            $scope.newThesisDescription = $scope.newThesisDescription.split(' ');
            $scope.newThesisDescriptionTrimmed = [];
            for (i = 0; i < $scope.newThesisDescription.length; i++) {
                $scope.newThesisDescription[i] = $scope.newThesisDescription[i].trim();
                if ($scope.newThesisDescription[i] !== "") {
                    $scope.newThesisDescriptionTrimmed.push($scope.newThesisDescription[i]);
                }
            }

            $scope.keywords = $scope.newThesisDescriptionTrimmed;
            if ($scope.newThesisDescription.length === 1 && $scope.newThesisDescription[0] === "") {
                $scope.keywords = [];
            }



            $scope.descriptionMatchLimit = Math.ceil(($scope.descriptionMatchLimit / 100) * $scope.keywords.length);


            if ($scope.tags) {
                for (i = 0; i < $scope.tags.length; i++) {
                    if ($scope.tags[i].checked === true) {
                        $scope.searchTags.push($scope.tags[i].value);
                    }
                }
            }
            ThesisService.getThesesPage($scope.pageNumber, $scope.numberOfItemsPerLoad, $scope.searchKeyWord, $scope.searchTags, null, $scope.searchCourse, $scope.searchStudyLevel, null, $scope.searchFields, null, $scope.keywords, $scope.descriptionMatchLimit, function (response) {
                if (response.content.length === 0) {
                    $scope.noResults = true;
                }
                $scope.theses = response.content;
                $scope.totalPages = response.totalPages;
                $scope.searchPerformed = true;
                $scope.pageNumber++;
            });

        };
        $scope.loadMore = function () {

            ThesisService.getThesesPage($scope.pageNumber, $scope.numberOfItemsPerLoad, $scope.searchKeyWord, $scope.searchTags, null, $scope.searchCourse, $scope.searchStudyLevel, null, $scope.searchFields, null, $scope.keywords, $scope.descriptionMatchLimit, function (response) {
                for (i = 0; i < response.content.length; i++) {
                    $scope.theses.push(response.content[i]);
                }
                $scope.pageNumber++;


            });
        };

        $scope.download = function (thesisId) {
            var path = '/zavrsni_radovi/webapi/theses/' + thesisId + '/download';
            window.open(path, '_blank', '');
        };


//END FUNCTION DEFINITION
//

    }]);

