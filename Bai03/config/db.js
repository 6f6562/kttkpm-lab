const mongoose = require("mongoose");

const MONGO_URI = process.env.MONGO_URI || "mongodb://127.0.0.1:27017/ktvtkpm";

async function connectDB() {
    await mongoose.connect(MONGO_URI);
    console.log("✅ MongoDB connected:", MONGO_URI.replace(/\/\/.*@/, "//***@"));
}

module.exports = { connectDB, MONGO_URI };
