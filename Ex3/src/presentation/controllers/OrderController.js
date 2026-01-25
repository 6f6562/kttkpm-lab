const CreateOrderSync = require('../../application/use-cases/CreateOrderSync');
const SequelizeOrderRepository = require('../../infrastructure/database/SequelizeOrderRepository');
const SmtpMailer = require('../../infrastructure/services/SmtpMailer');

class OrderController {
    async createOrderSync(req, res, next) {
        const { product, email } = req.body;

        // Instantiate dependencies (In a real app, use DI container)
        const orderRepository = new SequelizeOrderRepository();
        const mailer = new SmtpMailer();
        const createOrderSyncExample = new CreateOrderSync(orderRepository, mailer);

        try {
            const startTime = Date.now();

            // Execute Use Case
            const order = await createOrderSyncExample.execute({ product, email });

            const endTime = Date.now();
            const duration = (endTime - startTime) / 1000;

            // Render View (assuming 'index' or a result page)
            // We pass the result and duration to the view
            res.render('index', {
                title: 'Synchronous Order Result',
                message: `Order created successfully in ${duration} seconds.`,
                order: order
            });
        } catch (error) {
            next(error);
        }
    }
}

module.exports = new OrderController();
