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
                templateUrl: 'src/view/user/addPostDialog.html',
                targetEvent: ev
            });
        }


    }
})();