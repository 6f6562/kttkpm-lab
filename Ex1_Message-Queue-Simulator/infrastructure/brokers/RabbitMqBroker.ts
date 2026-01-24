import * as amqp from 'amqplib';
import { EventEmitter } from 'events';
import { IMessageBroker } from '../../domain/interfaces/IMessageBroker';
import { Message } from '../../domain/entities/Message';
import { MessageSentEvent } from '../../domain/events/MessageSent';
import { RabbitMqConfig } from '../shared/RabbitMqConfig';

export class RabbitMqBroker extends EventEmitter implements IMessageBroker {
    private connection: amqp.Connection | null = null;
    private channel: amqp.Channel | null = null;

    async connect(): Promise<void> {
        try {
            this.connection = await amqp.connect(RabbitMqConfig.url);
            this.channel = await this.connection.createChannel();

            await this.channel.assertExchange(RabbitMqConfig.exchange, 'direct', { durable: true });
            await this.channel.assertQueue(RabbitMqConfig.queue, { durable: true });
            await this.channel.bindQueue(RabbitMqConfig.queue, RabbitMqConfig.exchange, RabbitMqConfig.routingKey);

            console.log('Connected to RabbitMQ');
        } catch (error) {
            console.error('Failed to connect to RabbitMQ:', error);
        }
    }

    async publish(message: Message): Promise<void> {
        if (!this.channel) {
            await this.connect();
        }

        if (this.channel) {
            const buffer = Buffer.from(JSON.stringify(message));
            const success = this.channel.publish(RabbitMqConfig.exchange, RabbitMqConfig.routingKey, buffer);

            if (success) {
                // Emit MessageSentEvent
                // Note: Normally we'd use the message ID returning from the server or generate one.
                // For simulation, we generate a mock ID.
                const event = new MessageSentEvent(`msg-${Date.now()}`, new Date());
                this.emit('message_sent', event);
                console.log('Message published to RabbitMQ');
            } else {
                console.error('Failed to publish message');
            }
        }
    }

    async subscribe(callback: (message: Message) => void): Promise<void> {
        if (!this.channel) {
            await this.connect();
        }

        if (this.channel) {
            await this.channel.consume(RabbitMqConfig.queue, (msg) => {
                if (msg) {
                    try {
                        const content = JSON.parse(msg.content.toString());
                        // Reconstruct Message entity. 
                        // JSON.stringify on the entity with private fields (_name, etc.) outputs those fields.
                        const message = new Message(
                            content._name,
                            content._message,
                            new Date(content._createdTime)
                        );
                        
                        callback(message);
                        this.channel!.ack(msg);
                    } catch (err) {
                        console.error('Error processing message:', err);
                        this.channel!.nack(msg, false, false); // Reject without requeue if invalid
                    }
                }
            });
            console.log('Subscribed to queue:', RabbitMqConfig.queue);
        }
    }
}
