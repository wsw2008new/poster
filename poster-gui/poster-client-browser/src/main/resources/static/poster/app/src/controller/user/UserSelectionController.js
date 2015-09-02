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

        self.userService = userService;
        self.selected = null;
        self.selectedPost=null;
        self.users = [];
        self.savePost = savePost;
        self.selectUser = selectUser;
        self.refreshPostsForUser=refreshPostsForUser;
        self.onButtonClick = onButtonClick;
        self.toggleList = toggleUsersList;

        // Load all registered users


        userService.loadAll().$promise.then(function (response) {
            self.userService.setSelected(self.selected);
            self.users = response;
            self.selected = self.users[0];
            self.userService.setSelected(self.selected.userId);
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
            self.userService.setSelected(user.userId);
            self.toggleList();

            self.currentPosts = postService.loadAllPostsForUser(user.userId);
        }

        function selectPost(post) {
            self.selectedPost = post;
            self.userService.setSelectedPost(post.id);
        }

        function refreshPostsForUser(user) {
            self.currentPosts = postService.loadAllPostsForUser(user);
        }

        function savePost() {
            postService.savePost(self.selected.userId);
        }

    }

})();
