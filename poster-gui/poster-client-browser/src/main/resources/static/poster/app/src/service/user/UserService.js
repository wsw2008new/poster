(function () {
    'use strict';

    angular.module('services')
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
            }
        };
    }

})();
