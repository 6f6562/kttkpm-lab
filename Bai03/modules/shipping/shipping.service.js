const mongoose = require("mongoose");
const repo = require("../order/order.repo");

async function shipOrder(orderId) {
    if (!mongoose.Types.ObjectId.isValid(orderId)) {
        throw new Error("ID đơn hàng không hợp lệ");
    }
    const order = await repo.findById(orderId);

    if (!order) throw new Error("Không tìm thấy đơn hàng");
    if (order.status !== "PAID") throw new Error("Phải thanh toán (PAID) trước khi giao hàng");

    return await repo.updateStatus(orderId, "SHIPPED");
}

module.exports = { shipOrder };