require("./UserMale");
require("./UserFemale");
require("./UserUnder30");
require("./User30Above");

const UserMain = require("./UserMain");
const UserProfilePic = require("./UserProfilePic");

UserMain.hasOne(UserProfilePic, {
  foreignKey: "userId",
  sourceKey: "id",
});

UserProfilePic.belongsTo(UserMain, {
  foreignKey: "userId",
  targetKey: "id",
});

module.exports = {
  UserMain,
  UserProfilePic,
};
