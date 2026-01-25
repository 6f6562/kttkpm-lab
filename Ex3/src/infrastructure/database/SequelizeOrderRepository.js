const IOrderRepository = require('../../domain/repositories/IOrderRepository');
const sequelize = require('./database');
const { DataTypes } = require('sequelize');

// Define the Order model internally for Sequelize
const OrderModel = sequelize.define('Order', {
    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    product: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    email: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    status: {
        type: DataTypes.STRING,
        defaultValue: 'pending',
    },
}, {
    tableName: 'orders',
    timestamps: true,
});

class SequelizeOrderRepository extends IOrderRepository {
    async save(order) {
        // Sync the model (in production, use migrations)
        await OrderModel.sync();

        // Create the record
        const newOrder = await OrderModel.create({
            product: order.product,
            email: order.email,
            status: order.status
        });

        // Return the domain entity (or similar)
        // Here we update the ID from the DB
        order.id = newOrder.id;
        return order;
    }
}

module.exports = SequelizeOrderRepository;
