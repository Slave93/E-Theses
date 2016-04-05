/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


app.controller('HomeController', ['$scope', '$rootScope', 'UserService', 'CourseService', 'ThesisService', 'FieldOfStudyService', 'ThesisUploadService', 'DTOptionsBuilder', function ($scope, $rootScope, UserService, CourseService, ThesisService, FieldOfStudyService, ThesisUploadService, DTOptionsBuilder) {

//INIT

        $scope.newThesisName = "";
        $scope.newThesisDescription = "";
        $scope.newThesisDefenseDate = "";
        $scope.newThesisGrade = "";
        $scope.newThesisCourseName = "";
        $scope.newThesisTags = "";
        $scope.newThesisUserId = "";
        $scope.newThesisMentorId = "";
        $scope.newThesisFullUserName = "";
        $scope.newThesisUserEmail = "";
        $scope.newThesisFieldsText = "";
        $scope.newThesisFields = [];
        $scope.infoThesisId = "";
        $scope.infoThesisName = "";
        $scope.infoThesisDescription = "";
        $scope.infoThesisDefenseDate = "";
        $scope.infoThesisGrade = "";
        $scope.infoThesisCourseName = "";
        $scope.infoThesisUserEmail = "";
        $scope.infoThesisFirstName = "";
        $scope.infoThesisLastName = "";
        $scope.infoThesisUserId = "";
        $scope.infoThesisUserName = "";
        $scope.infoThesisMentorEmail = "";
        $scope.infoThesisMentorFirstName = "";
        $scope.infoThesisMentorLastName = "";
        $scope.infoThesisMentorId = "";
        $scope.infoThesisMentorName = "";
        $scope.dtOptions = DTOptionsBuilder.newOptions()
                .withPaginationType('full_numbers')
                .withDisplayLength(10);
        CourseService.getAllCourses(function (courses) {
            $scope.courses = courses;
        });
        FieldOfStudyService.getAllFields(function (response) {
            $scope.fields = response;
        });
        ThesisService.getAllTheses(function (response) {
            $scope.theses = response;
            console.log(response);
        });
        ThesisUploadService.getAllFiles(function (response) {
            $scope.files = response;
        });
        UserService.getAllUsers(function (users) {
            $scope.users = users;
            $scope.admins = [];
            for (i = 0; i < $scope.users.length; i++) {
                if ($scope.users[i].firstName === null || $scope.users[i].lastName === null || $scope.users[i].studentsTranscript === null) {
                    $scope.users[i].displayName = $scope.users[i].email;
                } else {
                    $scope.users[i].displayName = $scope.users[i].firstName + " " + $scope.users[i].lastName + " " + $scope.users[i].studentsTranscript;
                }
                if ($scope.users[i].admin) {
                    $scope.admins.push($scope.users[i]);
                }
            }
        });
        //END INIT

        //THESIS OPERATIONS  
        $scope.createThesis = function () {

            for (i = 0; i < $scope.fields.length; i++) {
                var item = $scope.fields[i];
                if (item.checked) {
                    $scope.newThesisFields.push($scope.fields[i].name);
                    item.checked = false;
                }
            }

            $scope.newThesisFieldsText = $scope.newThesisFieldsText.trim();

            $scope.newThesisFieldsText = $scope.newThesisFieldsText.split(' ');

            for (i = 0; i < $scope.newThesisFieldsText.length; i++) {
                $scope.newThesisFieldsText[i] = $scope.newThesisFieldsText[i].trim();
                if ($scope.newThesisFieldsText[i] !== "") {
                    $scope.newThesisFields.push($scope.newThesisFieldsText[i]);
                }
            }

            var fields = $scope.newThesisFields;
            if ($scope.newThesisTags.length === 1 && $scope.newThesisTags[0] === "") {
                fields = [];
            }

            var date = $scope.newThesisDefenseDate.split(".");
            if (date.length !== 3) {
                $scope.newThesisDefenseDate = Date().now;
            } else {
                $scope.newThesisDefenseDate = new Date(parseInt(date[2]), parseInt(date[1]), parseInt(date[0]));
            }
            $scope.newThesisTags = $scope.newThesisTags.trim();
            $scope.newThesisTags = $scope.newThesisTags.split(' ');
            $scope.newThesisTagsTrimmed = [];
            for (i = 0; i < $scope.newThesisTags.length; i++) {
                $scope.newThesisTags[i] = $scope.newThesisTags[i].trim();
                if ($scope.newThesisTags[i] !== "") {
                    $scope.newThesisTagsTrimmed.push($scope.newThesisTags[i]);
                }
            }

            var tags = $scope.newThesisTagsTrimmed;
            if ($scope.newThesisTags.length === 1 && $scope.newThesisTags[0] === "") {

                tags = [];
            }

            ThesisService.createThesis($scope.newThesisName, $scope.newThesisGrade, $scope.newThesisDefenseDate, $scope.newThesisDescription, $scope.newThesisCourseName, $scope.newThesisUserId, $scope.newThesisMentorId, tags, fields, $scope.newThesisFullUserName, $scope.newThesisUserEmail, function (response) {
                if ($scope.thesisFile !== undefined) {
                    $scope.updateThesisId = response.id;
                    uploadThesisFile(response.id);
                    $scope.thesisFile = undefined;
                }
                $scope.theses.push(response);
            });
            $scope.reinit();
        };
        $scope.updateThesis = function () {

            for (i = 0; i < $scope.fields.length; i++) {
                var item = $scope.fields[i];
                if (item.checked) {
                    $scope.newThesisFields.push($scope.fields[i].name);
                    item.checked = false;
                }
            }

            $scope.newThesisFieldsText = $scope.newThesisFieldsText.trim();

            $scope.newThesisFieldsText = $scope.newThesisFieldsText.split(' ');

            for (i = 0; i < $scope.newThesisFieldsText.length; i++) {
                $scope.newThesisFieldsText[i] = $scope.newThesisFieldsText[i].trim();
                if ($scope.newThesisFieldsText[i] !== "") {
                    $scope.newThesisFields.push($scope.newThesisFieldsText[i]);
                }
            }

            var fields = $scope.newThesisFields;
            if ($scope.newThesisTags.length === 1 && $scope.newThesisTags[0] === "") {
                fields = [];
            }
            var date = $scope.newThesisDefenseDate.split(".");
            if (date.length !== 3) {
                $scope.newThesisDefenseDate = Date().now;
            } else {
                $scope.newThesisDefenseDate = new Date(parseInt(date[2]), parseInt(date[1]), parseInt(date[0]));
            }
            $scope.newThesisTags = $scope.newThesisTags.trim();
            $scope.newThesisTags = $scope.newThesisTags.split(' ');
            $scope.newThesisTagsTrimmed = [];
            for (i = 0; i < $scope.newThesisTags.length; i++) {
                $scope.newThesisTags[i] = $scope.newThesisTags[i].trim();
                if ($scope.newThesisTags[i] !== "") {
                    $scope.newThesisTagsTrimmed.push($scope.newThesisTags[i]);
                }
            }

            var tags = $scope.newThesisTagsTrimmed;
            if ($scope.newThesisTags.length === 1 && $scope.newThesisTags[0] === "") {

                tags = [];
            }

            ThesisService.updateThesis($scope.updateThesisId, $scope.newThesisName, $scope.newThesisGrade, $scope.newThesisDefenseDate, $scope.newThesisDescription, $scope.newThesisCourseName, $scope.newThesisUserId, $scope.newThesisMentorId, tags, fields, $scope.newThesisFullUserName, $scope.newThesisUserEmail, function (response) {
                if ($scope.thesisFile !== undefined) {
                    uploadThesisFile(response.id);
                    $scope.thesisFile = undefined;
                }
                $scope.theses.splice(findIndexById($scope.theses, response.id), 1);
                $scope.theses.push(response);
            });
            $scope.reinit();
        };
        $scope.deleteThesis = function (thesis) {
            if (confirm("Da li zelite da obrisete rad " + thesis.name + " ?")) {
                ThesisService.deleteThesis(thesis.id, function (response) {
                    $scope.theses.splice(findIndexById($scope.theses, thesis.id), 1);
                });
            }
        };
        //FILE OPERATIONS
        var uploadThesisFile = function (thesisId) {
            var file = $scope.thesisFile;
            ThesisUploadService.uploadFile(thesisId, file, function (response) {
                var these = $scope.theses[findIndexById($scope.theses, $scope.updateThesisId)];
                these.file = response;
                $scope.files.push(response);
                $scope.theses.splice(findIndexById($scope.theses, $scope.updateThesisId), 1);
                $scope.theses.push(these);
                $rootScope.errorMessage = "";
            });
        };
        //downloads file by thesis id. 
        $scope.download = function () {
            var path = $rootScope.webApiPath + 'theses/' + $scope.infoThesisId + '/download';
            window.open(path, '_blank', '');
        };
        //downloads file by file id.
        $scope.downloadFile = function (fileId) {
            var path = $rootScope.webApiPath + 'theses/files/' + fileId + '/download';
            window.open(path, '_blank', '');
        };
        ///deletes file by file id
        $scope.deleteFile = function (fileId) {
            ThesisUploadService.deleteFile(fileId, function (response) {
                for (i = 0; i < $scope.theses.length; i++) {

                    if ($scope.theses[i].file && $scope.theses[i].file.id === fileId) {
                        var thesis = $scope.theses[i];
                        thesis.file = null;
                        $scope.theses.splice(i, 1);
                        $scope.theses.push(thesis);
                        $scope.files.splice(findIndexById($scope.files, fileId), 1);
                        return;
                    }
                }

            });
        };
        //deletes file associated with these
        $scope.deleteThesisFile = function () {
            ThesisUploadService.deleteFile($scope.updateThesisFileId, function (response) {
                $rootScope.errorMessage = "";
                var these = $scope.theses[findIndexById($scope.theses, $scope.updateThesisId)];
                these.file = null;
                $scope.theses.splice(findIndexById($scope.theses, $scope.updateThesisId), 1);
                $scope.theses.push(these);
                $scope.files.splice(findIndexById($scope.files, $scope.updateThesisFileId), 1);
            });
            $scope.reinit();
        };
        //HELPER METHODS
        $scope.reinit = function () {
            $scope.newThesisName = "";
            $scope.newThesisDescription = "";
            $scope.newThesisDefenseDate = "";
            $scope.newThesisGrade = "";
            $scope.newThesisCourseName = "";
            $scope.newThesisTags = "";
            $scope.newThesisUserId = "";
            $scope.newThesisMentorId = "";
            $scope.newThesisFullUserName = "";
            $scope.newThesisUserEmail = "";
            $scope.newThesisFields = [];
            $scope.newThesisFieldsText = "";
            $scope.infoThesisId = "";
            $scope.infoThesisName = "";
            $scope.infoThesisDescription = "";
            $scope.infoThesisDefenseDate = "";
            $scope.infoThesisGrade = "";
            $scope.infoThesisCourseName = "";
            $scope.infoThesisUserEmail = "";
            $scope.infoThesisFirstName = "";
            $scope.infoThesisLastName = "";
            $scope.infoThesisUserId = "";
            $scope.infoThesisUserName = "";
            $scope.infoThesisMentorEmail = "";
            $scope.infoThesisMentorFirstName = "";
            $scope.infoThesisMentorLastName = "";
            $scope.infoThesisMentorId = "";
            $scope.infoThesisMentorName = "";
            $rootScope.errorMessage = "";
        };
        $scope.editModalInit = function (thesis) {
            $scope.updateThesisId = thesis.id;
            $scope.newThesisName = thesis.name;
            $scope.newThesisDescription = thesis.description;
            $scope.updateThesisFile = thesis.file;
            $scope.newThesisFullUserName = thesis.userName;
            $scope.newThesisUserEmail = thesis.userEmail;
            $scope.newThesisFullUserName = thesis.userName;
            $scope.newThesisUserEmail = thesis.userEmail;
            if (thesis.file) {
                $scope.updateThesisFileId = thesis.file.id;
            }
            if (thesis.defenseDate) {
                $scope.newThesisDefenseDate = getFormattedDate(new Date(thesis.defenseDate));
            }

            $scope.newThesisGrade = "" + thesis.grade;
            if (thesis.course !== null && thesis.course.name !== null) {

                $scope.newThesisCourseName = thesis.course.name;
            }

            for (i = 0; i < thesis.tags.length; i++) {
                $scope.newThesisTags += " " + thesis.tags[i].value;
            }

            for (i = 0; i < $scope.fields.length; i++) {
                for (j = 0; j < thesis.fieldsOfStudy.length; j++) {
                    if ($scope.fields[i].name === thesis.fieldsOfStudy[j].name) {
                        $scope.fields[i].checked = true;
                    }
                }
            }

            if (thesis.user) {
                $scope.newThesisUserId = thesis.user.id;
                $scope.newThesisUserName = thesis.user.firstName + " " + thesis.user.lastName;
            }
            if (thesis.mentor) {
                $scope.newThesisMentorId = thesis.mentor.id;
                $scope.newThesisMentorName = thesis.mentor.firstName + " " + thesis.mentor.lastName;
            }

        }

        $scope.getInfoModalInit = function (thesis) {
            $scope.infoThesisId = thesis.id;
            $scope.infoThesisName = thesis.name;
            $scope.infoThesisDescription = thesis.description;
            $scope.infoThesisDefenseDate = thesis.defenseDate;
            $scope.infoThesisGrade = thesis.grade;
            $scope.infoThesisFile = thesis.file;
            if (thesis.course) {
                $scope.infoThesisCourseName = thesis.course.name;
            } else
                $scope.infoThesisCourseName = "";
            $scope.infoThesisTags = thesis.tags;
            $scope.infoThesisFields = thesis.fieldsOfStudy;
            if (thesis.user) {
                $scope.infoThesisUserEmail = thesis.user.email;
            } else {
                $scope.infoThesisUserEmail = thesis.userEmail;
            }
            if (thesis.user && $scope.user.firstName && $scope.user.lastName) {
                $scope.infoThesisFirstName = thesis.user.firstName;
                $scope.infoThesisLastName = thesis.user.lastName;
                $scope.infoThesisUserId = thesis.user.id;
                $scope.infoThesisUserName = thesis.user.firstName + " " + thesis.user.lastName;
            } else {
                $scope.infoThesisUserName = thesis.userName;
            }

            if (thesis.mentor) {
                $scope.infoThesisMentorEmail = thesis.mentor.email;
                $scope.infoThesisMentorFirstName = thesis.mentor.firstName;
                $scope.infoThesisMentorLastName = thesis.mentor.lastName;
                $scope.infoThesisMentorId = thesis.mentor.id;
                $scope.infoThesisMentorName = thesis.mentor.firstName + " " + thesis.mentor.lastName;
            }
        }

        function getFormattedDate(date) {
            var year = date.getFullYear();
            var month = (1 + date.getMonth()).toString();
            month = month.length > 1 ? month : '0' + month;
            var day = date.getDate().toString();
            day = day.length > 1 ? day : '0' + day;
            return day + '.' + month + '.' + year;
        }

        var findIndexById = function (collection, id) {
            for (i = 0; i < collection.length; i++) {
                if (collection[i].id === id) {
                    return i;
                }
            }
        }
    }]);