myapp = angular.module("admin-app", ["ngRoute"]);

const domain = "/wagon"
const root_rest = "";

myapp.config(function ($routeProvider) {
    $routeProvider
        .when("/product", {
            templateUrl: `${domain}/admin/pages/product/product-home.html`,
            controller: "product-ctrl"
        })
        .when("/home", {
            templateUrl: `${domain}/admin/pages/home/home-main.html`,
            controller: "product-ctrl"
        })
        .otherwise({
            template: "<h1>NOT FOUND</h1>"
        });
})

function getRequestLink(url) {
    return `${domain}/${url}`;
}
