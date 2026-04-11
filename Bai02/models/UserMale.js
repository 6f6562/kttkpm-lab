// models/UserMale.js
const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');

const UserMale = sequelize.define('UserMale', {
  id: { type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true },
  name: DataTypes.STRING,
  age: DataTypes.INTEGER
}, { tableName: 'table_user_01', timestamps: false });

module.exports = UserMale;