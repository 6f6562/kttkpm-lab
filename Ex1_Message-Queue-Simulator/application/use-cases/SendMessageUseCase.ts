import { IMessageBroker } from "../../domain/interfaces/IMessageBroker";
import { Message } from "../../domain/entities/Message";
import { MessageCreatedEvent } from "../../domain/events/MessageCreated";

export class SendMessageUseCase {
    constructor(private readonly broker: IMessageBroker) {}

    async execute(name: string, messageContent: string): Promise<void> {
        // 1. Khởi tạo Entity Message
        const message = new Message(name, messageContent, new Date());

        // 2. Bắn sự kiện MessageCreatedEvent nội bộ (Simulation)
        const event = new MessageCreatedEvent(message);
        console.log("Event Emitted:", event);

        // 3. Gọi interface IMessageBroker.publish(message)
        await this.broker.publish(message);
    }
}
