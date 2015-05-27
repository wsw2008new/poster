(function () {
    angular.module('newPostButton', ['ngMaterial'])
        .controller('newPostButtonController', [
            '$log', '$scope', '$mdDialog',
            NewPostButtonController
        ]);
    function NewPostButtonController($log, $scope, $mdDialog) {
        var self = this;

        self.onButtonClick = onButtonClick;
        self.savePost=savePost;
        self.text="aaaaa";

        function onButtonClick(ev) {
            $mdDialog.show({
                controller: DialogController,
                templateUrl: 'src/users/view/addPostDialog.html',
                targetEvent: ev
            })
                .then(function (answer) {
                    $scope.alert = 'You said the information was "' + answer + '".';
                }, function () {
                    $scope.alert = 'You cancelled the dialog.';
                });
        }

        function savePost(){
            var savedPost = $resource('/poster/api/post/save/', {}, {
                'query': {
                    method: 'POST',
                    transformResponse: function (data) {
                        return angular.fromJson(data).posts
                    },
                    isArray: true
                }
            });
            var allPosts = AllPosts.query();
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