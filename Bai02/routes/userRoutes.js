const express = require("express");
const router = express.Router();

const UserMale = require("../models/UserMale");
const UserFemale = require("../models/UserFemale");
const UserUnder30 = require("../models/UserUnder30");
const User30Above = require("../models/User30Above");
const { UserMain, UserProfilePic } = require("../models");

function summarizeBlob(buf) {
  if (!buf) return null;
  const b = Buffer.isBuffer(buf) ? buf : Buffer.from(buf);
  return { length: b.length, preview: b.toString("base64").slice(0, 32) + "…" };
}

/** Phân vùng ngang theo giới: nam → table_user_01, nữ → table_user_02 */
router.get("/gender/:g", async (req, res) => {
  const g = String(req.params.g).toUpperCase();
  if (g === "M") return res.json(await UserMale.findAll());
  if (g === "F") return res.json(await UserFemale.findAll());
  return res.status(400).json({ error: "Dùng M hoặc F" });
});

/** Phân vùng theo hàm (tuổi): dưới 30 → user_under_30, từ 30 trở lên → user_30_above */
router.get("/age/:age", async (req, res) => {
  const age = Number.parseInt(req.params.age, 10);
  if (Number.isNaN(age) || age < 0) {
    return res.status(400).json({ error: "age phải là số không âm" });
  }
  if (age < 30) return res.json(await UserUnder30.findAll());
  return res.json(await User30Above.findAll());
});

/** Phân vùng dọc: thông tin chính user_main + ảnh user_profile_pic */
router.get("/vertical", async (req, res) => {
  try {
    const users = await UserMain.findAll({
      include: [{ model: UserProfilePic }],
      order: [["id", "ASC"]],
    });
    const body = users.map((u) => {
      const j = u.toJSON();
      if (j.UserProfilePic?.profilePic != null) {
        j.UserProfilePic.profilePic = summarizeBlob(j.UserProfilePic.profilePic);
      }
      return j;
    });
    res.json(body);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: err.message });
  }
});

module.exports = router;
