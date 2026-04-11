// models/UserMain.js
const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');

const UserMain = sequelize.define('UserMain', {
  id: { type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true },
  name: DataTypes.STRING,
  age: DataTypes.INTEGER
}, { tableName: 'user_main', timestamps: false });

module.exports = UserMain;