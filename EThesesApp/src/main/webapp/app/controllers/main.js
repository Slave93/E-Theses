
app.controller('MainController', ['$scope', '$rootScope', 'ThesisService','StudiesService', function ($scope, $rootScope, ThesisService,StudiesService) {

        $scope.initialNumberOfItemsPerLoad = 5;
        $scope.numberOfItemsPerLoad = 5;

        $scope.theses = [];
        $scope.pageNumber = 1;
        
        StudiesService.getAllStudies(function (response) {
            $scope.studies = response;
            $scope.studiesDict = new Array();
            for (i = 0; i < $scope.studies.length; i++) {
                var item = $scope.studies[i];
                $scope.studiesDict[item.id] = item.name;
            }
        });

        ThesisService.getThesesPage($scope.pageNumber, $scope.numberOfItemsPerLoad, null, null, null, null, null, null, null, null, null, null, function (response) {
            if (response.content.length === 0) {
                $scope.noResults = true;
            }
            $scope.theses = response.content;
            if ($scope.theses.length === 0) {
                $scope.noResults = true;
            }
            $scope.totalPages = response.totalPages;
            $scope.pageNumber++;
        });

        $scope.loadMore = function () {
            ThesisService.getThesesPage($scope.pageNumber, $scope.numberOfItemsPerLoad, null, null, null, null, null, null, null, null, null, null, function (response) {
                for (i = 0; i < response.content.length; i++) {
                    $scope.theses.push(response.content[i]);
                }
                $scope.pageNumber++;
            });
        };
        $scope.download = function (thesisId) {
            var path = $rootScope.webApiPath + 'theses/' + thesisId + '/download';
            window.open(path, '_blank', '');
        };
    }]);
