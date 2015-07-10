(function () {
    'use strict';

    angular.module('services')
        .service('postService', ['$q', '$resource', PostService])
        .config(function ($resourceProvider) {
            $resourceProvider.defaults.stripTrailingSlashes = false;
        });

    /**
     * Users DataService
     * Uses embedded, hard-coded data model; acts asynchronously to simulate
     * remote data service call(s).
     *
     * @returns {{loadAllPostsForUser:function, savePost:function}}
     * @constructor
     */
    function PostService($q, $resource) {
        return {
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
                            method: 'POST',
                            transformResponse: function (data) {
                                console.log(angular.fromJson(data).errorMessages)
                                return angular.fromJson(data).errorMessages
                            },
                            isArray: true
                        }
                    });
                return savedPost.query({text: text, userId: selectedUserId});
            }
        };
    }

})();
