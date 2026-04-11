const mongoose = require("mongoose");
const repo = require("./order.repo");

async function createOrder(item) {
    if (!item || typeof item !== "string" || !item.trim()) {
        throw new Error("item là bắt buộc (chuỗi không rỗng)");
    }
    return await repo.create(item.trim());
}

async function getOrders() {
    return await repo.getAll();
}

async function getOrderById(id) {
    if (!mongoose.Types.ObjectId.isValid(id)) {
        throw new Error("ID đơn hàng không hợp lệ");
    }
    const order = await repo.findById(id);
    if (!order) throw new Error("Không tìm thấy đơn hàng");
    return order;
}

module.exports = { createOrder, getOrders, getOrderById };
