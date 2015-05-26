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
        return {
            loadAll: function () {
                // Simulate async nature of real remote calls
                var AllUsers = $resource('/api/user/registered/all/', {});
                var allUsers = AllUsers.query();
                return $q.when(allUsers);
            },

            loadAllPostsForUser: function (id) {
                // Simulate async nature of real remote calls
                var AllPosts = $resource('/api/post/get/user/'+id, {}, {'query': {method: 'GET', isArray: false}});
                var allPosts = AllPosts.query();
                return $q.when(allPosts);
            }
        };
    }

})();
