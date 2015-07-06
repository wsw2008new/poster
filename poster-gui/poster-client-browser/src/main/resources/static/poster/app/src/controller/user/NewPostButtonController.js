(function () {
    angular.module('controllers')
        .controller('newPostButtonController', [
            '$log', '$scope', '$mdDialog',
            NewPostButtonController
        ]);
    function NewPostButtonController($log, $scope, $mdDialog) {
        var self = this;

        self.onButtonClick = onButtonClick;
        self.text = "";

        function onButtonClick(ev) {
            $mdDialog.show({
                controller: DialogController,
                templateUrl: 'src/view/user/addPostDialog.html',
                targetEvent: ev
            })
                .then(function (answer) {
                    $scope.alert = 'You said the information was "' + answer + '".';
                }, function () {
                    $scope.alert = 'You cancelled the dialog.';
                });
        }


    }

    function DialogController($scope, $mdDialog) {
        $scope.hide = function () {
            $mdDialog.hide();
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
        $scope.answer = function (answer) {
            $mdDialog.hide(answer);
        };
    }
})();