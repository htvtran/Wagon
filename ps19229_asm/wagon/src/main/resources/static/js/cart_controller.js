const cartapp = angular.module("myapp", []);



cartapp.controller("cart-ctrl", function ($scope, $http, $document) {
    $scope.items = [];
    $scope.link = "";
    $document.ready(function () {

        console.log($scope.cart.items);
        $scope.loadToMiniCart();
        console.log($scope.items);

        console.log('sco li ', link);;

    });
    $scope.squantity = 1;
    $scope.u = 'ddd';
    $scope.cart = {
        items: [],
        addmini(id, $event) {
            console.log('event');
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
                console.log("failed");
                $http.get(`${root_rest}/rest/product/detail/${id}`).then(resp => {
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
            this.saveToLocalStorage();

        },
        clear() {
            console.log('clear');
            this.items = this.items.filter(i => i.selected === false);
            console.log('clear ', this.items);
            this.saveToLocalStorage();
        },
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

            return Math.round((total) * 100) / 100;
        },
        get amount() {
            let amount = 0;
            this.items.forEach((i) => { amount += i.quantity });

            return amount;
        },
        get selectedAmount() {
            let amount = 0;
            this.items.forEach((i) => { if (i.selected) amount += i.quantity });
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

    $scope.order = {
        get userOrder() {
            return new Order('123 Quatar', $scope.cart);
        }
    }

    $scope.purchasedOrder = function () {
        let order = $scope.order.userOrder;
        console.log('user order', order);
        let location = 'order/purchase';
        let url = `${root_rest}/rest/${location}`;
        let data = order
        console.log(url);
        $http.post(url, data, { headers: { 'responseType': 'text' } })
            .then(resp => {

                alert("Thank you, your order's been purchase");

                $scope.cart.clear();
                window.location.href = `${root_rest}/account/orders`;

            })

            .catch(err => { console.log(err); });
    }

    $scope.cart.loadFromLocalStorage();
    $scope.loadToMiniCart();
    $scope.link = link;


});

const options = {
    year: "numeric",
    month: "numeric",
    day: "numeric",
    hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: false
};

function Order(address, cart) {
    //new Intl.DateTimeFormat('en-US', options).format(date)
    this.date = new Date().toLocaleDateString('en-CA', options).replace(", ", " ");
    this.address = address;
    this.account = { username: getUserName() };
    this.total = cart.total;
    this.orderDetail = getOrderDetail(cart);

}

function getUserName() {
    return document.getElementById("data-username").dataset.username;
}
function getOrderDetail(cart) {
    let list = [];
    let o = cart.items.map(i => {
        if (i.selected)
            list.push(new OrderDetail(i))
    }
    );
    // console.log(list);
    return list;
}

function OrderDetail(item) {
    this.product = { id: item.id };
    this.price = item.price;
    this.quantity = item.quantity
        ;

}