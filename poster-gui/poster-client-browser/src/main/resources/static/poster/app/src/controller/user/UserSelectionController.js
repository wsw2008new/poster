(function () {

    angular
        .module('controllers')
        .controller('userSelectionController', [
            'userService', 'postService', '$mdSidenav', '$mdBottomSheet', '$log', '$q',
            UserSelectionController
        ]);

    /**
     * Main Controller for the Angular Material Starter App
     * @param $scope
     * @param $mdSidenav
     * @param avatarsService
     * @constructor
     */
    function UserSelectionController(userService, postService, $mdSidenav, $mdBottomSheet, $log, $q) {
        var self = this;

        self.selected = null;
        self.users = [];
        self.savePost = savePost;
        self.selectUser = selectUser;
        self.onButtonClick = onButtonClick;
        self.toggleList = toggleUsersList;

        // Load all registered users


        userService.loadAll().$promise.then(function(response){
            self.users =response;
            self.selected = self.users[0];
            self.currentPosts = postService.loadAllPostsForUser(self.selected.userId);
        });

        // *********************************
        // Internal methods
        // *********************************

        /**
         * First hide the bottomsheet IF visible, then
         * hide or Show the 'left' sideNav area
         */
        function toggleUsersList() {
            var pending = $mdBottomSheet.hide() || $q.when(true);

            pending.then(function () {
                $mdSidenav('left').toggle();
            });
        }

        function onButtonClick() {
            console.log("click");
        }

        /**
         * Select the current avatars
         * @param menuId
         */
        function selectUser(user) {
            self.selected = user;
            self.toggleList();

            self.currentPosts=postService.loadAllPostsForUser(user.userId);
        }

        function savePost() {
            postService.savePost(self.selected.userId);
        }

    }

})();
