const mongoose = require("mongoose");

const OrderSchema = new mongoose.Schema(
    {
        item: { type: String, required: true, trim: true },
        status: {
            type: String,
            enum: ["CREATED", "PAID", "SHIPPED", "DELIVERED"],
            default: "CREATED",
        },
    },
    { timestamps: true }
);

module.exports = mongoose.model("Order", OrderSchema);
