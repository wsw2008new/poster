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
                var AllUsers = $resource('/poster/api/user/registered/all/', {}, {
                    'query': {
                        method: 'GET',
                        transformResponse: function (data) {
                            return angular.fromJson(data).users
                        },
                        isArray: true
                    }
                });
                return AllUsers.query();
            },

            loadAllPostsForUser: function (id) {
                // Simulate async nature of real remote calls
                var AllPosts = $resource('/poster/api/post/get/user/' + id, {}, {
                    'query': {
                        method: 'GET',
                        transformResponse: function (data) {
                            return angular.fromJson(data).posts
                        },
                        isArray: true
                    }
                });
                return AllPosts.query();
            },

            savePost: function (selectedUserId, text) {
                var savedPost = $resource('/poster/api/post/save/', {},
                    {
                        'query': {
                            method: 'POST'
                        }
                    });
                savedPost.query({text: text, userId: selectedUserId});
            }
        };
    }

})();
