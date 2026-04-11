const mongoose = require("mongoose");
const Order = require("./modules/order/order.model");

const { MONGO_URI } = require("./config/db");

async function seed() {
    try {
        await mongoose.connect(MONGO_URI);
        console.log("✅ MongoDB connected");

        await Order.deleteMany({});
        console.log("🗑️  Đã xóa dữ liệu Order cũ (nếu có)");

        const orders = [
            { item: "Laptop", status: "CREATED" },
            { item: "Phone", status: "PAID" },
            { item: "Keyboard", status: "SHIPPED" },
        ];

        const result = await Order.insertMany(orders);
        console.log("✅ Đã chèn mẫu:", result.length, "đơn");

        await mongoose.disconnect();
        console.log("✅ MongoDB disconnected");
    } catch (err) {
        console.error("❌ Seed error:", err);
        process.exit(1);
    }
}

seed();
