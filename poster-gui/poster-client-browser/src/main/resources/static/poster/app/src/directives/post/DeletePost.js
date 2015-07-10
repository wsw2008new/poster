(function () {
    'use strict';

    angular.module('directives').directive('showonhoverparent',
        function () {
            return {
                link: function (scope, element, attrs) {
                    element.parent().bind('mouseenter', function () {
                        element.show();
                    });
                    element.parent().bind('mouseleave', function () {
                        element.hide();
                    });
                }
            };
        });
})();