const path = require("path");
const express = require("express");

const { connectDB } = require("./config/db");

const app = express();

app.use(express.json());
app.use(express.static(path.join(__dirname, "public")));

async function startServer() {
    try {
        await connectDB();

        const orderController = require("./modules/order/order.controller");
        const paymentController = require("./modules/payment/payment.controller");
        const shippingController = require("./modules/shipping/shipping.controller");

        app.use("/orders", orderController);
        app.use("/payments", paymentController);
        app.use("/shipping", shippingController);

        app.get("/api/health", (_req, res) => {
            res.json({ ok: true, service: "order-payment-shipping (1 DB)" });
        });

        const PORT = process.env.PORT || 3000;
        app.listen(PORT, () => {
            console.log(`🚀 Monolith (service modules) http://localhost:${PORT}`);
            console.log(`   Giao diện demo: http://localhost:${PORT}/`);
        });
    } catch (err) {
        console.error("❌ Server start error:", err);
        process.exit(1);
    }
}

startServer();
