class Order {
    constructor({ id = null, product, email, status = 'pending' }) {
        this.id = id;
        this.product = product;
        this.email = email;
        this.status = status;
    }
}

module.exports = Order;
