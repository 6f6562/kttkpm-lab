const sequelize = require("../config/database");

require("../models");

const UserMale = require("../models/UserMale");
const UserFemale = require("../models/UserFemale");
const UserMain = require("../models/UserMain");
const UserProfilePic = require("../models/UserProfilePic");
const UserUnder30 = require("../models/UserUnder30");
const User30Above = require("../models/User30Above");

async function seed() {
  const soft = process.argv.includes("--soft");
  await sequelize.sync({ force: !soft });

  await UserMale.bulkCreate([
    { name: "An", age: 25 },
    { name: "Binh", age: 30 },
  ]);

  await UserFemale.bulkCreate([
    { name: "Lan", age: 22 },
    { name: "Hoa", age: 28 },
  ]);

  await UserMain.bulkCreate([
    { name: "Minh", age: 20 },
    { name: "Dung", age: 35 },
  ]);

  const mains = await UserMain.findAll({ order: [["id", "ASC"]] });

  await UserProfilePic.create({
    userId: mains[0].id,
    profilePic: Buffer.from("fakeimage-demo-bytes"),
  });

  const plainUsers = mains.map((u) => u.get({ plain: true }));

  await UserUnder30.bulkCreate(plainUsers.filter((u) => u.age < 30));
  await User30Above.bulkCreate(plainUsers.filter((u) => u.age >= 30));

  console.log(
    soft
      ? "Seed completed (soft — không xóa bảng; có thể trùng dữ liệu nếu chạy lại)."
      : "Seed completed (đã sync force — dữ liệu mới hoàn toàn)."
  );
  process.exit(0);
}

seed().catch((e) => {
  console.error(e);
  process.exit(1);
});
