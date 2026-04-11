const express = require("express");
const sequelize = require("./config/database");
const userRoutes = require("./routes/userRoutes");

require("./models");

const app = express();
app.use(express.json());

const PORT = Number(process.env.PORT) || 3000;

app.get("/", (req, res) => {
  const host = req.get("host") || `localhost:${PORT}`;
  const proto = req.get("x-forwarded-proto") || req.protocol || "http";
  const base = `${proto}://${host}`;
  res.json({
    title: "Bai02 — demo phân vùng CSDL (Sequelize + MariaDB)",
    partitions: {
      horizontal_gender: {
        desc: "Nam / nữ lưu ở hai bảng khác nhau",
        tables: ["table_user_01 (nam)", "table_user_02 (nữ)"],
        try: [`${base}/users/gender/M`, `${base}/users/gender/F`],
      },
      functional_age: {
        desc: "Routing theo tuổi tới bảng tương ứng",
        tables: ["user_under_30", "user_30_above"],
        try: [`${base}/users/age/25`, `${base}/users/age/35`],
      },
      vertical: {
        desc: "Cột nhạy cảm (ảnh) tách bảng user_profile_pic, join user_main",
        tables: ["user_main", "user_profile_pic"],
        try: [`${base}/users/vertical`],
      },
    },
    hint: "Chạy `npm run seed` (hoặc trong Docker xem README.md) trước khi gọi API nếu chưa có dữ liệu.",
  });
});

app.use("/users", userRoutes);

async function main() {
  await sequelize.authenticate();
  await sequelize.sync();
  app.listen(PORT, "0.0.0.0", () => {
    console.log(`Bai02 listening at http://localhost:${PORT}`);
  });
}

main().catch((err) => {
  console.error("Không khởi động được:", err.message);
  process.exit(1);
});
