myapp = angular.module("admin-app", ["ngRoute"]);

const domain = "/wagon"
const root_rest = "";

myapp.config(function ($httpProvider, $routeProvider) {
    // $httpProvider.defaults.headers.common['Authorization'] = 'httpProvider'
    $routeProvider
        .when("/product", {
            templateUrl: `${domain}/admin/pages/product/product-home.html`,
            controller: "product-ctrl"
        })
        .when("/home", {
            templateUrl: `${domain}/admin/pages/home/home-main.html`,
            controller: "home-ctrl"
        })
        .when("/logout", {
            templateUrl: `${domain}/admin/pages/home/home-main.html`,
            controller: "logout-ctrl"
        })
        .otherwise({
            template: "<h1>NOT FOUND</h1>"
        });
})

myapp.controller("home-ctrl", function ($scope, $http) {

    $scope.user = {};

    $http.get('')
})


myapp.controller("logout-ctrl", function ($scope, $http) {
    window.location.href = `${domain}/logout`;

})
function getRequestLink(url) {
    return `${domain}/${url}`;
}
