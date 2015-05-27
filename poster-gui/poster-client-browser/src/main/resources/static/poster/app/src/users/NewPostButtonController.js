(function () {
    angular.module('newPostButton', ['ngMaterial'])
        .controller('newPostButtonController', [
            '$log',
            NewPostButtonController
        ]);
    function NewPostButtonController($log) {
        var self=this;
        self.onButtonClick = onButtonClick;

        function onButtonClick(){
            console.log("click");
        }
    }
})();