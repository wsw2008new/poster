(function () {
    angular.module('controllers')
        .controller('newPostButtonController', [
            'userService', 'postService', '$log', '$scope', '$timeout', '$controller', '$mdDialog',
            NewPostButtonController
        ]);
    function NewPostButtonController(userService, postService, $log, $scope, $timeout, $controller, $mdDialog) {
        var self = this;

        self.userSelectionController = $controller('userSelectionController');

        self.onButtonClick = onButtonClick;
        self.triggerUser = triggerUser;
        self.userService = userService;
        self.postService = postService;
        self.text = "";

        $scope.submitPost = function (text) {
            self.selectedUser = userService.getSelected();
            var postSaveResultErrors = self.postService.savePost(self.selectedUser, text);
            if (postSaveResultErrors.length == 0) {

                $mdDialog.hide();

                self.userSelectionController.refreshPostsForUser(self.selectedUser);
                $timeout(self.triggerUser, 0, false)
            }

        };

        function triggerUser() {
            angular.element('#user' + self.selectedUser).trigger('click');
        }

        function onButtonClick(ev) {
            $mdDialog.show({
                templateUrl: 'src/view/user/addPostDialog.html',
                targetEvent: ev
            });
        }
    }
})();