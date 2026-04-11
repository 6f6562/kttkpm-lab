const path = require("path");
require("dotenv").config({ path: path.join(__dirname, "..", ".env") });

const { Sequelize } = require("sequelize");

const database = process.env.DB_NAME || "partition_demo";
const username = process.env.DB_USER || "root";
const password = process.env.DB_PASSWORD ?? "root";
const host = process.env.DB_HOST || "localhost";
const port = Number(process.env.DB_PORT) || 3306;
const dialect = (process.env.DB_DIALECT || "mysql").toLowerCase();

if (dialect !== "mysql" && dialect !== "mariadb") {
  throw new Error(
    `DB_DIALECT phải là "mysql" hoặc "mariadb" (nhận được: ${dialect})`
  );
}

const options = {
  host,
  port,
  dialect,
  logging: process.env.DB_LOGGING === "1" ? console.log : false,
};

if (dialect === "mysql") {
  options.dialectModule = require("mysql2");
}

const sequelize = new Sequelize(database, username, password, options);

module.exports = sequelize;
