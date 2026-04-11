const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');

const UserProfilePic = sequelize.define('UserProfilePic', {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  userId: DataTypes.INTEGER,
  profilePic: DataTypes.BLOB('long')
}, {
  tableName: 'user_profile_pic',
  timestamps: false
});

module.exports = UserProfilePic;