
app.controller('SettingsController', ['$scope', '$rootScope', 'CourseService', 'StudiesService', 'FieldOfStudyService', 'TagService', 'DTOptionsBuilder', function ($scope, $rootScope, CourseService, StudiesService, FieldOfStudyService, TagService, DTOptionsBuilder) {

        //INIT
        $scope.newStudyLevelName = "";
        $scope.newStudyLevelNameShort = "";

        $scope.newCourseName = "";
        $scope.newCourseNameShort = "";
        $scope.newCourseStudies = [];

        $scope.newFieldName = "";

        StudiesService.getAllStudies(function (studies) {
            $scope.studies = studies;
        });

        CourseService.getAllCourses(function (courses) {
            $scope.courses = courses;
        });

        FieldOfStudyService.getAllFields(function (fields) {
            $scope.fields = fields;
        });


        TagService.getAllTags(function (tags) {
            $scope.tags = tags;
        });

        $scope.dtOptions = DTOptionsBuilder.newOptions()
                .withPaginationType('full_numbers')
                .withDisplayLength(10);
        //END INIT

        //STUDIES OPERATIONS

        $scope.createStudyLevel = function () {
            StudiesService.createStudyLevel($scope.newStudyLevelName, $scope.newStudyLevelNameShort, function (response) {
                $scope.studies.push(response);
                $scope.newStudyLevelName = "";
                $scope.newStudyLevelNameShort = "";
                $rootScope.errorMessage = "";
            });
        };

        $scope.updateStudyLevel = function () {
            StudiesService.updateStudyLevel($scope.updateStudyLevelName, $scope.updateStudyLevelNameShort, $scope.updateStudyLevelId, function (response) {
                $scope.studies.splice(findIndexById($scope.studies, $scope.updateStudyLevelId), 1);
                $scope.studies.push(response);
                $rootScope.errorMessage = "";
            });
        };

        $scope.deleteStudyLevel = function (study) {
            if (confirm("Da li zelite da obrisete smer " + study.name + " ?")) {
                StudiesService.deleteStudyLevel(study.id, function (response) {
                    $scope.studies.splice(findIndexById($scope.studies, study.id), 1);
                });
            }
        }


        //COURSE OPERATIONS

        $scope.createCourse = function () {
            for (i = 0; i < $scope.studies.length; i++) {
                var item = $scope.studies[i];
                if (item.checked) {
                    $scope.newCourseStudies.push($scope.studies[i].id);
                    item.checked = false;
                }
            }
            CourseService.createCourse($scope.newCourseName, $scope.newCourseNameShort, $scope.newCourseStudies, function (response) {
                $scope.courses.push(response);
                $scope.newCourseName = "";
                $scope.newCourseNameShort = "";
                $scope.newCourseStudies = [];
                $rootScope.errorMessage = "";
            });
        };

        $scope.updateCourse = function () {
            for (i = 0; i < $scope.studies.length; i++) {
                var item = $scope.studies[i];
                if (item.checked) {
                    $scope.newCourseStudies.push($scope.studies[i].id);
                    item.checked = false;
                }
            }
            CourseService.updateCourse($scope.updateCourseName, $scope.updateCourseShortName, $scope.updateCourseId, $scope.newCourseStudies, function (response) {
                $scope.courses.splice(findIndexById($scope.courses, $scope.updateCourseId), 1);
                $scope.courses.push(response);
                $scope.newCourseStudies = [];
                $rootScope.errorMessage = "";
            });
        };

        $scope.deleteCourse = function (course) {
            if (confirm("Da li zelite da obrisete smer " + course.name + " ?")) {
                CourseService.deleteCourse(course.id, function (response) {
                    $scope.courses.splice(findIndexById($scope.courses, course.id), 1);
                });
            }
        }

//Fields OPERATIONS

        $scope.createField = function () {
            FieldOfStudyService.createField($scope.newFieldName, function (response) {
                $scope.fields.push(response);
                $scope.newFieldName = "";
            });
        };

        $scope.updateField = function () {
            FieldOfStudyService.updateField($scope.updateFieldId, $scope.updateFieldName, function (response) {
                $scope.fields.splice(findIndexById($scope.fields, $scope.updateFieldId), 1);
                $scope.fields.push(response);
                $scope.updateFieldId = "";
                $scope.updateFieldName = "";
            });
        };

        $scope.deleteField = function (field) {
            if (confirm("Da li zelite da obrisete oblast " + field.name + " ?")) {
                FieldOfStudyService.deleteField(field.id, function (response) {
                    $scope.fields.splice(findIndexById($scope.fields, field.id), 1);
                });
            }
        }

        //TAG OPERATIONS
        $scope.deleteTag = function (tag) {
            if (confirm("Da li zelite da obrisete tag #" + tag.value + " ?")) {
                TagService.deleteTag(tag.id, function (response) {
                    $scope.tags.splice(findIndexById($scope.courses, tag.id), 1);
                });
            }
        };

        //HELPER METHODS
        $scope.editFieldModalInit = function (id, name) {
            $scope.updateFieldId = id;
            $scope.updateFieldName = name;
        }
        $scope.editStudyLevelModalInit = function (study) {
            $scope.updateStudyLevelId = study.id;
            $scope.updateStudyLevelName = study.name;
            $scope.updateStudyLevelNameShort = study.nameShort;
        };


        $scope.editCourseModalInit = function (course) {
            for (i = 0; i < $scope.studies.length; i++) {
                for (j = 0; j < course.studies.length; j++) {
                    if ($scope.studies[i].id === course.studies[j].id) {
                        $scope.studies[i].checked = true;
                    }
                }
            }
            $scope.updateCourseId = course.id;
            $scope.updateCourseName = course.name;
            $scope.updateCourseShortName = course.nameShort;
        };


        /*$scope.getInfo = function (id) {
         index = findIndexById($scope.courses, id);
         var course = $scope.courses[index];
         $scope.courseInfoName = course.name;
         $scope.courseInfoNameShort = course.nameShort;
         }*/

        var findIndexById = function (collection, id) {
            for (i = 0; i < collection.length; i++) {
                if (collection[i].id === id) {
                    return i;
                }
            }
        };
    }]);