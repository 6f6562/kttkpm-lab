// models/User30Above.js
const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');

const User30Above = sequelize.define('User30Above', {
  id: { type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true }, // <-- thêm autoIncrement
  name: DataTypes.STRING,
  age: DataTypes.INTEGER
}, { tableName: 'user_30_above', timestamps: false });

module.exports = User30Above;