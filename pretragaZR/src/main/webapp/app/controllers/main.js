
app.controller('MainController', ['$scope', '$rootScope', 'ThesisService', function ($scope, $rootScope, ThesisService) {

        $scope.initialNumberOfItemsPerLoad = 5;
        $scope.numberOfItemsPerLoad = 5;
        
        $scope.theses = [];
        $scope.pageNumber = 1;
        
        ThesisService.getThesesPage($scope.pageNumber, $scope.numberOfItemsPerLoad, null, null, null, null, null, null, null, function (response) {
            if (response.content.length === 0) {
                $scope.noResults = true;
            }
            $scope.theses = response.content;
            if($scope.theses.length ===0){
            	$scope.noResults = true;
            }
            $scope.totalPages = response.totalPages;
            $scope.pageNumber++;
        });

        $scope.loadMore = function () {
            ThesisService.getThesesPage($scope.pageNumber, $scope.numberOfItemsPerLoad, null, null, null, null, null, null, null, function (response) {
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
