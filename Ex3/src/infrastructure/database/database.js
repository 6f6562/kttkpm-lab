const { Sequelize } = require('sequelize');

const sequelize = new Sequelize(
    process.env.DB_NAME || 'test',
    process.env.DB_USER || 'root',
    process.env.DB_PASSWORD || 'root',
    {
        host: process.env.DB_HOST || 'localhost',
        dialect: 'mariadb',
        port: process.env.DB_PORT || 3306,
        logging: false,
    }
);

module.exports = sequelize;
