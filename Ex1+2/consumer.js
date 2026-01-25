const amqp = require('amqplib');

const QUEUE_NAME = 'task_queue';
const RABBITMQ_URL = 'amqp://127.0.0.1:5672';

async function startConsumer() {
    try {
        const connection = await amqp.connect(RABBITMQ_URL);
        const channel = await connection.createChannel();

        await channel.assertQueue(QUEUE_NAME, {
            durable: true
        });

        channel.prefetch(1);

        console.log(" [*] Waiting for messages in %s. To exit press CTRL+C", QUEUE_NAME);

        channel.consume(QUEUE_NAME, (msg) => {
            if (msg !== null) {
                const content = msg.content.toString();
                console.log(" [Consumer] Received '%s'", content);

                const processingTime = 2000;
                setTimeout(() => {
                    console.log(" [Consumer] Done processing '%s'", content);
                    channel.ack(msg);
                }, processingTime);
            }
        }, {
            noAck: false
        });

    } catch (error) {
        console.error(" [Consumer] Error:", error);
    }
}

if (require.main === module) {
    startConsumer();
}

module.exports = { startConsumer };
