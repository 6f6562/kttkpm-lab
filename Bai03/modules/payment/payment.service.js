const mongoose = require("mongoose");
const repo = require("../order/order.repo");

async function payOrder(orderId) {
    if (!mongoose.Types.ObjectId.isValid(orderId)) {
        throw new Error("ID đơn hàng không hợp lệ");
    }
    const order = await repo.findById(orderId);

    if (!order) throw new Error("Không tìm thấy đơn hàng");
    if (order.status !== "CREATED") throw new Error("Chỉ thanh toán được khi đơn ở trạng thái CREATED");

    return await repo.updateStatus(orderId, "PAID");
}

module.exports = { payOrder };