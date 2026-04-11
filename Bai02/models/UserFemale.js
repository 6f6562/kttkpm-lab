// models/UserFemale.js
const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');

const UserFemale = sequelize.define('UserFemale', {
  id: { type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true },
  name: DataTypes.STRING,
  age: DataTypes.INTEGER
}, { tableName: 'table_user_02', timestamps: false });

module.exports = UserFemale;