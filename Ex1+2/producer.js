const amqp = require('amqplib');

const QUEUE_NAME = 'task_queue';
const RABBITMQ_URL = 'amqp://127.0.0.1:5672';

async function sendMessage(msg) {
    try {
        const connection = await amqp.connect(RABBITMQ_URL);
        const channel = await connection.createChannel();

        await channel.assertQueue(QUEUE_NAME, {
            durable: true // Queue survives broker restart
        });

        const msgBuffer = Buffer.from(JSON.stringify(msg));
        channel.sendToQueue(QUEUE_NAME, msgBuffer, {
            persistent: true // Message survives broker restart
        });

        console.log(" [Producer] Sent '%s'", JSON.stringify(msg));

        setTimeout(() => {
            connection.close();
        }, 500);

        return { status: 'success', message: 'Message sent to queue' };
    } catch (error) {
        console.error(" [Producer] Error:", error);
        return { status: 'error', message: error.message };
    }
}

module.exports = { sendMessage };
