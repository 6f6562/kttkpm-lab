import { IMessageBroker } from "../../domain/interfaces/IMessageBroker";
import { MessageReceivedEvent } from "../../domain/events/MessageReceived";

export class MessageReceivedListener {
    constructor(private readonly broker: IMessageBroker) {}

    async listen(callback: (event: MessageReceivedEvent) => void): Promise<void> {
        await this.broker.subscribe((message) => {
            // Chuyển đổi thành MessageReceivedEvent
            const event = new MessageReceivedEvent(message);
            
            // Thông báo cho lớp Presentation
            callback(event);
        });
    }
}
