

const options = {
    year: "numeric",
    month: "numeric",
    day: "numeric",
    hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: false
};
myapp.controller("product-ctrl", function ($scope, $http) {

    $(document).ready(function () {
        // $.noConflict();

        $('#pTable').DataTable();

    });
    $scope.title = "PRODUCT PAGE"
    $scope.resources = getRequestLink("admin/pages/product");
    $scope.rest = {
        root: getRequestLink("rest"),
        getLink: function (name) {

            switch (name) {
                case 'update':
                case 'delete':
                case 'create': return this.root + '/products';
                case 'all':
                    return this.root + '/products';
                case 'id':
                    return this.root + '/products/';
                case 'cats':
                    return this.root + '/category';
                case 'upload':
                    return this.root + '/upload/images';
                default:
                    return "false";
                // code block
            }

        }
    };

    $scope.items = [];
    $scope.form = {};
    $scope.cats = [];


    $scope.intitalize = function () {
        console.log($scope.rest.getLink("all"));
        let link = $scope.rest.getLink("all");
        let catLink = $scope.rest.getLink("cats");
        $http.get(link).then(resp => {
            $scope.items = resp.data
            console.log($scope.items);
        });

        $http.get(catLink).then(resp => {
            $scope.cats = resp.data
            console.log($scope.cats);
        });


    }
    $scope.intitalize();


    $scope.resetForm = function () {
        $scope.form = {
            createdDate: new Date().toLocaleDateString('en-CA', options).replace(", ", " "),
            image: 'sample.png'
        }
    }

    $scope.resetForm();

    $scope.edit = function (item) {
        let link = $scope.rest.getLink("id") + item.id;
        $scope.form = angular.copy(item);
        console.log($scope.form.category.id);
        let form = document.getElementById("productForm");
        form.scrollIntoView({
            behavior: "smooth",
            block: "start",
            inline: "start"
        });

    }

    $scope.createProd = function () {
        let item = angular.copy($scope.form);
        let link = $scope.rest.getLink("create");
        $http.post(link, item).then(resp => {
            $scope.items.push(resp.data);
            $scope.resetForm();
            alert("Successfully Created Product");
        }).catch(error => {
            console.log(error);
        })
    }

    $scope.updatePro = function () {
        let item = angular.copy($scope.form);
        let link = $scope.rest.getLink("update") + '/' + item.id;
        $http.put(link, item)
            .then(resp => {
                let index = $scope.items.findIndex(p => p.id == item.id);
                $scope.items[index] = item;
                alert("Update Successfully")

            })
    }

    $scope.detelePro = function (item) {

        let link = $scope.rest.getLink("delete") + '/' + item.id;
        $http.delete(link)
            .then(resp => {
                let index = $scope.items.findIndex(p => p.id == item.id);
                $scope.items.splice(index, 1);
                alert("Delete Successfully")
                $scope.resetForm();
            })
    }

    $scope.imageChanged = function (files) {
        var data = new FormData();
        data.append('file', files[0]);
        let link = $scope.rest.getLink("upload");
        console.log(link);
        $http.post(link, data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            console.log(error);
        })


    }



})