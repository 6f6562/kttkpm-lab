// models/UserUnder30.js
const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');

const UserUnder30 = sequelize.define('UserUnder30', {
  id: { type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true }, // <-- thêm autoIncrement
  name: DataTypes.STRING,
  age: DataTypes.INTEGER
}, { tableName: 'user_under_30', timestamps: false });

module.exports = UserUnder30;