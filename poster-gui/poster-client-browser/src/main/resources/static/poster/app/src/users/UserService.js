(function () {
    'use strict';

    angular.module('users')
        .service('userService', ['$q', '$resource', UserService])
        .config(function ($resourceProvider) {
            $resourceProvider.defaults.stripTrailingSlashes = false;
        });

    /**
     * Users DataService
     * Uses embedded, hard-coded data model; acts asynchronously to simulate
     * remote data service call(s).
     *
     * @returns {{loadAll: Function}}
     * @constructor
     */
    function UserService($q, $resource) {
        var users = [{
            "userId": "2e8ba4c0-d149-4ab7-be64-983545c4432c",
            "userNickName": "qdqd",
            "userName": "dqqq",
            "errorMessages": []
        }, {
            "userId": "fad34e32-105e-4d5a-8ab6-5f1502140a17",
            "userNickName": "qd12123qd",
            "userName": "dqq313113q",
            "errorMessages": []
        }];

        // Promise-based API
        return {
            loadAll: function () {
                // Simulate async nature of real remote calls
                var AllUsers = $resource('/api/user/registered/all/', {});
                var allUsers = AllUsers.query();
                return $q.when(allUsers);
            }
        };
    }

})();
