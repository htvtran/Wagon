const cartapp = angular.module("myapp", []);

cartapp.controller("cart-ctrl", function ($scope, $http, $document) {
    $scope.items = [];
    $document.ready(function () {

        console.log($scope.cart.items);
        $scope.loadToMiniCart();
        console.log($scope.items);

    });
    $scope.squantity = 1;
    $scope.u = 'ddd';
    $scope.cart = {
        items: [],
        add(id, $event) {
            this.add(id, 1, $event);
            return false;
        },

        add(id, quantitiy, $event) {

            $event.preventDefault();
            console.log(id);
            let item = this.items.find(item => item.id == id);
            if (item) {
                item.quantity += quantitiy;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/product/detail/${id}`).then(resp => {
                    let i = resp.data;
                    i.quantity = quantitiy;
                    this.items.push(i);
                    this.saveToLocalStorage();
                    // console.log(JSON.parse(JSON.stringify(resp.data)));
                }

                )
            }
            console.dir(this.items);

            // alert('add')
            return false;
        },
        remove(id) {
            const index = this.items.indexOf(id);
            if (index > -1) { // only splice array when item is found
                this.items.splice(index, 1); // 2nd parameter means remove one item only
            }
            console.log('removed');
            this.saveToLocalStorage();

        },
        clear() { },
        total_of(item) {
            let i = this.items.find(i => i.id === item.id);
            let p = this.price_of(item);

            return Math.round((i.quantity * p) * 100) / 100;
        },
        price_of(item) {
            let i = this.items.find(i => i.id === item.id);
            if (i.discount !== null || i.discount != 0) {

                return ((100 - i.discount) * 0.01) * i.price;
            }
            return i.price;
        },
        get total() {
            let total = 0.0;
            let i = this.items.forEach(i => {
                if (i.selected) total += this.total_of(i)
            });
            return total;
        },
        get amount() {
            let amount = 0;
            this.items.forEach((i) => { amount += i.quantity });
            console.log("total item ", amount);
            return amount;
        },
        saveToLocalStorage() {
            console.log('saved to local');
            let json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cartObj", json);

            $scope.loadToMiniCart();
        },
        loadFromLocalStorage() {
            let json = localStorage.getItem("cartObj");
            if (json == null) {
                $scope.cart.items = [];
                return [];
            }
            $scope.cart.items = JSON.parse(json);

            return JSON.parse(json);
        }
    }

    $scope.getLink = function ($event) {
        let targetName = $event.target.tagName;
        if (targetName.toLowerCase() === 'span') return $event.target.parentElement;
        return $event.target;
    }

    $scope.loadToMiniCart = function () {
        // minicart-amount
        document.getElementById('minicart-amount').innerText = $scope.cart.amount;
        $scope.items = $scope.cart.items;

    }

    $scope.addCartFromDetail = function (id, $event) {
        let quantity = parseInt($('#quantity').val());
        $scope.cart.add(id, quantity, $event);
    }

    $scope.disableAddCart = function () {
        // console.log('dis');
        let quantity = parseInt($('#quantity').val());
        if (quantity == 0 || isNaN(quantity)) {
            $('#add-cart-btn').prop('disabled', true);
        } else {
            $('#add-cart-btn').prop('disabled', false);
        }
        $scope.squantity = quantity;
        console.log($scope.squantity);
    }

    $scope.order = {}

    $scope.cart.loadFromLocalStorage();
    $scope.loadToMiniCart();
});