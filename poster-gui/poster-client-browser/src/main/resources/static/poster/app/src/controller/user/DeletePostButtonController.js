(function () {
    angular.module('controllers')
        .controller('deletePostButtonController', [
            'userService', 'postService', '$log', '$scope', '$timeout', '$controller', '$mdDialog',
            DeletePostButtonController
        ]);
    function DeletePostButtonController(userService, postService, $log, $scope, $timeout, $controller, $mdDialog) {
        var self = this;

        self.userSelectionController = $controller('userSelectionController');

        self.onButtonClick = onButtonClick;
        self.triggerUser = triggerUser;
        self.userService = userService;
        self.postService = postService;
        self.text = "";

        $scope.submitDeletion = function (id) {
            self.selectedUser = userService.getSelected();

            self.postService.deletePost(id);

            $mdDialog.hide();

            self.userSelectionController.refreshPostsForUser(self.selectedUser);
            $timeout(self.triggerUser, 0, false)

        };

        function triggerUser() {
            angular.element('#user' + self.selectedUser).trigger('click');
        }

        function onButtonClick(ev, post) {
            $mdDialog.show({
                templateUrl: 'src/view/user/deletePostDialog.html',
                targetEvent: ev,
                controller: function ($mdDialog) {
                    var vm = this;
                    vm.post = {};
                    vm.post = post;

                    $scope.hide = function () {
                        $mdDialog.hide();
                    };
                    $scope.cancel = function () {
                        $mdDialog.cancel();
                    };
                },
                controllerAs: 'modal'
            });
        }
    }
})();