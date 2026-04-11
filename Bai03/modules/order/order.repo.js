const Order = require("./order.model");

async function create(item) {
    return await Order.create({ item, status: "CREATED" });
}

async function findById(id) {
    return await Order.findById(id);
}

async function updateStatus(id, status) {
    return await Order.findByIdAndUpdate(id, { status }, { new: true });
}

async function getAll() {
    return await Order.find().sort({ createdAt: -1 });
}

module.exports = { create, findById, updateStatus, getAll };
