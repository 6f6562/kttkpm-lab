const Order = require('../../domain/entities/Order');

class CreateOrderSync {
    constructor(orderRepository, mailer) {
        this.orderRepository = orderRepository;
        this.mailer = mailer;
    }

    async execute(orderData) {
        // 1. Create Domain Entity
        // Note: In a real app, validation happens here or in the entity
        const order = new Order(orderData);

        // 2. Save to Repository (Wait for DB)
        const savedOrder = await this.orderRepository.save(order);
        console.log(`[CreateOrderSync] Order saved with ID: ${savedOrder.id}`);

        // 3. Send Email (Wait for Mailer)
        await this.mailer.sendMail(savedOrder.email, `Order ${savedOrder.id} created successfully.`);
        console.log(`[CreateOrderSync] Email sent for Order ID: ${savedOrder.id}`);

        return savedOrder;
    }
}

module.exports = CreateOrderSync;
